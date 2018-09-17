package com.andy.heyi.util.result;

/**
 * @ClassName ResultCode
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2018/8/9$ 4:16 PM$
 * @UpdateUser: lidong
 * @UpdateDate: 2018/8/9$ 4:16 PM$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
public interface ResultCode {
    /**
     * 已经成功获取数据
     */
    int SUCCESS = 200;

    /**
     * 未授权
     */
    int UNAUTHORIZED = 401;
    /**
     * 未找到
     */
    int NOT_FOUND = 404;

    /**
     * 内部错误
     */
    int FAILURE = 500;


    /**
     * token过期，请重新登录
     */
    int TOKEN_NO_WORK = 1000;

    /**
     * 用户名或密码错误
     */
    int USERNAME_OR_PASSWORD_ERROR = 1001;

    /**
     * 用户不存在
     */
    int USER_NOT_FOUND = 1002;

    /**
     * 用户未登录
     */
    int USER_NOT_LOGIN = 1003;

}
