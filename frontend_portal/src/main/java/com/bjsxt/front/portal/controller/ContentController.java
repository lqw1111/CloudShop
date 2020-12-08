package com.bjsxt.front.portal.controller;

import com.bjsxt.front.portal.service.ContentService;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/frontend/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @RequestMapping("/selectFrontendContentByAD")
    public Result selectFrontendContentByAD() {
        try {
            return contentService.selectFrontendContentByAD();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error("error");
    }
}
