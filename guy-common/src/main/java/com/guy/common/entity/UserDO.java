package com.guy.common.entity;

import java.io.Serializable;
import java.util.Date;

import com.zhiyi.generalbeanplus.annotation.TargetTableName;
import lombok.Data;

/**
 * tb_user
 * @author 
 */
@Data
@TargetTableName("tb_user")
public class UserDO implements Serializable {
    private Integer id;

    /**
     * 64位的 雪花算法生成id
     */
    private Long uuid;

    /**
     * 名称
     */
    private String userName;

    /**
     * 部门id
     */
    private Integer deptId;

    /**
     * 密码 md5加密后的
     */
    private String userPassword;

    /**
     * 密码加密盐值
     */
    private String salt;

    private String createdAt;

    private String updatedAt;

    private String deletedAt;

    private static final long serialVersionUID = 1L;
}