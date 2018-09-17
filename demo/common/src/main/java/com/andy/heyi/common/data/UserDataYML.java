package com.andy.heyi.common.data;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName UserDataYML
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2018/7/18$ 11:00 AM$
 * @UpdateUser: lidong
 * @UpdateDate: 2018/7/18$ 11:00 AM$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
@Component
@ConfigurationProperties("user")
@Data
public class UserDataYML {
    // 一下的属性仅供测试使用
    private String userName;
    private String tag;
}
