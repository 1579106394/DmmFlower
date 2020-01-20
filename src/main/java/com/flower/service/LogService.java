package com.flower.service;

import com.flower.pojo.Log;
import com.flower.utils.Page;

public interface LogService {

    // 添加日志
    void addLog(Log log);

    // 获取日志列表
    Page<Log> getLogList(Page<Log> p);

    // 根据id删除日志
    void deleteLogById(String logId);
}
