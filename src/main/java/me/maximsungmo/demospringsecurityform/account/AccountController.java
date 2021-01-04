package me.maximsungmo.demospringsecurityform.account;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

  final AccountService accountService;

  public AccountController(AccountService accountRepository) {
    this.accountService = accountRepository;
  }

  @GetMapping("/account/{role}/{username}/{password}")
  public Account creaAccount(@ModelAttribute Account account) {
    return accountService.createNew(account);
  }
}
