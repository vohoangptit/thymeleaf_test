package jp.sbpayment.bpr.controller;

import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import jp.sbpayment.bpr.bl.dto.NoticeDto;
import jp.sbpayment.bpr.bl.dto.NoticeMailDto;
import jp.sbpayment.bpr.bl.dto.NoticeTemplateDto;
import jp.sbpayment.bpr.services.NoticeManagementService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@AllArgsConstructor
public class NotificationRegisterController {

  private final NoticeManagementService noticeManagementService;

  /**
   * create new notice.
   *
   * @param model send data to view
   * @return String
   */
  @GetMapping("/notice/create")
  public String createNotice(Model model) {

    List<NoticeTemplateDto> templates = noticeManagementService.loadTemplates();
    model.addAttribute("templates", templates);
    return "pages/NoticeRegist";
  }

  /**
   * save new notice to database.
   *
   * @param noticeDto notice dto object
   */
  @PostMapping("/notice/save")
  @ResponseBody
  public void saveNotice(@Valid @RequestBody NoticeDto noticeDto) {
    if (noticeDto.getDisplayDay() < 0) {
      return;
    }
    Date announceDate = DateUtils.addDays(new Date(), noticeDto.getDisplayDay());
    noticeDto.setAnnounceDate(announceDate);
    noticeManagementService.save(noticeDto);
  }

  @GetMapping("/notice/newest")
  @ResponseBody
  public NoticeDto findNewestNoticeInserted() {
    return this.noticeManagementService.findNewestNoticeInserted();
  }

  @GetMapping("/notice-template/{templateName}")
  @ResponseBody
  public NoticeTemplateDto findByTemplateName(@PathVariable String templateName) {
    return this.noticeManagementService.findByTemplateName(templateName);
  }

  /**
   * Save template notice.
   *
   * @param noticeDto    notice object
   * @param templateName name of template
   */
  @PostMapping("/notice-template/save/{templateName}")
  @ResponseBody
  @Transactional(rollbackFor = Exception.class)
  public void saveNoticeTemplate(@Valid @RequestBody NoticeDto noticeDto,
                   @Valid @PathVariable String templateName) {
    //save notice
    Date announceDate = DateUtils.addDays(new Date(), noticeDto.getDisplayDay());
    noticeDto.setAnnounceDate(announceDate);
    
    this.noticeManagementService.saveNoticeTemplate(noticeDto, templateName);
  }

  @PostMapping("/notice-mail/save")
  @ResponseBody
  public void saveNoticeMail(@Valid @RequestBody NoticeMailDto noticeMailDto) {
    noticeManagementService.saveEmail(noticeMailDto);
  }

}
