package com.board.obk_board.session;

import com.board.obk_board.domain.CustomUserDetail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LoginedUser {
    private Authentication authentication;

    @PostConstruct
    public void init(){
        authentication = SecurityContextHolder.getContext().getAuthentication();
    }

    public String getUserName(){
        CustomUserDetail userDetails = (CustomUserDetail)authentication.getPrincipal();
        return userDetails.getUsername();
    }

    public boolean isAnonymouse(){
        return authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ANONYMOUS"));
    }
}
