package com.knmz.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * User
 *
 * @Author: chenzeping
 * @Date: 2019/7/24 10:11
 */
@Data
@TableName("knmz_user")
public class User {
    @TableId
    private String account;
    private String logo;
    private String nick;
    @TableField("real_name")
    private String realName;
    private String brief;

    private String email;
    private String phone;

    @TableField("jpush_id")
    private String jpushId;

    @TableField("create_date")
    private LocalDateTime createDate;

    @TableField("update_date")
    private LocalDateTime updateDate;
}
