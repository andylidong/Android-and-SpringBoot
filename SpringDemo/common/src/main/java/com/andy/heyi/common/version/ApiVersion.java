package com.andy.heyi.common.version;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName ApiVersion
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2018/8/8$ 4:10 PM$
 * @UpdateUser: lidong
 * @UpdateDate: 2018/8/8$ 4:10 PM$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiVersion {

    /**
     * 标识版本号
     *
     * @return
     */
    int value();
}