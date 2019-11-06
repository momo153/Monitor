package com.petrochina.e7.monitor.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
@Data
public class Plan implements Serializable {
    private static final long serialVersionUID = -5809782578272943999L;
    private Integer planId;

    private Integer acceptId;

    private Integer publicId;

    private String planNm;

    private String planYear;

    private String planTypeCode;
//    @JsonFormat(timezone = "GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd")
    private Date recodeTime;

    private String passMonitorUnit;

    private String monitorUnit;

    private Integer parentPlanId;

    private String recodePerson;

    private Integer recodeOrgId;

    private String statusCode;
//    @JsonFormat(timezone = "GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd")
    private Date recodeYear;

    private String specialtyCategory;

    private String monitorProjectType;

    private String monitorProjectNm;

    private Integer equipId;

    private String equipNm;

    private String monitorContent;

    private Integer planAmount;

    private String remark;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plan plan = (Plan) o;
        return Objects.equals(planId, plan.planId) &&
                Objects.equals(acceptId, plan.acceptId) &&
                Objects.equals(publicId, plan.publicId) &&
                Objects.equals(planNm, plan.planNm) &&
                Objects.equals(planYear, plan.planYear) &&
                Objects.equals(planTypeCode, plan.planTypeCode) &&
                Objects.equals(recodeTime, plan.recodeTime) &&
                Objects.equals(passMonitorUnit, plan.passMonitorUnit) &&
                Objects.equals(monitorUnit, plan.monitorUnit) &&
                Objects.equals(parentPlanId, plan.parentPlanId) &&
                Objects.equals(recodePerson, plan.recodePerson) &&
                Objects.equals(recodeOrgId, plan.recodeOrgId) &&
                Objects.equals(statusCode, plan.statusCode) &&
                Objects.equals(recodeYear, plan.recodeYear) &&
                Objects.equals(specialtyCategory, plan.specialtyCategory) &&
                Objects.equals(monitorProjectType, plan.monitorProjectType) &&
                Objects.equals(monitorProjectNm, plan.monitorProjectNm) &&
                Objects.equals(equipId, plan.equipId) &&
                Objects.equals(equipNm, plan.equipNm) &&
                Objects.equals(monitorContent, plan.monitorContent) &&
                Objects.equals(planAmount, plan.planAmount) &&
                Objects.equals(remark, plan.remark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(planId, acceptId, publicId, planNm, planYear, planTypeCode, recodeTime, passMonitorUnit, monitorUnit, parentPlanId, recodePerson, recodeOrgId, statusCode, recodeYear, specialtyCategory, monitorProjectType, monitorProjectNm, equipId, equipNm, monitorContent, planAmount, remark);
    }

    @Override
    public String toString() {
        return "Plan{" +
                "planId=" + planId +
                ", acceptId=" + acceptId +
                ", publicId=" + publicId +
                ", planNm='" + planNm + '\'' +
                ", planYear=" + planYear +
                ", planTypeCode='" + planTypeCode + '\'' +
                ", recodeTime=" + recodeTime +
                ", passMonitorUnit='" + passMonitorUnit + '\'' +
                ", monitorUnit='" + monitorUnit + '\'' +
                ", parentPlanId=" + parentPlanId +
                ", recodePerson='" + recodePerson + '\'' +
                ", recodeOrgId=" + recodeOrgId +
                ", statusCode='" + statusCode + '\'' +
                ", recodeYear=" + recodeYear +
                ", specialtyCategory='" + specialtyCategory + '\'' +
                ", monitorProjectType='" + monitorProjectType + '\'' +
                ", monitorProjectNm='" + monitorProjectNm + '\'' +
                ", equipId=" + equipId +
                ", equipNm='" + equipNm + '\'' +
                ", monitorContent='" + monitorContent + '\'' +
                ", planAmount=" + planAmount +
                ", remark='" + remark + '\'' +
                '}';
    }
}