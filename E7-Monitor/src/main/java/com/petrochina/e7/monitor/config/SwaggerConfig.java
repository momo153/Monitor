package com.petrochina.e7.monitor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ProjectName com.petrochina.e7.monitor.config
 * @ClassName: SwaggerConfig
 * @Description: TODO
 * @Author: Administrator
 * @Date: 2019/08/27 0027$ 15:34$
 * @Version: 1.0
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

    /**
     * 创建API应用
     * apiInfo() 增加API相关信息
     * 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现，
     * 本例采用指定扫描的包路径来定义指定要建立API的目录。
     *com.petrochina.e7.monitor.controller
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.petrochina.e7.monitor.controller"))
                .paths(PathSelectors.any())
                .build();
    }


    /**
     * 创建该API的基本信息（这些基本信息会展现在文档页面中）
     * 访问地址：http://项目实际地址/swagger-ui.html
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("标题:Monitor使用Swagger2构建RESTful APIs")
                .description("描述信息:更多请关注http://www.baidu.com")
                .termsOfServiceUrl("https://swagger.io/")
                .contact("mzc")
                .version("版本:1.0")
                .build();
    }




}
