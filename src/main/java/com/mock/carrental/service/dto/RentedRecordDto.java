package com.mock.carrental.service.dto;

import cn.hutool.core.bean.BeanUtil;
import com.mock.carrental.dao.entity.CarEntity;
import com.mock.carrental.dao.entity.RentedRecordEntity;
import com.mock.carrental.enums.RentedStatusEnum;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
public class RentedRecordDto {

    private Long id;

    private Long customerId;

    private Long carId;

    private Date startRentedTime;

    private Date expectReturnTime;

    private Date actualReturnTime;

    private RentedStatusEnum rentedStatus;

    public static List<RentedRecordDto> convertListFromEntity(List<RentedRecordEntity> records) {
        List<RentedRecordDto> recordList = new ArrayList<>();
        if (CollectionUtils.isEmpty(records)) {
            return recordList;
        }
        records.forEach(entity -> recordList.add(RentedRecordDto.convertFromEntity(entity)));
        return recordList;
    }

    public static RentedRecordDto convertFromEntity(RentedRecordEntity rentedRecordEntity) {
        RentedRecordDto recordDto = new RentedRecordDto();
        if (Objects.isNull(rentedRecordEntity)) {
            return recordDto;
        }
        BeanUtil.copyProperties(rentedRecordEntity, recordDto);
        return recordDto;
    }
}
