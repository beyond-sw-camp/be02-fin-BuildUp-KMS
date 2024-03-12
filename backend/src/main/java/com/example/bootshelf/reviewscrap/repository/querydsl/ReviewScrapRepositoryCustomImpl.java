package com.example.bootshelf.reviewscrap.repository.querydsl;

import com.example.bootshelf.reviewscrap.model.QReviewScrap;
import com.example.bootshelf.reviewscrap.model.ReviewScrap;
import com.example.bootshelf.user.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewScrapRepositoryCustomImpl extends QuerydslRepositorySupport implements ReviewScrapRepositoryCustom {
    public ReviewScrapRepositoryCustomImpl() {
        super(ReviewScrap.class);
    }

    @Override
    public Page<ReviewScrap> findByUserIdx(User user, Pageable pageable) {
        QReviewScrap reviewScrap = new QReviewScrap("reviewScrap");

        List<ReviewScrap> result = from(reviewScrap)
                .leftJoin(reviewScrap.review).fetchJoin()
                .leftJoin(reviewScrap.user).fetchJoin()
                .where(reviewScrap.status.eq(true))
                .distinct()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch().stream().collect(Collectors.toList());


        return new PageImpl<>(result, pageable, pageable.getPageSize());
    }
}
