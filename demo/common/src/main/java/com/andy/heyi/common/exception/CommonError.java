package com.andy.heyi.common.exception;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @ClassName CommonError
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2018/8/16$ 11:12 AM$
 * @UpdateUser: lidong
 * @UpdateDate: 2018/8/16$ 11:12 AM$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
@Component
public class CommonError implements ErrorPageRegistrar {
    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage e404 = new ErrorPage( HttpStatus.NOT_FOUND, "/common/404.html" );
        ErrorPage e500 = new ErrorPage( HttpStatus.INTERNAL_SERVER_ERROR, "/common/500.html" );
        ErrorPage eArg = new ErrorPage( IllegalArgumentException.class, "/common/error.html" );
        registry.addErrorPages( e404, e500, eArg );
    }
}
