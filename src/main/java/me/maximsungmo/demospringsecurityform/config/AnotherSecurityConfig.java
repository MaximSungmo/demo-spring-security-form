package me.maximsungmo.demospringsecurityform.config;

import me.maximsungmo.demospringsecurityform.account.AccountService;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@Order(Ordered.LOWEST_PRECEDENCE - 15)
public class AnotherSecurityConfig extends WebSecurityConfigurerAdapter {

  final AccountService accountService;

  public AnotherSecurityConfig(AccountService accountService) {
    this.accountService = accountService;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.antMatcher("/account/**").authorizeRequests().anyRequest().permitAll();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(accountService);
  }
}
