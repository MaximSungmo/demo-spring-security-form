package me.maximsungmo.demospringsecurityform.account;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

  @Autowired MockMvc mockMvc;
  @Autowired AccountService accountService;

  @Test
  public void index_anonymous() throws Exception {
    mockMvc.perform(get("/").with(anonymous())).andDo(print()).andExpect(status().isOk());
  }

  @Test
  @WithAnonymousUser
  public void index_anonymous_annotation() throws Exception {
    mockMvc.perform(get("/")).andExpect(status().isOk());
  }

  @Test
  public void index_user() throws Exception {
    mockMvc
        .perform(get("/").with(user("user").roles("USER")))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  //  @WithMockUser(username = "user", roles = "USER")
  @WithUser
  public void index_user_annotation() throws Exception {
    mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk());
  }

  @Test
  public void index_admin() throws Exception {
    mockMvc
        .perform(get("/").with(user("admin").roles("ADMIN")))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  public void admin_user() throws Exception {
    mockMvc
        .perform(get("/admin").with(user("user").roles("USER")))
        .andDo(print())
        .andExpect(status().isForbidden());
  }

  @Test
  public void admin_admin() throws Exception {
    mockMvc
        .perform(get("/admin").with(user("admin").roles("ADMIN")))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  @Transactional
  public void login_success() throws Exception {
    String username = "user";
    String password = "123";
    Account user = this.createUser(username, password);

    mockMvc
        .perform(formLogin().user(username).password(password))
        .andExpect(authenticated())
        .andDo(print());
  }

  @Test
  @Transactional
  public void login_fail_username() throws Exception {
    String username = "user";
    String incorrectId = "user1";
    String password = "123";
    Account user = this.createUser(username, password);

    mockMvc
        .perform(formLogin().user(incorrectId).password(password))
        .andExpect(unauthenticated())
        .andDo(print());
  }

  @Test
  @Transactional
  public void login_fail_password() throws Exception {
    String username = "user";
    String incorrectPassword = "1234";
    String password = "123";
    Account user = this.createUser(username, password);

    mockMvc
        .perform(formLogin().user(username).password(incorrectPassword))
        .andExpect(unauthenticated())
        .andDo(print());
  }

  private Account createUser(String username, String password) {
    Account account = new Account();
    account.setUsername("user");
    account.setPassword("123");
    account.setRole("USER");
    return accountService.createNew(account);
  }
}
