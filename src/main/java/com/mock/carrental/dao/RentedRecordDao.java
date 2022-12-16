package com.mock.carrental.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mock.carrental.dao.entity.RentedRecordEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface RentedRecordDao extends BaseMapper<RentedRecordEntity> {
}
