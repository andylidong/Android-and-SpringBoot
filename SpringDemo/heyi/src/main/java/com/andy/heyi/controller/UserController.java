package com.andy.heyi.controller;

import com.andy.heyi.model.user.User;
import com.andy.heyi.service.user.UserService;
import com.andy.heyi.util.ValidatorUtil;
import com.andy.heyi.util.result.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @ClassName UserController
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2018/7/18$ 6:04 PM$
 * @UpdateUser: lidong
 * @UpdateDate: 2018/7/18$ 6:04 PM$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
@Controller
@RequestMapping(value = "/user")
@ApiOperation(value = "User", notes = "用户信息")
public class UserController {

    // 添加和修改用户信息的界面
    private static final String BOOK_FORM_PATH_NAME = "user/userForm";

    // 展示和删除用户信息的界面
    private static final String BOOK_LIST_PATH_NAME = "user/userList";

    // 重定向到用户组展示界面
    private static final String REDIRECT_TO_BOOK_URL = "redirect:/user";

    @Resource
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    /**
     * 获取 User 列表
     * 处理 "/user" 的 GET 请求，用来获取 User 列表
     */
    @RequestMapping(method = RequestMethod.GET)
    public String getUserList(@RequestParam(value = "token", defaultValue = "") String token, ModelMap map) {
        map.addAttribute( "userList", userService.findAll() );
        map.addAttribute( "token", token );
        return BOOK_LIST_PATH_NAME;
    }

    /**
     * 获取创建 User 表单
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createUserForm(@RequestParam(value = "token", defaultValue = "") String token, ModelMap map) {
        map.addAttribute( "user", new User() );
        map.addAttribute( "action", "create" );
        map.addAttribute( "token", token );
        return BOOK_FORM_PATH_NAME;
    }

    /**
     * 创建 User
     * 处理 "/user/create" 的 POST 请求，用来新建 User 信息
     * 通过 @ModelAttribute 绑定表单实体参数，也通过 @RequestParam 传递参数
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String postUser(@RequestParam(value = "token", defaultValue = "") String token, @ModelAttribute @Valid User user, BindingResult result, ModelMap map) {
        map.addAttribute( "token", token );
        if (result.hasErrors()) {
            ValidatorUtil.validator( result, map, messageSource );
            map.addAttribute( "action", "create" );
            return BOOK_FORM_PATH_NAME;
        }
        userService.save( user );
        return REDIRECT_TO_BOOK_URL;
    }

    /**
     * 获取更新 User 表单
     * 处理 "/user/update/{id}" 的 GET 请求，通过 URL 中的 id 值获取 User 信息
     * URL 中的 id ，通过 @PathVariable 绑定参数
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String getUser(@PathVariable int id, @RequestParam(value = "token", defaultValue = "") String token, ModelMap map) {
        map.addAttribute( "user", userService.findById( id ) );
        map.addAttribute( "action", "update" );
        map.addAttribute( "token", token );
        return BOOK_FORM_PATH_NAME;
    }

    /**
     * 更新 User
     * 处理 "/update" 的 PUT 请求，用来更新 User 信息
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String putUser(@RequestParam(value = "token", defaultValue = "") String token, @ModelAttribute @Valid User user, BindingResult result, ModelMap map) {
        map.addAttribute( "token", token );
        if (result.hasErrors()) {
            ValidatorUtil.validator( result, map, messageSource );
            map.addAttribute( "action", "update" );
            return BOOK_FORM_PATH_NAME;
        }
        userService.update( user );
        return REDIRECT_TO_BOOK_URL;
    }

    /**
     * 删除 User
     * 处理 "/user/{id}" 的 GET 请求，用来删除 User 信息
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable int id, @RequestParam(value = "token", defaultValue = "") String token) {
        userService.deleteById( id );
        return REDIRECT_TO_BOOK_URL + "?token=" + token;
    }

    /**
     * @param token
     * @param user
     * @return
     */
    @PostMapping(value = "/users", consumes = "application/json")
    @ResponseBody
    public Object findUser(@RequestParam(value = "token", defaultValue = "") String token, @RequestBody User user) {
        if (user == null) {
            return null;
        }
        return ResultUtil.success( userService.findById( user.getId() ) );
    }

    @GetMapping(value = "/users")
    @ResponseBody
    public Object findUser(@RequestParam(value = "token", defaultValue = "") String token, @RequestParam("id") int id) {
        if (id < 1) {
            return null;
        }
        return ResultUtil.success( userService.findById( id ) );
    }


}
