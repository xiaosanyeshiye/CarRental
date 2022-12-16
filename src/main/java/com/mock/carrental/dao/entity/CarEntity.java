package com.mock.carrental.dao.entity;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mock.carrental.common.base.BaseEntity;
import com.mock.carrental.enums.CarStatusEnum;
import com.mock.carrental.service.dto.CarDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("t_car")
public class CarEntity extends BaseEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String carName;

    /** default max time is 4 day **/
    private Integer maxTimeToRented;

    private Long rentedRecordId;

    private Date lastRentedTime;

    private CarStatusEnum carStatus;

    public static CarEntity convertFromDto(CarDto carDto) {
        CarEntity carEntity = new CarEntity();
        if (Objects.isNull(carDto)) {
            return carEntity;
        }
        BeanUtil.copyProperties(carDto, carEntity);
        return carEntity;
    }
}
