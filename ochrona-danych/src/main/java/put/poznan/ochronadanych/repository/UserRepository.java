package put.poznan.ochronadanych.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import put.poznan.ochronadanych.model.WebUser;

@Repository
public interface UserRepository extends JpaRepository<WebUser, Long> {

}
