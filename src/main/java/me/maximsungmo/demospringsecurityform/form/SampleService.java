package me.maximsungmo.demospringsecurityform.form;

import me.maximsungmo.demospringsecurityform.account.Account;
import me.maximsungmo.demospringsecurityform.account.AccountContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SampleService {

  public void dashboard() {
    // 스프링 시큐리티 컨텍스트 홀더를 통해 정보 가져오기
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    Object principal = authentication.getPrincipal(); // loadUserByUsername 의 return 된 User 객체
    Collection<? extends GrantedAuthority> authorities =
        authentication.getAuthorities(); // 유저 권한의 집합
    Object credentials = authentication.getCredentials();
    boolean authenticated = authentication.isAuthenticated();
  }

  /* threadLocal 사용한 ContextHolder 구현 */
  public void checkAccount() {
    Account account = AccountContext.getAccount();
    System.out.println("===============================");
    System.out.println(account.getUsername());
  }
}
