package com.petrochina.e7.monitor.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.petrochina.e7.monitor.commons.utils.JsonUtils;
import com.petrochina.e7.monitor.commons.utils.Result;
import com.petrochina.e7.monitor.commons.utils.ResultUtil;
import com.petrochina.e7.monitor.commons.utils.StringUtils;
import com.petrochina.e7.monitor.dao.*;
import com.petrochina.e7.monitor.pojo.MonitorData;
import com.petrochina.e7.monitor.pojo.Report;
import com.petrochina.e7.monitor.service.IReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName com.petrochina.e7.monitor.service.impl
 * @ClassName: DataEntryServerImpl
 * @Description: TODO 监测数据录入ServiceImpl
 * @Author: Administrator
 * @Date: 2019/07/28 0028$ 17:17$
 * @Version: 1.0
 */

@Service
public class ReportServerImpl implements IReportService {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private  MonitorDataMapper monitorDataMapper;
    @Autowired
    private DataParamMapper dataParamMapper;
    @Autowired
    private DataIndexMapper dataIndexMapper;
    @Autowired
    private ParamValueMapper paramValueMapper;
    @Autowired
    private IndexValueMapper indexValueMapper;

    @Autowired
    private ReportMapper reportMapper;

    /**
     * @return java.util.List<com.petrochina.e7.monitor.pojo.Data>
     * @Author zs
     * @Description //TODO 查询所有监测报告数据录入信息
     * @Date 2019年10月15日11:14:00
     * @Param []
     **/
  /*  @Override
    public String findAllDatas(String currentPage, String pagesize) {
        *//*查询所有数据包括主数据/参数/指标*//*
        List<MonitorData> allMonitorData = null;
        String datasJson = null;
        try {
            PageHelper.startPage(Integer.valueOf(currentPage), Integer.valueOf(pagesize));
            allMonitorData = monitorDataMapper.finAllDatas();
            PageInfo<MonitorData> pageInfo = new PageInfo<MonitorData>(allMonitorData);
            List<Map<String, Object>> paramAndIndexList = new ArrayList<Map<String, Object>>();
            for (MonitorData monitorData : allMonitorData) {
                Map<String, Object> ooo = monitorDataResult(monitorData.getMonitorId().toString());
                paramAndIndexList.add(ooo);
            }
            Result dataResult = ResultUtil.success(paramAndIndexList, (int) pageInfo.getTotal());
            datasJson = JSON.toJSONStringWithDateFormat(dataResult, "yyyy-MM-dd", SerializerFeature.WriteDateUseDateFormat);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datasJson;
    }*/

    /**
     * @Author zs
     * @Description //TODO 条件查询
     * @Date 2019年10月16日09:20:52
     * @Param [currentPage, pagesize, monitorData]
     * @return java.lang.String
     **/
    public String searchByCons(String currentPage, String pagesize, Report report) {
        PageHelper.startPage(Integer.valueOf(currentPage),Integer.valueOf(pagesize));
        //   PageHelper.orderBy("MONITOR_ID desc");
//        List<MonitorData> allmonitorData = monitorDataMapper.searchByCons(monitorData);
        List<Report> allreport = reportMapper.search(report);
        PageInfo<Report> pageInfo = new PageInfo<>(allreport);
        Result reportResult = ResultUtil.success(pageInfo.getList(), (int) pageInfo.getTotal());
//        String monitorDataJson = JSON.toJSONString(monitorDataResult);
        String reportJson = JSON.toJSONStringWithDateFormat(reportResult, "yyyy-MM-dd", SerializerFeature.WriteDateUseDateFormat);
        return reportJson;
    }
    /**
    /**
     * @Author zs
     * @Description //TODO 查询所有
     * @Date 2019年10月15日16:41:40
     * @Param [currentPage, pagesize]
     * @return java.lang.String
     **/
    public String findAll(String currentPage, String pagesize) {
        PageHelper.startPage(Integer.valueOf(currentPage),Integer.valueOf(pagesize));
        List<Report> allReport = reportMapper.findAll();
        PageInfo<Report> pageInfo = new PageInfo<>(allReport);
        Result success = ResultUtil.success(pageInfo.getList(), (int) pageInfo.getTotal());
        String reportJson = JSON.toJSONStringWithDateFormat(success, "yyyy-MM-dd", SerializerFeature.WriteDateUseDateFormat);
        return reportJson;
    }



    /**
     * @return int
     * @Author zs
     * @Description //TODO 删除报告
     * @Date 2019年10月16日14:51:38
     * @Param [ids]
     **/
    public int deleteReportByIds(String reportIds) {
        int[] ids = StringUtils.stringConvertInt(reportIds);
        return reportMapper.deleteReportByIds(ids);
    }

