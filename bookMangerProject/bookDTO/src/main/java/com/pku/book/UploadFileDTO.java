package com.pku.book;

import java.io.InputStream;
import java.io.Serializable;

public class UploadFileDTO implements Serializable{

	private static final long serialVersionUID = -8073940132960631482L;

	private byte[] stream;
	
	private InputStream streamIn;
	
	private String fileName;
	
	private String fileType;
	
	private String uploadPath;//电子书文件的上传路径
	
	private Integer fileSize;//电子书文件大小
	
	private String fileSizeShow;//电子书文件大小
	
	private Integer charTotal;//电子书文件字符个数

	public byte[] getStream() {
		return stream;
	}

	public void setStream(byte[] stream) {
		this.stream = stream;
	}

	public InputStream getStreamIn() {
		return streamIn;
	}

	public void setStreamIn(InputStream streamIn) {
		this.streamIn = streamIn;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	public Integer getFileSize() {
		return fileSize;
	}

	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileSizeShow() {
		return fileSizeShow;
	}

	public void setFileSizeShow(String fileSizeShow) {
		this.fileSizeShow = fileSizeShow;
	}

	public Integer getCharTotal() {
		return charTotal;
	}

	public void setCharTotal(Integer charTotal) {
		this.charTotal = charTotal;
	}
	
}

