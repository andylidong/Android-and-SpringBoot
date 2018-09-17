package com.andy.heyi.service.user;

import com.andy.heyi.model.user.User;
import com.andy.heyi.service.IService;

/**
 * @InterfaceName UserService
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2018/7/17$ 3:35 PM$
 * @UpdateUser: lidong
 * @UpdateDate: 2018/7/17$ 3:35 PM$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
public interface UserService extends IService<User> {

    /**
     * 通过用户的姓名查找信息
     *
     * @param name
     * @return
     */
    User loadUserByUsername(String name);

    /**
     * 通过用户的姓名查找信息
     *
     * @param name
     * @return
     */
    User findUserByName(String name);
}
