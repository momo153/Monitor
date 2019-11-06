package com.petrochina.e7.monitor.pojo;

import lombok.Data;

import java.util.List;
import java.util.Objects;

/**
 * @ProjectName com.petrochina.e7.monitor.pojo
 * @ClassName: CustomParamValue
 * @Description: TODO
 * @Author: Administrator
 * @Date: 2019/09/05 0005$ 15:57$
 * @Version: 1.0
 */
@Data
public class CustomDataParam extends DataParam {
    private List<ParamValue> paramValueList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CustomDataParam that = (CustomDataParam) o;
        return Objects.equals(paramValueList, that.paramValueList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), paramValueList);
    }

    @Override
    public String toString() {
        return "CustomParamValue{" +
                "paramValueList=" + paramValueList +
                '}';
    }
}
