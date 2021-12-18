package put.poznan.ochronadanych.service;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import put.poznan.ochronadanych.dto.PostRequest;
import put.poznan.ochronadanych.dto.PostResponse;
import put.poznan.ochronadanych.exception.PutODException;
import put.poznan.ochronadanych.model.Post;
import put.poznan.ochronadanych.model.Topic;
import put.poznan.ochronadanych.repository.PostRepository;
import put.poznan.ochronadanych.repository.TopicRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final TopicRepository topicRepository;
    private final AuthService authService;

    public void save(PostRequest postRequest) throws PutODException {

        postRepository.save(mapPostRequestToPost(postRequest));
    }

   @Transactional(readOnly = true)
    public PostResponse getPost(Long id) throws PutODException {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PutODException("POST WITH ID NOT FOUND ID="+id));
        return mapPostToPostResponse(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts(){
        return postRepository.findAll()
                .stream()
                .map(post -> {
                    try {
                        return mapPostToPostResponse(post);
                    } catch (PutODException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .collect(toList());
    }

   @Transactional(readOnly = true)
    public List<PostResponse> getPostsByTopic(Long topicId) throws PutODException {
       System.out.println("id w post servie="+topicId);
        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new PutODException("topic with id not found"+topicId.toString()));
        List<Post> posts = postRepository.findAllByTopic(topic);
        return posts.stream().map(post -> {
            try {
                return mapPostToPostResponse(post);
            } catch (PutODException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(toList());
    }


    public Post mapPostRequestToPost(PostRequest postRequest) throws PutODException {
        return  Post.builder()
                .postName(postRequest.getPostName())
                .postId(postRequest.getPostId())
                .topic(topicRepository.findByName(postRequest.getTopicName())
                        .orElseThrow(() -> new PutODException("topic name with name"+postRequest.getTopicName()+"not found")))
                .webUser(authService.getCurrentUser())
                .url(postRequest.getUrl())
                .description(postRequest.getDescription()).build();

    }

    public PostResponse mapPostToPostResponse(Post post) throws PutODException{
        return PostResponse.builder()
                .postName(post.getPostName())
                .id(post.getPostId())
                .topicName(post.getTopic().getName())
                .description(post.getDescription())
                .userName(post.getWebUser().getUsername())
                .url(post.getUrl()).build();
    }
}
