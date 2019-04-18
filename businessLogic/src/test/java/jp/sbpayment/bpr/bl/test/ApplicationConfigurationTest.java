package jp.sbpayment.bpr.bl.test;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan({"jp.sbpayment*"})
@EntityScan("jp.sbpayment*")
@EnableJpaRepositories("jp.sbpayment*")
@EnableAutoConfiguration
public class ApplicationConfigurationTest {
}
