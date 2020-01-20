package com.flower.service.impl;

import com.flower.mapper.SysMapper;
import com.flower.pojo.Sys;
import com.flower.service.SysService;
import com.flower.utils.DateUtils;
import com.flower.utils.Page;
import com.flower.utils.SqlBackupUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class SysServiceImpl implements SysService {

    @Autowired
    private SysMapper sysMapper;

    @Value("${SQL_PATH}")
    private String SQL_PATH;

    @Override
    public void backupSql(Sys sys) {
        // 补全属性
        String date = DateUtils.newDate();
        sys.setSqlId(UUID.randomUUID().toString());
        sys.setSqlCreatedTime(date);
        sys.setSqlName(date.replaceAll("年", "")
                .replaceAll("月", "")
                .replaceAll("日", "")
                .replaceAll(" ", "")
                .replaceAll(":", "") + ".sql");
        sysMapper.addSys(sys);

        // 调用数据库备份工具
        SqlBackupUtils.backup("flower", sys.getSqlName(), SQL_PATH);
    }

    @Override
    public void restoreSql(Sys sys) {
        Sys s = sysMapper.getSysById(sys.getSqlId());
        SqlBackupUtils.restore("flower", s.getSqlName(), SQL_PATH);
    }

    @Override
    public void deleteSysById(String sysId) {
        // 先获取，用来删除文件
        Sys sys = sysMapper.getSysById(sysId);
        sysMapper.deleteSysById(sysId);

        // 删除备份目录下对应的文件
        String fileName = sys.getSqlName();
        File file = new File(SQL_PATH + fileName);
        file.delete();
    }

    @Override
    public Page<Sys> getSysList(Page<Sys> p) {
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

        List<Sys> sysList = sysMapper.getSysList(p);
        p.setList(sysList);

        Integer totalCount = sysMapper.getSysCount(p);
        p.setTotalCount(totalCount);

        Integer totalPage = (int) Math.ceil(totalCount * 1.0 / currentCount);
        p.setTotalPage(totalPage);

        return p;
    }
}
