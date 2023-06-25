package com.jzj;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class Log4j2Test {
    public static final Logger log = LogManager.getLogger(Log4j2Test.class);

    @Test
    public void test01() {
        log.fatal("fatal");
        log.error("error");
        log.warn("warn");
        log.info("info");
        log.debug("debug");//默认级别
        log.trace("trace");
    }

    @Test
    public void test02() {
        String jsonStr = "{\"duration\":1309,\"id\":0e41910980359412,\"kind\":\"SERVER\",\"localEndpoint\":{\"serviceName\":\"online-stock-microservice\",\"ipv4\":\"172.20.213.218\"},\"name\":\"n" +
                "et.techfuser.grpc.onlinestockfacade.service.stockservice/findone\",\"parentId\":\"6348642f883430de\",\"shared\":true,\"traceId\":\"6348642f883430de\"}";

        String jsonStr2 = "{\"duration\":2028,\"id\":\"93d7e39318058843\",\"kind\":\"SERVER\",\"localEndpoint\":{\"serviceName\":\"online-stock-microservice\",\"ipv4\":\"172.20.243.169\"},\"name\":\"net.techfuser.grpc.onlinestockfacade.service.stockservice/" +
                "findone\",\"parentId\":0e83180660718231,\"shared\":true,\"timestamp\":1687233072381034,\"traceId\":\"ab71ed8f9af13d9a\"}";
        JSONObject jsonObject = JSON.parseObject(jsonStr2);
    }
}
