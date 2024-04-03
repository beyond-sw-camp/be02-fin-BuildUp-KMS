package com.example.bootshelf.reviewsvc.reviewcommentup.repository.querydsl;

import com.example.bootshelf.boardsvc.boardcommentup.model.entity.BoardCommentUp;
import com.example.bootshelf.reviewsvc.reviewcommentup.model.entity.QReviewCommentUp;
import com.example.bootshelf.reviewsvc.reviewcommentup.model.entity.ReviewCommentUp;
import com.example.bootshelf.user.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewCommentUpRepositoryCustomImpl extends QuerydslRepositorySupport implements ReviewCommentUpRepositoryCustom {
    public ReviewCommentUpRepositoryCustomImpl() {
        super(BoardCommentUp.class);
    }

    @Override
    public Page<ReviewCommentUp> findByUser(User user, Pageable pageable) {
        QReviewCommentUp reviewCommentUp = new QReviewCommentUp("reviewCommentUp");

        List<ReviewCommentUp> result = from(reviewCommentUp)
                .leftJoin(reviewCommentUp.user).fetchJoin()
                .leftJoin(reviewCommentUp.reviewComment).fetchJoin()
                .where(reviewCommentUp.status.eq(true))
                .distinct()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch().stream().collect(Collectors.toList());

        return new PageImpl<>(result, pageable, pageable.getPageSize());
    }

    @Override
    public ReviewCommentUp findByUserIdxAndReviewCommentIdx(Integer userIdx, Integer reviewCommentIdx) {
        QReviewCommentUp reviewCommentUp = new QReviewCommentUp("reviewCommentUp");

        ReviewCommentUp result = from(reviewCommentUp)
                .leftJoin(reviewCommentUp.user).fetchJoin()
                .leftJoin(reviewCommentUp.reviewComment).fetchJoin()
                .where(reviewCommentUp.user.idx.eq(userIdx).and(reviewCommentUp.reviewComment.idx.eq(reviewCommentIdx)))
                .fetchOne();

        return result;
    }
}
