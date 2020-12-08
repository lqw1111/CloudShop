package com.bjsxt.item.service.Impl;

import com.bjsxt.item.service.ItemService;
import com.bjsxt.mapper.TbItemCatMapper;
import com.bjsxt.mapper.TbItemDescMapper;
import com.bjsxt.mapper.TbItemMapper;
import com.bjsxt.mapper.TbItemParamItemMapper;
import com.bjsxt.pojo.*;
import com.bjsxt.utils.PageResult;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper tbItemMapper;

    @Autowired
    private TbItemDescMapper tbItemDescMapper;

    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;

    /**
     * 查询所有商品并分页
     * @param page
     * @param rows
     * @return
     */
    @Override
    public PageResult selectTbItemAllByPage(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo((byte)1);
        List<TbItem> list = this.tbItemMapper.selectByExample(example);
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        PageResult result = new PageResult();
        result.setPageIndex(page);
        result.setTotalPage(pageInfo.getTotal());
        result.setResult(list);
        return result;
    }

    @Override
    @LcnTransaction
    public Integer insertTBItem(TbItem tbItem) {
        return this.tbItemMapper.insert(tbItem);
    }

    /**
     * 更新与删除商品
     * @param tbItem
     * @return
     */
    @Override
    @LcnTransaction
    public Integer updateItemById(TbItem tbItem) {
        tbItem.setUpdated(new Date());
        return this.tbItemMapper.updateByPrimaryKeySelective(tbItem);
    }

    /**
     * 根据史昂品Id查询商品信息
     * @param itemId
     * @return
     */
    @Override
    public Map<String, Object> preUpdateItem(Long itemId) {
        Map<String, Object> map = new HashMap<>();

        TbItem item = this.tbItemMapper.selectByPrimaryKey(itemId);
        map.put("item", item);

        TbItemDesc tbItemDesc = this.tbItemDescMapper.selectByPrimaryKey(itemId);
        map.put("itemDesc", tbItemDesc.getItemDesc());

        TbItemCat tbItemCat = this.tbItemCatMapper.selectByPrimaryKey(item.getCid());
        map.put("itemCat", tbItemCat.getName());

        //商品规格参数
        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        List<TbItemParamItem> list = this.tbItemParamItemMapper.selectByExampleWithBLOBs(example);
        if (list != null && list.size() > 0) {
            map.put("itemParamItem", list.get(0));
        }

        return map;
    }

    @Override
    public TbItem selectItemInfo(Long itemId) {
        return this.tbItemMapper.selectByPrimaryKey(itemId);
    }


}
