package com.andy.heyi.common.version;

import com.andy.heyi.common.consts.Constants;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName ApiVesrsionCondition
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2018/8/8$ 4:12 PM$
 * @UpdateUser: lidong
 * @UpdateDate: 2018/8/8$ 4:12 PM$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
public class ApiVersionCondition implements RequestCondition<ApiVersionCondition> {

    // 路径中版本的前缀， 这里用 /v[1-9]/的形式
    private final static Pattern VERSION_PREFIX_PATTERN = Pattern.compile( Constants.API_PREFIX + "(\\d+)/" );

    private int apiVersion;

    public ApiVersionCondition(int apiVersion) {
        this.apiVersion = apiVersion;
    }

    /**
     * 采用最后定义优先原则，则方法上的定义覆盖类上面的定义
     *
     * @param other
     * @return
     */
    public ApiVersionCondition combine(ApiVersionCondition other) {
        return new ApiVersionCondition( other.getApiVersion() );
    }

    /**
     * 这样我们通过v1,v2这样的地址就能访问到对应的版本，但是这样会有一个问题，如果客户端传入v3,但是所定义的版本并没有定义v3，
     * 此时客户端肯定会报404错误，也就是没有这个页面，怎么办呢，相当的就是拦截器，如果传入的版本没有，则默认访问最新的版本
     *
     * @param request
     * @return
     */
    public ApiVersionCondition getMatchingCondition(HttpServletRequest request) {
        try {
            String requestUrl = request.getRequestURI();
            // 判断是否符合v1、v2的标准
            Matcher m = VERSION_PREFIX_PATTERN.matcher( requestUrl );
            if (m.find()) {
                Integer version = Integer.valueOf( m.group( 1 ) );
                // 如果请求的版本号大于配置版本号， 则满足
                if (version >= this.apiVersion)
                    return this;
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    /**
     * 优先匹配最新的版本号
     *
     * @param other
     * @param request
     * @return
     */
    public int compareTo(ApiVersionCondition other, HttpServletRequest request) {
        return other.getApiVersion() - this.apiVersion;
    }

    public int getApiVersion() {
        return apiVersion;
    }

}