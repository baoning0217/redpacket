package com.xishanqu.redpacket.service;

import com.xishanqu.redpacket.dao.FileDao;
import com.xishanqu.redpacket.pojo.FileImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author cuicui
 */
@Service
public class FileService {

    @Autowired
    public FileDao fileDao;

    /**
     * 保存文件
     *
     * @param file
     * @return
     */
    public FileImage saveFile(FileImage file) {
        return fileDao.save(file);
    }

    /**
     * 删除文件
     *
     * @param id
     */
    public void removeFile(String id) {
        fileDao.removeFile(id);
    }

    /**
     * 根据id获取文件
     *
     * @param id
     * @return
     */
    public FileImage getFileById(String id) {

        return fileDao.getFileById(id);
    }

    /**
     * 分页查询，按上传时间降序
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public List<FileImage> listFilesByPage(int pageIndex, int pageSize) {
        return fileDao.listFilesByPage(pageIndex, pageSize);
    }

}
