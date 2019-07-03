package com.xishanqu.redpacket.controller;

import com.xishanqu.redpacket.pojo.RedPacket;
import com.xishanqu.redpacket.service.RedPacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author BaoNing 2019/7/2
 */
@RestController
@RequestMapping("/admin/redPacket")
public class RedPacketController {

    @Autowired
    private RedPacketService redPacketService;

    /**
     * 插入红包
     * @param redPacket
     * @return
     */
    @RequestMapping("/create")
    public int insertRedPacket(@RequestBody RedPacket redPacket){

        redPacket.setNote("2019-07-03");

        //总金额
        Double amount = redPacket.getAmount();

        //总数
        Integer total = redPacket.getTotal();

        redPacket.setStock(total);

        Double unitAmount =  amount/total;
        redPacket.setUnitAmount(unitAmount);

        int result = redPacketService.insertRedPacket(redPacket);
        return result;
    }



}
