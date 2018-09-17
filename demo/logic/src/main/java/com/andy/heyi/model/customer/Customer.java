package com.andy.heyi.model.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * @ClassName Customer
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2018/8/17$ 1:23 PM$
 * @UpdateUser: lidong
 * @UpdateDate: 2018/8/17$ 1:23 PM$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements Serializable {
    /**
     * 客户编号
     */
    @Id
    public String id;
    /**
     * 客户名称
     */
    public String firstName;
    /**
     * 客户姓氏
     */
    public String lastName;

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
