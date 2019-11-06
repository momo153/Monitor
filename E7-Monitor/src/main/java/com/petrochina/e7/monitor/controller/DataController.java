package com.petrochina.e7.monitor.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.petrochina.e7.monitor.commons.controller.BaseController;
import com.petrochina.e7.monitor.commons.utils.JsonUtils;
import com.petrochina.e7.monitor.config.CrossFilter;
import com.petrochina.e7.monitor.pojo.DataIndex;
import com.petrochina.e7.monitor.pojo.DataParam;
import com.petrochina.e7.monitor.pojo.MechanicalExcel;
import com.petrochina.e7.monitor.pojo.MonitorData;
import com.petrochina.e7.monitor.service.IDataService;
import com.petrochina.e7.monitor.service.IDeviceService;
import com.petrochina.e7.monitor.service.IMntEnumService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
@RequestMapping(value = "/project/data")
@Api(value = "监测数据管理接口")
public class DataController extends BaseController {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private IDataService dataService;
    @Autowired
    private IDeviceService deviceService;
    @Autowired
    private IMntEnumService mntEnumService;

    @Bean
    public FilterRegistrationBean crossFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new CrossFilter());
        registration.addUrlPatterns("/*");
        registration.setName("crossFilter");
        return registration;
    }

    /**
     * @return java.lang.String
     * @Author mzc
     * @Description //TODO 查询所有监测数据
     * @Date 10:40 2019/08/07 0007
     * @Param []
     **/
    @GetMapping(value = "/list")
    @ApiOperation(value = "数据列表信息", notes = "查询所有监测数据列表信息")
    public String getDatas(@RequestParam(defaultValue = "1") String currentPage, @RequestParam(defaultValue = "10") String pagesize) {
        String dataJson = dataService.findAllData(currentPage, pagesize);
        logger.info("dataJson:" + dataJson);
        return dataJson;
    }

    /**
     * @return java.lang.String
     * @Author mzc
     * @Description //TODO 通过id查询监测数据
     * @Date 10:36 2019/08/07 0007
     * @Param [id]
     **/
    @GetMapping(value = "/findById")
    @ApiOperation(value = "查询监测数据", notes = "通过id获取监测数据详细信息")
    @ApiImplicitParam(paramType = "query", name = "id", value = "监测数据ID", required = true, dataType = "String")
    public String findById(@RequestParam("id") String id) {
        String dataJson = dataService.findById(id);
        logger.info("id" + id + "<==>" + "dataJson" + dataJson);
        return dataJson;

//
    }

    /**
     * @return java.lang.String
     * @Author mzc
     * @Description //TODO 多条件查询
     * @Date 10:41 2019/09/26 0026
     * @Param []
     **/
    @PostMapping("/queryByCon")
    @ApiOperation(value = "条件查询", notes = "条件查询")
    public String queryByCon(@RequestBody Map<String, Object> con) {
        String datajson = null;
        try {
            MonitorData monitorData = JsonUtils.json2obj(JsonUtils.obj2json(con.get("MonitorData")), MonitorData.class);
            String currentPage = con.get("currentPage").toString();
            String pagesize = con.get("pagesize").toString();
            datajson = dataService.searchByCon(currentPage, pagesize, monitorData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datajson;
    }

    /**
     * @return java.lang.String
     * @Author mzc
     * @Description //TODO //数据录入新增之前准备数据(list列表数据/加热炉基本信息数据/参数名数据/指标名数据)
     * //通过组织机构查询监测设备信息,确定具体是哪台设备
     * @Date 09:34 2019/08/12 0012
     * @Param []
     **/
    public String findDeviceByOrg() {
//        deviceService.findParam();
        return "";
    }

    /**
     * @return java.lang.String
     * @Author mzc
     * @Description //TODO 查询设备参数名信息(参数:系统id/设备id/参数类型)
     * @Date 16:23 2019/08/28 0028
     * @Param []
     **/
    @GetMapping(value = "/findparam")
    @ApiOperation(value = "参数名称信息", notes = "通过体系/设备ID/参数类型查询参数名称信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "categoryId", value = "体系ID", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "equipId", value = "设备ID", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "paramType", value = "参数类型", required = true, dataType = "String")
    })
    public Map<String, String> findParam(@RequestParam("categoryId") String categoryId,
                                         @RequestParam("equipId") String equipId,
                                         @RequestParam("paramType") String paramType) {
        Map<String, String> paramMap = dataService.findParam(categoryId, equipId, paramType);
//        JSONObject paramJson = JsonUtils.List2Json(0, "", paramList.size(), paramList);
//        logger.info(paramJson.toString());
//        return paramJson.toString();
        return paramMap;
    }

    /**
     * @return java.lang.String
     * @Author mzc
     * @Description //TODO 查询设备指标名(参数:体系id/设备id)
     * @Date 17:00 2019/08/28 0028
     * @Param []
     **/
    @GetMapping(value = "/findindex")
    @ApiOperation(value = "指标名称信息", notes = "通过体系/设备Id查询指标名称信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "categoryId", value = "体系ID", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "equipId", value = "设备ID", required = true, dataType = "String"),
    })
    public Map<String, String> findIndex(@RequestParam("categoryId") String categoryId,
                                         @RequestParam("equipId") String equipId) {
        Map<String, String> indexMap = dataService.findIndex(categoryId, equipId);
//        JSONObject indexJson = JsonUtils.List2Json(0, "", indexList.size(), indexList);
//        return indexJson;
        return indexMap;
    }

    /**
     * @return java.lang.String
     * @Author mzc
     * @Description //TODO 修改,查看页面指标和参数value值数据会回显
     * @Date 10:37 2019/08/07 0007
     * @Param []
     **/
    @PostMapping(value = "/addpage")
    @ApiOperation(value = "跳转添加页面", notes = "通过体系/设备id/参数名称查询参数和指标名称信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "categoryId", value = "体系ID", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "equipId", value = "设备ID", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "paramType", value = "参数类型", required = false, dataType = "String")
    })
    public String toAddPage(@RequestParam("categoryId") String categoryId,
                            @RequestParam("equipId") String equipId,
                            @RequestParam("paramType") String paramType
    ) {
        Map<String, Object> paramAndIndexMap = new HashMap<String, Object>();
       /* Integer deviceId = 1;
        //查询设备基本信息
        deviceService.findDeviceDetailById(deviceId);*/
        //查询参数信息回显
        Map<String, String> paramMap = findParam(categoryId, equipId, paramType);
        //查询指标信息回显
        Map<String, String> indexMap = findIndex(categoryId, equipId);
        paramAndIndexMap.put("paramJson", paramMap);
        paramAndIndexMap.put("indexJson", indexMap);
        JSONObject paramAndIndexJson = JsonUtils.mapToString(0, "", paramAndIndexMap.size(), paramAndIndexMap);
        logger.info(paramAndIndexJson.toString());
        return paramAndIndexJson.toString();
    }

    /**
     * @return java.lang.String
     * @Author mzc
     * @Description //TODO 参数录入计算后指标值回显页面
     * @Date 14:59 2019/10/08 0008
     * @Param [models]
     **/
    //计算回显指标数据
    @PostMapping(value = "/calculecho")
    @ApiOperation(value = "计算结果回显", notes = "参数值录入回显计算结果信息")
