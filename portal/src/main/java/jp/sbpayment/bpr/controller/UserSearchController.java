package jp.sbpayment.bpr.controller;

import java.util.ArrayList;
import java.util.Optional;
import jp.sbpayment.bpr.bl.dto.UserDto;
import jp.sbpayment.bpr.common.utils.SbpsUtils;
import jp.sbpayment.bpr.services.UserManagementService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class UserSearchController {

  private static final int PAGE_SIZE = 5;

  private final UserManagementService userManagementService;

  /**
   * do search.
   */
  @GetMapping("/user/search")
  public String search(@RequestParam(value = "name", required = false) String name,
                       @RequestParam(value = "email", required = false) String email, Model model,
                       @RequestParam(value = "page") Optional<Integer> page) {

    final int currentPage = page.orElse(1);
    final int pageSize = PAGE_SIZE;
    final int index = (currentPage - 1) * pageSize;

    Page<UserDto> userPage =
        userManagementService.findPaginated(PageRequest.of(currentPage - 1, pageSize), name, email);
    model.addAttribute("userPage", userPage);
    int totalPages = userPage.getTotalPages();
    int reloadPage = currentPage;
    if (totalPages > 0) {

      if (reloadPage > totalPages) {
        reloadPage = totalPages;
        return "redirect:/user/search" + "?name=" + name + "&email=" + email + "&page="
            + reloadPage;
      }

      model.addAttribute("currentPage", currentPage);

      ArrayList<Integer> sequencePagination =
          SbpsUtils.calcTheSequencePaginationNumbersRendering(currentPage, totalPages, pageSize);
      model.addAttribute("sequencePagination", sequencePagination);
    }
    model.addAttribute("index", index);
    model.addAttribute("name", name);
    model.addAttribute("email", email);
    model.addAttribute("isFirstLoad",
        (StringUtils.equals(name, null) && StringUtils.equals(email, null)));
    
    return "pages/UserSearchList";
  }

  /**
   * show detail.
   */
  @GetMapping("/user/{username}/detail")
  public String showDetail(@PathVariable String username, Model model) {
    model.addAttribute("user", this.userManagementService.findUserByUserName(username));
    model.addAttribute("roles", this.userManagementService.loadRoles());
    return "pages/UserDetail";
  }

}
