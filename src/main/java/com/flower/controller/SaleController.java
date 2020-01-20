package com.flower.controller;

import com.flower.pojo.Sale;
import com.flower.service.SaleService;
import com.flower.utils.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("api/sale/")
public class SaleController {

    @Autowired
    private SaleService saleService;

    /**
     * 获取销售记录列表
     * @param model
     * @param p
     * @return
     */
    @RequestMapping("saleList.html")
    public String saleList(Model model, Page<Sale> p) {
        Page<Sale> page = saleService.getSaleList(p);

        model.addAttribute("page", page);
        Integer saleCount = saleService.getSaleNum();
        model.addAttribute("saleCount",saleCount);

        return "sale/sale-list";
    }

    /**
     * 根据id删除销售记录
     * @param saleId
     * @return
     */
    @RequestMapping("deleteSale{saleId}.html")
    public String deleteFlower(@PathVariable String saleId, String[] ids) {

        if(StringUtils.isNotBlank(saleId)) {
            saleService.deleteSaleById(saleId);
        }else {
            for (String id : ids) {
                saleService.deleteSaleById(id);
            }
        }


        return "redirect:/api/sale/saleList.html";
    }

    /**
     * 获取花卉总销量
     * @param model
     * @return
     */
    @RequestMapping("allSale.html")
    public String allSale(Model model) {
        List<Sale> saleList = saleService.getAllSale();
        model.addAttribute("saleList", saleList);

        return "sale/all-sale";
    }

}
