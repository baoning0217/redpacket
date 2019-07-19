 package com.xishanqu.redpacket.dao;


 import com.xishanqu.redpacket.pojo.FileImage;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.data.domain.PageRequest;
 import org.springframework.data.domain.Pageable;
 import org.springframework.data.domain.Sort;
 import org.springframework.data.mongodb.core.MongoTemplate;
 import org.springframework.data.mongodb.core.query.Criteria;
 import org.springframework.data.mongodb.core.query.Query;
 import org.springframework.stereotype.Component;

 import java.util.List;


 /**
 * 数据库访问接口
 * @Author BaoNin
 */
@Component
public class FileDao {

 @Autowired
 private MongoTemplate mongoTemplate;

  /**
   * 保存
   * @param fileImage
   * @return
   */
 public FileImage save(FileImage fileImage){
  return mongoTemplate.save(fileImage);
 }

  /**
   * 根据id删除
   * @param id
   */
 public void removeFile(String id){
  Query query = new Query(Criteria.where("_id").is(id));
  mongoTemplate.remove(query, FileImage.class);
 }

  /**
   * 根据id获取
   * @param id
   * @return
   */
 public FileImage getFileById(String id){
  Query query = new Query(Criteria.where("_id").is(id));
  return mongoTemplate.findOne(query, FileImage.class);
 }

  /**
   * 查询列表数据
   * @param pageIndex
   * @param pageSize
   * @return
   */
 public List<FileImage> listFilesByPage(int pageIndex, int pageSize ){
  Sort sort = new Sort(Sort.Direction.DESC,"uploadDate");
  Pageable pageable = PageRequest.of(pageIndex,pageSize,sort);
  Query query = new Query();
  query.with(pageable);
  List<FileImage> fileImages = mongoTemplate.find(query, FileImage.class);
  return fileImages;
 }


}
