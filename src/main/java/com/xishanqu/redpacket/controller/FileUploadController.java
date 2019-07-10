package com.xishanqu.redpacket.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @Author BaoNing 2019/7/10
 */
@RestController
@RequestMapping("/admin/file")
public class FileUploadController {

    /**
     * file upload
     * @param uploadFile
     * @param request
     * @return
     */
    @PostMapping("/upload")
    public String upload(@RequestParam(value="uploadFile",required=false)MultipartFile uploadFile, HttpServletRequest request){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String realPath = request.getSession().getServletContext().getRealPath("/uploadFile/");
        String format = sdf.format(new Date());
        File folder = new File(realPath + format);
        if (!folder.isDirectory()){
            folder.mkdirs();
        }
        String oldName = uploadFile.getOriginalFilename();
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
        try {
            uploadFile.transferTo(new File(folder, newName));
            String filePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/uploadFile/" + format + newName;
            return filePath;
        }catch (Exception e){
            e.printStackTrace();
        }
        return "upload error!";
    }

}
