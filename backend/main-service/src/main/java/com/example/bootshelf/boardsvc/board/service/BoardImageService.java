package com.example.bootshelf.boardsvc.board.service;

import com.example.bootshelf.boardsvc.board.model.response.PostUploadBoardImageRes;
import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.config.aws.ImageUtils;
import com.example.bootshelf.config.aws.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class BoardImageService {

    @Value("${cloud.aws.s3.board-bucket}")
    private String boardBucket;

    private final S3Service s3Service;

    @Transactional(readOnly = false)
    public BaseRes uploadBoardImage(MultipartFile boardImage) {

        String savePath = ImageUtils.makeImagePath(boardImage.getOriginalFilename());
        savePath = s3Service.uploadFile(boardBucket, boardImage, savePath);

        PostUploadBoardImageRes postUploadBoardImageRes = PostUploadBoardImageRes.builder()
                .imageUrl(savePath)
                .build();

        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("게시글 이미지 업로드 완료")
                .result(postUploadBoardImageRes)
                .build();

        return baseRes;
    }

}

