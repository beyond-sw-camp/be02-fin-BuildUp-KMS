package com.example.bootshelf.reviewsvc.review.repository.querydsl;

import com.example.bootshelf.boardsvc.board.model.entity.Board;
import com.example.bootshelf.boardsvc.board.model.entity.QBoard;
import com.example.bootshelf.common.error.ErrorCode;
import com.example.bootshelf.common.error.entityexception.ReviewException;
import com.example.bootshelf.reviewsvc.review.model.entity.QReview;
import com.example.bootshelf.reviewsvc.review.model.entity.Review;
import com.example.bootshelf.reviewsvc.reviewcategory.model.QReviewCategory;
import com.example.bootshelf.reviewsvc.reviewimage.model.QReviewImage;
import com.example.bootshelf.user.model.entity.QUser;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewRepositoryCustomImpl extends QuerydslRepositorySupport implements ReviewRepositoryCustom {
    public ReviewRepositoryCustomImpl() {
        super(Review.class);
    }

    // 인증회원 본인 후기글 목록 조회
    @Override
    public Page<Review> findMyReviewList(Integer userIdx, Pageable pageable) {
        QReview review = new QReview("review");
        QReviewCategory reviewCategory = new QReviewCategory("reviewCategory");
        QReviewImage reviewImage = new QReviewImage("reviewImage");


        List<Review> result = from(review)
                .leftJoin(review.reviewImageList, reviewImage).fetchJoin()
                .leftJoin(review.reviewCategory, reviewCategory).fetchJoin()
                .where(review.user.idx.eq(userIdx))
                .distinct()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch().stream().distinct().collect(Collectors.toList());

        return new PageImpl<>(result, pageable, result.size());
    }

    // 정렬 조건 별 후기글 목록 조회
    @Override
    public Page<Review> findReviewList(Integer reviewCategoryIdx, Integer sortType, Pageable pageable) {

        QReview review = new QReview("review");
        QReviewImage reviewImage = new QReviewImage("reviewImage");
        QUser user = new QUser("user");
        QReviewCategory reviewCategory = new QReviewCategory("reviewCategory");

        OrderSpecifier[] orderSpecifiers = createOrderSpecifier(sortType, review);

        List<Review> result = from(review)
                .leftJoin(review.reviewImageList, reviewImage).fetchJoin()
                .leftJoin(review.user, user).fetchJoin()
                .where(review.reviewCategory.idx.eq(reviewCategoryIdx))
                .orderBy(orderSpecifiers)
                .distinct()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch().stream().distinct().collect(Collectors.toList());

        // 전체 데이터 개수 조회
        long total = from(review)
                .leftJoin(review.reviewCategory, reviewCategory)
                .leftJoin(review.user, user)
                .where(review.reviewCategory.idx.eq(reviewCategoryIdx))
                .fetchCount();

        return new PageImpl<>(result, pageable, total);
    }

    private OrderSpecifier[] createOrderSpecifier(Integer sortType, QReview review) {
        List<OrderSpecifier> orderSpecifiers = new ArrayList<>();

        // sortType에 따른 동적 정렬 조건 설정
        switch (sortType) {
            case 1:
                orderSpecifiers.add(review.updatedAt.desc());
                break;
            case 2:
                orderSpecifiers.add(review.upCnt.desc());
                break;
            case 3:
                orderSpecifiers.add(review.viewCnt.desc());
                break;
            case 4:
                orderSpecifiers.add(review.scrapCnt.desc());
                break;
            case 5:
                orderSpecifiers.add(review.commentCnt.desc());
                break;
            default:
                orderSpecifiers.add(review.updatedAt.desc());
        }
        return orderSpecifiers.toArray(new OrderSpecifier[0]);
    }

    // 검색어를 포함하는 리뷰 목록 조회 (BooleanExpression 사용)
    public Page<Review> findReviewsBySearchTerm(Integer sortType, String searchTerm, Pageable pageable) {

        QReview review = new QReview("review");
        QReviewCategory reviewCategory = new QReviewCategory("reviewCategory");
        QUser user = new QUser("user");

        OrderSpecifier[] orderSpecifiers = createOrderSpecifier(sortType, review);

        // 검색 조건을 BooleanExpression으로 구성
        BooleanExpression searchCondition = searchTermContains(searchTerm);

        List<Review> result = from(review)
                .leftJoin(review.reviewCategory, reviewCategory).fetchJoin()
                .leftJoin(review.user, user).fetchJoin()
                .where(searchCondition)
                .orderBy(orderSpecifiers)
                .distinct()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // 전체 데이터 개수 조회
        long total = from(review)
                .leftJoin(review.reviewCategory, reviewCategory)
                .leftJoin(review.user, user)
                .where(searchCondition)
                .fetchCount();

        return new PageImpl<>(result, pageable, total);
    }

    @Override
    public Page<Review> searchReviewListByQuery(Pageable pageable, String query, Integer searchType) {
        QReview qReview = QReview.review;

        BooleanExpression searchCondition = searchType == 1 ? titleContains(query)
                : titleContains(query).or(contentContains(query));

        // 조회 쿼리 생성 및 페이징 처리
        JPQLQuery<Review> querySQL = from(qReview)
                .leftJoin(qReview.user).fetchJoin()
                .leftJoin(qReview.reviewCategory).fetchJoin()
                .where(searchCondition)
                .orderBy(qReview.createdAt.desc());

        // pagination 적용
        JPQLQuery<Review> pageableQuery = getQuerydsl().applyPagination(pageable, querySQL);
        List<Review> reviewList = pageableQuery.fetch();

        return new PageImpl<>(reviewList, pageable, pageableQuery.fetchCount());
    }

    private BooleanExpression titleContains(String query) {
        if (query == null || query.trim().isEmpty()) return null;
        return QReview.review.reviewTitle.containsIgnoreCase(query);
    }

    private BooleanExpression contentContains(String query) {
        if (query == null || query.trim().isEmpty()) return null;
        return QReview.review.reviewContent.containsIgnoreCase(query);
    }


    /**
     *  (게시판 +) 후기 검색 api (v2)
     *  -> 페이지네이션 잘 안됨
     */
    @Override
    public Page<Review> searchReviewListByQueryV2(Pageable pageable, String query, Integer searchType) {
        QReview qReview = QReview.review;

        // 검색 조건
        BooleanExpression searchCondition = searchType == 1 ? titleContainsV2(query)
                : titleContainsV2(query).or(contentContainsV2(query)); // Ensure correct method names are used

        // 조회 쿼리 생성 및 페이징 처리
        JPQLQuery<Review> querySQL = from(qReview)
                .leftJoin(qReview.user).fetchJoin()
                .leftJoin(qReview.reviewCategory).fetchJoin()
                .where(searchCondition)
                .orderBy(qReview.createdAt.desc());

        // pagination 적용
        JPQLQuery<Review> pageableQuery = getQuerydsl().applyPagination(pageable, querySQL);
        List<Review> reviewList = pageableQuery.fetch();
        long count = pageableQuery.fetchCount(); // Fetch the count of all matching records

        return new PageImpl<>(reviewList, pageable, count); // Return a Page<Board> instead of List<Board>
    }

    private BooleanExpression titleContainsV2(String query) {
        if (query == null || query.trim().isEmpty()) return null;
        return QReview.review.reviewTitle.containsIgnoreCase(query);
    }

    private BooleanExpression contentContainsV2(String query) {
        if (query == null || query.trim().isEmpty()) return null;
        return QReview.review.reviewContent.containsIgnoreCase(query);
    }

    // 검색어에 따른 조건을 리턴하는 메서드
    private BooleanExpression searchTermContains(String searchTerm) {
        if (searchTerm == null || searchTerm.isEmpty()) {
            throw new ReviewException(ErrorCode.NO_SEARCH_TERMS, String.format("Please enter a search term"));
        }

        QReview review = new QReview("review");

        BooleanExpression titleContains = review.reviewTitle.containsIgnoreCase(searchTerm);
        BooleanExpression contentContains = review.reviewContent.containsIgnoreCase(searchTerm);

        return titleContains.or(contentContains);
    }
}
