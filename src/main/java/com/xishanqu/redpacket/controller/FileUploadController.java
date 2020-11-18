package com.xishanqu.redpacket.controller;

import com.xishanqu.redpacket.common.bean.ImageServer;
import com.xishanqu.redpacket.common.bean.MailInfo;
import com.xishanqu.redpacket.common.mail.MailService;
import com.xishanqu.redpacket.common.util.FastDFSClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@Slf4j
public class FileUploadController {

    @Autowired
    private MailService mailService;

    public final static MailInfo mailInfo = new MailInfo();

    @Autowired
    private ImageServer imageServer;


    /**
     * 文件上传
     *
     * @Param
     * @Return
     * @Author BaoNing
     * @Time 2019/07/22
     */
    @PostMapping("/fast")
    public String fastDFSUpload(@RequestParam(value = "fastFile") MultipartFile file) throws Exception {
        FastDFSClient fastDFSClient = new FastDFSClient("classpath:client.conf");
        String fileId = fastDFSClient.fastDFSUpload(file);
        return imageServer.getImageServerUrl() + ":" + imageServer.getImageServerPort() + "/" + fileId;
    }


    /**
     * 发送附件文件
     *
     * @param multipartFile
     */
    @PostMapping("/mail")
    public void sendAttachMail(@RequestParam(value = "upFile") MultipartFile multipartFile) {

        //TODO 待转化
        //发送邮件
        try {
            mailService.sendAttachFileMail(
                    mailInfo.getFrom(),
                    mailInfo.getTo(),
                    mailInfo.getCc(),
                    mailInfo.getSubject(),
                    mailInfo.getContent(),
                    multipartFile);
        } catch (Exception ex) {
            log.info("发送邮件失败>>>>>>>>>>ex={}", ex);
        }
    }


    /**
     * file upload
     *
     * @param uploadFile
     * @param request
     * @return
     */
    @PostMapping("/upload")
    public String upload(@RequestParam(value = "uploadFile", required = false) MultipartFile uploadFile, HttpServletRequest request) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String realPath = request.getSession().getServletContext().getRealPath("/uploadFile/");
        String format = sdf.format(new Date());
        File folder = new File(realPath + format);
        if (!folder.isDirectory()) {
            folder.mkdirs();
        }
        String oldName = uploadFile.getOriginalFilename();
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
        try {
            uploadFile.transferTo(new File(folder, newName));
            String filePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/uploadFile/" + format + newName;
            return filePath;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "upload error!";
    }

}
