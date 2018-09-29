package com.andy.heyi.controller;

import com.andy.heyi.common.data.UserDataYML;
import com.andy.heyi.common.version.ApiVersion;
import com.andy.heyi.dto.User;
import com.andy.heyi.service.http.RecylerViewService;
import com.andy.heyi.util.RestTemplateUtil;
import com.andy.heyi.util.result.ResultCode;
import com.andy.heyi.util.result.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
@RestController
@ApiOperation(value = "Hello", notes = "欢迎信息")
@ApiVersion(1)
public class HelloController {

    @Autowired
    private UserDataYML userDataYML;

    @Autowired
    private RecylerViewService recylerViewService;

    @GetMapping(value = "demo")
    public String demo(@RequestParam(value = "name", defaultValue = "This is a demo for first!!!!!!") String name) {
        return ResultUtil.success(name);
    }

    @ApiVersion(3)
    @GetMapping(value = "demo")
    public String demo3(@RequestParam(value = "name", defaultValue = "This is a demo for third!!!!!!") String name) {
        return ResultUtil.success(name);
    }

    @GetMapping(value = "andy")
    public Object andy() {
        return ResultUtil.success("这是: " + userDataYML.getUserName() + " , 口头禅: " + userDataYML.getTag());
    }

    @PostMapping(value = "loginRxJava")
    public String login(@RequestParam("name") String name, @RequestParam("password") String password) {
        if (StringUtils.isEmpty(name) | StringUtils.isEmpty(password)) {
            return ResultUtil.success("请您输入完整的登录信息！");
        }
        if (name.equalsIgnoreCase("andy") && password.equalsIgnoreCase("andy"))
            return ResultUtil.success("登录成功！");
        else {
            return ResultUtil.success("登录失败！");
        }
    }

    @PostMapping(value = "login")
    public String login(@Nullable @RequestBody User user) {
        if (ObjectUtils.isEmpty(user) | StringUtils.isEmpty(user.getName()) | StringUtils.isEmpty(user.getPassword())) {
            return ResultUtil.error(ResultCode.USERNAME_OR_PASSWORD_ERROR, "请您输入完整的登录信息！");
        }
        if (user.getName().equalsIgnoreCase("andy") && user.getPassword().equalsIgnoreCase("andy"))
            return ResultUtil.success("登录成功！");
        else {
            return ResultUtil.error(ResultCode.USERNAME_OR_PASSWORD_ERROR, "登录失败！");
        }
    }

    @GetMapping("recylerview")
    public String recylerview() {
        // Object result = recylerViewService.data();
        Map data = (Map) RestTemplateUtil.get("https://gank.io/api/data/福利/10/1");
        return ResultUtil.success(data.get("results"));
    }
}
