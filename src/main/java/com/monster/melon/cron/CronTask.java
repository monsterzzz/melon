package com.monster.melon.cron;


import com.monster.melon.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

//@Configuration
//@EnableScheduling
@Slf4j
public class CronTask {

    private final EventService eventService;

    public CronTask(EventService eventService) {
        this.eventService = eventService;
    }

    @Scheduled(cron = "0/5 * * * * *")
    public void logOutput(){

        //eventService.updateViewNum();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        log.info(format.format(new Date()));
    }
}
