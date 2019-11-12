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
 * @author zl
 * @date 2019/9/27 11:02
 */
@Mapper
public interface UserPlusDao {
    /**
     * 修改昵称
     *
     * @param account 账号
     * @param nick    昵称
     * @return boolean
     */
    @Update("UPDATE knmz_user SET `nick` = #{nick}, `update_date` = NOW() WHERE `account` = #{account} ")
    boolean updateNick(@Param("account") String account, @Param("nick") String nick);

    /**
     * 批量修改昵称
     *
     * @param accountList 账号列表
     * @param nick        昵称
     * @return boolean
     */
    boolean batchUpdateNick(@Param("accountList") List<String> accountList, @Param("nick") String nick);

    /**
     * 批量修改创建时间
     *
     * @param list       用户列表
     * @param createDate 创建时间
     * @return boolean
     */
    boolean batchUpdateCreateData(@Param("list") List<User> list, @Param("createDate") String createDate);

    /**
     * 获取状态用户
     *
     * @return list
     */
    @Select("SELECT * FROM knmz_user WHERE `status` = 1 AND update_date > DATE_ADD(NOW(), INTERVAL - 15 DAY)")
    List<User> getUserStatus();
}
