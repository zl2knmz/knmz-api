package com.knmz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.knmz.dao.UserDao;
import com.knmz.dao.UserPlusDao;
import com.knmz.entity.User;
import com.knmz.service.UserService;
import com.knmz.utils.AssertUtil;
import com.knmz.utils.DESUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * UserServiceImpl
 *
 * @author zl
 * @date 2019/7/23 20:47
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserPlusDao userPlusDao;

    @Override
    public boolean checkAccount(String account) {
        boolean isExist = false;
        if (StringUtils.isNotBlank(account)) {
            if (account.startsWith("+88609")) {
                account = account.replace("+88609", "+8869");
            }
            String finalAccount = account;
            QueryWrapper<User> filter = new QueryWrapper<User>();
            filter.and(f -> f.eq("phone", finalAccount).or().eq("email", finalAccount).or().eq("account", finalAccount));
            filter.eq("status", 1);
            isExist = userDao.selectCount(filter) > 0;
        }
        LOGGER.info("check account:" + account);
        return isExist;
    }

    @Override
    public Map<String, Object> login(String phoneOrEmail, String password) {
        Map<String, Object> userMap = null;
        if (StringUtils.isNotBlank(phoneOrEmail) && StringUtils.isNotBlank(password)) {
            if (phoneOrEmail.startsWith("+88609")) {
                phoneOrEmail = phoneOrEmail.replace("+88609", "+8869");
            }
            String finalPhoneOrEmail = phoneOrEmail;
            QueryWrapper<User> filter = new QueryWrapper<User>();
            filter.and(f -> f.eq("phone", finalPhoneOrEmail).or().eq("email", finalPhoneOrEmail));
//            filter.eq("password", DESUtils.encrypt4dotnet(password));
            filter.eq("password", password);
            filter.eq("status", 1);
            User user = userDao.selectOne(filter);
            AssertUtil.notNull(user, "帳號密碼不正確");
            userMap = exportUserInfo(user);
        }
        LOGGER.info("login phone or email :" + phoneOrEmail);
        return userMap;
    }


    @Override
    public Map<String, Object> getUserInfo(String account) {
        User user = userDao.selectById(account);
        return exportUserInfo(user);
    }

    @Override
    public boolean updateNick(String account, String nick) {
        return userPlusDao.updateNick(account, nick);
    }

    @Override
    public boolean batchUpdateNick(List<String> accounts, String nick) {
        return userPlusDao.batchUpdateNick(accounts, nick);
    }

    /**
     * 仅返回公开用户信息
     */
    private Map<String, Object> exportUserInfo(User user) {
        Map<String, Object> userMap = null;
        if (user != null) {
            userMap = new HashMap<>();
            userMap.put("account", user.getAccount());
            userMap.put("logo", user.getLogo());
            userMap.put("realName", user.getRealName());
            userMap.put("brief", user.getBrief());
        }
        return userMap;
    }

}