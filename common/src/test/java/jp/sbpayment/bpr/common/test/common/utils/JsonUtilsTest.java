package jp.sbpayment.bpr.common.test.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import jp.sbpayment.bpr.common.test.ApplicationConfigurationTest;
import jp.sbpayment.bpr.common.utils.JsonUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationConfigurationTest.class)
public class JsonUtilsTest {

  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  @Test
  public void testConvertObjectMapToJsonSuccess() {
    Map map = new HashMap();
    map.put("username", "admin");
    map.put("password", "12345");
    String json = JsonUtils.convert(map);
    Assert.assertEquals("{\"password\":\"12345\",\"username\":\"admin\"}", json);
  }

  @Test
  public void testConvertObjectMapToJsonFailure() {
    String json = JsonUtils.convert(new Object());
    Assert.assertNull(json);
  }

  @Test
  public void testConvertJsonToObjectSuccess() {
    String json = "{\"password\":\"12345\",\"username\":\"admin\"}";
    Map map = JsonUtils.convert(json, Map.class);
    Assert.assertEquals(2, map.size());
    Assert.assertEquals("12345", map.get("password"));
    Assert.assertEquals("admin", map.get("username"));
  }

  @Test
  public void testConvertJsonToObjectFailure() {
    Map map = JsonUtils.convert("test", Map.class);
    Assert.assertNull(map);
  }

}
