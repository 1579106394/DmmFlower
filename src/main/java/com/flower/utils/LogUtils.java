package com.flower.utils;

import com.flower.pojo.Log;

import java.util.UUID;

public class LogUtils {


    public static Log newLog(String createdTime, String article) {
        Log log = new Log();
        log.setLogId(UUID.randomUUID().toString());
        log.setLogCreatedTime(createdTime);
        log.setLogArticle(article);
        log.setLogFlag(1);

        return log;
    }


}
