package com.mock.carrental.common.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity implements Serializable {

    @TableField(
            fill = FieldFill.INSERT
    )
    private Date createTime;

    @TableField(
            fill = FieldFill.INSERT_UPDATE
    )
    private Date updateTime;

    @TableLogic
    private Integer isDeleted;
}
