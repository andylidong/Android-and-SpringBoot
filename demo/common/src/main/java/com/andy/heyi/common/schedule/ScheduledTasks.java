package com.andy.heyi.common.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName ScheduledTasks
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2018/8/2$ 5:27 PM$
 * @UpdateUser: lidong
 * @UpdateDate: 2018/8/2$ 5:27 PM$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/

@Component
@EnableScheduling
@Slf4j
public class ScheduledTasks {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );

    @Scheduled(fixedRate = 1000000)
    public void reportCurrentTime() {
        log.info( "当前时间：" + dateFormat.format( new Date() ) );
    }

}
