package com.example.bootshelf.user.repository.querydsl;

import com.example.bootshelf.course.Course;
import com.example.bootshelf.user.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserRepositoryCustom {
    Optional<User> findUser(String email);

}
