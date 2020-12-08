package com.bjsxt.front.portal.service.Impl;

import com.bjsxt.front.portal.feign.CommonContentFeignClient;
import com.bjsxt.front.portal.feign.CommonRedisFeignClient;
import com.bjsxt.front.portal.service.ContentService;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private CommonContentFeignClient commonContentFeignClient;

    @Autowired
    private CommonRedisFeignClient commonRedisFeignClient;

    @Override
    public Result selectFrontendContentByAD() {
        //查询缓存
        try {
            List<Map> list = this.commonRedisFeignClient.selectContentAD();
            if (list != null && list.size() > 0) {
                return Result.ok(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Map> list = this.commonContentFeignClient.selectFrontendContentByAD();
        try {
            if (list != null && list.size() > 0) {
                this.commonRedisFeignClient.insertContentAD(list);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        if (list != null && list.size() > 0) {
            return Result.ok(list);
        }
        return Result.error("查无结果");
    }
}
