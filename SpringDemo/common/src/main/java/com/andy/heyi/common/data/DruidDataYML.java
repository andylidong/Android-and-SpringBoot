package com.andy.heyi.common.data;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName DruidDataYML
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2018/8/7$ 10:58 AM$
 * @UpdateUser: lidong
 * @UpdateDate: 2018/8/7$ 10:58 AM$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
@Component
@ConfigurationProperties("druid")
@Data
public class DruidDataYML {

    /**
     * 允许的访问IP, 设置ip白名单
     */
    private String allowIP;

    /**
     * 拒绝的访问IP, 设置ip黑名单，如果allow与deny共同存在时,deny优先于allow
     */
    private String denyIP;

    /**
     * 登录的账号和密码
     */
    private String userName;
    private String password;

    /**
     * 是否可以重置数据
     */
    private String resetEnable;

    /**
     * 设置过滤器过滤路径
     */
    private String urlPatterns;

    /**
     * 忽略过滤的形式
     */
    private String initParameter;


}
