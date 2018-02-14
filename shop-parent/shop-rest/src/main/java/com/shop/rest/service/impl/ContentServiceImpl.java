package com.shop.rest.service.impl;

import com.shop.mapper.ContentMapper;
import com.shop.pojo.Content;
import com.shop.pojo.ContentExample;
import com.shop.rest.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<Content> getContentList(long categoryId) {

        ContentExample example = new ContentExample();
        ContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        return contentMapper.selectByExample(example);
    }
}
