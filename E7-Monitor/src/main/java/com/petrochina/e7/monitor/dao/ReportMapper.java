package com.petrochina.e7.monitor.dao;

import com.petrochina.e7.monitor.pojo.Report;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReportMapper {
    int deleteByPrimaryKey(Integer reportId);

    int insert(Report record);

    int insertSelective(Report record);

    Report selectByPrimaryKey(Integer reportId);

    int updateByPrimaryKeySelective(Report record);

    int updateByPrimaryKey(Report record);

    List<Report> findAll();

    List<Report> search(Report report);


    int deleteReportByIds(@Param("ids") int[] ids);
}