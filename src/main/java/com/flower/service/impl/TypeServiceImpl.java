package com.flower.service.impl;

import com.flower.mapper.LogMapper;
import com.flower.mapper.TypeMapper;
import com.flower.pojo.Log;
import com.flower.pojo.Staff;
import com.flower.pojo.Type;
import com.flower.service.TypeService;
import com.flower.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Transactional
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private LogMapper logMapper;

    @Autowired
    private JedisClient jedisClient;

    @Override
    public Page<Type> getTypeList(Page<Type> p) {
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

        List<Type> typeList = typeMapper.getTypeList(p);
        p.setList(typeList);

        Integer totalCount = typeMapper.getTypeCount(p);
        p.setTotalCount(totalCount);

        Integer totalPage = (int) Math.ceil(totalCount * 1.0 / currentCount);
        p.setTotalPage(totalPage);

        String staffName = JsonUtils.jsonToPojo(jedisClient.get("staff"), Staff.class).getStaffName();
        String time = DateUtils.newDate();
        String article = staffName + "在" + time + "查看了花卉分类列表";
        Log log = LogUtils.newLog(time, article);
        logMapper.addLog(log);


        return p;
    }

    @Override
    public void addType(Type type) {
        // 补全属性
        type.setTypeId(UUID.randomUUID().toString());
        type.setTypeNum(0);
        type.setTypeFlag(1);
        typeMapper.addType(type);

        String staffName = JsonUtils.jsonToPojo(jedisClient.get("staff"), Staff.class).getStaffName();
        String time = DateUtils.newDate();
        String article = staffName + "在" + time + "新增了品种" + type.getTypeName();
        Log log = LogUtils.newLog(time, article);
        logMapper.addLog(log);

    }

    @Override
    public void deleteTypeById(String typeId) {
        Type type = typeMapper.getTypeById(typeId);

        typeMapper.deleteTypeById(typeId);

        String staffName = JsonUtils.jsonToPojo(jedisClient.get("staff"), Staff.class).getStaffName();
        String time = DateUtils.newDate();
        String article = staffName + "在" + time + "删除了分类" + type.getTypeId();
        Log log = LogUtils.newLog(time, article);
        logMapper.addLog(log);

    }

    @Override
    public void editTyper(Type type) {
        Type t = typeMapper.getTypeById(type.getTypeId());
        String staffName = JsonUtils.jsonToPojo(jedisClient.get("staff"), Staff.class).getStaffName();
        String time = DateUtils.newDate();
        String article = staffName + "在" + time + "编辑了分类" + t.getTypeName() +
                "，分类名从" + t.getTypeName() + "改成了" + type.getTypeName();
        Log log = LogUtils.newLog(time, article);
        logMapper.addLog(log);

        typeMapper.editType(type);
    }

    @Override
    public Type getTypeById(String typeId) {
        return typeMapper.getTypeById(typeId);
    }

    @Override
    public List<Type> getAllTypeList() {
        return typeMapper.getAllTypeList();
    }
}
