/**
 * Copyright(C) 2021 Company:easy-spring-staging Co.
 */
package com.easyspring.core.conf.swagger;

import com.easyspring.core.argument.SwaggerParam;
import com.easyspring.core.argument.UserParam;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;

/**
 * swagger抽象配置类.
 *
 * @author caobaoyu
 * @create 2021-09-10 9:56
 **/
public abstract class AbstractSwaggerConfiguration implements WebMvcConfigurer {

    /**
     * 创建swagger文档组
     *
     * @param projectName         工程名称
     * @param basePackage         扫描包
     * @param version             版本表
     * @param globalParameterList 自定义全局参数列表
     * @return springfox.documentation.spring.web.plugins.Docket Docket
     * @Exception
     * @author caobaoyu
     * @date 2021/9/10 14:58
     */
    public Docket createDocket(
            String projectName,
            String basePackage,
            String version,
            List<Parameter> globalParameterList
    ) {
        return createDocket(
                String.format("%s-API文档", projectName),
                basePackage,
                String.format("%s-API文档", projectName),
                String.format("%s-API文档", projectName),
                "#",
                version,
                globalParameterList
        );
    }

    /**
     * 创建swagger文档组
     *
     * @param groupName           组名称
     * @param basePackage         扫描包
     * @param title               标题
     * @param description         描述
     * @param termsOfServiceUrl   成员网址
     * @param version             版本表
     * @param globalParameterList 自定义全局参数列表
     * @return springfox.documentation.spring.web.plugins.Docket Docket
     * @Exception
     * @author caobaoyu
     * @date 2021/9/10 14:58
     */
    public Docket createDocket(String groupName,
                               String basePackage,
                               String title,
                               String description,
                               String termsOfServiceUrl,
                               String version,
                               List<Parameter> globalParameterList
    ) {
        Docket docket = buildDocket(
                groupName,
                basePackage,
                title,
                description,
                termsOfServiceUrl,
                version);

        if (globalParameterList != null && globalParameterList.size() > 0) {
            docket.globalOperationParameters(globalParameterList);
        }
        return docket;

    }

    /**
     * 创建参数,用于给所有接口文档统一加入参数
     *
     * @param name          名称
     * @param description   描述
     * @param dataType      数据类型
     * @param parameterType 参数类型
     * @return springfox.documentation.service.Parameter Parameter
     * @Exception
     * @author caobaoyu
     * @date 2021/9/10 15:01
     */
    protected Parameter createParameter(String name, String description, String dataType, String parameterType) {
        return new ParameterBuilder().name(name)
                .description(description)
                .modelRef(new ModelRef(dataType))
                .parameterType(parameterType)
                .required(false).build();
    }

    /**
     * 构建文档
     *
     * @param groupName         组名称
     * @param basePackage       扫描包
     * @param title             标题
     * @param description       描述
     * @param termsOfServiceUrl 成员网址
     * @param version           版本表
     * @return springfox.documentation.spring.web.plugins.Docket Docket
     * @author caobaoyu
     * @date 2021/9/10 15:04
     */
    private Docket buildDocket(String groupName,
                               String basePackage,
                               String title,
                               String description,
                               String termsOfServiceUrl,
                               String version) {
        return new Docket(DocumentationType.SWAGGER_2)
                .ignoredParameterTypes(SwaggerParam.class, UserParam.class)
                .groupName(groupName)
                .apiInfo(apiInfo(title, description, termsOfServiceUrl, version))
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 构建Docket基本信息
     *
     * @param title             标题
     * @param description       描述
     * @param termsOfServiceUrl 成员网址
     * @param version           版本表
     * @return ApiInfo
     * @author caobaoyu
     * @date 2021/9/10 15:04
     */
    private ApiInfo apiInfo(String title,
                            String description,
                            String termsOfServiceUrl,
                            String version) {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .termsOfServiceUrl(termsOfServiceUrl)
                .version(version)
                .build();
    }

    /**
     * 配置swagger资源路径,解决throw-exception-if-no-handler-found为true时,无法访问swagger资源
     *
     * @param registry
     * @author caobaoyu
     * @date 2021/9/10 15:09
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        registry.addResourceHandler("/swagger-resources/**")
                .addResourceLocations("classpath:/META-INF/resources/swagger-resources/");

        registry.addResourceHandler("/swagger/**")
                .addResourceLocations("classpath:/META-INF/resources/swagger*");

        registry.addResourceHandler("/v2/api-docs/**")
                .addResourceLocations("classpath:/META-INF/resources/v2/api-docs/");
    }


}
