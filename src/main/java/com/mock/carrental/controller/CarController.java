package com.mock.carrental.controller;

import com.mock.carrental.common.annotation.VerifyToken;
import com.mock.carrental.common.base.BaseResponse;
import com.mock.carrental.common.base.PageList;
import com.mock.carrental.service.ICarService;
import com.mock.carrental.service.dto.CarDto;
import com.mock.carrental.service.dto.command.RentCarCommand;
import com.mock.carrental.service.dto.query.PageCarQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/car")
public class CarController {

    @Autowired
    private ICarService carService;

    @PostMapping(value = "/list")
    public BaseResponse<List<CarDto>> listCars(@RequestBody PageCarQuery carQuery){
        PageList<CarDto> list = carService.listCars(carQuery);
        return BaseResponse.success(list.getRecord(), list.getPage());
    }

    @VerifyToken
    @PostMapping(value = "/rent")
    public BaseResponse<Void> rentCar(@RequestBody RentCarCommand command){
        carService.rentCar(command);
        return BaseResponse.successMes("rent car success");
    }

    @VerifyToken
    @PostMapping(value = "/return")
    public BaseResponse<Void> returnCar(@RequestBody RentCarCommand command){
        carService.returnCar(command);
        return BaseResponse.successMes("return car success");
    }
}
