package jp.sbpayment.bpr.controller;

import javax.validation.Valid;
import jp.sbpayment.bpr.bl.dto.UserDto;
import jp.sbpayment.bpr.services.UserManagementService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@AllArgsConstructor
public class UserRegisterController {

  private final UserManagementService userManagementService;

  /**
   * regist page.
   */
  @GetMapping("/user/regist")
  public String showRegist(Model model) {

    model.addAttribute("roles", this.userManagementService.loadRoles());
    return "pages/UserRegist";
  }

  /**
   * do save.
   */
  @PostMapping("/user/save")
  @ResponseBody
  public int save(@Valid @RequestBody UserDto userDto) {
    
    return this.userManagementService.save(userDto);
  }

}
