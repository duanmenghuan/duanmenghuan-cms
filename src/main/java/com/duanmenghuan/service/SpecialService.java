package com.duanmenghuan.service;

import com.duanmenghuan.bean.Special;

import java.util.List;

public interface SpecialService {
    List<Special> list();

    int add(Special special);

    int addArticle(Integer specId, Integer articleId);

    Special findById(Integer id);

    int removeArticle(Integer specId, Integer articleId);

    int update(Special special);

}
