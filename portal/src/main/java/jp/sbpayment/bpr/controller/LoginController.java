package jp.sbpayment.bpr.controller;

import java.security.Principal;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

  /**
   * do login.
   */
  @GetMapping("/login")
  public String showLogin(Model model, String error, Principal principal) {
    if (!StringUtils.equals(error, null)) {
      model.addAttribute("error", "ユーザー名とパスワードは無効ではありません。");
    }

    return principal == null ? "pages/Login" : "redirect:/";
  }

}
