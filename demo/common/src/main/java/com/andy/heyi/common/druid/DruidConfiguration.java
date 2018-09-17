package com.andy.heyi.common.druid;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.andy.heyi.common.data.DruidDataYML;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @ClassName DruidConfiguration
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2018/8/7$ 10:56 AM$
 * @UpdateUser: lidong
 * @UpdateDate: 2018/8/7$ 10:56 AM$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
@Configuration
public class DruidConfiguration {
    @Resource
    private DruidDataYML druidDataYML;

    @Bean
    public ServletRegistrationBean statViewServlet() {
        // 创建servlet注册实体
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean( new StatViewServlet(), "/druid/*" );
        // 设置ip白名单
        servletRegistrationBean.addInitParameter( "allow", druidDataYML.getAllowIP() );
        // 设置ip黑名单，如果allow与deny共同存在时,deny优先于allow
        servletRegistrationBean.addInitParameter( "deny", druidDataYML.getDenyIP() );
        // 设置控制台管理用户
        servletRegistrationBean.addInitParameter( "loginUsername", druidDataYML.getUserName() );
        servletRegistrationBean.addInitParameter( "loginPassword", druidDataYML.getPassword() );
        //是否可以重置数据
        servletRegistrationBean.addInitParameter( "resetEnable", druidDataYML.getResetEnable() );
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean statFilter() {
        // 创建过滤器
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean( new WebStatFilter() );
        // 设置过滤器过滤路径
        filterRegistrationBean.addUrlPatterns( druidDataYML.getUrlPatterns() );
        // 忽略过滤的形式
        filterRegistrationBean.addInitParameter( "exclusions", druidDataYML.getInitParameter() );
        return filterRegistrationBean;
    }
}
