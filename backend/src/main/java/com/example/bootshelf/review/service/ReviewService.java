package com.example.bootshelf.review.service;

import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.common.error.ErrorCode;
import com.example.bootshelf.review.exception.ReviewException;
import com.example.bootshelf.review.model.entity.Review;
import com.example.bootshelf.review.model.request.PostCreateReviewReq;
import com.example.bootshelf.review.model.response.*;
import com.example.bootshelf.review.repository.ReviewRepository;
import com.example.bootshelf.reviewcategory.model.ReviewCategory;
import com.example.bootshelf.reviewcomment.model.ReviewComment;
import com.example.bootshelf.reviewimage.model.ReviewImage;
import com.example.bootshelf.reviewimage.service.ReviewImageService;
import com.example.bootshelf.user.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewImageService reviewImageService;
    private final ReviewRepository reviewRepository;

    // 후기글 작성
    @Transactional(readOnly = false)
    public BaseRes createReview(User user, PostCreateReviewReq postCreateReviewReq, MultipartFile[] reviewImages) {

        Optional<Review> result = reviewRepository.findByReviewTitle(postCreateReviewReq.getReviewTitle());

        // 후기글 제목 중복에 대한 예외 처리
        if (result.isPresent()) {
            throw new ReviewException(ErrorCode.DUPLICATE_REVIEW_TITLE, String.format("Review Title [ %s ] is duplicated.", postCreateReviewReq.getReviewTitle()));
        }

        Review review = Review.builder()
                .user(user)
                .reviewCategory(ReviewCategory.builder().idx(postCreateReviewReq.getReviewCategoryIdx()).build())
                .reviewTitle(postCreateReviewReq.getReviewTitle())
                .reviewContent(postCreateReviewReq.getReviewContent())
                .courseName(postCreateReviewReq.getCourseName())
                .courseEvaluation(postCreateReviewReq.getCourseEvaluation())
                .viewCnt(0)
                .upCnt(0)
                .scrapCnt(0)
                .commentCnt(0)
                .status(true)
                .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                .updatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                .build();

        review = reviewRepository.save(review);

        reviewImageService.createReviewImage(review, reviewImages);

        PostCreateReviewRes postCreateReviewRes = PostCreateReviewRes.builder()
                .reviewIdx(review.getIdx())
                .reviewCategoryIdx(postCreateReviewReq.getReviewCategoryIdx())
                .reviewTitle(postCreateReviewReq.getReviewTitle())
                .courseName(postCreateReviewReq.getCourseName())
                .reviewContent(postCreateReviewReq.getReviewContent())
                .courseEvaluation(postCreateReviewReq.getCourseEvaluation())
                .build();

        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("후기글 등록 성공")
                .result(postCreateReviewRes)
                .build();

        return baseRes;
    }

    // 인증회원 본인이 작성한 후기글 목록 조회
    @Transactional(readOnly = true)
    public BaseRes myList(User user, Pageable pageable) {

        Page<Review> reviewList = reviewRepository.findMyReviewList(user.getIdx(), pageable);

        List<GetMyListReviewRes> getListReviewResMyList = new ArrayList<>();

        for (Review review : reviewList) {

            List<ReviewImage> reviewImageList = review.getReviewImageList();

            ReviewImage reviewImage = reviewImageList.get(0);
            String image = reviewImage.getReviewImage();

            GetMyListReviewRes getMyListReviewRes = GetMyListReviewRes.builder()
                    .reviewIdx(review.getIdx())
                    .reviewCategoryIdx(review.getReviewCategory().getIdx())
                    .reviewTitle(review.getReviewTitle())
                    .reviewContent(review.getReviewContent())
                    .reviewImage(image)
                    .courseName(review.getCourseName())
                    .courseEvaluation(review.getCourseEvaluation())
                    .viewCnt(review.getViewCnt())
                    .upCnt(review.getUpCnt())
                    .scrapCnt(review.getScrapCnt())
                    .commentCnt(review.getCommentCnt())
                    .updatedAt(review.getUpdatedAt())
                    .build();

            getListReviewResMyList.add(getMyListReviewRes);
        }
        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("인증회원 본인 후기글 목록 조회 요청 성공")
                .result(getListReviewResMyList)
                .build();

        return baseRes;
    }

    // 정렬 조건 별 후기글 목록 조회
    @Transactional(readOnly = true)
    public BaseRes listReview(Integer reviewCategoryIdx, Integer sortType, Pageable pageable) {

        Page<Review> reviewList = reviewRepository.findReviewList(reviewCategoryIdx, sortType, pageable);

        List<GetListReviewRes> getCategoryListResListReview = new ArrayList<>();

        for (Review review : reviewList) {

            List<ReviewImage> reviewImageList = review.getReviewImageList();

            ReviewImage reviewImage = reviewImageList.get(0);
            String image = reviewImage.getReviewImage();

            GetListReviewRes getListReviewRes = GetListReviewRes.builder()
                    .reviewIdx(review.getIdx())
                    .userIdx(review.getUser().getIdx())
                    .userNickName(review.getUser().getNickName())
                    .reviewTitle(review.getReviewTitle())
                    .reviewContent(review.getReviewContent())
                    .reviewImage(image)
                    .courseName(review.getCourseName())
                    .courseEvaluation(review.getCourseEvaluation())
                    .viewCnt(review.getViewCnt())
                    .upCnt(review.getUpCnt())
                    .scrapCnt(review.getScrapCnt())
                    .commentCnt(review.getCommentCnt())
                    .updatedAt(review.getUpdatedAt())
                    .build();

            getCategoryListResListReview.add(getListReviewRes);
        }
        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("후기글 목록 조회 요청 성공")
                .result(getCategoryListResListReview)
                .build();

        return baseRes;
    }

    // 후기글 상세 조회
    @Transactional(readOnly = true)
    public BaseRes readReview(Integer reviewIdx) {

        Optional<Review> result = reviewRepository.findByIdx(reviewIdx);

        if (!result.isPresent()) {
            throw new ReviewException(ErrorCode.REVIEW_NOT_EXISTS, String.format("Review Idx [ %s ] is not exists.", reviewIdx));
        }

        Review review = result.get();

        // 댓글 조회
        List<GetListCommentReviewRes> getListCommentResListReview = new ArrayList<>();

        for (ReviewComment reviewComment : review.getReviewCommentList()) {

            // 댓글이 최상위 댓글일 때만 처리
            if (reviewComment.getParent() == null) {
                getListCommentResListReview.add(convertToCommentReviewRes(reviewComment));
            }
        }

        // 이미지 조회
        List<GetListImageReviewRes> getListImageReviewResList = new ArrayList<>();

        for (ReviewImage reviewImage : review.getReviewImageList()) {
            GetListImageReviewRes getListImageReviewRes = GetListImageReviewRes.builder()
                    .reviewImageIdx(reviewImage.getIdx())
                    .reviewImage(reviewImage.getReviewImage())
                    .build();

            getListImageReviewResList.add(getListImageReviewRes);
        }

        GetReadReviewRes getReadReviewRes = GetReadReviewRes.builder()
                .reviewIdx(review.getIdx())
                .userIdx(review.getUser().getIdx())
                .userNickName(review.getUser().getNickName())
                .reviewTitle(review.getReviewTitle())
                .reviewContent(review.getReviewContent())
                .courseName(review.getCourseName())
                .courseEvaluation(review.getCourseEvaluation())
                .viewCnt(review.getViewCnt())
                .upCnt(review.getUpCnt())
                .commentCnt(review.getCommentCnt())
                .updatedAt(review.getUpdatedAt())
                .reviewImageList(getListImageReviewResList)
                .reviewCommentList(getListCommentResListReview)
                .build();

        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("후기글 목록 조회 요청 성공")
                .result(getReadReviewRes)
                .build();

        return baseRes;
    }

    private GetListCommentReviewRes convertToCommentReviewRes(ReviewComment reviewComment) {

        List<GetListCommentReviewRes> childCommentsRes = new ArrayList<>();

        for (ReviewComment childComment : reviewComment.getChildren()) {
            GetListCommentReviewRes childRes = convertToCommentReviewRes(childComment);
            childCommentsRes.add(childRes);
        }

        return GetListCommentReviewRes.builder()
                .commentIdx(reviewComment.getIdx())
                .userIdx(reviewComment.getUser().getIdx())
                .userNickName(reviewComment.getUser().getNickName())
                .reviewCommentContent(reviewComment.getReviewCommentContent())
                .upCnt(reviewComment.getUpCnt())
                .updatedAt(reviewComment.getUpdatedAt())
                .children(childCommentsRes) // 대댓글 목록 추가
                .build();
    }
}
