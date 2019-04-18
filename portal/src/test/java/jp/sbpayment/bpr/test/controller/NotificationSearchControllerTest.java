package jp.sbpayment.bpr.test.controller;

import java.util.Date;
import jp.sbpayment.bpr.bl.dto.NoticeDto;
import jp.sbpayment.bpr.controller.NotificationSearchController;
import jp.sbpayment.bpr.services.NoticeManagementService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(value = NotificationSearchController.class, secure = false)
public class NotificationSearchControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private NoticeManagementService noticeManagementService;

  private PodamFactory podam = new PodamFactoryImpl();

  private NoticeDto noticeDto;

  /**
   * Setup before running test case.
   */
  @Before
  public void setUp() {
    noticeDto = podam.manufacturePojoWithFullData(NoticeDto.class);
    noticeDto.setAnnounceDate(new Date());
    noticeDto.setDisplayDay(2);
    noticeDto.setDisplayTarget(2);
  }

  @Test
  public void showDetailNotice() throws Exception {
    Mockito.when(noticeManagementService.showDetail(noticeDto.getId())).thenReturn(noticeDto);

    mockMvc.perform(MockMvcRequestBuilders.get("/notice/{notice_id}/detail", noticeDto.getId()))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("pages/NoticeDetail"))
            .andReturn();
  }

  @Test
  public void deleteNotice() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/notice/{notice_id}/delete", noticeDto.getId()))
            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
  }

}
