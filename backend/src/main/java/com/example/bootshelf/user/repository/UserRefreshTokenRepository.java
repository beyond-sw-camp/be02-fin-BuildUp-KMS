package com.example.bootshelf.user.repository;

import com.example.bootshelf.user.model.entity.UserRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRefreshTokenRepository extends JpaRepository<UserRefreshToken, Integer> {
    Optional<UserRefreshToken> findByUserIdx(Integer idx);
    Integer deleteByUserIdx(Integer idx);
}
