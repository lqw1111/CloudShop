package com.bjsxt.front.portal.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@FeignClient(value = "common-content")
public interface CommonContentFeignClient {

    @PostMapping("/service/content/selectFrontendContentByAD")
    List<Map> selectFrontendContentByAD();
}
