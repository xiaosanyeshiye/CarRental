package com.mock.carrental.service.dto;

import cn.hutool.core.bean.BeanUtil;
import com.mock.carrental.dao.entity.CarEntity;
import com.mock.carrental.enums.CarStatusEnum;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
public class CarDto {

    private Long id;

    private String carName;

    /** default max time is 4 day **/
    private Integer maxTimeToRented;

    private Long rentedRecordId;

    private Date lastRentedTime;

    private CarStatusEnum carStatus;

    public static List<CarDto> convertListFromEntity(List<CarEntity> entityList) {
        List<CarDto> carList = new ArrayList<>();
        if (CollectionUtils.isEmpty(entityList)) {
            return carList;
        }
        entityList.forEach(carEntity -> carList.add(CarDto.convertFromEntity(carEntity)));
        return carList;
    }

    public static CarDto convertFromEntity(CarEntity carEntity) {
        CarDto carDto = new CarDto();
        if (Objects.isNull(carEntity)) {
            return carDto;
        }
        BeanUtil.copyProperties(carEntity, carDto);
        return carDto;
    }
}
