package com.petrochina.e7.monitor.dao;

import com.petrochina.e7.monitor.pojo.Plan;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author mzc
 * @Description //TODO 监测计划Service
 * @Date 12:40 2019/07/31 0031
 * @Param
 * @return
**/
public interface PlanMapper {

    /**
     * @Author mzc
     * @Description //TODO 保存监测计划
     * @Date 13:13 2019/07/31 0031
     * @Param [record]
     * @return int
    **/
    int insert(Plan record);

    int insertSelective(Plan record);

    Plan selectByPrimaryKey(Integer planId);

    int updateByPrimaryKeySelective(Plan record);

    int updateByPrimaryKey(Plan record);
    /**
     * @Author mzc 
     * @Description //TODO 查询所有的监测计划
     * @Date 12:27 2019/07/31 0031
     * @Param []
     * @return java.util.List<com.petrochina.e7.monitor.project.pojo.Plan>
    **/
    List<Plan> selectPlans();
    /**
     * @Author mzc 
     * @Description //TODO 通过ID查询监测计划
     * @Date 12:31 2019/07/31 0031
     * @Param [id]
     * @return com.petrochina.e7.monitor.project.pojo.Plan
    **/
    Plan findPlanById(String id);
    /**
     * @Author mzc
     * @Description //TODO 通过ID删除 监测计划
     * @Date 12:31 2019/07/31 0031
     * @Param [id]
     * @return int
    **/
    int deletePlanById(Integer id);
    /**
     * @Author mzc 
     * @Description //TODO 通过ID删除多条监测计划
     * @Date 12:31 2019/07/31 0031
     * @Param [planIds]@Param("params") Map<String, Object
     * @return int
    **/
    int deletePlanByIds(@Param("ids") int[] ids);
    /**
     * @Author mzc 
     * @Description //TODO 跳转修改页面
     * @Date 12:31 2019/07/31 0031
     * @Param [id]
     * @return int
    **/
    int editPlanById(Plan plan);

    /**
     * @Author mzc
     * @Description //TODO 条件查询
     * @Date 16:17 2019/09/24 0024
     * @Param [plan]
     * @return void
    **/
    List<Plan> selectPlanByCon(Plan plan);
}