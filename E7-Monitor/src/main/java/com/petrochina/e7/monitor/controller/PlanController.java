package com.petrochina.e7.monitor.controller;

import com.alibaba.fastjson.JSONObject;
import com.petrochina.e7.monitor.commons.controller.BaseController;
import com.petrochina.e7.monitor.commons.utils.JsonUtils;
import com.petrochina.e7.monitor.pojo.Plan;
import com.petrochina.e7.monitor.service.IMntEnumService;
import com.petrochina.e7.monitor.service.IPlanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author mzc
 * @ProjectName com.petrochina.e7.monitor.controller
 * @ClassName: PlanController
 * @Description: TODO  监测计划Controller
 * @Author: Administrator
 * @Date: 2019/07/28 0028$ 16:34$
 * @Version: 1.0
 */
@CrossOrigin()
@Api(value = "监测计划管理接口")
@RestController
@RequestMapping(value = "/project/plan")
public class PlanController extends BaseController {

    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private IPlanService PlanService;
    @Autowired
    private IMntEnumService mntEnumService;

    /**
     * @return java.lang.String
     * @Author mzc
     * @Description //TODO 监测计划查询所有
     * @Date 11:06 2019/07/30 0030
     * @Param []
     **/
    @GetMapping(value = "/list")
    @ApiOperation(value = "计划列表信息", notes = "查询监测计划列表信息")
    public String getPlans(@RequestParam(defaultValue = "1") String currentPage, @RequestParam(defaultValue = "10") String pagesize) {
        String plansJosn = PlanService.findAllPlan(currentPage, pagesize);
        logger.info("plansJosn:" + plansJosn);
        return plansJosn;
    }

    /**
     * @return java.lang.String
     * @Author mzc
     * @Description //TODO 通过ID查询监测计划信息
     * @Date 11:12 2019/07/30 0030
     * @Param []
     **/
    @GetMapping(value = "/find")
    @ApiOperation(value = "查询监测计划", notes = "通过id查询监测计划")
    @ApiImplicitParam(paramType = "query", name = "id", value = "监测计划Id", required = true, dataType = "String")
    public String findPlanByid(@RequestParam("id") String id) {
        String planJosn = PlanService.findPlanById(id);
        logger.info("id" + id +"<==>"+ "planJosn:" + planJosn);
        return planJosn;
    }

