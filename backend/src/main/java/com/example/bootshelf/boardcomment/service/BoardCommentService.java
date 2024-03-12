package com.example.bootshelf.boardcomment.service;

import com.example.bootshelf.board.model.entity.Board;
import com.example.bootshelf.boardcomment.exception.BoardCommentException;
import com.example.bootshelf.boardcomment.model.entity.BoardComment;
import com.example.bootshelf.boardcomment.model.request.PatchUpdateBoardCommentReq;
import com.example.bootshelf.boardcomment.model.request.PostCreateBoardCommentReq;
import com.example.bootshelf.boardcomment.model.request.PostCreateBoardReplyReq;
import com.example.bootshelf.boardcomment.model.response.GetListBoardCommentRes;
import com.example.bootshelf.boardcomment.model.response.PatchUpdateBoardCommentRes;
import com.example.bootshelf.boardcomment.model.response.PostCreateBoardCommentRes;
import com.example.bootshelf.boardcomment.model.response.PostCreateBoardReplyRes;
import com.example.bootshelf.boardcomment.repository.BoardCommentRepository;
import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.common.error.ErrorCode;

import com.example.bootshelf.user.model.entity.User;
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
public class BoardCommentService {
    private final BoardCommentRepository boardCommentRepository;

    // 댓글 작성
    @Transactional(readOnly = false)
    public BaseRes createBoardComment(User user, Integer boardIdx, PostCreateBoardCommentReq postCreateBoardCommentReq) {

        // 댓글의 내용이 비어있을 때
        if (postCreateBoardCommentReq.getBoardCommentContent() == null || postCreateBoardCommentReq.getBoardCommentContent().isEmpty()) {
            throw new BoardCommentException(ErrorCode.INVALID_INPUT_VALUE, String.format("Board Comment Content is empty."));
        }

        boardCommentRepository.save(BoardComment.builder()
                    .board(Board.builder().idx(boardIdx).build())
                    .user(user)
                    .commentContent(postCreateBoardCommentReq.getBoardCommentContent())
                    .status(true)
                    .upCnt(0)
                    .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .updatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .build());

            PostCreateBoardCommentRes postCreateBoardCommentRes = PostCreateBoardCommentRes.builder()
                    .boardCommentContent(postCreateBoardCommentReq.getBoardCommentContent())
                    .build();

            BaseRes baseRes = BaseRes.builder()
                    .isSuccess(true)
                    .message("댓글 등록 성공")
                    .result(postCreateBoardCommentRes)
                    .build();

            return baseRes;

    }

    // 댓글/대댓글 조회
    @Transactional(readOnly = true)
    public BaseRes listBoardComment(Integer boardIdx) {
        List<BoardComment> topLevelComments = boardCommentRepository.findByBoardIdxAndParentIsNull(boardIdx);
        List<GetListBoardCommentRes> response = new ArrayList<>();

        // 상위 댓글에 대해 응답을 구성
        for (BoardComment topLevelComment : topLevelComments) {
            GetListBoardCommentRes topLevelResponse = buildResponseWithChildren(topLevelComment);
            response.add(topLevelResponse);
        }

        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("요청 성공")
                .result(response)
                .build();

        return baseRes;
    }

    // 댓글과 대댓글에 대한 응답을 구성
    private GetListBoardCommentRes buildResponseWithChildren(BoardComment boardComment) {
        GetListBoardCommentRes getListBoardCommentRes = GetListBoardCommentRes.builder()
                .idx(boardComment.getIdx())
                .boardIdx(boardComment.getBoard().getIdx())
                .userIdx(boardComment.getUser().getIdx())
                .nickName(boardComment.getUser().getNickName())
                .boardCommnetContent(boardComment.getCommentContent())
                .createAt(boardComment.getCreatedAt())
                .updateAt(boardComment.getUpdatedAt())
                .build();

        List<GetListBoardCommentRes> childrenResponses = new ArrayList<>();
        buildChildrenResponses(boardComment, childrenResponses);
        getListBoardCommentRes.setChildren(childrenResponses);

        return getListBoardCommentRes;
    }

    private void buildChildrenResponses(BoardComment parent, List<GetListBoardCommentRes> childrenResponses) {
        List<BoardComment> childrenComments = parent.getChildren();
        if (childrenComments != null && !childrenComments.isEmpty()) {
            for (BoardComment childComment : childrenComments) {
                GetListBoardCommentRes childResponse = buildResponseWithChildren(childComment);
                childrenResponses.add(childResponse);
            }
        }
    }

