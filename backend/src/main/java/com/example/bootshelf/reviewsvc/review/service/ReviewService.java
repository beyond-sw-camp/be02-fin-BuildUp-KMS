package com.example.bootshelf.reviewsvc.review.service;

import com.example.bootshelf.boardsvc.board.model.response.GetBoardListByQueryResResult;
import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.common.error.ErrorCode;
import com.example.bootshelf.common.error.entityexception.ReviewException;
import com.example.bootshelf.reviewsvc.review.model.entity.Review;
import com.example.bootshelf.reviewsvc.review.model.request.PatchUpdateReviewReq;
import com.example.bootshelf.reviewsvc.review.model.request.PostCreateReviewReq;
import com.example.bootshelf.reviewsvc.review.model.response.*;
import com.example.bootshelf.reviewsvc.review.repository.ReviewRepository;
import com.example.bootshelf.reviewsvc.reviewcategory.model.ReviewCategory;
import com.example.bootshelf.reviewsvc.reviewcomment.model.entity.ReviewComment;
import com.example.bootshelf.reviewsvc.reviewimage.model.ReviewImage;
import com.example.bootshelf.reviewsvc.reviewimage.service.ReviewImageService;
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

        if (reviewImages == null || reviewImages.length == 0) {
            reviewImageService.createReviewImage(review, reviewImages);
        }

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

            GetMyListReviewRes getMyListReviewRes = GetMyListReviewRes.builder()
                    .reviewIdx(review.getIdx())
                    .reviewCategoryIdx(review.getReviewCategory().getIdx())
                    .reviewTitle(review.getReviewTitle())
                    .reviewContent(review.getReviewContent())
                    .courseName(review.getCourseName())
                    .courseEvaluation(review.getCourseEvaluation())
                    .viewCnt(review.getViewCnt())
                    .upCnt(review.getUpCnt())
                    .scrapCnt(review.getScrapCnt())
                    .commentCnt(review.getCommentCnt())
                    .updatedAt(review.getUpdatedAt())
                    .build();

            List<ReviewImage> reviewImageList = review.getReviewImageList();
            if (!reviewImageList.isEmpty()) {
                ReviewImage reviewImage = reviewImageList.get(0);
                String image = reviewImage.getReviewImage();
                getMyListReviewRes.setReviewImage(image);
            }
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

        List<GetListReviewRes> getListReviewResList = new ArrayList<>();

        for (Review review : reviewList) {

            GetListReviewRes getListReviewRes = GetListReviewRes.builder()
                    .reviewIdx(review.getIdx())
                    .userIdx(review.getUser().getIdx())
                    .userNickName(review.getUser().getNickName())
                    .profileImage(review.getUser().getProfileImage())
                    .reviewTitle(review.getReviewTitle())
                    .reviewContent(review.getReviewContent())
                    .courseName(review.getCourseName())
                    .courseEvaluation(review.getCourseEvaluation())
                    .viewCnt(review.getViewCnt())
                    .upCnt(review.getUpCnt())
                    .scrapCnt(review.getScrapCnt())
                    .commentCnt(review.getCommentCnt())
                    .updatedAt(review.getUpdatedAt())
                    .build();

            List<ReviewImage> reviewImageList = review.getReviewImageList();
            if (!reviewImageList.isEmpty()) {
                ReviewImage reviewImage = reviewImageList.get(0);
                String image = reviewImage.getReviewImage();
                getListReviewRes.setReviewImage(image);
            }

            getListReviewResList.add(getListReviewRes);
        }

        Long totalCnt = reviewList.getTotalElements();
        Integer totalPages = reviewList.getTotalPages();

        GetListReviewResResult result = GetListReviewResResult.builder()
                .totalCnt(totalCnt)
                .totalPages(totalPages)
                .list(getListReviewResList)
                .build();

        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("후기글 목록 조회 요청 성공")
                .result(result)
                .build();

        return baseRes;
    }

    // 후기글 상세 조회
    @Transactional(readOnly = false)
    public BaseRes readReview(Integer reviewIdx) {

        Optional<Review> result = reviewRepository.findByIdx(reviewIdx);

        if (!result.isPresent()) {
            throw new ReviewException(ErrorCode.REVIEW_NOT_EXISTS, String.format("Review Idx [ %s ] is not exists.", reviewIdx));
        }

        Review review = result.get();
        review.increaseViewCount();
        reviewRepository.save(review);

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

        if (!review.getReviewImageList().isEmpty()) {
            for (ReviewImage reviewImage : review.getReviewImageList()) {
                GetListImageReviewRes getListImageReviewRes = GetListImageReviewRes.builder()
                        .reviewImageIdx(reviewImage.getIdx())
                        .reviewImage(reviewImage.getReviewImage())
                        .build();

                getListImageReviewResList.add(getListImageReviewRes);
            }
        }

        GetReadReviewRes getReadReviewRes = GetReadReviewRes.builder()
                .reviewIdx(review.getIdx())
                .userIdx(review.getUser().getIdx())
                .userNickName(review.getUser().getNickName())
                .profileImage(review.getUser().getProfileImage())
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
                .profileImage(reviewComment.getUser().getProfileImage())
                .reviewCommentContent(reviewComment.getReviewCommentContent())
                .upCnt(reviewComment.getUpCnt())
                .updatedAt(reviewComment.getUpdatedAt())
                .children(childCommentsRes)
                .build();
    }

    // 후기글 수정
    @Transactional(readOnly = false)
    public BaseRes updateReview(User user, PatchUpdateReviewReq patchUpdateReviewReq) {

        Optional<Review> result = reviewRepository.findByIdxAndUserIdx(patchUpdateReviewReq.getReviewIdx(), user.getIdx());

        // 수정하고자 하는 후기글을 못찾을 때 예외 처리
        if (!result.isPresent()) {
            throw new ReviewException(ErrorCode.REVIEW_NOT_EXISTS, String.format("Review Idx [ %s ] is not exists.", patchUpdateReviewReq.getReviewIdx()));
        }

        Optional<Review> resultTitle = reviewRepository.findByReviewTitle(patchUpdateReviewReq.getReviewTitle());

        // 수정 후기글 제목 중복에 대한 예외 처리
        if (resultTitle.isPresent()) {
            throw new ReviewException(ErrorCode.DUPLICATE_REVIEW_TITLE, String.format("Review Title [ %s ] is duplicated.", patchUpdateReviewReq.getReviewTitle()));
        }

        Review review = result.get();
        review.update(patchUpdateReviewReq);
        review.setUpdatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
        reviewRepository.save(review);

        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("후기글 수정 성공")
                .result("요청 성공")
                .build();

        return baseRes;
    }

    // 후기글 삭제
    @Transactional(readOnly = false)
    public BaseRes deleteReview(User user, Integer reviewIdx) {

        Optional<Review> result = reviewRepository.findByIdxAndUserIdx(reviewIdx, user.getIdx());

        // 삭제하고자 하는 후기글을 못찾을 때 예외 처리
        if (!result.isPresent()) {
            throw new ReviewException(ErrorCode.REVIEW_NOT_EXISTS, String.format("Review Idx [ %s ] is not exists.", reviewIdx));
        }

        Review review = result.get();
        review.setStatus(false);
        review.setUpdatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
        reviewRepository.save(review);

        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("후기글 삭제 성공")
                .result("요청 성공")
                .build();

        return baseRes;
    }

    // 검색어 별 후기글 목록 조회
    @Transactional(readOnly = true)
    public BaseRes searchReview(Integer sortType, String searchTerm, Pageable pageable) {

        Page<Review> reviewList = reviewRepository.findReviewsBySearchTerm(sortType, searchTerm, pageable);

        List<GetSearchListReviewRes> getSearchListReviewResList = new ArrayList<>();

        for (Review review : reviewList) {

            GetSearchListReviewRes getSearchListReviewRes = GetSearchListReviewRes.builder()
                    .reviewIdx(review.getIdx())
                    .userIdx(review.getUser().getIdx())
                    .userNickName(review.getUser().getNickName())
                    .profileImage(review.getUser().getProfileImage())
                    .reviewCategoryIdx(review.getReviewCategory().getIdx())
                    .reviewCategoryName(review.getReviewCategory().getCategoryName())
                    .reviewTitle(review.getReviewTitle())
                    .reviewContent(review.getReviewContent())
                    .courseName(review.getCourseName())
                    .viewCnt(review.getViewCnt())
                    .upCnt(review.getUpCnt())
                    .scrapCnt(review.getScrapCnt())
                    .commentCnt(review.getCommentCnt())
                    .updatedAt(review.getUpdatedAt())
                    .build();

            List<ReviewImage> reviewImageList = review.getReviewImageList();
            if (!reviewImageList.isEmpty()) {
                ReviewImage reviewImage = reviewImageList.get(0);
                String image = reviewImage.getReviewImage();
                getSearchListReviewRes.setReviewImage(image);
            }

            getSearchListReviewResList.add(getSearchListReviewRes);
        }

        Long totalCnt = reviewList.getTotalElements();
        Integer totalPages = reviewList.getTotalPages();

        GetSearchListReviewResResult result = GetSearchListReviewResResult.builder()
                .totalCnt(totalCnt)
                .totalPages(totalPages)
                .list(getSearchListReviewResList)
                .build();

        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("후기글 제목으로 검색결과 조회 요청 성공")
                .result(result)
                .build();

        return baseRes;
    }
}
