package com.education.route;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author wangrui
 * @ClassName GatewayPredicateDefinition
 * @description 路由断言定义模型
 * @date 2020/1/15 9:56
 **/
@Data
public class GatewayPredicateDefinition {

    /*** 断言对应的name **/
    private String name;

    /**  配置的断言规则 **/
    private Map<String,String> args = new LinkedHashMap<>();
}
