package com.example.bootshelf.reviewsvc.reviewscrap.repository.querydsl;

import com.example.bootshelf.reviewsvc.reviewscrap.model.entity.QReviewScrap;
import com.example.bootshelf.reviewsvc.reviewscrap.model.entity.ReviewScrap;
import com.example.bootshelf.user.model.entity.User;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.ArrayList;
import java.util.List;
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

        ReviewScrap result = from(reviewScrap)
                .leftJoin(reviewScrap.user).fetchJoin()
                .leftJoin(reviewScrap.review).fetchJoin()
                .where(reviewScrap.user.idx.eq(userIdx).and(reviewScrap.review.idx.eq(reviewIdx)))
                .fetchOne();

        return result;
    }

    @Override
    public Page<ReviewScrap> findByUserAndCategoryIdx(User user, Integer reviewCategoryIdx, Integer sortIdx, Pageable pageable) {
        QReviewScrap qReviewScrap = new QReviewScrap("reviewScrap");

        OrderSpecifier[] orderSpecifiers = createOrderSpecifier(sortIdx, qReviewScrap);
        JPQLQuery<ReviewScrap> result = from(qReviewScrap)
                .leftJoin(qReviewScrap.review.reviewCategory)
                .where(qReviewScrap.user.eq(user).and(qReviewScrap.status.eq(true)).and(qReviewScrap.review.reviewCategory.idx.eq(reviewCategoryIdx)))
                .orderBy(orderSpecifiers);

        JPQLQuery<ReviewScrap> pageableQuery = getQuerydsl().applyPagination(pageable, result);
        List<ReviewScrap> reviewScrapList = pageableQuery.fetch();

        return new PageImpl<>(reviewScrapList, pageable, pageableQuery.fetchCount());

    }

    private OrderSpecifier[] createOrderSpecifier(Integer sortIdx, QReviewScrap qReviewScrap) {
        List<OrderSpecifier> orderSpecifiers = new ArrayList<>();

        switch (sortIdx) {
            case 1:
                orderSpecifiers.add(qReviewScrap.review.updatedAt.desc());
                break;
            case 2:
                orderSpecifiers.add(qReviewScrap.review.upCnt.desc());
                break;
            case 3:
                orderSpecifiers.add(qReviewScrap.review.viewCnt.desc());
                break;
            case 4:
                orderSpecifiers.add(qReviewScrap.review.scrapCnt.desc());
                break;
            case 5:
                orderSpecifiers.add(qReviewScrap.review.commentCnt.desc());
                break;
            default:
                orderSpecifiers.add(qReviewScrap.review.updatedAt.desc());
        }
        return orderSpecifiers.toArray(new OrderSpecifier[0]);
    }
}
