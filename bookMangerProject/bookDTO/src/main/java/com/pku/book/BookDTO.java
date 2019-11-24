package com.pku.book;

import java.util.Date;

import com.pku.common.BaseDTO;

public class BookDTO extends BaseDTO{
	
	private static final long serialVersionUID = 1645575327444462672L;
	
	private String bookName; //书名
	
	private String author;//作者
	
	private String category;//分类
	
	private String categoryName;//分类名称
	
	private String publishHouse;//出版社
	
	private Boolean borrow;//是否借阅
	
	private String type;//书的类型
	
	private String typeName;//书的类型名称
	
	private Date inTime;//入库日期
	
	private Date inTimeStart;//入库日期搜索开始
	
	private Date inTimeEnd;//入库日期搜索结束
	
	private String uploadPath;//电子书文件的上传路径
	
	private Integer fileSize;//电子书文件大小
	
	private String fileSizeShow;//电子书文件大小
	
	private Integer charTotal;//电子书文件字符个数
	
	private String uploadFileName;//电子书上传文件名
	
	private String place;//实体书的摆放位置
	
	private String readStatus;//实体书的阅读状态
	
	
	
	public String getReadStatus() {
		return readStatus;
	}

	public void setReadStatus(String readStatus) {
		this.readStatus = readStatus;
	}

	public Date getInTimeStart() {
		return inTimeStart;
	}

	public void setInTimeStart(Date inTimeStart) {
		this.inTimeStart = inTimeStart;
	}

	public Date getInTimeEnd() {
		return inTimeEnd;
	}

	public void setInTimeEnd(Date inTimeEnd) {
		this.inTimeEnd = inTimeEnd;
	}

	public String getFileSizeShow() {
		return fileSizeShow;
	}

	public void setFileSizeShow(String fileSizeShow) {
		this.fileSizeShow = fileSizeShow;
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

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
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

	@Override
	public String toString() {
		return "BookDTO [bookName=" + bookName + ", author=" + author
				+ ", category=" + category + ", categoryName=" + categoryName
				+ ", publishHouse=" + publishHouse + ", borrow=" + borrow
				+ ", type=" + type + ", typeName=" + typeName + ", inTime="
				+ inTime + ", uploadPath=" + uploadPath + ", place=" + place
				+ "]";
	}
}

