package com.book.shop.config;


import com.book.shop.config.auth.PrincipalOauth2UserService;
import com.book.shop.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity

public class SecurityConfig {

    @Autowired
    private PrincipalOauth2UserService principalOauth2UserService;

    @Autowired
    MemberService memberService;


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.formLogin((formLogin) -> formLogin
                        .usernameParameter("email") // 로그인 시 사용할 파라미터로 email 사용
                        .failureUrl("/members/login/error") // 로그인 실패시 이동할 페이지
                        .loginPage("/members/login") // 로그인 페이지 설정
                        .defaultSuccessUrl("/"))// 로그인 성공시 이동할 페이지)
                .logout((logout) -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout")) // 로그아웃 url 설정
                        .logoutSuccessUrl("/") // 로그아웃 성공 시 이동할 url
                        .invalidateHttpSession(true) // 기존에 생성된 사용자 세션도 invalidateHttpSession 을 통해 삭제하도록 처리//
                );
        http
                .oauth2Login((oauth2) -> oauth2
                        .loginPage("/oauth2/authorization/google")
                        .defaultSuccessUrl("/")
                        .userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint.userService(principalOauth2UserService))
                );

        http.authorizeHttpRequests((auth) -> auth
                .requestMatchers("/css/**", "/js/**", "/img/**").permitAll()
                .requestMatchers("/", "/members/**", "/item/**", "/images/**", "/shoppingmall/**").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
        );
        http.exceptionHandling(authenticationManager -> authenticationManager
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint()));



        return http.build();
    }
}