package jp.sbpayment.bpr.bl.test.service;

import java.util.List;
import jp.sbpayment.bpr.bl.dto.NoticeTemplateDto;
import jp.sbpayment.bpr.bl.repository.NotificationTemplateRepository;
import jp.sbpayment.bpr.bl.service.NotificationTemplateService;
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
public class NotificationTemplateServiceTest {

  @Autowired
  private NotificationTemplateService notificationTemplateService;

  @Autowired
  private NotificationTemplateRepository notificationTemplateRepository;

  private PodamFactory podam = new PodamFactoryImpl();

  private NoticeTemplateDto noticeTemplateDto;

  @Before
  public void setUp() {
    notificationTemplateRepository.deleteAll();
    noticeTemplateDto = podam.manufacturePojoWithFullData(NoticeTemplateDto.class);
  }

  @Test
  public void testSaveNoticeTemplateSuccess() {
    Assert.assertEquals(0, notificationTemplateRepository.count());
    notificationTemplateService.save(noticeTemplateDto);
    Assert.assertEquals(1, notificationTemplateRepository.count());
  }

  @Test(expected = DataIntegrityViolationException.class)
  public void testSaveNoticeTemplateRequiredTemplateName() {
    Assert.assertEquals(0, notificationTemplateRepository.count());
    noticeTemplateDto.setTemplateName(null);
    notificationTemplateService.save(noticeTemplateDto);
    Assert.assertEquals(1, notificationTemplateRepository.count());
  }

  @Test(expected = DataIntegrityViolationException.class)
  public void testSaveNoticeTemplateRequiredTitle() {
    Assert.assertEquals(0, notificationTemplateRepository.count());
    noticeTemplateDto.setTitle(null);
    notificationTemplateService.save(noticeTemplateDto);
    Assert.assertEquals(1, notificationTemplateRepository.count());
  }

  @Test(expected = DataIntegrityViolationException.class)
  public void testSaveNoticeTemplateRequiredNotice() {
    Assert.assertEquals(0, notificationTemplateRepository.count());
    noticeTemplateDto.setNotice(null);
    notificationTemplateService.save(noticeTemplateDto);
    Assert.assertEquals(1, notificationTemplateRepository.count());
  }

  @Test(expected = NumberFormatException.class)
  public void testSaveNoticeTemplateRequiredEnabled() {
    Assert.assertEquals(0, notificationTemplateRepository.count());
    noticeTemplateDto.setEnabled(Integer.valueOf(null));
    notificationTemplateService.save(noticeTemplateDto);
    Assert.assertEquals(1, notificationTemplateRepository.count());
  }

  @Test
  public void testFindAll() {
    notificationTemplateService.save(noticeTemplateDto);
    List<NoticeTemplateDto> inserted = notificationTemplateService.findAll();
    Assert.assertNotNull(inserted.get(0));
    Assert.assertEquals(inserted.get(0).getTemplateName(), noticeTemplateDto.getTemplateName());
  }

  @Test
  public void findByTemplateName() {
    notificationTemplateService.save(noticeTemplateDto);
    NoticeTemplateDto inserted = notificationTemplateService
            .findByTemplateName(noticeTemplateDto.getTemplateName());
    Assert.assertNotNull(inserted);
    Assert.assertEquals(inserted.getTemplateName(), noticeTemplateDto.getTemplateName());
  }

}
