package com.example.bootshelf.config;

import com.example.bootshelf.config.filter.JwtFilter;
import com.example.bootshelf.config.handler.OAuth2AuthenticationSuccessHandler;
import com.example.bootshelf.user.exception.security.CustomAccessDeniedHandler;
import com.example.bootshelf.user.exception.security.CustomAuthenticationEntryPoint;
import com.example.bootshelf.user.repository.UserRepository;
import com.example.bootshelf.user.service.UserOAuth2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    @Value("${jwt.secret-key}")
    private String secretKey;

    private final UserRepository userRepository;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    private final UserOAuth2Service userOAuth2Service;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;


    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        try {
            http.csrf().disable()
                    .authorizeHttpRequests()
                    .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()  // CORS 해결하기 위한 OPTION 메서드 허용
                    .antMatchers("/**").permitAll()
                    .antMatchers("/user/signup", "/user/check/course").permitAll()
                    .antMatchers("/user/verify", "/user/login").permitAll()
                    .antMatchers("/review/list/**", "/review/**/search/**", "/review/search/**", "/review/*", "/review/*/comment", "/reviewcomment/up/list").permitAll()

                    .antMatchers("/board/category/**", "/board/tag/**", "/board/*", "/board/search/**", "/board/*/search/**", "/board/searchv2/**", "/board/*/comment", "/boardcomment/up/list").permitAll()

                    .antMatchers("/user/read").hasAnyRole("USER", "AUTHUSER", "KAKAO", "ADMIN")
                    .antMatchers("/user/update", "/user/update/image", "/user/checkpw").hasAnyRole("USER", "AUTHUSER")
                    .antMatchers("/user/cancel").hasAnyRole("USER", "AUTHUSER", "KAKAO")

                    .antMatchers("/review/*/comment/create", "/review/*/update/*", "/review/*/delete/*", "/review/*/comment/create/*").hasAnyRole("USER", "AUTHUSER", "KAKAO")
                    .antMatchers("/reviewcomment/up/create", "/reviewcomment/up/check/**", "/reviewcomment/up/delete/**").hasAnyRole("USER", "AUTHUSER", "KAKAO")
                    .antMatchers("/reviewscrap/create", "/reviewscrap/list", "/reviewscrap/check/**", "/reviewscrap/delete/**").hasAnyRole("USER", "AUTHUSER", "KAKAO")
                    .antMatchers("/reviewup/create", "/reviewup/list", "/reviewup/check/**", "/reviewup/delete/**").hasAnyRole("USER", "AUTHUSER", "KAKAO")

                    .antMatchers("/board/create", "/board/mylist/**", "/board/update/**", "/board/delete/**").hasAnyRole("USER", "AUTHUSER", "KAKAO")
                    .antMatchers("/board/*/comment/create", "/board/*/update/*", "/board/*/delete/*", "/board/*/comment/create/*").hasAnyRole("USER", "AUTHUSER", "KAKAO")
                    .antMatchers("/boardcomment/up/create", "/boardcomment/up/check/**", "/boardcomment/up/delete/**").hasAnyRole("USER", "AUTHUSER", "KAKAO")
                    .antMatchers("/boardscrap/create", "/boardscrap/list", "/boardscrap/check/**", "/boardscrap/delete/**").hasAnyRole("USER", "AUTHUSER", "KAKAO")
                    .antMatchers("/boardup/create", "/boardup/list", "/boardup/check/**", "/boardup/delete/**").hasAnyRole("USER", "AUTHUSER", "KAKAO")

                    .antMatchers("/review/create", "/review/myList", "/review/update", "/review/delete/**").hasRole("AUTHUSER")

                    .antMatchers("/user/list/**", "/user/delete/**", "/admin/signup", "/admin/login", "/admin/update", "/admin/delete/**").hasRole("ADMIN")
                    .antMatchers("/admin/review/create", "/admin/review/list", "/admin/review/update/**", "/admin/review/delete/**").hasRole("ADMIN")
                    .antMatchers("/admin/board/create", "/admin/board/list", "/admin/board/update/**", "/admin/board/delete/**").hasRole("ADMIN")
                    .antMatchers("/admin/tag/create", "/admin/tag/list", "/admin/tag/update", "/admin/tag/delete/**").hasRole("ADMIN")

                    .anyRequest().authenticated()
                    .and()
                    .exceptionHandling()
                    .accessDeniedHandler(customAccessDeniedHandler) // 인가에 대한 예외 처리
//                    .authenticationEntryPoint(customAuthenticationEntryPoint) // 인증에 대한 예외 처리
                    .and()
                    .formLogin().disable()
                    .addFilterBefore(new JwtFilter(secretKey, userRepository), UsernamePasswordAuthenticationFilter.class)
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    // OAuth 2.0 로그인 처리
                    .oauth2Login()
                    // 로그인 성공 시 실행할 커스텀 핸들러 설정
                    .successHandler(oAuth2AuthenticationSuccessHandler)
                    // 인증이 완료된 사용자에 대한 정보를 가져옴
                    .userInfoEndpoint()
                    // userInfoEndpoint로부터 얻은 사용자 정보를 처리할 사용자 서비스를 지정
                    .userService(userOAuth2Service);
            return http.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
