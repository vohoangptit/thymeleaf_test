package jp.sbpayment.bpr.common.test.common.filelog;

import jp.sbpayment.bpr.common.filelog.FileLogCommon;
import jp.sbpayment.bpr.common.test.ApplicationConfigurationTest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationConfigurationTest.class)
public class FileLogCommonTest {

  private FileLogCommon fileLogCommon;

  @Test
  public void testGetInstance() {
    FileLogCommon fileLog = FileLogCommon.getInstance();
    Assert.assertEquals(FileLogCommon.instance, fileLog);
    Assert.assertEquals("jp.sbpayment.bpr.common.filelog.FileLogCommon",
            FileLogCommon.logger.getName());
  }

  @Test
  public void testDebug() {
    fileLogCommon = FileLogCommon.getInstance();
    fileLogCommon.debug(FileLogCommon.class, "message");
    Assert.assertEquals("jp.sbpayment.bpr.common.filelog.FileLogCommon",
            FileLogCommon.logger.getName());
    Assert.assertEquals(Level.DEBUG, FileLogCommon.logger.getLevel());
  }

  @Test
  public void testInfo() {
    fileLogCommon = FileLogCommon.getInstance();
    fileLogCommon.info("message");
    Assert.assertEquals("jp.sbpayment.bpr.common.filelog.FileLogCommon",
            FileLogCommon.logger.getName());
  }

  @Test
  public void testWarn() {
    fileLogCommon = FileLogCommon.getInstance();
    fileLogCommon.warn("message");
    Assert.assertEquals("jp.sbpayment.bpr.common.filelog.FileLogCommon",
            FileLogCommon.logger.getName());
  }

  @Test
  public void testError() {
    fileLogCommon = FileLogCommon.getInstance();
    fileLogCommon.error("message");
    Assert.assertEquals("jp.sbpayment.bpr.common.filelog.FileLogCommon",
            FileLogCommon.logger.getName());
  }
}
