package jp.sbpayment.bpr.bl.test.service;

import jp.sbpayment.bpr.bl.dto.NoticeMailDto;
import jp.sbpayment.bpr.bl.repository.NotificationMailRepository;
import jp.sbpayment.bpr.bl.service.NotificationMailService;
import jp.sbpayment.bpr.bl.test.ApplicationConfigurationTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationConfigurationTest.class)
public class NotificationMailServiceTest {

  @Autowired
  private NotificationMailService notificationMailService;

  @Autowired
  private NotificationMailRepository notificationMailRepository;

  private PodamFactory podam = new PodamFactoryImpl();

  private NoticeMailDto noticeMailDto;

  @Before
  public void setUp() {
    notificationMailRepository.deleteAll();
    noticeMailDto = podam.manufacturePojoWithFullData(NoticeMailDto.class);
  }

  @Test
  public void testSaveNoticeMailSuccess() {
    Assert.assertEquals(0, notificationMailRepository.count());
    notificationMailService.save(noticeMailDto);
    Assert.assertEquals(1, notificationMailRepository.count());
  }

  @Test(expected = DataIntegrityViolationException.class)
  public void testSaveNoticeMailRequiredNoticeId() {
    noticeMailDto.setNoticeId(null);
    notificationMailService.save(noticeMailDto);
  }

  @Test(expected = NumberFormatException.class)
  public void testSaveNoticeMailRequiredStatus() {
    noticeMailDto.setStatus(Integer.valueOf(null));
    notificationMailService.save(noticeMailDto);
  }

}
