package com.knmz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.knmz.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserMapper
 *
 * @Author: chenzeping
 * @Date: 2019/7/24 10:11
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
