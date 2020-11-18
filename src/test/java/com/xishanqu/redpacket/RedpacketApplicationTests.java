package com.xishanqu.redpacket;

import com.xishanqu.redpacket.service.UserRedPacketService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedpacketApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private UserRedPacketService userRedPacketService;


    @Test
    public void grabRedPacket() {

        for (long i = 1; i < 20001; i++) {
            int result = userRedPacketService.grabRedPacket(3L, i);
            System.out.println("抢红包:" + result + "--" + i);
        }
    }


}
