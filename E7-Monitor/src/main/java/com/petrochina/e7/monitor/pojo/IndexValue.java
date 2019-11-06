package com.petrochina.e7.monitor.pojo;

import lombok.Data;

import java.util.Objects;

@Data
public class IndexValue {
    private Integer monitorIndexId;

    private Integer reportId;

    private Integer monitorId;

    private Integer categoryIndexId;

    private String categoryIndexValue;

    private Integer orgId;

    private String remark;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IndexValue that = (IndexValue) o;
        return Objects.equals(monitorIndexId, that.monitorIndexId) &&
                Objects.equals(reportId, that.reportId) &&
                Objects.equals(monitorId, that.monitorId) &&
                Objects.equals(categoryIndexId, that.categoryIndexId) &&
                Objects.equals(categoryIndexValue, that.categoryIndexValue) &&
                Objects.equals(orgId, that.orgId) &&
                Objects.equals(remark, that.remark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(monitorIndexId, reportId, monitorId, categoryIndexId, categoryIndexValue, orgId, remark);
    }

    @Override
    public String toString() {
        return "IndexValue{" +
                "monitorIndexId=" + monitorIndexId +
                ", reportId=" + reportId +
                ", monitorId=" + monitorId +
                ", categoryIndexId=" + categoryIndexId +
                ", categoryIndexValue='" + categoryIndexValue + '\'' +
                ", orgId=" + orgId +
                ", remark='" + remark + '\'' +
                '}';
    }
}