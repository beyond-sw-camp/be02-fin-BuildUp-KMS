package com.example.bootshelf.boardsvc.board.controller;

import com.example.bootshelf.boardsvc.board.model.request.PostCreateBoardReq;
import com.example.bootshelf.boardsvc.board.service.BoardService;
import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.config.SecurityConfig;
import com.example.bootshelf.config.handler.OAuth2AuthenticationSuccessHandler;
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
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(BoardController.class)
@ContextConfiguration(classes = {SecurityConfig.class, BoardController.class})
@AutoConfigureMockMvc
class BoardControllerTest {
    @Autowired
    MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private BoardService boardService;

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
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @DisplayName("게시판 게시글 생성 테스트")
    @WithMockUser
    @Test
    void boardController_create_success() throws Exception {
        PostCreateBoardReq mockRequest = new PostCreateBoardReq();
        mockRequest.setBoardTitle("Test Title");
        mockRequest.setBoardContent("Test Content");
        mockRequest.setBoardCategoryIdx(1);
        mockRequest.setTagList(Arrays.asList("tag1", "tag2"));

        // Mock MultipartFile[]
        MultipartFile[] mockFiles = new MultipartFile[1];
        mockFiles[0] = new MockMultipartFile("file", "test.jpg", "image/jpeg", "test image content".getBytes());

        // Mock Response
        BaseRes mockResponse = BaseRes.builder()
                .isSuccess(true)
                .message("게시글 등록 성공")
                .result(new Object()) // Assume your actual result object here
                .build();

        when(boardService.createBoard(any(User.class), any(PostCreateBoardReq.class), any(MultipartFile[].class)))
                .thenReturn(mockResponse);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestJson = objectMapper.writeValueAsString(mockRequest);

        MockMultipartFile jsonFile = new MockMultipartFile("board", "", "application/json", requestJson.getBytes());
        MockMultipartFile imageFile = new MockMultipartFile("boardImage", "test.jpg", "image/jpeg", "test image content".getBytes());

//        // Perform Post Request
//        mvc.perform(MockMvcRequestBuilders.multipart("/create")
//                        .file(jsonFile)
//                        .file(imageFile)
//                        .contentType(MediaType.MULTIPART_FORM_DATA)
//                        .with(csrf()))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.isSuccess").value(true))
//                .andExpect(jsonPath("$.message").value("게시글 등록 성공"));

        // Verify that the boardService.createBoard was called
        verify(boardService, times(1)).createBoard(any(User.class), any(PostCreateBoardReq.class), any(MultipartFile[].class));

    }
}