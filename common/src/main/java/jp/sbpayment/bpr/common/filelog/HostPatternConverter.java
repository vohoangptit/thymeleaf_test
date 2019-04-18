package jp.sbpayment.bpr.common.filelog;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.pattern.ConverterKeys;
import org.apache.logging.log4j.core.pattern.LogEventPatternConverter;

@Plugin(name = "HostConverter", category = "Converter")
@ConverterKeys({"host"})
public class HostPatternConverter extends LogEventPatternConverter {

  private static final HostPatternConverter INSTANCE = new HostPatternConverter();

  private HostPatternConverter() {
    super("host", "host");
  }

  public static HostPatternConverter newInstance(final String[] options) {
    return INSTANCE;
  }

  private String getHostInfo() {
    try {
      return InetAddress.getLocalHost().getHostAddress();
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
    return "";
  }

  @Override
  public void format(LogEvent event, StringBuilder toAppendTo) {
    toAppendTo.append(getHostInfo());
  }

}
