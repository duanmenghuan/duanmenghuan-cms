package com.dmh.service;


import java.util.List;

import com.dmh.bean.Article;
import com.github.pagehelper.PageInfo;


public interface ArticleService {
	
	PageInfo<Article> list(Integer chnId, Integer catId, Integer page);

	PageInfo<Article> hostList(Integer page);

	List<Article> last(int i);

	Article findById(Integer id);

	int add(Article article);

	PageInfo<Article> myarticle(Integer id, int page);

	int remove(Integer id);

	int update(Article article);

	PageInfo<Article> getAdminArticle(Integer page);

	int updateStatus(Integer articleId, int status);

	int updateHot(Integer articleId, int status);

}
