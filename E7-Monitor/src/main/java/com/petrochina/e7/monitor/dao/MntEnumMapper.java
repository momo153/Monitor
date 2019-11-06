package com.petrochina.e7.monitor.dao;

import com.petrochina.e7.monitor.pojo.MntEnum;

import java.util.List;

public interface MntEnumMapper {
    int deleteByPrimaryKey(Integer codelistId);

    int insert(MntEnum record);

    int insertSelective(MntEnum record);

    MntEnum selectByPrimaryKey(Integer codelistId);

    int updateByPrimaryKeySelective(MntEnum record);

    int updateByPrimaryKey(MntEnum record);

    /**
     * @return java.util.List<java.lang.String>
     * @Author mzc
     * @Description //TODO 查询专业类别list<String></>
     * @Date 15:57 2019/07/26 0026
     * @Param []
     **/
    List<String> findSpec(String specCode);

    /**
     * @return java.util.List<java.lang.String>
     * @Author mzc
     * @Description //TODO 查询监测内容list<String></>
     * @Date 16:56 2019/07/26 0026
     * @Param [contCode]
     **/
    List<String> findContent(String contCode);

    /**
     * @return java.util.List<java.lang.String>
     * @Author mzc
     * @Description //TODO 查询监测项目类型list<Sring></>
     * @Date 17:07 2019/07/26 0026
     * @Param [projectCode]
     **/
    List<String> findProject(String projectCode);

    /**
     * @return java.util.List<java.lang.String>
     * @Author mzc
     * @Description //TODO 查询监测项目名称list<String></>
     * @Date 17:24 2019/07/26 0026
     * @Param [projectNM]
     **/
    List<String> findProjectNM(String projectNM);
    /**
     * @Author mzc
     * @Description //TODO 查询计划类型list<String></>
     * @Date 13:14 2019/07/31 0031
     * @Param [plan_type_code]
     * @return java.util.List<java.lang.String>
    **/
    List<String> findPlanType(String planTypeCode);
    /**
     * @Author mzc
     * @Description //TODO 查询计划状态list<String></>
     * @Date 13:14 2019/07/31 0031
     * @Param [plan_status_code]
     * @return java.util.List<java.lang.String>
    **/
    List<String> findPlanStatus(String planStatusCode);

    /**
     * @Author mzc
     * @Description //TODO 查询监测设备名称
     * @Date 15:02 2019/09/17 0017
     * @Param [monitor_dev_nm_code]
     * @return java.lang.Object
     **/
    List<String> findDevNm(String monitor_dev_nm_code);

    /**
     * @Author mzc
     * @Description //TODO 查询需求类型
     * @Date 09:34 2019/09/27 0027
     * @Param [demand_type_code]
     * @return java.lang.Object
    **/
    List<String> findDemandType(String demand_type_code);
}