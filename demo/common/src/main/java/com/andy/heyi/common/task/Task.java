package com.andy.heyi.common.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName Task
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2018/8/2$ 5:31 PM$
 * @UpdateUser: lidong
 * @UpdateDate: 2018/8/2$ 5:31 PM$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
// 配置任务
@Configuration
// 开始异步支持
@EnableAsync
// log输出
@Slf4j
public class Task {

    /**
     * 自定义异步线程池
     * 如果没有这个方法，则使用默认的线程池
     *
     * @return
     */
    @Bean
    public AsyncTaskExecutor taskExecutor() {
        log.info( "Task.taskExecutor == " );
        // 设置异步线程池
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix( "Anno-Executor" );
        executor.setMaxPoolSize( 10 );
        // 设置拒绝策略
        executor.setRejectedExecutionHandler( (r, executor1) -> log.info( "AsyncTaskExecutor.rejectedExecution == " ) );
        // 使用预定义的异常处理类
        executor.setRejectedExecutionHandler( new ThreadPoolExecutor.CallerRunsPolicy() );
        return executor;
    }

    @Async
    public Future<String> doTaskOne() {
        log.info( "Task.doTaskOne，开始做任务一" );
        try {
            long start = System.currentTimeMillis();
            long end = System.currentTimeMillis();
            log.info( "Task.doTaskOne，完成任务一，耗时：" + (end - start) + "毫秒" );
            return new AsyncResult<>( "任务一完成" );
        } catch (Exception e) {
            log.info( "Task.doTaskOne，Exception = " + e.getMessage() );
            return null;
        }
    }

    @Async
    public Future<String> doTaskTwo() {
        log.info( "Task.doTaskTwo，开始做任务二" );
        try {
            long start = System.currentTimeMillis();
            long end = System.currentTimeMillis();
            log.info( "Task.doTaskTwo，完成任务二，耗时：" + (end - start) + "毫秒" );
            return new AsyncResult<>( "任务二完成" );
        } catch (Exception e) {
            log.info( "Task.doTaskTwo，Exception = " + e.getMessage() );
            return null;
        }
    }

    @Async
    public Future<String> doTaskThree() {
        log.info( "Task.doTaskThree，开始做任务三" );
        try {
            long start = System.currentTimeMillis();
            long end = System.currentTimeMillis();
            log.info( "Task.doTaskThree，完成任务三，耗时：" + (end - start) + "毫秒" );
            return new AsyncResult<>( "任务三完成" );
        } catch (Exception e) {
            log.info( "Task.doTaskThree，Exception = " + e.getMessage() );
            return null;
        }
    }
}
