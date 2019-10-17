package com.dmh.service;

import com.dmh.bean.Article;
import com.github.pagehelper.PageInfo;


public interface ArticleService {
	
	PageInfo<Article> list(Integer chnId, Integer catId, Integer page);

}
