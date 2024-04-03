package com.example.bootshelf.reviewsvc.review.service;

import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.config.aws.ImageUtils;
import com.example.bootshelf.config.aws.S3Service;
import com.example.bootshelf.reviewsvc.review.model.entity.Review;
import com.example.bootshelf.reviewsvc.review.model.response.PostUploadReviewImageRes;
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

    @Transactional(readOnly = false)
    public BaseRes uploadReviewImage(MultipartFile reviewImage) {

        String savePath = ImageUtils.makeImagePath(reviewImage.getOriginalFilename());
        savePath = s3Service.uploadFile(reviewBucket, reviewImage, savePath);

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

}
