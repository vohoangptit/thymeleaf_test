package jp.sbpayment.bpr.common.test.common.response;

import jp.sbpayment.bpr.common.response.CustomResponse;
import jp.sbpayment.bpr.common.test.ApplicationConfigurationTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationConfigurationTest.class)
public class CustomerResponseTest {

  @Test
  public void testGetMessage() {
    CustomResponse customResponse = new CustomResponse("message");
    String message = customResponse.getMessage();
    Assert.assertEquals("message", message);
  }

  @Test
  public void testSetMessage() {
    CustomResponse customResponse = new CustomResponse();
    customResponse.setMessage("message");
    String message = customResponse.getMessage();
    Assert.assertEquals("message", message);
  }

}
