package com.petrochina.e7.monitor.pojo;

//import lombok.Data;

import lombok.Data;

import java.util.Objects;

@Data
public class DataParam {
    private Integer categoryParamId;

    private String categoryId;

    private String equipId;

    private String categoryParamName;

    private String categoryParamUnit;

    private String categoryParamDataType;

    private String definitionAnd;

    private String rank;

    private String iskey;

    private String issearchkey;

    private String remark;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataParam dataParam = (DataParam) o;
        return Objects.equals(categoryParamId, dataParam.categoryParamId) &&
                Objects.equals(categoryId, dataParam.categoryId) &&
                Objects.equals(equipId, dataParam.equipId) &&
                Objects.equals(categoryParamName, dataParam.categoryParamName) &&
                Objects.equals(categoryParamUnit, dataParam.categoryParamUnit) &&
                Objects.equals(categoryParamDataType, dataParam.categoryParamDataType) &&
                Objects.equals(definitionAnd, dataParam.definitionAnd) &&
                Objects.equals(rank, dataParam.rank) &&
                Objects.equals(iskey, dataParam.iskey) &&
                Objects.equals(issearchkey, dataParam.issearchkey) &&
                Objects.equals(remark, dataParam.remark) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryParamId, categoryId, equipId, categoryParamName, categoryParamUnit, categoryParamDataType, definitionAnd, rank, iskey, issearchkey, remark);
    }

    @Override
    public String toString() {
        return "DataParam{" +
                "categoryParamId=" + categoryParamId +
                ", categoryId=" + categoryId +
                ", equipId=" + equipId +
                ", categoryParamName='" + categoryParamName + '\'' +
                ", categoryParamUnit='" + categoryParamUnit + '\'' +
                ", categoryParamDataType='" + categoryParamDataType + '\'' +
                ", definitionAnd='" + definitionAnd + '\'' +
                ", rank='" + rank + '\'' +
                ", iskey='" + iskey + '\'' +
                ", issearchkey='" + issearchkey + '\'' +
                ", rek='" + remark + '\'' +
                '}';
    }
}