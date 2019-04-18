package jp.sbpayment.bpr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableCaching
@EnableAsync
public class PortalApplication extends SpringBootServletInitializer {
  public static void main(String[] args) {
    SpringApplication.run(PortalApplication.class, args);
  }
}   
