package com.education.controller;

import com.education.inter.GitHubFeign;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wangrui
 * @ClassName GitHubController
 * @description 使用Feign访问GitHub查询API
 * @date 2020/1/21 16:24
 **/
@RestController
@RequestMapping(
        value = "/github",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
)
public class GitHubController {

    @Resource
    private GitHubFeign gitHubFeign;

    @RequestMapping(
            value = "/search/repositories",
            method = RequestMethod.GET
    )
    String searchRepo(@RequestParam("q") String q) {
        return gitHubFeign.searchRepo(q);
    }
}
