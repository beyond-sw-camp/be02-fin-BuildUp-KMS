package com.example.bootshelf.boardsvc.boardcomment.controller;

import com.example.bootshelf.boardsvc.boardcomment.model.request.PostCreateBoardCommentReq;
import com.example.bootshelf.boardsvc.boardcomment.model.response.PostCreateBoardCommentRes;
import com.example.bootshelf.boardsvc.boardcomment.service.BoardCommentService;
import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.config.SecurityConfig;
import com.example.bootshelf.config.handler.OAuth2AuthenticationSuccessHandler;
import com.example.bootshelf.config.utils.JwtUtils;
import com.example.bootshelf.user.exception.security.CustomAccessDeniedHandler;
import com.example.bootshelf.user.exception.security.CustomAuthenticationEntryPoint;
import com.example.bootshelf.user.model.entity.User;

import com.example.bootshelf.user.model.request.PostSignUpUserReq;
import com.example.bootshelf.user.repository.UserRepository;
import com.example.bootshelf.user.service.UserOAuth2Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.security.Key;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
@WebMvcTest(BoardCommentController.class)
@ContextConfiguration(classes = {SecurityConfig.class, BoardCommentController.class})
@AutoConfigureMockMvc
@DisplayName("ReviewCommentController 테스트")
class BoardCommentControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private BoardCommentService boardCommentService;

    @MockBean
    private JwtUtils jwtUtils;

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

    @DisplayName("게시글 댓글 등록 성공 테스트")
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

        PostCreateBoardCommentReq mockRequest = PostCreateBoardCommentReq.builder()
                .boardCommentContent("게시판 테스트 댓글입니다.")
                .build();
        given(jwtUtils.getAuthority(any(String.class), any(String.class))).willReturn("ROLE_USER", "abcdefghijklmnopqrstuvwxyz0123456789");
        given(this.boardCommentService.createBoardComment(any(User.class), eq(1), any(PostCreateBoardCommentReq.class))).willReturn(baseRes);


//        doReturn(baseRes).when(this.boardCommentService).createBoardComment(any(User.class),  eq(1), any(PostCreateBoardCommentReq.class));


        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZHgiOjcsImVtYWlsIjoidGVzdDAyQGdtYWlsLmNvbSIsIm5hbWUiOiLthYzsiqTthLAwMiIsIm5pY2tOYW1lIjoi7YWM7Iqk7YSwMDIiLCJST0xFIjoiUk9MRV9BVVRIVVNFUiIsImlhdCI6MTcxMDc3NDYzOCwiZXhwIjoxNzEwNzc3NjM4fQ.gUPQMylRgrzJE_21EUCaNZI9N9DyvN8sCVvpFtXXRZI";


        // 요청을 보내고 응답을 받음
        mvc.perform(post("/board/{boardIdx}/comment/create", 1)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .accept(MediaType.APPLICATION_JSON))
                                        .andExpect(status().isOk())
                                        .andExpect(MockMvcResultMatchers.jsonPath("$.isSuccess").value(true))
                                        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("댓글 등록 성공"))
                                        .andDo(print());
    }

}