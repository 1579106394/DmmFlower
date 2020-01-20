package com.flower.controller;

import com.flower.pojo.Address;
import com.flower.pojo.Cart;
import com.flower.pojo.Flower;
import com.flower.pojo.Staff;
import com.flower.service.AddressService;
import com.flower.service.CartService;
import com.flower.service.FlowerService;
import com.flower.utils.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("api/cart/")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private FlowerService flowerService;

    @Autowired
    private AddressService addressService;

    /**
     * ajax加入购物车
     * @param flower
     * @param session
     * @return
     */
    @RequestMapping("addCart.action")
    @ResponseBody
    public Result addCart(@RequestBody Flower flower, HttpSession session) {

        Staff staff = (Staff) session.getAttribute("staff");
        flower.setStaff(staff);

        Flower flower1 = flowerService.getFlowerFromCartByFlowerId(flower);
        if(flower1 == null) {
            cartService.addCart(flower);
            return Result.ok();
        }else {

            return Result.build(400, "购物车已存在该商品，请勿重复添加");
        }

    }

    /**
     * 获取购物车中的花卉
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("cartList.html")
    public String cartList(Model model, HttpSession session) {

        Staff staff = (Staff) session.getAttribute("staff");

        Cart cart = cartService.getCartList(staff);

        model.addAttribute("cart", cart);

        return "cart/cart-list";
    }

    /**
     * 根据id从购物车中移出花卉
     * @param flowerId
     * @param cartId
     * @param ids
     * @return
     */
    @RequestMapping("deleteFromCart{cartId}/{flowerId}.html")
    public String deleteFromCart(@PathVariable String flowerId, @PathVariable String cartId, String[] ids) {
        try {
            if(StringUtils.isNotBlank(flowerId)) {
                cartService.deleteFromCart(flowerId, cartId);
            }else {
                for (String id : ids) {
                    cartService.deleteFromCart(id, cartId);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/api/cart/cartList.html";
    }

    /**
     * 跳转到提交订单页面
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("toAddOrder.html")
    public String toAddOrder(Model model, HttpSession session) {

        Staff staff = (Staff) session.getAttribute("staff");
        Cart cart = cartService.getCartList(staff);
        model.addAttribute("cart", cart);

        List<Address> addressList = addressService.getAllAddressList();
        model.addAttribute("addressList", addressList);

        return "cart/flower-list";
    }

}
