package com.andersen.banking.meeting_impl.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;

@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "scheduling.enabled", matchIfMissing = true)
public class SchedulerConfig {

    @Bean
    public TaskScheduler taskScheduler() {
           Logger logger = LoggerFactory.getLogger(getClass());
           ConcurrentTaskScheduler scheduler = new ConcurrentTaskScheduler();
           scheduler.setErrorHandler(t -> logger.error("Exception in @Scheduled task. ", t));

           return scheduler;
    }
}
