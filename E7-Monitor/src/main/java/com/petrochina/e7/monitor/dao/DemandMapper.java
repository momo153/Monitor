package com.petrochina.e7.monitor.dao;

import com.petrochina.e7.monitor.pojo.Demand;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DemandMapper {
    int deleteByPrimaryKey(Integer demandId);

    int insert(Demand record);

    int insertSelective(Demand record);

    Demand selectByPrimaryKey(Integer demandId);

    int updateByPrimaryKeySelective(Demand record);

//    int updateByPrimaryKey(Demand record);



    /**
     * @return java.util.List<Demand>
     * @Author mzc
     * @Description //TODO 查询所有监测需求list
     * @Date 14:24 2019/07/26 0026
     * @Param []
     **/
    List<Demand> selecDemands();

    /**
     * @return Demand
     * @Author mzc
     * @Description //TODO 根据ID查询监测需求
     * @Date 15:05 2019/07/28 0028
     * @Param [id]
     **/
    Demand findDemandById(String demandId);
    /**
     * @return Demand
     * @Author mzc
     * @Description //TODO 根据ID查询监测需求
     * @Date 15:05 2019/07/28 0028
     * @Param [id]
     **/
    Demand findDemandById2(String demandId);
    /**
     * @return int
     * @Author mzc
     * @Description //TODO 通过ID删除监测需求
     * @Date 15:08 2019/07/28 0028
     * @Param [demandId]
     **/
    int deleteDemandById(String demandIds);

    /**
     * @return int
     * @Author mzc
     * @Description //TODO 通过ID批量删除监测需求
     * @Date 14:06 2019/07/28 0028
     * @Param [demandIds]
     **/
    int deleteDemandByIds(@Param("ids")int[] ids);

    /**
     * @return Demand
     * @Author mzc
     * @Description //TODO 跳转修改页面
     * @Date 15:29 2019/07/28 0028
     * @Param [id]
     **/
    int editDemandById(Demand demand);

    /**
     * @Author mzc
     * @Description //TODO 多条件查询
     * @Date 20:18 2019/09/24 0024
     * @Param [demand]
     * @return java.util.List<com.petrochina.e7.monitor.pojo.Plan>
    **/
    List<Demand> selectDemandByCon(Demand demand);
}