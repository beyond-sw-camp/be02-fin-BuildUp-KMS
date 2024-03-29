package com.example.bootshelf.user.repository;

import com.example.bootshelf.user.model.entity.UserRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRefreshTokenRepository extends JpaRepository<UserRefreshToken, Integer> {
    Optional<UserRefreshToken> findByUserIdxAndReissueCountLessThan(Integer idx, long count);
}