//    @ApiImplicitParam(paramType = "query",name = "json",value = "datajson",required = true,dataType = "String")
    public String calculIndexEcho(@RequestBody JSONObject models) {
        String indexResultStr = dataService.cauculIdexEcho(models);
        return indexResultStr;
    }

    @PostMapping(value = "/add")
    @ApiOperation(value = "新增监测数据", notes = "新增监测数据")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "currentPage", value = "当前页", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "pagesize", value = "每页条数", required = true, dataType = "String")
    })
    public String insertData(@RequestBody JSONObject models, @RequestParam(defaultValue = "1") String currentPage, @RequestParam(defaultValue = "10") String pagesize) {
        try {
            Map<String, Object> stringObjectMap = JsonUtils.json2map(JsonUtils.obj2json(models.get("monitorData")));
            MonitorData data = JsonUtils.json2obj(JsonUtils.obj2json(stringObjectMap.get("data")), MonitorData.class);
            dataService.insert(data);
            //参数 k=v
            Map<String, Object> dataParamMap = JSONObject.parseObject(JSON.toJSONString(stringObjectMap.get("dataParam")));
            dataService.insertAndUpdateParam(dataParamMap, data.getMonitorId(), data.getEquipId());
            //指标 k=v
            Map<String, Object> dataIndexMap = JSONObject.parseObject(JSON.toJSONString(stringObjectMap.get("dataIndex")));
            dataService.insertAndUpdateIndex(dataIndexMap, data.getMonitorId(), data.getEquipId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getDatas(currentPage, pagesize);
    }

    /**
     * @return java.lang.String
     * @Author mzc
     * @Description //TODO 通过id批量删除
     * @Date 16:46 2019/09/25 0025
     * @Param []
     **/
    @DeleteMapping(value = "/delete")
    @ApiOperation(value = "删除监测数据", notes = "通过id删除监测数据")
    public String deleteByIds(@RequestBody Map<String, Object> dataIds) {
//        String ids = dataIds.get("ids").toString();
        String ids = dataIds.get("ids").toString().substring(1, dataIds.get("ids").toString().length() - 1).replaceAll(" ", "");
        String currentPage = dataIds.get("currentPage").toString();
        String pagesize = dataIds.get("pagesize").toString();
        dataService.deleteDataByIds(ids);
        String datas = getDatas(currentPage, pagesize);
        return datas;
    }

    /**
     * @return
     * @Author mzc
     * @Description //TODO 修改监测数据
     * @Date 15:54 2019/10/08 0008
     * @Param
     **/
    @PostMapping(value = "/edit")
    @ApiOperation(value = "修改监测数据", notes = "修改后保存")
    public String editMonitorData(@RequestBody JSONObject monitorDataJson) {
        String currentPage = null;
        String pagesize = null;
        try {
//            Object monitorData = monitorDataJson.get("monitorData");
            Map<String, Object> stringObjectMap = JsonUtils.json2map(JsonUtils.obj2json(monitorDataJson.get("monitorData")));
//            Object data = stringObjectMap.get("data");
            MonitorData data = JsonUtils.json2obj(JsonUtils.obj2json(stringObjectMap.get("data")), MonitorData.class);
            dataService.updateById(data);
            Map<String, Object> dataParamMap = JSONObject.parseObject(JSON.toJSONString(stringObjectMap.get("dataParam")));
            dataService.insertAndUpdateParam(dataParamMap, data.getMonitorId(), data.getEquipId());

            Map<String, Object> dataIndexMap = JSONObject.parseObject(JSON.toJSONString(stringObjectMap.get("dataIndex")));
            dataService.insertAndUpdateIndex(dataIndexMap, data.getMonitorId(), data.getEquipId());

            currentPage = stringObjectMap.get("currentPage").toString();
            pagesize = stringObjectMap.get("pagesize").toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  getDatas(currentPage, pagesize);
    }

    /**
     * @return java.lang.String
     * @Author mzc
     * @Description //TODO 跳转添加页面,查询设备指标和参数信息
     * @Date 10:37 2019/08/07 0007
     * @Param []
     **/
    @GetMapping(value = "/addpage2")
    @ApiOperation(value = "跳转添加页面", notes = "通过体系/设备id/参数名称查询参数和指标名称信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "categoryId", value = "体系ID", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "equipId", value = "设备ID", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "paramType", value = "参数类型", required = true, dataType = "String")
    })
    public String toAddPage2(@RequestParam("categoryId") String categoryId,
                             @RequestParam("equipId") String equipId,
                             @RequestParam("paramType") String paramType
    ) {

        Map<String, Object> paramAndIndexMap = new HashMap<String, Object>();
       /* Integer deviceId = 1;
        //查询设备基本信息
        deviceService.findDeviceDetailById(deviceId);*/
        //查询参数信息回显
        List<DataParam> dataParamList = dataService.findParam2(categoryId, equipId, paramType);
        //查询指标信息回显
//        Map<String, String> indexMap = findIndex(categoryId, equipId);
        List<DataIndex> dataIndexList = dataService.findIndex2(categoryId, equipId);
        paramAndIndexMap.put("paramJson", dataParamList);
        paramAndIndexMap.put("indexJson", dataIndexList);
        JSONObject paramAndIndexJson = JsonUtils.mapToString(0, "", paramAndIndexMap.size(), paramAndIndexMap);
        logger.info(paramAndIndexJson.toString());
        return paramAndIndexJson.toString();
    }

    /**
     * @return java.lang.String
     * @Author mzc
     * @Description //TODO 查询枚举值
     * @Date 15:55 2019/10/16 0016
     * @Param []
     **/
    @GetMapping(value = "/findenum")
    @ApiOperation(value = "查询枚举值", notes = "查询枚举值信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "SPECIALTY_CATEGORY_CODE", value = "专业类别码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "MONITOR_PROJECT_TYPE_CODE", value = "监测项目码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "MONITOR_PROJECT_NM_CODE", value = "监测项目名称码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "MONITOR_CONTENT_CODE", value = "监测内容码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "MONITOR_DEV_NM_CODE", value = "监测设备名称", required = true, dataType = "String")
    })
    public String findEnum(@RequestParam("SPECIALTY_CATEGORY_CODE") String SPECIALTY_CATEGORY_CODE,
                           @RequestParam("MONITOR_PROJECT_TYPE_CODE") String MONITOR_PROJECT_TYPE_CODE,
                           @RequestParam("MONITOR_PROJECT_NM_CODE") String MONITOR_PROJECT_NM_CODE,
                           @RequestParam("MONITOR_CONTENT_CODE") String MONITOR_CONTENT_CODE,
                           @RequestParam("MONITOR_DEV_NM_CODE") String MONITOR_DEV_NM_CODE) {
        String planEchoJson = mntEnumService.findEchoData(SPECIALTY_CATEGORY_CODE, MONITOR_PROJECT_TYPE_CODE, MONITOR_PROJECT_NM_CODE, MONITOR_CONTENT_CODE, MONITOR_DEV_NM_CODE);
        logger.info(planEchoJson);
        return planEchoJson;
    }

    /**
     * @return java.lang.String
     * @Author mzc
     * @Description //TODO 修改,查看页面指标和参数value值数据会回显
     * @Date 10:37 2019/08/07 0007
     * @Param []
     **/
    @PostMapping(value = "/paramAndIndexEcho")
    @ApiOperation(value = "查询参数和指标值回显", notes = "通过体系/设备id/参数名称查询参数和指标名称信息")
    @ApiImplicitParam(paramType = "query", name = "monitorId", value = "主数据ID", required = true, dataType = "String")
    public String paramAndIndexEcho(@RequestBody JSONObject json) {
        String monitorId = json.get("monitorId").toString();
        String paramAndIndexEchoJson = dataService.paramAndIndexEcho(monitorId);
        return paramAndIndexEchoJson;
    }


/*    @GetMapping(value = "/excelTest")
    @ApiOperation(value = "test", notes = "test")
    @ApiImplicitParam(paramType = "query", name = "param", value = "param", required = true, dataType = "String")
    public String test (@RequestParam("param") String param){

        Double aDouble = PumpUnitFormula.permeabilitK1(param);
        return aDouble.toString();
    }*/


    @ApiOperation("excel上传")
    @PostMapping(value ="/excelUpload",headers = "content-type=multipart/form-data")
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:8080")
    public String upload(@RequestParam("files[]") MultipartFile[] files) throws Exception {
//        MultipartFile file =files[0];
        StringBuffer massageList = new StringBuffer();
        StringBuffer massage = new StringBuffer();

        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            String str = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            if (str.equals(".xls") || str.equals(".xlsx")) {///判断是不是表格
                //去日志表查询 表名  如果 不重复 则添加
                String originalFilename = file.getOriginalFilename();
                Boolean flag = dataService.findOriginalFilename(originalFilename);
                if (flag == false) {
                    massage.append(str+"请勿重复上传或修改Excel表名;");
                }
                List<MechanicalExcel> list = dataService.fetchMechanicalExcel(file);
                //向日志表插入数据
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                String date = df.format(new Date());// new Date()为获取当前系统时间
                Date parse = df.parse(date);
                //向日志表插入数据
                int logFlag = dataService.fetchExcelLog(originalFilename, parse);
                System.out.println(list);
                System.out.println(logFlag);
            } else {
                massage.append(str+"不是表格,无法上传;");
            }
            if(massage.equals("")){
                massage.append(str+"上传成功,日志已记录;");
            }else{

            }
            massageList.append(massage);
            massage= new StringBuffer("");
        }
        return massageList.toString();
    }



    @ApiOperation("数据表格下载")
    @GetMapping(value="/downloadEntity")
    @ResponseBody
    public String down(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/x-download");
        response.addHeader("Content-Disposition", "attachment;filename=test.xls");
        Integer i  =  5;
        System.out.println("");
        response.setHeader("Content-Disposition","attachment;filename=test.xls");
        return "";
    }


    //显示信息(年份,组织机构信息,监测总数量,)
    @ApiOperation("查询组织机构信息")
    @GetMapping(value = "/getOrg")
    public String getOrg(){

        String ss = dataService.ss();



        return "";
    }

    @GetMapping("/download")
    public String downloadFile(@RequestParam("excelName") String excelName, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String filename = excelName;



        return "";
    }


}