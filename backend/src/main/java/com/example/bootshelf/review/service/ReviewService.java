package com.example.bootshelf.review.service;

import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.review.exception.ReviewTitleDuplicateException;
import com.example.bootshelf.review.model.entity.Review;
import com.example.bootshelf.review.model.request.PostCreateReviewReq;
import com.example.bootshelf.review.model.response.GetCategoryListReviewRes;
import com.example.bootshelf.review.model.response.GetListReviewRes;
import com.example.bootshelf.review.model.response.PostCreateReviewRes;
import com.example.bootshelf.review.repository.ReviewRepository;
import com.example.bootshelf.reviewcategory.model.ReviewCategory;
import com.example.bootshelf.reviewimage.model.ReviewImage;
import com.example.bootshelf.reviewimage.service.ReviewImageService;
import com.example.bootshelf.user.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
            throw new ReviewTitleDuplicateException(postCreateReviewReq.getReviewTitle());
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
    public BaseRes myList(User user, Integer page, Integer size) {

        Pageable pageable = PageRequest.of(page-1, size);
        Page<Review> reviewList = reviewRepository.findMyReviewList(user.getIdx(), pageable);

        List<GetListReviewRes> getListReviewResList = new ArrayList<>();

        for(Review review : reviewList) {

            List<ReviewImage> reviewImageList = review.getReviewImageList();

            ReviewImage reviewImage = reviewImageList.get(0);
            String image = reviewImage.getReviewImage();

            GetListReviewRes getListReviewRes = GetListReviewRes.builder()
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

            getListReviewResList.add(getListReviewRes);
        }
        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("인증회원 본인 후기글 목록 조회 요청 성공")
                .result(getListReviewResList)
                .build();

        return baseRes;
    }

    // 카테고리 별 후기글 목록 조회
    @Transactional(readOnly = true)
    public BaseRes listReview(Integer reviewCategoryIdx, Integer sortType, Integer page, Integer size) {

        Pageable pageable = PageRequest.of(page - 1, size);

        Page<Review> reviewList = reviewRepository.findReviewList(reviewCategoryIdx, sortType, pageable);

        List<GetCategoryListReviewRes> getCategoryListResListReview = new ArrayList<>();

        for (Review review : reviewList) {

            List<ReviewImage> reviewImageList = review.getReviewImageList();

            ReviewImage reviewImage = reviewImageList.get(0);
            String image = reviewImage.getReviewImage();

            GetCategoryListReviewRes getCategoryListReviewRes = GetCategoryListReviewRes.builder()
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

            getCategoryListResListReview.add(getCategoryListReviewRes);
        }
        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("카테고리별 후기글 목록 조회 요청 성공")
                .result(getCategoryListResListReview)
                .build();

        return baseRes;
    }

    // 카테고리 별 후기글 목록 조회
//    @Transactional(readOnly = true)
//    public BaseRes categoryListReview(Integer reviewCategoryIdx, Integer page, Integer size) {
//
//        Pageable pageable = PageRequest.of(page - 1, size);
//        Page<Review> categoryReviewList = reviewRepository.findCategoryReviewList(reviewCategoryIdx, pageable);
//
//        List<GetCategoryListReviewRes> getCategoryListResListReview = new ArrayList<>();
//
//        for (Review review : categoryReviewList) {
//
//            List<ReviewImage> reviewImageList = review.getReviewImageList();
//
//            ReviewImage reviewImage = reviewImageList.get(0);
//            String image = reviewImage.getReviewImage();
//
//            GetCategoryListReviewRes getCategoryListReviewRes = GetCategoryListReviewRes.builder()
//                    .reviewIdx(review.getIdx())
//                    .userIdx(review.getUser().getIdx())
//                    .userNickName(review.getUser().getNickName())
//                    .reviewTitle(review.getReviewTitle())
//                    .reviewContent(review.getReviewContent())
//                    .reviewImage(image)
//                    .courseName(review.getCourseName())
//                    .courseEvaluation(review.getCourseEvaluation())
//                    .viewCnt(review.getViewCnt())
//                    .upCnt(review.getUpCnt())
//                    .scrapCnt(review.getScrapCnt())
//                    .commentCnt(review.getCommentCnt())
//                    .updatedAt(review.getUpdatedAt())
//                    .build();
//
//            getCategoryListResListReview.add(getCategoryListReviewRes);
//        }
//        BaseRes baseRes = BaseRes.builder()
//                .isSuccess(true)
//                .message("카테고리별 후기글 목록 조회 요청 성공")
//                .result(getCategoryListResListReview)
//                .build();
//
//        return baseRes;
//    }
}
