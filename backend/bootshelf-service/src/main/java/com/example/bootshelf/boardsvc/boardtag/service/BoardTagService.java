package com.example.bootshelf.boardsvc.boardtag.service;

import com.example.bootshelf.boardsvc.board.model.entity.Board;
import com.example.bootshelf.boardsvc.boardtag.model.entity.BoardTag;
import com.example.bootshelf.boardsvc.boardtag.model.response.GetListHotTagRes;
import com.example.bootshelf.boardsvc.boardtag.model.response.GetListHotTagResResult;
import com.example.bootshelf.boardsvc.boardtag.repository.BoardTagRepository;
import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.common.error.ErrorCode;
import com.example.bootshelf.common.error.entityexception.BoardTagException;
import com.example.bootshelf.tag.model.entity.Tag;
import com.example.bootshelf.tag.repository.TagRepository;
import com.example.bootshelf.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardTagService {

    private final TagService tagService;
    private final BoardTagRepository boardTagRepository;
    private final TagRepository tagRepository;

    // 입력 받은 태그가 없다면 반환,
    // 만약 태그가 없다면 메소드 실행
    // 입력받은 태그가 DB에 있는지 repo를 통해 확인
    // 입력받은 태그가 DB에 있다면 반환,
    // 입력받은 태그가 DB에 없다면 DB에 입력후 태그를 게시글에 저장
    public void saveBoardTag(List<String> reqTagList, Integer idx) {
        List<Tag> tagList = new ArrayList<>();
        for (String str : reqTagList) {
            Optional<Tag> existTag = tagRepository.findByTagName(str);
            if (existTag.isPresent()) {
                tagList.add(existTag.get());
            } else {
                Tag tag = tagService.createTag(str);
                tagList.add(tag);
            }
        }
        for (Tag tag : tagList) {
            BoardTag boardTag = BoardTag.builder()
                    .tag(tag)
                    .board(Board.builder().idx(idx).build())
                    .status(true)
                    .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                    .updatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                    .build();

            boardTagRepository.save(boardTag);
        }
    }

    @Transactional(readOnly = false)
    public void updateBoardTag(List<String> reqTagList, Integer idx) {


        Integer result = boardTagRepository.deleteAllByBoard_Idx(idx);

        if(result.equals(0)) {
            throw new BoardTagException(ErrorCode.BOARD_TAG_NOT_EXISTS, String.format("Board [ %s ] does not have BoardTag", idx));
        }
        saveBoardTag(reqTagList, idx);
    }

    public BaseRes listHotTag() {

        List<GetListHotTagRes> boardTagList = boardTagRepository.findByHotTagList();

        List<GetListHotTagResResult> getListHotTagResResultList = new ArrayList<>();

        for(GetListHotTagRes GetListHotTagRes : boardTagList) {

            GetListHotTagResResult getListHotTagResResult= GetListHotTagResResult.builder()
                    .tagIdx(GetListHotTagRes.getTagIdx())
                    .tagName(GetListHotTagRes.getTagName())
                    .count(GetListHotTagRes.getCount())
                    .build();

            getListHotTagResResultList.add(getListHotTagResResult);
        }

        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("인기 태그 조회 성공")
                .result(getListHotTagResResultList)
                .build();

        return baseRes;
    }
}
