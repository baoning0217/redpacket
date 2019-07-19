package com.xishanqu.redpacket.controller;

import com.xishanqu.redpacket.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author BaoNing Created On 2019年1月22日
*/
@RestController
@RequestMapping("/admin/image")
public class ImageController {

    @Autowired
    private ImageService imageService;


    /**
     * 图片上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public int uploadImage(@RequestParam("image") MultipartFile file){
        return imageService.insertImage(file);
    }

}
