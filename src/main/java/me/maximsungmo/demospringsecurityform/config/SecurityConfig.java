package me.maximsungmo.demospringsecurityform.config;

import me.maximsungmo.demospringsecurityform.account.AccountService;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@Order(Ordered.LOWEST_PRECEDENCE - 100)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  final AccountService accountService;

  public SecurityConfig(AccountService accountService) {
    this.accountService = accountService;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .mvcMatchers("/", "/info", "/account/**")
        .permitAll()
        .mvcMatchers("/admin")
        .hasRole("ADMIN")
        .mvcMatchers("/user")
        .hasRole("USER")
        .anyRequest()
        .authenticated()
        .accessDecisionManager(accessDecisionManager()) // 아예 accessdecision 구현
//        .expressionHandler(expressionHandler()) // expressionHandler 만 구현
    ;

    http.formLogin();
    http.httpBasic();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(accountService);
  }

  // 권한을 허용하기 위해서 롤에 대한 계층 구조를 통해 만약 ADMIN이 접속한다면 USER 페이지도 접근이 가능하게끔 해줘야한다.
  // 이러한 권한은 AccessDecisionManager를 통해서 할 수 있고 구현을 위하 voter list 를 정의해서 넘겨주어야한다.
  public AccessDecisionManager accessDecisionManager() {
    // 계층구조를 만들고
    RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
    roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER");

    // 그 계층구조를 핸들링 할 수 있도록 핸들러에 넣어서
    DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
    handler.setRoleHierarchy(roleHierarchy);

    // 해당하는 보터는 이 핸들러를 사용하겠다고 명시해주고
    WebExpressionVoter webExpressionVoter = new WebExpressionVoter();
    webExpressionVoter.setExpressionHandler(handler);

    // 명시된 보터를 리스트로 넣어주면 된다.
    List<AccessDecisionVoter<? extends Object>> voters = Arrays.asList(webExpressionVoter);
    return new AffirmativeBased(voters);
  }

  // 위의 과정을 조금 줄이는 방법으로는 expressionhandler를 구현해서 넣어주는 방법
  public SecurityExpressionHandler expressionHandler() {
    // 계층구조를 만들고
    RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
    roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER");

    // 그 계층구조를 핸들링 할 수 있도록 핸들러에 넣어서
    DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
    handler.setRoleHierarchy(roleHierarchy);

    return handler;
  }
}
