package com.xishanqu.redpacket.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @Author BaoNing
 */
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileImage {
	
	@Id
	private String id;
	/**
	 * 文件名称
	 */
	private String name;
	/**
	 * 文件类型
	 */
	private String contentType;
	private long size;
	private Date uploadDate;
	private String md5;
	/**
	 * 文件内容
	 */
	private Binary content;
	/**
	 * 文件路径
	 */
	private String path;


	public FileImage(String name, String contentType, long size, Binary content) {
		this.name = name;
		this.contentType = contentType;
		this.size = size;
		this.uploadDate = new Date();
		this.content = content;
	}
	

}
