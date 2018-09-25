package com.andy.heyi.controller;

import com.andy.heyi.common.data.UserDataYML;
import com.andy.heyi.common.version.ApiVersion;
import com.andy.heyi.util.result.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName HelloController
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2018/7/17$ 11:57 AM$
 * @UpdateUser: lidong
 * @UpdateDate: 2018/7/17$ 11:57 AM$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
@Controller
@ApiOperation(value = "Hello", notes = "欢迎信息")
@ApiVersion(1)
public class HelloController {

    @Autowired
    private UserDataYML userDataYML;

    @GetMapping(value = "demo")
    @ResponseBody
    public String demo(@RequestParam(value = "name", defaultValue = "This is a demo for first!!!!!!") String name) {
        return ResultUtil.success( name );
    }

    @ApiVersion(3)
    @GetMapping(value = "demo")
    @ResponseBody
    public String demo3(@RequestParam(value = "name", defaultValue = "This is a demo for third!!!!!!") String name) {
        return ResultUtil.success( name );
    }

    @GetMapping(value = "andy")
    @ResponseBody
    public Object andy() {
        return ResultUtil.success( "这是: " + userDataYML.getUserName() + " , 口头禅: " + userDataYML.getTag() );
    }
}
