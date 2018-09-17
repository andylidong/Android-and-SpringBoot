package com.andy.heyi.common.swagger2;


import com.andy.heyi.Scanner;
import com.andy.heyi.common.i18n.I18nService;
import com.andy.heyi.common.i18n.data.Swagger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Swagger2
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2018/7/26$ 2:39 PM$
 * @UpdateUser: lidong
 * @UpdateDate: 2018/7/26$ 2:39 PM$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
@EnableSwagger2
@Configuration
public class Swagger2 {

    @Autowired
    private I18nService i18nService;

    @Bean
    public Docket createRestApi1() {
        return new Docket( DocumentationType.SPRING_WEB )
                .groupName( i18nService.getValue( Swagger.GROUPNAME1 ) )
                .genericModelSubstitutes( DeferredResult.class )
                .useDefaultResponseMessages( false )
                .forCodeGeneration( true )
                .apiInfo( apiInfo() )
                .securitySchemes( securitySchemes() )
                .select()
                .apis( RequestHandlerSelectors.basePackage( Scanner.class.getPackage().getName() ) )
                .paths( PathSelectors.any() )
                .build();
    }

    @Bean
    public Docket createRestApi2() {
        return new Docket( DocumentationType.SWAGGER_2 )
                .groupName( i18nService.getValue( Swagger.GROUPNAME2 ) )
                .genericModelSubstitutes( DeferredResult.class )
                .useDefaultResponseMessages( false )
                .forCodeGeneration( true )
                .apiInfo( apiInfo() )
                .securitySchemes( securitySchemes() )
                .select()
                .apis( RequestHandlerSelectors.basePackage( Scanner.class.getPackage().getName() ) )
                .paths( PathSelectors.any() )
                .build();
    }


    @Bean
    public Docket createRestApi3() {
        return new Docket( DocumentationType.SWAGGER_12 )
                .groupName( i18nService.getValue( Swagger.GROUPNAME3 ) )
                .genericModelSubstitutes( DeferredResult.class )
                .useDefaultResponseMessages( false )
                .forCodeGeneration( true )
                .securitySchemes( securitySchemes() )
                .apiInfo( apiInfo() )
                .select()
                .apis( RequestHandlerSelectors.basePackage( Scanner.class.getPackage().getName() ) )
                .paths( PathSelectors.any() )
                .build();
    }

    /**
     * 设置api的信息
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title( i18nService.getValue( Swagger.TITLE ) )
                .description( i18nService.getValue( Swagger.DESCRIPTIONS ) )
                .version( "v1" )
                .build();
    }


    private List<ApiKey> securitySchemes() {
        List apikey = new ArrayList();
        apikey.add( new ApiKey( "Authorization", "token", "header" ) );
        return apikey;
    }

}
