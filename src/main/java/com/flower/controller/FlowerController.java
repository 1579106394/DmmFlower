package com.flower.controller;

import com.flower.pojo.Comment;
import com.flower.pojo.Flower;
import com.flower.pojo.Staff;
import com.flower.pojo.Type;
import com.flower.service.CommentService;
import com.flower.service.FlowerService;
import com.flower.service.TypeService;
import com.flower.utils.Page;
import com.flower.utils.Result;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("api/flower/")
public class FlowerController {

    @Autowired
    private FlowerService flowerService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private CommentService commentService;

    @Value("${FLOWER_PATH}")
    public String FLOWER_PATH;

    /**
     * 花卉列表
     *
     * @param model
     * @param p
     * @return
     */
    @RequestMapping("flowerList.html")
    public String flowerList(Model model, Page<Flower> p) {

        Page<Flower> page = flowerService.getFlowerList(p);

        model.addAttribute("page", page);

        return "flower/flower-list";
    }

    /**
     * 添加花卉(进货)
     *
     * @param flower
     * @return
     */
    @RequestMapping("addFlower.html")
    public String addFlower(Flower flower, MultipartFile photo) {

        // 保存图片到
        String name = UUID.randomUUID().toString().replaceAll("-", "");
        // 获得照片后缀名
        String ext = FilenameUtils.getExtension(photo.getOriginalFilename());
        String fileName = name + "." + ext;
        try {
            photo.transferTo(new File(FLOWER_PATH + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        flower.setFlowerImg("photo\\" + fileName);
        flowerService.addFlower(flower);

        return "redirect:/api/flower/flowerList.html";
    }

    /**
     * 跳转到添加花卉
     */
    @RequestMapping("toAdd.html")
    public String toAdd(Model model) {

        List<Type> typeList = typeService.getAllTypeList();
        model.addAttribute("typeList", typeList);

        return "flower/flower-add";
    }

    /**
     * 编辑花卉
     *
     * @param flower
     * @return
     */
    @RequestMapping("editFlower.html")
    public String editFlower(Flower flower) {

        flowerService.editFlower(flower);

        return "redirect:/api/flower/flowerList.html";
    }

    /**
     * 根据id删除花卉
     *
     * @param flowerId
     * @return
     */
    @RequestMapping("deleteFlower{flowerId}.html")
    public String deleteFlower(@PathVariable String flowerId, String[] ids) {
        try {
            if (StringUtils.isNotBlank(flowerId)) {
                flowerService.deleteFlowerById(flowerId);
            } else {
                for (String id : ids) {
                    flowerService.deleteFlowerById(id);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return "redirect:/api/flower/flowerList.html";
    }

    /**
     * 跳转到编辑花卉
     *
     * @param flowerId
     * @param model
     * @return
     */
    @RequestMapping("toEdit{flowerId}.html")
    public String toEdit(@PathVariable String flowerId, Model model) {
        Flower flower = flowerService.getFlowerById(flowerId);
        model.addAttribute("flower", flower);

        List<Type> typeList = typeService.getAllTypeList();
        model.addAttribute("typeList", typeList);
        return "flower/flower-edit";
    }

    /**
     * 跳转到进货
     *
     * @param flowerId
     * @param model
     * @return
     */
    @RequestMapping("toAddNum{flowerId}.html")
    public String toAddNum(@PathVariable String flowerId, Model model) {
        Flower flower = flowerService.getFlowerById(flowerId);
        model.addAttribute("flower", flower);
        return "flower/flower-add-num";
    }

    /**
     * ajax获取花卉的数量，判断是否该进货
     * @param session
     * @return
     */
    @RequestMapping("getFlowerNum.action")
    @ResponseBody
    public Result getFlowerNum(HttpSession session) {
        Staff staff = (Staff) session.getAttribute("staff");

        List<Flower> flowerList = flowerService.getAllFlowerById();
        String msg = "";
        if (staff.getStaffRole() != 2) {

            for (Flower flower : flowerList) {
                Integer flowerNum = flower.getFlowerNum();
                if (flowerNum < 30) {
                    msg += flower.getFlowerName() + "的库存量少于30！\r\n";
                }
            }
            if (StringUtils.isNotBlank(msg)) {
                msg += "请尽快进货！";
            }

        }
        return Result.build(200, msg);
    }


    /**
     * 用户查看花卉信息
     * @param flowerId
     * @param model
     * @return
     */
    @RequestMapping("user/readFlower/{flowerId}.html")
    public String readFlowerById(@PathVariable String flowerId, Model model) {

        Flower flower = flowerService.getFlowerById(flowerId);

        model.addAttribute("flower", flower);

        // 查询这个花卉的评论
        List<Comment> commentList = commentService.getCommentListByFlowerId(flowerId);
        model.addAttribute("commentList", commentList);


        return "userpage/item-info";
    }

    /**
     * 用户界面分页查询花卉列表
     * @param typeId
     * @param p
     * @param model
     * @return
     */
    @RequestMapping("user/flowerList{typeId}.html")
    public String flowerList(@PathVariable  String typeId, Page p, Model model) {

        if(StringUtils.isNotBlank(typeId)) {
            p.getParams().put("typeId", typeId);
        }
        p.setCurrentCount(10);
        Page page = flowerService.getFlowerList(p);
        model.addAttribute("page", page);

        return "userpage/item-list";
    }

}
