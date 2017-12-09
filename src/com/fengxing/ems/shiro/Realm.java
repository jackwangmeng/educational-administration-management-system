package com.fengxing.ems.shiro;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.fengxing.ems.entity.User;
import com.fengxing.ems.mapper.UserMapper;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;

public class Realm extends AuthorizingRealm {
	@Autowired
	private UserMapper userMapper;

	// 获取认证信息的方法
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("到了realm获取认证信息的方法---------------------");
		MyShiroToken myShiroToken = (MyShiroToken) token;
		// 获取用户输入的用户名,用户类别
		String userName = myShiroToken.getUsername();
		String role = (String) myShiroToken.getAttribute("role");
		System.out.println("userName  " + userName );
		
		User userFromDataBase = null;
		Object credentials = null;
		// 从数据库查询用户的记录
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", userName);
		params.put("role", role);
		userFromDataBase = userMapper.getUser(params);
		//数据库查出来的密码，是加了盐的
		if(userFromDataBase != null) {
			credentials = userFromDataBase.getPassword();
			System.out.println(userFromDataBase.toString());
		}
		System.out.println("######################3");
		// 帐号不存在抛出UnknownAccountException
		if (null == userFromDataBase) {
			System.out.println("帐号不存在");
			throw new UnknownAccountException();
		}
		// 构建authenticationInfo对象返回
		// (1) principal 认证的实体信息，可以是userName,也可以是数据表对应的实体类对象
		Object principal = role;
		
		// (2)credentials 加盐的密码,实际项目中加盐的密码是直接从数据库中查出来的
		
		// (3) 当前realm对象的name,调用父类的getName()方法
		String realmName = getName();
		
		// (4)盐
		ByteSource credentialsSalt = ByteSource.Util.bytes(userName);
		
		SimpleAuthenticationInfo info =
				new SimpleAuthenticationInfo
				(principal, credentials, credentialsSalt, realmName);
		return info;
	}

	// 获取授权信息的方法
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}
}