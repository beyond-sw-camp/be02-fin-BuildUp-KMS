package com.example.bootshelf.reviewscrap.controller;

import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.config.SecurityConfig;
import com.example.bootshelf.config.handler.OAuth2AuthenticationSuccessHandler;
import com.example.bootshelf.reviewsvc.reviewscrap.controller.ReviewScrapController;
import com.example.bootshelf.reviewsvc.reviewscrap.model.request.PostCreateReviewScrapReq;
import com.example.bootshelf.reviewsvc.reviewscrap.model.response.PostCreateReviewScrapRes;
import com.example.bootshelf.reviewsvc.reviewscrap.service.ReviewScrapService;
import com.example.bootshelf.user.exception.security.CustomAccessDeniedHandler;
import com.example.bootshelf.user.exception.security.CustomAuthenticationEntryPoint;
import com.example.bootshelf.user.model.entity.User;
import com.example.bootshelf.user.repository.UserRepository;
import com.example.bootshelf.user.service.RefreshTokenService;
import com.example.bootshelf.user.service.UserOAuth2Service;
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
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ReviewScrapController.class)
@ContextConfiguration(classes = {SecurityConfig.class, ReviewScrapController.class})
@AutoConfigureMockMvc
class ReviewScrapControllerTest {


    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("jwt.secret-key", () -> "secretKey");
        registry.add("jwt.token.expired-time-ms", () -> "3000000");
        registry.add("jwt.token.refresh-expiration-ms", () -> "84000000");
    }

    @Autowired
    MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private ReviewScrapService reviewScrapService;

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

    @MockBean
    private RefreshTokenService refreshTokenService;

    @MockBean
    private ClientRegistrationRepository clientRegistrationRepository;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @DisplayName("리뷰 스크랩 테스트")
    @WithMockUser
    @Test
    void reviewScrapController_create_success() throws Exception {
        PostCreateReviewScrapRes mockResponse = PostCreateReviewScrapRes.builder()
                .reviewScrapIdx(1)
                .userIdx(1)
                .reviewIdx(1)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("리뷰 스크랩 등록 성공")
                .result(mockResponse)
                .build();

        given(reviewScrapService.createReviewScrap(any(User.class), any(PostCreateReviewScrapReq.class)))
                .willReturn(baseRes);

        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(new PostCreateReviewScrapReq(1));

        mvc.perform(post("/main/reviewscrap/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andDo(print())
                .andExpect(status().isOk());
    }
}