package com.example.bootshelf.user.repository;

import com.example.bootshelf.user.model.entity.UserRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRefreshTokenRepository extends JpaRepository<UserRefreshToken, Integer> {
    Optional<UserRefreshToken> findByUserIdx(Integer idx);
}
