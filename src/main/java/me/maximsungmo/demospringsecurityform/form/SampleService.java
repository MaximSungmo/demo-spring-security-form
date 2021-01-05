package me.maximsungmo.demospringsecurityform.form;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SampleService {

  public void dashboard() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    Object principal = authentication.getPrincipal(); // loadUserByUsername 의 return 된 User 객체
    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities(); // 유저 권한의 집합
    Object credentials = authentication.getCredentials();
    boolean authenticated = authentication.isAuthenticated();
  }
}
