package com.fengxing.ems.shiro;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authc.UsernamePasswordToken;
public class MyShiroToken extends UsernamePasswordToken{

	private static final long serialVersionUID = 1L;
	
	private Map<String, Object> attributes;
	
	public MyShiroToken(String username, String password) {
		super(username, password);
		attributes = new HashMap<String, Object>();
	}
	
	public void setAttribute(String key, String value) {
		attributes.put(key, value);
	}
	
	public Object getAttribute(String key) {
		return attributes.get(key);
	}

}

