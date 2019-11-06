package com.petrochina.e7.monitor.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.petrochina.e7.monitor.commons.utils.Result;
import com.petrochina.e7.monitor.commons.utils.ResultUtil;
import com.petrochina.e7.monitor.commons.utils.StringUtils;
import com.petrochina.e7.monitor.dao.DemandMapper;
import com.petrochina.e7.monitor.pojo.Demand;
import com.petrochina.e7.monitor.service.IDemandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author mzc
 * @Description //TODO 监测需求ServiceImpl
 * @Date 14:25 2019/07/26 0026
 * @Param
 * @return
 **/
@Service
public class DemandServiceImpl implements IDemandService {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DemandMapper demandMapper;

    /**
     * @return java.util.List<Demand>
     * @Author mzc
     * @Description //TODO 查询所有监测需求list
     * @Date 14:24 2019/07/26 0026
     * @Param []
     **/
 @Override
 public String findAllDemand(String currentPage, String pagesize) {
     PageHelper.startPage(Integer.valueOf(currentPage), Integer.valueOf(pagesize));
//     PageHelper.orderBy("DEMAND_ID desc");
     List<Demand> allDemand = demandMapper.selecDemands();
     PageInfo<Demand> pageInfo = new PageInfo<Demand>(allDemand);
     Result demandResult = ResultUtil.success(pageInfo.getList(), (int)pageInfo.getTotal());
//     String demandsJson = JSON.toJSONString(demandResult);
     String demandsJson = JSON.toJSONStringWithDateFormat(demandResult, "yyyy-MM-dd", SerializerFeature.WriteDateUseDateFormat);
     logger.info(currentPage + "=" + pagesize + "==>" + demandsJson);
     return demandsJson;
 }

    /**
     * @return Demand
     * @Author mzc
     * @Description //TODO 根据ID查询监测需求
     * @Date 15:05 2019/07/28 0028
     * @Param [id]
     **/
    @Override
    public String findDemandById(String id) {
        Demand demand = demandMapper.findDemandById(id);
//        Result demandJson = ResultUtil.success(demand,1);
//        String denamdJson = JSON.toJSONString(ResultUtil.success(demand,1));
        String demandJson = JSON.toJSONStringWithDateFormat(ResultUtil.success(demand,1), "yyyy-MM-dd", SerializerFeature.WriteDateUseDateFormat);
        return demandJson;
    }
    /**
     * @return Demand
     * @Author mzc
     * @Description //TODO 根据ID查询监测需求
     * @Date 15:05 2019/07/28 0028
     * @Param [id]
     **/
    @Override
    public Demand findDemandById2(String id) {
        return demandMapper.findDemandById2(id);
    }

    /**
     * @return int
     * @Author mzc
     * @Description //TODO 保存监测需求信息
     * @Date 16:22 2019/07/27 0027
     * @Param [demand]
     **/
    @Override
    public int insert(Demand demand) {
        return demandMapper.insert(demand);
    }

    /**
     * @return int
     * @Author mzc
     * @Description //TODO 通过ID删除监测需求
     * @Date 13:05 2019/07/28 0028
     * @Param [id]
     **/
    @Override
    public int deleteDemandById(String id) {
        return demandMapper.deleteDemandById(id);
    }

    /**
     * @return void
     * @Author mzc
     * @Description //TODO 通过ID批量删除监测需求
     * @Date 10:55 2019/07/26 0028
     * @Param [id]
     **/
    @Override
    public int deleteDemandByIds(String demandIds) {
        int[] ids = StringUtils.stringConvertInt(demandIds);
//        for (int demandId : ids) {
//            //如果需求状态为已通过则不能删除
//        }
        return demandMapper.deleteDemandByIds(ids);
    }

    /**
     * @return Demand
     * @Author mzc
     * @Description //TODO 跳转修改页面
     * @Date 15:28 2019/07/28 0028
     * @Param [id]
     **/
    @Override
    public int editDemandById(Demand demand) {
        return demandMapper.editDemandById(demand);
    }

    /**
     * @Author mzc
     * @Description //TODO 多条件查询
     * @Date 20:17 2019/09/24 0024
     * @Param [currentPage, pagesize, demand]
     * @return java.lang.String
    **/
    @Override
    public String searchDemandByCon(String currentPage, String pagesize, Demand demand) {
        PageHelper.startPage(Integer.valueOf(currentPage),Integer.valueOf(pagesize));
//        PageHelper.orderBy("DEMAND_ID desc");
        List<Demand> allDemand = demandMapper.selectDemandByCon(demand);
        PageInfo<Demand> pageInfo = new PageInfo<Demand>(allDemand);
        Result demandResult = ResultUtil.success(pageInfo.getList(), (int) pageInfo.getTotal());
//        String demandJson = JSON.toJSONString(success);
        String demandJson = JSON.toJSONStringWithDateFormat(demandResult, "yyyy-MM-dd", SerializerFeature.WriteDateUseDateFormat);
        return demandJson;
    }
}
