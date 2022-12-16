package com.mock.carrental.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mock.carrental.common.base.BaseEntity;
import com.mock.carrental.enums.RentedStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("t_rented_record")
public class RentedRecordEntity extends BaseEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long customerId;

    private Long carId;

    private String carName;

    private Date startRentedTime;

    private Date expectReturnTime;

    private Date actualReturnTime;

    private RentedStatusEnum rentedStatus;
}
