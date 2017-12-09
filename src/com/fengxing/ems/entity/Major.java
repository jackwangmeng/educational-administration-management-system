package com.fengxing.ems.entity;
/*
 * createTIme 2017-12-3 18:40
 */
public class Major {
	private String name;
	private String college;
	private Integer length;
	private String extra;
	
	public Major() {
		
	}
	
	public Major(String name) {
		this.name = name;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	@Override
	public String toString() {
		return "Major [name=" + name + ", college=" + college + ", length=" + length + ", extra=" + extra + "]";
	}
	
}
