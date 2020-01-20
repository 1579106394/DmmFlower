package com.flower.service;

import com.flower.pojo.Type;
import com.flower.utils.Page;

import java.util.List;

public interface TypeService {

    // 获取分类列表
    Page<Type> getTypeList(Page<Type> p);

    // 添加分类
    void addType(Type type);

    // 根据id删除分类
    void deleteTypeById(String typeId);

    // 编辑分类
    void editTyper(Type type);

    // 根据id获取分类
    Type getTypeById(String typeId);

    // 获取所有分类
    List<Type> getAllTypeList();
}
