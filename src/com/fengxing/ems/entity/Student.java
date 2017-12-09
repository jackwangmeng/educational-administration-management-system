package com.fengxing.ems.entity;

/*
 * createTime:2017-12-3 18:58
 */
public class Student extends User{
	private static final String NORMAL = "normal";//正常
	private static final String EXTEND = "extend";//延长学制
	private static final String REST = "rest";//休学
	
	private Class cls;
	private String status = NORMAL;
	private String extra;
	private String kind;
	public Class getCls() {
		return cls;
	}
	public void setCls(Class cls) {
		this.cls = cls;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getExtra() {
		return extra;
	}
	public void setExtra(String extra) {
		this.extra = extra;
	}
	
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	@Override
	public String toString() {
		return "Student [cls=" + cls + ", status=" + status + ", extra=" + extra + ", kind=" + kind + "]";
	}
	
}
