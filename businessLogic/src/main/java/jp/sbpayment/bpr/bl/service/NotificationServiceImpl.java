package jp.sbpayment.bpr.bl.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import jp.sbpayment.bpr.bl.dto.NoticeDto;
import jp.sbpayment.bpr.bl.mapper.BaseMapper;
import jp.sbpayment.bpr.bl.mapper.NoticeMapper;
import jp.sbpayment.bpr.bl.model.Notice;
import jp.sbpayment.bpr.bl.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service for Notice Management.
 */
@Service
@AllArgsConstructor
public class NotificationServiceImpl 
    extends BusinessLogicServiceImpl<NotificationRepository, NoticeDto, Notice>
    implements NotificationService {

  private final NotificationRepository notificationRepository;

  @Override
  protected NotificationRepository getRepository() {
    return this.notificationRepository;
  }

  @Override
  protected BaseMapper<NoticeDto, Notice> getMapper() {
    return NoticeMapper.INSTANCE;
  }
  
  @Override
  public NoticeDto delete(long id) {
    
    Optional<Notice> optional = getRepository().findById(id);
    if (optional.isEmpty()) {
      return null;
    }
    Notice entity = optional.get();
    // entity.setDelete(1);
    entity.setDisplayDay(-1);
    entity = getRepository().save(entity);
    return getMapper().toDto(entity);
  }

  @Override
  public NoticeDto findNewestNoticeInserted() {
    Notice entity = this.notificationRepository.findNewestNoticeInserted();
    if (entity == null) {
      return null;
    }
    return getMapper().toDto(entity);
  }

  @Override
  public Page<NoticeDto> findPaginated(Pageable pageable, String title, String notice) {
    
    int pageSize = pageable.getPageSize();
    int currentPage = pageable.getPageNumber();
    int startItem = currentPage * pageSize;

    List<NoticeDto> list;

    List<Notice> notices = notificationRepository.findByTitleAndNotice(title, notice);

    if (notices.size() < startItem) {
      list = Collections.emptyList();
    } else {
      int toIndex = Math.min(startItem + pageSize, notices.size());
      list = notices.subList(startItem, toIndex).stream()
          .map(entity -> getMapper().toDto(entity)).collect(Collectors.toList());
    }
    return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), notices.size());
  }

  @Override
  public List<NoticeDto> search(String title, String notice) {
    
    List<Notice> entities = this.getRepository().findByTitleAndNotice(title, notice);
    if (entities == null || entities.isEmpty()) {
      return Collections.emptyList();
    }
    return entities.stream().map(entity -> getMapper().toDto(entity)).collect(Collectors.toList());
  }

}
