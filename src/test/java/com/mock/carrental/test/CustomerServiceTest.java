package com.mock.carrental.test;

import cn.hutool.core.date.DateUtil;
import com.mock.carrental.CarRentalApplication;
import com.mock.carrental.common.base.PageList;
import com.mock.carrental.service.ICarService;
import com.mock.carrental.service.ICustomerService;
import com.mock.carrental.service.dto.CarDto;
import com.mock.carrental.service.dto.CustomerDto;
import com.mock.carrental.service.dto.command.CustomerCommand;
import com.mock.carrental.service.dto.command.LoginCommand;
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
public class CustomerServiceTest {

    @Autowired
    private ICustomerService customerService;

    @Test
    public void testLogin() {
        LoginCommand command = new LoginCommand();
        command.setLoginAccount("luw");
        String token = customerService.login(command);
        Assert.assertNotNull("login fail", token);
    }

    @Test
    public void testGetCustomerByToken() {
        LoginCommand command = new LoginCommand();
        command.setLoginAccount("luw");
        String token = customerService.login(command);
        CustomerDto customerDto = customerService.getCustomerByToken(token);
        Assert.assertNotNull("get customer fail", customerDto);
    }

    @Test
    public void testCreateAccount() {
        CustomerCommand command = new CustomerCommand();
        StringBuilder sb = new StringBuilder();
        sb.append("test_").append(DateUtil.today());
        command.setCustomerName(sb.toString());
        command.setLoginAccount(sb.toString());
        CustomerDto customerDto = customerService.createCustomer(command);
        Assert.assertNotNull("Create fail", customerDto.getId());
    }
}
