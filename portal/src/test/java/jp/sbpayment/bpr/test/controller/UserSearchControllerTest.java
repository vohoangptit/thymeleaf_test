package jp.sbpayment.bpr.test.controller;

import jp.sbpayment.bpr.bl.dto.UserDto;
import jp.sbpayment.bpr.controller.UserSearchController;
import jp.sbpayment.bpr.services.UserManagementService;
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
@WebMvcTest(value = UserSearchController.class, secure = false)
public class UserSearchControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserManagementService userManagementService;

  @MockBean
  private Page<UserDto> page;

  private UserDto userDto;

  private PodamFactory podam = new PodamFactoryImpl();



  /**
   * Setup before running test case.
   */
  @Before
  public void setUp() {
    userDto = podam.manufacturePojoWithFullData(UserDto.class);
  }

  @Test
  public void showSearchUserSuccessful() throws Exception {
    Mockito.when(userManagementService.findPaginated(Mockito.any(PageRequest.class),
            Mockito.any(String.class), Mockito.any(String.class)))
            .thenReturn(page);

    mockMvc.perform(MockMvcRequestBuilders.get("/user/search")
            .param("name", "name").param("email", "email")
            .param("page", "1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("pages/UserSearchList"))
            .andReturn();

    Mockito.when(page.getTotalPages()).thenReturn(3);

    mockMvc.perform(MockMvcRequestBuilders.get("/user/search")
            .param("name", "name").param("email", "email")
            .param("page", "1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("pages/UserSearchList"))
            .andReturn();

    mockMvc.perform(MockMvcRequestBuilders.get("/user/search")
            .param("name", "name").param("email", "email")
            .param("page", "4"))
            .andExpect(MockMvcResultMatchers.status().isFound())
            .andExpect(MockMvcResultMatchers
                    .redirectedUrl("/user/search?name=name&email=email&page=3"))
            .andReturn();
  }

  @Test
  public void showDetailUserSuccessful() throws Exception {
    Mockito.when(userManagementService.findUserByUserName(userDto.getUsername()))
            .thenReturn(userDto);
    mockMvc.perform(MockMvcRequestBuilders.get("/user/{username}/detail", userDto.getUsername()))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("pages/UserDetail"))
            .andReturn();
  }

}
