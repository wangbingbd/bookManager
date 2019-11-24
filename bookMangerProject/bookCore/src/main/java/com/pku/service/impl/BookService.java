package com.pku.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Service;

import com.pku.assembler.BookAssembler;
import com.pku.book.BookDTO;
import com.pku.book.OnlineReadDTO;
import com.pku.common.BookConstant;
import com.pku.common.InvokeResult;
import com.pku.common.JpaEntityManager;
import com.pku.common.Page;
import com.pku.common.PkuException;
import com.pku.entity.Book;
import com.pku.service.IBookService;
import com.pku.util.DateUitls;
import com.pku.util.SpringContextUtil;

/**
 * 图书操作实现类
 * Package : com.pku.service.impl
 * 
 * @author YixinCapital -- wangwenlong
 *		   2017年4月28日 下午3:34:54
 *
 */
@Service
public class BookService implements IBookService{
	
	private static final Logger logger = LoggerFactory.getLogger(BookService.class);
	
	public InvokeResult<BookDTO> save(BookDTO bookDto) throws PkuException{
		InvokeResult<BookDTO> iResult = new InvokeResult<BookDTO>();
		Book book = null;
		try {
			Book entity = BookAssembler.assembleForBean(bookDto,null);
			book = entity.create();
			bookDto.setId(book==null?"":book.getId());
			iResult.setData(bookDto);
			iResult.setHasErrors(false);
		} catch (Exception e) {
			logger.error("图书保存异常："+e.getMessage(), e);
			iResult.failure("图书保存异常");
		}
		return iResult;
	}

	public InvokeResult<BookDTO> update(BookDTO bookDto) throws PkuException{
		InvokeResult<BookDTO> iResult = new InvokeResult<BookDTO>();
		try {
			if(StringUtils.isBlank(bookDto.getId())){
				iResult.failure("图书主键id 为空");
			}else{
				Book book = Book.get(Book.class, bookDto.getId());
				String fileUploadPath_old = book.getUploadPath();
				BookAssembler.assembleForUpdate(bookDto,book);
				String fileUploadPath_new = book.getUploadPath();
				if(!fileUploadPath_old.equals(fileUploadPath_new)){
					book.setTag(0);
				}
				book.update();
				iResult.setData(bookDto);
				iResult.setHasErrors(false);
			}
		} catch (Exception e) {
			logger.error("图书更新异常："+e.getMessage(), e);
			iResult.failure("图书更新异常");
		}
		return iResult;
	}

	public InvokeResult<BookDTO> findById(String id) throws PkuException{
		InvokeResult<BookDTO> iResult = new InvokeResult<BookDTO>();
		try {
			if(StringUtils.isBlank(id)){
				iResult.failure("图书主键id 为空");
			}else{
				Book book = Book.get(Book.class, id);
				BookDTO bookDto = BookAssembler.assembleForDto(book, null);
				iResult.setData(bookDto);
				iResult.setHasErrors(false);
			}
		} catch (Exception e) {
			logger.error("图书根据主键ID查询异常："+e.getMessage(), e);
			iResult.failure("图书根据主键ID查询异常");
		}
		return iResult;
	}

	@SuppressWarnings("unchecked")
	public InvokeResult<Page<BookDTO>> pageQuery(BookDTO bookDTO) throws PkuException {
		InvokeResult<Page<BookDTO>> iResult = new InvokeResult<Page<BookDTO>>();
		try {
			StringBuffer sql = new StringBuffer("SELECT * FROM pku_book WHERE 1=1 AND IS_DELETED = false");
			StringBuffer count = new StringBuffer("SELECT COUNT(ID) FROM pku_book WHERE 1=1 AND IS_DELETED = false ");
			if(StringUtils.isNotBlank(bookDTO.getBookName())){
				sql.append(" AND book_name LIKE '%"+bookDTO.getBookName()+"%'");
				count.append(" AND book_name LIKE '%"+bookDTO.getBookName()+"%'");
			}
			if(StringUtils.isNotBlank(bookDTO.getAuthor())){
				sql.append(" AND author LIKE '%"+bookDTO.getAuthor()+"%'");
				count.append(" AND author LIKE '%"+bookDTO.getAuthor()+"%'");
			}
			if(StringUtils.isNotBlank(bookDTO.getCategory())){
				sql.append(" AND category =" +bookDTO.getCategory());
				count.append(" AND category =" +bookDTO.getCategory());
			}
			if(StringUtils.isNotBlank(bookDTO.getPublishHouse())){
				sql.append(" AND publish_house LIKE '%"+bookDTO.getPublishHouse()+"%'");
				count.append(" AND publish_house LIKE '%"+bookDTO.getPublishHouse()+"%'");
			}
			if(StringUtils.isNotBlank(bookDTO.getType())){
				sql.append(" AND type = "+bookDTO.getType());
				count.append(" AND type = "+bookDTO.getType());
			}
			if(bookDTO.getBorrow() !=null){
				sql.append(" AND borrow = "+bookDTO.getBorrow());
				count.append(" AND borrow = "+bookDTO.getBorrow());
			}
			
			if(bookDTO.getInTimeStart()!=null){
				String dateStr = DateUitls.dateToStr(bookDTO.getInTimeStart());
				sql.append(" AND in_time >= '"+dateStr+"'");
				count.append(" AND in_time >= '"+dateStr+"'");
			}
			
			if(bookDTO.getInTimeEnd()!=null){
				Date end = DateUitls.getNextDay(bookDTO.getInTimeEnd());
				String dateStr = DateUitls.dateToStr(end);
				sql.append(" AND in_time < '"+dateStr+"'");
				count.append(" AND in_time < '"+dateStr+"'");
			}
			
			sql.append(" ORDER BY CREATE_TIME DESC ");
			
			JpaEntityManager jpaEntityManager = SpringContextUtil.getApplicationContext().getBean(JpaEntityManager.class);
			BigInteger resultCount = (BigInteger) jpaEntityManager.getEntityManager().createNativeQuery(count.toString()).getSingleResult();
			Integer entityCount = resultCount.intValue();
			List<Book> bookList = new ArrayList<Book>();
			List<BookDTO> dtoList = new ArrayList<BookDTO>();
			if(resultCount!=null && entityCount >0){
				bookList = jpaEntityManager.getEntityManager().createNativeQuery(sql.toString(), Book.class)
						.setFirstResult(bookDTO.getCurrent()-1).setMaxResults((bookDTO.getCurrent()*bookDTO.getRowCount()))
						.getResultList();
				logger.info("bookList.size()===="+bookList.size());
				for(Book book:bookList){
					BookDTO dto = new BookDTO();
					BookAssembler.assembleForDto(book, dto);
					dtoList.add(dto);
				}
				
			}
			Page<BookDTO> page = new Page<BookDTO>(bookDTO.getCurrent(),entityCount,bookDTO.getRowCount(),dtoList); 
			iResult.setData(page);
		} catch (BeansException e) {
			logger.error("图书列表查询异常："+e.getMessage(), e);
			iResult.failure("图书列表查询异常");
		}
		return iResult;
	}

