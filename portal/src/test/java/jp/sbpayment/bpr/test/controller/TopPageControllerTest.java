package jp.sbpayment.bpr.test.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import jp.sbpayment.bpr.controller.TopPageController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(value = TopPageController.class, secure = false)
public class TopPageControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private Principal principal;

  @MockBean
  private SecurityContext securityContext;

  @MockBean
  private MockHttpSession session;

  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void loadHomeIndexPage() throws Exception {
    Collection<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
    UsernamePasswordAuthenticationToken authentication =
            new UsernamePasswordAuthenticationToken("admin", "admin", authorities);
    Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
    SecurityContextHolder.setContext(securityContext);
    String[] pathIndex = {"", "/", "/top-page"};
    for (String path : pathIndex) {
      mockMvc.perform(MockMvcRequestBuilders.get(path).session(session))
              .andExpect(MockMvcResultMatchers.status().isOk())
              .andExpect(MockMvcResultMatchers.view().name("pages/TopPage"))
              .andReturn();
    }
  }

}
