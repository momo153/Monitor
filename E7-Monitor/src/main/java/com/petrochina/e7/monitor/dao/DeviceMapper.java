package com.petrochina.e7.monitor.dao;

import com.petrochina.e7.monitor.pojo.Device;

public interface DeviceMapper {
    int deleteByPrimaryKey(Integer equipId);

    int insert(Device record);

    int insertSelective(Device record);

    Device selectByPrimaryKey(Integer equipId);

    int updateByPrimaryKeySelective(Device record);

    int updateByPrimaryKey(Device record);
}