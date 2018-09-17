package com.andy.heyi.common.version;

import com.andy.heyi.common.consts.Constants;
import com.andy.heyi.util.EmptyUtil;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.condition.*;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

/**
 * @ClassName CustomRequestMappingHandlerMapping
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2018/8/8$ 4:13 PM$
 * @UpdateUser: lidong
 * @UpdateDate: 2018/8/8$ 4:13 PM$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
public class CustomRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

    @Override
    protected RequestCondition<ApiVersionCondition> getCustomTypeCondition(Class<?> handlerType) {
        ApiVersion apiVersion = AnnotationUtils.findAnnotation( handlerType, ApiVersion.class );
        return createCondition( apiVersion );
    }

    @Override
    protected RequestCondition<ApiVersionCondition> getCustomMethodCondition(Method method) {
        ApiVersion apiVersion = AnnotationUtils.findAnnotation( method, ApiVersion.class );
        return createCondition( apiVersion );
    }

    @Override
    protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
        RequestMappingInfo info = super.getMappingForMethod( method, handlerType );
        ApiVersion methodAnnotation = AnnotationUtils.findAnnotation( method, ApiVersion.class );
        if (methodAnnotation != null) {
            RequestCondition<?> methodCondition = getCustomMethodCondition( method );
            info = createApiVersionInfo( methodAnnotation, methodCondition ).combine( info );
        } else {
            ApiVersion typeAnnotation = AnnotationUtils.findAnnotation( handlerType, ApiVersion.class );
            if (typeAnnotation != null) {
                RequestCondition<?> typeCondition = getCustomTypeCondition( handlerType );
                info = createApiVersionInfo( typeAnnotation, typeCondition ).combine( info );
            }
        }
        return info;
    }

    /**
     * @param apiVersion
     * @return
     */
    private RequestCondition<ApiVersionCondition> createCondition(ApiVersion apiVersion) {
        return EmptyUtil.isEmpty( apiVersion ) ? null : new ApiVersionCondition( apiVersion.value() );
    }

    /**
     * 创建api的信息
     *
     * @param annotation
     * @param customCondition
     * @return
     */
    private RequestMappingInfo createApiVersionInfo(ApiVersion annotation, RequestCondition<?> customCondition) {
        int values = annotation.value();
        return new RequestMappingInfo(
                new PatternsRequestCondition( new String[]{Constants.API_PREFIX + String.valueOf( values )}, getUrlPathHelper(), getPathMatcher(), useSuffixPatternMatch(), useTrailingSlashMatch(), getFileExtensions() ),
                new RequestMethodsRequestCondition(),
                new ParamsRequestCondition(),
                new HeadersRequestCondition(),
                new ConsumesRequestCondition(),
                new ProducesRequestCondition(),
                customCondition );
    }
}