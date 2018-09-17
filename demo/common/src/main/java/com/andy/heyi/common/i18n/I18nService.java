package com.andy.heyi.common.i18n;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * @ClassName I18nService
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2018/8/14$ 1:56 PM$
 * @UpdateUser: lidong
 * @UpdateDate: 2018/8/14$ 1:56 PM$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
@Service
public class I18nService {
    @Autowired
    protected MessageSource messageSource;

    /**
     * 获取错误码描述信息
     *
     * @param code 错误码
     * @return 国际化信息
     */
    public String getValue(String code) {
        // 在spring boot项目的启动类main方法,设置项目部署语言环境Locale.setDefault(Locale.CHINESE);
        /**
         * 在启动类设置过locale为zh.所以下面MessageSource会加载messages_zh.properties文件;
         * 项目需要用到哪些语言环境,就需要创建对应的messages_xx.properties文件.
         * 例如,语言环境现在是en_US,那么就需要创建messages_en_US.properties文件
         */
        Locale locale = LocaleContextHolder.getLocale();
        try {
            return messageSource.getMessage( code, null, locale );
        } catch (NoSuchMessageException e) {
            return "";
        }
    }
}
