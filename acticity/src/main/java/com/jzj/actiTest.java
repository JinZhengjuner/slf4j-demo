package com.jzj;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class actiTest {
//    public static final Logger log = LogManager.getLogger(actiTest.class);
    @Test
    public void test(){
        log.debug("debug");
        log.error("xx");
        log.info("xxx");
        log.trace("trace");
    }
}
