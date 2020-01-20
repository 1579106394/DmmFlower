package com.flower.service.impl;

import com.flower.mapper.LogMapper;
import com.flower.mapper.NewsMapper;
import com.flower.pojo.*;
import com.flower.service.NewsService;
import com.flower.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsMapper newsMapper;

    @Autowired
    private JedisClient jedisClient;

    @Autowired
    private LogMapper logMapper;

    @Override
    public Page<News> getNewsList(Page<News> p) {
        if (p.getCurrentPage() == null) {
            p.setCurrentPage(1);
        }

        if (p.getCurrentCount() == null) {
            p.setCurrentCount(10);
        }

        Integer currentPage = p.getCurrentPage();
        Integer currentCount = p.getCurrentCount();

        Integer index = (currentPage - 1) * currentCount;
        p.setIndex(index);

        List<News> newsList = newsMapper.getNewsList(p);
        p.setList(newsList);

        Integer totalCount = newsMapper.getNewsCount(p);
        p.setTotalCount(totalCount);

        Integer totalPage = (int) Math.ceil(totalCount * 1.0 / currentCount);
        p.setTotalPage(totalPage);

        String staffName = JsonUtils.jsonToPojo(jedisClient.get("staff"), Staff.class).getStaffName();
        String time = DateUtils.newDate();
        String article = staffName + "在" + time + "查看了公告列表";
        Log log = LogUtils.newLog(time, article);
        logMapper.addLog(log);


        return p;
    }

    @Override
    public void addNews(News news) {
        // 补全属性
        news.setNewsId(UUID.randomUUID().toString());
        news.setNewsCreatedTime(DateUtils.newDate());
        news.setNewsReadNum(0);
        news.setNewsFlag(1);
        newsMapper.addNews(news);

        String staffName = JsonUtils.jsonToPojo(jedisClient.get("staff"), Staff.class).getStaffName();
        String time = DateUtils.newDate();
        String article = staffName + "在" + time + "发表了公告：" + news.getNewsTitle();
        Log log = LogUtils.newLog(time, article);
        logMapper.addLog(log);
    }

    @Override
    public void editNews(News news) {
        // 修改属性
        News n = newsMapper.getNewsById(news.getNewsId());
        news.setNewsCreatedTime(DateUtils.newDate());

        String staffName = JsonUtils.jsonToPojo(jedisClient.get("staff"), Staff.class).getStaffName();
        String time = DateUtils.newDate();
        String article = staffName + "在" + time + "编辑了公告" + n.getNewsTitle() +
                "，标题从" + n.getNewsTitle() + "改成了" + news.getNewsTitle();
        Log log = LogUtils.newLog(time, article);
        logMapper.addLog(log);

        newsMapper.editNews(news);
    }

    @Override
    public void deleteNewsById(String newsId) {
        News news = newsMapper.getNewsById(newsId);
        newsMapper.deleteNewsById(newsId);

        String staffName = JsonUtils.jsonToPojo(jedisClient.get("staff"), Staff.class).getStaffName();
        String time = DateUtils.newDate();
        String article = staffName + "在" + time + "删除了公告" + news.getNewsTitle();
        Log log = LogUtils.newLog(time, article);
        logMapper.addLog(log);
    }

    @Override
    public News getNewsById(String newsId) {
        return newsMapper.getNewsById(newsId);
    }
}
