package com.example.bootshelf.boardsvc.boardimage.service;

import com.example.bootshelf.boardsvc.boardimage.model.response.PostUploadBoardImageRes;
import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.common.error.ErrorCode;
import com.example.bootshelf.common.error.entityexception.BoardException;
import com.example.bootshelf.common.error.entityexception.ReviewException;
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
    private String boardBucket;

    private final S3Service s3Service;

    private final BoardImageRepository boardImageRepository;

//    @Transactional(readOnly = false)
//    public void createBoardImage(Integer id, MultipartFile[] boardImages) {
//
//        for (MultipartFile boardImage : boardImages) {
//
//            String savePath = ImageUtils.makeBoardImagePath(boardImage.getOriginalFilename());
//            savePath = s3Service.uploadBoardFile(boardBucket, boardImage, savePath);
//
//            boardImageRepository.save(BoardImage.builder()
//                    .boardImage(savePath)
//                    .board(Board.builder().idx(id).build())
//                    .status(true)
//                    .build());
//        }
//    }

    @Transactional(readOnly = false)
    public BaseRes uploadBoardImage(MultipartFile boardImage) {

        String savePath = ImageUtils.makeBoardImagePath(boardImage.getOriginalFilename());
        savePath = s3Service.uploadBoardFile(boardBucket, boardImage, savePath);

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

    @Transactional(readOnly = false)
    public void saveImageUrl(Integer boardIdx, String imageUrl) {

            boardImageRepository.save(BoardImage.builder()
                    .boardImage(imageUrl)
                    .board(Board.builder().idx(boardIdx).build())
                    .status(true)
                    .build());
    }

}

