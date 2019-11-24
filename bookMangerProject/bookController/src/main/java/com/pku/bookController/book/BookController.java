package com.pku.bookController.book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pku.book.BookDTO;
import com.pku.book.OnlineReadDTO;
import com.pku.common.InvokeResult;
import com.pku.common.Page;
import com.pku.common.enums.BookType;
import com.pku.common.enums.Category;
import com.pku.common.enums.ReadStatus;
import com.pku.exception.PkuException;
import com.pku.service.IBookService;

@Controller
@RequestMapping("/bookController")
public class BookController {
	
	private Logger logger = LoggerFactory.getLogger(BookController.class);
	
	@Autowired
	private IBookService bookService;  
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/query", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Page<BookDTO> pageQuery(BookDTO dto){
		this.logger.info("========pageQuery 进入=================");
		InvokeResult<Page<BookDTO>> iresult = bookService.pageQuery(dto);
		if(iresult.isSuccess()){
			this.logger.info("========pageQuery 退出=================");
			return (Page<BookDTO>)iresult.getData();
		}else{
			return new Page<BookDTO>();
		}
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public InvokeResult<BookDTO> save(BookDTO bookDtoO){
		this.logger.info("======== save 进入=================");
		InvokeResult<BookDTO> iresult = new InvokeResult<BookDTO>();
		try {
			iresult = bookService.save(bookDtoO);
		} catch (PkuException e) {
			this.logger.error("图书保存异常："+e.getMessage(), e);
			iresult.failure("图书保存异常");
		}
		this.logger.info("======== save 退出=================");
		return iresult;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public InvokeResult<BookDTO> update(BookDTO bookDtoO){
		this.logger.info("======== update 进入=================");
		InvokeResult<BookDTO> iresult = new InvokeResult<BookDTO>();
		try {
			iresult = bookService.update(bookDtoO);
		} catch (PkuException e) {
			this.logger.error("图书更新异常："+e.getMessage(), e);
			iresult.failure("图书更新异常");
		}
		this.logger.info("======== update 退出=================");
		return iresult;
	}
	
	   @ResponseBody
	   @RequestMapping(value = "/getBookTypes", method = {RequestMethod.POST})
	   public InvokeResult<List<Map<String,String>>> getCategoryTypes() {
		   InvokeResult<List<Map<String,String>>> result = new InvokeResult<List<Map<String,String>>>();
	       try {
	    	   List<BookType> BookTypes = BookType.getList();
	    	   List<Map<String,String>> resultList = new ArrayList<Map<String,String>>();
	    	   for(BookType type:BookTypes){
	    		   Map<String,String> typeMap = new HashMap<String,String>();
	    		   typeMap.put("value", type.getValue());
	    		   typeMap.put("name", type.getName());
	    		   resultList.add(typeMap);
	    	   }
	    	   result.setData(resultList);
	       } catch (Exception e) {
	          this.logger.error("=========获取图书类型异常 getBookTypes", e);
	       	  result.failure("获取图书类型异常");
	       }
	       return result;
	   }
	   
	   @ResponseBody
	   @RequestMapping(value = "/getCategorys", method = {RequestMethod.POST})
	   public InvokeResult<List<Map<String,String>>> getCategorys() {
		   InvokeResult<List<Map<String,String>>> result = new InvokeResult<List<Map<String,String>>>();
	       try {
	    	   List<Category> categoryTypes = Category.getList();
	    	   List<Map<String,String>> resultList = new ArrayList<Map<String,String>>();
	    	   for(Category type:categoryTypes){
	    		   Map<String,String> typeMap = new HashMap<String,String>();
	    		   typeMap.put("value", type.getValue());
	    		   typeMap.put("name", type.getName());
	    		   resultList.add(typeMap);
	    	   }
	    	   result.setData(resultList);
	       } catch (Exception e) {
	          this.logger.error("=========获取图书类别异常 getCategorys", e);
	       	  result.failure("获取图书类别异常");
	       }
	       return result;
	   }
	   
	   
	   @ResponseBody
	   @RequestMapping(value = "/getReadStatus", method = {RequestMethod.POST})
	   public InvokeResult<List<Map<String,String>>> getReadStatus() {
		   InvokeResult<List<Map<String,String>>> result = new InvokeResult<List<Map<String,String>>>();
	       try {
	    	   List<ReadStatus> readStatus = ReadStatus.getList();
	    	   List<Map<String,String>> resultList = new ArrayList<Map<String,String>>();
	    	   for(ReadStatus type:readStatus){
	    		   Map<String,String> typeMap = new HashMap<String,String>();
	    		   typeMap.put("value", type.getValue());
	    		   typeMap.put("name", type.getName());
	    		   resultList.add(typeMap);
	    	   }
	    	   result.setData(resultList);
	       } catch (Exception e) {
	          this.logger.error("=========获取图书类别异常 getCategorys", e);
	       	  result.failure("获取图书类别异常");
	       }
	       return result;
	   }
	   
	   @ResponseBody
	   @RequestMapping(value = "/fingBookById", method = {RequestMethod.POST})
	   public InvokeResult<BookDTO> fingBookById(BookDTO bookDto){
		   InvokeResult<BookDTO> iresult = new InvokeResult<BookDTO>();
		   try {
			   	iresult = bookService.findById(bookDto.getId());
			} catch (PkuException e) {
				this.logger.error("=========获取图书信息异常 fingBookById", e);
				iresult.failure("获取图书信息异常");
			}
		   return iresult;
	   }
	   
	   @ResponseBody
	   @RequestMapping(value = "/logicRemove", method = {RequestMethod.POST})
	   public InvokeResult<String> logicRemove(BookDTO bookDto){
		   InvokeResult<String> iresult = new InvokeResult<String>();
		   try {
			   	iresult = bookService.logicRemove(bookDto.getId());
			} catch (PkuException e) {
				this.logger.error("=========图书根据主键逻辑删除异常 logicRemove", e);
				iresult.failure("图书根据主键逻辑删除异常");
			}
		   return iresult;
	   }
	   
	   @ResponseBody
	   @RequestMapping(value = "/onlineRead", produces = "text/html; charset=utf-8", method = {RequestMethod.POST})
	   public String onlineRead(OnlineReadDTO online){
		   String html = "";
		   try {
			   InvokeResult<String> iresult = bookService.onlineRead(online);
			   if(iresult.isSuccess()){
				   html = (String) iresult.getData();
			   }else{
				   html = iresult.getErrorMessage();
			   }
			} catch (PkuException e) {
				this.logger.error("=========在线阅读获取异常 onlineRead", e);
			}
		   return html;
	   }
}

