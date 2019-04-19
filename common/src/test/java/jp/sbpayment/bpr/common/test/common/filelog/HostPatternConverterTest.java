package jp.sbpayment.bpr.common.test.common.filelog;

import java.net.InetAddress;
import java.net.UnknownHostException;
import jp.sbpayment.bpr.common.filelog.HostPatternConverter;
import jp.sbpayment.bpr.common.test.ApplicationConfigurationTest;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.impl.Log4jLogEvent;
import org.apache.logging.log4j.util.ReflectionUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationConfigurationTest.class)
public class HostPatternConverterTest {

  @Mock
  private HostPatternConverter hostPatternConverter;

  @Test
  public void testGetHostInfoSuccess() throws UnknownHostException {
    String host = ReflectionTestUtils.invokeMethod(hostPatternConverter, "getHostInfo");
    Assert.assertEquals(InetAddress.getLocalHost().getHostAddress(), host);
  }

  @Test
  public void testFormat() throws UnknownHostException {
    LogEvent event = new Log4jLogEvent();
    StringBuilder toAppendTo = new StringBuilder();
    HostPatternConverter.INSTANCE.format(event, toAppendTo);
    Assert.assertEquals(InetAddress.getLocalHost().getHostAddress(), toAppendTo.toString());
  }
}
