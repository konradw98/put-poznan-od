package put.poznan.ochronadanych.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import put.poznan.ochronadanych.dto.TopicDto;
import put.poznan.ochronadanych.service.TopicService;

import java.util.List;

@RestController
@RequestMapping("/api/topic")
@AllArgsConstructor
@Slf4j
public class TopicController {

    private final TopicService topicService;

    @PostMapping
    public ResponseEntity<TopicDto> createTopic(@RequestBody TopicDto topicDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(topicService.save(topicDto));
    }

    @GetMapping
    public ResponseEntity<List<TopicDto>> getAllTopic() {
      return ResponseEntity.status(HttpStatus.OK).body(topicService.getAll());
    }
}
