package com.example.bootshelf.reviewsvc.review.controller;

import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.common.error.ErrorCode;
import com.example.bootshelf.common.error.entityexception.ReviewException;
import com.example.bootshelf.config.SecurityConfig;
import com.example.bootshelf.config.handler.OAuth2AuthenticationSuccessHandler;
import com.example.bootshelf.config.utils.JwtUtils;
import com.example.bootshelf.reviewsvc.review.model.request.PatchUpdateReviewReq;
import com.example.bootshelf.reviewsvc.review.model.request.PostCreateReviewReq;
import com.example.bootshelf.reviewsvc.review.model.response.GetMyListReviewRes;
import com.example.bootshelf.reviewsvc.review.model.response.PostCreateReviewRes;
import com.example.bootshelf.reviewsvc.review.repository.ReviewRepository;
import com.example.bootshelf.reviewsvc.review.service.ReviewImageService;
import com.example.bootshelf.reviewsvc.review.service.ReviewService;
import com.example.bootshelf.user.controller.mock.WithCustomMockUser;
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
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ReviewController.class)
@ContextConfiguration(classes = {SecurityConfig.class, ReviewController.class})
@AutoConfigureMockMvc
@DisplayName("ReviewController 테스트")
public class ReviewControllerTest {

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
    private JwtUtils jwtUtils;

    @MockBean
    private ReviewService reviewService;

    @MockBean
    private OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private ReviewRepository reviewRepository;

    @MockBean
    private UserOAuth2Service userOAuth2Service;

    @MockBean
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @MockBean
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @MockBean
    private RefreshTokenService refreshTokenService;

    @MockBean
    private ReviewImageService reviewImageService;

    @MockBean
    private ClientRegistrationRepository clientRegistrationRepository;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @DisplayName("후기글 생성 성공")
    @WithCustomMockUser
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

        given(reviewService.createReview(any(User.class), any(PostCreateReviewReq.class)))
                .willReturn(baseRes);

        mvc.perform(multipart("/main/review/create")
                        .content(mapper.writeValueAsBytes(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.isSuccess").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("후기글 등록 성공"))
                .andDo(print());
    }

    @DisplayName("후기글 생성 실패")
    @WithCustomMockUser
    @Test
    void reviewController_create_failed() throws Exception {

        // given
        ObjectMapper mapper = new ObjectMapper();

        PostCreateReviewReq request = PostCreateReviewReq.builder()
                .reviewCategoryIdx(1)
                .reviewTitle("한화 후기")
                .reviewContent("부트캠프")
                .courseName("백엔드")
                .courseEvaluation(5)
                .build();

        String content = mapper.writeValueAsString(request);

        given(reviewService.createReview(any(User.class), any(PostCreateReviewReq.class)))
                .willThrow(new ReviewException(ErrorCode.DUPLICATE_REVIEW_TITLE, "Review Title [ %s ] is duplicated."));

        // when & then
        mvc.perform(multipart("/main/review/create")
                        .content(mapper.writeValueAsBytes(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("ACCOUNT-001"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Request processing failed; nested exception is com.example.bootshelf.common.error.entityexception.ReviewException: Review Title [ %s ] is duplicated."))
                .andDo(print());
    }


    @DisplayName("인증회원 작성 후기글 목록 조회 성공")
    @WithCustomMockUser
    @Test
    void reviewController_myList_success() throws Exception {

        GetMyListReviewRes mockResponse = GetMyListReviewRes.builder()
                .idx(1)
                .reviewCategoryIdx(1)
                .title("후기")
                .content("좋아요")
                .courseName("백엔드")
                .courseEvaluation(5)
                .viewCnt(5)
                .upCnt(5)
                .scrapCnt(5)
                .commentCnt(5)
                .type("review")
                .boardType("write")
                .updatedAt(LocalDateTime.now())
                .build();

        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("인증회원 본인 후기글 목록 조회 요청 성공")
                .result(mockResponse)
                .build();
        // given
        ObjectMapper mapper = new ObjectMapper();

        given(reviewService.myList(any(User.class), any(Pageable.class), any(Integer.class), any(Integer.class)))
                .willReturn(baseRes);

        // when & then
        mvc.perform(get("/main/review/mylist/1/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.isSuccess").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("인증회원 본인 후기글 목록 조회 요청 성공"))
                .andDo(print());
    }

    @DisplayName("후기글 수정 성공")
    @WithCustomMockUser
    @Test
    void reviewController_update_success() throws Exception {

        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("후기글 수정 성공")
                .result("요청 성공")
                .build();
        // given
        ObjectMapper mapper = new ObjectMapper();

        PatchUpdateReviewReq request = PatchUpdateReviewReq.builder()
                .reviewIdx(2)
                .reviewTitle("부트캠프 후기")
                .reviewContent("좋아욥")
                .courseEvaluation(5)
                .build();

        given(reviewService.updateReview(any(User.class), any(PatchUpdateReviewReq.class)))
                .willReturn(baseRes);

        // when & then
        mvc.perform(multipart(HttpMethod.PATCH, "/main/review/update")
                        .content(mapper.writeValueAsBytes(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf())
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.isSuccess").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("후기글 수정 성공"))
                .andDo(print());
    }

    @DisplayName("후기글 삭제 성공")
    @WithCustomMockUser
    @Test
    void reviewController_cancel_success() throws Exception {

        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("후기글 삭제 성공")
                .result("요청 성공")
                .build();

        // given
        given(reviewService.deleteReview(any(User.class), any(Integer.class)))
                .willReturn(baseRes);

        // when & then

        mvc.perform(delete("/main/review/delete/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.isSuccess").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("후기글 삭제 성공"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value("요청 성공"))
                .andDo(print());
    }

    @DisplayName("후기글 삭제 실패")
    @WithCustomMockUser
    @Test
    void reviewController_deleteReview_failed() throws Exception {

        // given
        given(reviewService.deleteReview(any(User.class), any(Integer.class)))
                .willThrow(new ReviewException(ErrorCode.REVIEW_NOT_EXISTS, "Review Idx [ %s ] is not exists."));

        // when & then

        mvc.perform(delete("/main/review/delete/1")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isUnauthorized())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("ACCOUNT-001"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Request processing failed; nested exception is com.example.bootshelf.common.error.entityexception.ReviewException: Review Idx [ %s ] is not exists."))
                .andDo(print());
    }
}