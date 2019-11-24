package com.pku.common.enums;

import java.util.ArrayList;
import java.util.List;

public enum Category {
	
	SLH("01","数理化"),
	
	MLMD("02","马列毛邓"),
	
	ZXZJ("03","哲学宗教"),
	
	SK("04","社科"),
	
	ZZFL("05","政治法律"),
	
	JS("06","军事"),
	
	JJ("07","经济"),
	
	KJWT("08","科教文体"),
	
	YYWX("09","语言文字"),
	
	WX("10","文学"),
	
	YS("11","艺术"),
	
	LSDL("12","历史地理"),

	ZRKX("13","自然科学"),
	
	TWDQ("14","天文地球"),
	
	SWKX("15","生物科学"),
	
	YYWS("17","医药卫生"),
	
	NYKX("18","农业科学"),
	
	GYJS("19","工业技术"),
	
	JTYS("20","交通运输"),
	
	HKHT("21","航空航天"),
	
	HJWS("22","环境安全"),
	
	ZH("23","综合"),
	
	QT("24","其他");
	
	private String value;

    private String name;
	
	private Category(){}
	
	private Category(String value,String name){
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
        for (Category type : Category.values()) {
            if (value.equals(type.getValue())) {
                return type.getName();
            }
        }
        return "";
    }
	
	private static List<Category> list;

    static {
        list = new ArrayList<Category>();
        Category[] types = Category.values();
        for (Category type : types) {
            list.add(type);
        }
    }

    public static List<Category> getDataList() {
        return list;
    }

    public static List<Category> getList() {
        return list;
    }
}

