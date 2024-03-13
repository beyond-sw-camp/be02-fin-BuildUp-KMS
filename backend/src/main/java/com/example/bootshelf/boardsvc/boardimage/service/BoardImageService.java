package com.example.bootshelf.boardsvc.boardimage.service;

import com.example.bootshelf.config.aws.S3Service;
import com.example.bootshelf.boardsvc.board.model.entity.Board;
import com.example.bootshelf.boardsvc.boardimage.model.entity.BoardImage;
import com.example.bootshelf.boardsvc.boardimage.repository.BoardImageRepository;
import com.example.bootshelf.config.aws.ImageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class BoardImageService {

    @Value("${cloud.aws.s3.board-bucket}")
    private String boradBucket;

    private final S3Service s3Service;

    private final BoardImageRepository boardImageRepository;

    @Transactional(readOnly = false)
    public void createBoardImage(Integer id, MultipartFile[] images) {

        for (MultipartFile image : images) {

            String savePath = ImageUtils.makeBoardImagePath(image.getOriginalFilename());
            savePath = s3Service.uploadBoardFile(boradBucket, image, savePath);

            boardImageRepository.save(BoardImage.builder()
                    .boardImage(savePath)
                    .board(Board.builder().idx(id).build())
                    .status(true)
                    .build());
        }
    }
}
