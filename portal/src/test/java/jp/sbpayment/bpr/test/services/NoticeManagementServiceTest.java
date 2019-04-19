package jp.sbpayment.bpr.test.services;

import java.util.List;
import jp.sbpayment.bpr.bl.dto.NoticeDto;
import jp.sbpayment.bpr.bl.dto.NoticeMailDto;
import jp.sbpayment.bpr.bl.dto.NoticeTemplateDto;
import jp.sbpayment.bpr.bl.service.NotificationMailService;
import jp.sbpayment.bpr.bl.service.NotificationService;
import jp.sbpayment.bpr.bl.service.NotificationTemplateService;
import jp.sbpayment.bpr.services.NoticeManagementService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NoticeManagementServiceTest {

  @Autowired
  private NoticeManagementService noticeManagementService;

  @Autowired
  private NotificationService notificationService;

  @Autowired
  private NotificationTemplateService notificationTemplateService;

  @Autowired
  private NotificationMailService notificationMailService;

  private PodamFactory podam = new PodamFactoryImpl();

  private NoticeDto noticeDto;

  private NoticeMailDto noticeMailDto;

  private NoticeTemplateDto noticeTemplateDto;

  @MockBean
  PageRequest pageRequest;

  /**
   * setup.
   */
  @Before
  public void setUp() {
    noticeDto = podam.manufacturePojoWithFullData(NoticeDto.class);
    noticeMailDto = podam.manufacturePojoWithFullData(NoticeMailDto.class);
    noticeTemplateDto = podam.manufacturePojoWithFullData(NoticeTemplateDto.class);
  }

  @Test
  public void testSave() {
    noticeDto.setId(1L);
    noticeManagementService.save(noticeDto);
    NoticeDto returnedNotice = notificationService.findById(noticeDto.getId());
    Assert.assertNotNull(returnedNotice);
    Assert.assertEquals(1L, returnedNotice.getId());
    Assert.assertEquals(noticeDto.getTitle(), returnedNotice.getTitle());
  }

  @Test
  public void findNewestNoticeInserted() {
    noticeManagementService.save(noticeDto);
    NoticeDto returnedNotice = noticeManagementService.findNewestNoticeInserted();
    Assert.assertNotNull(returnedNotice);
    Assert.assertEquals(noticeDto.getTitle(), returnedNotice.getTitle());
  }

  @Test
  public void testSaveNoticeTemplate() {
    noticeManagementService.saveNoticeTemplate(noticeDto, "test");
    NoticeTemplateDto returnedNoticeTemplate = notificationTemplateService
            .findByTemplateName("test");
    Assert.assertNotNull(returnedNoticeTemplate);
    Assert.assertEquals("test", returnedNoticeTemplate.getTemplateName());

  }

  @Test
  public void testDelete() {
    noticeDto.setId(1L);
    noticeManagementService.save(noticeDto);
    noticeManagementService.delete(notificationService.findById(noticeDto.getId()).getId());
    Assert.assertEquals(noticeManagementService.showDetail(noticeDto.getId()).getDisplayDay(), -1);
  }

  @Test
  public void testSaveEmail() {
    noticeMailDto.setId(1L);
    noticeManagementService.saveEmail(noticeMailDto);
    NoticeMailDto returnedNoticeMailDto = notificationMailService.findById(noticeMailDto.getId());
    Assert.assertNotNull(returnedNoticeMailDto);
    Assert.assertEquals(1L, returnedNoticeMailDto.getId());
  }

  @Test
  public void testFindByTemplateName() {
    noticeTemplateDto.setTemplateName("test");
    notificationTemplateService.save(noticeTemplateDto);
    NoticeTemplateDto returnedNoticeTemplateDto = noticeManagementService
            .findByTemplateName(noticeTemplateDto.getTemplateName());
    Assert.assertNotNull(returnedNoticeTemplateDto);
    Assert.assertEquals("test", returnedNoticeTemplateDto.getTemplateName());
  }

  @Test
  public void testShowDetail() {
    noticeDto.setId(1L);
    notificationService.save(noticeDto);
    NoticeDto returnedNoticeDto = noticeManagementService.showDetail(noticeDto.getId());
    Assert.assertNotNull(returnedNoticeDto);
    Assert.assertEquals(1L, returnedNoticeDto.getId());
  }

  @Test
  public void testLoadTemplates() {
    notificationTemplateService.deleteAll();
    noticeTemplateDto.setTemplateName("template1");
    notificationTemplateService.save(noticeTemplateDto);
    noticeTemplateDto = podam.manufacturePojoWithFullData(NoticeTemplateDto.class);
    noticeTemplateDto.setTemplateName("template2");
    notificationTemplateService.save(noticeTemplateDto);
    List<NoticeTemplateDto> returnedList = noticeManagementService.loadTemplates();
    Assert.assertNotNull(returnedList);
    Assert.assertEquals("template1", returnedList.get(0).getTemplateName());
    Assert.assertEquals("template2", returnedList.get(1).getTemplateName());
  }

  @Test
  public void testFindPaginated() {
    Mockito.when(pageRequest.getPageSize()).thenReturn(2);
    Mockito.when(pageRequest.getPageNumber()).thenReturn(2);
    Page<NoticeDto> paginated = noticeManagementService.findPaginated(pageRequest,
            noticeDto.getTitle(), noticeDto.getNotice());
    Assert.assertEquals(0, paginated.getTotalPages());

    Mockito.when(pageRequest.getPageSize()).thenReturn(1);
    Mockito.when(pageRequest.getPageNumber()).thenReturn(0);
    notificationService.save(noticeDto);
    Page<NoticeDto> paginate = notificationService.findPaginated(pageRequest,
            noticeDto.getTitle(), noticeDto.getNotice());
    Assert.assertEquals(1, paginate.getTotalPages());
  }

}
