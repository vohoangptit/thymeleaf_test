package jp.sbpayment.bpr.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import jp.sbpayment.bpr.bl.dto.NoticeDto;
import jp.sbpayment.bpr.bl.dto.NoticeMailDto;
import jp.sbpayment.bpr.bl.dto.NoticeTemplateDto;
import jp.sbpayment.bpr.bl.log.ConstLog;
import jp.sbpayment.bpr.bl.model.Notice;
import jp.sbpayment.bpr.bl.service.NotificationMailService;
import jp.sbpayment.bpr.bl.service.NotificationServiceImpl;
import jp.sbpayment.bpr.bl.service.NotificationTemplateService;
import jp.sbpayment.bpr.common.utils.JsonUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NoticeManagementServiceImpl
    extends PortalServiceImpl<NoticeDto, Notice, NotificationServiceImpl>
    implements NoticeManagementService {

  private final NotificationTemplateService notificationTemplateService;

  private final NotificationMailService notificationMailService;

  @Override
  public void save(@Valid NoticeDto noticeDto) {

    operationLogCommon.logOperation(ConstLog.LOG_FUNC_NOTICE_REGISTRATION,
        ConstLog.LOG_OPER_REGISTER, JsonUtils.convert(noticeDto));
    service.save(noticeDto);

  }

  @Override
  public NoticeDto findNewestNoticeInserted() {
    return this.service.findNewestNoticeInserted();
  }

  @Override
  public void saveNoticeTemplate(NoticeDto noticeDto, String templateName) {

    // save notice template
    NoticeTemplateDto noticeTemplateDto = new NoticeTemplateDto();
    noticeTemplateDto.setTitle(noticeDto.getTitle());
    noticeTemplateDto.setNotice(noticeDto.getNotice());
    noticeTemplateDto.setTemplateName(templateName);

    NoticeTemplateDto oldNoticeTemplateDto =
        notificationTemplateService.findByTemplateName(templateName);
    String logFunc = ConstLog.LOG_FUNC_TEMPLATE_REGISTRATION;
    String logOperation = ConstLog.LOG_OPER_REGISTER;
    NoticeTemplateDto noticeTemplateInsertOrUpdate = noticeTemplateDto;

    if (oldNoticeTemplateDto != null) {
      logOperation = ConstLog.LOG_OPER_UPDATE;
      oldNoticeTemplateDto.setTemplateName(noticeTemplateDto.getTemplateName());
      oldNoticeTemplateDto.setNotice(noticeTemplateDto.getNotice());
      oldNoticeTemplateDto.setTitle(noticeTemplateDto.getTitle());
      noticeTemplateInsertOrUpdate = oldNoticeTemplateDto;
    }

    operationLogCommon.logOperation(logFunc, logOperation, JsonUtils.convert(noticeTemplateDto));
    notificationTemplateService.save(noticeTemplateInsertOrUpdate);
  }

  @Override
  public void saveEmail(NoticeMailDto noticeMailDto) {

    // TODO log mail
    // operationLogCommon.logOperation(ConstLog.LOG_FUNC_MAILL_REGISTRATION,
    // ConstLog.LOG_OPER_REGISTER, JsonUtils.convert(noticeMailDto));
    this.notificationMailService.save(noticeMailDto);
  }

  @Override
  public NoticeTemplateDto findByTemplateName(String templateName) {
    return this.notificationTemplateService.findByTemplateName(templateName);
  }

  @Override
  public Page<NoticeDto> findPaginated(PageRequest pageRequest, String title, String notice) {

    // Log to Operation Table Log
    Map<String, Object> data = new HashMap<>();
    data.put("title", title);
    data.put("notice", notice);
    operationLogCommon.logOperation(ConstLog.LOG_FUNC_NOTICE_LIST, ConstLog.LOG_OPER_SEARCH,
        JsonUtils.convert(data));
    Page<NoticeDto> pageData = service.findPaginated(pageRequest, title, notice);

    return pageData;
  }

  @Override
  public NoticeDto showDetail(long noticeId) {

    return service.findById(noticeId);
  }

  @Override
  public void delete(long noticeId) {

    Map<String, Object> data = new HashMap<>();
    data.put("noticeId", noticeId);
    operationLogCommon.logOperation(ConstLog.LOG_FUNC_NOTICE_INFOR, ConstLog.LOG_OPER_DELETE,
        JsonUtils.convert(data));
    service.delete(noticeId);
  }

  @Override
  public List<NoticeTemplateDto> loadTemplates() {
    return this.notificationTemplateService.findAll();
  }

}
