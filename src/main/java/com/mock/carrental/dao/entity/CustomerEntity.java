package com.mock.carrental.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mock.carrental.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("t_customer")
public class CustomerEntity extends BaseEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String customerName;

    /** loginAccount unique **/
    private String loginAccount;

    private String token;

    private Date tokenExpireTime;
}
