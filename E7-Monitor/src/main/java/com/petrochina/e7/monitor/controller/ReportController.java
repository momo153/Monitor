package com.petrochina.e7.monitor.controller;

import com.petrochina.e7.monitor.commons.controller.BaseController;
import com.petrochina.e7.monitor.commons.utils.JsonUtils;
import com.petrochina.e7.monitor.pojo.Report;
import com.petrochina.e7.monitor.service.IDataService;
import com.petrochina.e7.monitor.service.IDeviceService;
import com.petrochina.e7.monitor.service.IMntEnumService;
import com.petrochina.e7.monitor.service.IReportService;
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
 * @ProjectName com.petrochina.e7.monitor.controller
 * @ClassName: DataEntryController
 * @Description: TODO
 * @Author: Administrator
 * @Date: 2019/07/28 0028$ 16:36$
 * @Version: 1.0
 */
@RestController
@RequestMapping(value = "/project/report")
@Api(value = "监测报告管理接口")
public class ReportController extends BaseController {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private IDataService dataService;
    @Autowired
    private IDeviceService deviceService;

    @Autowired
    private IReportService reportService;

    @Autowired
    private IMntEnumService mntEnumService;
    /**
     * @return java.lang.String
     * @Author mzc
     * @Description //TODO 查询所有
     * @Date 10:40 2019/08/07 0007
     * @Param []
     **/
/*    @GetMapping(value = "/list")
    @ApiOperation(value = "报告数据列表信息", notes = "查询所有监测报告数据列表信息")
    public String getDatas(@RequestParam(defaultValue = "1") String currentPage, @RequestParam(defaultValue = "10") String pagesize) {
        String dataJson = reportService.findAllDatas(currentPage, pagesize);
        logger.info("dataJson:" + dataJson);
        return dataJson;
    }*/
    /**
     * @Author zs
     * @Description //TODO 多条件查询
     * @Date 2019年10月15日14:30:51
     * @Param []
     * @return java.lang.String
    **/
    @PostMapping("/queryByCon")
    @ApiOperation(value = "条件查询", notes = "条件查询")
    public String queryByCon (@RequestBody Map<String, Object> con){
        String datajson = null;
        try {
            Report report = JsonUtils.json2obj(JsonUtils.obj2json(con.get("report")), Report.class);
            String currentPage = con.get("currentPage").toString();
            String pagesize = con.get("pagesize").toString();
            datajson = reportService.searchByCons(currentPage,pagesize,report);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datajson;
    }

    /**
     * @return java.lang.String
     * @Author mzc
     * @Description //TODO 查询所有监测报告数据2
     * @Date 10:40 2019/08/07 0007
     * @Param []
     **/
    @GetMapping(value = "/lists")
    @ApiOperation(value = "数据列表信息", notes = "查询所有监测数据列表信息")
    public String getReport(@RequestParam(defaultValue = "1") String currentPage, @RequestParam(defaultValue = "10") String pagesize) {
        String reportJson = reportService.findAll(currentPage, pagesize);
        logger.info("dataJson:" + reportJson);
        return reportJson;
    }

    /**
     * @return java.lang.String
     * @Author zs
     * @Description //TODO 通过ID批量删除监测报告
     * @Date 2019年10月16日14:47:01
     * @Param [id]
     **/
    @DeleteMapping(value = "/delete")
    @ApiOperation(value = "删除监测报告", notes = "通过id删除监测报告")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "currentPage",value = "当前页",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "pagesize",value = "每页条数",required = true,dataType = "String")
    })
    public String deleteReportById(@RequestBody Map<String, Object> ReportIds) {
        String replaceAll = ReportIds.get("ids").toString().substring(1, ReportIds.get("ids").toString().length() - 1).replaceAll(" ", "");
        String currentPage = ReportIds.get("currentPage").toString();
        String pagesize = ReportIds.get("pagesize").toString();
        reportService.deleteReportByIds(replaceAll);
        String reportJson = getReport(currentPage,pagesize);
        return reportJson;
    }


    /**
     * @return java.lang.String
     * @Author mzc
     * @Description //TODO 查询枚举值
     * @Date 16:35 2019/07/26 0027
     * @Param [mmap]
     **/
    @GetMapping("/findTypeCode")
    @ApiOperation(value = "查询枚举值", notes = "查询枚举值")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "MONITOR_PROJECT_TYPE_CODE", value = "监测项目类型", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "MONITOR_PROJECT_NM_CODE", value = "监测项目名称", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "REPORT_STATUS_CODE", value = "报告状态", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "SPECIALTY_CATEGORY_CODE", value = "专业类别", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "MONITOR_CONTENT_CODE", value = "监测内容", required = true, dataType = "String")


    })
    public String findTypeCode(@RequestParam("MONITOR_PROJECT_TYPE_CODE") String MONITOR_PROJECT_TYPE_CODE,
                               @RequestParam("REPORT_STATUS_CODE") String REPORT_STATUS_CODE,
                               @RequestParam("MONITOR_PROJECT_NM_CODE") String MONITOR_PROJECT_NM_CODE,
                               @RequestParam("SPECIALTY_CATEGORY_CODE") String SPECIALTY_CATEGORY_CODE,
                               @RequestParam("MONITOR_CONTENT_CODE") String MONITOR_CONTENT_CODE
                            ) {
        String demandEchoJson = mntEnumService.findTypeCode(MONITOR_PROJECT_TYPE_CODE,REPORT_STATUS_CODE,MONITOR_PROJECT_NM_CODE,SPECIALTY_CATEGORY_CODE,MONITOR_CONTENT_CODE);
        logger.info(demandEchoJson);
        return demandEchoJson;
    }
}