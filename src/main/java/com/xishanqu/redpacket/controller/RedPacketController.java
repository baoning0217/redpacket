package com.xishanqu.redpacket.controller;

import com.xishanqu.redpacket.pojo.RedPacket;
import com.xishanqu.redpacket.service.RedPacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * @Author BaoNing 2019/7/2
 */
@RestController
@RequestMapping("/admin/redPacket")
public class RedPacketController {

    @Autowired
    private RedPacketService redPacketService;


    /**
     * 获取指定红包
     * @param redPacket
     * @return
     */
    @PostMapping("/get")
    public RedPacket getRedPacket(@RequestBody RedPacket redPacket){
        return redPacketService.getRedPacket(redPacket.getId());
    }


    /**
     * 插入红包
     * @param redPacket
     * @return
     */
    @RequestMapping("/create")
    public int insertRedPacket(@RequestBody RedPacket redPacket){

        LocalDate localDate = LocalDate.now();
        redPacket.setNote(localDate.toString());
        redPacket.setVersion(0);
        //总金额
        Double amount = redPacket.getAmount();
        //总数
        Integer total = redPacket.getTotal();
        redPacket.setStock(total);
        Double unitAmount =  amount/total;
        redPacket.setUnitAmount(unitAmount);
        int result = redPacketService.addRedPacket(redPacket);
        return result;
    }



}
