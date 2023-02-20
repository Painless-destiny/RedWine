package com.redwine.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author wyh
 * @since 2023-02-21 12:13:23
 */
@Getter
@Setter
@TableName("user")
@ApiModel(value = "User对象", description = "")
public class User {

    @ApiModelProperty("用户id")
    @TableId(value = "uid", type = IdType.AUTO)
    private Integer uid;

    @ApiModelProperty("用户名")
    @TableField("username")
    private String username;

    @ApiModelProperty("密码")
    @TableField("password")
    private String password;

    @ApiModelProperty("盐值")
    @TableField("salt")
    private String salt;

    @ApiModelProperty("电话号码")
    @TableField("phone")
    private String phone;

    @ApiModelProperty("电子邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty("性别:0-女，1-男")
    @TableField("gender")
    private Integer gender;

    @ApiModelProperty("头像")
    @TableField("avatar")
    private String avatar;

    @ApiModelProperty("是否删除：0-未删除，1-已删除")
    @TableField("is_delete")
    private Integer isDelete;

    @ApiModelProperty("日志-创建人")
    @TableField("created_user")
    private String createdUser;

    @ApiModelProperty("日志-创建时间")
    @TableField("created_time")
    private Date createdTime;

    @ApiModelProperty("日志-最后修改执行人")
    @TableField("modified_user")
    private String modifiedUser;

    @ApiModelProperty("日志-最后修改时间")
    @TableField("modified_time")
    private Date modifiedTime;


}
