package jp.sbpayment.bpr.bl.test.service;

import java.util.List;
import java.util.Optional;
import jp.sbpayment.bpr.bl.dto.NoticeDto;
import jp.sbpayment.bpr.bl.mapper.NoticeMapper;
import jp.sbpayment.bpr.bl.model.Notice;
import jp.sbpayment.bpr.bl.repository.NotificationRepository;
import jp.sbpayment.bpr.bl.service.NotificationService;
import jp.sbpayment.bpr.bl.test.ApplicationConfigurationTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationConfigurationTest.class)
public class NotificationServiceTest {

  @Autowired
  private NotificationService notificationService;

  @Autowired
  private NotificationRepository notificationRepository;

  private PodamFactory podam = new PodamFactoryImpl();

  private NoticeDto noticeDto;

  @MockBean
  Pageable pageable;

  /**
   * setup.
   */
  @Before
  public void setUp() {
    notificationRepository.deleteAll();
    noticeDto = podam.manufacturePojoWithFullData(NoticeDto.class);
  }

  @Test
  public void testSaveNoticeSuccess() {
    notificationService.save(noticeDto);
    NoticeDto returnedNotice = notificationService.findNewestNoticeInserted();
    Assert.assertNotNull(returnedNotice);
    Assert.assertEquals(noticeDto.getTitle(), returnedNotice.getTitle());
  }

  @Test(expected = DataIntegrityViolationException.class)
  public void testSaveNoticeRequiredTitle() {
    noticeDto.setTitle(null);
    notificationService.save(noticeDto);
  }

  @Test(expected = DataIntegrityViolationException.class)
  public void testSaveNoticeRequiredAnnounceDay() {
    noticeDto.setAnnounceDate(null);
    notificationService.save(noticeDto);
  }

  @Test(expected = DataIntegrityViolationException.class)
  public void testSaveNoticeRequiredNotice() {
    noticeDto.setNotice(null);
    notificationService.save(noticeDto);
  }

  @Test(expected = NumberFormatException.class)
  public void testSaveNoticeRequiredDisplayDay() {
    noticeDto.setDisplayDay(Integer.valueOf(null));
    notificationService.save(noticeDto);
  }

  @Test(expected = NumberFormatException.class)
  public void testSaveNoticeRequiredDisplayTarget() {
    noticeDto.setDisplayTarget(Integer.valueOf(null));
    notificationService.save(noticeDto);
  }

  @Test
  public void testDeleteNoticeSuccess() {


    notificationService.save(noticeDto);
    NoticeDto deletedNotice = notificationService.findNewestNoticeInserted();
    notificationService.delete(deletedNotice.getId());
    Optional<Notice> optionalNotice = notificationRepository.findById(deletedNotice.getId());

    Assert.assertEquals(true, optionalNotice.isPresent());
    if (optionalNotice.isPresent()) {
      NoticeDto returnedNotice = NoticeMapper.INSTANCE.toDto(optionalNotice.get());
      Assert.assertEquals(returnedNotice.getDisplayDay(), -1);
    }

    long id = 111111;
    notificationService.delete(id);
    Optional<Notice> optionalNoticeFail = notificationRepository.findById(id);
    Assert.assertEquals(false, optionalNoticeFail.isPresent());

  }



  @Test
  public void testFindNewestNoticeInserted() {
    notificationService.save(noticeDto);
    NoticeDto returnedNotice = notificationService.findNewestNoticeInserted();
    Assert.assertNotNull(returnedNotice);
    Assert.assertEquals(noticeDto.getTitle(), returnedNotice.getTitle());
  }

  @Test
  public void testFindByTitleAndNotice() {
    noticeDto.setTitle("title");
    noticeDto.setNotice("notice");
    notificationService.save(noticeDto);
    Iterable<NoticeDto> returned = notificationService.findAll();
    Assert.assertNotNull(returned);
    Assert.assertEquals("title", returned.iterator().next().getTitle());
    Assert.assertEquals("notice", returned.iterator().next().getNotice());
  }

  @Test
  public void testSearchNotice() {
    noticeDto.setTitle("title");
    noticeDto.setNotice("notice");
    notificationService.save(noticeDto);
    List<NoticeDto> returned = notificationService.search(noticeDto.getTitle(),
            noticeDto.getNotice());
    Assert.assertNotNull(returned);
    Assert.assertEquals("title", returned.iterator().next().getTitle());
    Assert.assertEquals("notice", returned.iterator().next().getNotice());
  }

  @Test
  public void testPaginated() {
    Mockito.when(pageable.getPageSize()).thenReturn(2);
    Mockito.when(pageable.getPageNumber()).thenReturn(2);
    Page<NoticeDto> paginated = notificationService.findPaginated(pageable,
            noticeDto.getTitle(), noticeDto.getNotice());
    Assert.assertEquals(0, paginated.getTotalPages());

    Mockito.when(pageable.getPageSize()).thenReturn(1);
    Mockito.when(pageable.getPageNumber()).thenReturn(0);
    notificationService.save(noticeDto);
    Page<NoticeDto> paginate = notificationService.findPaginated(pageable,
            noticeDto.getTitle(), noticeDto.getNotice());
    Assert.assertEquals(1, paginate.getTotalPages());
  }


  @Test
  public void testFindByNoticeId() {
    notificationService.save(noticeDto);
    NoticeDto inserted = notificationService.findNewestNoticeInserted();
    NoticeDto returned = notificationService.findById(inserted.getId());
    Assert.assertNotNull(returned);
    Assert.assertEquals(noticeDto.getTitle(), returned.getTitle());
  }

  @Test
  public void testFindAll() {
    notificationService.save(noticeDto);
    Iterable<NoticeDto> inserted = notificationService.findAll();
    Assert.assertNotNull(inserted.iterator().next());
    Assert.assertEquals(inserted.iterator().next().getTitle(), noticeDto.getTitle());
    Assert.assertEquals(inserted.iterator().next().getNotice(), noticeDto.getNotice());
  }

}
