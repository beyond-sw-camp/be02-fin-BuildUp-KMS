package com.example.bootshelf.user.repository;


import com.example.bootshelf.user.model.entity.User;
import com.example.bootshelf.user.repository.querydsl.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, UserRepositoryCustom {
    public Optional<User> findByIdx(Integer userIdx);

    public Integer deleteByIdx(Integer idx);
    public Optional<User> findByEmail(String email);
    public Optional<User> findByNickName(String userNickName);
    public Optional<User> findByEmailAndName(String email, String name);
    public Optional<User> findByNameAndNickName(String name, String Nickname);
}
