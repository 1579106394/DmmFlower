package com.flower.controller;

import com.flower.pojo.Type;
import com.flower.service.TypeService;
import com.flower.utils.Page;
import com.flower.utils.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("api/type/")
public class TypeController {

    @Autowired
    private TypeService typeService;

    /**
     * 分类列表
     *
     * @param model
     * @param p
     * @return
     */
    @RequestMapping("typeList.html")
    public String typeList(Model model, Page<Type> p) {
        Page<Type> page = typeService.getTypeList(p);

        model.addAttribute("page", page);

        return "type/type-list";
    }

    /**
     * 添加分类
     *
     * @param type
     * @return
     */
    @RequestMapping("addType.html")
    public String addType(Type type) {

        typeService.addType(type);

        return "redirect:/api/type/typeList.html";
    }

    /**
     * 编辑分类
     *
     * @param type
     * @return
     */
    @RequestMapping("editType.html")
    public String editType(Type type) {

        typeService.editTyper(type);

        return "redirect:/api/type/typeList.html";
    }

    /**
     * 根据id删除分类
     *
     * @param typeId
     * @return
     */
    @RequestMapping("deleteType{typeId}.html")
    public String deleteType(@PathVariable String typeId, String[] ids) {
        try {
            if (StringUtils.isNotBlank(typeId)) {
                typeService.deleteTypeById(typeId);
            } else {
                for (String id : ids) {
                    typeService.deleteTypeById(id);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/api/type/typeList.html";
    }

    /**
     * 跳转到编辑分类
     *
     * @param typeId
     * @param model
     * @return
     */
    @RequestMapping("toEdit{typeId}.html")
    public String toEdit(@PathVariable String typeId, Model model) {
        Type type = typeService.getTypeById(typeId);
        model.addAttribute("type", type);
        return "type/type-edit";
    }

    @RequestMapping("user/typeList.action")
    @ResponseBody
    public Result userTypeList() {
        List<Type> typeList = typeService.getAllTypeList();
        return Result.ok(typeList);
    }
}
