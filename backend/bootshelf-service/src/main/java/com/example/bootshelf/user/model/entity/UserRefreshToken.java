package com.example.bootshelf.user.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
public class UserRefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    private Integer userIdx;

    private String refreshToken;

    public boolean validateRefreshToken(String refreshToken){
        return this.refreshToken.equals(refreshToken);
    }
}
