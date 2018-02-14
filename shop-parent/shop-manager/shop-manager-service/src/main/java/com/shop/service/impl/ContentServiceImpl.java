package com.shop.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shop.common.pojo.EUDataGridResult;
import com.shop.common.utils.ShopResponse;
import com.shop.mapper.ContentMapper;
import com.shop.pojo.Content;
import com.shop.pojo.ContentExample;
import com.shop.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author: chaodong.xi
 * @since: 2018/2/13
 */

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentMapper contentMapper;

    @Override
    public EUDataGridResult getContentList(int page, int rows) {

        ContentExample contentExample = new ContentExample();
        PageHelper.startPage(page, rows);
        List<Content> contentList = contentMapper.selectByExample(contentExample);

        PageInfo<Content> pageInfo = new PageInfo<>(contentList);
        Long total = pageInfo.getTotal();

        EUDataGridResult euDataGridResult = new EUDataGridResult();
        euDataGridResult.setTotal(total);
        euDataGridResult.setRows(contentList);

        return euDataGridResult;
    }

    @Override
    public ShopResponse insertContent(Content content) {
        content.setCreated(new Date());
        content.setUpdated(new Date());
        boolean insertResult = contentMapper.insert(content) > 0;
        return insertResult ? ShopResponse.ok() : ShopResponse.build(999, "插入失败");
    }
}
