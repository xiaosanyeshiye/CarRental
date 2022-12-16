package com.mock.carrental.test;

import com.mock.carrental.CarRentalApplication;
import com.mock.carrental.common.base.PageList;
import com.mock.carrental.enums.CarStatusEnum;
import com.mock.carrental.enums.RentedStatusEnum;
import com.mock.carrental.service.IRentedRecordService;
import com.mock.carrental.service.dto.CarDto;
import com.mock.carrental.service.dto.RentedRecordDto;
import com.mock.carrental.service.dto.command.RentCarCommand;
import com.mock.carrental.service.dto.command.RentedRecordCommand;
import com.mock.carrental.service.dto.command.RentedRecordUpdateCommand;
import com.mock.carrental.service.dto.query.PageRentedRecordQuery;
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
public class RentedRecordServiceTest {

    @Autowired
    private IRentedRecordService iRentedRecordService;

    @Test
    public void testListRentedRecords() {
        PageRentedRecordQuery rentedRecordQuery = new PageRentedRecordQuery();
        rentedRecordQuery.setPageSize(0l);
        PageList<RentedRecordDto> rentedRecordDtos = iRentedRecordService.listRentedRecords(rentedRecordQuery);
        Assert.assertEquals("page query not use", 0, rentedRecordDtos.getRecord().size());
    }
}
