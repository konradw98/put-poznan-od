package put.poznan.ochronadanych.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import put.poznan.ochronadanych.model.Comment;

;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}