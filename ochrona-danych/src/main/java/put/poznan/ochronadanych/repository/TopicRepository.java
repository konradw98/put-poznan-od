package put.poznan.ochronadanych.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import put.poznan.ochronadanych.model.Topic;

import java.util.Optional;


@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

    Optional<Topic> findByName(String topicName);

}
