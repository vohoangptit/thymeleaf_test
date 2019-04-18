package jp.sbpayment.bpr.controller;

import java.util.ArrayList;
import java.util.Optional;
import jp.sbpayment.bpr.bl.dto.NoticeDto;
import jp.sbpayment.bpr.common.utils.SbpsUtils;
import jp.sbpayment.bpr.services.NoticeManagementService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@AllArgsConstructor
public class NotificationSearchController {

  private static final int PAGE_SIZE = 5;

  private final NoticeManagementService noticeService;

  /**
   * search notice by title and notice text.
   *
   * @param title title string
   * @param notice notice string
   * @param model send data to view
   * @param page page result
   * @return string
   */
  @GetMapping("/notice/search")
  public String search(@RequestParam(value = "title", required = false) String title,
      @RequestParam(value = "notice", required = false) String notice, Model model,
      @RequestParam(value = "page") Optional<Integer> page) {
    final int currentPage = page.orElse(1);
    final int pageSize = PAGE_SIZE;
    final int index = (currentPage - 1) * PAGE_SIZE;

    Page<NoticeDto> noticePage =
        this.noticeService.findPaginated(PageRequest.of(currentPage - 1, pageSize), title, notice);
    model.addAttribute("noticePage", noticePage);
    int totalPages = noticePage.getTotalPages();
    int reloadPage = currentPage;
    if (totalPages > 0) {

      if (reloadPage > totalPages) {
        reloadPage = totalPages;
        return "redirect:/notice/search" + "?title=" + title + "&notice=" + notice + "&page="
            + reloadPage;
      }

      model.addAttribute("currentPage", currentPage);

      /*
       * As pagination always display 5 pages one time, 
       * we should be calculated each time user changed page number
       */
      /*
       * For ensure always displayed the first page number and last page number 
       * with current page when there are many pages.
       * (more than 5)
       */
      ArrayList<Integer> sequencePagination =
          SbpsUtils.calcTheSequencePaginationNumbersRendering(currentPage, totalPages, pageSize);
      model.addAttribute("sequencePagination", sequencePagination);
    }
    model.addAttribute("index", index);
    model.addAttribute("title", title);
    model.addAttribute("notice", notice);

    // When the page is first loaded, the detail grid view did not display anything
    // Hence, we should be check there for avoid display the not found message on first load
    model.addAttribute("isFirstLoad", (title == null && notice == null));

    return "pages/NoticeSearchList";
  }

  /**
   * Show detail.
   * 
   * @param noticeId notice String
   * @param model data to view
   * @return
   */
  @GetMapping("/notice/{noticeId}/detail")
  public String showDetail(@PathVariable long noticeId, Model model) {

    model.addAttribute("notice", this.noticeService.showDetail(noticeId));
    return "pages/NoticeDetail";
  }

  /**
   * delete notice by notice id.
   *
   * @param noticeId notice id
   */
  @GetMapping("/notice/{noticeId}/delete")
  @ResponseStatus(value = HttpStatus.OK)
  public void delete(@PathVariable long noticeId) {

    this.noticeService.delete(noticeId);
  }

}
