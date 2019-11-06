package com.petrochina.e7.monitor.service;

import java.util.List;

/**
 * @ProjectName com.petrochina.e7.monitor.project.service
 * @ClassName: IMntEnumService
 * @Description: TODO
 * @Author: Administrator
 * @Date: 2019/07/28 0028$ 18:47$
 * @Version: 1.0
 */

public interface IMntEnumService {
    /**
     * @Author mzc
     * @Description //TODO 查询专业类别（枚举）
     * @Date 18:49 2019/07/28 0028
     * @Param [specialty_category_code]
     * @return java.lang.List<String>
    **/
    List<String> findSpec(String specialty_category_code);
    /**
     * @Author mzc
     * @Description //TODO 监测项目类型（枚举）
     * @Date 18:49 2019/07/28 0028
     * @Param [monitor_project_type_code]
     * @return java.lang.List<String>
    **/
    List<String> findProject(String monitor_project_type_code);
    /**
     * @Author mzc
     * @Description //TODO 监测内容（模糊查询）
     * @Date 18:50 2019/07/28 0028
     * @Param [monitor_content_code]
     * @return java.lang.List<String>
    **/
    List<String> findContent(String monitor_content_code);
    /**
     * @Author mzc
     * @Description //TODO 监测项目名称（模糊查询）
     * @Date 18:50 2019/07/28 0028
     * @Param [monitor_project_nm_code]
     * @return java.lang.List<String>
    **/
    List<String> findprojectNM(String monitor_project_nm_code);
    /**
     * @Author mzc
     * @Description //TODO  监测计划类型(枚举/集团/板块/地区公司)
     * @Date 17:06 2019/07/30 0030
     * @Param [plan_type_code]
     * @return java.util.List<java.lang.String>
    **/
    List<String> findPlanType(String plan_type_code);
    /**
     * @Author mzc
     * @Description //TODO 计划状态(枚举/未上报/上报)
     * @Date 17:06 2019/07/30 0030
     * @Param [plan_status_code]
     * @return java.util.List<java.lang.String>
    **/
    List<String> findPlanStatus(String plan_status_code);

    /**
     * @Author mzc
     * @Description //TODO 需求查询枚举值并回显数据
     * @Date 16:02 2019/09/09 0009
     * @Param []
     * @return void
    *
     * @param SPECIALTY_CATEGORY_CODE
     * @param MONITOR_PROJECT_TYPE_CODE
     * @param MONITOR_PROJECT_NM_CODE
     * @param MONITOR_CONTENT_CODE
     * @param MONITOR_CONTENT_CODE*/
    String findEchoDemand(String SPECIALTY_CATEGORY_CODE, String MONITOR_PROJECT_TYPE_CODE, String MONITOR_PROJECT_NM_CODE, String MONITOR_CONTENT_CODE,String MONITOR_DEV_NM_CODE,String DEMAND_TYPE_CODE);

    /**
     * @Author mzc
     * @Description //TODO 计划查询枚举值并回显数据
     * @Date 10:45 2019/09/11 0011
     * @Param [plan_type_code, plan_status_code, specialty_category_code, monitor_project_type_code, monitor_project_nm_code, monitor_content_code]
     * @return void
     **/
    String findEchoPlan(String plan_type_code, String plan_status_code, String specialty_category_code, String monitor_project_type_code, String monitor_project_nm_code, String monitor_content_code, String monitor_dev_nm_code);


    /**
     * @Author mzc
     * @Description //TODO 数据录入查询枚举值并回显数据
     * @Date 16:03 2019/10/16 0016
     * @Param [specialty_category_code, monitor_project_type_code, monitor_project_nm_code, monitor_content_code, monitor_dev_nm_code]
     * @return java.lang.String
    **/
    String findEchoData(String specialty_category_code, String monitor_project_type_code, String monitor_project_nm_code, String monitor_content_code, String monitor_dev_nm_code);

    /**
     * @Author zs
     * @Description //TODO
     * @Date 18:42 2019/10/29 0029
     * @Param [monitor_project_type_code, report_status_code, monitor_project_nm_code, specialty_category_code, monitor_content_code]
     * @return java.lang.String
    **/
    String findTypeCode(String monitor_project_type_code, String report_status_code, String monitor_project_nm_code, String specialty_category_code, String monitor_content_code);
}
