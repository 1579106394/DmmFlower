package com.flower.controller;

import com.flower.pojo.News;
import com.flower.service.NewsService;
import com.flower.utils.Page;
import com.flower.utils.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("api/news/")
public class NewsController {

    @Autowired
    private NewsService newsService;

    /**
     * 公告列表
     *
     * @param model
     * @param p
     * @return
     */
    @RequestMapping("newsList.html")
    public String newsList(Model model, Page<News> p) {

        Page<News> page = newsService.getNewsList(p);

        model.addAttribute("page", page);

        return "news/news-list";
    }

    /**
     * 添加公告
     *
     * @param news
     * @return
     */
    @RequestMapping("addNews.html")
    public String addNews(News news) {

        newsService.addNews(news);

        return "redirect:/api/news/newsList.html";
    }



    /**
     * 编辑公告
     *
     * @param news
     * @return
     */
    @RequestMapping("editNews.html")
    public String editNews(News news) {

        newsService.editNews(news);

        return "redirect:/api/news/newsList.html";
    }

    /**
     * 根据id删除公告
     *
     * @param newsId
     * @return
     */
    @RequestMapping("deleteNews{newsId}.html")
    public String deleteNews(@PathVariable String newsId, String[] ids) {
        try {
            if (StringUtils.isNotBlank(newsId)) {
                newsService.deleteNewsById(newsId);
            } else {
                for (String id : ids) {
                    newsService.deleteNewsById(id);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return "redirect:/api/news/newsList.html";
    }

    /**
     * 跳转到编辑公告
     *
     * @param newsId
     * @param model
     * @return
     */
    @RequestMapping("toEdit{newsId}.html")
    public String toEdit(@PathVariable String newsId, Model model) {
        News news = newsService.getNewsById(newsId);
        model.addAttribute("news", news);

        return "news/news-edit";
    }


    /**
     * ajax查看公告
     * @return
     */
    @RequestMapping("ajaxReadNews.action")
    @ResponseBody
    public Result ajaxReadNews(@RequestBody News news) {
        News n = newsService.getNewsById(news.getNewsId());
        return Result.ok(n);
    }


    /**
     * 阅读公告
     * @param model
     * @param newsId
     * @return
     */
    @RequestMapping("user/readNews/{newsId}.html")
    public String readNews(Model model, @PathVariable String newsId) {

        News news = newsService.getNewsById(newsId);

        model.addAttribute("news", news);

        return "userpage/news-read";
    }


}
