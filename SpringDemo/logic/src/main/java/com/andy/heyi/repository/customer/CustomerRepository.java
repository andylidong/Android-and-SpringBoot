package com.andy.heyi.repository.customer;

import com.andy.heyi.model.customer.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @ClassName Customer
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2018/8/17$ 1:24 PM$
 * @UpdateUser: lidong
 * @UpdateDate: 2018/8/17$ 1:24 PM$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
public interface CustomerRepository extends MongoRepository<Customer, String> {
}