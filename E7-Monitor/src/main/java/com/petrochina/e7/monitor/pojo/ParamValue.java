package com.petrochina.e7.monitor.pojo;

import lombok.Data;

import java.util.Objects;
@Data
public class ParamValue {
    private Integer monitorParamId;

    private Integer monitorId;

    private Integer categoryParamId;

    private String categoryParamValue;

    private Integer orgId;

    private String remark;

    public Integer getmonitorParamId() {
        return monitorParamId;
    }

    public void setmonitorParamId(Integer monitorParamId) {
        this.monitorParamId = monitorParamId;
    }

    public Integer getMonitorId() {
        return monitorId;
    }

    public void setMonitorId(Integer monitorId) {
        this.monitorId = monitorId;
    }

    public Integer getCategoryParamId() {
        return categoryParamId;
    }

    public void setCategoryParamId(Integer categoryParamId) {
        this.categoryParamId = categoryParamId;
    }

    public String getCategoryParamValue() {
        return categoryParamValue;
    }

    public void setCategoryParamValue(String categoryParamValue) {
        this.categoryParamValue = categoryParamValue == null ? null : categoryParamValue.trim();
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParamValue that = (ParamValue) o;
        return Objects.equals(monitorParamId, that.monitorParamId) &&
                Objects.equals(monitorId, that.monitorId) &&
                Objects.equals(categoryParamId, that.categoryParamId) &&
                Objects.equals(categoryParamValue, that.categoryParamValue) &&
                Objects.equals(orgId, that.orgId) &&
                Objects.equals(remark, that.remark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(monitorParamId, monitorId, categoryParamId, categoryParamValue, orgId, remark);
    }

    @Override
    public String toString() {
        return "ParamValue{" +
                "monitorParamId=" + monitorParamId +
                ", monitorId=" + monitorId +
                ", categoryParamId=" + categoryParamId +
                ", categoryParamValue='" + categoryParamValue + '\'' +
                ", orgId=" + orgId +
                ", remark='" + remark + '\'' +
                '}';
    }
}