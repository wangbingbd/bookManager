package com.pku.assembler;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pku.book.BookDTO;
import com.pku.common.enums.BookType;
import com.pku.common.enums.Category;
import com.pku.entity.Book;

/**
 * 图书DTO与实体类之间装配
 * Package : com.pku.assembler
 * 
 * @author YixinCapital -- wangwenlong
 *		   2017年4月28日 下午4:00:09
 *
 */
public class BookAssembler {
	
	private static final Logger logger = LoggerFactory.getLogger(BookAssembler.class);
	
	/**
	 * DTO 装配实体bean
	 * @param dto
	 * @param book
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2017年4月29日 下午5:33:36
	 */
	public static Book assembleForBean(BookDTO dto,Book book){
		if(book==null){
			book = new Book();
		}
		convert(dto,book,false);
		return book;
	};
	
	public static Book assembleForUpdate(BookDTO dto,Book book){
		if(book==null){
			book = new Book();
		}
		convert(dto,book,true);
		return book;
	}
	
	/**
	 * 实体bean装配到DTO
	 * @param book
	 * @param dto
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2017年4月29日 下午5:35:12
	 */
	public static BookDTO assembleForDto(Book book,BookDTO dto){
		if(dto==null){
			dto = new BookDTO();
		}
		convert(book,dto,false);
		dto.setTypeName(BookType.getDisplayNameByIndex(dto.getType()));
		dto.setCategoryName(Category.getDisplayNameByIndex(dto.getCategory()));
		if(dto.getFileSize()!=null){
			BigDecimal total = new BigDecimal(dto.getFileSize());
			BigDecimal weight = new BigDecimal(1024);
			dto.setFileSizeShow(total.divide(weight, 2, BigDecimal.ROUND_HALF_UP).toPlainString());
		}
		return dto;
	};
	
	private static void convert(Object source, Object target,boolean isExceptNull) {
		try {
			Field[] fieldsSource = source.getClass().getDeclaredFields();
			Field[] fieldsTarget = target.getClass().getDeclaredFields();
			Map<String, Field> targetMap = new HashMap<String, Field>();
			for (Field field : fieldsTarget) {
				targetMap.put(field.getName(), field);
			}
			for (Field field : fieldsSource) {
				String filedName = field.getName();
				if ("serialVersionUID".equals(filedName)) {
					continue;
				}
				Field fieldTarget = targetMap.get(filedName);
				if (fieldTarget != null) {
					fieldTarget.setAccessible(true);
					field.setAccessible(true);
					Object value = field.get(source);
					if(isExceptNull && value==null){
						continue;
					}
					fieldTarget.set(target, value);
				}
			}
		} catch (Exception e) {
			logger.error("===convert 类型转换异常===", e);
		}
	}
}

