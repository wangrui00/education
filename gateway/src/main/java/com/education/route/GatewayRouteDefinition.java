package com.education.route;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangrui
 * @ClassName GatewayRouteDefinition
 * @description 路由定义模型
 * @date 2020/1/15 9:48
 **/
@Data
public class GatewayRouteDefinition {

    //路由的id
    private String id;

    //路由断言集合配置
    private List<GatewayPredicateDefinition> predicates = new ArrayList<>();

    //路由过滤器集合配置
    private List<GatewayFilterDefinition> filters = new ArrayList<>();

    //路由规则转发的目标uri
    private String uri;

    //路由执行的顺序
    private int order = 0;
}
