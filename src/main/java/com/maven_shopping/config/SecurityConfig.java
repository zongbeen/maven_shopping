package com.maven_shopping.config;

import com.maven_shopping.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity //보안 설정을 커스터마이징
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MemberService memberService;
    //http 요청에 대한 보안 설정
    //페이지 권한, 로그인 페이지, 로그아웃 메소드등 설정
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/members/login") //로그인 페이지 URL
                .defaultSuccessUrl("/") //로그인 성공 시 URL
                .usernameParameter("email") //로그인 시 사용할 파라미터 이름을 email로 지정
                .failureUrl("/members/login/error") //로그인 실패 시 URL
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher
                        ("/members/logout"))//로그아웃 URL
                .logoutSuccessUrl("/") //로그아웃 성공 시 URL
        ;
    }
    //비밀번호 암호화하여 저장
    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
    // spring security 인증은 AuthenticationManagerBuilder를 통해 이루어짐
    //AuthenticationManagerBuilder가 AuthenticationManager 생성
    //userDetailService를 구현하고 있는 객체로 memberService를 지정해주고 비밀번호 암호화를 위해 passwordEncoder를 지정
    throws Exception{
        auth.userDetailsService(memberService)
                .passwordEncoder(passwordEncoder());
    }
}
