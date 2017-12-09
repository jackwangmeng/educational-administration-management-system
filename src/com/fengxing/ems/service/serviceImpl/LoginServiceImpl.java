package com.fengxing.ems.service.serviceImpl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import com.fengxing.ems.service.serviceInterface.LoginService;
import com.fengxing.ems.shiro.MyShiroToken;

@Service
public class LoginServiceImpl implements LoginService {
	@Override
	public void vertify(String id, String password, String role, boolean rememberMe) 
			throws UnknownAccountException, IncorrectCredentialsException,UnknownAccountException{
		System.out.println("到了验证的方法！----------------------");
		
		//id不全为数字的话直接抛出异常
		if(!id.matches("[0-9]+")) {
			System.out.println("id不全为数字");
			throw new UnknownAccountException();
		}
		Subject currentUser = SecurityUtils.getSubject();
		MyShiroToken myShiroToken = new MyShiroToken(id, password);
		myShiroToken.setAttribute("role", role);
		myShiroToken.setRememberMe(rememberMe);
		currentUser.login(myShiroToken);
	}
}
