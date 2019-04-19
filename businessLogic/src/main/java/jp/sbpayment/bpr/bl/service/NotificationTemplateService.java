package jp.sbpayment.bpr.bl.service;

import jp.sbpayment.bpr.bl.dto.NoticeTemplateDto;
import jp.sbpayment.bpr.bl.model.NoticeTemplate;

public interface NotificationTemplateService extends 
    BusinessLogicService<NoticeTemplateDto, NoticeTemplate> {

  NoticeTemplateDto findByTemplateName(String templateName);

  void deleteAll();

}
