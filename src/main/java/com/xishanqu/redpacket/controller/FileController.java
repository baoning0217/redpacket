package com.xishanqu.redpacket.controller;

import com.xishanqu.redpacket.common.util.MD5Util;
import com.xishanqu.redpacket.pojo.FileImage;
import com.xishanqu.redpacket.service.FileService;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * @Author BaoNing
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/admin")
public class FileController {

    @Autowired
    private FileService fileService;

    @Value("${server.address}")
    private String serverAddress;

    @Value("${server.port}")
    private String serverPort;


    @GetMapping(value = "/")
    public String index(Model model) {
        //展示20条最新数据
        model.addAttribute("files", fileService.listFilesByPage(0, 20));
        return "index";
    }

    /**
     * 分页查询文件
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("files/{pageIndex}/{pageSize}")
    public List<FileImage> listFilesByPage(@PathVariable int pageIndex, @PathVariable int pageSize) {
        return fileService.listFilesByPage(pageIndex, pageSize);
    }

    /**
     * 获取文件片信息
     *
     * @param id
     * @return
     */
    @GetMapping("files/{id}")
    public ResponseEntity<Object> serveFile(@PathVariable String id) {
        FileImage file = fileService.getFileById(id);
        if (file != null) {
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;fileName=\"" + file.getName() + "\"")
                    .header(HttpHeaders.CONTENT_TYPE, "application/octet-stream")
                    .header(HttpHeaders.CONTENT_LENGTH, file.getSize() + "")
                    .header("Connection", "close")
                    .body(file.getContent().getData());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("没有文件！");
        }

    }

    /**
     * 在线显示文件
     *
     * @param id
     * @return
     */
    @GetMapping("/view/{id}")
    public ResponseEntity<Object> serveFileOnline(@PathVariable String id) {

        FileImage file = fileService.getFileById(id);
        if (file != null) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "fileName=\"" + file.getName() + "\"")
                    .header(HttpHeaders.CONTENT_TYPE, file.getContentType())
                    .header(HttpHeaders.CONTENT_LENGTH, file.getSize() + "")
                    .header("Connection", "close")
                    .body(file.getContent().getData());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("没有文件!");
        }
    }

    /**
     * 上传
     *
     * @param file
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        try {
            FileImage f = new FileImage(file.getOriginalFilename(), file.getContentType(), file.getSize(), new Binary(file.getBytes()));
            f.setMd5(MD5Util.getMD5(file.getInputStream()));
            fileService.saveFile(f);
        } catch (IOException | NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "上传失败！ " + file.getOriginalFilename());
            return "redirect:/admin/";
        }
        redirectAttributes.addFlashAttribute("message", "上传成功！ " + file.getOriginalFilename());
        return "redirect:/admin/";
    }


//	/**
//	 * 上传接口
//	 * @param file
//	 * @return
//	 */
//	@PostMapping("/upload")
//	public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file){
//		FileImage returnFile = null;
//		try {
//			FileImage f = new FileImage(file.getOriginalFilename(),file.getContentType(),file.getSize(),new Binary(file.getBytes()));
//			f.setMd5(MD5Util.getMD5(file.getInputStream()));
//			returnFile = fileService.saveFile(f);
//			String path = "//" + serverAddress + ":" + serverPort + "/view/" + returnFile.getId();
//			System.out.println(path);
//			return ResponseEntity.status(HttpStatus.OK).body(path);
//		}catch(IOException | NoSuchAlgorithmException ex) {
//			ex.printStackTrace();
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
//		}
//	}


    /**
     * 删除文件
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFile(@PathVariable String id) {

        try {
            fileService.removeFile(id);
            return ResponseEntity.status(HttpStatus.OK).body("删除成功!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("删除失败!" + e.getMessage());
        }

    }


}
