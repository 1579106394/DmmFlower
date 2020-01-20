package com.flower.mapper;

import com.flower.pojo.Log;
import com.flower.utils.Page;

import java.util.List;

public interface LogMapper {

    // 插入日志
    void addLog(Log log);

    // 获取日志列表
    List<Log> getLogList(Page<Log> p);

    // 获取日志数量
    Integer getLogCount(Page<Log> p);

    // 根据id删除日志
    void deleteLogById(String logId);
}
