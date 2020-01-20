package com.flower.service;

import com.flower.pojo.Sale;
import com.flower.utils.Page;

public interface SaleService {

    // 查看销售记录列表
    Page<Sale> getSaleList(Page<Sale> p);

    // 根据id删除销售记录
    void deleteSaleById(String saleId);

    java.util.List<Sale> getAllSale();

    Integer getSaleNum();
}
