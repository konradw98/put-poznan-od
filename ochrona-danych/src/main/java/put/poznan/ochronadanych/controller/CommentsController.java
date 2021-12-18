package put.poznan.ochronadanych.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import put.poznan.ochronadanych.dto.CommentsDto;
import put.poznan.ochronadanych.exception.PutODException;
import put.poznan.ochronadanych.service.CommentService;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/comments/")
@AllArgsConstructor
public class CommentsController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Void> createComment(@RequestBody CommentsDto commentsDto) throws PutODException {
        commentService.save(commentsDto);
        return new ResponseEntity<>(CREATED);
    }

   @GetMapping("/byPost/{postId}")
    public ResponseEntity<List<CommentsDto>> getAllCommentsForPost(@PathVariable Long postId) throws PutODException {
        return ResponseEntity.status(OK)
                .body(commentService.getAllCommentsForPost(postId));
    }




}
