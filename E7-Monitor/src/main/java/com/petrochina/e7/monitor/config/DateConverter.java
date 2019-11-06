package com.petrochina.e7.monitor.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ProjectName com.petrochina.e7.monitor.config
 * @ClassName: DateConverter
 * @Description: TODO
 * @Author: Administrator
 * @Date: 2019/09/17 0017$ 11:28$
 * @Version: 1.0
 */
public class DateConverter implements Converter<String,Date>{

    @Override
    public Date convert(String source) {
        Date target = null;
        if(!StringUtils.isEmpty(source)) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                target =  format.parse(source);
            } catch (ParseException e) {
                throw new RuntimeException(String.format("parser %s to Date fail", source));
            }
        }
        return target;
    }
}
