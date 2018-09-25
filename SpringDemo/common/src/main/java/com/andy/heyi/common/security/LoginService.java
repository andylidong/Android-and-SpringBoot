package com.andy.heyi.common.security;

import com.andy.heyi.common.data.ConfigDataYML;
import com.andy.heyi.service.user.UserService;
import com.andy.heyi.util.EmptyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 **/
@Slf4j
@Service("loginService")
public class LoginService implements UserDetailsService {
    @Resource
    private UserService userService;

    @Resource
    private ConfigDataYML configDataYML;

    /**
     * 密码编码器
     */
    private PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    /**
     * 用户管理
     */
    private final User.UserBuilder userBuilder = User.builder().passwordEncoder( encoder::encode );


    @Override
    public UserDetails loadUserByUsername(String username) {
        log.info( "LoginService.loadUserByUsername, username = " + username );
        try {
            return this.loadByUsername( username );
        } catch (UsernameNotFoundException e) {
            log.info( "LoginService.loadUserByUsername, Exception = " + e.getMessage() );
            return null;
        }
    }

    /**
     * 通过用户名来判断用户的信息是否存在，存在，则将信息放入UserDetails中
     *
     * @param username
     * @return
     */
    private UserDetails loadByUsername(String username) {
        // 1、全局设置，设置默认的账号登录信息
        if (username.equalsIgnoreCase( configDataYML.getAdminName() )) {
            UserDetails userDetails = userBuilder
                    .username( username )
                    .password( "admin" )
                    .roles( "ADMIN" )
                    .build();
            return userDetails;
        }
        // 2、从数据库中读取用户的信息，判断登录的账号是否在数据库中, 获取到密码，与输入的作比较
        com.andy.heyi.model.user.User user = userService.loadUserByUsername( username );
        UserDetails userDetails;
        if (EmptyUtil.isNotEmpty( user )) {
            userDetails = userBuilder
                    .username( username )
                    .password( user.getPassword() )
                    .roles( "USER" )
                    .build();
            return userDetails;
        }
        userDetails = userBuilder
                .username( username )
                .password( "" )
                .roles( "USER" )
                .build();
        return userDetails;
    }
}
