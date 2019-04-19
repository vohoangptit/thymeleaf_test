package jp.sbpayment.bpr.bl.service;

import jp.sbpayment.bpr.bl.dto.NoticeTemplateDto;
import jp.sbpayment.bpr.bl.mapper.BaseMapper;
import jp.sbpayment.bpr.bl.mapper.NoticeTemplateMapper;
import jp.sbpayment.bpr.bl.model.NoticeTemplate;
import jp.sbpayment.bpr.bl.repository.NotificationTemplateRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service for Notice Template Management.
 */
@Service
@AllArgsConstructor
public class NotificationTemplateServiceImpl extends
    BusinessLogicServiceImpl<NotificationTemplateRepository, NoticeTemplateDto, NoticeTemplate>
    implements NotificationTemplateService {

  private final NotificationTemplateRepository notificationTemplateRepository;

  @Override
  protected NotificationTemplateRepository getRepository() {
    return this.notificationTemplateRepository;
  }

  @Override
  protected BaseMapper<NoticeTemplateDto, NoticeTemplate> getMapper() {
    return NoticeTemplateMapper.INSTANCE;
  }

  @Override
  public NoticeTemplateDto findByTemplateName(String templateName) {
    
    if (templateName == null || "".contentEquals(templateName)) {
      return null;
    }
    NoticeTemplate entity = this.getRepository().findByTemplateName(templateName);
    return getMapper().toDto(entity);
  }

  @Override
  public void deleteAll() {

    this.notificationTemplateRepository.deleteAll();
  }
}
