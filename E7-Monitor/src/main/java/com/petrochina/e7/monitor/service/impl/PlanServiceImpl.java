package com.petrochina.e7.monitor.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.petrochina.e7.monitor.commons.utils.Result;
import com.petrochina.e7.monitor.commons.utils.ResultUtil;
import com.petrochina.e7.monitor.commons.utils.StringUtils;
import com.petrochina.e7.monitor.dao.PlanMapper;
import com.petrochina.e7.monitor.pojo.Plan;
import com.petrochina.e7.monitor.service.IPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mzc
 * @ProjectName com.petrochina.e7.monitor.project.service.impl
 * @ClassName: PlanServiceImpl
 * @Description: TODO 监测计划ServiceImpl
 * @Author: Administrator
 * @Date: 2019/07/29 0029$ 10:08$
 * @Version: 1.0
 */

@Service
public class PlanServiceImpl implements IPlanService {

    @Autowired
    private PlanMapper planMapper;

    /**
     * @return java.util.List<com.petrochina.e7.monitor.project.pojo.Plan>
     * @Author mzc
     * @Description //TODO 查询所有监测计划
     * @Date 11:16 2019/07/31 0031
     * @Param []
     **/
    @Override
    public String findAllPlan(String currentPage, String pagesize) {
        PageHelper.startPage(Integer.valueOf(currentPage),Integer.valueOf(pagesize));
        List<Plan> allPlan = planMapper.selectPlans();
        PageInfo<Plan> pageInfo = new PageInfo<>(allPlan);
        Result success = ResultUtil.success(pageInfo.getList(), (int) pageInfo.getTotal());
        String planJson = JSON.toJSONStringWithDateFormat(success, "yyyy-MM-dd", SerializerFeature.WriteDateUseDateFormat);
        return planJson;
    }

    /**
     * @return com.petrochina.e7.monitor.project.pojo.Plan
     * @Author mzc
     * @Description //TODO 通过ID查询监测计划
     * @Date 11:18 2019/07/31 0031
     * @Param []
     **/
    @Override
    public String findPlanById(String id) {
        Plan plan = planMapper.findPlanById(id);
        String planJson = JSON.toJSONStringWithDateFormat(ResultUtil.success(plan, 1), "yyyy-MM-dd", SerializerFeature.WriteDateUseDateFormat);
        return planJson;
}

    /**
     * @return int
     * @Author mzc
     * @Description //TODO 保存监测计划--->plan/plan_list.html
     * @Date 11:19 2019/07/31 0031
     * @Param [plan]
     **/
    @Override
    public int insert(Plan plan) {
        return planMapper.insert(plan);
    }

    /**
     * @return int
     * @Author mzc
     * @Description //TODO 通过ID删除监测计划--->plan/plan_list.html
     * @Date 12:08 2019/07/31 0031
     * @Param [id]
     **/
    @Override
    public int deletePlanById(Integer id) {
        return planMapper.deletePlanById(id);
    }
    /**
     * @return int
     * @Author mzc
     * @Description //TODO 删除监测计划
     * @Date 12:08 2019/07/31 0031
     * @Param [ids]
     **/
    @Override
    public int deletePlanByIds(String planIds) {
        int[] ids = StringUtils.stringConvertInt(planIds);
        return planMapper.deletePlanByIds(ids);
    }

    /**
     * @return int
     * @Author mzc
     * @Description //TODO 跳转到修改页面
     * @Date 12:09 2019/07/31 0031
     * @Param [id]
     **/
    @Override
    public int editPlanById(Plan plan) {
        return planMapper.editPlanById(plan);
    }

    /**
     * @Author mzc
     * @Description //TODO 条件查询
     * @Date 13:44 2019/09/24 0024
     * @Param [currentPage, pagesize, plan]
     * @return java.lang.String
    **/
    @Override
    public String searchByCon(String currentPage, String pagesize, Plan plan) {
        PageHelper.startPage(Integer.valueOf(currentPage),Integer.valueOf(pagesize));
//        PageHelper.orderBy("PLAN_ID desc");
        List<Plan> allPlan = planMapper.selectPlanByCon(plan);
        PageInfo<Plan> pageInfo = new PageInfo<>(allPlan);
        Result planResult = ResultUtil.success(pageInfo.getList(), (int) pageInfo.getTotal());
//        String planJson = JSON.toJSONString(success);
        String planJson = JSON.toJSONStringWithDateFormat(planResult, "yyyy-MM-dd", SerializerFeature.WriteDateUseDateFormat);
        return planJson;
    }
}
