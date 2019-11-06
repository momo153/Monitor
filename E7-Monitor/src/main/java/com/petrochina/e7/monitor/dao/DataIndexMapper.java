package com.petrochina.e7.monitor.dao;

import com.petrochina.e7.monitor.pojo.DataIndex;
import com.petrochina.e7.monitor.pojo.DataParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DataIndexMapper {
    int deleteByPrimaryKey(Integer categoryIndexId);

    int insert(DataIndex record);

    int insertSelective(DataIndex record);

    DataIndex selectByPrimaryKey(Integer categoryIndexId);

    int updateByPrimaryKeySelective(DataIndex record);

    int updateByPrimaryKey(DataIndex record);

    /**
     * @Author mzc
     * @Description //TODO 查询设备指标信息
     * @Date 10:47 2019/08/09 0009
     * @Param [categoryId, equipId]
     * @return java.util.List<java.lang.String>
     **/
    List<DataIndex> findByIndex(String categoryId, String equipId);
    List<DataIndex> findByIndex2(String categoryId, String equipId);
    /**
     * @Author mzc
     * @Description //TODO 通过指标名查詢指标信息
     * @Date 09:04 2019/08/20 0020
     * @Param [categoryIndexName]
     * @return void
     **/
    DataIndex findByIndexNm(String remark, String equipId);

    /**
     * @Author mzc
     * @Description //TODO 通过主数据id查询监测参数
     * @Date 14:42 2019/09/04 0004
     * @Param [id]
     * @return com.petrochina.e7.monitor.pojo.DataParam
     **/
    DataParam findById(String id);

    /**
     * @return void
     * @Author mzc
     * @Description //TODO  通过设备id查询监测设备指标名称
     * @Date 14:12 2019/09/11 0011
     * @Param [equipId]
     **/
    List<Map<String,Object>> findIndexByEquipId(String equipId);


    /**
     * @Author mzc
     * @Description //TODO 修改,查看页面指标和value值数据会回显
     * @Date 17:19 2019/10/19 0019
     * @Param [categoryId, equipId, monitorId]
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.String>>
    **/
    List<Map<String, String>> indexAndValueEcho(String categoryId, String equipId, String monitorId);
}