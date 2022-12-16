package com.mock.carrental.service;

import com.mock.carrental.common.base.PageList;
import com.mock.carrental.service.dto.CarDto;
import com.mock.carrental.service.dto.command.RentCarCommand;
import com.mock.carrental.service.dto.query.PageCarQuery;

public interface ICarService {

    CarDto getCar(Long carId);

    PageList<CarDto> listCars(PageCarQuery carQuery);

    void rentCar(RentCarCommand command);

    void returnCar(RentCarCommand command);
}
