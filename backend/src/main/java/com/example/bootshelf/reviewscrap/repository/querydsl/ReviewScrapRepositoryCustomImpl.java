package com.example.bootshelf.reviewscrap.repository.querydsl;

import com.example.bootshelf.review.model.entity.QReview;
import com.example.bootshelf.reviewscrap.model.QReviewScrap;
import com.example.bootshelf.reviewscrap.model.ReviewScrap;
import com.example.bootshelf.user.model.entity.QUser;
import com.example.bootshelf.user.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ReviewScrapRepositoryCustomImpl extends QuerydslRepositorySupport implements ReviewScrapRepositoryCustom {
    public ReviewScrapRepositoryCustomImpl() {
        super(ReviewScrap.class);
    }

    @Override
    public Page<ReviewScrap> findByUser(User user, Pageable pageable) {
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

    @Override
    public ReviewScrap findByUserIdxAndReviewIdx(Integer userIdx, Integer reviewIdx) {
        QReviewScrap reviewScrap = new QReviewScrap("reviewScrap");
        QReview review = new QReview("review");
        QUser user = new QUser("user");

        ReviewScrap result = from(reviewScrap)
                .leftJoin(reviewScrap.user).fetchJoin()
                .leftJoin(reviewScrap.review).fetchJoin()
                .where(reviewScrap.user.idx.eq(userIdx).and(reviewScrap.review.idx.eq(reviewIdx)))
                .fetchOne();

        return result;
    }


}
