package jp.sbpayment.bpr.test.services;

import jp.sbpayment.bpr.bl.dto.NoticeDto;
import jp.sbpayment.bpr.services.NoticeManagementService;
import jp.sbpayment.bpr.test.PortalApplicationTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PortalApplicationTest.class)
public class NoticeManagementServiceTest {

  @MockBean
  private NoticeManagementService noticeManagementService;

  private PodamFactory podam = new PodamFactoryImpl();

  private NoticeDto noticeDto;

  @Before
  public void setUp() {
    noticeDto = podam.manufacturePojoWithFullData(NoticeDto.class);
  }

  @Test
  public void testSaveSuccess() {
    noticeManagementService.save(noticeDto);

    NoticeDto returned = noticeManagementService.findNewestNoticeInserted();
  }
}
