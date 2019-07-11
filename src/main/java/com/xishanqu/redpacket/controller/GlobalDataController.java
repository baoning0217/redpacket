package com.xishanqu.redpacket.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author BaoNing 2019/7/11
 */
@RestController
@RequestMapping("/admin/data")
@Slf4j
public class GlobalDataController {

    /**
     * 获取全局数据
     * @param model
     * @return
     */
    @GetMapping("/global")
    public Object getGlobalData(Model model){
        Map<String, Object> asMap = model.asMap();
        log.info("getGlobalData>>>>>>>>>>>>asMap={}", asMap);
        Object info = asMap.get("info");
        log.info("info>>>>>>>>>>>>info={}",info);
        return info;
    }

}
