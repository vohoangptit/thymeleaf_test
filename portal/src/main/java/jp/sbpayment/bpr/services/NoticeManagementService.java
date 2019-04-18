package jp.sbpayment.bpr.services;

import java.util.List;
import jp.sbpayment.bpr.bl.dto.NoticeDto;
import jp.sbpayment.bpr.bl.dto.NoticeMailDto;
import jp.sbpayment.bpr.bl.dto.NoticeTemplateDto;
import jp.sbpayment.bpr.bl.model.Notice;
import jp.sbpayment.bpr.bl.service.NotificationServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface NoticeManagementService
        extends PortalService<NoticeDto, Notice, NotificationServiceImpl> {

  void save(NoticeDto noticeDto);

  NoticeDto findNewestNoticeInserted();

  void saveNoticeTemplate(NoticeDto noticeDto, String templateName);

  void saveEmail(NoticeMailDto noticeMailDto);

  NoticeTemplateDto findByTemplateName(String templateName);

  NoticeDto showDetail(long noticeId);

  void delete(long noticeId);

  List<NoticeTemplateDto> loadTemplates();

  Page<NoticeDto> findPaginated(PageRequest pageRequest, String title, String notice);

}
