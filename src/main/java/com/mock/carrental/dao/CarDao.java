package com.mock.carrental.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mock.carrental.dao.entity.CarEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface CarDao extends BaseMapper<CarEntity> {


}
