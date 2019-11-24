package com.pku.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pku.base.BaseEntity;

@Entity
@Table(name="pku_book")
public class Book extends BaseEntity{
	
	private static final long serialVersionUID = -547834655609878193L;

	@Column(name = "book_name",columnDefinition="varchar(512) COMMENT '书名'")
	private String bookName;
	
	@Column(name = "author",columnDefinition="varchar(512) COMMENT '作者'")
	private String author;
	
	@Column(name = "category",columnDefinition="varchar(512) COMMENT '分类'")
	private String category;
	
	@Column(name = "publish_house",columnDefinition="varchar(512) COMMENT '出版社'")
	private String publishHouse;
	
	@Column(name = "borrow",columnDefinition="bit(1) COMMENT '是否被借阅'")
	private Boolean borrow;
	
	@Column(name = "type",columnDefinition="varchar(4) COMMENT '书的类型'")
	private String type;
	
	@Column(name = "in_time",columnDefinition="datetime COMMENT '图书入库日期'")
	private Date inTime;
	
	@Column(name = "upload_path",columnDefinition="varchar(512) comment '电子书文件的上传路径'")
	private String uploadPath;
	
	@Column(name = "file_size",columnDefinition="int(11) comment '电子书文件大小'")
	private Integer fileSize;
	
	@Column(name = "char_total",columnDefinition="int(11) comment '电子书文件字符个数'")
	private Integer charTotal;
	
	@Column(name = "upload_file_name",columnDefinition="varchar(512) comment '电子书上传文件名'")
	private String uploadFileName;
	
	@Column(name = "tag",columnDefinition="int(11) comment '电子书在线阅读标签'")
	private Integer tag;
	
	@Column(name = "place",columnDefinition="varchar(512) comment '实体书的摆放位置'")
	private String place;
	
	@Column(name = "read_status",columnDefinition="varchar(4) comment '实体书的阅读状态'")
	private String readStatus;

	
	
	public String getReadStatus() {
		return readStatus;
	}

	public void setReadStatus(String readStatus) {
		this.readStatus = readStatus;
	}

	public Integer getTag() {
		return tag;
	}

	public void setTag(Integer tag) {
		this.tag = tag;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public Integer getFileSize() {
		return fileSize;
	}

	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}

	public Integer getCharTotal() {
		return charTotal;
	}

	public void setCharTotal(Integer charTotal) {
		this.charTotal = charTotal;
	}

	public Boolean getBorrow() {
		return borrow;
	}

	public void setBorrow(Boolean borrow) {
		this.borrow = borrow;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getInTime() {
		return inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPublishHouse() {
		return publishHouse;
	}

	public void setPublishHouse(String publishHouse) {
		this.publishHouse = publishHouse;
	}
	
	public Book create(){
		this.setCreateTime(new Date());
		return (Book) this.save();
	}
	
	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	@Override
	public String toString() {
		return "Book [bookName=" + bookName + ", author=" + author
				+ ", category=" + category + ", publishHouse=" + publishHouse
				+ ", borrow=" + borrow + ", type=" + type + ", inTime="
				+ inTime + ", uploadPath=" + uploadPath + ", place=" + place
				+ "]";
	}
}
