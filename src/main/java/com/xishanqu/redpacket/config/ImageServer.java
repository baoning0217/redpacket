package com.xishanqu.redpacket.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author BaoNing 2019/7/22
 */
@Component
@Data
public class ImageServer {

    @Value(value = "${image.server.url}")
    private String imageServerUrl;

    @Value(value = "${image.server.port}")
    private String imageServerPort;
}
