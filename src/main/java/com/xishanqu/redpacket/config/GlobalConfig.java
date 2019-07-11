package com.xishanqu.redpacket.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.HashMap;
import java.util.Map;

/**
 * 添加全局数据
 * @Author BaoNing 2019/7/11
 */
@ControllerAdvice
public class GlobalConfig {

    @ModelAttribute(value = "info")
    public Map<String, String> userInfo(){
        Map<String, String> map = new HashMap<>();
        map.put("username", "baoning");
        map.put("password", "123456");
        return map;
    }

}
