package com.andy.heyi.service.user;

import com.andy.heyi.model.user.User;
import com.andy.heyi.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @ClassName UserServiceImpl
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2018/7/17$ 3:35 PM$
 * @UpdateUser: lidong
 * @UpdateDate: 2018/7/17$ 3:35 PM$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
@Service("userService")
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public User save(User user) {
        log.info( "UserServiceImpl, save user = " + user );
        try {
            return userRepository.save( user );
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.info( "UserServiceImpl, user Exception = " + e.getMessage() );
            return null;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(List<User> user) {
        log.info( "UserServiceImpl, save user = " + user );
        try {
            user.forEach( item -> userRepository.save( item ) );
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.info( "UserServiceImpl, user Exception = " + e.getMessage() );
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User update(User user) {
        log.info( "UserServiceImpl, update user = " + user );
        try {
            return userRepository.save( user );
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.info( "UserServiceImpl, update Exception = " + e.getMessage() );
            return null;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(List<User> user) {
        log.info( "UserServiceImpl, update user = " + user );
        try {
            user.forEach( item -> userRepository.save( item ) );
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.info( "UserServiceImpl, update Exception = " + e.getMessage() );
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User deleteById(Integer id) {
        log.info( "UserServiceImpl, deleteById id = " + id );
        try {
            userRepository.deleteById( id );
            Optional<User> user = userRepository.findById( id );
            return !user.isPresent() ? null : user.get();
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.info( "UserServiceImpl, deleteById Exception = " + e.getMessage() );
            return null;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByIds(List<Integer> ids) {
        log.info( "UserServiceImpl, deleteByIds userId = " + ids );
        try {
            ids.forEach( (id) -> userRepository.deleteById( id ) );
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.info( "UserServiceImpl, deleteByIds Exception = " + e.getMessage() );
        }
    }

    @Override
    public User findById(Integer userId) {
        log.info( "UserServiceImpl, findById userId = " + userId );
        try {
            Optional<User> user = userRepository.findById( userId );
            return !user.isPresent() ? null : user.get();
        } catch (Exception e) {
            log.info( "UserServiceImpl, findById Exception = " + e.getMessage() );
            return null;
        }
    }

    @Override
    public List<User> findByIds(List<Integer> ids) {
        log.info( "UserServiceImpl, findByIds userId = " + ids );
        try {
            List<User> data = new ArrayList<User>();
            ids.forEach( (id) -> {
                Optional<User> user = userRepository.findById( id );
                if (user.isPresent()) data.add( user.get() );
            } );
            return data;
        } catch (Exception e) {
            log.info( "UserServiceImpl, findByIds Exception = " + e.getMessage() );
            return null;
        }
    }

    @Override
    public List<User> findAll() {
        log.info( "UserServiceImpl, findAll" );
        try {
            List data = userRepository.findAll();
            return data;
        } catch (Exception e) {
            log.info( "UserServiceImpl, findAll Exception = " + e.getMessage() );
            return null;
        }
    }

    @Override
    public User loadUserByUsername(String name) {
        log.info( "UserServiceImpl, loadUserByUsername username = " + name );
        try {
            return userRepository.findByName( name );
        } catch (Exception e) {
            log.info( "UserServiceImpl, loadUserByUsername Exception = " + e.getMessage() );
            return null;
        }
    }

    @Override
    public User findUserByName(String name) {
        log.info( "UserServiceImpl, loadUserByUsername username = " + name );
        try {
            return userRepository.findUserByName( name );
        } catch (Exception e) {
            log.info( "UserServiceImpl, loadUserByUsername Exception = " + e.getMessage() );
            return null;
        }
    }
}
