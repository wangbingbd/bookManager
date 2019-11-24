package com.pku.common.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 家庭图书的阅读状态
 * Package : com.pku.common.enums
 * 
 */
public enum ReadStatus {
	
	NOT_READ("01","未读"),
	
	READING("02","在读"),
	
	READED("03","已读");

	private String value;

    private String name;
    
    private ReadStatus(){}
    
    private ReadStatus(String value,String name){
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
        for (ReadStatus type : ReadStatus.values()) {
            if (value.equals(type.getValue())) {
                return type.getName();
            }
        }
        return "";
    }
    
	private static List<ReadStatus> list;

    static {
        list = new ArrayList<ReadStatus>();
        ReadStatus[] types = ReadStatus.values();
        for (ReadStatus type : types) {
            list.add(type);
        }
    }

    public static List<ReadStatus> getDataList() {
        return list;
    }

    public static List<ReadStatus> getList() {
        return list;
    }
}

