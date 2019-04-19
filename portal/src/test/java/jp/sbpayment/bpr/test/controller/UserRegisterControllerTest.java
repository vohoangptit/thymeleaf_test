package jp.sbpayment.bpr.test.controller;

import jp.sbpayment.bpr.bl.dto.UserDto;
import jp.sbpayment.bpr.common.utils.JsonUtils;
import jp.sbpayment.bpr.controller.UserRegisterController;
import jp.sbpayment.bpr.services.UserManagementService;
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
@WebMvcTest(value = UserRegisterController.class, secure = false)
public class UserRegisterControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserManagementService userManagementService;

  private PodamFactory podam = new PodamFactoryImpl();

  private UserDto userDto;

  // private RoleDto roleDto;

  /**
   * Setup before running test case.
   */
  @Before
  public void setUp() {
    userDto = podam.manufacturePojoWithFullData(UserDto.class);
    userDto.setEmail("test@mail.com");
  }

  @Test
  public void showUserRegister() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/user/regist"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("pages/UserRegist")).andReturn();
  }

  @Test
  public void testSaveUserSuccessful() throws Exception {
    String json = JsonUtils.convert(userDto);
    mockMvc.perform(MockMvcRequestBuilders.post("/user/save")
            .content(json)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
  }

  @Test
  public void testUpdateUserSuccessful() throws Exception {
    Mockito.when(userManagementService.findUserByUserName(userDto.getUsername()))
            .thenReturn(userDto);

    String json = JsonUtils.convert(userDto);
    mockMvc.perform(MockMvcRequestBuilders.post("/user/save")
            .content(json)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

  }

}
