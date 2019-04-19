package jp.sbpayment.bpr.config;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.Executor;
import jp.sbpayment.bpr.log.LogInterceptor;
import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

  @Bean
  public LayoutDialect layoutDialect() {
    return new LayoutDialect();
  }

  /**
   * Task Executor.
   */
  @Bean
  public Executor taskExecutor() throws IOException {
    Properties properties = new Properties();
    properties.load(getClass().getClassLoader().getResourceAsStream("application.properties"));
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(Integer.parseInt(properties.getProperty("executor.corepoolsize")));
    executor.setMaxPoolSize(Integer.parseInt(properties.getProperty("executor.maxpoolsize")));
    executor.setQueueCapacity(Integer.parseInt(properties.getProperty("executor.queuecapacity")));
    executor.setThreadNamePrefix(properties.getProperty("executor.threadnameprefix"));
    executor.initialize();
    return executor;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new LogInterceptor());
  }

}