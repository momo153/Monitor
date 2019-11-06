package com.petrochina.e7.monitor.service;

import com.petrochina.e7.monitor.pojo.Report;

/**
 * @ProjectName com.petrochina.e7.monitor.project.service
 * @ClassName: IDataEntryServer
 * @Description: TODO 监测数据录入Service接口
 * @Author: Administrator
 * @Date: 2019/07/28 0028$ 17:15$
 * @Version: 1.0
 */
public interface IReportService {


    /**
     * @Author zs
     * @Description //TODO 查询所有监测报告数据list
     * @Date 2019年10月15日11:12:04
     * @Param []
     * @return java.util.List<com.petrochina.e7.monitor.pojo.Data>
     *
     * @param currentPage
     * @param pagesize*/
//    String findAllDatas(String currentPage, String pagesize);

    /**
     * @Author zs
     * @Description //TODO 条件查询
     * @Date 2019-10-15 15:44:55
     * @Param [currentPage, pagesize, monitorData]
     * @return java.lang.String
     **/
    String searchByCons(String currentPage, String pagesize, Report report);

    /**
     * @Author zs
     * @Description //TODO 查询所有
     * @Date 2019年10月15日17:35:27
     * @Param [currentPage, pagesize]
     * @return java.lang.String
     **/
    String findAll(String currentPage, String pagesize);
    /**
     * @Author zs
     * @Description //TODO 通过ID批量删除监测报告
     * @Date 2019年10月16日14:50:15
     * @Param [ids]
     * @return int
     **/
    int deleteReportByIds(String reportIds);
}
