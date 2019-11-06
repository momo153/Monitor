package com.petrochina.e7.monitor.pojo;

import lombok.Data;

import java.util.List;
import java.util.Objects;

/**
 * @ProjectName com.petrochina.e7.monitor.pojo
 * @ClassName: MonitorDataPuls
 * @Description: TODO
 * @Author: Administrator
 * @Date: 2019/11/02 0002$ 17:32$
 * @Version: 1.0
 */
@Data
public class MonitorDataPuls {

    private String levelaOrg;

    private String levelbOrgStr;

    private String levelcOrgStr;

    private String leveldOrgStr;

    private String equipId;

    private Integer amount;

    private List<MonitorData> monitorDataList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonitorDataPuls that = (MonitorDataPuls) o;
        return Objects.equals(levelaOrg, that.levelaOrg) &&
                Objects.equals(levelbOrgStr, that.levelbOrgStr) &&
                Objects.equals(levelcOrgStr, that.levelcOrgStr) &&
                Objects.equals(leveldOrgStr, that.leveldOrgStr) &&
                Objects.equals(equipId, that.equipId) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(monitorDataList, that.monitorDataList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(levelaOrg, levelbOrgStr, levelcOrgStr, leveldOrgStr, equipId, amount, monitorDataList);
    }

    @Override
    public String toString() {
        return "MonitorDataPuls{" +
                "levelaOrg=" + levelaOrg +
                ", levelbOrgStr='" + levelbOrgStr + '\'' +
                ", levelcOrgStr='" + levelcOrgStr + '\'' +
                ", leveldOrgStr='" + leveldOrgStr + '\'' +
                ", equipId='" + equipId + '\'' +
                ", amount=" + amount +
                ", monitorDataList=" + monitorDataList +
                '}';
    }
}
