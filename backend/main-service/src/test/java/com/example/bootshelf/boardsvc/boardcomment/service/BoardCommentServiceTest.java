package com.example.bootshelf.boardsvc.boardcomment.service;

import com.example.bootshelf.boardsvc.board.repository.BoardRepository;
import com.example.bootshelf.boardsvc.boardcomment.repository.BoardCommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
@WebMvcTest(BoardCommentService.class)
@AutoConfigureMockMvc
@DisplayName("BoardCommentService 테스트")
class BoardCommentServiceTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private BoardCommentRepository boardCommentRepository;

    @MockBean
    private BoardRepository boardRepository;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private BoardCommentService boardCommentService;

    @BeforeEach
    public void setup() {

    }

    @Test
    void createBoardComment_Success() {
//        // Given
//        User user = new User(); // 사용자 객체 생성
//        user.setIdx(1);
//
//        Board board = new Board(); // 게시글 객체 생성
//        board.setIdx(1); // 게시글 아이디 설정
//
//        PostCreateBoardCommentReq postCreateBoardCommentReq = PostCreateBoardCommentReq.builder()
//                .boardCommentContent("게시글 댓글 작성 성공 테스트입니다.")
//                .build(); // 요청 객체 생성
//
//        given(boardRepository.findByIdx(any(Integer.class))).willReturn(Optional.of(board)); // findByIdx 메서드 호출시 가짜 board 객체 반환
//        given(boardCommentRepository.save(any(BoardComment.class))).willReturn(new BoardComment()); // save 메서드 호출시 가짜 boardComment 객체 반환
//
//        // When
//        BaseRes response = boardCommentService.createBoardComment(user, 1, postCreateBoardCommentReq);
//
//        // Then
//        assertNotNull(response); // 반환된 결과가 null이 아님을 확인
//        assertTrue(response.getIsSuccess()); // 성공 여부 확인
//        assertEquals("댓글 등록 성공", response.getMessage()); // 메시지 일치 확인
//        assertNotNull(response.getResult()); // 결과 객체가 null이 아님을 확인
//        assertEquals(postCreateBoardCommentReq.getBoardCommentContent(), ((PostCreateBoardCommentRes) response.getResult()).getBoardCommentContent()); // 결과 객체의 내용이 요청과 일치하는지 확인
//    }
    }

}