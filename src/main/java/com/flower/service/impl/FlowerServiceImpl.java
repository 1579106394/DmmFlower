package com.flower.service.impl;

import com.flower.mapper.FlowerMapper;
import com.flower.mapper.HistoryMapper;
import com.flower.mapper.LogMapper;
import com.flower.mapper.TypeMapper;
import com.flower.pojo.*;
import com.flower.service.FlowerService;
import com.flower.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class FlowerServiceImpl implements FlowerService {

    @Autowired
    private FlowerMapper flowerMapper;

    @Autowired
    private HistoryMapper historyMapper;

    @Autowired
    private LogMapper logMapper;

    @Autowired
    private JedisClient jedisClient;

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public void addFlower(Flower flower) {
        // 补全属性
        flower.setFlowerId(UUID.randomUUID().toString());
        flower.setFlowerFlag(1);
        flower.setFlowerCreatedTime(DateUtils.newDate());
        flowerMapper.addFlower(flower);

        // 该分类数量增加
        Type type = typeMapper.getTypeById(flower.getType().getTypeId());
        type.setTypeNum(type.getTypeNum() + flower.getFlowerNum());
        typeMapper.editType(type);

        // 生成财务记录
        // 默认进货价格是售价的80%
        Double totalPrice = flower.getFlowerPrice() * flower.getFlowerNum() * 0.8;
        historyMapper.pay(totalPrice);

        String staffName = JsonUtils.jsonToPojo(jedisClient.get("staff"), Staff.class).getStaffName();
        String time = DateUtils.newDate();
        String article = staffName + "在" + time + "购入了了花卉" + flower.getFlowerName() + " " + flower.getFlowerNum() + "kg";
        Log log = LogUtils.newLog(time, article);
        logMapper.addLog(log);

        History history = HistoryUtils.newHistory(totalPrice, "购入" + flower.getFlowerNum() + "kg" + flower.getFlowerName(), 1);
        historyMapper.addHistory(history);


    }

    @Override
    public Page<Flower> getFlowerList(Page<Flower> p) {
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

        List<Flower> flowerList = flowerMapper.getFlowerList(p);
        p.setList(flowerList);

        Integer totalCount = flowerMapper.getFlowerCount(p);
        p.setTotalCount(totalCount);

        Integer totalPage = (int) Math.ceil(totalCount * 1.0 / currentCount);
        p.setTotalPage(totalPage);

        String staffName = JsonUtils.jsonToPojo(jedisClient.get("staff"), Staff.class).getStaffName();
        String time = DateUtils.newDate();
        String article = staffName + "在" + time + "查看了花卉列表";
        Log log = LogUtils.newLog(time, article);
        logMapper.addLog(log);

        return p;
    }

    @Override
    public void deleteFlowerById(String flowerId) {
        Flower flower = flowerMapper.getFlowerById(flowerId);
        flowerMapper.deleteFlowerById(flowerId);

        String staffName = JsonUtils.jsonToPojo(jedisClient.get("staff"), Staff.class).getStaffName();
        String time = DateUtils.newDate();
        String article = staffName + "在" + time + "删除了花卉" + flower.getFlowerName();
        Log log = LogUtils.newLog(time, article);
        logMapper.addLog(log);

    }

    @Override
    public Flower getFlowerById(String flowerId) {
        return flowerMapper.getFlowerById(flowerId);
    }

    @Override
    public void editFlower(Flower flower) {
        // 修改属性
        Flower f = flowerMapper.getFlowerById(flower.getFlowerId());
        flower.setFlowerCreatedTime(DateUtils.newDate());

        //如果数量不为空，就增加这个数量
        if (flower.getFlowerNum() != null) {
            Double totalPrice = f.getFlowerPrice() * flower.getFlowerNum() * 0.8;
            historyMapper.pay(totalPrice);
            History history = HistoryUtils.newHistory(totalPrice, "购入" + flower.getFlowerNum() + "kg" + flower.getFlowerName(), 1);
            historyMapper.addHistory(history);


            String staffName = JsonUtils.jsonToPojo(jedisClient.get("staff"), Staff.class).getStaffName();
            String time = DateUtils.newDate();
            String article = staffName + "在" + time + "购入了花卉" + flower.getFlowerName() + flower.getFlowerNum() + "kg";
            Log log = LogUtils.newLog(time, article);
            logMapper.addLog(log);
            flower.setFlowerNum(f.getFlowerNum() + flower.getFlowerNum());

        } else {
            String staffName = JsonUtils.jsonToPojo(jedisClient.get("staff"), Staff.class).getStaffName();
            String time = DateUtils.newDate();
            String article = staffName + "在" + time + "编辑了花卉" + f.getFlowerName() +
                    "，花卉名从" + f.getFlowerName() + "改成了" + flower.getFlowerName() +
                    "，单价从" + f.getFlowerPrice() + "改成了" + flower.getFlowerPrice();
            Log log = LogUtils.newLog(time, article);
            logMapper.addLog(log);
        }

        flowerMapper.editFlower(flower);
    }

    @Override
    public Flower getFlowerFromCartByFlowerId(Flower flower) {
        return flowerMapper.getFlowerFromCartByFlowerId(flower);
    }

    @Override
    public List<Flower> getAllFlowerById() {
        return flowerMapper.getAllFlowerById();
    }

}
