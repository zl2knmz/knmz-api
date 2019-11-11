package com.knmz.service;

import java.util.List;
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
     * @return boolean
     */
    boolean checkAccount(String phoneOrEmail);

    /**
     * 登录
     * @param phoneOrEmail 手机号或邮箱
     * @param password 密码
     */
    Map<String, Object> login(String phoneOrEmail, String password);

    /**
     * 用户详情
     * @param account 账号
     */
    Map<String, Object> getUserInfo(String account);

    /**
     * 修改昵称
     * @param account 账号
     * @param nick 昵称
     * @return boolean
     */
    boolean updateNick(String account, String nick);

    /**
     * 批量修改昵称
     * @param accountList 账号列表
     * @param nick 昵称
     * @return boolean
     */
    boolean batchUpdateNick(List<String> accountList, String nick);
}
