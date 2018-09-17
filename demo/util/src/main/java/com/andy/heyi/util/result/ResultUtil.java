package com.andy.heyi.util.result;

import com.alibaba.fastjson.JSONObject;

/**
 * @ClassName ResultUtil
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2018/7/19$ 11:47 AM$
 * @UpdateUser: lidong
 * @UpdateDate: 2018/7/19$ 11:47 AM$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
public class ResultUtil {

    /**
     * 当正确时返回的值
     *
     * @param data
     * @return
     */
    public static String success(Object data) {
        Result result = new Result();
        result.setCode( ResultCode.SUCCESS );
        result.setMsg( "success" );
        result.setData( data );
        return new JSONObject().toJSONString( result );
    }

    /**
     * 处理默认成功的
     *
     * @return
     */
    public static String success() {
        return success( null );
    }

    /**
     * 当错误时返回的值
     *
     * @param code
     * @param msg
     * @return
     */
    public static String error(int code, String msg) {
        Result result = new Result();
        result.setCode( code );
        result.setMsg( msg );
        return new JSONObject().toJSONString( result );
    }
}
