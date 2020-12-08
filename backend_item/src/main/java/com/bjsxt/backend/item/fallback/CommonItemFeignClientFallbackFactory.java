package com.bjsxt.backend.item.fallback;

import com.bjsxt.backend.item.feign.CommonItemFeignClient;
import com.bjsxt.pojo.*;
import com.bjsxt.utils.PageResult;
import feign.hystrix.FallbackFactory;

import java.util.List;
import java.util.Map;

/**
 * Common item服务返回拖底数据
 */
public class CommonItemFeignClientFallbackFactory implements FallbackFactory<CommonItemFeignClient> {
    @Override
    public CommonItemFeignClient create(Throwable throwable) {
        return new CommonItemFeignClient() {
            @Override
            public PageResult selectTbItemAllByPage(Integer page, Integer rows) {
                return null;
            }

            @Override
            public Integer insertTbItem(TbItem tbItem) {
                return null;
            }

            @Override
            public Integer deleteItemById(TbItem tbItem) {
                return null;
            }

            @Override
            public Map<String, Object> preUpdateItem(Long itemId) {
                return null;
            }

            @Override
            public Integer updateTbitem(TbItem tbItem) {
                return null;
            }

            @Override
            public List<TbItemCat> selectItemCategoryByParentId(Long id) {
                return null;
            }

            @Override
            public TbItemParam selectItemParamByItemCatId(Long itemCatId) {
                return null;
            }

            @Override
            public PageResult selectItemParamAll(Integer page, Integer rows) {
                return null;
            }

            @Override
            public Integer insertItemParam(TbItemParam tbItemParam) {
                return null;
            }

            @Override
            public Integer deleteItemParamById(Long id) {
                return null;
            }

            @Override
            public Integer insertItemDesc(TbItemDesc tbItemDesc) {
                return null;
            }

            @Override
            public Integer updateItemDesc(TbItemDesc tbItemDesc) {
                return null;
            }

            @Override
            public Integer insertTbItemParamItem(TbItemParamItem tbItemParamItem) {
                return null;
            }

            @Override
            public Integer updateItemParamItem(TbItemParamItem tbItemParamItem) {
                return null;
            }
        };
    }
}
