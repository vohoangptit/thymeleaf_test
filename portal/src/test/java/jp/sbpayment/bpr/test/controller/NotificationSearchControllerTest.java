package jp.sbpayment.bpr.test.controller;

import java.util.Date;
import jp.sbpayment.bpr.bl.dto.NoticeDto;
import jp.sbpayment.bpr.bl.dto.UserDto;
import jp.sbpayment.bpr.controller.NotificationSearchController;
import jp.sbpayment.bpr.services.NoticeManagementService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

  @MockBean
  private Page<NoticeDto> page;

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
  public void showSearchNoticeSuccessful() throws Exception {
    Mockito.when(noticeManagementService.findPaginated(Mockito.any(PageRequest.class),
            Mockito.any(String.class), Mockito.any(String.class)))
            .thenReturn(page);

    mockMvc.perform(MockMvcRequestBuilders.get("/notice/search")
            .param("title", "title").param("notice", "notice")
            .param("page", "1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("pages/NoticeSearchList"))
            .andReturn();

    Mockito.when(page.getTotalPages()).thenReturn(3);

    mockMvc.perform(MockMvcRequestBuilders.get("/notice/search")
            .param("title", "title").param("notice", "notice")
            .param("page", "1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("pages/NoticeSearchList"))
            .andReturn();

    mockMvc.perform(MockMvcRequestBuilders.get("/notice/search")
            .param("title", "title").param("notice", "notice")
            .param("page", "4"))
            .andExpect(MockMvcResultMatchers.status().isFound())
            .andExpect(MockMvcResultMatchers
                    .redirectedUrl("/notice/search?title=title&notice=notice&page=3"))
            .andReturn();
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
