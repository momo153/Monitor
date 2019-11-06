package com.petrochina.e7.monitor.dao;

import com.petrochina.e7.monitor.pojo.ParamValue;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ParamValueMapper {
    int deleteByPrimaryKey(Integer monitorParamId);

    int insert(ParamValue record);

    int insertSelective(ParamValue record);

    List<ParamValue> selectByPrimaryKey(Integer monitorParamId);

    int updateByPrimaryKeySelective(ParamValue record);

    int updateByPrimaryKey(ParamValue record);

    /**
     * @Author mzc
     * @Description //TODO 修改监测参数信息
     * @Date 14:15 2019/10/09 0009
     * @Param [record]
     * @return int
    **/
    int updateParamValue(ParamValue record);
    /**
     * @Author mzc
     * @Description //TODO 通过监测主数据id查询监测参数值信息
     * @Date 14:45 2019/09/11 0011
     * @Param [monitorId]
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
    **/
    List<Map<String,Object>> findParamValueById(Integer monitorId);
    
    /**
     * @Author mzc 
     * @Description //TODO 通过monitorId查询参数值
     * @Date 09:33 2019/10/09 0009
     * @Param [i]
     * @return java.util.List<com.petrochina.e7.monitor.pojo.ParamValue>
    **/
    List<ParamValue> selectParamBymonitorId(Integer monitorId);

    /**
     * @Author mzc
     * @Description //TODO 通过monitorId查询参数值信息
     * @Date 16:07 2019/09/04 0004
     * @Param [monitorId]
     * @return com.petrochina.e7.monitor.pojo.ParamValue
    **/
//    List<DataParam> selectByMonitorId(Integer monitorId, Integer equipId);
}