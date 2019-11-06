package com.petrochina.e7.monitor.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Properties;

/**
 * @ProjectName com.petrochina.e7.monitor.controller
 * @ClassName: IndexController
 * @Description: TODO
 * @Author: Administrator
 * @Date: 2019/08/20 0020$ 11:23$
 * @Version: 1.0
 */

@RestController
@RequestMapping()
public class IndexController {
    Logger logger = LoggerFactory.getLogger(getClass());
    @GetMapping(value = "/index1")
    public String index() {
        return "index1";
    }

    @GetMapping("/gettree")
    public String getTree() {
        Properties properties = new Properties();
        String navigation = null;
        try {
            properties = PropertiesLoaderUtils.loadAllProperties("config/navigation.properties");
            navigation = new String(properties.getProperty("navigation.1").getBytes("utf-8"), "utf-8");
            logger.info(navigation);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return navigation;
    }
}
