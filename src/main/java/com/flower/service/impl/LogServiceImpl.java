package com.flower.service.impl;

import com.flower.mapper.LogMapper;
import com.flower.pojo.Log;
import com.flower.service.LogService;
import com.flower.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    public void addLog(Log log) {
        logMapper.addLog(log);
    }

    @Override
    public Page<Log> getLogList(Page<Log> p) {
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

        List<Log> logList = logMapper.getLogList(p);
        p.setList(logList);

        Integer totalCount = logMapper.getLogCount(p);
        p.setTotalCount(totalCount);

        Integer totalPage = (int) Math.ceil(totalCount * 1.0 / currentCount);
        p.setTotalPage(totalPage);

        return p;
    }

    @Override
    public void deleteLogById(String logId) {
        logMapper.deleteLogById(logId);
    }
}
