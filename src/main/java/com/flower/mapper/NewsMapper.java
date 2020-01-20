package com.flower.mapper;

import com.flower.pojo.News;
import com.flower.utils.Page;

import java.util.List;

public interface NewsMapper {

    // 获取公告列表
    List<News> getNewsList(Page<News> p);

    // 获取公告数量
    Integer getNewsCount(Page<News> p);

    // 发表公告
    void addNews(News news);

    // 根据id查询公告
    News getNewsById(String newsId);

    // 根据id删除公告
    void deleteNewsById(String newsId);

    // 编辑公告
    void editNews(News news);
}
