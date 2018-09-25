package com.andy.heyi.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName IPAddressUtil
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2018/8/7$ 10:04 AM$
 * @UpdateUser: lidong
 * @UpdateDate: 2018/8/7$ 10:04 AM$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
public class IPAddressUtil {

    public static String getClientIpAddress(HttpServletRequest request) {
        String ip = request.getHeader( "X-Forwarded-For" );
        if (EmptyUtil.isEmpty( ip ) || "unknown".equalsIgnoreCase( ip )) {
            ip = request.getHeader( "Proxy-Client-IP" );
        }
        if (EmptyUtil.isEmpty( ip ) || "unknown".equalsIgnoreCase( ip )) {
            ip = request.getHeader( "WL-Proxy-Client-IP" );
        }
        if (EmptyUtil.isEmpty( ip ) || "unknown".equalsIgnoreCase( ip )) {
            ip = request.getHeader( "HTTP_CLIENT_IP" );
        }
        if (EmptyUtil.isEmpty( ip ) || "unknown".equalsIgnoreCase( ip )) {
            ip = request.getHeader( "HTTP_X_FORWARDED_FOR" );
        }
        if (EmptyUtil.isEmpty( ip ) || "unknown".equalsIgnoreCase( ip )) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
