package com.mock.carrental.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mock.carrental.dao.entity.CustomerEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDao extends BaseMapper<CustomerEntity> {
}
