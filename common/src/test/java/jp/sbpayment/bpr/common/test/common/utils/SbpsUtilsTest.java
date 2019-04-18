package jp.sbpayment.bpr.common.test.common.utils;

import java.util.ArrayList;
import jp.sbpayment.bpr.common.test.ApplicationConfigurationTest;
import jp.sbpayment.bpr.common.utils.SbpsUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationConfigurationTest.class)
public class SbpsUtilsTest {

  @Test
  public void testCalcTheSequencePaginationNumbersRendering_CurrentPageLessThanPageSize() {
    ArrayList<Integer> result = SbpsUtils.calcTheSequencePaginationNumbersRendering(0, 1, 1);
    Assert.assertEquals(2, result.size());
    Assert.assertEquals(2, result.get(0).intValue());
    Assert.assertEquals(1, result.get(1).intValue());
  }

  @Test
  public void testCalcTheSequencePaginationNumbersRendering_CurrentPageEqualPageSize() {
    ArrayList<Integer> result = SbpsUtils.calcTheSequencePaginationNumbersRendering(1, 1, 1);
    Assert.assertEquals(2, result.size());
    Assert.assertEquals(-3, result.get(0).intValue());
    Assert.assertEquals(1, result.get(1).intValue());
  }

  @Test
  public void testCalcTheSequencePaginationNumbersRendering_CurrentPageGreatThanPageSize() {
    ArrayList<Integer> result = SbpsUtils.calcTheSequencePaginationNumbersRendering(5, 10, 1);
    Assert.assertEquals(2, result.size());
    Assert.assertEquals(4, result.get(0).intValue());
    Assert.assertEquals(6, result.get(1).intValue());
  }

}
