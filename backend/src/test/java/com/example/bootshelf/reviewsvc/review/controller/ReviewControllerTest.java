package com.example.bootshelf.reviewsvc.review.controller;

import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.config.SecurityConfig;
import com.example.bootshelf.config.handler.OAuth2AuthenticationSuccessHandler;
import com.example.bootshelf.config.utils.JwtUtils;
import com.example.bootshelf.reviewsvc.review.model.request.PostCreateReviewReq;
import com.example.bootshelf.reviewsvc.review.model.response.PostCreateReviewRes;
import com.example.bootshelf.reviewsvc.review.repository.ReviewRepository;
import com.example.bootshelf.reviewsvc.review.service.ReviewService;
import com.example.bootshelf.reviewsvc.reviewimage.service.ReviewImageService;
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
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ReviewController.class)
@ContextConfiguration(classes = {SecurityConfig.class, ReviewController.class})
@AutoConfigureMockMvc
@DisplayName("ReviewController 테스트")
public class ReviewControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private JwtUtils jwtUtils;


    @MockBean
    private ReviewService reviewService;

    @MockBean
    private OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private ReviewImageService reviewImageService;
    @MockBean
    private ReviewRepository reviewRepository;

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

    // 401 에러 뜸
    @DisplayName("후기글 생성 테스트")
    @WithMockUser(username = "test01@gmail.com", roles = {"ROLE_USER"})
    @Test
    void reviewScrapController_create_success() throws Exception {
        PostCreateReviewRes mockResponse = PostCreateReviewRes.builder()
                .reviewIdx(1)
                .reviewCategoryIdx(1)
                .reviewTitle("한화 후기")
                .courseName("백엔드")
                .reviewContent("부트캠프")
                .courseEvaluation(5)
                .build();

        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("후기글 등록 성공")
                .result(mockResponse)
                .build();

        ObjectMapper mapper = new ObjectMapper();

        PostCreateReviewReq request = PostCreateReviewReq.builder()
                .reviewCategoryIdx(1)
                .reviewTitle("한화 후기")
                .reviewContent("부트캠프")
                .courseName("백엔드")
                .courseEvaluation(5)
                .build();

        String content = mapper.writeValueAsString(request);

        final String fileName = "testImage1";
        final String contentType = "png";

        MockMultipartFile multipartFile = setMockMultipartFile(fileName, contentType);

        given(reviewService.createReview(any(User.class), any(PostCreateReviewReq.class), any()))
                .willReturn(baseRes);

        mvc.perform(multipart("/review/create")
                        .file(new MockMultipartFile("review", "", "application/json", content.getBytes(StandardCharsets.UTF_8)))
//                        .file(multipartFile)
                        .contentType(MULTIPART_FORM_DATA)
                        .accept(APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                        .andExpect(status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.isSuccess").value(true))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("후기글 등록 성공"))
                        .andDo(print());
    }

    private MockMultipartFile setMockMultipartFile(String fileName, String contentType) {
        return new MockMultipartFile("reviewImage", fileName + "." + contentType, contentType, "<<data>>".getBytes());
    }

}
