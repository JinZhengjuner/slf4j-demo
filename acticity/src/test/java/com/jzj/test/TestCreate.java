package com.jzj.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.junit.Test;

public class TestCreate {


    /**
     * 一般创建流程引擎方式
     */
    @Test
    public void testNormal(){
        ProcessEngineConfiguration configurationFromResource = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activity.cfg.xml");
        //获取流程引擎对象
        ProcessEngine processEngine = configurationFromResource.buildProcessEngine();

    }

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
