package com.dmh.service.impl;

import com.dmh.bean.Article;
import com.dmh.service.ArticleService;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestArticle extends  BaseTest {
    @Autowired
    ArticleService articleService;

    @Test
    public void  testList(){
        int chnId = 3;
        PageInfo<Article> list = articleService.list(3, 0, 1);
        if (list!=null&&list.getList()!=null){
            list.getList().forEach(str ->{
                System.out.println(" str is"+str);
            });
        }
    }
}
