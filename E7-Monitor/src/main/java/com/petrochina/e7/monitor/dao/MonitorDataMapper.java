package com.petrochina.e7.monitor.dao;

import com.petrochina.e7.monitor.pojo.MonitorData;
import com.petrochina.e7.monitor.pojo.MonitorDataPuls;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface MonitorDataMapper {

    /**
     * @Author mzc
     * @Description //TODO 通过id删除监测数据信息
     * @Date 10:28 2019/09/12 0012
     * @Param [monitorId]
     * @return int
    **/
    int deleteDataById(Integer monitorId);
    @Options(useGeneratedKeys = true,keyProperty = "monitorId")
    int insert(MonitorData record);

    int insertSelective(MonitorData record);

    MonitorData selectByPrimaryKey(Integer monitorId);

    int updateByPrimaryKeySelective(MonitorData record);

    int updateByPrimaryKey(MonitorData record);

    /**
     * @Author mzc
     * @Description //TODO 查询所有监测数据录入信息
     * @Date 14:44 2019/08/07 0007
     * @Param []
     * @return java.util.List<com.petrochina.e7.monitor.pojo.Data>
     **/
    List<MonitorData> finAllData();
    /**
     * @Author mzc
     * @Description //TODO 通过id查询监测数据录入信息
     * @Date 14:45 2019/08/07 0007
     * @Param [id]
     * @return com.petrochina.e7.monitor.pojo.Data
     **/
    MonitorData findById(String id);

    /**
     * @Author mzc
     * @Description //TODO 通过id批量删除数据信息
     * @Date 17:32 2019/09/25 0025
     * @Param [idsarr]
     * @return int
    **/
    int deleteDataByIds(@Param("idsarr") int[] idsarr);

    /**
     * @Author mzc
     * @Description //TODO 条件查询
     * @Date 13:41 2019/09/26 0026
     * @Param []
     * @return java.util.List<com.petrochina.e7.monitor.pojo.MonitorData>
    **/
    List<MonitorData> searchByCon(MonitorData monitorData);

    /**
     * @Author mzc
     * @Description //TODO 查询monitorId最大值
     * @Date 19:41 2019/10/17 0017
     * @Param []
     * @return int
    **/
    int findMonitorIdMax();



    List<MonitorDataPuls> ss();



    List<MonitorDataPuls> findSum();

    List<MonitorData> findDetail(String  levelaOrg);
}