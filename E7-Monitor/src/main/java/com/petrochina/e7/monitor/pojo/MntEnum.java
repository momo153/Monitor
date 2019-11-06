package com.petrochina.e7.monitor.pojo;

import java.util.Objects;

public class MntEnum {
    private Integer codelistId;

    private String codelistCode;

    private String codelistName;

    private String codeValue;

    private String codeName;

    private Integer publicId;

    public Integer getCodelistId() {
        return codelistId;
    }

    public void setCodelistId(Integer codelistId) {
        this.codelistId = codelistId;
    }

    public String getCodelistCode() {
        return codelistCode;
    }

    public void setCodelistCode(String codelistCode) {
        this.codelistCode = codelistCode == null ? null : codelistCode.trim();
    }

    public String getCodelistName() {
        return codelistName;
    }

    public void setCodelistName(String codelistName) {
        this.codelistName = codelistName == null ? null : codelistName.trim();
    }

    public String getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue == null ? null : codeValue.trim();
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName == null ? null : codeName.trim();
    }

    public Integer getPublicId() {
        return publicId;
    }

    public void setPublicId(Integer publicId) {
        this.publicId = publicId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MntEnum mntEnum = (MntEnum) o;
        return Objects.equals(codelistId, mntEnum.codelistId) &&
                Objects.equals(codelistCode, mntEnum.codelistCode) &&
                Objects.equals(codelistName, mntEnum.codelistName) &&
                Objects.equals(codeValue, mntEnum.codeValue) &&
                Objects.equals(codeName, mntEnum.codeName) &&
                Objects.equals(publicId, mntEnum.publicId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codelistId, codelistCode, codelistName, codeValue, codeName, publicId);
    }

    @Override
    public String toString() {
        return "MntEnum{" +
                "codelistId=" + codelistId +
                ", codelistCode='" + codelistCode + '\'' +
                ", codelistName='" + codelistName + '\'' +
                ", codeValue='" + codeValue + '\'' +
                ", codeName='" + codeName + '\'' +
                ", publicId=" + publicId +
                '}';
    }
}