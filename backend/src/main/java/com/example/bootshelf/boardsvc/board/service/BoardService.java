package com.example.bootshelf.boardsvc.board.service;

import com.example.bootshelf.boardsvc.board.model.entity.Board;
import com.example.bootshelf.boardsvc.board.model.request.PatchUpdateBoardReq;
import com.example.bootshelf.boardsvc.board.model.request.PostCreateBoardReq;
import com.example.bootshelf.boardsvc.board.model.response.*;
import com.example.bootshelf.boardsvc.board.model.response.GetReadBoardRes;
import com.example.bootshelf.boardsvc.board.model.response.GetListBoardRes;
import com.example.bootshelf.boardsvc.board.model.response.PostCreateBoardRes;
import com.example.bootshelf.boardsvc.board.repository.BoardRepository;
import com.example.bootshelf.boardsvc.boardcategory.model.entity.BoardCategory;
import com.example.bootshelf.boardsvc.boardcomment.model.entity.BoardComment;
import com.example.bootshelf.boardsvc.boardscrap.model.response.GetFindBoardScrapRes;
import com.example.bootshelf.boardsvc.boardscrap.model.response.GetFindBoardScrapResResult;
import com.example.bootshelf.boardsvc.boardtag.model.entity.BoardTag;
import com.example.bootshelf.boardsvc.boardtag.service.BoardTagService;
import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.common.error.ErrorCode;
import com.example.bootshelf.common.error.entityexception.BoardException;
import com.example.bootshelf.reviewsvc.review.model.entity.Review;
import com.example.bootshelf.reviewsvc.review.model.response.GetListCommentReviewRes;
import com.example.bootshelf.reviewsvc.review.repository.ReviewRepository;
import com.example.bootshelf.reviewsvc.reviewcomment.model.entity.ReviewComment;
import com.example.bootshelf.user.model.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardTagService boardTagService;
    private final ReviewRepository reviewRepository;


    @Transactional(readOnly = false)
    public BaseRes createBoard(User user, PostCreateBoardReq request) {

        Optional<Board> result = boardRepository.findByBoardTitle(request.getBoardTitle());

        if (result.isPresent()) {
            throw new BoardException(ErrorCode.DUPICATED_BOARD_TITLE, String.format("Board Title [ %s ] is duplicated.", request.getBoardTitle()));
        }

        Board board = Board.builder()
                .boardTitle(request.getBoardTitle())
                .boardContent(request.getBoardContent())
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

        if (request.getTagList() != null) {
            boardTagService.saveBoardTag(request.getTagList(), board.getIdx());
        }

        PostCreateBoardRes response = PostCreateBoardRes.builder()
                .boardIdx(board.getIdx())
                .boardtitle(board.getBoardTitle())
                .boardcontent(board.getBoardContent())
                .boardCategoryIdx(board.getBoardCategory().getIdx())
                .boardTagList(request.getTagList())
                .build();

        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("게시글 등록 성공")
                .result(response)
                .build();

        return baseRes;
    }

    // 게시글 상세 조회
    @Transactional(readOnly = true)
    public BaseRes findBoardByIdx(Integer boardIdx) {
        Optional<Board> result = boardRepository.findByIdx(boardIdx);

        if (!result.isPresent()) {
            throw new BoardException(ErrorCode.BOARD_NOT_EXISTS, String.format("Board Idx [ %s ] is not exists.", boardIdx));
        }

        Board board = result.get();
        board.increaseViewCnt();
        board.setUpdatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
        boardRepository.save(board);

        // 댓글 조회
        List<GetListCommentBoardRes> getListCommentBoardResList = new ArrayList<>();

        for (BoardComment boardComment : board.getBoardCommentList()) {

            // 댓글이 최상위 댓글일 때만 처리
            if (boardComment.getParent() == null) {
                getListCommentBoardResList.add(convertToCommentBoardRes(boardComment));
            }
        }

        // 태그 조회
        List<BoardTag> boardTagList = board.getBoardTagList();
        List<String> tagNames = new ArrayList<>();

        if (!boardTagList.isEmpty()) {
            for (BoardTag boardTag : boardTagList) {
                String tagName = boardTag.getTag().getTagName();
                tagNames.add(tagName);
            }
        }

        GetReadBoardRes getReadBoardRes = GetReadBoardRes.builder()
                .idx(board.getIdx())
                .boardCategoryIdx(board.getBoardCategory().getIdx())
                .boardCategoryName(board.getBoardCategory().getCategoryName())
                .nickName(board.getUser().getNickName())
                .userProfileImage(board.getUser().getProfileImage())
                .boardTitle(board.getBoardTitle())
                .boardContent(board.getBoardContent())
                .tagList(tagNames)
                .commentCnt(board.getCommentCnt())
                .viewCnt(board.getViewCnt())
                .upCnt(board.getUpCnt())
                .scrapCnt(board.getScrapCnt())
                .createdAt(board.getCreatedAt())
                .updatedAt(board.getUpdatedAt())
                .boardCommentList(getListCommentBoardResList)
                .build();

        BaseRes baseRes = BaseRes.builder()
                .message("게시글 상세 조회 요청 성공")
                .isSuccess(true)
                .result(getReadBoardRes)
                .build();

        return baseRes;
    }

    // 게시글 댓글 조회
    private GetListCommentBoardRes convertToCommentBoardRes(BoardComment boardComment) {

        List<GetListCommentBoardRes> childCommentsRes = new ArrayList<>();

        for (BoardComment childComment : boardComment.getChildren()) {
            GetListCommentBoardRes childRes = convertToCommentBoardRes(childComment);
            childCommentsRes.add(childRes);
        }

        return GetListCommentBoardRes.builder()
                .commentIdx(boardComment.getIdx())
                .userIdx(boardComment.getUser().getIdx())
                .userNickName(boardComment.getUser().getNickName())
                .profileImage(boardComment.getUser().getProfileImage())
                .boardCommentContent(boardComment.getCommentContent())
                .upCnt(boardComment.getUpCnt())
                .updatedAt(boardComment.getUpdatedAt())
                .children(childCommentsRes)
                .build();
    }

    // 목록 조회 시 글만 추출
    public static String extractText(String html) {
        Document doc = Jsoup.parse(html);
        return doc.text();
    }

    // 내 작성글 조회.
    @Transactional(readOnly = true)
    public BaseRes findMyBoardList(User user, Pageable pageable, Integer sortIdx) {
        Page<Board> boardList = boardRepository.findMyBoardList(user.getIdx(), pageable, sortIdx);
        List<GetListBoardRes> getListBoardResList = new ArrayList<>();

        for (Board board : boardList) {

            List<BoardTag> boardTagList = board.getBoardTagList();
            List<String> tagNames = new ArrayList<>();

            for (BoardTag boardTag : boardTagList) {
                String tagName = boardTag.getTag().getTagName();
                tagNames.add(tagName);
            }

            String textContent = extractText(board.getBoardContent());

            GetListBoardRes getListBoardRes = GetListBoardRes.builder()
                    .idx(board.getIdx())
                    .nickName(user.getNickName())
                    .userProfileImage(user.getProfileImage())
                    .title(board.getBoardTitle())
                    .content(textContent)
                    .boardCategoryIdx(board.getBoardCategory().getIdx())
                    .tagNameList(tagNames)
                    .viewCnt(board.getViewCnt())
                    .upCnt(board.getUpCnt())
                    .scrapCnt(board.getScrapCnt())
                    .commentCnt(board.getCommentCnt())
                    .createdAt(board.getCreatedAt())
                    .build();

            getListBoardResList.add(getListBoardRes);
        }
        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("인증회원 본인 후기글 목록 조회 요청 성공")
                .result(getListBoardResList)
                .build();

        return baseRes;
    }

    // 카테고리별 내 작성글 조회
    @Transactional(readOnly = true)
    public BaseRes findMyBoardListByCategory(User user, Pageable pageable, Integer boardCategoryIdx, Integer sortIdx) {

        Page<Board> boardList = boardRepository.findMyBoardListByCategory(user.getIdx(), pageable, boardCategoryIdx, sortIdx);
        List<GetListBoardRes> getListBoardResList = new ArrayList<>();

        for (Board board : boardList) {

            List<BoardTag> boardTagList = board.getBoardTagList();
            List<String> tagNames = new ArrayList<>();

            for (BoardTag boardTag : boardTagList) {
                String tagName = boardTag.getTag().getTagName();
                tagNames.add(tagName);
            }

            String textContent = extractText(board.getBoardContent());

            GetListBoardRes getListBoardRes = GetListBoardRes.builder()
                    .idx(board.getIdx())
                    .nickName(board.getUser().getNickName())
                    .userProfileImage(user.getProfileImage())
                    .title(board.getBoardTitle())
                    .content(textContent)
                    .boardCategoryIdx(board.getBoardCategory().getIdx())
                    .tagNameList(tagNames)
                    .viewCnt(board.getViewCnt())
                    .upCnt(board.getUpCnt())
                    .scrapCnt(board.getScrapCnt())
                    .commentCnt(board.getCommentCnt())
                    .type("board")
                    .boardType("write")
                    .createdAt(board.getCreatedAt())
                    .updatedAt(board.getUpdatedAt())
                    .build();

            getListBoardResList.add(getListBoardRes);
        }
        Long totalCnt = boardList.getTotalElements();
        Integer totalPages = boardList.getTotalPages();

        GetListBoardResResult result = GetListBoardResResult.builder()
                .totalCnt(totalCnt)
                .totalPages(totalPages)
                .list(getListBoardResList)
                .build();

        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("인증회원 본인 후기글 목록 조회 요청 성공")
                .result(result)
                .build();

        return baseRes;
    }

    // 카테고리 별 게시글 조회
    @Transactional(readOnly = true)
    public BaseRes findListByCategory(Pageable pageable, Integer boardCategoryIdx, Integer sortIdx) {
        Page<Board> boardList = boardRepository.findBoardListByCategory(pageable, boardCategoryIdx, sortIdx);
        List<GetListBoardRes> getListBoardResList = new ArrayList<>();

        for (Board board : boardList) {

            List<BoardTag> boardTagList = board.getBoardTagList();
            List<String> tagNames = new ArrayList<>();

            for (BoardTag boardTag : boardTagList) {
                String tagName = boardTag.getTag().getTagName();
                tagNames.add(tagName);
            }

            String textContent = extractText(board.getBoardContent());

            GetListBoardRes getListBoardRes = GetListBoardRes.builder()
                    .idx(board.getIdx())
                    .nickName(board.getUser().getNickName())
                    .userProfileImage(board.getUser().getProfileImage())
                    .title(board.getBoardTitle())
                    .content(textContent)
                    .boardCategoryIdx(board.getBoardCategory().getIdx())
                    .tagNameList(tagNames)
                    .viewCnt(board.getViewCnt())
                    .upCnt(board.getUpCnt())
                    .scrapCnt(board.getScrapCnt())
                    .commentCnt(board.getCommentCnt())
                    .createdAt(board.getCreatedAt())
                    .updatedAt(board.getUpdatedAt())
                    .build();

            Document doc = Jsoup.parse(board.getBoardContent());
            Elements images = doc.select("img");

            if (!images.isEmpty()) {
                getListBoardRes.setBoardImg(images.get(0).attr("src"));
            }

            getListBoardResList.add(getListBoardRes);
        }

        Long totalCnt = boardList.getTotalElements();
        Integer totalPages = boardList.getTotalPages();

        GetListBoardResResult result = GetListBoardResResult.builder()
                .totalCnt(totalCnt)
                .totalPages(totalPages)
                .list(getListBoardResList)
                .build();

        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("게시글 카테고리별 목록 조회 요청 성공")
                .result(result)
                .build();

        return baseRes;
    }


    // 태그 별 게시글 조회
    @Transactional(readOnly = false)
    public BaseRes findListByTag(Pageable pageable, Integer tagIdx, Integer boardCategoryIdx, Integer sortIdx) {
        Page<Board> boardList = boardRepository.findBoardListByTag(pageable, tagIdx, boardCategoryIdx, sortIdx);
        List<GetListBoardRes> getListBoardResList = new ArrayList<>();

        for (Board board : boardList) {

            List<BoardTag> boardTagList = board.getBoardTagList();
            List<String> tagNames = new ArrayList<>();

            for (BoardTag boardTag : boardTagList) {
                String tagName = boardTag.getTag().getTagName();
                tagNames.add(tagName);
            }

            String textContent = extractText(board.getBoardContent());

            GetListBoardRes getListBoardRes = GetListBoardRes.builder()
                    .idx(board.getIdx())
                    .nickName(board.getUser().getNickName())
                    .userProfileImage(board.getUser().getProfileImage())
                    .title(board.getBoardTitle())
                    .content(textContent)
                    .boardCategoryIdx(board.getBoardCategory().getIdx())
                    .tagNameList(tagNames)
                    .viewCnt(board.getViewCnt())
                    .upCnt(board.getUpCnt())
                    .scrapCnt(board.getScrapCnt())
                    .commentCnt(board.getCommentCnt())
                    .createdAt(board.getCreatedAt())
                    .updatedAt(board.getUpdatedAt())
                    .build();

            Document doc = Jsoup.parse(board.getBoardContent());
            Elements images = doc.select("img");

            if (!images.isEmpty()) {
                getListBoardRes.setBoardImg(images.get(0).attr("src"));
            }

            getListBoardResList.add(getListBoardRes);
        }

        Long totalCnt = boardList.getTotalElements();
        Integer totalPages = boardList.getTotalPages();

        GetListBoardResResult result = GetListBoardResResult.builder()
                .totalCnt(totalCnt)
                .totalPages(totalPages)
                .list(getListBoardResList)
                .build();

        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("게시글 태그별 목록 조회 요청 성공")
                .result(result)
                .build();

        return baseRes;
    }

    // 태그 검색 별 조회
    @Transactional(readOnly = true)
    public BaseRes findSearchListByTag(Pageable pageable, Integer tagIdx, Integer boardCategoryIdx, String searchTerm, Integer sortIdx) {

        Page<Board> boardList = boardRepository.findBoardSearchListByTag(pageable, tagIdx, boardCategoryIdx, searchTerm, sortIdx);
        List<GetListBoardRes> getListBoardResList = new ArrayList<>();

        for (Board board : boardList) {

            List<BoardTag> boardTagList = board.getBoardTagList();
            List<String> tagNames = new ArrayList<>();

            for (BoardTag boardTag : boardTagList) {
                String tagName = boardTag.getTag().getTagName();
                tagNames.add(tagName);
            }

            String textContent = extractText(board.getBoardContent());

            GetListBoardRes getListBoardRes = GetListBoardRes.builder()
                    .idx(board.getIdx())
                    .nickName(board.getUser().getNickName())
                    .userProfileImage(board.getUser().getProfileImage())
                    .title(board.getBoardTitle())
                    .content(textContent)
                    .boardCategoryIdx(board.getBoardCategory().getIdx())
                    .tagNameList(tagNames)
                    .viewCnt(board.getViewCnt())
                    .upCnt(board.getUpCnt())
                    .scrapCnt(board.getScrapCnt())
                    .commentCnt(board.getCommentCnt())
                    .createdAt(board.getCreatedAt())
                    .updatedAt(board.getUpdatedAt())
                    .build();

            Document doc = Jsoup.parse(board.getBoardContent());
            Elements images = doc.select("img");

            if (!images.isEmpty()) {
                getListBoardRes.setBoardImg(images.get(0).attr("src"));
            }

            getListBoardResList.add(getListBoardRes);
        }

        Long totalCnt = boardList.getTotalElements();
        Integer totalPages = boardList.getTotalPages();

        GetListBoardResResult result = GetListBoardResResult.builder()
                .totalCnt(totalCnt)
                .totalPages(totalPages)
                .list(getListBoardResList)
                .build();

        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("게시글 태그별 검색어 목록 조회 요청 성공")
                .result(result)
                .build();

        return baseRes;
    }


    // 검색어 별 게시글 조회
    @Transactional(readOnly = true)
    public BaseRes searchBoardListByQuery(String query, Integer searchType, Pageable pageable) {
        Page<Board> boardList = boardRepository.searchBoardListByQuery(pageable, query, searchType);

        List<GetBoardListByQueryRes> getBoardListByQueryResList = new ArrayList<>();

        for (Board board : boardList) {

            String textContent = extractText(board.getBoardContent());

            GetBoardListByQueryRes getBoardListByQueryRes = GetBoardListByQueryRes.builder()
                    .idx(board.getIdx())
                    .title(board.getBoardTitle())
                    .content(textContent)
                    .nickName(board.getUser().getNickName())
                    .createdAt(board.getCreatedAt())
                    .updatedAt(board.getUpdatedAt())
                    .viewCnt(board.getViewCnt())
                    .commentCnt(board.getCommentCnt())
                    .upCnt(board.getUpCnt())
                    .build();

            getBoardListByQueryResList.add(getBoardListByQueryRes);
        }

        Long totalCnt = boardList.getTotalElements();
        Integer totalPages = boardList.getTotalPages();

        GetBoardListByQueryResResult result = GetBoardListByQueryResResult.builder()
                .totalCnt(totalCnt)
                .totalPages(totalPages)
                .list(getBoardListByQueryResList)
                .build();

        return BaseRes.builder()
                .isSuccess(true)
                .message("메인 페이지 검색 결과 조회 성공 <게시판>")
                .result(result)
                .build();
    }


    // 검색어, 카테고리 별 게시글 조회
    @Transactional(readOnly = true)
    public BaseRes searchBoardListByQueryAndCategory(Integer boardCategoryIdx, String query, Integer sortIdx, Pageable pageable) {
        Page<Board> boardList = boardRepository.searchBoardListByQueryAndCategory(pageable, boardCategoryIdx, query, sortIdx);

        List<GetBoardListByQueryRes> getBoardListByQueryResList = new ArrayList<>();

        for (Board board : boardList) {

            List<BoardTag> boardTagList = board.getBoardTagList();
            List<String> tagNames = new ArrayList<>();

            for (BoardTag boardTag : boardTagList) {
                String tagName = boardTag.getTag().getTagName();
                tagNames.add(tagName);
            }

            String textContent = extractText(board.getBoardContent());

            GetBoardListByQueryRes getBoardListByQueryRes = GetBoardListByQueryRes.builder()
                    .idx(board.getIdx())
                    .title(board.getBoardTitle())
                    .content(textContent)
                    .nickName(board.getUser().getNickName())
                    .createdAt(board.getCreatedAt())
                    .updatedAt(board.getUpdatedAt())
                    .viewCnt(board.getViewCnt())
                    .commentCnt(board.getCommentCnt())
                    .upCnt(board.getUpCnt())
                    .tagNameList(tagNames)
                    .userProfileImage(board.getUser().getProfileImage())
                    .build();

            Document doc = Jsoup.parse(board.getBoardContent());
            Elements images = doc.select("img");

            if (!images.isEmpty()) {
                getBoardListByQueryRes.setBoardImg(images.get(0).attr("src"));
            }

            getBoardListByQueryResList.add(getBoardListByQueryRes);
        }

        Long totalCnt = boardList.getTotalElements();
        Integer totalPages = boardList.getTotalPages();

        GetBoardListByQueryResResult result = GetBoardListByQueryResResult.builder()
                .totalCnt(totalCnt)
                .totalPages(totalPages)
                .list(getBoardListByQueryResList)
                .build();

        return BaseRes.builder()
                .isSuccess(true)
                .message("메인 페이지 검색 결과 조회 성공 <게시판>")
                .result(result)
                .build();
    }

    /**
     * 게시판 + 후기 검색 api
     * -> 페이지네이션 잘 안됨
     */
    @Transactional(readOnly = true)
    public BaseRes searchResultListByQueryV2(String query, Integer searchType, Pageable pageable) {
        Page<Board> boardPage = boardRepository.searchBoardListByQueryV2(pageable, query, searchType);
        Page<Review> reviewPage = reviewRepository.searchReviewListByQueryV2(pageable, query, searchType);

        List<SearchResultRes> searchResultResList = Stream.concat(
                        boardPage.stream().map(board -> SearchResultRes.builder()
                                .idx(board.getIdx())
                                .title(board.getBoardTitle())
                                .content(board.getBoardContent())
                                .categoryName(board.getBoardCategory().getCategoryName())
                                .nickName(board.getUser().getNickName())
                                .createdAt(board.getCreatedAt())
                                .viewCnt(board.getViewCnt())
                                .commentCnt(board.getCommentCnt())
                                .upCnt(board.getUpCnt())
                                .type("Board")
                                .build()),
                        reviewPage.stream().map(review -> SearchResultRes.builder()
                                .idx(review.getIdx())
                                .title(review.getReviewTitle())
                                .content(review.getReviewContent())
                                .categoryName(review.getReviewCategory().getCategoryName())
                                .nickName(review.getUser().getNickName())
                                .createdAt(review.getCreatedAt())
                                .viewCnt(review.getViewCnt())
                                .commentCnt(review.getCommentCnt())
                                .upCnt(review.getUpCnt())
                                .type("Review")
                                .build())
                ).sorted(Comparator.comparing(SearchResultRes::getCreatedAt).reversed())
                .collect(Collectors.toList());

        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), searchResultResList.size());
        List<SearchResultRes> paginatedResults = searchResultResList.subList(start, end);

        Page<SearchResultRes> page = new PageImpl<>(paginatedResults, pageable, searchResultResList.size());

        GetResultListByQueryRes result = GetResultListByQueryRes.builder()
                .totalCnt((long) searchResultResList.size())
                .totalPages(page.getTotalPages())
                .list(page.getContent())
                .build();

        return BaseRes.builder()
                .isSuccess(true)
                .message("검색 결과 조회 성공 <게시글 + 후기>")
                .result(result)
                .build();
    }

    // 게시글 수정
    @Transactional(readOnly = false)
    public BaseRes updateBoard(User user, PatchUpdateBoardReq patchUpdateBoardReq) {
        Optional<Board> result = boardRepository.findByIdxAndUserIdx(patchUpdateBoardReq.getBoardIdx(), user.getIdx());

        if (!result.isPresent()) {
            throw new BoardException(ErrorCode.BOARD_NOT_EXISTS, String.format("Board Idx [ %s ] is not exists.", patchUpdateBoardReq.getBoardIdx()));
        }
        Board board = result.get();

        if (!board.getBoardTitle().equals(patchUpdateBoardReq.getBoardTitle())) {
            Optional<Board> resultTitle = boardRepository.findByBoardTitle(patchUpdateBoardReq.getBoardTitle());

            if (resultTitle.isPresent()) {
                throw new BoardException(ErrorCode.DUPICATED_BOARD_TITLE, String.format("Board Title [ %s ] is duplicated.", patchUpdateBoardReq.getBoardTitle()));
            }
        }

        boardTagService.updateBoardTag(patchUpdateBoardReq.getTagList(), patchUpdateBoardReq.getBoardIdx());

        board.setBoardTitle(patchUpdateBoardReq.getBoardTitle());
        board.setBoardContent(patchUpdateBoardReq.getBoardContent());
        board.setUpdatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));

        boardRepository.save(board);

        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("게시글 수정 성공")
                .result("요청 성공")
                .build();

        return baseRes;
    }

    // 게시글 삭제
    @Transactional(readOnly = false)
    public BaseRes deleteBoard(User user, Integer idx) {

        Optional<Board> result = boardRepository.findByIdxAndUserIdx(idx, user.getIdx());

        if (!result.isPresent()) {
            throw new BoardException(ErrorCode.BOARD_NOT_EXISTS, String.format("Board Idx [ %s ] is not exists.", idx));
        }

        Board board = result.get();
        board.setStatus(false);
        board.setUpdatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
        boardRepository.save(board);

        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("게시글 삭제 성공")
                .result("요청 성공")
                .build();

        return baseRes;
    }

    // 본인 게시글 상세 조회
    @Transactional(readOnly = false)
    public BaseRes findBoardDetailByUserIdx(Integer boardIdx, User user) {
        Optional<Board> result = boardRepository.findByIdxAndUserIdx(boardIdx, user.getIdx());

        if (!result.isPresent()) {
            throw new BoardException(ErrorCode.BOARD_NOT_EXISTS, String.format("Board Idx [ %s ] is not exists.", boardIdx));
        }

        Board board = result.get();

        List<String> tags = new ArrayList<>();

        if (!board.getBoardTagList().isEmpty()) {
            for (BoardTag boardTag : board.getBoardTagList()) {
                tags.add(boardTag.getTag().getTagName());
            }
        }

        GetReadBoardRes getReadBoardRes = GetReadBoardRes.builder()
                .idx(board.getIdx())
                .boardCategoryName(board.getBoardCategory().getCategoryName())
                .boardCategoryIdx(board.getBoardCategory().getIdx())
                .boardTitle(board.getBoardTitle())
                .boardContent(board.getBoardContent())
                .tagList(tags)
                .build();

        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("본인 작성 게시글 상세 조회 성공")
                .result(getReadBoardRes)
                .build();

        return baseRes;
    }

    // Hot 인기 게시글 조회
    @Transactional(readOnly = true)
    public BaseRes listHotBoard(Pageable pageable, Integer boardCategoryIdx, Integer sortIdx) {
        Page<Board> boardList = boardRepository.findBoardListByCategory(pageable, boardCategoryIdx, sortIdx);
        List<GetListHotBoardRes> getListResListHotBoard = new ArrayList<>();

        for (Board board : boardList) {

            List<BoardTag> boardTagList = board.getBoardTagList();
            List<String> tagNames = new ArrayList<>();

            for (BoardTag boardTag : boardTagList) {
                String tagName = boardTag.getTag().getTagName();
                tagNames.add(tagName);
            }

            String textContent = extractText(board.getBoardContent());

            GetListHotBoardRes getListHotBoardRes = GetListHotBoardRes.builder()
                    .idx(board.getIdx())
                    .userIdx(board.getUser().getIdx())
                    .nickName(board.getUser().getNickName())
                    .profileImage(board.getUser().getProfileImage())
                    .title(board.getBoardTitle())
                    .content(textContent)
                    .tagNameList(tagNames)
                    .viewCnt(board.getViewCnt())
                    .upCnt(board.getUpCnt())
                    .scrapCnt(board.getScrapCnt())
                    .commentCnt(board.getCommentCnt())
                    .createdAt(board.getCreatedAt())
                    .updatedAt(board.getUpdatedAt())
                    .type("board")
                    .build();

            Document doc = Jsoup.parse(board.getBoardContent());
            Elements images = doc.select("img");

            if (!images.isEmpty()) {
                getListHotBoardRes.setImage(images.get(0).attr("src"));
            }

            getListResListHotBoard.add(getListHotBoardRes);
        }

        Long totalCnt = boardList.getTotalElements();
        Integer totalPages = boardList.getTotalPages();

        GetListHotBoardResResult result = GetListHotBoardResResult.builder()
                .totalCnt(totalCnt)
                .totalPages(totalPages)
                .list(getListResListHotBoard)
                .build();

        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("인기 게시글 카테고리별 목록 조회 요청 성공")
                .result(result)
                .build();

        return baseRes;
    }

    // 핫 인기 게시글 검색어 조회
    @Transactional(readOnly = true)
    public BaseRes searchHotBoard(Integer boardCategoryIdx, String query, Integer sortIdx, Pageable pageable) {
        Page<Board> boardList = boardRepository.searchBoardListByQueryAndCategory(pageable, boardCategoryIdx, query, sortIdx);

        List<GetListHotBoardRes> getListResListHotBoard = new ArrayList<>();

        for (Board board : boardList) {

            List<BoardTag> boardTagList = board.getBoardTagList();
            List<String> tagNames = new ArrayList<>();

            for (BoardTag boardTag : boardTagList) {
                String tagName = boardTag.getTag().getTagName();
                tagNames.add(tagName);
            }

            String textContent = extractText(board.getBoardContent());

            GetListHotBoardRes getListHotBoardRes = GetListHotBoardRes.builder()
                    .idx(board.getIdx())
                    .userIdx(board.getUser().getIdx())
                    .nickName(board.getUser().getNickName())
                    .profileImage(board.getUser().getProfileImage())
                    .title(board.getBoardTitle())
                    .content(textContent)
                    .tagNameList(tagNames)
                    .viewCnt(board.getViewCnt())
                    .upCnt(board.getUpCnt())
                    .scrapCnt(board.getScrapCnt())
                    .commentCnt(board.getCommentCnt())
                    .createdAt(board.getCreatedAt())
                    .updatedAt(board.getUpdatedAt())
                    .type("board")
                    .build();

            Document doc = Jsoup.parse(board.getBoardContent());
            Elements images = doc.select("img");

            if (!images.isEmpty()) {
                getListHotBoardRes.setImage(images.get(0).attr("src"));
            }

            getListResListHotBoard.add(getListHotBoardRes);

        }

        Long totalCnt = boardList.getTotalElements();
        Integer totalPages = boardList.getTotalPages();

        GetListHotBoardResResult result = GetListHotBoardResResult.builder()
                .totalCnt(totalCnt)
                .totalPages(totalPages)
                .list(getListResListHotBoard)
                .build();

        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("인기 게시글 검색어별 목록 조회 요청 성공")
                .result(result)
                .build();

        return baseRes;
    }
}
