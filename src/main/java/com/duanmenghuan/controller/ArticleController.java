package com.duanmenghuan.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.duanmenghuan.bean.*;
import com.duanmenghuan.comon.ArticleType;
import com.duanmenghuan.service.CommentService;
import com.duanmenghuan.web.PageUtils;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
/**
 *
 * @author duanmenghuan
 *
 */
import org.springframework.web.multipart.MultipartFile;

import com.duanmenghuan.comon.ConstClass;
import com.duanmenghuan.service.ArticleService;
import com.duanmenghuan.service.CatService;
import com.duanmenghuan.service.ChannelService;

@Controller
@RequestMapping("article")
public class ArticleController {


    @Autowired
    ArticleService articleService;

    @Autowired
    ChannelService channelService;

    @Autowired
    CatService catService;

    @Autowired
    CommentService commentService;

    /**
     * 显示一片具体的文章
     *
     * @param request
     * @param id      文章的ID
     * @return
     */
	@RequestMapping("show")
    public String show(HttpServletRequest request, Integer id) {
        Article article = articleService.findById(id);
        if (article.getArticleType()== ArticleType.HTML){
            request.setAttribute("article", article);
            return "/article/detail";
        }else {
            Gson gson = new Gson();
            article.setImgList(gson.fromJson(article.getContent(),List.class));
            request.setAttribute("article", article);
            return "article/slieimgarticle";
        }

    }

    /**
     * 获取全部评论
     * @param request
     * @param articleId
     * @param page
     * @return
     */
    @RequestMapping("getlist")
    public String show(HttpServletRequest request, Integer articleId,@RequestParam(defaultValue = "1") int page) {
        PageInfo<Comment> commenPage =  commentService.listss(articleId,page);
        String pagestr = PageUtils.pageLoad(page, commenPage.getPageNum(), "/my/list?page"+page,commenPage.getSize());
        request.setAttribute("pagestr", pagestr);
        request.setAttribute("commenPage", commenPage);
        return "/my/list1";
    }

    /**
     * 跳转到添加页面
     *
     * @param request
     * @return
     */
    @GetMapping("add")
    public String add(HttpServletRequest request) {
        List<Channel> allChnls = channelService.getAllChnls();
        request.setAttribute("allChnls", allChnls);
        return "article/publish";
    }

    /**
     * 用户发送文章
     *
     * @param request
     * @return
     * @throws IOException
     * @throws IllegalStateException
     */
    @PostMapping("add")
    public String add(HttpServletRequest request, MultipartFile file, Article article) throws IllegalStateException, IOException {
        processFile(file, article);
        User user = (User) request.getSession().getAttribute(ConstClass.SESSION_USER_KEY);
        System.out.println("user is " + user);
        article.setUserId(user.getId());
        articleService.add(article);
        return "article/publish";
    }

    /**
     * 跳转到发送多个图片页面
     * @param request
     * @return
     */
    @RequestMapping(value = "addimg",method=RequestMethod.GET)
    public String addimg(HttpServletRequest request) {
        List<Channel> allChnls = channelService.getAllChnls();
        request.setAttribute("channels", allChnls);
        return "article/publishimg";

    }

    /**
     * 多个图片上传
     * @param request
     * @param article
     * @param file
     * @param imgs
     * @param imgsdesc
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    @PostMapping("addimg")
    public String addimg(HttpServletRequest request,Article article,
                         @RequestParam("file") MultipartFile file,//标题图片
                         @RequestParam("imgs") MultipartFile[] imgs,// 文章中图片
                         @RequestParam("imgsdesc") String[]  imgsdesc// 文章中图片的描述
    ) throws IllegalStateException, IOException {
        article.setArticleType(ArticleType.IMAGE);

        processFile(file,article);
        List<ImageBean> imgBeans =  new ArrayList<ImageBean>();

        for (int i = 0; i < imgs.length; i++) {
            String picUrl = processFile(imgs[i]);//
            if(!"".equals(picUrl)) {
                ImageBean imageBean = new ImageBean(imgsdesc[i],picUrl);
                imgBeans.add(imageBean);
            }

        }

        Gson gson = new Gson();
        String json = gson.toJson(imgBeans);// 文章的内容
        article.setContent(json);//


        //获取作者
        User loginUser = (User)request.getSession().getAttribute(ConstClass.SESSION_USER_KEY);
        article.setUserId(loginUser.getId());

        articleService.add(article);

        return "article/publish";
    }
    /**
     * 根据频道获取相应的分类  用户发布文章或者修改文章的下拉框
     *
     * @param chnlId
     * @return
     */
    @GetMapping("listCatByChnl")
    @ResponseBody
    //public  List<Cat> getCatByChnl(int chnlId)
    public ResultMsg getCatByChnl(int chnlId) {
        List<Cat> chnlList = catService.getListByChnlId(chnlId);
        //System.out.println("chnlList is" +chnlList);
        return new ResultMsg(1, "获取数据成功", chnlList);
        // chnlList;
    }

