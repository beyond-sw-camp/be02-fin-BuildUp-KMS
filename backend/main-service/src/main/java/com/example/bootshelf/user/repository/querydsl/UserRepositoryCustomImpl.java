package com.example.bootshelf.user.repository.querydsl;


import com.example.bootshelf.user.model.entity.QUser;
import com.example.bootshelf.user.model.entity.User;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.Optional;

public class UserRepositoryCustomImpl extends QuerydslRepositorySupport implements UserRepositoryCustom {
    public UserRepositoryCustomImpl() {
        super(User.class);
    }


    @Override
    public Optional<User> findUser(String email) {
        QUser user = new QUser("user");


        Optional<User> result = Optional.ofNullable(from(user)
                .where(user.email.eq(email))
                .fetchOne()
        );

        return result;
    }
}
