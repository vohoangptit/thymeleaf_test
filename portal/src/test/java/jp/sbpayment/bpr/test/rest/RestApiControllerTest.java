package jp.sbpayment.bpr.test.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import jp.sbpayment.bpr.api.RestApiController;
import jp.sbpayment.bpr.bl.dto.NoticeDto;
import jp.sbpayment.bpr.services.NoticeManagementService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(value = RestApiController.class, secure = false)
public class RestApiControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private NoticeManagementService noticeManagementService;

  @Autowired
  private ObjectMapper objectMapper;

  private PodamFactory podam;

  private NoticeDto noticeDto;

  private DateFormat dateFormat;

  /**
   * Setup before running test case.
   */
  @Before
  public void setUp() {

    dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    podam = new PodamFactoryImpl();
    noticeDto = podam.manufacturePojoWithFullData(NoticeDto.class);
    noticeDto.setId(1);
    noticeDto.setAnnounceDate(new Date());
    noticeDto.setDisplayDay(2);
    noticeDto.setDisplayTarget(2);
  }

  @Test
  public void searchNoticeSuccess() throws Exception {
    Mockito.when(noticeManagementService.showDetail(noticeDto.getId())).thenReturn(noticeDto);
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/notice/1")
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();

    NoticeDto responseNotice = fromJsonResult(mvcResult, NoticeDto.class);
    Assert.assertEquals(200, mvcResult.getResponse().getStatus());
    Assert.assertEquals(noticeDto.getId(), responseNotice.getId());
    Assert.assertEquals(noticeDto.getTitle(), responseNotice.getTitle());
    Assert.assertEquals(noticeDto.getNotice(), responseNotice.getNotice());
    Assert.assertEquals(dateFormat.format(noticeDto.getAnnounceDate()),
            dateFormat.format(responseNotice.getAnnounceDate()));
    Assert.assertEquals(noticeDto.getDisplayDay(), responseNotice.getDisplayDay());
  }

  @Test
  public void searchNoticeBadRequest() throws Exception {
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/notice/-1")
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    Assert.assertEquals(200, mvcResult.getResponse().getStatus());
  }


  <T> T fromJsonResult(MvcResult result,
                       Class<T> className) throws Exception {
    return objectMapper.readValue(
            result.getResponse().getContentAsString(),
            className);
  }

}
