package com.example.bootshelf.reviewsvc.reviewimage.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.example.bootshelf.config.aws.ImageUtils;
import com.example.bootshelf.config.aws.S3Service;
import com.example.bootshelf.reviewsvc.review.model.entity.Review;
import com.example.bootshelf.reviewsvc.reviewimage.model.ReviewImage;
import com.example.bootshelf.reviewsvc.reviewimage.repository.ReviewImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewImageService {

    @Value("${cloud.aws.s3.review-bucket}")
    private String reviewBucket;

    private final S3Service s3Service;

    private final ReviewImageRepository reviewImageRepository;


    @Transactional(readOnly = false)
    public void createReviewImage(Review review, MultipartFile[] reviewImages) {

        for (MultipartFile reviewImage : reviewImages) {

            String savePath = ImageUtils.makeBoardImagePath(reviewImage.getOriginalFilename());
            savePath = s3Service.uploadBoardFile(reviewBucket, reviewImage, savePath);

            reviewImageRepository.save(ReviewImage.builder()
                    .review(review)
                    .reviewImage(savePath)
                    .status(true)
                    .build());
        }
    }
    @Transactional(readOnly = false)
    public void updateReviewImage(Review review, MultipartFile reviewImage) {

        String savePath = ImageUtils.makeBoardImagePath(reviewImage.getOriginalFilename());
        savePath = s3Service.uploadBoardFile(reviewBucket, reviewImage, savePath);

        reviewImageRepository.save(ReviewImage.builder()
                .review(review)
                .reviewImage(savePath)
                .status(true)
                .build());
    }
}
