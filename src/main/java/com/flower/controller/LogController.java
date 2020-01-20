package com.flower.controller;

import com.flower.pojo.Log;
import com.flower.service.LogService;
import com.flower.utils.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/log/")
public class LogController {

    @Autowired
    private LogService logService;

    /**
     * 日志列表
     * @param model
     * @param p
     * @return
     */
    @RequestMapping("logList.html")
    public String flowerList(Model model, Page<Log> p) {

        Page<Log> page = logService.getLogList(p);

        model.addAttribute("page", page);

        return "log/log-list";
    }


    /**
     * 根据id删除日志
     *
     * @param logId
     * @return
     */
    @RequestMapping("deleteLog{logId}.html")
    public String deleteFlower(@PathVariable String logId, String[] ids) {

        if (StringUtils.isNotBlank(logId)) {
            logService.deleteLogById(logId);
        } else {
            for (String id : ids) {
                logService.deleteLogById(id);
            }
        }


        return "redirect:/api/log/logList.html";
    }

}
