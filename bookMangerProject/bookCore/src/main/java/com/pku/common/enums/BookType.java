/**
 * 2017年5月2日
 * wangwenlong
 */
package com.pku.common.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 书的类型
 * Package : com.pku.common
 * 
 * @author YixinCapital -- wangwenlong
 *		   2017年5月2日 上午9:28:48
 *
 */
public enum BookType {
	
	ELECTRONIC("01","电子书"),
	
	PHYSICAL("02","实体书");

	private String value;

    private String name;
    
    private BookType(){}
    
    private BookType(String value,String name){
    	this.value = value;
    	this.name = name;
    }

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static String getDisplayNameByIndex(String value) {
        for (BookType type : BookType.values()) {
            if (value.equals(type.getValue())) {
                return type.getName();
            }
        }
        return "";
    }
    
	private static List<BookType> list;

    static {
        list = new ArrayList<BookType>();
        BookType[] types = BookType.values();
        for (BookType type : types) {
            list.add(type);
        }
    }

    public static List<BookType> getDataList() {
        return list;
    }

    public static List<BookType> getList() {
        return list;
    }
}

