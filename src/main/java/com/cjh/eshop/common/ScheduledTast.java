package com.cjh.eshop.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务
 * @author 陈建杭
 *
 */
@Component
public class ScheduledTast {
    
    private static Logger logger = LoggerFactory.getLogger(ScheduledTast.class);
    
    /*
    @Scheduled(fixedDelay = 5000)  
    void doSomethingWithDelay(){  
        System.out.println("I'm doing with delay now!");  
    }  
      
    @Scheduled(fixedRate = 5000)  
    void doSomethingWithRate(){  
        System.out.println("I'm doing with rate now!");  
    }  
    */
    // 每天 00:00 时做一次数据库备份
    @Scheduled(cron = "0 0 0 * * *")  
    void doSomethingWith() { 
        logger.info("开始备份数据库");
        
        // TODO 备份数据库
        
        logger.info("数据库备份完成");
    }  
}
