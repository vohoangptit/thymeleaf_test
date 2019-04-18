package jp.sbpayment.bpr.log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jp.sbpayment.bpr.common.filelog.FileLogCommon;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * LogInterceptor.
 */
public class LogInterceptor extends HandlerInterceptorAdapter {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
          throws Exception {
    FileLogCommon.getInstance().info("\n-------- LogInterception.preHandle --- ");
    FileLogCommon.getInstance().info("Request URL: " + request.getRequestURL());
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                         ModelAndView modelAndView) throws Exception {
    FileLogCommon.getInstance().info("\n-------- LogInterception.postHandle --- ");
    FileLogCommon.getInstance().info("Request URL: " + request.getRequestURL());
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                              Object handler, Exception ex) throws Exception {
    FileLogCommon.getInstance().info("\n-------- LogInterception.afterCompletion --- ");
    FileLogCommon.getInstance().info("Request URL: " + request.getRequestURL());
  }

}
