package com.andy.heyi.common.mvc;

import com.andy.heyi.common.interceptor.CustomInterceptor;
import com.andy.heyi.common.version.CustomRequestMappingHandlerMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.UrlPathHelper;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @ClassName WebMvcConfiguration
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2018/7/25$ 2:33 PM$
 * @UpdateUser: lidong
 * @UpdateDate: 2018/7/25$ 2:33 PM$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {
    @Bean
    public HandlerInterceptor getCustomInterceptor() {
        return new CustomInterceptor();
    }

    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter( Charset.forName( "UTF-8" ) );
        return converter;
    }

    /**
     * 设置api的版本控制 ApiVersion(1), 加强版本控制
     *
     * @return
     */
    @Bean
    @Override
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        RequestMappingHandlerMapping mapping = new CustomRequestMappingHandlerMapping();
        mapping.setOrder( 0 );
        mapping.setInterceptors( getInterceptors() );
        mapping.setContentNegotiationManager( mvcContentNegotiationManager() );
        mapping.setCorsConfigurations( getCorsConfigurations() );
        PathMatchConfigurer configurer = getPathMatchConfigurer();
        Boolean useSuffixPatternMatch = configurer.isUseSuffixPatternMatch();
        Boolean useRegisteredSuffixPatternMatch = configurer.isUseRegisteredSuffixPatternMatch();
        Boolean useTrailingSlashMatch = configurer.isUseTrailingSlashMatch();
        if (useSuffixPatternMatch != null) {
            mapping.setUseSuffixPatternMatch( useSuffixPatternMatch );
        }
        if (useRegisteredSuffixPatternMatch != null) {
            mapping.setUseRegisteredSuffixPatternMatch( useRegisteredSuffixPatternMatch );
        }
        if (useTrailingSlashMatch != null) {
            mapping.setUseTrailingSlashMatch( useTrailingSlashMatch );
        }
        UrlPathHelper pathHelper = configurer.getUrlPathHelper();
        if (pathHelper != null) {
            mapping.setUrlPathHelper( pathHelper );
        }
        PathMatcher pathMatcher = configurer.getPathMatcher();
        if (pathMatcher != null) {
            mapping.setPathMatcher( pathMatcher );
        }
        return mapping;
    }

    /**
     * 解决乱码
     *
     * @param converters
     */
    @Override
    public void configureMessageConverters(
            List<HttpMessageConverter<?>> converters) {
        converters.add( responseBodyConverter() );
    }

    /**
     * 多个拦截器组成一个拦截器链
     * addPathPatterns 用于添加拦截规则
     * excludePathPatterns 用户排除拦截
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截器的设置
        registry.addInterceptor( getCustomInterceptor() ).addPathPatterns( "/**" );
        // 设置国际化
        registry.addInterceptor( new LocaleChangeInterceptor() ).addPathPatterns( "/**" );
    }

    /**
     * 跨域访问的设置
     * addMapping：配置可以被跨域的路径，可以任意配置，可以具体到直接请求路径。
     * allowedMethods：允许所有的请求方法访问该跨域资源服务器，如：POST、GET、PUT、DELETE等。
     * allowedOrigins：允许所有的请求域名访问我们的跨域资源，可以固定单条或者多条内容，如："http://www.baidu.com"，只有百度可以访问我们的跨域资源。
     * allowedHeaders：允许所有的请求header访问，可以自定义设置任意请求头信息，如："X-YAUTH-TOKEN"
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping( "/**" )
                .allowedOrigins( "*" )
                .allowedHeaders( "*" )
                .allowedMethods( "*" )
                .allowCredentials( true )
                .maxAge( 3600 );
    }

    /**
     * 发现如果继承了WebMvcConfigurationSupport，则在yml中配置的相关内容会失效。 需要重新指定静态资源
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler( "/**" ).addResourceLocations(
                "classpath:/static/" );
        registry.addResourceHandler( "swagger-ui.html" ).addResourceLocations(
                "classpath:/META-INF/resources/" );
        registry.addResourceHandler( "/webjars/**" ).addResourceLocations(
                "classpath:/META-INF/resources/webjars/" );
    }

    /**
     * 配置servlet处理
     *
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

}
