package com.example.bootshelf.reviewsvc.reviewimage.service;

import com.example.bootshelf.boardsvc.boardimage.model.response.PostUploadBoardImageRes;
import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.common.error.ErrorCode;
import com.example.bootshelf.common.error.entityexception.ReviewException;
import com.example.bootshelf.config.aws.ImageUtils;
import com.example.bootshelf.config.aws.S3Service;
import com.example.bootshelf.reviewsvc.review.model.entity.Review;
import com.example.bootshelf.reviewsvc.reviewimage.model.entity.ReviewImage;
import com.example.bootshelf.reviewsvc.reviewimage.model.response.PostUploadReviewImageRes;
import com.example.bootshelf.reviewsvc.reviewimage.repository.ReviewImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ReviewImageService {

    @Value("${cloud.aws.s3.review-bucket}")
    private String reviewBucket;

    private final S3Service s3Service;

    private final ReviewImageRepository reviewImageRepository;


    //    @Transactional(readOnly = false)
//    public void createReviewImage(Review review, MultipartFile[] reviewImages) {
//
//        for (MultipartFile reviewImage : reviewImages) {
//
//            String savePath = ImageUtils.makeBoardImagePath(reviewImage.getOriginalFilename());
//            savePath = s3Service.uploadBoardFile(reviewBucket, reviewImage, savePath);
//
//            reviewImageRepository.save(ReviewImage.builder()
//                    .review(review)
//                    .reviewImage(savePath)
//                    .status(true)
//                    .build());
//        }
//    }
    @Transactional(readOnly = false)
    public BaseRes uploadReviewImage(MultipartFile reviewImage) {

        String savePath = ImageUtils.makeBoardImagePath(reviewImage.getOriginalFilename());
        savePath = s3Service.uploadBoardFile(reviewBucket, reviewImage, savePath);

        PostUploadReviewImageRes postUploadReviewImageRes = PostUploadReviewImageRes.builder()
                .imageUrl(savePath)
                .build();

        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("후기글 이미지 업로드 완료")
                .result(postUploadReviewImageRes)
                .build();

        return baseRes;
    }

    @Transactional(readOnly = false)
    public void saveImageUrl(Integer reviewIdx, String imageUrl) {

        reviewImageRepository.save(ReviewImage.builder()
                .reviewImage(imageUrl)
                .review(Review.builder().idx(reviewIdx).build())
                .status(true)
                .build());
    }
}
