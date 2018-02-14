package com.shop.rest.service.impl;

import com.shop.mapper.ItemCatMapper;
import com.shop.pojo.ItemCat;
import com.shop.pojo.ItemCatExample;
import com.shop.rest.pojo.CatNode;
import com.shop.rest.pojo.CatResult;
import com.shop.rest.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: chaodong.xi
 * @since: 2018/2/9
 */

@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private ItemCatMapper itemCatMapper;

    @Override
    public CatResult getItemCatList() {

        CatResult catResult = new CatResult();
        catResult.setData(getCatList(0));
        return catResult;
    }

    private List<?> getCatList(long parentId) {
        ItemCatExample itemCatExample = new ItemCatExample();
        ItemCatExample.Criteria criteria = itemCatExample.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<ItemCat> itemCatList = itemCatMapper.selectByExample(itemCatExample);

        List<Object> catNodeList = new ArrayList<>();
        int catCount = 0;

        for(ItemCat itemCat  : itemCatList) {
            if (itemCat.getIsParent()) {
                CatNode catNode = new CatNode();
                if (parentId == 0) {
                    catNode.setName("<a href='/products/" + itemCat.getId() + ".html'>" + itemCat.getName() + "</a>");
                } else {
                    catNode.setName(itemCat.getName());
                }
                catNode.setUrl("/products/" + itemCat.getId() + ".html");
                catNode.setItem(getCatList(itemCat.getId()));

                catNodeList.add(catNode);
                catCount++;
                if(parentId == 0 && catCount == 14){
                    break;
                }
            } else {
                catNodeList.add("/products/" + itemCat.getId() + ".html|" + itemCat.getName());
            }
        }
        return catNodeList;
    }
}
