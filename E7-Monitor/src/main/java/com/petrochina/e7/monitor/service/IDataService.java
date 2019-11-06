package com.petrochina.e7.monitor.service;

import com.alibaba.fastjson.JSONObject;
import com.petrochina.e7.monitor.pojo.DataIndex;
import com.petrochina.e7.monitor.pojo.DataParam;
import com.petrochina.e7.monitor.pojo.MechanicalExcel;
import com.petrochina.e7.monitor.pojo.MonitorData;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName com.petrochina.e7.monitor.project.service
 * @ClassName: IDataEntryServer
 * @Description: TODO 监测数据录入Service接口
 * @Author: Administrator
 * @Date: 2019/07/28 0028$ 17:15$
 * @Version: 1.0
 */
public interface IDataService {

    /**
     * @Author mzc
     * @Description //TODO 查询所有监测数据list
     * @Date 14:35 2019/08/07 0007
     * @Param []
     * @return java.util.List<com.petrochina.e7.monitor.pojo.Data>
    *
     * @param currentPage
     * @param pagesize*/
    String findAllData(String currentPage, String pagesize);
    /**
     * @Author mzc
     * @Description //TODO 通过ID查询具体监测数据
     * @Date 14:36 2019/08/07 0007
     * @Param [id]
     * @return com.petrochina.e7.monitor.pojo.Data
    **/
    String findById(String id);

    /**
     * @Author mzc
     * @Description //TODO 查询设备参数信息
     * @Date 15:33 2019/08/08 0008
     * @Param []
     * @return java.util.List<java.lang.String>
    **/
    Map<String, String> findParam(String categoryId,String equipId,String paramType);

    /**
     * @Author mzc 
     * @Description //TODO  查询设备指标信息
     * @Date 10:41 2019/08/09 0009
     * @Param [categoryId, equipId]
     * @return java.util.List<java.lang.String>
    **/
    Map<String, String> findIndex(String categoryId, String equipId);

    /**
     * @Author mzc
     * @Description //TODO 保存数据录入主数据
     * @Date 09:57 2019/08/19 0019
     * @Param [data]
     * @return void
    **/
    int insert(MonitorData data);
    /**
     * @Author mzc
     * @Description //TODO 保存监测参数信息
     * @Date 10:36 2019/08/19 0019
     * @Param [dataParamMap]
     * @return void
    **/
    void insertAndUpdateParam(Map<String, Object> dataParamMap,Integer monitor, String equipId);
    /**
     * @Author mzc
     * @Description //TODO 保存监测指标信息
     * @Date 10:37 2019/08/19 0019
     * @Param [dataIndexMap]
     * @return void
    **/
    void insertAndUpdateIndex(Map<String, Object> dataIndexMap,Integer monitor, String equipId);

    /**
     * @Author mzc
     * @Description //TODO 通过d删除监测数据信息
     * @Date 10:23 2019/09/12 0012
     * @Param [id]
     * @return void
    **/
    int deleteDataById(String id);
    /**
     * @Author mzc
     * @Description //TODO 通过d删除监测数据信息
     * @Date 10:23 2019/09/12 0012
     * @Param [id]
     * @return void
     **/
    int deleteDataByIds(String ids);

    /**
     * @Author mzc
     * @Description //TODO 条件查询
     * @Date 11:26 2019/09/26 0026
     * @Param [currentPage, pagesize, monitorData]
     * @return java.lang.String
    **/
    String searchByCon(String currentPage, String pagesize, MonitorData monitorData);

    /*
     * @Author mzc
     * @Description //TODO 获取参数计算指标的值
     * @Date 14:54 2019/10/08 0008
     * @Param [models]
     * @return java.lang.String
    **/
    String cauculIdexEcho(JSONObject models);

    /**
     * @Author mzc
     * @Description //TODO 修改监测数据(主数据)
     * @Date 16:57 2019/10/08 0008
     * @Param [monitorId]
     * @return void
    **/
    void updateById(MonitorData data);

    /**
     * @Author mzc
     * @Description //TODO 修改监测数据(参数数据)
     * @Date 17:10 2019/10/08 0008
     * @Param [dataParam]
     * @return void
    **/
//    void updateParam(Map<String, Object> dataParamMap, Integer monitorId);

    /**
     * @Author mzc
     * @Description //TODO 查询数据参数名
     * @Date 09:26 2019/10/14 0014
     * @Param [categoryId, equipId, paramType]
     * @return java.util.List<com.petrochina.e7.monitor.pojo.DataParam>
    **/
    List<DataParam> findParam2(String categoryId, String equipId, String paramType);
    /**
     * @Author mzc
     * @Description //TODO 查询数据指标名
     * @Date 09:26 2019/10/14 0014
     * @Param [categoryId, equipId]
     * @return java.util.List<com.petrochina.e7.monitor.pojo.DataIndex>
    **/
    List<DataIndex> findIndex2(String categoryId, String equipId);

    /**
     * @Author mzc
     * @Description //TODO 查询monitorId最大值
     * @Date 19:40 2019/10/17 0017
     * @Param []
     * @return int
    **/
    int findMonitorIdMax();


    /**
     * @Author mzc
     * @Description //TODO 修改,查看页面指标和参数value值数据会回显
     * @Date 16:19 2019/10/19 0019
     * @Param [categoryId, equipId, monitorId]
     * @return java.lang.String
    **/
    String paramAndIndexEcho(String monitorId);

    /**
     * @Author mzc 
     * @Description //TODO 
     * @Date 18:18 2019/10/29 0029
     * @Param [file]
     * @return java.util.List<com.petrochina.e7.monitor.pojo.MechanicalExcel>
    **/
    List<MechanicalExcel> fetchMechanicalExcel(MultipartFile file);



    String ss();

    Boolean findOriginalFilename(String originalFilename);
    //向日志表插入数据
    int fetchExcelLog(String originalFilename, Date parse);



}
