package put.poznan.ochronadanych.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import put.poznan.ochronadanych.dto.PostRequest;
import put.poznan.ochronadanych.dto.PostResponse;
import put.poznan.ochronadanych.exception.PutODException;
import put.poznan.ochronadanych.service.PostService;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;
//@CrossOrigin()
@RestController
@RequestMapping("/api/posts/")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<Void> createPost(@RequestBody PostRequest postRequest) throws PutODException {
        postService.save(postRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

   @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        return status(HttpStatus.OK).body(postService.getAllPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long id) throws PutODException {
        return status(HttpStatus.OK).body(postService.getPost(id));
    }

    @GetMapping("byTopic/{id}")
    public ResponseEntity<List<PostResponse>> getPostsBySubreddit(@PathVariable Long id) throws PutODException {
        System.out.println("ID w post controller="+id);
        return status(HttpStatus.OK).body(postService.getPostsByTopic(id));
    }


}
