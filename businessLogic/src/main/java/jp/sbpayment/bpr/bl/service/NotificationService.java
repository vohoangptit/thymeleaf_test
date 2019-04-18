package jp.sbpayment.bpr.bl.service;

import java.util.List;
import jp.sbpayment.bpr.bl.dto.NoticeDto;
import jp.sbpayment.bpr.bl.model.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NotificationService extends BusinessLogicService<NoticeDto, Notice> {

  NoticeDto findNewestNoticeInserted();

  Page<NoticeDto> findPaginated(Pageable pageable, String title, String notice);

  List<NoticeDto> search(String title, String notice);

}
