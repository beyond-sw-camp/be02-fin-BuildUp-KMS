package com.example.bootshelf.user.repository.querydsl;

import com.example.bootshelf.user.model.entity.User;

import java.util.Optional;

public interface UserRepositoryCustom {
    Optional<User> findUser(String email);

}
