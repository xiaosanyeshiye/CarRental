package com.mock.carrental.test;

import com.mock.carrental.CarRentalApplication;
import com.mock.carrental.common.base.PageList;
import com.mock.carrental.common.context.UserContext;
import com.mock.carrental.common.context.UserSession;
import com.mock.carrental.enums.CarStatusEnum;
import com.mock.carrental.service.ICarService;
import com.mock.carrental.service.dto.CarDto;
import com.mock.carrental.service.dto.command.RentCarCommand;
import com.mock.carrental.service.dto.query.PageCarQuery;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CarRentalApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarServiceTest {

    @Autowired
    private ICarService carService;

    @Test
    public void testListCars() {
        PageCarQuery carQuery = new PageCarQuery();
        carQuery.setPageSize(2l);
        PageList<CarDto> carDtos = carService.listCars(carQuery);
        Assert.assertEquals("car num not 2", 2, carDtos.getRecord().size());
    }

    @Test
    public void testGetCar() {
        CarDto car = carService.getCar(1l);
        Assert.assertEquals("car id not 1", "1", String.valueOf(car.getId()));
    }

    @Test
    public void testRentCar() {
        UserContext.set(new UserSession(1l, "lu wei"));
        RentCarCommand rentCarCommand = new RentCarCommand();
        rentCarCommand.setCarId(1l);
        CarDto carDto = carService.rentCar(rentCarCommand);
        Assert.assertEquals("car can not rent", CarStatusEnum.RENTED, carDto.getCarStatus());

        UserContext.remove();
    }

    @Test
    public void testReturnCar() {
        RentCarCommand rentCarCommand = new RentCarCommand();
        rentCarCommand.setCarId(1l);
        CarDto carDto = carService.returnCar(rentCarCommand);
        Assert.assertEquals("car can not return", CarStatusEnum.FREE, carDto.getCarStatus());
    }
}
