package com.petrochina.e7.monitor.service;

import com.petrochina.e7.monitor.pojo.Plan;

/**
 * @ProjectName com.petrochina.e7.monitor.project.service
 * @ClassName: IPlanService
 * @Description: TODO 监测计划Service接口
 * @Author: Administrator
 * @Date: 2019/07/29 0029$ 10:07$
 * @Version: 1.0
 * @author mzc
 */

public interface IPlanService {

    /**
     * @Author mzc
     * @Description //TODO 查询所有监测计划
     * @Date 10:23 2019/07/29 0029
     * @Param []
     * @return void
    **/
    String findAllPlan(String currentPage, String pagesize);
    /**
     * @Author mzc
     * @Description //TODO 通过ID查询一条监测计划信息
     * @Date 11:11 2019/07/30 0030
     * @Param []
     * @return com.petrochina.e7.monitor.project.pojo.Plan
    **/
    String findPlanById(String id);

    /**
     * @Author mzc
     * @Description //TODO 监测计划新增
     * @Date 17:23 2019/07/30 0030
     * @Param [plan]
     * @return int
    **/
    int insert(Plan plan);

    /**
     * @Author mzc
     * @Description //TODO 通过ID删除监测计划
     * @Date 17:30 2019/07/30 0030
     * @Param [id]
     * @return int
     **/
    int deletePlanById(Integer id);
    /**
     * @Author mzc
     * @Description //TODO 通过ID批量删除监测计划
     * @Date 17:34 2019/07/30 0030
     * @Param [ids]
     * @return int
    **/
    int deletePlanByIds(String planIds);
    /**
     * @Author mzc
     * @Description //TODO 跳转修改页面
     * @Date 11:07 2019/07/31 0031
     * @Param [id]
     * @return int
    **/
    int editPlanById(Plan plan);

    /**
     * @Author mzc
     * @Description //TODO 条件查询
     * @Date 13:43 2019/09/24 0024
     * @Param [currentPage, pagesize, plan]
     * @return void
    **/
    String searchByCon(String currentPage, String pagesize, Plan plan);
}
