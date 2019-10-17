package com.dmh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmh.bean.Article;
import com.dmh.dao.ArticleMapper;
import com.dmh.service.ArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ArticleServiceImpl implements ArticleService {
	
	@Autowired
	ArticleMapper articleMapper;

	@Override
	public PageInfo<Article> list(Integer chnId, Integer catId, Integer page) {
		
		PageHelper.startPage(page, 10);
		// TODO Auto-generated method stub
		return new PageInfo(articleMapper.list(chnId,catId)) ;
	}

}
