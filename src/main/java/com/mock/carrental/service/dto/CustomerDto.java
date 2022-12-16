package com.mock.carrental.service.dto;

import cn.hutool.core.bean.BeanUtil;
import com.mock.carrental.dao.entity.CustomerEntity;
import lombok.Data;

import java.util.Date;
import java.util.Objects;

@Data
public class CustomerDto {

    private Long id;

    private String customerName;

    private String loginAccount;

    private String token;

    private Date tokenExpireTime;

    public static CustomerDto convertFromEntity(CustomerEntity entity) {
        CustomerDto dto = new CustomerDto();
        if (Objects.isNull(entity)) {
            return dto;
        }
        BeanUtil.copyProperties(entity, dto);
        return dto;
    }
}
