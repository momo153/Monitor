package com.petrochina.e7.monitor.pojo;

import java.util.Date;

/**
 * @ProjectName com.petrochina.e7.monitor.pojo
 * @ClassName: ExcelLog
 * @Description: TODO
 * @Author: hgq
 * @Date: 2019/11/1$ 10:30$
 * @Version: 1.0
 */

public class ExcelLog {
  private  Integer   Id;
  private  String   excelName;
  private  Date uploadTime ;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getExcelName() {
        return excelName;
    }

    public void setExcelName(String excelName) {
        this.excelName = excelName;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }
}
