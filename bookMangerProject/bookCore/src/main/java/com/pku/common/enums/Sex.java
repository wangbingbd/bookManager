package com.pku.common.enums;

public enum Sex {

	Man(0,"男"),
	WOMAN(1,"女");
	
	private Integer value;
	
	private String name;
	private Sex(){}
	
	private Sex(Integer value,String name){
		this.value = value;
		this.name = name;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}

