package com.knmz.service;

import java.util.Map;

/**
 * UserService
 *
 * @Author: chenzeping
 * @Date: 2019/7/23 19:01
 */
public interface UserService {
    /**
     * 账号检查
     * @param phoneOrEmail 手机号或者邮箱
     * @return b
     */
    boolean checkAccount(String phoneOrEmail);

    /**
     * 更新设备注册极光Id
     */
    Map<String, Object> login(String phoneOrEmail, String password);

    /**
     * 更新设备注册极光Id
     */
    Map<String, Object> getUserInfo(String account);

}
