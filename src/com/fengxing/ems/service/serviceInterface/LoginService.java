package com.fengxing.ems.service.serviceInterface;

public interface LoginService {
	public void vertify(String id, String pwd, 
				String role, boolean rememberMe);
}
