package com.andy.heyi.repository.user;

import com.andy.heyi.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * 访问数据库的接口
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * 根据用户名查找用户信息
     *
     * @param username
     * @return
     */
    @Query(value = "select u from User as u where u.name = ?1")
    User findByName(String username);

    /**
     * 根据用户名查找用户信息
     *
     * @param name
     * @return
     */
    User findUserByName(String name);
}
