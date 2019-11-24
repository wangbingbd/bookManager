package com.pku.service;

import com.pku.book.UploadFileDTO;
import com.pku.common.InvokeResult;
import com.pku.common.PkuException;

/**
 * 文件处理接口
 * Package : com.pku.service 
 * 
 * @author YixinCapital -- wangwenlong
 *		   2017年5月3日 上午11:43:47
 *
 */
public interface IFileService {

	public InvokeResult<UploadFileDTO> uploadFile(UploadFileDTO uploadDto) throws PkuException;

}

