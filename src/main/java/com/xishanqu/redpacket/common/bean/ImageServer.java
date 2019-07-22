package com.xishanqu.redpacket.common.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author BaoNing 2019/7/22
 */
@Data
@Component
public class ImageServer {

    @Value("${image.server.url}")
    public String imageServerUrl;

    @Value("${image.server.port}")
    public String imageServerPort;

}
