package com.mock.carrental.controller;

import com.mock.carrental.common.base.BaseResponse;
import com.mock.carrental.service.ICustomerService;
import com.mock.carrental.service.dto.command.CustomerCommand;
import com.mock.carrental.service.dto.command.LoginCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @PostMapping(value = "/login")
    public BaseResponse<String> login(@RequestBody LoginCommand command){
        String token = customerService.login(command);
        return BaseResponse.success("login success", token);
    }

    @PostMapping(value = "/createCustomer")
    public BaseResponse<Void> createCustomer(@RequestBody CustomerCommand command) {
        customerService.createCustomer(command);
        return BaseResponse.successMes("Create customer success, your login account is " + command.getLoginAccount());
    }
}
