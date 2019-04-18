package jp.sbpayment.bpr.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import javax.servlet.http.HttpSession;
import jp.sbpayment.bpr.common.utils.JsonUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TopPageController {

  /**
   * show index.
   */
  @RequestMapping(value = {"", "/", "/top-page"})
  public String index(HttpSession session) throws IOException {
    session.setAttribute("username",
            SecurityContextHolder.getContext().getAuthentication().getName());
    session.setAttribute("role", SecurityContextHolder.getContext()
            .getAuthentication().getAuthorities().iterator().next().getAuthority());

    // use file properties to show all error messages
    BufferedReader file = new BufferedReader(new InputStreamReader(
            new FileInputStream(getClass().getClassLoader()
                    .getResource("messages.properties").getFile()), "UTF8"));
    Properties properties = new Properties();
    properties.load(file);
    session.setAttribute("jsonMessages", JsonUtils.convert(properties));
    return "pages/TopPage";
  }

}