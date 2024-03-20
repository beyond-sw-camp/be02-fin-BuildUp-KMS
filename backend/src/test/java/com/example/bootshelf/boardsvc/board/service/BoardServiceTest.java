package com.example.bootshelf.boardsvc.board.service;

import com.example.bootshelf.boardsvc.board.model.entity.Board;
import com.example.bootshelf.boardsvc.board.model.request.PostCreateBoardReq;
import com.example.bootshelf.boardsvc.board.model.response.GetListBoardRes;
import com.example.bootshelf.boardsvc.board.repository.BoardRepository;
import com.example.bootshelf.boardsvc.boardcategory.model.entity.BoardCategory;
import com.example.bootshelf.boardsvc.boardimage.service.BoardImageService;
import com.example.bootshelf.boardsvc.boardtag.service.BoardTagService;
import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.reviewsvc.review.repository.ReviewRepository;
import com.example.bootshelf.user.model.entity.User;
import com.example.bootshelf.user.repository.UserRepository;
import com.example.bootshelf.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static net.bytebuddy.matcher.ElementMatchers.is;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class BoardServiceTest {

    @Mock
    private BoardImageService boardImageService;

    @Mock
    private BoardTagService boardTagService;

    @Mock
    private BoardRepository boardRepository;

    @InjectMocks
    private BoardService boardService;

    @WithMockUser
    @Test
    @DisplayName("게시글 생성 테스트")
    public void createBoardTest() throws Exception {
        MultipartFile[] mockFiles = new MultipartFile[] {
                new MockMultipartFile("file", "test1.jpg", "image/jpeg", "test1".getBytes()),
                new MockMultipartFile("file", "test2.jpg", "image/jpeg", "test2".getBytes())
        };
        // given
        BoardService boardServiceMock = Mockito.mock(BoardService.class);
        PostCreateBoardReq mockRequest = new PostCreateBoardReq();
        mockRequest.setBoardTitle("Test Title");
        mockRequest.setBoardContent("Test Content");
        mockRequest.setBoardCategoryIdx(1);
        mockRequest.setTagList(Arrays.asList("tag1", "tag2"));

        mockFiles[0] = new MockMultipartFile("file", "test.jpg", "image/jpeg", "test image content".getBytes());

        BaseRes mockResponse = BaseRes.builder()
                .isSuccess(true)
                .message("게시글 등록 성공")
                .result(new Object())
                .build();
        Mockito.when(boardServiceMock.createBoard(Mockito.any(User.class), Mockito.any(PostCreateBoardReq.class), Mockito.any(MultipartFile[].class)))
                .thenReturn(mockResponse);

        doNothing().when(boardImageService).createBoardImage(anyInt(), any(MultipartFile[].class));
        doNothing().when(boardTagService).saveBoardTag(anyList(), anyInt());

        when(boardRepository.save(any())).thenReturn(new Board()); // 저장에 대한 가짜 응답 설정

        // when
        BaseRes response = boardServiceMock.createBoard(new User(), mockRequest, mockFiles);

        // then
//        verify(boardImageService, times(1)).createBoardImage(anyInt(), eq(mockFiles));
//        verify(boardTagService, times(1)).saveBoardTag(eq(mockRequest.getTagList()), anyInt());
        assertNotNull(response);
        assertTrue(response.getIsSuccess());
        assertEquals("게시글 등록 성공", response.getMessage());
        verify(boardRepository, times(1)).save(any());
    }
}
