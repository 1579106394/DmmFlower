package com.flower.mapper;

import com.flower.pojo.Type;
import com.flower.utils.Page;

import java.util.List;

public interface TypeMapper {

    // 分页查询分类列表
    List<Type> getTypeList(Page<Type> p);

    // 查询分类总数
    Integer getTypeCount(Page<Type> p);

    // 根据id获取分类
    Type getTypeById(String typeId);

    // 根据id删除分类
    void deleteTypeById(String typeId);

    // 创建分类
    void addType(Type type);

    // 编辑分类
    void editType(Type type);

    // 获取所有分类
    List<Type> getAllTypeList();

    // 根据花卉的id获取对应的品种id
    Type getTypeByFlowerId(String flowerId);
}
