package com.jzj.bootlog;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BootLogApplicationTests {

    public static final Logger log = LoggerFactory.getLogger(BootLogApplicationTests.class);

    @Test
    void contextLoads() {
        log.error("error");
        log.warn("warn");
        log.info("info");//默认级别
        log.debug("debug");
        log.trace("trace");

    }

}
