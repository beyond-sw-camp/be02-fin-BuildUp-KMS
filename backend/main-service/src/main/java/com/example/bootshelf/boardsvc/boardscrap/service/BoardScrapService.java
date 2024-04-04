package com.example.bootshelf.boardsvc.boardscrap.service;

import com.example.bootshelf.boardsvc.board.model.entity.Board;
import com.example.bootshelf.boardsvc.board.repository.BoardRepository;
import com.example.bootshelf.boardsvc.boardscrap.model.entity.BoardScrap;
import com.example.bootshelf.boardsvc.boardscrap.model.request.PostCreateBoardScrapReq;
import com.example.bootshelf.boardsvc.boardscrap.model.response.GetCheckBoardScrapRes;
import com.example.bootshelf.boardsvc.boardscrap.model.response.GetListBoardResByScrap;
import com.example.bootshelf.boardsvc.boardscrap.model.response.GetListBoardResByScrapRes;
import com.example.bootshelf.boardsvc.boardscrap.model.response.PostCreateBoardScrapRes;
import com.example.bootshelf.boardsvc.boardscrap.repository.BoardScrapRepository;
import com.example.bootshelf.boardsvc.boardtag.model.entity.BoardTag;
import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.common.error.ErrorCode;
import com.example.bootshelf.common.error.entityexception.BoardException;
import com.example.bootshelf.common.error.entityexception.BoardScrapException;
import com.example.bootshelf.user.model.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardScrapService {
    private final BoardScrapRepository boardScrapRepository;
    private final BoardRepository boardRepository;

    @Transactional(readOnly = false)
    public BaseRes createBoardScrap(User user, PostCreateBoardScrapReq req) {
        Board board = boardRepository.findByIdx(req.getBoardIdx())
                .orElseThrow(() -> new BoardException(ErrorCode.BOARD_NOT_EXISTS, String.format("Board [ %s ] is not exists.", req.getBoardIdx())));

        BoardScrap boardScrapResult = boardScrapRepository.findByUserIdxAndBoardIdx(user.getIdx(), req.getBoardIdx());
        if (boardScrapResult != null) {
            if (boardScrapResult.getStatus().equals(true))
                throw new BoardScrapException(ErrorCode.DUPLICATED_BOARD_SCRAP, String.format("Board [ %s ] is already scrapped.", req.getBoardIdx()));
            else {
                boardScrapResult.setStatus(true);
                boardScrapRepository.save(boardScrapResult);

                board.increaseScrapCnt();
                boardRepository.save(board);

                PostCreateBoardScrapRes res = PostCreateBoardScrapRes.toDto(boardScrapResult);

                return BaseRes.builder()
                        .isSuccess(true)
                        .message("게시판 게시글 스크랩 등록 성공")
                        .result(res)
                        .build();
            }
        }

        BoardScrap boardScrap = BoardScrap.toEntity(user, req);

        boardScrap = boardScrapRepository.save(boardScrap);

        board.increaseScrapCnt();
        boardRepository.save(board);

        PostCreateBoardScrapRes res = PostCreateBoardScrapRes.toDto(boardScrap);

        return BaseRes.builder()
                .isSuccess(true)
                .message("게시판 게시글 스크랩 등록 성공")
                .result(res)
                .build();
    }

    @Transactional(readOnly = true)
    public BaseRes checkBoardScrap(User user, Integer boardIdx) {
        BoardScrap boardScrapResult = boardScrapRepository.findByUserIdxAndBoardIdx(user.getIdx(), boardIdx);
        if (boardScrapResult != null) {
            if (boardScrapResult.getStatus().equals(true)) {
                GetCheckBoardScrapRes res = GetCheckBoardScrapRes.builder()
                        .boardScrapIdx(boardScrapResult.getIdx())
                        .status(true)
                        .build();

                return BaseRes.builder()
                        .isSuccess(true)
                        .message("게시물 스크랩 여부 확인 성공")
                        .result(res)
                        .build();
            } else {
                GetCheckBoardScrapRes res = GetCheckBoardScrapRes.builder()
                        .boardScrapIdx(boardIdx)
                        .status(false)
                        .build();

                return BaseRes.builder()
                        .isSuccess(true)
                        .message("게시물 스크랩 여부 확인 성공")
                        .result(res)
                        .build();
            }

        } else {
            GetCheckBoardScrapRes res2 = GetCheckBoardScrapRes.builder()
                    .boardScrapIdx(boardIdx)
                    .status(false)
                    .build();

            return BaseRes.builder()
                    .isSuccess(true)
                    .message("게시물 스크랩 여부 확인 성공")
                    .result(res2)
                    .build();
        }
    }

    // 목록 조회 시 글만 추출
    public static String extractText(String html) {
        Document doc = Jsoup.parse(html);
        return doc.text();
    }

    @Transactional(readOnly = true)
    public BaseRes findBoardScrapListByCategory(User user, Integer boardCategoryIdx, Integer sortIdx, Pageable pageable) {
        Page<BoardScrap> boardScrapList = boardScrapRepository.findBoardScrapListByCategory(user, boardCategoryIdx, sortIdx, pageable);
//        if (boardScrapList.isEmpty())
//            throw new BoardException(ErrorCode.BOARD_SCRAP_IS_EMPTY, "스크랩한 게시글이 존재하지 않습니다.");

        List<GetListBoardResByScrap> resultList = new ArrayList<>();
        for (BoardScrap boardScrap : boardScrapList.getContent()) {

            List<BoardTag> boardTagList = boardScrap.getBoard().getBoardTagList();
            List<String> tagNames = new ArrayList<>();

            for (BoardTag boardTag : boardTagList) {
                String tagName = boardTag.getTag().getTagName();
                tagNames.add(tagName);
            }

            String textContent = extractText(boardScrap.getBoard().getBoardContent());

            GetListBoardResByScrap res = GetListBoardResByScrap.builder()
                    .idx(boardScrap.getBoard().getIdx())
                    .scrapIdx(boardScrap.getIdx())
                    .nickName(boardScrap.getBoard().getUser().getNickName())
                    .userProfileImage(user.getProfileImage())
                    .title(boardScrap.getBoard().getBoardTitle())
                    .content(textContent)
                    .boardCategoryIdx(boardScrap.getBoard().getBoardCategory().getIdx())
                    .tagNameList(tagNames)
                    .viewCnt(boardScrap.getBoard().getViewCnt())
                    .upCnt(boardScrap.getBoard().getUpCnt())
                    .scrapCnt(boardScrap.getBoard().getScrapCnt())
                    .commentCnt(boardScrap.getBoard().getCommentCnt())
                    .type("board")
                    .boardType("scrap")
                    .createdAt(boardScrap.getBoard().getCreatedAt())
                    .updatedAt(boardScrap.getBoard().getUpdatedAt())
                    .build();

            resultList.add(res);
        }
        Long totalCnt = boardScrapList.getTotalElements();
        Integer totalPages = boardScrapList.getTotalPages();

        GetListBoardResByScrapRes result = GetListBoardResByScrapRes.builder()
                .totalCnt(totalCnt)
                .totalPages(totalPages)
                .list(resultList)
                .build();

        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("메인 페이지 검색 결과 조회 성공 <게시판>")
                .result(result)
                .build();

        return baseRes;
    }

    @Transactional(readOnly = false)
    public BaseRes deleteBoardScrap(User user, Integer boardScrapIdx) {
        Optional<BoardScrap> result = boardScrapRepository.findByIdx(boardScrapIdx);
        if (result.isPresent()) {
            BoardScrap boardScrap = result.get();
            Board board = boardScrap.getBoard();

            if (boardScrap.getUser().getIdx().equals(user.getIdx())) {
                boardScrap.setStatus(false);
                boardScrapRepository.save(boardScrap);

                board.decreaseScrapCnt();
                boardRepository.save(board);

                return BaseRes.builder()
                        .isSuccess(true)
                        .message("게시글 스크랩 삭제 성공")
                        .build();
            }
            throw new BoardScrapException(ErrorCode.UNAUTHORIZED_BOARD_SCRAP,
                    String.format("User [ %s ] is already scrapped.\n " +
                            "User who scrapped board is [ %s ].", user.getIdx(), boardScrap.getUser().getIdx()));
        }
        throw new BoardScrapException(ErrorCode.BOARD_SCRAP_NOT_EXISTS, String.format("Board scrap idx [ %s ] is not exists.", boardScrapIdx));
    }

// 현재 미사용 API
//    @Transactional(readOnly = true)
//    public BaseRes findBoardScrapList(User user, Pageable pageable) {
//        Page<BoardScrap> boardScrapList = boardScrapRepository.findByUser(user, pageable);
//        if (boardScrapList.isEmpty())
//            throw new BoardScrapException(ErrorCode.BOARD_SCRAP_IS_EMPTY, "스크랩한 게시글이 존재하지 않습니다.");
//
//        List<GetFindBoardScrapRes> resultList = new ArrayList<>();
//        for (BoardScrap boardScrap : boardScrapList.getContent()) {
//
//            if (boardScrap.getStatus() == false) {
//                continue;
//            }
//
//            GetFindBoardScrapRes res = GetFindBoardScrapRes.builder()
//                    .boardScrapIdx(boardScrap.getIdx())
//                    .boardIdx(boardScrap.getBoard().getIdx())
//                    .boardCategoryIdx(boardScrap.getBoard().getBoardCategory().getIdx())
//                    .createdAt(boardScrap.getCreatedAt())
//                    .updatedAt(boardScrap.getUpdatedAt())
//                    .build();
//
//            resultList.add(res);
//        }
//
//        return BaseRes.builder()
//                .isSuccess(true)
//                .message("게시글 스크랩 목록 조회 성공")
//                .result(resultList)
//                .build();
//    }
}