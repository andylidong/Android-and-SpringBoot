package com.andy.heyi.common.data;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName ConfigDataYML
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2018/8/6$ 6:12 PM$
 * @UpdateUser: lidong
 * @UpdateDate: 2018/8/6$ 6:12 PM$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
@Component
@ConfigurationProperties("config")
@Data
public class ConfigDataYML {
    /**
     * 登录的管理员的账号，写死的，不建议使用
     */
    private String adminName;

    /**
     * 拦截的地址
     */
    private String filterAddress;

    /**
     * 拦截之后跳转的页面
     */
    private String filterToView;

    /**
     * 是否进行拦截
     */
    private Boolean enableFilter;

    /**
     * 放行的路径
     */
    private String[] passUrl;
}