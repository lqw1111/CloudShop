package com.bjsxt.item.service.Impl;

import com.bjsxt.item.service.ItemCategoryService;
import com.bjsxt.mapper.TbItemCatMapper;
import com.bjsxt.pojo.TbItemCat;
import com.bjsxt.pojo.TbItemCatExample;
import com.bjsxt.utils.CatNode;
import com.bjsxt.utils.CatResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {

    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    /**
     * 根据分类id查询子节点
     * @param id
     * @return
     */
    @Override
    public List<TbItemCat> selectItemCategoryByParentId(Long id) {
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(id);
        criteria.andStatusEqualTo(1);
        List<TbItemCat> list = this.tbItemCatMapper.selectByExample(example);
        return list;
    }

    /**
     * 查询首页商品分类
     * @return
     */
    @Override
    public CatResult selectItemCategoryAll() {
        CatResult catResult = new CatResult();
        //查询商品分类
        catResult.setData(getCatList(0L));
        return catResult;
    }

    /**
     * 商品查询
     * @param parentId
     * @return
     */
    private List<?> getCatList(Long parentId) {
        //创建查询条件
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> list = this.tbItemCatMapper.selectByExample(example);
        List resultList = new ArrayList();
        int count = 0;
        for (TbItemCat tbItemCat : list) {
            if (tbItemCat.getIsParent()) {
                CatNode catNode = new CatNode();
                catNode.setName(tbItemCat.getName());
                catNode.setItem(getCatList(tbItemCat.getId()));
                resultList.add(catNode);
                count ++ ;
                if (count >= 18) {
                    break;
                }
            } else {
                resultList.add(tbItemCat.getName());
            }
        }
        return resultList;
    }
}
