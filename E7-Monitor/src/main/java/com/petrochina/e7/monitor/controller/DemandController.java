package com.petrochina.e7.monitor.controller;

import com.alibaba.fastjson.JSONObject;
import com.petrochina.e7.monitor.commons.controller.BaseController;
import com.petrochina.e7.monitor.commons.utils.JsonUtils;
import com.petrochina.e7.monitor.pojo.Demand;
import com.petrochina.e7.monitor.service.IDemandService;
import com.petrochina.e7.monitor.service.IMntEnumService;
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
 * @Author mzc
 * @Description //TODO 监测需求Controller
 * @Date 14:27 2019/07/26 0026
 * @Param
 * @return
 **/
@CrossOrigin()
@Api(value = "监测需求管理接口")
@RestController
@RequestMapping(value = "/project/demand")
public class DemandController extends BaseController {

    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private IDemandService demandService;
    @Autowired
    private IMntEnumService mntEnumService;

    /**
     * @return java.lang.String
     * @Author mzc
     * @Description //TODO 查询所有监测需求list
     * @Date 14:17 2019/07/26 0026
     * @Param [model]
     **/
    @GetMapping(value = "/list")
    @ResponseBody
    @ApiOperation(value = "需求列表信息",notes = "需求列表信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "currentPage",value = "当前页",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "pagesize",value = "每页条数",required = true,dataType = "String")
    })
    public String getDemands(@RequestParam(defaultValue = "1") String currentPage, @RequestParam(defaultValue = "10") String pagesize) {
        String demandsJson = demandService.findAllDemand(currentPage,pagesize);
        logger.info("demandsJson:" + demandsJson);
        return demandsJson;
    }



    /**
     * @return java.lang.String
     * @Author mzc
     * @Description //TODO 通过ID查询单条监测需求
     * @Date 15:09 2019/07/28 0028
     * @Param [id, model]
     **/
    @GetMapping(value = "/find")
    @ApiOperation(value = "查询监测需求", notes = "查询指定需求信息")
    @ApiImplicitParam(paramType = "query", name = "id", value = "监测需求ID", required = true, dataType = "String")
    public String findDemandById(@RequestParam("id") String id) {
        String demandJson = demandService.findDemandById(id);
        logger.info(id + "demandJson:" + demandJson);
        return demandJson;
    }

    /**
     * @Author mzc
     * @Description //TODO 多条件查询监测需求信息
     * @Date 10:03 2019/09/24 0024
     * @Param []
     * @return java.lang.String
     **/
    @PostMapping(value = "/searchByCon")
    @ApiOperation(value = "条件查询", notes = "条件查询")
    public String queryByCon(@RequestBody JSONObject con){
        String demandsJson = null;
        try {
            Demand demand = JsonUtils.json2obj(JsonUtils.obj2json(con.get("demand")), Demand.class);
            String currentPage = con.get("currentPage").toString();
            String pagesize = con.get("pagesize").toString();
            demandsJson = demandService.searchDemandByCon(currentPage, pagesize, demand);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return demandsJson;
    }

    /**
     * @return java.lang.String
     * @Author mzc
     * @Description //TODO 跳转添加页面
     * @Date 16:35 2019/07/26 0027
     * @Param [mmap]
     **/
    @GetMapping("/addpage")
    @ApiOperation(value = "跳转添加页面", notes = "跳转添加页面查询枚举值")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "SPECIALTY_CATEGORY_CODE", value = "专业类别码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "MONITOR_PROJECT_TYPE_CODE", value = "监测项目码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "MONITOR_PROJECT_NM_CODE", value = "监测项目名称码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "MONITOR_CONTENT_CODE", value = "监测内容码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "MONITOR_DEV_NM_CODE", value = "监测设备名称", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "DEMAND_TYPE_CODE", value = "需求类型", required = true, dataType = "String")
    })
    public String toAddPage(@RequestParam("SPECIALTY_CATEGORY_CODE") String SPECIALTY_CATEGORY_CODE,
                            @RequestParam("MONITOR_PROJECT_TYPE_CODE") String MONITOR_PROJECT_TYPE_CODE,
                            @RequestParam("MONITOR_PROJECT_NM_CODE") String MONITOR_PROJECT_NM_CODE,
                            @RequestParam("MONITOR_CONTENT_CODE") String MONITOR_CONTENT_CODE,
                            @RequestParam("MONITOR_DEV_NM_CODE") String MONITOR_DEV_NM_CODE,
                            @RequestParam("DEMAND_TYPE_CODE") String DEMAND_TYPE_CODE) {
        String demandEchoJson = mntEnumService.findEchoDemand(SPECIALTY_CATEGORY_CODE, MONITOR_PROJECT_TYPE_CODE, MONITOR_PROJECT_NM_CODE,
                MONITOR_CONTENT_CODE,MONITOR_DEV_NM_CODE,DEMAND_TYPE_CODE);
        logger.info(demandEchoJson);
        return demandEchoJson;
    }

    /**
     * @return java.lang.String
     * @Author mzc
     * @Description //TODO 添加监测需求操作
     * @Date 16:36 2019/07/26 0027
     * @Param [demand]
     **/
    @PostMapping(value = "/add")
    @ApiOperation(value = "添加监测需求", notes = "添加监测需求")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "currentPage", value = "当前页", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "pagesize", value = "当前页条数", required = true, dataType = "String")
    })
    public String insertDemand(@RequestBody Map<String, Object> demandJson) {
        String demandsJson = null;
        try {
            Demand demand = JsonUtils.json2obj(JsonUtils.obj2json(demandJson.get("demand")), Demand.class);
            String currentPage = demandJson.get("currentPage").toString();
            String pagesize = demandJson.get("pagesize").toString();
            demandService.insert(demand);
            demandsJson = getDemands(currentPage, pagesize);
            logger.info(demandsJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return demandsJson;
    }


    /**
     * @return java.lang.String
     * @Author mzc
     * @Description //TODO 通过id批量删除需求信息
     * @Date 13:12 2019/07/28 0028
     * @Param [id]
     **/
    @DeleteMapping(value = "/delete")
    @ApiOperation(value = "删除监测需求", notes = "通过id删除监测需求信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "currentPage",value = "当前页",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "pagesize",value = "每页条数",required = true,dataType = "String")
    })
    public String deleteDemandById(@RequestBody Map<String,Object> DemandIds) {
       /* String demandIds = DemandIds.get("ids").toString();
        String currentPage = DemandIds.get("currentPage").toString();
        String pagesize = DemandIds.get("pagesize").toString();
        demandService.deleteDemandByIds(demandIds);
        String demandsJson = getDemands(currentPage, pagesize);
        return demandsJson;*/
        String replaceAll = DemandIds.get("ids").toString().substring(1, DemandIds.get("ids").toString().length() - 1).replaceAll(" ", "");
        String currentPage = DemandIds.get("currentPage").toString();
        String pagesize = DemandIds.get("pagesize").toString();
        demandService.deleteDemandByIds(replaceAll);
        String demandsJson = getDemands(currentPage,pagesize);
        return demandsJson;
    }


    /**
     * @return java.lang.String
     * @Author mzc
     * @Description //TODO 跳转修改页面
     * @Date 15:16 2019/07/28 0028
     * @Param [id, model]
     **/
    @GetMapping(value = "/editpage/{id}")
    @ApiOperation(value = "跳转修改页面", notes = "通过id查询监测需求信息回显页面")
    @ApiImplicitParam(paramType = "query", name = "id", value = "需求id", required = true, dataType = "String")
    public String toEditPage(@RequestParam("id") String id) {
        String demandJson = demandService.findDemandById(id);
//        Map<String,Object> mmap = new HashMap<String, Object>();
        //查询枚举值
        logger.info("demandJson:"+demandJson);
        return demandJson;
    }

    /**
     * @return java.lang.String
     * @Author mzc
     * @Description //TODO 修改保存监测需求信息
     * @Date 15:57 2019/07/28 0028
     * @Param [demand]
     **/
    @PostMapping(value = "/edit")
    @ApiOperation(value = "修改监测需求", notes = "监测需求修改")
    public String editDemand(@RequestBody Map<String, Object> demandJson) {
        String demandsJson = null;
        try {
            Demand demand = JsonUtils.json2obj(JsonUtils.obj2json(demandJson.get("demand")), Demand.class);
            String currentPage = demandJson.get("currentPage").toString();
            String pagesize = demandJson.get("pagesize").toString();
            demandService.editDemandById(demand);
            demandsJson = getDemands(currentPage, pagesize);
            logger.info(demandsJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return demandsJson;
    }

}
