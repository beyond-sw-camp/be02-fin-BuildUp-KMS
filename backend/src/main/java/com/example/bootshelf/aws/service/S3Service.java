package com.example.bootshelf.aws.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class S3Service {
    @Value("${cloud.aws.s3.board-bucket}")
    private String boradBucket;

    private final AmazonS3 s3;

    public String uploadBoardFile(MultipartFile file, String savePath){
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        try {
            s3.putObject(boradBucket, savePath, file.getInputStream(), metadata);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return s3.getUrl(boradBucket, savePath).toString();
    }
}
