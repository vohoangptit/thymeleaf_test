package jp.sbpayment.bpr.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jp.sbpayment.bpr.bl.dto.NoticeDto;
import jp.sbpayment.bpr.bl.dto.NoticeMailDto;
import jp.sbpayment.bpr.bl.dto.NoticeTemplateDto;
import jp.sbpayment.bpr.controller.NotificationRegisterController;
import jp.sbpayment.bpr.services.NoticeManagementService;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(value = NotificationRegisterController.class, secure = false)
public class NotificationRegisterControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private NoticeManagementService noticeManagementService;

  @Autowired
  private ObjectMapper objectMapper;

  private PodamFactory podam = new PodamFactoryImpl();

  private NoticeTemplateDto noticeTemplateDto;

  private NoticeDto noticeDto;

  private NoticeMailDto noticeMailDto;

  /**
   * Setup before running test case.
   */
  @Before
  public void setUp() {
    noticeTemplateDto = podam.manufacturePojoWithFullData(NoticeTemplateDto.class);
    noticeDto = podam.manufacturePojoWithFullData(NoticeDto.class);
    noticeMailDto = podam.manufacturePojoWithFullData(NoticeMailDto.class);
  }

  @Test
  public void showNoticeRegister() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/notice/create"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("pages/NoticeRegist")).andReturn();
  }

  @Test
  public void saveNotice() throws Exception {
    String jsonNotice = objectMapper.writeValueAsString(noticeDto);

    mockMvc.perform(MockMvcRequestBuilders.post("/notice/save")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonNotice).accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
  }

  @Test
  public void saveNoticeTemplate() throws Exception {
    String jsonNotice = objectMapper.writeValueAsString(noticeDto);

    mockMvc.perform(MockMvcRequestBuilders.post("/notice-template/save/{templateName}",
            noticeTemplateDto.getTemplateName())
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonNotice).accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
  }

  @Test
  public void updateNoticeTemplate() throws Exception {
    Mockito.when(noticeManagementService.findByTemplateName(noticeTemplateDto.getTemplateName()
            )).thenReturn(noticeTemplateDto);

    String jsonNotice = objectMapper.writeValueAsString(noticeDto);

    mockMvc.perform(MockMvcRequestBuilders.post("/notice-template/save/{templateName}",
            noticeTemplateDto.getTemplateName())
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonNotice).accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
  }

  @Test
  public void findByTemplateName() throws Exception {
    String jsonNoticeTemplate = objectMapper.writeValueAsString(noticeTemplateDto);

    Mockito.when(noticeManagementService.findByTemplateName(
            noticeTemplateDto.getTemplateName()))
            .thenReturn(noticeTemplateDto);

    mockMvc.perform(MockMvcRequestBuilders.get("/notice-template/"
            + noticeTemplateDto.getTemplateName()).accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().string(jsonNoticeTemplate))
            .andReturn();
  }

  @Test
  public void saveNoticeMail() throws Exception {
    String jsonNoticeMail = objectMapper.writeValueAsString(noticeMailDto);

    mockMvc.perform(MockMvcRequestBuilders.post("/notice-mail/save")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonNoticeMail).accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
  }

}
