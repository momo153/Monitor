package com.petrochina.e7.monitor.service;

import com.petrochina.e7.monitor.pojo.Demand;

/**
 * @ProjectName com.petrochina.e7.monitor.prokect.service
 * @ClassName: IDemandService
 * @Description: TODO 监测需求Service
 * @Author: Administrator
 * @Date: 2019/07/28 0028$ 17:18$
 * @Version: 1.0
 */

public interface IDemandService {
    /**
     * @return java.util.List<com.petrochina.e7.monitor.project.pojo.Demand>
     * @Author mzc
     * @Description //TODO 查询所有监测需求list
     * @Date 17:59 2019/07/28 0028
     * @Param []
     **/
//    List<Demand> findAllDemand();
    String findAllDemand(String currentPage, String pagesize);


    /**
     * @return java.lang.Object
     * @Author mzc
     * @Description //TODO 通过ID查询单条监测需求
     * @Date 17:59 2019/07/28 0028
     * @Param [id]
     **/
    String findDemandById(String id);
    /**
     * @return java.lang.Object
     * @Author mzc
     * @Description //TODO 通过ID查询单条监测需求
     * @Date 17:59 2019/07/28 0028
     * @Param [id]
     **/
    Demand findDemandById2(String id);
    /**
     * @return void
     * @Author mzc
     * @Description //TODO 添加监测需求操作
     * @Date 17:59 2019/07/28 0028
     * @Param [demand]
     **/
    int insert(Demand demand);

    /**
     * @return void
     * @Author mzc
     * @Description //TODO 单个删除
     * @Date 18:00 2019/07/28 0028
     * @Param [id]
     **/
    int deleteDemandById(String id);
    /**
     * @return void
     * @Author mzc
     * @Description //TODO 批量删除监测需求
     * @Date 18:00 2019/07/28 0028
     * @Param [ids]
     **/
    int deleteDemandByIds(String demandIds);

    /**
     * @return java.lang.Object
     * @Author mzc
     * @Description //TODO 跳转修改页面
     * @Date 18:20 2019/07/28 0028
     * @Param [id]
     **/
    int editDemandById(Demand demand);

    /**
     * @Author mzc
     * @Description //TODO 多条件查询
     * @Date 20:16 2019/09/24 0024
     * @Param [currentPage, pagesize, demand]
     * @return java.lang.String
    **/
    String searchDemandByCon(String currentPage, String pagesize, Demand demand);
}
