package com.example.bootshelf.boardsvc.boardcomment.controller;

import com.example.bootshelf.boardsvc.boardcomment.model.request.PostCreateBoardCommentReq;
import com.example.bootshelf.boardsvc.boardcomment.model.response.GetListBoardCommentRes;
import com.example.bootshelf.boardsvc.boardcomment.model.response.PostCreateBoardCommentRes;
import com.example.bootshelf.boardsvc.boardcomment.service.BoardCommentService;
import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.common.error.ErrorCode;
import com.example.bootshelf.common.error.entityexception.BoardCommentException;
import com.example.bootshelf.user.controller.mock.WithCustomMockUser;
import com.example.bootshelf.user.model.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oauth2Login;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
@WebMvcTest(BoardCommentController.class)
@AutoConfigureMockMvc
@DisplayName("BoardCommentController 테스트")
class BoardCommentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private BoardCommentService boardCommentService;

    @BeforeEach
    public void setup() {
        // 테스트 전 실행
        MockMvc mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @DisplayName("게시글 댓글 등록 성공")
    @WithCustomMockUser
    void boardCommentController_create_success() throws Exception {
        // Given
        PostCreateBoardCommentRes postCreateBoardCommentRes = PostCreateBoardCommentRes.builder()
                .idx(1)
                .boardCommentContent("게시글 댓글 작성 성공 테스트입니다.")
                .build();

        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("댓글 등록 성공")
                .result(postCreateBoardCommentRes)
                .build();

        PostCreateBoardCommentReq postCreateBoardCommentReq = PostCreateBoardCommentReq.builder()
                .boardCommentContent("게시글 댓글 작성 성공 테스트입니다.")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();

        given(boardCommentService.createBoardComment(any(User.class), any(Integer.class), any(PostCreateBoardCommentReq.class)))
                .willReturn(baseRes);

        // When
        mockMvc.perform(post("/board/1/comment/create")
                        .content(objectMapper.writeValueAsBytes(postCreateBoardCommentReq))
                        // 객체를 json 파일로 변환해준다.
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isSuccess").value(true))
                .andExpect(jsonPath("$.message").value("댓글 등록 성공"))
                .andDo(print());
    }

    @Test
    @DisplayName("게시글 댓글 등록 실패 - 댓글 내용이 작성되지 않았을 때")
    @WithCustomMockUser
    void boardCommentController_create_fail_commentContentEmpty() throws Exception {
        // Given
        ObjectMapper objectMapper = new ObjectMapper();

        PostCreateBoardCommentReq postCreateBoardCommentReq = PostCreateBoardCommentReq.builder()
                .boardCommentContent(null)
                .build();

        given(boardCommentService.createBoardComment(any(User.class), any(Integer.class), any(PostCreateBoardCommentReq.class)))
                .willThrow(new BoardCommentException(ErrorCode.INVALID_INPUT_VALUE, "Board Comment Content is empty."));
        // When
        mockMvc.perform(post("/board/1/comment/create")
                        .content(objectMapper.writeValueAsBytes(postCreateBoardCommentReq))
                        // 객체를 json 파일로 변환해준다.
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("COMMON-001"))
                .andDo(print());
    }

    @DisplayName("게시글 댓글 조회 성공 테스트")
    @Test
    @WithAnonymousUser
    void boardCommentController_list_success() throws Exception {
        //given
        GetListBoardCommentRes getListBoardCommentRes = GetListBoardCommentRes.builder()
                .idx(1)
                .boardIdx(1)
                .userIdx(1)
                .nickName("hj")
                .profileImage("url")
                .boardCommnetContent("게시글 댓글 조회 테스트 입니다.")
                .status(true)
                .upCnt(0)
                .createAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .build();

        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("요청 성공")
                .result(getListBoardCommentRes)
                .build();

        given(boardCommentService.listBoardComment(any(Integer.class))).willReturn(baseRes);

        mockMvc.perform(get("/board/1/comment")
//                        .with(anonymous())
                        .with(oauth2Login())
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isSuccess").value(true))
                .andExpect(jsonPath("$.message").value("요청 성공"))
                .andDo(print());

    }
}
