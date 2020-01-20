package com.flower.service;

import com.flower.pojo.News;
import com.flower.utils.Page;

public interface NewsService {

    // 公告列表
    Page<News> getNewsList(Page<News> p);

    // 添加公告
    void addNews(News news);

    // 编辑公告
    void editNews(News news);

    // 根据id删除公告
    void deleteNewsById(String newsId);

    // 根据id获取公告
    News getNewsById(String newsId);
}
