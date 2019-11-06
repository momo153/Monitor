package com.petrochina.e7.monitor.service.impl;

import com.alibaba.fastjson.JSON;
import com.petrochina.e7.monitor.commons.utils.Result;
import com.petrochina.e7.monitor.commons.utils.ResultUtil;
import com.petrochina.e7.monitor.dao.MntEnumMapper;
import com.petrochina.e7.monitor.service.IMntEnumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName com.petrochina.e7.monitor.service
 * @ClassName: MntEnumService
 * @Description: TODO
 * @Author: Administrator
 * @Date: 2019/07/28 0028$ 15:59$
 * @Version: 1.0
 */

@Service
public class MntEnumServiceImpl implements IMntEnumService {

    @Autowired
    private MntEnumMapper mntEnumMapper;

    /**
     * @return java.util.List<java.lang.String>
     * @Author mzc
     * @Description //TODO 查询专业类别list<String></>
     * @Date 15:56 2019/07/26 0026
     * @Param []
     **/
    @Override
    public List<String> findSpec(String specCode) {
        return mntEnumMapper.findSpec(specCode);
    }

    /**
     * @return java.util.List<java.lang.String>
     * @Author mzc
     * @Description //TODO 查询监测内容list<String></>
     * @Date 16:55 2019/07/26 0026
     * @Param [contCode]
     **/
    @Override
    public List<String> findContent(String contCode) {
        return mntEnumMapper.findContent(contCode);
    }

    /**
     * @return java.util.List<java.lang.String>
     * @Author mzc
     * @Description //TODO 查询监测项目类型list<Sring></>
     * @Date 17:06 2019/07/26 0026
     * @Param [projectCode]
     **/
    @Override
    public List<String> findProject(String projectCode) {
        return mntEnumMapper.findProject(projectCode);
    }

    /**
     * @return java.util.List<java.lang.String>
     * @Author mzc
     * @Description //TODO 查询监测项目名称list<String></>
     * @Date 17:22 2019/07/26 0026
     * @Param [pNM]
     **/
    @Override
    public List<String> findprojectNM(String projectNM) {
        return mntEnumMapper.findProjectNM(projectNM);
    }
    /**
     * @Author mzc
     * @Description //TODO 监测计划类型(枚举/集团/板块/地区公司)
     * @Date 12:10 2019/07/31 0031
     * @Param [plan_type_code]
     * @return java.util.List<java.lang.String>
    **/
    @Override
    public List<String> findPlanType(String planTypeCode) {
        return mntEnumMapper.findPlanType(planTypeCode);
    }
    /**
     * @Author mzc
     * @Description //TODO 计划状态(枚举/未上报/上报)
     * @Date 12:11 2019/07/31 0031
     * @Param [plan_status_code]
     * @return java.util.List<java.lang.String>
    **/
    @Override
    public List<String> findPlanStatus(String planStatusCode) {

        return mntEnumMapper.findPlanStatus(planStatusCode);
    }

    /**
     * @Author mzc
     * @Description //TODO 需求查询枚举值并回显数据
     * @Date 16:03 2019/09/09 0009
     * @Param []
     * @return void
    *
     * @param SPECIALTY_CATEGORY_CODE
     * @param MONITOR_PROJECT_TYPE_CODE
     * @param MONITOR_PROJECT_NM_CODE
     * @param MONITOR_CONTENT_CODE
     * @param DEMAND_TYPE_CODE*/
    @Override
    public String findEchoDemand(String SPECIALTY_CATEGORY_CODE, String MONITOR_PROJECT_TYPE_CODE, String MONITOR_PROJECT_NM_CODE, String MONITOR_CONTENT_CODE,String MONITOR_DEV_NM_CODE,String DEMAND_TYPE_CODE) {

        Map<String, Object> mmap = new HashMap<String, Object>();
        //查询专业类别（枚举）
        mmap.put("specList", mntEnumMapper.findSpec("SPECIALTY_CATEGORY_CODE"));
        //监测项目类型（枚举）
        mmap.put("projectList", mntEnumMapper.findProject("MONITOR_PROJECT_TYPE_CODE"));
        //监测项目名称（模糊查询）
        mmap.put("projectNMList", mntEnumMapper.findProjectNM("MONITOR_PROJECT_NM_CODE"));
        //监测内容（模糊查询）
        mmap.put("contList", mntEnumMapper.findContent("MONITOR_CONTENT_CODE"));
        //查询监测设备名称
        mmap.put("devNm", mntEnumMapper.findDevNm("MONITOR_DEV_NM_CODE"));
        //需求类型
        mmap.put("demandType",mntEnumMapper.findDemandType("DEMAND_TYPE_CODE"));
        Result demandEchoResult = ResultUtil.success(mmap, mmap.size());
        String demandEchoJson = JSON.toJSONString(demandEchoResult);

        return demandEchoJson;
    }

