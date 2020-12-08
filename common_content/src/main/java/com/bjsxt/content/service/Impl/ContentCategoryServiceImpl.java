package com.bjsxt.content.service.Impl;

import com.bjsxt.content.service.ContentCategoryService;
import com.bjsxt.mapper.TbContentCategoryMapper;
import com.bjsxt.pojo.TbContentCategory;
import com.bjsxt.pojo.TbContentCategoryExample;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper;

    /**
     * 根据父节点ID查询子节点
     * @param parentId
     * @return
     */
    @Override
    public List<TbContentCategory> selectContentCategoryByParentId(Long parentId) {
        TbContentCategoryExample tbContentCategoryExample = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = tbContentCategoryExample.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbContentCategory> list = this.tbContentCategoryMapper.selectByExample(tbContentCategoryExample);
        return list;
    }

    /**
     * 添加内容分类
     * @param tbContentCategory
     * @return
     */
    @Override
    @LcnTransaction
    public Integer insertContentCategory(TbContentCategory tbContentCategory) {
        //补齐数据
        tbContentCategory.setUpdated(new Date());
        tbContentCategory.setCreated(new Date());
        tbContentCategory.setIsParent(false);
        tbContentCategory.setSortOrder(1);
        tbContentCategory.setStatus(1);

        //插入当前节点
        Integer contentCategoryNum = this.tbContentCategoryMapper.insert(tbContentCategory);

        //查询当前新节点的父节点
        TbContentCategory contentCategory = this.tbContentCategoryMapper.selectByPrimaryKey(tbContentCategory.getParentId());
        //判断当前父节点是否是叶子节点
        if (!contentCategory.getIsParent()) {
            contentCategory.setIsParent(true);
            contentCategory.setUpdated(new Date());
            this.tbContentCategoryMapper.updateByPrimaryKey(contentCategory);
        }
        return contentCategoryNum;
    }

    @Override
    @LcnTransaction
    public Integer deleteContentCategoryById(Long categoryId) {
        //查询当前节点
        TbContentCategory currentCategory = this.tbContentCategoryMapper.selectByPrimaryKey(categoryId);

        //删除当前节点的子节点
        Integer status = this.deleteNode(currentCategory);

        //查询当前节点的父节点
        TbContentCategory parentCategory = this.tbContentCategoryMapper.selectByPrimaryKey(currentCategory.getParentId());

        //查看当前节点是否有兄弟节点，决定是否修改父节点的状态
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentCategory.getId());
        List<TbContentCategory> list = this.tbContentCategoryMapper.selectByExample(example);
        if (list.size() == 0) {
            parentCategory.setIsParent(false);
            parentCategory.setUpdated(new Date());
            this.tbContentCategoryMapper.updateByPrimaryKey(parentCategory);
        }

        return 200;
    }

    /**
     * 删除当前节点子节点
     * @param currentCategory
     * @return
     */
    @LcnTransaction
    private Integer deleteNode(TbContentCategory currentCategory) {
        if (currentCategory.getIsParent()) {
            //父节点
            //查询所有子节点
            TbContentCategoryExample example = new TbContentCategoryExample();
            TbContentCategoryExample.Criteria criteria = example.createCriteria();
            criteria.andParentIdEqualTo(currentCategory.getId());
            List<TbContentCategory> list = this.tbContentCategoryMapper.selectByExample(example);
            for (TbContentCategory tbContentCategory : list) {
                deleteNode(tbContentCategory);
                this.tbContentCategoryMapper.deleteByPrimaryKey(currentCategory.getId());
            }
        } else {
            //不是父节点
            this.tbContentCategoryMapper.deleteByPrimaryKey(currentCategory.getId());
        }
        return 200;
    }

    /**
     * 修改内容分类名称
     * @param tbContentCategory
     * @return
     */
    @Override
    @LcnTransaction
    public Integer updateContentCategory(TbContentCategory tbContentCategory) {
        tbContentCategory.setUpdated(new Date());
        return this.tbContentCategoryMapper.updateByPrimaryKeySelective(tbContentCategory);
    }
}
