package com.example.bootshelf.reviewsvc.reviewcategory.service;

import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.common.error.ErrorCode;
import com.example.bootshelf.common.error.entityexception.BoardCommentException;
import com.example.bootshelf.common.error.entityexception.ReviewCategoryException;
import com.example.bootshelf.common.error.entityexception.ReviewException;
import com.example.bootshelf.reviewsvc.review.model.entity.Review;
import com.example.bootshelf.reviewsvc.review.repository.ReviewRepository;
import com.example.bootshelf.reviewsvc.reviewcategory.model.ReviewCategory;
import com.example.bootshelf.reviewsvc.reviewcategory.model.request.PatchUpdateReviewCategoryReq;
import com.example.bootshelf.reviewsvc.reviewcategory.model.request.PostCreateReviewCategoryReq;
import com.example.bootshelf.reviewsvc.reviewcategory.model.response.GetListReviewCategoryRes;
import com.example.bootshelf.reviewsvc.reviewcategory.model.response.PatchUpdateReviewCategoryRes;
import com.example.bootshelf.reviewsvc.reviewcategory.model.response.PostCreateReviewCategoryRes;
import com.example.bootshelf.reviewsvc.reviewcategory.repository.ReviewCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.joda.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewCategoryService {

    private final ReviewCategoryRepository reviewCategoryRepository;
    private final ReviewRepository reviewRepository;

    // 카테고리 추가
    @Transactional(readOnly = false)
    public BaseRes createReviewCategory(PostCreateReviewCategoryReq postCreateReviewCategoryReq) {

        Optional<ReviewCategory> result = reviewCategoryRepository.findByCategoryName(postCreateReviewCategoryReq.getCategoryName());

        if (result.isPresent()) {
            throw new ReviewException(ErrorCode.DUPLICATED_REVIEW_CATEGORY, String.format("Review Category [ %s ] is duplicated.", postCreateReviewCategoryReq.getCategoryName()));
        }
        if (postCreateReviewCategoryReq.getCategoryName() == null || postCreateReviewCategoryReq.getCategoryName().isEmpty()) {
            throw new BoardCommentException(ErrorCode.INVALID_INPUT_VALUE, String.format("Review Category is empty."));
        }

        reviewCategoryRepository.save(ReviewCategory.builder()
                .categoryName(postCreateReviewCategoryReq.getCategoryName())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .status(true)
                .build());

        PostCreateReviewCategoryRes postCreateReviewCategoryRes = PostCreateReviewCategoryRes.builder()
                .categoryName(postCreateReviewCategoryReq.getCategoryName())
                .build();

        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("후기 게시판 카테고리 등록 성공")
                .result(postCreateReviewCategoryRes)
                .build();

        return baseRes;
    }


    // 카테고리 전체 목록
    @Transactional(readOnly = true)
    public BaseRes listReviewCategory(Pageable pageable) {

        Page<ReviewCategory> reviewCategoriesPage = reviewCategoryRepository.findAll(pageable);

        List<ReviewCategory> reviewCategories = reviewCategoriesPage.getContent();

        List<GetListReviewCategoryRes> response = new ArrayList<>();
        for (ReviewCategory reviewCategory : reviewCategories) {
            GetListReviewCategoryRes getListReviewCategoryRes = GetListReviewCategoryRes.builder()
                    .idx(reviewCategory.getIdx())
                    .categoryName(reviewCategory.getCategoryName())
                    .build();

            response.add(getListReviewCategoryRes);
        }

        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("요청 성공")
                .result(response)
                .build();

        return baseRes;
    }

    // 수정
    @Transactional(readOnly = false)
    public BaseRes updateReviewCategory(PatchUpdateReviewCategoryReq patchUpdateReviewCategoryReq, Integer reviewCategoryIdx) {
        Optional<ReviewCategory> result = reviewCategoryRepository.findById(reviewCategoryIdx);
        Optional<ReviewCategory> resultCategoryName = reviewCategoryRepository.findByCategoryName(patchUpdateReviewCategoryReq.getCategoryName());

        if (resultCategoryName.isPresent()) {
            throw new ReviewException(ErrorCode.DUPLICATED_REVIEW_CATEGORY, String.format("Review Category [ %s ] is duplicated.", patchUpdateReviewCategoryReq.getCategoryName()));
        }
        // 수정하고자 하는 카테고리를 찾지 못할 때
        if (!result.isPresent()) {
            throw new ReviewException(ErrorCode.REVIEW_CATEGORY_NOT_EXISTS, String.format("Review Category [ idx : %s ] is not exists.", reviewCategoryIdx));
        }

        ReviewCategory reviewCategory = result.get();
        reviewCategory.setUpdatedAt(LocalDateTime.now());
        reviewCategory.setCategoryName(patchUpdateReviewCategoryReq.getCategoryName());

        reviewCategoryRepository.save(reviewCategory);

        PatchUpdateReviewCategoryRes patchUpdateBoardCategoryRes = PatchUpdateReviewCategoryRes.builder()
                .idx(reviewCategory.getIdx())
                .categoryName(patchUpdateReviewCategoryReq.getCategoryName())
                .build();
        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("후기 게시판 카테고리 수정 성공")
                .result(patchUpdateBoardCategoryRes)
                .build();

        return baseRes;
    }


    //삭제
    @Transactional(readOnly = false)
    public BaseRes deleteReviewCategory(Integer reviewCategoryIdx) {
        Optional<ReviewCategory> result = reviewCategoryRepository.findById(reviewCategoryIdx);

        // 삭제하고자 하는 카테고리를 찾지 못할 때
        if (!result.isPresent()) {
            throw new ReviewCategoryException(ErrorCode.REVIEW_CATEGORY_NOT_EXISTS, String.format("Review Category [ idx : %s ] is not exists.", reviewCategoryIdx));
        }
        ReviewCategory reviewCategory = result.get();
        List<Review> reviewList = reviewCategory.getReviewList();

        for (Review review : reviewList) {
            review.setReviewCategory(null);
        }

        reviewRepository.saveAll(reviewList);
        reviewCategoryRepository.deleteById(reviewCategoryIdx);

        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("후기 게시판 카테고리 삭제 성공")
                .result("후기 게시판 카테고리 및 관련 게시글의 카테고리 정보가 삭제되었습니다.")
                .build();

        return baseRes;
    }
}
