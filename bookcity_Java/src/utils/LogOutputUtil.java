package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogOutputUtil {
    public static Logger logger = LoggerFactory.getLogger(LogOutputUtil.class);

    public static void main(String[] args) {
        logger.trace("......追踪......");
        logger.debug("......输出......");
        logger.info("......提示......");
        logger.warn("......警告......");
        logger.error("......错误......");
    }
}
