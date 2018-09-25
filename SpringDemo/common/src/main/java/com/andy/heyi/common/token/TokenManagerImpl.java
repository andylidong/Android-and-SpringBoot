package com.andy.heyi.common.token;

import com.andy.heyi.common.consts.Constants;
import com.andy.heyi.util.EmptyUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 通过Redis存储和验证token的实现类
 *
 * @see TokenManager
 */
@Component
public class TokenManagerImpl implements TokenManager {
    private RedisTemplate<String, Token> redis;

    @Resource
    public void setRedis(RedisTemplate redisTemplate) {
        this.redis = redisTemplate;
        // 泛型设置成Long后必须更改对应的序列化方案
        redis.setKeySerializer( new JdkSerializationRedisSerializer() );
    }

    @Override
    public Token createToken(String userId) {
        //使用uuid作为源token
        String token = UUID.randomUUID().toString().replace( "-", "" );
        Token model = new Token( userId, token );
        //存储到redis并设置过期时间
        redis.boundValueOps( token ).set( model, Constants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS );
        return model;
    }

    @Override
    public Token getTokenInfoByToken(String token) {
        if (EmptyUtil.isNotEmpty( token ) && redis.hasKey( token )) {
            Token model = redis.boundValueOps( token ).get();
            // 如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间
            redis.boundValueOps( token ).expire( Constants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS );
            return model;
        }
        return null;
    }

    @Override
    public void refreshUserToken(String token) {
        // 说明此用户进行了一次有效操作，延长token的过期时间
        redis.boundValueOps( token ).expire( Constants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS );
    }


    @Override
    public void deleteToken(String token) {
        redis.delete( token );
    }
}
