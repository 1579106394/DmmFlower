package com.flower.service;

import com.flower.pojo.Sys;
import com.flower.utils.Page;

public interface SysService {

    // 备份sql
    void backupSql(Sys sys);

    // 还原sql
    void restoreSql(Sys sys);

    // 根据id删除备份的数据
    void deleteSysById(String sysId);

    // 获取备份列表
    Page<Sys> getSysList(Page<Sys> p);
}
