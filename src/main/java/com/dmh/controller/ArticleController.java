package com.dmh.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 
 * @author dmh
 *
 */
import org.springframework.web.multipart.MultipartFile;

import com.dmh.bean.Article;
import com.dmh.bean.Cat;
import com.dmh.bean.Channel;
import com.dmh.bean.ResultMsg;
import com.dmh.bean.User;
import com.dmh.comon.ConstClass;
import com.dmh.service.ArticleService;
import com.dmh.service.CatService;
import com.dmh.service.ChannelService;
@Controller
@RequestMapping("article")
public class ArticleController {
	
	
	@Autowired
	ArticleService  articleService;
	
	@Autowired
	ChannelService channelService;
	
	@Autowired
	CatService  catService;
	
	/**
	 *  显示一片具体的文章
	 * @param request
	 * @param id 文章的ID
	 * @return
	 */
	@RequestMapping("show")
	public String  show(HttpServletRequest request,Integer id) 	         
	{
		Article article = articleService.findById(id);
		request.setAttribute("article",article);
		return "article/detail";
	}
	
	/**
	 * 跳转到添加页面
	 * @param request
	 * @return
	 */
	@GetMapping("add")
	public  String  add(HttpServletRequest request) 
	{
		List<Channel> allChnls = channelService.getAllChnls();
		 request.setAttribute("allChnls",allChnls);
		 
		return "article/publish";
	}
   
	/**
	 * 文件上传
	 * @param file
	 * @param article
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	public void processFile(MultipartFile file,Article article) throws IllegalStateException, IOException 
	{   
		if(file.isEmpty()||"".equals(file.getOriginalFilename()) 
		                 || file.getOriginalFilename().lastIndexOf('.')<0)
		{
			article.setPicture("");
			return ;
		}
		/**
		 *  获取原来的路径字符串
		 */
		String originalFilename = file.getOriginalFilename();
		String suffixName = originalFilename.substring(originalFilename.lastIndexOf('.'));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String path = "d:/pic/"+sdf.format(new Date());
		File file2 = new File(path);
		if(!file2.exists())
		{
		  //如果没有创建文件夹
		  file2.mkdirs();
		}
		String destFileName = path+"/"+UUID.randomUUID().toString()+suffixName;
		File distFile = new File(destFileName);
		file.transferTo(distFile);
		article.setPicture(destFileName.substring(7));
		System.out.println("路径"+article.getPicture());
	}
	/**
	 * 用户发送文章
	 * @param request
	 * @return 
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@PostMapping("add")
	public String add(HttpServletRequest request,MultipartFile file,Article article) throws IllegalStateException, IOException
	{
		processFile(file, article);
		User user = (User)request.getSession().getAttribute(ConstClass.SESSION_USER_KEY);
		System.out.println("user is "+ user);
		article.setUserId(user.getId());
		articleService.add(article);
		return "article/publish";
	}
	
	/**
	 * 根据频道获取相应的分类  用户发布文章或者修改文章的下拉框
	 * @param chnID
	 * @return
	 */
	@GetMapping("listCatByChnl")
	@ResponseBody
	//public  List<Cat> getCatByChnl(int chnlId)
	public ResultMsg getCatByChnl(int chnlId)
	{
		List<Cat> chnlList = catService.getListByChnlId(chnlId);
		//System.out.println("chnlList is" +chnlList);
		return  new ResultMsg(1,"获取数据成功",chnlList);
				// chnlList;	
	}
	
	
	
	/**
	 * 调到修改页面
	 * @param request
	 * @param id
	 * @return
	 */
	@GetMapping("update")
	public String update(HttpServletRequest request,Integer id)
	{
		List<Channel> allChnls =  channelService.getAllChnls();	
		
		Article article = articleService.findById(id);
		
		request.setAttribute("article",article);
		
		request.setAttribute("channels",allChnls);
		
		request.setAttribute("content1",article.getContent());
		return "my/update";
	}
	/**
	 * 
	 * @param request
	 * @param article
	 * @param file
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@PostMapping("update")
	@ResponseBody
	public boolean update(HttpServletRequest request,Article article,MultipartFile file) throws IllegalStateException, IOException 
	{
	 processFile(file, article);
	 User loginUser = (User)request.getSession().getAttribute(ConstClass.SESSION_USER_KEY);
	 article.setUserId(loginUser.getId());
	 int result = articleService.update(article);
	 System.out.println(result);
	 return result > 0;
	}
}
