package com.pku.service;

import com.pku.book.BookDTO;
import com.pku.book.OnlineReadDTO;
import com.pku.common.InvokeResult;
import com.pku.common.Page;
import com.pku.common.PkuException;

/**
 * 图书操作接口
 * Package : com.pku.service
 * 
 * @author YixinCapital -- wangwenlong
 *		   2017年4月28日 下午3:34:39
 *
 */
public interface IBookService extends IBaseService<BookDTO,String>{
	
	public InvokeResult<Page<BookDTO>> pageQuery(BookDTO bookDTO) throws PkuException;
	
	public InvokeResult<String> onlineRead(OnlineReadDTO online) throws PkuException;
}

