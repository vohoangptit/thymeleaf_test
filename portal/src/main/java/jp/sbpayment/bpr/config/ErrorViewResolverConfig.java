package jp.sbpayment.bpr.config;

import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

@Configuration
@AllArgsConstructor
public class ErrorViewResolverConfig implements ErrorViewResolver {

  private final MessageSource messageSource;

  @Override
  public ModelAndView resolveErrorView(HttpServletRequest req,
                                       HttpStatus status,
                                       Map<String, Object> model) {
    // Otherwise setup and send the user to a default error-view.
    ModelAndView mav = new ModelAndView();
    mav.addObject("url", req.getRequestURL());
    mav.addObject("title", messageSource.getMessage("Error.label." + status.value() + ".title", 
        null,
        Locale.JAPAN));
    mav.addObject("message", "入力したURLが当サイトのページと一致しません ");
    mav.addObject("status", status.value());
    mav.setViewName("pages/ErrorPage");
    return mav;
  }

}
