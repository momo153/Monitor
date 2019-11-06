package com.petrochina.e7.monitor.dao;

import com.petrochina.e7.monitor.pojo.SysOrg;

public interface SysOrgMapper {
    int deleteByPrimaryKey(Long orgId);

    int insert(SysOrg record);

    int insertSelective(SysOrg record);

    SysOrg selectByPrimaryKey(Long orgId);

    int updateByPrimaryKeySelective(SysOrg record);

    int updateByPrimaryKeyWithBLOBs(SysOrg record);

    int updateByPrimaryKey(SysOrg record);
}