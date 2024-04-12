package com.example.bootshelf.boardsvc.boardup.controller;

import com.example.bootshelf.boardsvc.board.repository.BoardRepository;

import com.example.bootshelf.boardsvc.boardup.model.request.PostCreateBoardUpReq;
import com.example.bootshelf.boardsvc.boardup.model.response.PostCreateBoardUpRes;
import com.example.bootshelf.boardsvc.boardup.repository.BoardUpRepository;
import com.example.bootshelf.boardsvc.boardup.service.BoardUpService;
import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.common.error.ErrorCode;
import com.example.bootshelf.common.error.entityexception.BoardException;
import com.example.bootshelf.config.handler.OAuth2AuthenticationSuccessHandler;
import com.example.bootshelf.config.utils.JwtUtils;
import com.example.bootshelf.user.controller.mock.WithCustomMockUser;
import com.example.bootshelf.user.exception.security.CustomAccessDeniedHandler;
import com.example.bootshelf.user.exception.security.CustomAuthenticationEntryPoint;
import com.example.bootshelf.user.model.entity.User;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(BoardUpController.class)
@AutoConfigureMockMvc
@DisplayName("BoardUpController 테스트")
class BoardUpControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private BoardUpService boardUpService;

    @MockBean
    private BoardUpRepository boardUpRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private BoardRepository boardRepository;

    @MockBean
    private JwtUtils jwtUtils;

    @MockBean
    private OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

    @MockBean
    private UserOAuth2Service userOAuth2Service;

    @MockBean
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @MockBean
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;


    @BeforeEach
    public void setup() {
        // 테스트 전 실행
        MockMvc mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @DisplayName("게시글 추천 성공")
    @WithCustomMockUser
    void boardUpController_createBoardUp_success() throws Exception {
        // Given
        PostCreateBoardUpRes postCreateBoardUpRes = PostCreateBoardUpRes.builder()
                .boardUpIdx(1)
                .userIdx(1)
                .boardIdx(1)
                .build();

        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("게시판 게시글 추천 등록 성공")
                .result(postCreateBoardUpRes)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();

        PostCreateBoardUpReq postCreateBoardUpReq = PostCreateBoardUpReq.builder()
                .boardIdx(1)
                .build();

        given(boardUpService.createBoardUp(any(User.class), any(PostCreateBoardUpReq.class))).willReturn(baseRes);

        // When
        mockMvc.perform(post("/boardup/create")
                        .content(objectMapper.writeValueAsBytes(postCreateBoardUpReq))
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isSuccess").value(true))
                .andExpect(jsonPath("$.message").value("게시판 게시글 추천 등록 성공"))
                .andDo(print());
    }

    @Test
    @DisplayName("게시글 추천 실패 - boardIdx 미존재")
    @WithCustomMockUser
    void boardUpController_createBoardUp_fail() throws Exception {
        //Given
        PostCreateBoardUpRes postCreateBoardUpRes = PostCreateBoardUpRes.builder()
                .boardUpIdx(1)
                .userIdx(1)
                .boardIdx(1)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();

        PostCreateBoardUpReq postCreateBoardUpReq = PostCreateBoardUpReq.builder()
                .boardIdx(999)
                .build();

        given(boardUpService.createBoardUp(any(User.class), any(PostCreateBoardUpReq.class)))
                .willThrow(new BoardException(ErrorCode.BOARD_NOT_EXISTS, String.format("Board [ %d ] is not exists.", postCreateBoardUpReq.getBoardIdx())));

        // When
        mockMvc.perform(post("/boardup/create")
                        .content(objectMapper.writeValueAsBytes(postCreateBoardUpReq))
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("BOARD-001"))
                .andDo(print());
    }



}