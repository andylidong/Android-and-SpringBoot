package com.andy.heyi.util;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Locale;

/**
 * @ClassName ValidatorUtil
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2018/8/8$ 2:27 PM$
 * @UpdateUser: lidong
 * @UpdateDate: 2018/8/8$ 2:27 PM$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
public class ValidatorUtil {

    /**
     * 实体的字段验证
     *
     * @param result
     * @param map
     * @param messageSource
     */
    public static void validator(BindingResult result, ModelMap map, MessageSource messageSource) {
        //获取错误字段集合
        List<FieldError> fieldErrors = result.getFieldErrors();
        //获取本地locale,zh_CN
        Locale currentLocale = LocaleContextHolder.getLocale();
        //遍历错误字段获取错误消息
        for (FieldError fieldError :
                fieldErrors) {
            //获取错误信息
            String errorMessage = messageSource.getMessage( fieldError, currentLocale );
            //添加到错误消息集合内
            map.addAttribute( fieldError.getField(), errorMessage );
        }
    }

    /**
     * 添加逻辑的验证
     *
     * @param map
     * @param key
     * @param value
     */
    public static void validator(ModelMap map, String key, String value) {
        //添加到错误消息集合内
        map.addAttribute( key, value );
    }
}
