package com.mock.carrental.service.impl;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mock.carrental.common.base.BasePage;
import com.mock.carrental.common.base.PageList;
import com.mock.carrental.common.context.UserContext;
import com.mock.carrental.dao.RentedRecordDao;
import com.mock.carrental.dao.entity.RentedRecordEntity;
import com.mock.carrental.enums.RentedStatusEnum;
import com.mock.carrental.service.IRentedRecordService;
import com.mock.carrental.service.dto.RentedRecordDto;
import com.mock.carrental.service.dto.command.RentedRecordCommand;
import com.mock.carrental.service.dto.command.RentedRecordUpdateCommand;
import com.mock.carrental.service.dto.query.PageRentedRecordQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class RentedRecordServiceImpl implements IRentedRecordService {

    @Autowired
    private RentedRecordDao rentedRecordDao;

    @Override
    public PageList<RentedRecordDto> listRentedRecords(PageRentedRecordQuery rentedRecordQuery) {
        LambdaQueryWrapper<RentedRecordEntity> queryWrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotBlank(rentedRecordQuery.getCarName())) {
            queryWrapper.eq(RentedRecordEntity::getCarName, rentedRecordQuery.getCarName());
        }
        if (Objects.nonNull(rentedRecordQuery.getRentedStatus())) {
            queryWrapper.eq(RentedRecordEntity::getRentedStatus, rentedRecordQuery.getRentedStatus());
        }

        Page<RentedRecordEntity> rentedRecordEntityPage = rentedRecordDao.selectPage(new Page<>(rentedRecordQuery.getPageIndex(),
                rentedRecordQuery.getPageSize()), queryWrapper);
        PageList<RentedRecordDto> rentedRecordPage = new PageList<>();
        rentedRecordPage.setPage(new BasePage(rentedRecordEntityPage.getCurrent(), rentedRecordEntityPage.getSize(),
                rentedRecordEntityPage.getPages(), rentedRecordEntityPage.getTotal()));
        rentedRecordPage.setRecord(RentedRecordDto.convertListFromEntity(rentedRecordEntityPage.getRecords()));
        return rentedRecordPage;
    }

    @Override
    public RentedRecordDto genRecord(RentedRecordCommand command) {
        RentedRecordEntity rentedRecordEntity = new RentedRecordEntity();
        // fill base info
        rentedRecordEntity.setRentedStatus(RentedStatusEnum.RENTED);
        rentedRecordEntity.setCarId(command.getCarId());
        rentedRecordEntity.setCarName(command.getCarName());
        rentedRecordEntity.setCustomerId(UserContext.get().getCustomerId());
        // deal time info
        Date curTime = new Date();
        Date expectReturnTime = DateUtil.offset(curTime, DateField.DAY_OF_MONTH, command.getMaxTimeToRented());
        rentedRecordEntity.setStartRentedTime(curTime);
        rentedRecordEntity.setExpectReturnTime(expectReturnTime);
        rentedRecordDao.insert(rentedRecordEntity);
        return RentedRecordDto.convertFromEntity(rentedRecordEntity);
    }

    /***
     * modify record after return car
     * @param command necessary param for update record
     */
    @Override
    public void updateReturnRecord(RentedRecordUpdateCommand command) {
        RentedRecordEntity rentedRecordEntity = rentedRecordDao.selectById(command.getRentedRecordId());
        rentedRecordEntity.setActualReturnTime(new Date());
        rentedRecordEntity.setRentedStatus(RentedStatusEnum.RETURN);
        rentedRecordDao.updateById(rentedRecordEntity);
    }
}
