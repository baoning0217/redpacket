package com.xishanqu.redpacket.controller;

import com.xishanqu.redpacket.pojo.RedPacket;
import com.xishanqu.redpacket.service.RedPacketService;
import com.xishanqu.redpacket.service.RedissonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * @Author BaoNing 2019/7/2
 */
@RestController
@RequestMapping("/admin/redPacket")
@Api(tags = "红包数据接口")
public class RedPacketController {

    @Autowired
    private RedPacketService redPacketService;
    @Autowired
    private RedissonService redissonService;


    /**
     * Redisson获取
     *
     * @Param
     * @Return
     * @Author BaoNing
     * @Time
     */
    @GetMapping("/getForRedisson")
    public RedPacket getRedPacketForRedisson(@RequestParam(value = "id") Long id) {
        return redissonService.reduceRedPacket(id);
    }


    /**
     * 获取指定红包
     *
     * @param id
     * @return # paramType
     * · header --> 请求参数的获取：@RequestHeader
     * · query --> 请求参数的获取：@RequestParam
     * · path（用于restful接口）--> 请求参数的获取：@PathVariable
     * · body（不常用）
     * · form（不常用）
     */
    @ApiOperation(value = "查询红包", notes = "根据id查询")
    @ApiImplicitParam(paramType = "query", name = "id", value = "红包id", required = true)
    @GetMapping("/get")
    public RedPacket getRedPacket(@RequestParam(value = "id") Long id) {
        return redPacketService.getRedPacket(id);
    }


    /**
     * 插入红包
     *
     * @param redPacket
     * @return
     */
    @ApiOperation(value = "新增红包", notes = "新增一个红包")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户id", required = true),
            @ApiImplicitParam(paramType = "query", name = "amount", value = "红包总额", required = true),
            @ApiImplicitParam(paramType = "query", name = "total", value = "红包总数", required = true)
    })
    @PostMapping("/create")
    public int insertRedPacket(@RequestBody @Validated RedPacket redPacket) {

        LocalDate localDate = LocalDate.now();
        redPacket.setNote(localDate.toString());
        redPacket.setVersion(0);
        //总金额
        Double amount = redPacket.getAmount();
        //总数
        Integer total = redPacket.getTotal();
        redPacket.setStock(total);
        Double unitAmount = amount / total;
        redPacket.setUnitAmount(unitAmount);
        int result = redPacketService.addRedPacket(redPacket);
        return result;
    }


}
