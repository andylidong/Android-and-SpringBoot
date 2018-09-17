package com.andy.heyi.common.token;

/**
 * 对Token进行操作的接口
 */
public interface TokenManager {

    /**
     * 创建一个token关联上指定用户
     *
     * @param userId 指定用户的id
     * @return 生成的token
     */
    Token createToken(String userId);

    /**
     * 从字符串中解析token
     *
     * @param token 加密后的字符串
     * @return
     */
    Token getTokenInfoByToken(String token);

    /**
     * 刷新token
     *
     * @param token
     */
    void refreshUserToken(String token);

    /**
     * 清除token
     */
    void deleteToken(String token);

}
