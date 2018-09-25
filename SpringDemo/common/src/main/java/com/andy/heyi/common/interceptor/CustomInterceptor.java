package com.andy.heyi.common.interceptor;

import com.andy.heyi.common.consts.Constants;
import com.andy.heyi.common.data.ConfigDataYML;
import com.andy.heyi.common.i18n.I18nService;
import com.andy.heyi.common.i18n.data.I18nExceptions;
import com.andy.heyi.common.token.Token;
import com.andy.heyi.common.token.TokenManager;
import com.andy.heyi.util.ArrayUtil;
import com.andy.heyi.util.EmptyUtil;
import com.andy.heyi.util.IPAddressUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 **/
@Component
@Slf4j
public class CustomInterceptor implements HandlerInterceptor {

    @Autowired
    private ConfigDataYML configDataYML;

    @Autowired
    private TokenManager manager;

    @Autowired
    private I18nService i18nService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info( "CustomInterceptor, preHandle = " + handler );
        return judgeMethod( request, response, handler );
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {
        log.info( "CustomInterceptor, postHandle = " + handler );
        if (EmptyUtil.isNotEmpty( request ) && EmptyUtil.isNotEmpty( configDataYML )) {
            String ip = IPAddressUtil.getClientIpAddress( request );
            if (ip.equalsIgnoreCase( configDataYML.getFilterAddress() )) {
                log.info( "CustomInterceptor, postHandle  ip = " + ip );
                modelAndView.setViewName( configDataYML.getFilterToView() );
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if (ex != null)
            log.info( "CustomInterceptor, afterCompletion = " + ex.getMessage() );
    }


    /**
     * 判断是否放行
     *
     * @param handler
     * @param request
     * @return
     */
    private boolean judgeMethod(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) return true;
        // 将配置的页面放行（登录，主页等）
        String requestMethod = ((HandlerMethod) handler).getMethod().getName();
        if (ArrayUtil.arratToListSizeWithPattern( configDataYML.getPassUrl(), requestMethod ) > 0)
            return true;
        // 将swagger2放行
        if (request.getRequestURI().contains( "swagger" )) return true;
        return judgeToken( request, response );
    }

    /**
     * 判断token是否正确
     *
     * @param request
     * @param response
     * @return
     */
    private boolean judgeToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 从header中得到token
        String token = request.getParameter( "token" );
        token = EmptyUtil.isEmpty( token ) ? request.getHeader( "token" ) : token;
        log.info( "CustomInterceptor, token = " + token );
        // 判断token是否符合标准
        if (!StringUtils.isEmpty( token )) {
            //验证token
            Token model = manager.getTokenInfoByToken( token );
            if (model != null) {
                log.info( "token验证通过" );
                // 如果token验证成功，将token对应的用户id存在request中，便于之后注入
                request.setAttribute( Constants.CURRENT_USER_ID, model.getUserName() );
                return true;
            }
        }
        log.info( "CustomInterceptor , token验证失败" );
        throw new Exception( i18nService.getValue( I18nExceptions.AUTHORIZATION ) );
    }
}
