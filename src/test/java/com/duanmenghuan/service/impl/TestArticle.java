package com.duanmenghuan.service.impl;

import com.duanmenghuan.bean.Article;
import com.duanmenghuan.bean.Blogroll;
import com.duanmenghuan.service.ArticleService;
import com.duanmenghuan.service.BolgrollService;
import com.github.pagehelper.PageInfo;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TestArticle extends BaseTest {
    @Autowired
    ArticleService articleService;

    @Autowired
    BolgrollService bolgrollService;

    @Test
    public void testList() {
        int chnId = 3;
        PageInfo<Article> list = articleService.list(3, 0, 1);
        if (list != null && list.getList() != null) {
            list.getList().forEach(str -> {
                System.out.println(" str is" + str);
            });
        }
    }


    @Test
    public void testGetAarticle() {

        System.out.println(" 33  文章是 ： " +  articleService.findById(33).toString());

        System.out.println(" 34  文章是 ： " + articleService.findById(34).toString());

    }


    @Test
    public  void  testyouqing(){
        List<Blogroll> listblogroll = bolgrollService.list();
        System.out.println(listblogroll);
    }
}
