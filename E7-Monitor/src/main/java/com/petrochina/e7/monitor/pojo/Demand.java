package com.petrochina.e7.monitor.pojo;

//import lombok.Data;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Data
public class Demand implements Serializable {
    private static final long serialVersionUID = -5809782578272943999L;
    private Integer demandId;

    private Integer acceptId;

    private Integer publicId;

    private String demandNm;

    private String demandTypeCode;

    private String demandYear;

    private String specialtyCategory;

    private String monitorProjectType;

    private String monitorProjectNm;

    private String monitorContent;

    private Integer equipId;

    private String equipNm;

    private Integer amount;
    @JSONField(format = "yyyy-MM-dd")
    private Date demandApplyTime;
    @JSONField(format = "yyyy-MM-dd")
    private Date demandEndTime;

    private String monitorUnit;

    private String declareOrgId;

    private String declareOrgNm;

    private String demandPerson;

    private String contactNum;

    private Integer parentOrgId;

    private String demandStatus;

    private String remark;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Demand demand = (Demand) o;
        return Objects.equals(demandId, demand.demandId) &&
                Objects.equals(acceptId, demand.acceptId) &&
                Objects.equals(publicId, demand.publicId) &&
                Objects.equals(demandNm, demand.demandNm) &&
                Objects.equals(demandTypeCode, demand.demandTypeCode) &&
                Objects.equals(demandYear, demand.demandYear) &&
                Objects.equals(specialtyCategory, demand.specialtyCategory) &&
                Objects.equals(monitorProjectType, demand.monitorProjectType) &&
                Objects.equals(monitorProjectNm, demand.monitorProjectNm) &&
                Objects.equals(monitorContent, demand.monitorContent) &&
                Objects.equals(equipId, demand.equipId) &&
                Objects.equals(equipNm, demand.equipNm) &&
                Objects.equals(amount, demand.amount) &&
                Objects.equals(demandApplyTime, demand.demandApplyTime) &&
                Objects.equals(demandEndTime, demand.demandEndTime) &&
                Objects.equals(monitorUnit, demand.monitorUnit) &&
                Objects.equals(declareOrgId, demand.declareOrgId) &&
                Objects.equals(declareOrgNm, demand.declareOrgNm) &&
                Objects.equals(demandPerson, demand.demandPerson) &&
                Objects.equals(contactNum, demand.contactNum) &&
                Objects.equals(parentOrgId, demand.parentOrgId) &&
                Objects.equals(demandStatus, demand.demandStatus) &&
                Objects.equals(remark, demand.remark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(demandId, acceptId, publicId, demandNm, demandTypeCode, demandYear, specialtyCategory, monitorProjectType, monitorProjectNm, monitorContent, equipId, equipNm, amount, demandApplyTime, demandEndTime, monitorUnit, declareOrgId, declareOrgNm, demandPerson, contactNum, parentOrgId, demandStatus, remark);
    }

    @Override
    public String toString() {
        return "Demand{" +
                "demandId=" + demandId +
                ", acceptId=" + acceptId +
                ", publicId=" + publicId +
                ", demandNm='" + demandNm + '\'' +
                ", demandTypeCode=" + demandTypeCode +
                ", demandYear='" + demandYear + '\'' +
                ", specialtyCategory='" + specialtyCategory + '\'' +
                ", monitorProjectType='" + monitorProjectType + '\'' +
                ", monitorProjectNm='" + monitorProjectNm + '\'' +
                ", monitorContent='" + monitorContent + '\'' +
                ", equipId=" + equipId +
                ", equipNm='" + equipNm + '\'' +
                ", amount=" + amount +
                ", demandApplyTime=" + demandApplyTime +
                ", demandEndTime=" + demandEndTime +
                ", monitorUnit='" + monitorUnit + '\'' +
                ", declareOrgId='" + declareOrgId + '\'' +
                ", declareOrgNm='" + declareOrgNm + '\'' +
                ", demandPerson='" + demandPerson + '\'' +
                ", contactNum='" + contactNum + '\'' +
                ", parentOrgId=" + parentOrgId +
                ", demandStatus='" + demandStatus + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}