package com.example.bootshelf.admin.controller;


import com.example.bootshelf.admin.model.request.PostSignUpAdminReq;
import com.example.bootshelf.admin.model.response.PostSignUpAdminRes;
import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.common.error.entityexception.UserException;
import com.example.bootshelf.config.SecurityConfig;
import com.example.bootshelf.config.handler.OAuth2AuthenticationSuccessHandler;
import com.example.bootshelf.config.utils.JwtUtils;
import com.example.bootshelf.user.exception.security.CustomAccessDeniedHandler;
import com.example.bootshelf.user.exception.security.CustomAuthenticationEntryPoint;
import com.example.bootshelf.user.model.request.PostLoginUserReq;
import com.example.bootshelf.user.model.response.PostLoginUserRes;
import com.example.bootshelf.user.repository.UserRepository;
import com.example.bootshelf.user.service.UserOAuth2Service;
import com.example.bootshelf.user.service.UserService;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.example.bootshelf.common.error.ErrorCode.DIFFERENT_USER_PASSWORD;
import static com.example.bootshelf.common.error.ErrorCode.DUPLICATE_SIGNUP_EMAIL;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(AdminController.class)
@ContextConfiguration(classes = {SecurityConfig.class, AdminController.class})
@AutoConfigureMockMvc
@DisplayName("AdminController 테스트")
public class AdminControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private JwtUtils jwtUtils;

    @MockBean
    private UserService userService;

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

    @DisplayName("관리자 회원가입 성공")
    @WithMockUser
    @Test
    void adminController_signUp_success() throws Exception {

        PostSignUpAdminRes mockResponse = PostSignUpAdminRes.builder()
                .email("test01@gmail.com")
                .name("test01")
                .build();

        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("관리자 회원가입에 성공하였습니다.")
                .result(mockResponse)
                .build();

        // given
        ObjectMapper mapper = new ObjectMapper();

        PostSignUpAdminReq request = PostSignUpAdminReq.builder()
                .email("test01@gmail.com")
                .password("Qwer1234!")
                .name("test01")
                .build();

//        String content = mapper.writeValueAsString(request);

        given(userService.adminSignup(any(PostSignUpAdminReq.class)))
                .willReturn(baseRes);

        // when & then
        mvc.perform(post("/admin/signup")
                        .content(mapper.writeValueAsBytes(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.isSuccess").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("관리자 회원가입에 성공하였습니다."))
                .andDo(print());
    }

    @DisplayName("관리자 회원가입 실패 - 중복된 이메일")
    @Test
    void adminController_signup_fail_duplicatedEmail() throws Exception {

        // given
        ObjectMapper mapper = new ObjectMapper();

        PostSignUpAdminReq request = PostSignUpAdminReq.builder()
                .email("test02@gmail.com")
                .password("Qwer1234!")
                .name("test01")
                .build();

        given(userService.adminSignup(any(PostSignUpAdminReq.class)))
                .willThrow(new UserException(DUPLICATE_SIGNUP_EMAIL, "SignUp Email [ test02@gmail.com ] is duplicated."));


        // when & then
        mvc.perform(post("/admin/signup")
                        .content(mapper.writeValueAsBytes(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("ACCOUNT-001"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("SignUp Email [ test02@gmail.com ] is duplicated."))
                .andDo(print());
    }

    @DisplayName("관리자 로그인 성공")
    @WithMockUser
    @Test
    void adminController_login_success() throws Exception {
        PostLoginUserRes postLoginUserRes = PostLoginUserRes.builder()
                .token("eyJhbGciOiJIUzI1NiJ9.eyJpZHgiOjcsImVtYWlsIjoidGVzdDAyQGdtYWlsLmNvbSIsIm5hbWUiOiLthYzsiqTthLAwMiIsIm5pY2tOYW1lIjoi7YWM7Iqk7YSwMDIiLCJST0xFIjoiUk9MRV9BVVRIVVNFUiIsImlhdCI6MTcxMDc3NDYzOCwiZXhwIjoxNzEwNzc3NjM4fQ.gUPQMylRgrzJE_21EUCaNZI9N9DyvN8sCVvpFtXXRZI")
                .build();

        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("로그인에 성공하였습니다.")
                .result(postLoginUserRes)
                .build();

        // given
        ObjectMapper mapper = new ObjectMapper();

        PostLoginUserReq postLoginUserReq = PostLoginUserReq.builder()
                .email("test01@gmail.com")
                .password("Qwer1234!")
                .build();

        given(userService.login(any(PostLoginUserReq.class)))
                .willReturn(baseRes);

        // when & then
        mvc.perform(post("/admin/login")
                        .content(mapper.writeValueAsBytes(postLoginUserReq))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.isSuccess").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("로그인에 성공하였습니다."))
                .andDo(print());
    }

    @DisplayName("로그인 실패 - 비밀번호 불일치")
    @Test
    void adminController_login_fail_wrongPassword() throws Exception {

        // given
        ObjectMapper mapper = new ObjectMapper();

        PostLoginUserReq postLoginUserReq = PostLoginUserReq.builder()
                .email("test01@gmail.com")
                .password("Qwer1234@")
                .build();

        given(userService.login(any(PostLoginUserReq.class)))
                .willThrow(new UserException(DIFFERENT_USER_PASSWORD, "User Password [ Qwer1234@ ] is different."));


        // when & then
        mvc.perform(post("/admin/login")
                        .content(mapper.writeValueAsBytes(postLoginUserReq))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("ACCOUNT-001"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("User Password [ Qwer1234@ ] is different."))
                .andDo(print());
    }
}