    // 댓글/대댓글 수정
    @Transactional(readOnly = false)
    public BaseRes updateBoardComment(User user, Integer boardIdx, Integer idx, PatchUpdateBoardCommentReq patchUpdateBoardCommentReq) {
        Optional<BoardComment> result = boardCommentRepository.findByIdx(idx);

        // 수정하고자 하는 댓글/대댓글을 찾지 못할 때
        if (!result.isPresent()) {
            throw new BoardCommentException(ErrorCode.BOARD_COMMENT_NOT_EXISTS, String.format("BoardCommentIdx [ idx : %s ] is not exists.", idx));
        }

        // 수정할 내용이 비어있을 때
        if (result.isPresent()) {
            if (patchUpdateBoardCommentReq.getBoardCommentContent() == null || patchUpdateBoardCommentReq.getBoardCommentContent().isEmpty()) {
                throw new BoardCommentException(ErrorCode.INVALID_INPUT_VALUE, String.format("Content is empty."));
            }

            BoardComment boardComment = result.get();
            boardComment.setUpdatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            boardComment.setCommentContent(patchUpdateBoardCommentReq.getBoardCommentContent());

            boardCommentRepository.save(boardComment);

            PatchUpdateBoardCommentRes patchUpdateBoardCommentRes = PatchUpdateBoardCommentRes.builder()
                    .idx(boardComment.getIdx())
                    .userIdx(boardComment.getUser().getIdx())
                    .nickName(boardComment.getUser().getNickName())
                    .boardCommentContent(boardComment.getCommentContent())
                    .createAt(boardComment.getCreatedAt())
                    .updateAt(boardComment.getUpdatedAt())
                    .build();

            BaseRes baseRes = BaseRes.builder()
                    .isSuccess(true)
                    .message("댓글 수정 성공")
                    .result(patchUpdateBoardCommentRes)
                    .build();

            return baseRes;
        }
        return null;
    }

    // 댓글/대댓글 삭제
    @Transactional(readOnly = false)
    public BaseRes deleteBoardComment(Integer idx, User user) {
        Optional<BoardComment> result = boardCommentRepository.findByIdx(idx);

        // 삭제하고자 하는 댓글을 찾지 못할 때
        if (result.equals(0)) {
            throw new BoardCommentException(ErrorCode.BOARD_COMMENT_NOT_EXISTS, String.format("BoardCommentIdx [ idx : %s ] is not exists.", idx));
        }

        BoardComment boardComment = result.get();

        if (boardComment.getParent() == null) {
            // 하위 댓글들도 함께 삭제
            deleteChildrenComments(boardComment.getChildren());
        }

        boardCommentRepository.delete(boardComment);

        return BaseRes.builder()
                .isSuccess(true)
                .message("댓글 삭제 성공")
                .result("후기글 댓글 삭제 성공")
                .build();
    }

    private void deleteChildrenComments(List<BoardComment> children) {
        if (children != null && !children.isEmpty()) {
            for (BoardComment childComment : children) {
                // 재귀적으로 하위 댓글 삭제
                deleteChildrenComments(childComment.getChildren());
                boardCommentRepository.delete(childComment);
            }
        }
    }
    // 대댓글 작성
    @Transactional(readOnly = false)
    public BaseRes createBoardReply(User user, Integer boardIdx, Integer parentIdx, PostCreateBoardReplyReq postCreateBoardReplyReq) {

        Optional<BoardComment> parentBoardComment = boardCommentRepository.findById(parentIdx);

        // 상위댓글이 없을 때
        if (parentBoardComment.equals(0)) {
            throw new BoardCommentException(ErrorCode.BOARD_COMMENT_NOT_EXISTS, String.format("parentIdx [ idx : %s ] is not exists.", parentIdx));
        }

        // 대댓글의 내용이 비어있을 때
        if (postCreateBoardReplyReq.getBoardReplyContent() == null || postCreateBoardReplyReq.getBoardReplyContent().isEmpty()) {
            throw new BoardCommentException(ErrorCode.INVALID_INPUT_VALUE, String.format("Content is empty."));
        }

        BoardComment childrenBoardComment = BoardComment.builder()
                .board(Board.builder().idx(boardIdx).build())
                .user(user)
                .parent(parentBoardComment.get())  // 부모 댓글 설정
                .commentContent(postCreateBoardReplyReq.getBoardReplyContent())
                .status(true)
                .upCnt(0)
                .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .updatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();

        parentBoardComment.get().getChildren().add(childrenBoardComment);

        boardCommentRepository.save(childrenBoardComment);

        PostCreateBoardReplyRes postCreateBoardReplyRes = PostCreateBoardReplyRes.builder()
                .parentIdx(childrenBoardComment.getParent().getIdx())
                .boardCommentContent(postCreateBoardReplyReq.getBoardReplyContent())
                .build();

        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("댓글 등록 성공")
                .result(postCreateBoardReplyRes)
                .build();

        return baseRes;
    }
}
