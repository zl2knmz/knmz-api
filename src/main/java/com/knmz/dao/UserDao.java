package com.knmz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.knmz.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserDao
 *
 * @Author: chenzeping
 * @Date: 2019/7/24 10:11
 */
@Mapper
public interface UserDao extends BaseMapper<User> {
}
