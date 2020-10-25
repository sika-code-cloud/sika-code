package com.zyd.blog.business.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.zyd.blog.business.dto.BatchArticleLookDTO;
import com.zyd.blog.business.entity.Article;
import com.zyd.blog.business.entity.ArticleLook;
import com.zyd.blog.business.enums.SourceEnum;
import com.zyd.blog.business.service.BizArticleLookService;
import com.zyd.blog.persistence.beans.BizArticle;
import com.zyd.blog.persistence.beans.BizArticleLook;
import com.zyd.blog.persistence.mapper.BizArticleLookMapper;
import com.zyd.blog.persistence.mapper.BizArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * 文章浏览记录
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Service
public class BizArticleLookServiceImpl implements BizArticleLookService {

    @Autowired
    private BizArticleLookMapper bizArticleLookMapper;
    @Autowired
    private BizArticleMapper bizArticleMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ArticleLook insert(ArticleLook entity) {
        Assert.notNull(entity, "ArticleLook不可为空！");
        entity.setUpdateTime(new Date());
        entity.setCreateTime(new Date());
        bizArticleLookMapper.insertSelective(entity.getBizArticleLook());
        return entity;
    }


    public Integer systemInsert(BatchArticleLookDTO articleLookDTO) {
        Assert.notNull(articleLookDTO.getArticleId(), "文章编号不可为空！");
        Integer addNumber = articleLookDTO.getAddNumberNullToRandom();
        for (int i = 0; i < addNumber; ++i) {
            BizArticleLook bizArticleLook = new BizArticleLook();
            bizArticleLook.setSource(SourceEnum.SYSTEM.getType());
            bizArticleLook.setArticleId(articleLookDTO.getArticleId());
            bizArticleLook.setUpdateTime(new Date());
            bizArticleLook.setCreateTime(new Date());
            bizArticleLook.setUserIp("127.0.0.1");
            bizArticleLookMapper.insertSelective(bizArticleLook);
        }
        return addNumber;
    }

    @Override
    public Integer systemBrushFlow() {
        List<BizArticle> bizArticles = bizArticleMapper.selectAll();
        if (CollUtil.isEmpty(bizArticles)) {
            return 0;
        }
        Integer addTotal = 0;
        for (BizArticle bizArticle : bizArticles) {
            addTotal += systemInsert(new BatchArticleLookDTO().setArticleId(bizArticle.getId()));
        }
        return addTotal;
    }

}
