package com.petrochina.e7.monitor.dao;

import com.petrochina.e7.monitor.pojo.ExcelLog;
import com.petrochina.e7.monitor.pojo.MechanicalExcel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface MechanicalExcelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MechanicalExcel record);

    int insertSelective(MechanicalExcel record);

    MechanicalExcel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MechanicalExcel record);

    int updateByPrimaryKey(MechanicalExcel record);

    int insertList(List<MechanicalExcel> resultList);

    int findOriginalFilename(String originalFilename);
    //插入日志
    int fetchExcelLog(ExcelLog excelLog);

    List<ExcelLog> findExcelLogList();

    //查询数据
    List<MechanicalExcel> findDataByParam(String param);
}