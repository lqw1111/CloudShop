package com.bjsxt.common.redis.service;

import java.util.List;
import java.util.Map;

public interface ContentService {
    void insertContentAD(List<Map> list);

    List<Map> selectContentAD();
}
