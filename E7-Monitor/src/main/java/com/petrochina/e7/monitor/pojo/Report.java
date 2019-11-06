package com.petrochina.e7.monitor.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Report implements Serializable {
    private Integer reportId;

    private Integer butinId;

    private Integer acceptId;

    private Integer planId;

    private Integer demandId;

    private String reportNum;

    private String reportNm;

    private String monitorProjectType;

    private Integer passMonitorId;

    private String passMonitorUnit;

    private Integer montiorOrgId;

    private String monitorUnit;

    private Integer monitorYear;

    private String monitorReport;

    private String reportSub;

    private Date submitDate;

    private String reportStatus;

    private Integer publicId;

    private String remark;

    private String codeName;


    public void setMonitorProjectType(String monitorProjectType) {
        this.monitorProjectType = monitorProjectType;
    }

    public String getMonitorProjectType() {
        return monitorProjectType;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public Integer getButinId() {
        return butinId;
    }

    public void setButinId(Integer butinId) {
        this.butinId = butinId;
    }

    public Integer getAcceptId() {
        return acceptId;
    }

    public void setAcceptId(Integer acceptId) {
        this.acceptId = acceptId;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public Integer getDemandId() {
        return demandId;
    }

    public void setDemandId(Integer demandId) {
        this.demandId = demandId;
    }

    public String getReportNum() {
        return reportNum;
    }

    public void setReportNum(String reportNum) {
        this.reportNum = reportNum == null ? null : reportNum.trim();
    }

    public String getReportNm() {
        return reportNm;
    }

    public void setReportNm(String reportNm) {
        this.reportNm = reportNm == null ? null : reportNm.trim();
    }



    public Integer getPassMonitorId() {
        return passMonitorId;
    }

    public void setPassMonitorId(Integer passMonitorId) {
        this.passMonitorId = passMonitorId;
    }

    public String getPassMonitorUnit() {
        return passMonitorUnit;
    }

    public void setPassMonitorUnit(String passMonitorUnit) {
        this.passMonitorUnit = passMonitorUnit == null ? null : passMonitorUnit.trim();
    }

    public Integer getMontiorOrgId() {
        return montiorOrgId;
    }

    public void setMontiorOrgId(Integer montiorOrgId) {
        this.montiorOrgId = montiorOrgId;
    }

    public String getMonitorUnit() {
        return monitorUnit;
    }

    public void setMonitorUnit(String monitorUnit) {
        this.monitorUnit = monitorUnit == null ? null : monitorUnit.trim();
    }

    public Integer getMonitorYear() {
        return monitorYear;
    }

    public void setMonitorYear(Integer monitorYear) {
        this.monitorYear = monitorYear;
    }

    public String getMonitorReport() {
        return monitorReport;
    }

    public void setMonitorReport(String monitorReport) {
        this.monitorReport = monitorReport == null ? null : monitorReport.trim();
    }

    public String getReportSub() {
        return reportSub;
    }

    public void setReportSub(String reportSub) {
        this.reportSub = reportSub == null ? null : reportSub.trim();
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public String getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus == null ? null : reportStatus.trim();
    }

    public Integer getPublicId() {
        return publicId;
    }

    public void setPublicId(Integer publicId) {
        this.publicId = publicId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    @Override
    public String toString() {
        return "Report{" +
                "reportId=" + reportId +
                ", butinId=" + butinId +
                ", acceptId=" + acceptId +
                ", planId=" + planId +
                ", demandId=" + demandId +
                ", reportNum='" + reportNum + '\'' +
                ", reportNm='" + reportNm + '\'' +
                ", monitorProjectType=" + monitorProjectType +
                ", passMonitorId=" + passMonitorId +
                ", passMonitorUnit='" + passMonitorUnit + '\'' +
                ", montiorOrgId=" + montiorOrgId +
                ", monitorUnit='" + monitorUnit + '\'' +
                ", monitorYear=" + monitorYear +
                ", monitorReport='" + monitorReport + '\'' +
                ", reportSub='" + reportSub + '\'' +
                ", submitDate=" + submitDate +
                ", reportStatus='" + reportStatus + '\'' +
                ", publicId=" + publicId +
                ", remark='" + remark + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return Objects.equals(reportId, report.reportId) &&
                Objects.equals(butinId, report.butinId) &&
                Objects.equals(acceptId, report.acceptId) &&
                Objects.equals(planId, report.planId) &&
                Objects.equals(demandId, report.demandId) &&
                Objects.equals(reportNum, report.reportNum) &&
                Objects.equals(reportNm, report.reportNm) &&
                Objects.equals(monitorProjectType, report.monitorProjectType) &&
                Objects.equals(passMonitorId, report.passMonitorId) &&
                Objects.equals(passMonitorUnit, report.passMonitorUnit) &&
                Objects.equals(montiorOrgId, report.montiorOrgId) &&
                Objects.equals(monitorUnit, report.monitorUnit) &&
                Objects.equals(monitorYear, report.monitorYear) &&
                Objects.equals(monitorReport, report.monitorReport) &&
                Objects.equals(reportSub, report.reportSub) &&
                Objects.equals(submitDate, report.submitDate) &&
                Objects.equals(reportStatus, report.reportStatus) &&
                Objects.equals(publicId, report.publicId) &&
                Objects.equals(remark, report.remark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reportId, butinId, acceptId, planId, demandId, reportNum, reportNm, monitorProjectType, passMonitorId, passMonitorUnit, montiorOrgId, monitorUnit, monitorYear, monitorReport, reportSub, submitDate, reportStatus, publicId, remark);
    }
}