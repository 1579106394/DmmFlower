package com.flower.controller;

import com.flower.pojo.Staff;
import com.flower.pojo.Sys;
import com.flower.service.SysService;
import com.flower.utils.Page;
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

@Controller
@RequestMapping("api/sys/")
public class SysController {

    @Autowired
    private SysService sysService;

    /**
     * 备份列表
     *
     * @param model
     * @param p
     * @return
     */
    @RequestMapping("sqlList.html")
    public String sqlList(Model model, Page<Sys> p) {

        Page<Sys> page = sysService.getSysList(p);

        model.addAttribute("page", page);

        return "sys/sys-list";
    }

    /**
     * ajax备份数据库
     * @param session
     * @return
     */
    @RequestMapping("backupSql.action")
    @ResponseBody
    public Result backup(HttpSession session) {
        try {
            Staff staff = (Staff) session.getAttribute("staff");
            Sys sys = new Sys();
            sys.setStaff(staff);
            sysService.backupSql(sys);
            return Result.ok("数据备份成功！");
        }catch (Exception e) {
            e.printStackTrace();
            return Result.build(400, "数据备份失败！");
        }
    }

    /**
     * ajax恢复数据库
     * @param sys
     * @return
     */
    @RequestMapping("restoreSql.action")
    @ResponseBody
    public Result restoreSql(@RequestBody Sys sys) {
        try {
            sysService.restoreSql(sys);
            return Result.ok("数据恢复成功！");
        }catch (Exception e) {
            e.printStackTrace();
            return Result.build(400, "数据恢复失败！");
        }
    }

    /**
     * 根据id删除备份记录
     * @param sysId
     * @param ids
     * @return
     */
    @RequestMapping("deleteSql{sysId}.html")
    public String deleteSys(@PathVariable String sysId, String[] ids) {
        try {
            if (StringUtils.isNotBlank(sysId)) {
                sysService.deleteSysById(sysId);
            } else {
                for (String id : ids) {
                    sysService.deleteSysById(id);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/api/sys/sqlList.html";
    }

}
