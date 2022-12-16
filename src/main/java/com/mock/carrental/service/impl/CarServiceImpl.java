package com.mock.carrental.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mock.carrental.common.base.BasePage;
import com.mock.carrental.common.base.PageList;
import com.mock.carrental.dao.CarDao;
import com.mock.carrental.dao.entity.CarEntity;
import com.mock.carrental.enums.CarStatusEnum;
import com.mock.carrental.exception.BizException;
import com.mock.carrental.service.ICarService;
import com.mock.carrental.service.IRentedRecordService;
import com.mock.carrental.service.dto.CarDto;
import com.mock.carrental.service.dto.RentedRecordDto;
import com.mock.carrental.service.dto.command.RentCarCommand;
import com.mock.carrental.service.dto.command.RentedRecordCommand;
import com.mock.carrental.service.dto.command.RentedRecordUpdateCommand;
import com.mock.carrental.service.dto.query.PageCarQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class CarServiceImpl implements ICarService {

    @Autowired
    private CarDao carDao;

    @Autowired
    private IRentedRecordService rentedRecordService;

    @Override
    public CarDto getCar(Long carId) {
        CarEntity carEntity = carDao.selectById(carId);
        return CarDto.convertFromEntity(carEntity);
    }

    @Override
    public PageList<CarDto> listCars(PageCarQuery carQuery) {
        LambdaQueryWrapper<CarEntity> queryWrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotBlank(carQuery.getCarName())){
            queryWrapper.like(CarEntity::getCarName, carQuery.getCarName());
        }
        if (Objects.nonNull(carQuery.getCarStatus())) {
            queryWrapper.eq(CarEntity::getCarStatus, carQuery.getCarStatus());
        }

        Page<CarEntity> carEntityPage = carDao.selectPage(new Page<>(carQuery.getPageIndex(),
                carQuery.getPageSize()), queryWrapper);
        PageList<CarDto> carPage = new PageList<>();

        carPage.setPage(new BasePage(carEntityPage.getCurrent(), carEntityPage.getSize(), carEntityPage.getPages(),
                carEntityPage.getTotal()));
        List<CarDto> carDtos = CarDto.convertListFromEntity(carEntityPage.getRecords());
        carPage.setRecord(carDtos);
        return carPage;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void rentCar(RentCarCommand command) {
        CarDto carDto = this.getCar(command.getCarId());
        if (!CarStatusEnum.canRent(carDto.getCarStatus())) {
            log.error("Car not free.");
            throw new BizException("Car not free.");
        }

        RentedRecordCommand rentedRecordCommand = new RentedRecordCommand(command.getCarId(), carDto.getCarName(),
                carDto.getMaxTimeToRented());
        RentedRecordDto recordDto = rentedRecordService.genRecord(rentedRecordCommand);

        CarEntity carEntity = CarEntity.convertFromDto(carDto);
        carEntity.setRentedRecordId(recordDto.getId());
        carEntity.setLastRentedTime(new Date());
        carEntity.setCarStatus(CarStatusEnum.RENTED);
        carDao.updateById(carEntity);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void returnCar(RentCarCommand command) {
        CarDto carDto = this.getCar(command.getCarId());
        if (!CarStatusEnum.canReturn(carDto.getCarStatus())) {
            log.error("Car not rented.");
            throw new BizException("Car not rented.");
        }

        CarEntity carEntity = CarEntity.convertFromDto(carDto);
        carEntity.setCarStatus(CarStatusEnum.FREE);
        carDao.updateById(carEntity);

        RentedRecordUpdateCommand updateCommand = new RentedRecordUpdateCommand(carDto.getRentedRecordId());
        rentedRecordService.updateReturnRecord(updateCommand);
    }
}
