package com.andy.heyi.common.security;


import com.andy.heyi.common.data.ConfigDataYML;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.annotation.Resource;

/**
 * @ClassName SecurityConfig
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2018/7/18$ 9:59 AM$
 * @UpdateUser: lidong
 * @UpdateDate: 2018/7/18$ 9:59 AM$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Resource
    private LoginService loginService;

    @Resource
    private ConfigDataYML configDataYML;

    @Override
    protected void configure(HttpSecurity http) {
        log.info("SecurityConfig.HttpSecurity http ");
        try {
            if (!configDataYML.getEnableFilter()) {
                http.csrf().disable();
                return;
            }
            http.authorizeRequests()
                    .and()
                    // 下面的配置使HttpSecurity接收以"/swagger2/","/oauth/"开头请求。
                    .authorizeRequests()
                    .antMatchers("/static/**").permitAll()
                    .and().formLogin().loginPage("/login")
                    //设置默认登录成功跳转页面
                    .defaultSuccessUrl("/index").failureUrl("/login?error").permitAll()
                    .and()
                    //开启cookie保存用户数据
                    .rememberMe()
                    //设置cookie有效期
                    .tokenValiditySeconds(60 * 60 * 24 * 7)
                    //设置cookie的私钥
                    .key("lidong")
                    .and()
                    .logout()
                    //默认注销行为为logout，可以通过下面的方式来修改
                    .logoutUrl("/logout")
                    //设置注销成功后跳转页面，默认是跳转到登录页面
                    .logoutSuccessUrl("/login")
                    .permitAll();
            // 解决不允许显示在iframe的问题
            http.headers()
                    .frameOptions()
                    .sameOrigin()
                    .httpStrictTransportSecurity()
                    .disable();
            // 只允许一个用户登录,如果同一个账户两次登录,那么第一个账户将被踢下线,跳转到登录页面
            http.sessionManagement()
                    .invalidSessionUrl("/login")
                    .maximumSessions(1)
                    .expiredUrl("/login");

        } catch (Exception e) {
            log.error("SecurityConfig.configure http exception = " + e.getMessage());
        }
    }

    @Override
    public void configure(WebSecurity web) {
        // 忽略静态资源
        web.ignoring().antMatchers(
                // 本地的资源设置
                "/static/**",
                // -- druid
                "/druid/**",
                // -- swagger ui
                "/v2/swagger2-docs",
                "/swagger-resources",
                "/swagger-resources/**",
                "/configuration/ui",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**"
        );
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        log.info("SecurityConfig.AuthenticationManagerBuilder auth ");
        // 设置最初的密码，验证的用户名和密码
        try {
            auth.userDetailsService(loginService);
        } catch (Exception e) {
            log.error("SecurityConfig.configure AuthenticationManagerBuilder auth exception = " + e.getMessage());
        }
    }

    //配置全局设置
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        log.info("SecurityConfig.AuthenticationManagerBuilder auth ");
        // 设置最初的密码，验证的用户名和密码
        try {
            auth.userDetailsService(loginService);
        } catch (Exception e) {
            log.error("SecurityConfig.configureGlobal auth exception = " + e.getMessage());
        }
    }

}
