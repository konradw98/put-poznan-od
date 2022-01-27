package put.poznan.ochronadanych.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import put.poznan.ochronadanych.model.WebUser;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<WebUser, Long> {

    Optional<WebUser> findByUsername(String username);

}
