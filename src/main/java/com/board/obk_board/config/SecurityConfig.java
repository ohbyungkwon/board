package com.board.obk_board.config;

import com.board.obk_board.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserDetailService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }


    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/board**").authenticated()
                .anyRequest().permitAll();

        http.formLogin()
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                .loginProcessingUrl("/j_spring_security_check")
                .loginPage("/register")
                .defaultSuccessUrl("/board?page=0")
                .and()
                .csrf()
                .ignoringAntMatchers(
                        "/users/**",
                        "/users",
                        "/board**",
                        "/board/**",
                        "/comment",
                        "/comment/**",
                        "/check**"
                )
                .and()
                .httpBasic()
                .and()
                .logout()
                .logoutSuccessUrl("/register")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));//기본적으로 시큐리티의 로그아웃은 포스트만 지원을한다. 그렇기 때문에 마지막 줄을 추가하여 get을 지원하게함.

        http.sessionManagement()
                .invalidSessionUrl("/register")
                .maximumSessions(1)
                .expiredUrl("/register");
    }
}
