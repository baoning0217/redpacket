package com.xishanqu.redpacket.controller;

import com.xishanqu.redpacket.bean.MessageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Function:
 * @Author: BaoNing
 * @Date: 2019-07-13
 */
@RestController
@RequestMapping("/admin/greeting")
public class GreetingController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/hello")
    public void greeting(MessageInfo messageInfo) throws Exception{
        messagingTemplate.convertAndSend("/topic/greetings", messageInfo);
    }



}
