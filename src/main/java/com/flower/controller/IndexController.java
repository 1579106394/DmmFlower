package com.flower.controller;

import com.flower.mapper.AddressMapper;
import com.flower.mapper.FlowerMapper;
import com.flower.mapper.OrderMapper;
import com.flower.mapper.StaffMapper;
import com.flower.pojo.Address;
import com.flower.pojo.Flower;
import com.flower.pojo.Order;
import com.flower.pojo.Staff;
import com.flower.service.FlowerService;
import com.flower.service.HistoryService;
import com.flower.service.NewsService;
import com.flower.service.OrderService;
import com.flower.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private FlowerMapper flowerMapper;

    @Autowired
    private StaffMapper staffMapper;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private FlowerService flowerService;

    @Autowired
    private NewsService newsService;

    /**
     * 跳转到首页
     *
     * @param model
     * @return
     */
    @RequestMapping("/index.html")
    public String index(Model model, HttpSession session) {
        Staff staff = (Staff) session.getAttribute("staff");

        // 查询是否有新下单的订单、需要派送的订单、公司账务
        if (staff.getStaffRole() != 2) {

            Page p = new Page();
            p.getParams().put("flag", 3);
            Page<Order> newsPage = orderService.getOrderList(p); // 新订单
            if (newsPage.getList() != null && newsPage.getList().size() != 0) {
                model.addAttribute("orderFlag1", 1);
            }

            // 需要派送的订单(已下单，且分配了派送员)
            p.getParams().put("flag", 4);
            p.getParams().put("courier", staff);
            newsPage = orderService.getOrderList(p);
            if (newsPage.getList() != null && newsPage.getList().size() != 0) {
                model.addAttribute("OrderFlag2", 1);
            }

        }
        // 公司账务
        if (staff.getStaffRole() != 2) {

            Double adminPrice = historyService.getAdminPrice();
            model.addAttribute("adminPrice", adminPrice);

            Map<String, Object> dataMap = new HashMap<>();
            // 查询花卉数
            Integer flowerCount = flowerMapper.getFlowerCount(new Page<Flower>());
            dataMap.put("flowerCount", flowerCount);
            // 查询员工数
            Page staffPage = new Page();
            staffPage.getParams().put("staffRole", 1);
            Integer staffCount = staffMapper.getStaffCount(staffPage);
            dataMap.put("staffCount", staffCount);
            // 查询普通用户数
            staffPage.getParams().put("staffRole", 2);
            Integer userCount = staffMapper.getStaffCount(staffPage);
            dataMap.put("userCount", userCount);
            // 查询管理员数
            staffPage.getParams().put("staffRole", 3);
            Integer adminCount = staffMapper.getStaffCount(staffPage);
            dataMap.put("adminCount", adminCount);
            // 查询外送地址数
            Integer addressCount = addressMapper.getAddressCount(new Page<Address>());
            dataMap.put("addressCount", addressCount);
            // 查询订单数
            Integer orderCount = orderMapper.getOrderCount(new Page<Order>());
            dataMap.put("orderCount", orderCount);

            model.addAttribute("dataMap", dataMap);

        }
        return "index";
    }

    /**
     * 用户首页
     */
    @RequestMapping("/user/index.html")
    public String userIndex(Model model) {
        // 查询所有花卉
        Page p1 = new Page();
        p1.setCurrentCount(7);
        Page flowerPage = flowerService.getFlowerList(p1);
        model.addAttribute("flowerPage", flowerPage);

        // 查询公告列表
        Page p2 = new Page();
        p2.setCurrentCount(6);
        Page newsPage = newsService.getNewsList(p2);
        model.addAttribute("newsPage", newsPage);

        return "userpage/index";
    }

}
