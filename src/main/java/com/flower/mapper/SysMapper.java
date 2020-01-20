package com.flower.mapper;

import com.flower.pojo.Sys;
import com.flower.utils.Page;

import java.util.List;

public interface SysMapper {

    // 将备份记录插入到表中
    void addSys(Sys sys);

    // 根据id查询数据信息
    Sys getSysById(String sqlId);

    // 根据id删除备份的数据
    void deleteSysById(String sysId);

    // 获取备份列表
    List<Sys> getSysList(Page<Sys> p);

    // 获取备份数量
    Integer getSysCount(Page<Sys> p);
}
