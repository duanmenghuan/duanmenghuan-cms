package com.duanmenghuan.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.duanmenghuan.bean.Blogroll;
import com.duanmenghuan.service.BolgrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.duanmenghuan.bean.Article;
import com.duanmenghuan.bean.Cat;
import com.duanmenghuan.bean.Channel;
import com.duanmenghuan.service.ArticleService;
import com.duanmenghuan.service.CatService;
import com.duanmenghuan.service.ChannelService;
import com.duanmenghuan.web.PageUtils;
import com.github.pagehelper.PageInfo;

/**
 * @author duanmenghuan
 */
@Controller
public class IndexController {

    @Autowired
    ChannelService chnlService; //大分类的服务

    @Autowired
    CatService catService; //小分类的服务

    @Autowired
    ArticleService articleService; //文章的分类
    @Autowired
    BolgrollService bolgrollService; //文章的分类

    @RequestMapping("index")
    public String index(HttpServletRequest request,
                        @RequestParam(defaultValue = "0") Integer chnId,
                        @RequestParam(defaultValue = "0") Integer catId,
                        @RequestParam(defaultValue = "1") Integer page
    ) throws InterruptedException {
        Thread t1 = new Thread(){
            @Override
            public void run() {
                // 获取所有大分类
                List<Channel> channels = chnlService.getAllChnls();
                request.setAttribute("chnls", channels);//大分类
            }
        };
        Thread t2;
        if (chnId != 0) {
            t2 = new Thread(){
                 @Override
                 public void run() {
                     //获取该栏目下的所有分类
                     List<Cat> catygories = catService.getListByChnlId(chnId);
                     request.setAttribute("catygories", catygories);//小分类
                     PageInfo<Article> articleList = articleService.list(chnId, catId, page);
                     request.setAttribute("articles", articleList);//文章
                     PageUtils.page(request, "/index?chnId=" + chnId + "&catId=" + catId,
                             3, articleList.getList(),
                             (long) articleList.getTotal(), articleList.getPageNum());
                 }
             };


        } else {
            t2 = new Thread(){
                @Override
                public void run() {
                    //热门文章
                    //获取首页热门文章
                    PageInfo<Article> articleList = articleService.hostList(page);
                    request.setAttribute("articles", articleList);
                    PageUtils.page(request, "/index?chnId=" + chnId + "&catId=" + catId, 3, articleList.getList(),
                            (long) articleList.getTotal(), articleList.getPageNum());
                }
            };

        }
        Thread t3 = new Thread() {
            @Override
            public void run() {
                //获取最新的文章
                List<Article> lastList = articleService.last(5);
                request.setAttribute("lastList", lastList);
            }
        };

         Thread t4  = new Thread(){
             @Override
             public void run() {
                 List<Blogroll> listblogroll = bolgrollService.list();
                 request.setAttribute("listblogroll",listblogroll);
             }
         };


        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
        request.setAttribute("chnId", chnId);
        request.setAttribute("catId", catId);

        return "index";
    }

}
