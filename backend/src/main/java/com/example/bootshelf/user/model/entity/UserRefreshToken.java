package com.example.bootshelf.user.model.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
public class UserRefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    private Integer userIdx;

    private String refreshToken;

    public boolean validateRefreshToken(String refreshToken){
        return this.refreshToken.equals(refreshToken);
    }

    public UserRefreshToken(Integer userIdx, String token) {
        this.userIdx = userIdx;
        this.refreshToken = token;
    }
}
