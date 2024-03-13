package com.example.bootshelf.boardsvc.boardtag.service;

import com.example.bootshelf.boardsvc.board.model.entity.Board;
import com.example.bootshelf.boardsvc.boardtag.model.entity.BoardTag;
import com.example.bootshelf.boardsvc.boardtag.repository.BoardTagRepository;
import com.example.bootshelf.tag.model.entity.Tag;
import com.example.bootshelf.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardTagService {
    private final TagService tagService;
    private final BoardTagRepository boardTagRepository;
    // 입력 받은 태그가 없다면 반환,
    // 만약 태그가 없다면 메소드 실행
    // 입력받은 태그가 DB에 있는지 repo를 통해 확인
    // 입력받은 태그가 DB에 있다면 반환,
    // 입력받은 태그가 DB에 없다면 DB에 입력후 태그를 게시글에 저장
    public BoardTag saveBoardTag(List<String> reqTagList, Integer idx){
        List<Tag> tagList1 = new ArrayList<>();
        BoardTag bt = new BoardTag();
        for (String str : reqTagList) {
            Tag tag = tagService.createTag(str);
            tagList1.add(tag);
        }

        for (Tag tag : tagList1) {
            BoardTag boardTag = BoardTag.builder()
                    .tag(tag)
                    .board(Board.builder().idx(idx).build())
                    .status(true)
                    .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                    .updatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                    .build();

            bt = boardTagRepository.save(boardTag);
        }
        return bt;
    }
}
