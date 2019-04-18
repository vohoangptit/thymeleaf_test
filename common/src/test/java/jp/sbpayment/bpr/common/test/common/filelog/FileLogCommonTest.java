package jp.sbpayment.bpr.common.test.common.filelog;

import jp.sbpayment.bpr.common.filelog.FileLogCommon;
import jp.sbpayment.bpr.common.test.ApplicationConfigurationTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationConfigurationTest.class)
public class FileLogCommonTest {

  FileLogCommon fileLogCommon;

  @Before
  public void setUp() {
    fileLogCommon = FileLogCommon.getInstance();
  }

  @Test
  public void testFileLogCommonInstance() {
    FileLogCommon fileLog = FileLogCommon.getInstance();
    Assert.assertNotNull(fileLog);
    Assert.assertTrue(fileLog instanceof FileLogCommon);
  }

}
