package com.andy.heyi;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


// Spring Boot 应用的标识
@SpringBootApplication(scanBasePackageClasses = {Scanner.class})
// 注解用于Spring JPA的代码配置,用于取代xml形式的配置文件
@EnableJpaRepositories(basePackageClasses = {Scanner.class})
// 注解用于Spring Mongodb的代码配置,用于取代xml形式的配置文件
@EnableMongoRepositories(basePackageClasses = {Scanner.class})
// 启动事务注解
@EnableTransactionManagement
// Rest服务调用
@Configuration
@EnableAutoConfiguration
@EnableFeignClients(basePackageClasses = {Scanner.class})
public class HeyiApplication {

    public static void main(String[] args) {
        // 隐藏banner启动方式
        SpringApplication springApplication = new SpringApplication( HeyiApplication.class );
        // 设置banner的模式为隐藏
        springApplication.setBannerMode( Banner.Mode.OFF );
        // 启动spring boot应用程序
        springApplication.run( args );
    }
}
