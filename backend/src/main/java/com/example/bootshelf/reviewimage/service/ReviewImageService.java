package com.example.bootshelf.reviewimage.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.example.bootshelf.review.model.entity.Review;
import com.example.bootshelf.reviewimage.model.ReviewImage;
import com.example.bootshelf.reviewimage.repository.ReviewImageRepository;
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

    private final AmazonS3 s3;

    private final ReviewImageRepository reviewImageRepository;

    public String makeFolder(){
        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String folderPath = str.replace("/", File.separator);
        return folderPath;
    }

    public String saveFile(MultipartFile reviewImage) {
        String originalName = reviewImage.getOriginalFilename();

        String folderPath = makeFolder();
        String uuid = UUID.randomUUID().toString();
        String saveFileName = folderPath + File.separator + uuid + "_" + originalName;

        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(reviewImage.getSize());
            metadata.setContentType(reviewImage.getContentType());

            s3.putObject(reviewBucket, saveFileName.replace(File.separator, "/"), reviewImage.getInputStream(), metadata);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            // 로컬 파일 시스템에서 파일 삭제
            File localFile = new File(saveFileName);
            if (localFile.exists()) {
                localFile.delete();
            }
            return s3.getUrl(reviewBucket, saveFileName.replace(File.separator, "/")).toString();
        }
    }

    @Transactional(readOnly = false)
    public List<String> createReviewImage(Review review, MultipartFile[] reviewImages) {

        List<String> reviewImageList = new ArrayList<>();

        for (MultipartFile multipartFile : reviewImages) {

            String saveFileName = saveFile(multipartFile);

            reviewImageRepository.save(ReviewImage.builder()
                    .review(review)
                    .reviewImage(saveFileName.replace(File.separator, "/"))
                    .build());

            reviewImageList.add(saveFileName.replace(File.separator, "/"));
        }
        return reviewImageList;
    }
}
