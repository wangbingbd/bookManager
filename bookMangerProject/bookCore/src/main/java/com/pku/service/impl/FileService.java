package com.pku.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pku.book.UploadFileDTO;
import com.pku.common.BookConstant;
import com.pku.common.InvokeResult;
import com.pku.common.PkuException;
import com.pku.service.IFileService;

/**
 * 文件处理实现类
 * Package : com.pku.service.impl
 * 
 * @author YixinCapital -- wangwenlong
 *		   2017年5月3日 上午11:43:27
 *
 */
@Service
public class FileService implements IFileService {
	
	private static final Logger logger = LoggerFactory.getLogger(FileService.class);

	public InvokeResult<UploadFileDTO> uploadFile(UploadFileDTO uploadDto) throws PkuException {
		InvokeResult<UploadFileDTO> iresult = new InvokeResult<UploadFileDTO>();
		iresult.setHasErrors(true);
		FileOutputStream out = null;
		FileInputStream in = null;
		try {
			byte[] fileBytes = uploadDto.getStream();
			String innerName = "novel"+System.currentTimeMillis()+".txt";
			File file = new File(BookConstant.UPLOAD_PATH+innerName);
			out = new FileOutputStream(file);
			out.write(fileBytes);
			out.flush();
			
			if(file.exists()){
				UploadFileDTO dto = new UploadFileDTO();
				dto.setUploadPath(file.getAbsolutePath());// 上传路径
				dto.setFileSize(fileBytes.length);//文件大小
				BigDecimal total = new BigDecimal(fileBytes.length);
				BigDecimal weight = new BigDecimal(1024);
				dto.setFileSizeShow(total.divide(weight, 2, BigDecimal.ROUND_HALF_UP).toPlainString());
				dto.setFileName(uploadDto.getFileName()); //文件名
				dto.setCharTotal(getCharTotal(file));//字符个数
				iresult.setHasErrors(false);
				iresult.setData(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(out != null){
				try {
					out.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
			if (null != in) {  
                try {  
                	in.close();  
                } catch (IOException e) {  
                	logger.error(e.getMessage(), e);
                }  
            }  
		}
		return iresult;
	}
	
	private Integer getCharTotal(File file){
		FileInputStream in = null;
		InputStreamReader reader = null;
		try {
			in = new FileInputStream(file);
			reader = new InputStreamReader(in,"UTF-8");
			char[] buf = new char[BookConstant.ONLINE_CHAR_NUMBER];
			int count = 0;
			int length = 0;
			while((length = reader.read(buf))!=-1){
				count +=length;
			}
	        return count;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally{
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
			if(reader !=null){
				try {
					reader.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
		return 0;
	}

}

