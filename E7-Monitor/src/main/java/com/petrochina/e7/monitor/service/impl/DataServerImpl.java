package com.petrochina.e7.monitor.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.petrochina.e7.monitor.commons.utils.*;
import com.petrochina.e7.monitor.dao.*;
import com.petrochina.e7.monitor.pojo.*;
import com.petrochina.e7.monitor.service.IDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * @ProjectName com.petrochina.e7.monitor.service.impl
 * @ClassName: DataEntryServerImpl
 * @Description: TODO 监测数据录入ServiceImpl
 * @Author: Administrator
 * @Date: 2019/07/28 0028$ 17:17$
 * @Version: 1.0
 */

@Service
public class DataServerImpl implements IDataService {
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
    private MechanicalExcelMapper mechanicalExcelMapper;

    /**
     * @return java.util.List<com.petrochina.e7.monitor.pojo.Data>
     * @Author mzc
     * @Description //TODO 查询所有监测数据录入信息
     * @Date 15:36 2019/08/07 0007
     * @Param []
     **/
    @Override
    public String findAllData(String currentPage, String pagesize) {
        /*查询所有数据包括主数据/参数/指标*/
        List<MonitorData> allMonitorData = null;
        String datasJson = null;
        try {
            PageHelper.startPage(Integer.valueOf(currentPage), Integer.valueOf(pagesize));
            allMonitorData = monitorDataMapper.finAllData();
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
    }

    /**
     * @return com.petrochina.e7.monitor.pojo.Data
     * @Author mzc
     * @Description //TODO 通过ID查询监测数据录入信息
     * @Date 15:36 2019/08/07 0007
     * @Param [id]
     **/
    @Override
    public String findById(String id) {
        Map<String, Object> resultMap = monitorDataResult(id);
        String dataJson = JSON.toJSONStringWithDateFormat(ResultUtil.success(resultMap, 1), "yyyy-MM-dd", SerializerFeature.WriteDateUseDateFormat);

        return dataJson;
    }

    private Map<String,Object> monitorDataResult (String id){
        Map<String,Object> resultMap = new HashMap<>();
        Map<String, Object> monitorDataMap = null;
        try {
            MonitorData monitorData = monitorDataMapper.findById(id);
            String s = JsonUtils.obj2json(monitorData);
            System.out.println("s>>>>>"+s);
            monitorDataMap = JsonUtils.json2map(s);
//参数------------------------------------------------------------------------------------------------
            Map<String, String> paramJsonMap = getparamValue(monitorData.getEquipId(), monitorData.getMonitorId());
//        String s1 = JsonUtils.mapToString(paramJsonMap);
//指标------------------------------------------------------------------------------------------------
            Map<String, String> indexJsonMap = getIndexValue(monitorData.getEquipId(), monitorData.getMonitorId());
//        String s2 = JsonUtils.mapToString(indexJsonMap);
//结果------------------------------------------------------------------------------------------------
            monitorDataMap.put("dataParam", paramJsonMap);
            monitorDataMap.put("dataIndex", indexJsonMap);
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
    private Map<String, String> getparamValue(String equipId, Integer monitorId){
        List<Map<String,String>> paramList = new ArrayList<Map<String,String>>();
        Map<String, String> paramMap = new HashMap<String, String>();
        Map<String, String> pValueMap = new HashMap<String, String>();
        /*通过设备id查询参数名称*/
        List<Map<String, Object>> dataParams = dataParamMapper.findParamByEquipId(equipId);
        for (Map<String, Object> mapParam : dataParams){
            Object categoryParamId = mapParam.get("categoryParamId");
            Object categoryParamName = mapParam.get("categoryParamName");
            paramMap.put(categoryParamId.toString(),categoryParamName.toString());
        }
        /*通过monitorId查询参数值信息*/
        List<Map<String, Object>> paramValues = paramValueMapper.findParamValueById(monitorId);
        for(Map<String, Object> paramValueMap:paramValues){
            Object categoryParamId = paramValueMap.get("categoryParamId");
            Object categoryParamValue = paramValueMap.get("categoryParamValue");
            pValueMap.put(categoryParamId.toString(),categoryParamValue.toString());
        }
        paramList.add(paramMap);
        paramList.add(pValueMap);
        Map<String, String> paramJsonMap = getHourMap(paramList);
        return paramJsonMap;
    }


    /**
     * @Author mzc
     * @Description //TODO 通过设备id和监测数id获取指标值
     * @Date 13:54 2019/09/12 0012
     * @Param [equipId, monitorId]
     * @return java.lang.String
    **/
    private Map<String, String> getIndexValue(String equipId, Integer monitorId){
        List<Map<String,String>> indexList = new ArrayList<Map<String,String>>();
        HashMap<String, String> indexMap = new HashMap<String, String>();
        HashMap<String, String> iValueMap = new HashMap<String,String>();
        /*通过设备id查询指标名称*/
        List<Map<String, Object>> dataIndexs = dataIndexMapper.findIndexByEquipId(equipId);
        for(Map<String, Object> index : dataIndexs){
            Object categoryIndexId = index.get("categoryIndexId");
            Object categoryIndexName = index.get("categoryIndexName");
            indexMap.put(categoryIndexId.toString(),categoryIndexName.toString());
        }
        /*通过数据id查询指标值*/
        List<Map<String, Object>> indexValues = indexValueMapper.findIndexValueById(monitorId);
        for(Map<String, Object> indexValue : indexValues){
//            System.out.println("indexValue:"+indexValue);
            Object categoryIndexId = indexValue.get("categoryIndexId");
            Object categoryIndexValue = indexValue.get("categoryIndexValue");
            iValueMap.put(categoryIndexId.toString(),categoryIndexValue.toString());
        }
        indexList.add(indexMap);
        indexList.add(iValueMap);
        Map<String, String> indexJsonMap = getHourMap(indexList);
        return indexJsonMap;
    }



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
     * @return java.util.List<java.lang.String>
     * @Author mzc
     * @Description //TODO 查询设备参数信息
     * @Date 15:34 2019/08/08 0008
     * @Param []
     **/
    @Override
    public Map<String, String> findParam(String categoryId, String equipId, String paramType) {
        List<DataParam> paramList = dataParamMapper.findByParam(categoryId, equipId, paramType);
        Map<String, String> paramMap = new LinkedHashMap<>();
        for(DataParam param : paramList){
            paramMap.put(param.getCategoryParamName(),param.getRemark());
        }
        return paramMap;
    }

    /**
     * @return java.util.List<java.lang.String>
     * @Author mzc
     * @Description //TODO 查询设备指标信息
     * @Date 10:44 2019/08/09 0009
     * @Param [categoryId, equipId]
     **/
    @Override
    public Map<String, String> findIndex(String categoryId, String equipId) {
        List<DataIndex> indexList = dataIndexMapper.findByIndex(categoryId, equipId);
        Map<String, String> indexMap = new LinkedHashMap<>();
        for(DataIndex index : indexList){
            indexMap.put(index.getCategoryIndexName(),index.getRemark());
        }
        return indexMap;
    }

    /**
     * @return void
     * @Author mzc
     * @Description //TODO 保存数据录入主数据
     * @Date 09:57 2019/08/19 0019
     * @Param [data]
     **/
    @Override
    public int insert(MonitorData data) {
        int monitorId = monitorDataMapper.insert(data);
        return monitorId;
    }

    /**
     * @return void
     * @Author mzc
     * @Description //TODO 保存监测参数信息
     * @Date 10:38 2019/08/19 0019
     * @Param [dataParamMap]
     **/
    @Override
    public void insertAndUpdateParam(Map<String, Object> dataParamMap, Integer monitorId, String equipId) {
        List<ParamValue> paramList = paramValueMapper.selectParamBymonitorId(monitorId);
        for (Map.Entry<String, Object> entry : dataParamMap.entrySet()) {
            String remark = entry.getKey();
            DataParam dataParam = dataParamMapper.findByParamNm(remark, equipId);
            if (dataParam != null && paramList.size() == 0) {
                String value = (String) entry.getValue();
                ParamValue paramValue = new ParamValue();
                paramValue.setMonitorId(monitorId);
                paramValue.setCategoryParamId(dataParam.getCategoryParamId());
                paramValue.setCategoryParamValue(value);
                paramValueMapper.insert(paramValue);
            }
            if(dataParam != null && paramList.size() > 0){
                String value = (String) entry.getValue();
                ParamValue paramValue = new ParamValue();
                paramValue.setMonitorId(monitorId);
                paramValue.setCategoryParamId(dataParam.getCategoryParamId());
                paramValue.setCategoryParamValue(value);
                paramValueMapper.updateParamValue(paramValue);
            }
        }
    }

    /**
     * @return void
     * @Author mzc
     * @Description //TODO 保存监测指标信息
     * @Date 10:38 2019/08/19 0019
     * @Param [dataIndexMap]
     **/
    @Override
    public void insertAndUpdateIndex(Map<String, Object> dataIndexMap, Integer monitorId, String equipId) {
        List<Map<String, Object>> indexValueById = indexValueMapper.findIndexValueById(monitorId);
        for (Map.Entry<String, Object> entry : dataIndexMap.entrySet()) {
            String remark = entry.getKey();
            DataIndex dataIndex = dataIndexMapper.findByIndexNm(remark, equipId);
            if (dataIndex != null && indexValueById.size() ==0) {
                String value = (String) entry.getValue();
                IndexValue indexValue = new IndexValue();
                indexValue.setMonitorId(monitorId);
                indexValue.setCategoryIndexId(dataIndex.getCategoryIndexId());
                indexValue.setCategoryIndexValue(value);
                indexValueMapper.insert(indexValue);
            }
            if(dataIndex != null && indexValueById.size() > 0){
                String value = (String) entry.getValue();
                IndexValue indexValue = new IndexValue();
                indexValue.setMonitorId(monitorId);
                indexValue.setCategoryIndexId(dataIndex.getCategoryIndexId());
                indexValue.setCategoryIndexValue(value);
                indexValueMapper.updateIndexValue(indexValue);
            }
        }
    }

    /**
     * @Author mzc
     * @Description //TODO 删除监测数据
     * @Date 14:50 2019/09/16 0016
     * @Param [id]
     * @return int
    **/
    @Override
    public int deleteDataById(String id) {
        monitorDataMapper.deleteDataById(Integer.valueOf(id));
        return monitorDataMapper.deleteDataById(Integer.valueOf(id));
    }

    /**
     * @Author mzc
     * @Description //TODO 通过ids批量删除数据
     * @Date 17:21 2019/09/25 0025
     * @Param [ids]
     * @return int
    **/
    @Override
    public int deleteDataByIds(String ids) {
        int[] idsarr = StringUtils.stringConvertInt(ids);
//        for(){
//        }
        return monitorDataMapper.deleteDataByIds(idsarr);
    }

    /**
     * @Author mzc
     * @Description //TODO 条件查询
     * @Date 11:27 2019/09/26 0026
     * @Param [currentPage, pagesize, monitorData]
     * @return java.lang.String
    **/
    public String searchByCon(String currentPage, String pagesize, MonitorData monitorData){
        PageHelper.startPage(Integer.valueOf(currentPage),Integer.valueOf(pagesize));
        List<MonitorData> allmonitorData = monitorDataMapper.searchByCon(monitorData);
        PageInfo<MonitorData> pageInfo = new PageInfo<>(allmonitorData);
        Result monitorDataResult = ResultUtil.success(pageInfo.getList(), (int) pageInfo.getTotal());
//        String monitorDataJson = JSON.toJSONString(monitorDataResult);
        String monitorDataJson = JSON.toJSONStringWithDateFormat(monitorDataResult, "yyyy-MM-dd", SerializerFeature.WriteDateUseDateFormat);
        return monitorDataJson;
    }



    /**
     * @Author mzc
     * @Description //TODO 修改监测数据(主数据)
     * @Date 16:57 2019/10/08 0008
     * @Param [monitorId]
     * @return void
    **/
    @Override
    public void updateById(MonitorData data) {
        monitorDataMapper.updateByPrimaryKey(data);
    }


    /**
     * @Author mzc
     * @Description //TODO 查询参数名称
     * @Date 08:37 2019/10/16 0016
     * @Param [categoryId, equipId, paramType]
     * @return java.util.List<com.petrochina.e7.monitor.pojo.DataParam>
    **/
    @Override
    public List<DataParam> findParam2(String categoryId, String equipId, String paramType) {
        List<DataParam> paramList = dataParamMapper.findByParam2(categoryId, equipId, paramType);
        return paramList;

    }

    /**
     * @Author mzc
     * @Description //TODO 查询指标名称
     * @Date 08:38 2019/10/16 0016
     * @Param [categoryId, equipId]
     * @return java.util.List<com.petrochina.e7.monitor.pojo.DataIndex>
    **/
    @Override
    public List<DataIndex> findIndex2(String categoryId, String equipId) {
        List<DataIndex> indexList = dataIndexMapper.findByIndex2(categoryId,equipId);
        return indexList;
    }



    /**
     * @Author mzc
     * @Description //TODO 获取参数计算指标的值
     * @Date 14:55 2019/10/08 0008
     * @Param [models]
     * @return java.lang.String
     **/
    @Override
    public String cauculIdexEcho(JSONObject models) {
        Map<String, Object> paramMap = null;
        String result = null;
        try {
            String categoryId = models.get("categoryId").toString();
            String equipId = models.get("equipId").toString();
            Object dataParam = models.get("dataParam");
            paramMap = JsonUtils.json2map(JSON.toJSONString(dataParam));
//            result = cauculIdexEchoByFurnace(paramMap);
            if("集输系统".equals(categoryId) && "加热炉".equals(equipId)) {
                result = Calculation.cauculIdexEchoByFurnace(paramMap);
            }
            if("机采系统".equals(categoryId) && "抽油机".equals(equipId)){
                result = Calculation.cauculIdexEchoByPump(paramMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @Author mzc
     * @Description //TODO 查询最大id 返回
     * @Date 13:47 2019/10/17 0017
     * @Param []
     * @return int
     **/
    @Override
    public int findMonitorIdMax() {
        int monitorId = monitorDataMapper.findMonitorIdMax();
        return monitorId;
    }

    /**
     * @Author mzc
     * @Description //TODO 参数回显(没用到)
     * @Date 14:34 2019/10/20 0020
     * @Param [indexResultMap]
     * @return java.util.List<com.petrochina.e7.monitor.pojo.ParamValue>
    **/
    private List<ParamValue> paramEcho(Map<String, Object> indexResultMap){
        List<ParamValue> paramValueList = new ArrayList<>();
        return paramValueList;
    }

    /**
     * @Author mzc
     * @Description //TODO 修改,查看页面指标和参数value值数据回显
     * @Date 16:20 2019/10/19 0019
     * @Param [categoryId, equipId, monitorId]
     * @return java.lang.String
    **/
    @Override
    public String paramAndIndexEcho(String monitorId) {
        MonitorData monitorData = monitorDataMapper.findById(monitorId);
        List<Map<String, String>> paramAndValueEchoList = dataParamMapper.paramAndValueEcho(monitorData.getCategoryId(), monitorData.getEquipId(), monitorId);
        List<Map<String, String>> indexAndValueEchoList = dataIndexMapper.indexAndValueEcho(monitorData.getCategoryId(), monitorData.getEquipId(), monitorId );
        Map<String, List<Map<String, String>>> map = new HashMap();
        map.put("paramAndValue",paramAndValueEchoList);
        map.put("indexAndValue",indexAndValueEchoList);

        Result success = ResultUtil.success(map, map.size());
        System.out.println("success:>>>>>"+success);
        String paranAndIndexJson = JSON.toJSONStringWithDateFormat(success, "yyyy-MM-dd", SerializerFeature.WriteDateUseDateFormat);
        System.out.println(paranAndIndexJson);
        return paranAndIndexJson;
    }


    /**
     * @Description:
     * @Param: [file]
     * @return: java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @Author: hgq
     * @Date: 2019/10/17
     */
    @Override
    public List<MechanicalExcel> fetchMechanicalExcel(MultipartFile file) {
        List<MechanicalExcel> resultList = ExcelUtils.fetchMechanicalExcel(file);
        int a = mechanicalExcelMapper.insertList(resultList);
        System.out.println(a);
        return resultList;
    }

    @Override
    public Boolean findOriginalFilename(String originalFilename) {
        int a=  mechanicalExcelMapper.findOriginalFilename(originalFilename);
        Boolean flag =true ;
        if(a ==1){
            flag =false;
        }
        return flag;
    }
    //向日志表插入数据
    @Override
    public int fetchExcelLog(String excelName , Date date) {
        ExcelLog excelLog = new ExcelLog();
        excelLog.setExcelName(excelName);
        excelLog.setUploadTime(date);
        int flag =  mechanicalExcelMapper.fetchExcelLog(excelLog);
        return flag;
    }

//------10.30后修改---------------------------------------------------------------------------------------------------------------------------------------

    @Override
    public String ss() {

//        List<MonitorDataPuls> ssList = monitorDataMapper.ss();
        //查询汇总部分数(单位,设备名,数量)
        List<MonitorDataPuls> sumList = monitorDataMapper.findSum();
        List<MonitorDataPuls> list = new ArrayList<MonitorDataPuls>();
        for (MonitorDataPuls monitorDatap : sumList) {

            List<MonitorData> detail = monitorDataMapper.findDetail(monitorDatap.getLevelaOrg());
            monitorDatap.setMonitorDataList(detail);
            list.add(monitorDatap);
        }
        JSONObject jsonObject = JsonUtils.List2Json2(list);

        System.out.println("jsonObject:>>>>"+jsonObject);
        return null;
    }

}
