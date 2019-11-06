package com.petrochina.e7.monitor.pojo;

//import lombok.Data;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Data
public class MonitorData implements Serializable {
    private static final long serialVersionUID = -5809782578272943999L;

    private Integer monitorId;

    private String annual;

    private String equipId;

    private String specialtyCategory;

    private String categoryId;

    private String projectName;

    private String monitorContent;

    private Integer catagoryChaptersId;

    private Integer amount;

    private String byMeasuring;

    private String status;

    private Integer orgId;

    private Integer createUserId;

    private String createUserName;
    @JSONField(format = "yyyy-MM-d")
    private Date createTime;

    private String levelaOrg;

    private String levelbOrgStr;

    private String levelcOrgStr;

    private String leveldOrgStr;

    private Integer updateUserId;

    private String updateUserName;
    @JSONField(format = "yyyy-MM-dd")
    private Date updateTime;

    private String isOpen;

    private String monitorOrgId;

    private String remark;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonitorData that = (MonitorData) o;
        return Objects.equals(monitorId, that.monitorId) &&
                Objects.equals(annual, that.annual) &&
                Objects.equals(equipId, that.equipId) &&
                Objects.equals(specialtyCategory, that.specialtyCategory) &&
                Objects.equals(categoryId, that.categoryId) &&
                Objects.equals(projectName, that.projectName) &&
                Objects.equals(monitorContent, that.monitorContent) &&
                Objects.equals(catagoryChaptersId, that.catagoryChaptersId) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(byMeasuring, that.byMeasuring) &&
                Objects.equals(status, that.status) &&
                Objects.equals(orgId, that.orgId) &&
                Objects.equals(createUserId, that.createUserId) &&
                Objects.equals(createUserName, that.createUserName) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(levelaOrg, that.levelaOrg) &&
                Objects.equals(levelbOrgStr, that.levelbOrgStr) &&
                Objects.equals(levelcOrgStr, that.levelcOrgStr) &&
                Objects.equals(leveldOrgStr, that.leveldOrgStr) &&
                Objects.equals(updateUserId, that.updateUserId) &&
                Objects.equals(updateUserName, that.updateUserName) &&
                Objects.equals(updateTime, that.updateTime) &&
                Objects.equals(isOpen, that.isOpen) &&
                Objects.equals(monitorOrgId, that.monitorOrgId) &&
                Objects.equals(remark, that.remark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(monitorId, annual, equipId, specialtyCategory, categoryId, projectName, monitorContent, catagoryChaptersId, amount, byMeasuring, status, orgId, createUserId, createUserName, createTime, levelaOrg, levelbOrgStr, levelcOrgStr, leveldOrgStr, updateUserId, updateUserName, updateTime, isOpen, monitorOrgId, remark);
    }

    @Override
    public String toString() {
        return "MonitorData{" +
                "monitorId=" + monitorId +
                ", annual='" + annual + '\'' +
                ", equipId=" + equipId +
                ", specialtyCategory='" + specialtyCategory + '\'' +
                ", categoryId=" + categoryId +
                ", projectName='" + projectName + '\'' +
                ", monitorContent='" + monitorContent + '\'' +
                ", catagoryChaptersId=" + catagoryChaptersId +
                ", amount=" + amount +
                ", byMeasuring='" + byMeasuring + '\'' +
                ", status='" + status + '\'' +
                ", orgId=" + orgId +
                ", createUserId=" + createUserId +
                ", createUserName='" + createUserName + '\'' +
                ", createTime=" + createTime +
                ", levelaOrg=" + levelaOrg +
                ", levelbOrgStr='" + levelbOrgStr + '\'' +
                ", levelcOrgStr='" + levelcOrgStr + '\'' +
                ", leveldOrgStr='" + leveldOrgStr + '\'' +
                ", updateUserId=" + updateUserId +
                ", updateUserName='" + updateUserName + '\'' +
                ", updateTime=" + updateTime +
                ", isOpen='" + isOpen + '\'' +
                ", monitorOrgId=" + monitorOrgId +
                ", remark='" + remark + '\'' +
                '}';
    }
}