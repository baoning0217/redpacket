package com.xishanqu.redpacket.controller;

import com.xishanqu.redpacket.service.UserRedPacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author BaoNing 2019/7/2
 */
@RestController
@RequestMapping("/admin/userRedPacket")
public class UserRedPacketController {

    @Autowired
    private UserRedPacketService userRedPacketService;

    /**
     * 抢购
     * @param redPacketId
     * @param userId
     * @return
     */
    @PostMapping("/grab")
    public int grabRedPacket(Long redPacketId, Long userId){
        int result = userRedPacketService.grabRedPacket(redPacketId, userId);
        System.err.println("返回值:" + result +",红包id:" + redPacketId + ",抢购用户id:"+ userId);
        return result;
    }


}
