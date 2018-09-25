package com.example.dli.androiddemo.util.retrofit;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 用于标记http请求的基地址
 */
@Documented
@Target(value = {ElementType.TYPE})
@Retention(RUNTIME)
public @interface RetrofitUrl {
    String value() default "";
}