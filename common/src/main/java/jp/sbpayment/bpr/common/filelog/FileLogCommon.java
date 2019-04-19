package jp.sbpayment.bpr.common.filelog;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileLogCommon {
  
  public static FileLogCommon instance;
  public static Logger logger;

  /**
   * Single instance logger.
   *
   * @return FileLogCommon
   */
  public static FileLogCommon getInstance() {
    if (instance == null) {
      instance = new FileLogCommon();
    }
    logger = LogManager.getLogger();
    return instance;
  }

  /**
   * Log Debug.
   *
   * @param message log message
   */
  public void debug(Class<?> clazz, String message) {
    logger = LogManager.getLogger(clazz);
    logger.debug(message);
  }

  /**
   * Log Info.
   *
   * @param message log message
   */
  public void info(String message) {
    logger.info(message);
  }

  /**
   * Log Warn.
   *
   * @param message log message
   */
  public void warn(String message) {
    logger.warn(message);
  }

  /**
   * Log Error.
   *
   * @param message log message
   */
  public void error(String message) {
    logger.error(message);
  }

}
