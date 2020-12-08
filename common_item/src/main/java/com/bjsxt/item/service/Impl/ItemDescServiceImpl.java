package com.bjsxt.item.service.Impl;

import com.bjsxt.item.service.ItemDescService;
import com.bjsxt.mapper.TbItemDescMapper;
import com.bjsxt.pojo.TbItemDesc;
import com.bjsxt.pojo.TbItemDescExample;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemDescServiceImpl implements ItemDescService {

    @Autowired
    private TbItemDescMapper tbItemDescMapper;

    @Override
    @LcnTransaction
    public Integer insertItemDesc(TbItemDesc tbItemDesc) {
        return this.tbItemDescMapper.insert(tbItemDesc);
    }

    /**
     * 更新商品描述
     * @return
     */
    @Override
    @LcnTransaction
    public Integer updateItemDesc(TbItemDesc tbItemDesc) {
        tbItemDesc.setUpdated(new Date());
        return this.tbItemDescMapper.updateByPrimaryKeySelective(tbItemDesc);
    }

    @Override
    public TbItemDesc selectItemDescByItemId(Long itemId) {
        TbItemDescExample example = new TbItemDescExample();
        TbItemDescExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        List<TbItemDesc> list = this.tbItemDescMapper.selectByExampleWithBLOBs(example);
        return list.get(0);
    }
}
