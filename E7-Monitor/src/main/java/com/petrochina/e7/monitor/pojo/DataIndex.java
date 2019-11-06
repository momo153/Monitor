package com.petrochina.e7.monitor.pojo;

import lombok.Data;

import java.util.Objects;

@Data
public class DataIndex {
    private Integer categoryIndexId;

    private String categoryId;

    private String equipId;

    private String categoryIndexName;

    private String categoryIndexUnit;

    private String categoryIndexDataType;

    private String definitionAnd;

    private String rank;

    private String issearchkey;

    private String iskey;

    private String remark;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataIndex dataIndex = (DataIndex) o;
        return Objects.equals(categoryIndexId, dataIndex.categoryIndexId) &&
                Objects.equals(categoryId, dataIndex.categoryId) &&
                Objects.equals(equipId, dataIndex.equipId) &&
                Objects.equals(categoryIndexName, dataIndex.categoryIndexName) &&
                Objects.equals(categoryIndexUnit, dataIndex.categoryIndexUnit) &&
                Objects.equals(categoryIndexDataType, dataIndex.categoryIndexDataType) &&
                Objects.equals(definitionAnd, dataIndex.definitionAnd) &&
                Objects.equals(rank, dataIndex.rank) &&
                Objects.equals(issearchkey, dataIndex.issearchkey) &&
                Objects.equals(iskey, dataIndex.iskey) &&
                Objects.equals(remark, dataIndex.remark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryIndexId, categoryId, equipId, categoryIndexName, categoryIndexUnit, categoryIndexDataType, definitionAnd, rank, issearchkey, iskey, remark);
    }

    @Override
    public String toString() {
        return "DataIndex{" +
                "categoryIndexId=" + categoryIndexId +
                ", categoryId=" + categoryId +
                ", equipId=" + equipId +
                ", categoryIndexName='" + categoryIndexName + '\'' +
                ", categoryIndexUnit='" + categoryIndexUnit + '\'' +
                ", categoryIndexDataType='" + categoryIndexDataType + '\'' +
                ", definitionAnd='" + definitionAnd + '\'' +
                ", rank='" + rank + '\'' +
                ", issearchkey='" + issearchkey + '\'' +
                ", iskey='" + iskey + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}