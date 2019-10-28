package com.duanmenghuan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duanmenghuan.bean.Special;
import com.duanmenghuan.dao.ArticleMapper;
import com.duanmenghuan.dao.SpecialMapper;
import com.duanmenghuan.service.SpecialService;

@Service
public class SpecialServiceimpl implements SpecialService {


    @Autowired
    SpecialMapper specialMapper;

    @Autowired
    ArticleMapper articleMapper;

    @Override
    public List<Special> list() {
        List<Special> list = specialMapper.list();
        for(Special special : list){
            special.setArticleNum(articleMapper.getArticleNum(special.getId()));
        }
        return list;
    }

    @Override
    public int add(Special special) {
        return specialMapper.add(special);
    }

    @Override
    public int addArticle(Integer specId, Integer articleId) {
    
        return specialMapper.addArticle(specId,articleId);
    }

    /**
     * Special special = specialMapper.findById(id);
     * 		special.setArtilceList(articleMapper.findBySepecailId(id));
     * @param id
     * @return
     */
    @Override
    public Special findById(Integer id) {
       Special special =  specialMapper.findById(id);
       special.setArtilceList(articleMapper.findBySepecailId(id));
        return special;
    }

    @Override
    public int removeArticle(Integer specId, Integer articleId) {
        return specialMapper.removeArticle( specId,  articleId);
    }

    @Override
    public int update(Special special) {
        return specialMapper.update(special);
    }


}
