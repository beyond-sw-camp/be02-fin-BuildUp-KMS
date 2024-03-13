package com.example.bootshelf.board.service;

import com.amazonaws.services.kms.model.NotFoundException;
import com.example.bootshelf.board.model.entity.Board;
import com.example.bootshelf.board.model.entity.req.PatchUpdateBoardReq;
import com.example.bootshelf.board.model.entity.req.PostCreateBoardReq;
import com.example.bootshelf.board.model.entity.res.GetListBoardRes;
import com.example.bootshelf.board.model.entity.res.PostCreateBoardRes;
import com.example.bootshelf.board.repository.BoardRepository;
import com.example.bootshelf.boardcategory.model.entity.BoardCategory;
import com.example.bootshelf.boardcategory.repository.BoardCategoryRepository;
import com.example.bootshelf.boardcomment.model.entity.BoardComment;
import com.example.bootshelf.boardimage.model.entity.BoardImage;
import com.example.bootshelf.boardimage.service.BoardImageService;
import com.example.bootshelf.boardtag.model.entity.BoardTag;
import com.example.bootshelf.boardtag.model.entity.PostCreateBoardTagRes;
import com.example.bootshelf.boardtag.repository.BoardTagRepository;
import com.example.bootshelf.boardtag.service.BoardTagService;
import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.tag.model.entity.Tag;
import com.example.bootshelf.tag.service.TagService;
import com.example.bootshelf.user.model.entity.User;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardCategoryRepository boardCategoryRepository;
    private final BoardTagService boardTagService;
    private final BoardImageService boardImageService;
    private final TagService tagService;

    public PostCreateBoardRes createBoard(User user, PostCreateBoardReq request, MultipartFile[] uploadFiles) {

        Optional<Board> result = boardRepository.findByBoardTitle(request.getTitle());

        if (result.isPresent()) {
            throw new DuplicateRequestException("같은 이름을 가진 게시들이 존재합니다");
        }

        Board board = Board.builder()
                .boardTitle(request.getTitle())
                .boardContent(request.getContent())
                .boardCategory(BoardCategory.builder().idx(request.getBoardCategoryIdx()).build())
                .user(user)
                .status(true)
                .viewCnt(0)
                .upCnt(0)
                .commentCnt(0)
                .scrapCnt(0)
                .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                .updatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                .build();

        board = boardRepository.save(board);

        boardImageService.createBoardImage(board.getIdx(),uploadFiles);

        if(request.getTagList()!=null){
            boardTagService.saveBoardTag(request.getTagList(), board.getIdx());
        }

        PostCreateBoardRes response = PostCreateBoardRes.builder()
                .idx(board.getIdx())
                .boardtitle(board.getBoardTitle())
                .boardcontent(board.getBoardContent())
                .boardCategory(board.getBoardCategory())
                .boardTagList(request.getTagList())
                .build();

        return  response;
    }

    public BaseRes listBoard(Integer boardIdx){
        Optional<Board>result = boardRepository.findByIdx(boardIdx);

        if(!result.isPresent()){
            throw new NotFoundException("해당 게시글을 찾을 수 없습니다");
        }
        else {
            if(!result.get().getStatus()==true){
                throw new NotFoundException("해당 게시글을 찾을 수 없습니다");
            }
            else{
                Board board = result.get();
                List<BoardImage> boardImageList = board.getBoardImageList();
                List<String> fileNames = new ArrayList<>();

                for(BoardImage boardImage : boardImageList){
                    String fileName = boardImage.getBoardImage();
                    fileNames.add(fileName);
                }
                List<BoardTag> boardTagList = board.getBoardTagList();
                List<Integer> tagIdxs = new ArrayList<>();

                for(BoardTag boardTag : boardTagList){
                    Integer tagIdx = boardTag.getIdx();
                    tagIdxs.add(tagIdx);
                }
                List<BoardComment> commentList = board.getBoardCommentList();
                List<Integer> commentIdxs = new ArrayList<>();

                for(BoardComment boardComment : commentList){
                    Integer commentIdx = boardComment.getIdx();
                    commentIdxs.add(commentIdx);
                }

                GetListBoardRes res = GetListBoardRes.builder()
                        .idx(board.getIdx())
                        .boardTitle(board.getBoardTitle())
                        .boardContent(board.getBoardContent())
                        .boardCategoryIdx(board.getBoardCategory().getIdx())
                        .boardTagListIdx(tagIdxs)
                        .boardImageList(fileNames)
                        .boardCommentList(commentIdxs)
                        .viewCnt(board.getViewCnt())
                        .upCnt(board.getUpCnt())
                        .scrapCnt(board.getScrapCnt())
                        .createdAt(board.getCreatedAt())
                        .updatedAt(board.getUpdatedAt())
                        .userProfile(board.getUser().getProfileImage())
                        .userName(board.getUser().getName())
                        .build();

                BaseRes baseRes = BaseRes.builder()
                        .message("게시글 조회 성공")
                        .isSuccess(true)
                        .result(res)
                        .build();

                return baseRes;
            }
        }
    }
    public BaseRes updateBoard (User user, PatchUpdateBoardReq patchUpdateBoardReq, Integer boardIdx){
        Optional<Board> result = boardRepository.findByIdxAndUserIdx(user.getIdx(), boardIdx);

        if(!result.isPresent()){
            throw new NotFoundException("게시글을 찾을 수 없습니다.");
        }
        Optional<Board> resultTitle = boardRepository.findByBoardTitle(patchUpdateBoardReq.getBoardTitle());

        if(result.isPresent()){
            throw new DuplicateRequestException("이미 존재하는 게시글 제목입니다.");
        }


        Board board = result.get();

        board.setBoardTitle(patchUpdateBoardReq.getBoardTitle());
        board.setBoardContent(patchUpdateBoardReq.getBoardContent());
        board.setBoardCategory(BoardCategory.builder().idx(patchUpdateBoardReq.getBoardCategoryIdx()).build());

        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("게시글 수정 성공")
                .result("요청 성공")
                .build();

        return baseRes;
    }
}
