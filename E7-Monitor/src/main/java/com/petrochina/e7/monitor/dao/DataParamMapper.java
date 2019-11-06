package com.petrochina.e7.monitor.dao;

import com.petrochina.e7.monitor.pojo.DataIndex;
import com.petrochina.e7.monitor.pojo.DataParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DataParamMapper {
    int deleteByPrimaryKey(Integer categoryParamId);

    int insert(DataParam record);

    int insertSelective(DataParam record);

    DataParam selectByPrimaryKey(Integer categoryParamId);

    int updateByPrimaryKeySelective(DataParam record);

    int updateByPrimaryKey(DataParam record);
    /**
     * @Author mzc
     * @Description //TODO 通过categoryID equipId 查询设备参数信息
     * @Date 15:54 2019/08/08 0008
     * @Param []
     * @return java.util.List<java.lang.String>
    *
     * @param categoryId
     * @param equipId*/
    List<DataParam> findByParam(String categoryId, String equipId,String paramType);
    List<DataParam> findByParam2(String categoryId, String equipId,String paramType);
    /**
     * @Author mzc
     * @Description //TODO 通过参数名查询
     * @Date 11:22 2019/08/19 0019
     * @Param []
     * @return void
    **/
    DataParam findByParamNm(String remark, String equipId);

    DataIndex findById(String id);

//    void finddd(String id,String equipId);

    /**
     * @Author mzc
     * @Description //TODO  通过设备id查询监测设备参数名称
     * @Date 14:11 2019/09/11 0011
     * @Param [equipId]
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
    **/
    List<Map<String,Object>> findParamByEquipId(String equipId);


    /**
     * @Author mzc
     * @Description //TODO 修改,查看页面参数和value值数据会回显
     * @Date 16:25 2019/10/19 0019
     * @Param [categoryId, equipId, monitorId]
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.String>>
    **/
    List<Map<String, String>> paramAndValueEcho(String categoryId, String equipId, String monitorId);
}