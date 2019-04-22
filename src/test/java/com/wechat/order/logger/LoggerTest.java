package com.wechat.order.logger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LoggerTest {

    private final Logger logger = LoggerFactory.getLogger(LoggerTest.class);
    /*
    * slf4j日志框架的默认级别是info,info级别之上的默认不会打印，如debug*/
    @Test
    public void logTest() {
        logger.error("first error ......");
        logger.info("this is first logger ......");
        logger.debug("first debug ......");
    }
}
