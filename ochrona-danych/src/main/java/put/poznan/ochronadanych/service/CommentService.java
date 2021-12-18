package put.poznan.ochronadanych.service;


import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import put.poznan.ochronadanych.dto.CommentsDto;
import put.poznan.ochronadanych.exception.PutODException;
import put.poznan.ochronadanych.model.Comment;
import put.poznan.ochronadanych.model.NotificationEmail;
import put.poznan.ochronadanych.model.Post;
import put.poznan.ochronadanych.repository.CommentRepository;
import put.poznan.ochronadanych.repository.PostRepository;
import put.poznan.ochronadanych.repository.UserRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class CommentService {
    private static final String POST_URL = "";
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;


    public void save(CommentsDto commentsDto) throws PutODException {
        Post post = postRepository.findById(commentsDto.getPostId())
                .orElseThrow(() -> new PutODException("post with id "+commentsDto.getPostId().toString()+"not found!"));
        Comment comment = commentDtoToComment(commentsDto);
        commentRepository.save(comment);

    }

    public List<CommentsDto> getAllCommentsForPost(Long postId) throws PutODException {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PutODException("post with id not foun id-"+postId));
        return commentRepository.findByPost(post)
                .stream()
                .map(this::commentsDtoToComment).collect(toList());
    }

    public Comment commentDtoToComment(CommentsDto commentsDto) throws PutODException {
        return Comment.builder().text(commentsDto.getText())
                .post(postRepository.findById(commentsDto.getPostId())
                        .orElseThrow(() -> new PutODException("post with id "+commentsDto.getPostId().toString()+"not found!")))
                .webUser(userRepository.findByUsername(commentsDto.getUserName())
                        .orElseThrow(() -> new PutODException("user with name "+commentsDto.getUserName()+"not found!"))).build();

    }

    public CommentsDto commentsDtoToComment(Comment comment){
        return CommentsDto.builder()
                .id(comment.getId())
                .postId(comment.getPost().getPostId())
                .text(comment.getText())
                .userName(comment.getWebUser().getUsername())
                .build();
    }


}