	public InvokeResult<String> logicRemove(String id) throws PkuException {
		InvokeResult<String> iResult = new InvokeResult<String>();
		try {
			if(StringUtils.isBlank(id)){
				iResult.failure("图书主键id 为空");
			}else{
				Book book = Book.get(Book.class, id);
				book.logicRemove();
				iResult.setData(id);
				iResult.setHasErrors(false);
			}
		} catch (Exception e) {
			logger.error("图书根据主键逻辑删除异常："+e.getMessage(), e);
			iResult.failure("图书根据主键逻辑删除异常");
		}
		return iResult;
	}

	public InvokeResult<String> realRemove(String id) throws PkuException {
		InvokeResult<String> iResult = new InvokeResult<String>();
		try {
			if(StringUtils.isBlank(id)){
				iResult.failure("图书主键id 为空");
			}else{
				Book book = Book.get(Book.class, id);
				book.remove();
				iResult.setData(id);
				iResult.setHasErrors(false);
			}
		} catch (Exception e) {
			logger.error("图书根据主键物理删除异常："+e.getMessage(), e);
			iResult.failure("图书根据主键物理删除异常");
		}
		return iResult;
	}

	public InvokeResult<String> onlineRead(OnlineReadDTO online) throws PkuException {
		InvokeResult<String> iResult = new InvokeResult<String>();
		InputStreamReader reader = null;
		FileInputStream in = null;
		try {
			if(StringUtils.isBlank(online.getBookId())){
				iResult.failure("图书主键id 为空");
			}else{
				Book book = Book.get(Book.class, online.getBookId());
				if(StringUtils.isNotBlank(book.getUploadPath())){
					File file = new File(book.getUploadPath());
					if(file.exists()){
						in = new FileInputStream(file);
						reader = new InputStreamReader(in,"UTF-8");
						this.logger.info("========encoding:"+reader.getEncoding());
						char[] buf = new char[BookConstant.ONLINE_CHAR_NUMBER];
						int currentTag = book.getTag()==null?0:book.getTag();//当前的标签
						int length = 0;
						switch(online.getForword()){
						case 0: //当前页
							reader.skip(currentTag);
							length = reader.read(buf);
							break;
						case 1: //上一页
							reader.skip(currentTag>=BookConstant.ONLINE_CHAR_NUMBER?currentTag-BookConstant.ONLINE_CHAR_NUMBER:0);
							length = reader.read(buf);
							length = length==-1?0:length;
							currentTag= currentTag>=length?currentTag-length:0;
							break;
						case 2: //下一页
							if((currentTag+BookConstant.ONLINE_CHAR_NUMBER)>=book.getCharTotal()){
								iResult.failure("已达尾页");
								return iResult;
							}
							reader.skip(currentTag+BookConstant.ONLINE_CHAR_NUMBER);
							length = reader.read(buf);
							length = length==-1?0:length;
							currentTag +=length;
							break;
						case 3: //首页
							currentTag = 0;
							reader.skip(currentTag);
							length = reader.read(buf);
							break;
						case 4: //尾页
							currentTag = book.getCharTotal()>=BookConstant.ONLINE_CHAR_NUMBER?book.getCharTotal()-BookConstant.ONLINE_CHAR_NUMBER:0;
							reader.skip(currentTag);
							length = reader.read(buf);
							break;
						}
						book.setTag(currentTag);
						book.update();
						iResult.setData("<html>"+new String(buf).replaceAll("\n", "<br/>").replaceAll(" ", "&nbsp")+"</html>");
						iResult.setHasErrors(false);
					}
				}
			}
		} catch (Exception e) {
			logger.error("在线阅读获取异常："+e.getMessage(), e);
			iResult.failure("在线阅读获取异常");
		} finally{
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
			if(reader != null){
				try {
					reader.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
		return iResult;
	}
}

