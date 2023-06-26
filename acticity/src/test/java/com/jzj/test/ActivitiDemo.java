package com.jzj.test;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;

import java.io.File;

@Slf4j
public class ActivitiDemo {
    /**
     * 测试流程部署
     */
    @Test
    public void testProcessDeployment(){
        //1.创建 ProcessEnginess
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //2.获取RepositoryService
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //3.使用service进行流程部署，定义一个流程名字，把bpmn文件和png部署到数据库中
        Deployment deploy = repositoryService.createDeployment()
                .name("出差申请流程")
                .addClasspathResource("bpmn"+ File.separator+"chuchai.bpmn")
                .addClasspathResource("bpmn"+File.separator+"chuchai.png")
                .deploy();
        //4.输出整个部署信息
        log.info("流程部署id:{}", deploy.getId());
        log.info("流程部署name:{}", deploy.getName());

    }
}