    /**
     * @Author mzc
     * @Description //TODO 多条件查询监测计划信息
     * @Date 10:03 2019/09/24 0024
     * @Param []
     * @return java.lang.String
    **/
    @PostMapping(value = "/searchByCon")
    @ApiOperation(value = "条件查询", notes = "条件查询")
    public String queryByCon(@RequestBody JSONObject con){
        String plansJson = null;
        try {
            Plan plan = JsonUtils.json2obj(JsonUtils.obj2json(con.get("plan")), Plan.class);
            String currentPage = con.get("currentPage").toString();
            String pagesize = con.get("pagesize").toString();
            plansJson = PlanService.searchByCon(currentPage, pagesize, plan);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return plansJson;
    }

    /**
     * @return java.lang.String
     * @Author mzc
     * @Description //TODO 跳转计划新增页面
     * @Date 17:18 2019/07/30 0030
     * @Param [mmap]
     **/
    @GetMapping(value = "/addpage")
    @ApiOperation(value = "跳转添加页面", notes = "跳转添加页面枚举值回显")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "PLAN_TYPE_CODE", value = "计划类型", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "PLAN_STATUS_CODE", value = "计划状态", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "SPECIALTY_CATEGORY_CODE", value = "专业类别码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "MONITOR_PROJECT_TYPE_CODE", value = "监测项目码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "MONITOR_PROJECT_NM_CODE", value = "监测项目名称码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "MONITOR_CONTENT_CODE", value = "监测内容码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "MONITOR_DEV_NM_CODE", value = "监测设备名称", required = true, dataType = "String")
    })
    public String toAddPage(@RequestParam("PLAN_TYPE_CODE") String PLAN_TYPE_CODE,
                            @RequestParam("PLAN_STATUS_CODE") String PLAN_STATUS_CODE,
                            @RequestParam("SPECIALTY_CATEGORY_CODE") String SPECIALTY_CATEGORY_CODE,
                            @RequestParam("MONITOR_PROJECT_TYPE_CODE") String MONITOR_PROJECT_TYPE_CODE,
                            @RequestParam("MONITOR_PROJECT_NM_CODE") String MONITOR_PROJECT_NM_CODE,
                            @RequestParam("MONITOR_CONTENT_CODE") String MONITOR_CONTENT_CODE,
                            @RequestParam("MONITOR_DEV_NM_CODE") String MONITOR_DEV_NM_CODE) {
        String planEchoJson = mntEnumService.findEchoPlan(PLAN_TYPE_CODE, PLAN_STATUS_CODE, SPECIALTY_CATEGORY_CODE, MONITOR_PROJECT_TYPE_CODE, MONITOR_PROJECT_NM_CODE, MONITOR_CONTENT_CODE, MONITOR_DEV_NM_CODE);
        logger.info(planEchoJson);
        return planEchoJson;
    }

    /**
     * @return java.lang.String
     * @Author mzc
     * @Description //TODO 新增监测计划
     * @Date 17:24 2019/07/30 0030
     * @Param [plan]
     **/
    @PostMapping(value = "/add")
    @ApiOperation(value = "添加监测计划", notes = "添加监测计划")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "currentPage", value = "当前页", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "pagesize", value = "每页条数", required = true, dataType = "String")
    })
    public String insertPlan(@RequestBody Map<String, Object> planJson) {
        String plansJson = null;
        try {
            Plan plan = JsonUtils.json2obj(JsonUtils.obj2json(planJson.get("plan")), Plan.class);
            String currentPage = planJson.get("currentPage").toString();
            String pagesize = planJson.get("pagesize").toString();
            PlanService.insert(plan);
            plansJson = getPlans(currentPage, pagesize);
            logger.info(plansJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plansJson;
    }

    /**
     * @return java.lang.String
     * @Author mzc
     * @Description //TODO 通过ID批量删除监测计划
     * @Date 17:31 2019/07/30 0030
     * @Param [id]
     **/
    @DeleteMapping(value = "/delete")
    @ApiOperation(value = "删除监测计划", notes = "通过id删除监测计划")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "currentPage",value = "当前页",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "pagesize",value = "每页条数",required = true,dataType = "String")
    })
    public String deletePlanById(@RequestBody Map<String, Object> PlanIds) {
        String replaceAll = PlanIds.get("PlanIds").toString().substring(1, PlanIds.get("PlanIds").toString().length() - 1).replaceAll(" ", "");
        String currentPage = PlanIds.get("currentPage").toString();
        String pagesize = PlanIds.get("pagesize").toString();
        PlanService.deletePlanByIds(replaceAll);
        String plansJson = getPlans(currentPage,pagesize);
        return plansJson;
    }


    /**
     * @return java.lang.String
     * @Author mzc
     * @Description //TODO 跳转到修改页面--->plan/edit.html
     * @Date 10:41 2019/07/31 0031
     * @Param [id]
     **/
    @GetMapping(value = "/editpage/{id}")
    @ApiOperation(value = "跳转修改页面", notes = "通过id修改监测计划")
    @ApiImplicitParam(paramType = "query", name = "id", value = "planId", required = true, dataType = "String")
    public String toEditPage(@RequestParam("id") String id) {
        String planJson = PlanService.findPlanById(id);
        logger.info(id +"planJson"+planJson);
//        Map<String,Object> mmap = new HashMap<String, Object>();
        //查询枚举值
        return planJson;
    }

    /**
     * @return java.lang.String
     * @Author mzc
     * @Description //TODO 修改监测计划后保存--->plan/plan_list.html
     * @Date 10:54 2019/07/31 0031
     * @Param [plan]
     **/
    @PostMapping(value = "/edit")
    @ApiOperation(value = "修改监测计划", notes = "修改后保存")
    public String editPlan(@RequestBody Map<String, Object> planJson) {
        String plansJson = null;
        try {
            Plan plan = JsonUtils.json2obj(JsonUtils.obj2json(planJson.get("plan")), Plan.class);
            String currentPage = planJson.get("currentPage").toString();
            String pagesize = planJson.get("pagesize").toString();
            PlanService.editPlanById(plan);
            plansJson = getPlans(currentPage, pagesize);
            logger.info(plansJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plansJson;
    }

}
