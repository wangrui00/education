package com.education.route;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author wangrui
 * @ClassName GatewayFilterDefinition
 * @description 过滤器定义模型
 * @date 2020/1/15 9:54
 **/
@Data
public class GatewayFilterDefinition {

    /** Filter name**/
    private String name;

    /** 对应的路由规则 **/
    private Map<String,String> args = new LinkedHashMap<>();

}
