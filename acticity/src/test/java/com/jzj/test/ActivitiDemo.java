package com.jzj.test;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
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

    /**
     * 启动流程实例
     * act_hi_actinst 流程实例执行历史
     * act_hi_identityLink 流程参与者的历史信息
     * act_hi_procinst 流程实例的历史信息
     * act_fi_taskinst  任务的历史信息
     * act_ru_execution 流程执行的信息
     * act_re_identityLink 流程参与者信息
     * act_ru_task 任务信息
     *
     */
    @Test
    public void startProcess(){
        //1.创建processEngine（流程引擎）
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //2.获取runtimeservice
        RuntimeService runtimeService = processEngine.getRuntimeService();
        //3.根据流程定义的id启动流程
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("chuchai");
        //4.输出内容
        log.info("流程定义id:{}", processInstance.getProcessDefinitionId());
        log.info("流程实例id:{}", processInstance.getId());
        log.info("当前活动id:{}", processInstance.getActivityId());


    }
}
