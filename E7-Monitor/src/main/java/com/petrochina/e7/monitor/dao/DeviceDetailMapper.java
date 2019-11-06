package com.petrochina.e7.monitor.dao;

import com.petrochina.e7.monitor.pojo.DeviceDetail;

public interface DeviceDetailMapper {
    int deleteByPrimaryKey(Integer detailId);

    int insert(DeviceDetail record);

    int insertSelective(DeviceDetail record);

    DeviceDetail selectByPrimaryKey(Integer detailId);

    int updateByPrimaryKeySelective(DeviceDetail record);

    int updateByPrimaryKey(DeviceDetail record);
}