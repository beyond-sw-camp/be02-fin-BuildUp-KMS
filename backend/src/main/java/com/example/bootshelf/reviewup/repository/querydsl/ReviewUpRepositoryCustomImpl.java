package com.example.bootshelf.reviewup.repository.querydsl;

import com.example.bootshelf.reviewup.model.entity.QReviewUp;
import com.example.bootshelf.reviewup.model.entity.ReviewUp;
import com.example.bootshelf.user.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewUpRepositoryCustomImpl extends QuerydslRepositorySupport implements ReviewUpRepositoryCustom {
    public ReviewUpRepositoryCustomImpl() {
        super(ReviewUp.class);
    }


    @Override
    public Page<ReviewUp> findByUser(User user, Pageable pageable) {
        QReviewUp reviewUp = new QReviewUp("reviewUp");

        List<ReviewUp> result = from(reviewUp)
                .leftJoin(reviewUp.review).fetchJoin()
                .leftJoin(reviewUp.user).fetchJoin()
                .where(reviewUp.status.eq(true))
                .distinct()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch().stream().collect(Collectors.toList());

        return new PageImpl<>(result, pageable, pageable.getPageSize());
    }

    @Override
    public ReviewUp findByUserIdxAndReviewIdx(Integer userIdx, Integer reviewIdx) {
        QReviewUp reviewUp = new QReviewUp("reviewUp");

        ReviewUp result = from(reviewUp)
                .leftJoin(reviewUp.user).fetchJoin()
                .leftJoin(reviewUp.review).fetchJoin()
                .where(reviewUp.user.idx.eq(userIdx).and(reviewUp.review.idx.eq(reviewIdx)))
                .fetchOne();

        return result;
    }
}
