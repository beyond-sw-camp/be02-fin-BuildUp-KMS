package com.example.bootshelf.boardsvc.boardimage.service;

import com.example.bootshelf.aws.service.S3Service;
import com.example.bootshelf.boardsvc.board.model.entity.Board;
import com.example.bootshelf.boardsvc.boardimage.model.entity.BoardImage;
import com.example.bootshelf.boardsvc.boardimage.repository.BoardImageRepository;
import com.example.bootshelf.utils.ImageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class BoardImageService {
    private final S3Service s3Service;
    private final BoardImageRepository boardImageRepository;

    public void createBoardImage(Integer id, MultipartFile[] images){
        for(MultipartFile image: images){
            String savePath = ImageUtils.makeBoardImagePath(image.getOriginalFilename());
            savePath = s3Service.uploadBoardFile(image, savePath);

            BoardImage boardImage = BoardImage.builder()
                    .boardImage(savePath)
                    .board(Board.builder().idx(id).build())
                    .status(true)
                    .build();

            boardImageRepository.save(boardImage);
        }
    }
}
