package com.mock.carrental.service;

import com.mock.carrental.service.dto.CustomerDto;
import com.mock.carrental.service.dto.command.LoginCommand;

public interface ICustomerService {

    String login(LoginCommand command);

    CustomerDto getCustomerByToken(String token);
}
