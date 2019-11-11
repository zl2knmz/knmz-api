package com.knmz.dao;

import com.knmz.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * UserPlusDao
 *
 * @Author: chenzeping
 * @Date: 2019/9/27 11:02
 */
@Mapper
public interface UserPlusDao {
    @Update("UPDATE knmz_user SET `status` = 2, `nick` = #{nick}, `update_date` = NOW() WHERE `account` = #{account} ")
    boolean updateStatusAndNick(@Param("account") String id, @Param("nick") String nick);

    boolean batchUpdateNick(@Param("accounts") List<String> accounts, @Param("nick") String nick);

    boolean batchUpdateCreateData(@Param("list") List<User> list, @Param("createDate") String createDate);

    @Select("SELECT * FROM knmz_user WHERE `status` = 1 AND update_date > DATE_ADD(NOW(), INTERVAL - 15 DAY)")
    List<User> checkUserStatus();
}
