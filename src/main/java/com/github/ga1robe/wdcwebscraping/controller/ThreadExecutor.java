package com.github.ga1robe.wdcwebscraping.controller;

import com.github.ga1robe.wdcwebscraping.WdcWebScrapingApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import com.github.ga1robe.wdcwebscraping.service.ThreadService;

@Component
public class ThreadExecutor {

    private static final Logger log = LoggerFactory.getLogger(ThreadExecutor.class);

    @Autowired
    private TaskExecutor taskExecutor;
    @Autowired
    private ApplicationContext applicationContext;

    private Boolean debug = true;

    @EventListener(ApplicationReadyEvent.class)
    public void atStartup() {
        ThreadService threadService = applicationContext.getBean(ThreadService.class);
        taskExecutor.execute(threadService);
        if (debug) {
            log.info("###### Startup ok ######");
        }
    }
}
