package jp.sbpayment.bpr.test.controller;

import java.security.Principal;
import jp.sbpayment.bpr.controller.LoginController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(value = LoginController.class, secure = false)
public class LoginControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void showLogin() throws Exception {

    mockMvc.perform(MockMvcRequestBuilders.get("/login").param("error", "abc"))
            .andExpect(MockMvcResultMatchers.model().attribute("error", "ユーザー名とパスワードは無効ではありません。"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("pages/Login"))
            .andReturn();

    mockMvc.perform(MockMvcRequestBuilders.get("/login").principal(Mockito.mock(Principal.class)))
            .andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("error"))
            .andExpect(MockMvcResultMatchers.status().isFound())
            .andExpect(MockMvcResultMatchers.redirectedUrl("/"))
            .andReturn();
  }

}
