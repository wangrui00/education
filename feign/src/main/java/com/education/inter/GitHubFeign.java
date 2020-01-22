package com.education.inter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wangrui
 * @ClassName GitHubFeign
 * @description 使用Feign访问github查询API
 * @date 2020/1/21 16:22
 **/
@FeignClient(name = "github-client" ,url = "https://api.github.com")
public interface GitHubFeign {

    @RequestMapping(
            value = "/search/repositories",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    String searchRepo(@RequestParam("q") String q);
}
