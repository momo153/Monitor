package com.petrochina.e7.monitor.dao;

import com.petrochina.e7.monitor.pojo.IndexValue;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface IndexValueMapper {
    int deleteByPrimaryKey(Integer monitorIndexId);

    int insert(IndexValue record);

    int insertSelective(IndexValue record);

    IndexValue selectByPrimaryKey(Integer monitorIndexId);

    int updateByPrimaryKeySelective(IndexValue record);

    int updateByPrimaryKey(IndexValue record);

    IndexValue selectByMonitorId(int parseInt);

    /**
     * @Author mzc
     * @Description //TODO 通过监测主数据id查询监测指标值信息
     * @Date 14:45 2019/09/11 0011
     * @Param [monitorId]
     * @return void
    **/
    List<Map<String,Object>> findIndexValueById(Integer monitorId);

    /**
     * @Author mzc
     * @Description //TODO 修改监测指标信息
     * @Date 14:15 2019/10/09 0009
     * @Param [indexValue]
     * @return int
    **/
    int updateIndexValue(IndexValue indexValue);

    /**
     * @Author mzc
     * @Description //TODO 通过监测主数据id查询监测指标信息
     * @Date 13:57 2019/10/09 0009
     * @Param [monitorId]
     * @return java.util.List<com.petrochina.e7.monitor.pojo.IndexValue>
    **/
//    List<IndexValue> selectIndexBymonitorId(Integer monitorId);
}