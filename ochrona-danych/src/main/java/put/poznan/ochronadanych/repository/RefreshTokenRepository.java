package put.poznan.ochronadanych.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import put.poznan.ochronadanych.model.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    void deleteByToken(String token);
}
