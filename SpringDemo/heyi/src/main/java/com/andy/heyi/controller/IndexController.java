package com.andy.heyi.controller;

import com.andy.heyi.common.task.Task;
import com.andy.heyi.common.token.Token;
import com.andy.heyi.common.token.TokenManager;
import com.andy.heyi.util.EmptyUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ExecutionException;

/**
 * @ClassName IndexController
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2018/8/8$ 4:45 PM$
 * @UpdateUser: lidong
 * @UpdateDate: 2018/8/8$ 4:45 PM$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
@Slf4j
@Controller
@ApiOperation(value = "Index", notes = "欢迎信息")
public class IndexController {
    // 主界面
    private static final String INDEX_URL = "login/index";

    // 欢迎界面
    private static final String HELLO_URL = "login/hello";

    // 登录界面
    private static final String LOGIN_URL = "login/login";

    @Autowired
    private Task task;

    @Autowired
    private TokenManager tokenManager;

    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public String hello() {
        log.info( "IndexController , hello" );
        try {
            System.out.println( task.doTaskOne().get() );
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return HELLO_URL;
    }

    @RequestMapping(value = "/index", method = {RequestMethod.GET, RequestMethod.POST})
    public String index(HttpServletRequest request, ModelMap map) {
        log.info( "IndexController , index" );
        // 生成一个token，保存用户登录状态
        if (EmptyUtil.isNotEmpty( request.getRemoteUser() )) {
            Token model = tokenManager.createToken( request.getRemoteUser() );
            map.addAttribute( "token", model.getToken() );
            log.info( "loadByUsername , token = " + model.getToken() );
        }
        return INDEX_URL;
    }

    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public String login() {
        log.info( "IndexController , login" );
        return LOGIN_URL;
    }

    @RequestMapping(value = "/logout", method = {RequestMethod.GET, RequestMethod.POST})
    public String logout(@RequestParam(value = "token", defaultValue = "") String token) {
        log.info( "IndexController , logout token = " + token );
        if (token != null) tokenManager.deleteToken( token );
        return LOGIN_URL;
    }
}
