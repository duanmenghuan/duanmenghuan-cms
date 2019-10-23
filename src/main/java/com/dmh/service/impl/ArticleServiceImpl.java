package com.dmh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmh.bean.Article;
import com.dmh.bean.Tag;
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

		PageHelper.startPage(page, 3);
		// TODO Auto-generated method stub
		return new PageInfo<Article>(articleMapper.list(chnId, catId));
	}

	@Override
	public PageInfo<Article> hostList(Integer page) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, 3);

		return new PageInfo<Article>(articleMapper.articleList());
	}

	@Override
	public List<Article> last(int i) {
		// TODO Auto-generated method stub
		return articleMapper.lastlist(i);
	}

	@Override
	public Article findById(Integer id) {
		// TODO Auto-generated method stub
		return articleMapper.oneArticle(id);
	}

	@Override
	public int add(Article article) {
		// TODO Auto-generated method stub
		int result = articleMapper.add(article);
		processTag(article);
		return result;

	}

	/**
	 * ID 用户的 page 分页
	 */
	@Override
	public PageInfo<Article> myarticle(Integer id, int page) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, 5);
		return new PageInfo<Article>(articleMapper.listByUserId(id));
	}

	@Override
	public int remove(Integer id) {
		// TODO Auto-generated method stub
		return articleMapper.remove(id);
	}

	@Override
	public int update(Article article) {
		// TODO Auto-generated method stub
		 int  result = articleMapper.update(article);
		// 删除中间表中的
		 articleMapper.deleteByArticleId(article.getId());
		 processTag(article);
		return result ;
	}

	@Override
	public PageInfo<Article> getAdminArticle(Integer page) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, 5);
		return new PageInfo<Article>(articleMapper.listAdmin());
	}

	@Override
	public int updateStatus(Integer articleId, int status) {
		// TODO Auto-generated method stub
		return articleMapper.updateStatus(articleId, status);
	}

	@Override
	public int updateHot(Integer articleId, int status) {
		// TODO Auto-generated method stub
		return articleMapper.updateHot(articleId, status);
	}

	/**
	 * 处理标签
	 * 
	 * @param article
	 */
	public void processTag(Article article) {
		String[] tags = article.getTags().split(",");

		for (String tag : tags) {
			Tag tagBean = articleMapper.findTagByName(tag);
			if (tagBean == null) {
				tagBean = new Tag(tag);
				articleMapper.addTag(tagBean);
			}
			
			//插入中间表
			try {
				articleMapper.addArticleTag(article.getId(),tagBean.getId());
			}catch(Exception e){
				System.out.println("插入失败 ");
			}	
		}

	}

}