    private Map<String,Object> monitorDataResult (String id){
        Map<String,Object> resultMap = new HashMap<>();
        Map<String, Object> monitorDataMap = null;
        try {
            MonitorData monitorData = monitorDataMapper.findById(id);
            monitorDataMap = JsonUtils.json2map(JsonUtils.obj2json(monitorData));

//参数------------------------------------------------------------------------------------------------
//            Map<String, String> paramJsonMap = getparamValue(monitorData.getEquipId(), monitorData.getMonitorId());
//        String s1 = JsonUtils.mapToString(paramJsonMap);
//指标------------------------------------------------------------------------------------------------
//            Map<String, String> indexJsonMap = getIndexValue(monitorData.getEquipId(), monitorData.getMonitorId());
//        String s2 = JsonUtils.mapToString(indexJsonMap);
//结果------------------------------------------------------------------------------------------------



//            monitorDataMap.put("dataParam", paramJsonMap);
//            monitorDataMap.put("dataIndex", indexJsonMap);


//        resultMap.put("monitorData", monitorDataMap);
//        resultMap.put("dataParam", paramJsonMap);
//        resultMap.put("dataIndex", indexJsonMap);
//        System.out.println("resultMap:>>>>>>"+resultMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return monitorDataMap;
    }
    /**
     * @Author mzc
     * @Description //TODO 通过设备id和监测数id获取参数值
     * @Date 16:35 2019/09/25 0025
     * @Param [equipId, monitorId]
     * @return java.util.Map<java.lang.String,java.lang.String>
     **/
//    private Map<String, String> getparamValue(Integer equipId, Integer monitorId){
//        List<Map<String,String>> paramList = new ArrayList<Map<String,String>>();
//        Map<String, String> paramMap = new HashMap<String, String>();
//        Map<String, String> pValueMap = new HashMap<String, String>();
//        /*通过设备id查询参数名称*/
//        List<Map<String, Object>> dataParams = dataParamMapper.findParamByEquipId(equipId);
//        for (Map<String, Object> mapParam : dataParams){
//            Object categoryParamId = mapParam.get("categoryParamId");
//            Object categoryParamName = mapParam.get("categoryParamName");
//            paramMap.put(categoryParamId.toString(),categoryParamName.toString());
//        }
//        /*通过monitorId查询参数值信息*/
//        List<Map<String, Object>> paramValues = paramValueMapper.findParamValueById(monitorId);
//        for(Map<String, Object> paramValueMap:paramValues){
//            Object categoryParamId = paramValueMap.get("categoryParamId");
//            Object categoryParamValue = paramValueMap.get("categoryParamValue");
//            pValueMap.put(categoryParamId.toString(),categoryParamValue.toString());
//        }
//        paramList.add(paramMap);
//        paramList.add(pValueMap);
//        Map<String, String> paramJsonMap = getHourMap(paramList);
//        return paramJsonMap;
//    }
    /**
     * @Author mzc
     * @Description //TODO  map 数据处理方法
     * @Date 13:49 2019/09/12 0012
     * @Param [dateLists]
     * @return java.util.Map<java.lang.String,java.lang.String>
     **/
    private Map<String,String> getHourMap(List<Map<String,String>> dateLists){
        Map<String,String> hourMap = new HashMap<>();
        for (Map.Entry<String, String> en : dateLists.get(0).entrySet()) {
            String paramId = en.getKey();
            String val = dateLists.get(1).get(en.getKey());
            String param = en.getValue();
            if (hourMap.containsKey(en.getKey())) {//结果map中有此key
                param += hourMap.get(en.getKey());
            }
            hourMap.put(en.getValue(), dateLists.get(1).get(en.getKey()));
        }
        return hourMap;
    }

    /**
     * @Author mzc
     * @Description //TODO 通过设备id和监测数id获取指标值
     * @Date 13:54 2019/09/12 0012
     * @Param [equipId, monitorId]
     * @return java.lang.String
     **/
//    private Map<String, String> getIndexValue(Integer equipId, Integer monitorId){
//        List<Map<String,String>> indexList = new ArrayList<Map<String,String>>();
//        HashMap<String, String> indexMap = new HashMap<String, String>();
//        HashMap<String, String> iValueMap = new HashMap<String,String>();
//        /*通过设备id查询指标名称*/
//        List<Map<String, Object>> dataIndexs = dataIndexMapper.findIndexByEquipId(equipId);
//        for(Map<String, Object> index : dataIndexs){
//            Object categoryIndexId = index.get("categoryIndexId");
//            Object categoryIndexName = index.get("categoryIndexName");
//            indexMap.put(categoryIndexId.toString(),categoryIndexName.toString());
//        }
//        /*通过数据id查询指标值*/
//        List<Map<String, Object>> indexValues = indexValueMapper.findIndexValueById(monitorId);
//        for(Map<String, Object> indexValue : indexValues){
////            System.out.println("indexValue:"+indexValue);
//            Object categoryIndexId = indexValue.get("categoryIndexId");
//            Object categoryIndexValue = indexValue.get("categoryIndexValue");
//            iValueMap.put(categoryIndexId.toString(),categoryIndexValue.toString());
//        }
//        indexList.add(indexMap);
//        indexList.add(iValueMap);
//        Map<String, String> indexJsonMap = getHourMap(indexList);
//        return indexJsonMap;
//    }

}
