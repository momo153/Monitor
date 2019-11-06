package com.petrochina.e7.monitor.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @ProjectName com.petrochina.e7.monitor.config
 * @ClassName: EncodingFilterConfig
 * @Description: TODO
 * @Author: Administrator
 * @Date: 2019/11/05 0005$ 14:52$
 * @Version: 1.0
 */
@Configuration
public class EncodingFilterConfig extends WebMvcConfigurationSupport {
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setForceEncoding(true);
        characterEncodingFilter.setEncoding("UTF-8");
        registrationBean.setFilter(characterEncodingFilter);
        return registrationBean;
    }

    //1.这个为解决中文乱码
    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        return converter;
    }


    //2.1：解决中文乱码后，返回json时可能会出现No converter found for return value of type: xxxx
    //或这个：Could not find acceptable representation
    //解决此问题如下
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }

    //2.2：解决No converter found for return value of type: xxxx
    public MappingJackson2HttpMessageConverter messageConverter() {
        MappingJackson2HttpMessageConverter converter=new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(getObjectMapper());
        return converter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        //解决中文乱码
        converters.add(responseBodyConverter());

        //解决： 添加解决中文乱码后的配置之后，返回json数据直接报错 500：no convertter for return value of type
        //或这个：Could not find acceptable representation
        converters.add(messageConverter());
    }


}
