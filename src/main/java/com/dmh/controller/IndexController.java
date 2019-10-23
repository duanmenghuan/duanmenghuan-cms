package com.dmh.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dmh.bean.Article;
import com.dmh.bean.Cat;
import com.dmh.bean.Channel;
import com.dmh.service.ArticleService;
import com.dmh.service.CatService;
import com.dmh.service.ChannelService;
import com.dmh.web.PageUtils;
import com.github.pagehelper.PageInfo;

/**
 * 
 * @author duanmenghuan
 *
 */
@Controller
public class IndexController {

	@Autowired
	ChannelService chnlService; //大分类的服务
	
	@Autowired
	CatService catService; //小分类的服务
	
	@Autowired
	ArticleService articleService; //文章的分类

	@RequestMapping("index")
	public String index(HttpServletRequest request,
			@RequestParam(defaultValue="0") Integer chnId,
			@RequestParam(defaultValue="0")  Integer catId,
			@RequestParam(defaultValue="1")  Integer page
			) {

		// 获取所有大分类
		List<Channel> channels = chnlService.getAllChnls();
		if(chnId!=0) {
			//获取该栏目下的所有分类
			List<Cat> catygories = catService.getListByChnlId(chnId); 
			request.setAttribute("catygories", catygories);//小分类
			PageInfo<Article>  articleList = articleService.list(chnId,catId,page);
			request.setAttribute("articles", articleList);//文章
		PageUtils.page(request,"/index?chnId="+chnId+"&catId="+catId, 3, articleList.getList(), 
					(long)articleList.getTotal(), articleList.getPageNum());
			
		}else {
			//热门文章
			//获取首页热门文章
			PageInfo<Article>  articleList = articleService.hostList(page);
			request.setAttribute("articles", articleList);
			PageUtils.page(request,"/index?chnId="+chnId+"&catId="+catId, 3, articleList.getList(), 
					(long)articleList.getTotal(), articleList.getPageNum());
		}
		
		//获取最新的文章
		List<Article> lastList =  articleService.last(5);
		request.setAttribute("lastList", lastList);
		request.setAttribute("chnls", channels);//大分类
		request.setAttribute("chnId", chnId);
		request.setAttribute("catId", catId);
		
		return "index";
	}

}