    /**
     * @Author mzc
     * @Description //TODO 计划查询枚举值并回显数据
     * @Date 10:46 2019/09/11 0011
     * @Param [plan_type_code, plan_status_code, specialty_category_code, monitor_project_type_code, monitor_project_nm_code, monitor_content_code]
     * @return java.lang.String
     **/
    @Override
    public String findEchoPlan(String plan_type_code, String plan_status_code, String specialty_category_code, String monitor_project_type_code, String monitor_project_nm_code, String monitor_content_code, String monitor_dev_nm_code) {

        Map<String, Object> mmap = new HashMap<String, Object>();
        //计划类型(枚举)
        mmap.put("plantypeList", mntEnumMapper.findPlanType("PLAN_TYPE_CODE"));
        //计划状态(枚举)
        mmap.put("planStatusList", mntEnumMapper.findPlanStatus("PLAN_STATUS_CODE"));
        //监测单位
        //org
        //查询专业类别（枚举）
        mmap.put("specList", mntEnumMapper.findSpec("SPECIALTY_CATEGORY_CODE"));
        //监测项目类型（枚举）
        mmap.put("projectList", mntEnumMapper.findProject("MONITOR_PROJECT_TYPE_CODE"));
        //监测项目名称（模糊查询）
        mmap.put("contprojectNMList", mntEnumMapper.findProjectNM("MONITOR_PROJECT_NM_CODE"));
        //监测内容（模糊查询）
        mmap.put("contList", mntEnumMapper.findContent("MONITOR_CONTENT_CODE"));

        //查询监测设备名称
        mmap.put("devNm", mntEnumMapper.findDevNm("MONITOR_DEV_NM_CODE"));

//        JSONObject mmapJson = JsonUtils.mapToString(0,"",mmap.size(),mmap);
        Result planEchoResult = ResultUtil.success(mmap, mmap.size());
        String planEchoJson = JSON.toJSONString(planEchoResult);


        return planEchoJson;
    }


    /**
     * @Author mzc
     * @Description //TODO 数据录入查询枚举值并回显数据
     * @Date 16:05 2019/10/16 0016
     * @Param [specialty_category_code, monitor_project_type_code, monitor_project_nm_code, monitor_content_code, monitor_dev_nm_code]
     * @return java.lang.String
    **/
    @Override
    public String findEchoData(String specialty_category_code, String monitor_project_type_code, String monitor_project_nm_code, String monitor_content_code, String monitor_dev_nm_code) {
        Map<String, Object> mmap = new HashMap<String, Object>();

        //监测单位
        //org
        //查询专业类别（枚举）
        mmap.put("specList", mntEnumMapper.findSpec("SPECIALTY_CATEGORY_CODE"));
        //监测项目类型（枚举）
        mmap.put("projectList", mntEnumMapper.findProject("MONITOR_PROJECT_TYPE_CODE"));
        //监测项目名称（模糊查询）
        mmap.put("contprojectNMList", mntEnumMapper.findProjectNM("MONITOR_PROJECT_NM_CODE"));
        //监测内容（模糊查询）
        mmap.put("contList", mntEnumMapper.findContent("MONITOR_CONTENT_CODE"));
        //查询监测设备名称
        mmap.put("devNm", mntEnumMapper.findDevNm("MONITOR_DEV_NM_CODE"));

//        JSONObject mmapJson = JsonUtils.mapToString(0,"",mmap.size(),mmap);
        Result dataEchoResult = ResultUtil.success(mmap, mmap.size());
        String dataEchoJson = JSON.toJSONString(dataEchoResult);
        return dataEchoJson;
    }
    /**
     * @Author zs
     * @Date 2019年10月16日17:18:21
     * @Description //TODO 报告枚举获取
     * @param monitor_project_type_code
     * @param report_status_code
     * @param monitor_project_nm_code
     * @param specialty_category_code
     * @param monitor_content_code
     * @return
     */
    @Override
    public String findTypeCode(String monitor_project_type_code, String report_status_code, String monitor_project_nm_code, String specialty_category_code, String monitor_content_code) {
        Map<String, Object> mmap = new HashMap<String, Object>();
        //监测项目类型（枚举）
        mmap.put("projectList", mntEnumMapper.findProject("MONITOR_PROJECT_TYPE_CODE"));
        //报告状态(枚举)
        mmap.put("statusList", mntEnumMapper.findProject("REPORT_STATUS_CODE"));
        //专业类别(枚举)
        mmap.put("categoryList", mntEnumMapper.findProject("SPECIALTY_CATEGORY_CODE"));
        //监测项目名称(枚举)
        mmap.put("projectNmList", mntEnumMapper.findProject("MONITOR_PROJECT_NM_CODE"));
        //监测内容(枚举)
        mmap.put("contentList", mntEnumMapper.findProject("MONITOR_CONTENT_CODE"));
        Result planEchoResult = ResultUtil.success(mmap, mmap.size());
        String planEchoJson = JSON.toJSONString(planEchoResult);

        return planEchoJson;
    }
}
