package jp.sbpayment.bpr.bl.service;

import jp.sbpayment.bpr.bl.dto.NoticeMailDto;
import jp.sbpayment.bpr.bl.mapper.BaseMapper;
import jp.sbpayment.bpr.bl.mapper.NoticeMailMapper;
import jp.sbpayment.bpr.bl.model.NoticeMail;
import jp.sbpayment.bpr.bl.repository.NotificationMailRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service for Notice Mail Management.
 */
@Service
@AllArgsConstructor
public class NotificationMailServiceImpl extends 
    BusinessLogicServiceImpl<NotificationMailRepository, NoticeMailDto, NoticeMail> implements 
    NotificationMailService {

  private final NotificationMailRepository notificationMailRepository;

  @Override
  protected NotificationMailRepository getRepository() {
    return this.notificationMailRepository;
  }

  @Override
  protected BaseMapper<NoticeMailDto, NoticeMail> getMapper() {
    return NoticeMailMapper.INSTANCE;
  }
  
}