package jp.sbpayment.bpr.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public class JsonUtils {

  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  /**
   * Convert Object map to Json String.
   * 
   * @param object object
   * @return String Json string
   */
  public static String convert(Object object) {
    try {
      return OBJECT_MAPPER.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      return null;
    }
  }
  
  /**
   * Convert JSON to Object.
   * @param <T> class to convert
   * @param json JSON string
   * @param clazz class to convert
   * @return object result
   */
  public static <T> T convert(String json, Class<T> clazz) {
    
    try {
      return OBJECT_MAPPER.readValue(json, clazz);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

}
