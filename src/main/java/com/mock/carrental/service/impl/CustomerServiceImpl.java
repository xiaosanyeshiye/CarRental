package com.mock.carrental.service.impl;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mock.carrental.dao.CustomerDao;
import com.mock.carrental.dao.entity.CustomerEntity;
import com.mock.carrental.exception.BizException;
import com.mock.carrental.service.ICustomerService;
import com.mock.carrental.service.dto.CustomerDto;
import com.mock.carrental.service.dto.command.CustomerCommand;
import com.mock.carrental.service.dto.command.LoginCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    public String login(LoginCommand command) {
        LambdaQueryWrapper<CustomerEntity> queryWrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotBlank(command.getLoginAccount())){
            queryWrapper.eq(CustomerEntity::getLoginAccount, command.getLoginAccount());
        }
        CustomerEntity customerEntity = customerDao.selectOne(queryWrapper);
        if (Objects.isNull(customerEntity)) {
            throw new BizException("The account does not exist, please contact the administrator.");
        }
        Date cur = new Date();
        // token expired
        if (StrUtil.isBlank(customerEntity.getToken()) || DateUtil.compare(customerEntity.getTokenExpireTime(), cur) <= 0) {
            String token = UUID.randomUUID().toString();

            customerEntity.setToken(token);
            customerEntity.setTokenExpireTime(DateUtil.offset(cur, DateField.DAY_OF_MONTH, 1));
            customerDao.updateById(customerEntity);

            return token;
        } else {
            return customerEntity.getToken();
        }
    }

    @Override
    public CustomerDto getCustomerByToken(String token) {
        LambdaQueryWrapper<CustomerEntity> queryWrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isBlank(token)){
            throw new BizException("Token not be empty.");
        }
        queryWrapper.eq(CustomerEntity::getToken, token);
        CustomerEntity customerEntity = customerDao.selectOne(queryWrapper);
        if (Objects.isNull(customerEntity)) {
            throw new BizException("The account does not exist, please contact the administrator.");
        }
        return CustomerDto.convertFromEntity(customerEntity);
    }

    @Override
    public CustomerDto createCustomer(CustomerCommand command) {
        LambdaQueryWrapper<CustomerEntity> queryWrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isBlank(command.getLoginAccount())){
            throw new BizException("Login account not be empty.");
        }
        if (StrUtil.isBlank(command.getCustomerName())){
            throw new BizException("Customer name not be empty.");
        }
        queryWrapper.eq(CustomerEntity::getLoginAccount, command.getLoginAccount());
        CustomerEntity customerEntity = customerDao.selectOne(queryWrapper);
        if (Objects.nonNull(customerEntity)) {
            throw new BizException("The account duplicate, please enter another account.");
        }

        customerEntity = new CustomerEntity();
        customerEntity.setCustomerName(command.getCustomerName());
        customerEntity.setLoginAccount(command.getLoginAccount());
        customerDao.insert(customerEntity);
        return CustomerDto.convertFromEntity(customerEntity);
    }
}
