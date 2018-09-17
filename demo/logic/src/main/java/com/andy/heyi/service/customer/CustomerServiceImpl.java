package com.andy.heyi.service.customer;

import com.andy.heyi.model.customer.Customer;
import com.andy.heyi.repository.customer.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName CustomerServiceImpl
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2018/8/17$ 1:29 PM$
 * @UpdateUser: lidong
 * @UpdateDate: 2018/8/17$ 1:29 PM$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
@Service("customerService")
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    @Resource
    private CustomerRepository repository;

    @Override
    public Customer save(Customer model) {
        try {
            log.info( "CustomerServiceImpl, save, Customer " + model );
            return repository.save( model );
        } catch (Exception e) {
            log.error( "CustomerServiceImpl, save, Exception " + e.getMessage() );
            return null;
        }
    }

    @Override
    public void save(List<Customer> models) {
        try {
            log.info( "CustomerServiceImpl, save, List, Customer " );
            models.stream().forEach( item -> repository.save( item ) );
        } catch (Exception e) {
            log.error( "CustomerServiceImpl, save, List, Exception " + e.getMessage() );
        }
    }

    @Override
    public Customer update(Customer model) {
        return null;
    }

    @Override
    public void update(List<Customer> model) {
    }

    @Override
    public Customer deleteById(Integer id) {
        return null;
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
    }

    @Override
    public Customer findById(Integer id) {
        return null;
    }

    @Override
    public List<Customer> findByIds(List<Integer> ids) {
        return null;
    }

    @Override
    public List<Customer> findAll() {
        try {
            log.info( "CustomerServiceImpl, findAll " );
            return repository.findAll();
        } catch (Exception e) {
            log.error( "CustomerServiceImpl, findAll, Exception " + e.getMessage() );
            return null;
        }
    }
}
