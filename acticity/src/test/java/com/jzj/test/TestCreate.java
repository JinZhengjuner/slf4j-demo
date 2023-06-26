package com.jzj.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.junit.Test;

public class TestCreate {

    /**
     * 使用activity默认方式创建mysql的表
     */
    @Test
    public void testCreatTable(){
        //使用activity提供的工具类
        //getDefaultProcessEngine默认从resouce下读取名字为activity.cfg.xml的文件
        //创建processEngine时，就会创建mysql表
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        System.out.println(processEngine);
    }
}
