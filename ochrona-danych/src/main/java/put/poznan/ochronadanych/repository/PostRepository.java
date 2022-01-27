package put.poznan.ochronadanych.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import put.poznan.ochronadanych.model.Post;
import put.poznan.ochronadanych.model.Topic;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByTopic(Topic topic);


}
