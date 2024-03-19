package com.example.bootshelf.boardsvc.boardcomment.controller;

import com.example.bootshelf.boardsvc.board.model.request.PostCreateBoardReq;
import com.example.bootshelf.boardsvc.boardcomment.model.request.PostCreateBoardCommentReq;
import com.example.bootshelf.boardsvc.boardcomment.model.response.PostCreateBoardCommentRes;
import com.example.bootshelf.boardsvc.boardcomment.service.BoardCommentService;
import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.config.SecurityConfig;
import com.example.bootshelf.config.handler.OAuth2AuthenticationSuccessHandler;
import com.example.bootshelf.user.exception.security.CustomAccessDeniedHandler;
import com.example.bootshelf.user.exception.security.CustomAuthenticationEntryPoint;
import com.example.bootshelf.user.model.entity.User;
import com.example.bootshelf.user.model.request.PostSignUpUserReq;
import com.example.bootshelf.user.model.response.PostSignUpUserRes;
import com.example.bootshelf.user.repository.UserRepository;
import com.example.bootshelf.user.service.UserOAuth2Service;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(BoardCommentController.class)
@ContextConfiguration(classes = {SecurityConfig.class, BoardCommentController.class})
@AutoConfigureMockMvc
class BoardCommentControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private BoardCommentService boardCommentService;

    @MockBean
    private OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserOAuth2Service userOAuth2Service;

    @MockBean
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @MockBean
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @BeforeEach
    public void setup() {
        // 테스트 전 실행
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @DisplayName("게시글 댓글 등록 성공")
    @WithMockUser
    @Test
    void boardCommentController_create_success() throws Exception {
        PostCreateBoardCommentRes mockResponse = PostCreateBoardCommentRes.builder()
                .idx(1)
                .boardCommentContent("게시판 테스트 댓글입니다.")
                .build();

        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("댓글 등록 성공")
                .result(mockResponse)
                .build();

        Integer boardIdx = 1;

        PostCreateBoardCommentReq mockRequest = new PostCreateBoardCommentReq();
        mockRequest.setBoardCommentContent("게시판 테스트 댓글입니다.");

        given(boardCommentService.createBoardComment(any(User.class),boardIdx, any(PostCreateBoardCommentReq.class))).willReturn(baseRes);

        // 요청을 보내고 응답을 받음
        mvc.perform(post("/board/{boardIdx}/comment/create", boardIdx)
                        .content(objectMapper.writeValueAsString(mockRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

}