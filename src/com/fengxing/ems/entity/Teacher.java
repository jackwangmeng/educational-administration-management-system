package com.fengxing.ems.entity;

/*
 * createTime:2017-12-3 19:03
 */
public class Teacher extends User{
	private String pro;
	private Major major;
	private String extra;
	
	public Teacher() {
		
	}
	
	public Teacher(String id) {
		super(id);
	}
	
	public String getPro() {
		return pro;
	}
	public void setPro(String pro) {
		this.pro = pro;
	}
	public Major getMajor() {
		return major;
	}
	public void setMajor(Major major) {
		this.major = major;
	}
	public String getExtra() {
		return extra;
	}
	public void setExtra(String extra) {
		this.extra = extra;
	}
	@Override
	public String toString() {
		return super.toString() + "Teacher [pro=" + pro + ", major=" + major + ", extra=" + extra + "]";
	}
	
	
}