    /**
     * 调到修改页面
     *
     * @param request
     * @param id
     * @return
     */
    @GetMapping("update")
    public String update(HttpServletRequest request, Integer id) {
        List<Channel> allChnls = channelService.getAllChnls();

        Article article = articleService.findById(id);

        request.setAttribute("article", article);

        request.setAttribute("channels", allChnls);

        request.setAttribute("content1", article.getContent());
        return "my/update";
    }

    /**修改文章
     * @param request
     * @param article
     * @param file
     * @return
     * @throws IOException
     * @throws IllegalStateException
     */
    @PostMapping("update")
    @ResponseBody
    public boolean update(HttpServletRequest request, Article article, MultipartFile file) throws IllegalStateException, IOException {
        processFile(file, article);
        User loginUser = (User) request.getSession().getAttribute(ConstClass.SESSION_USER_KEY);
        article.setUserId(loginUser.getId());
        int result = articleService.update(article);
        System.out.println(result);
        return result > 0;
    }

    /**
     * 文件上传
     *
     * @param file
     * @param article
     * @return
     * @throws IOException
     * @throws IllegalStateException
     */
    public void processFile(MultipartFile file, Article article) throws IllegalStateException, IOException {
        if (file.isEmpty() || "".equals(file.getOriginalFilename())
                || file.getOriginalFilename().lastIndexOf('.') < 0) {
            article.setPicture("");
            return;
        }
        /**
         *  获取原来的路径字符串
         */
        String originalFilename = file.getOriginalFilename();
        String suffixName = originalFilename.substring(originalFilename.lastIndexOf('.'));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String path = "d:/pic/" + sdf.format(new Date());
        File file2 = new File(path);
        if (!file2.exists()) {
            //如果没有创建文件夹
            file2.mkdirs();
        }
        String destFileName = path + "/" + UUID.randomUUID().toString() + suffixName;
        File distFile = new File(destFileName);
        file.transferTo(distFile);
        article.setPicture(destFileName.substring(7));
        System.out.println("路径" + article.getPicture());
    }


    /**
     * 处理每一个图片集合中的文件
     * @param file
     * @param article
     * @throws IllegalStateException
     * @throws IOException
     */
    private String processFile(MultipartFile file) throws IllegalStateException, IOException {

        // 原来的文件名称
        System.out.println("file.isEmpty() :" + file.isEmpty()  );
        System.out.println("file.name :" + file.getOriginalFilename());

        if(file.isEmpty()||"".equals(file.getOriginalFilename()) || file.getOriginalFilename().lastIndexOf('.')<0 ) {
            return "";
        }

        String originName = file.getOriginalFilename();
        String suffixName = originName.substring(originName.lastIndexOf('.'));
        SimpleDateFormat sdf=  new SimpleDateFormat("yyyyMMdd");
        String path = "d:/pic/" + sdf.format(new Date());
        File pathFile = new File(path);
        if(!pathFile.exists()) {
            pathFile.mkdir();
        }
        String destFileName = 		path + "/" +  UUID.randomUUID().toString() + suffixName;
        File distFile = new File( destFileName);
        file.transferTo(distFile);//文件另存到这个目录下边
        return destFileName.substring(7);


    }

}
