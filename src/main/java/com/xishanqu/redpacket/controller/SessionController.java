package com.xishanqu.redpacket.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @Function:
 * @Author: BaoNing
 * @Date: 2019-07-11
 */
@RestController
@RequestMapping("/admin/session")
public class SessionController {

    @Value("${server.port}")
    private String port;

    /**
     * 保存session
     * @param name
     * @param httpSession
     * @return
     */
    @PostMapping("/save")
    public String saveSession(@RequestParam("name") String name, HttpSession httpSession){
        httpSession.setAttribute("name", name);
        return port;
    }


    /**
     * 获取session
     * @param httpSession
     * @return
     */
    @GetMapping("/get")
    public String getSession(HttpSession httpSession){
        String name = httpSession.getAttribute("name").toString();
        return port + ":" + name;
    }


}
