package com.flower.service.impl;

import com.flower.mapper.LogMapper;
import com.flower.mapper.SaleMapper;
import com.flower.pojo.Log;
import com.flower.pojo.Sale;
import com.flower.pojo.Staff;
import com.flower.service.SaleService;
import com.flower.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleMapper saleMapper;

    @Autowired
    private LogMapper logMapper;

    @Autowired
    private JedisClient jedisClient;

    @Override
    public Page<Sale> getSaleList(Page<Sale> p) {
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

        List<Sale> saleList = saleMapper.getSaleList(p);
        p.setList(saleList);

        Integer totalCount = saleMapper.getSaleCount(p);
        p.setTotalCount(totalCount);

        Integer totalPage = (int) Math.ceil(totalCount * 1.0 / currentCount);
        p.setTotalPage(totalPage);

        String staffName = JsonUtils.jsonToPojo(jedisClient.get("staff"), Staff.class).getStaffName();
        String time = DateUtils.newDate();
        String article = staffName + "在" + time + "查看了销售记录";
        Log log = LogUtils.newLog(time, article);
        logMapper.addLog(log);

        return p;
    }

    @Override
    public void deleteSaleById(String saleId) {
        saleMapper.deleteSaleById(saleId);
        String staffName = JsonUtils.jsonToPojo(jedisClient.get("staff"), Staff.class).getStaffName();
        String time = DateUtils.newDate();
        String article = staffName + "在" + time + "删除了ID为" + saleId + "的销售记录";
        Log log = LogUtils.newLog(time, article);
        logMapper.addLog(log);

    }

    @Override
    public List<Sale> getAllSale() {
        return saleMapper.getAllSale();
    }

    @Override
    public Integer getSaleNum() {
        return saleMapper.getSaleNum();
    }

}
