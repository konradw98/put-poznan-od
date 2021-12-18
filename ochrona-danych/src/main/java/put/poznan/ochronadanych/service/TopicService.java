package put.poznan.ochronadanych.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import put.poznan.ochronadanych.dto.TopicDto;
import put.poznan.ochronadanych.exception.PutODException;
import put.poznan.ochronadanych.model.Topic;
import put.poznan.ochronadanych.repository.TopicRepository;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class TopicService {

    private final TopicRepository topicRepository;

  
    @Transactional
    public TopicDto save(TopicDto topicDto) {
        Topic saveTopic = topicRepository.save(mapTopicDto(topicDto));
        topicDto.setId(saveTopic.getId());
        return topicDto;
    }

    private Topic mapTopicDto(TopicDto topicDto) {
       return Topic.builder().name(topicDto.getName())
                .description(topicDto.getDescription())
                .build();
    }

    private TopicDto mapToDto(Topic topic){
        return TopicDto.builder().name(topic.getName())
                .id(topic.getId())
                .numberOfPosts(topic.getPosts().size())
                .build();

    }

    @Transactional(readOnly = true )
    public List<TopicDto> getAll() {
        return topicRepository.findAll().stream().map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public TopicDto getTopic(Long id) throws PutODException {
        Topic Topic = topicRepository.findById(id)
                .orElseThrow(() -> new PutODException("No Topic found with ID - " + id));
        return mapToDto(Topic);
    }
}
