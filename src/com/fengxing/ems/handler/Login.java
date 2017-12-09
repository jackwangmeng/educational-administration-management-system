package com.fengxing.ems.handler;

import java.io.IOException;
import java.io.Writer;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.fengxing.ems.entity.User;
import com.fengxing.ems.service.serviceInterface.LoginService;


@Controller
public class Login {
	public static final String SUCCESS = "success";
	public static final String NO_ID = "no_id";
	public static final String WRONG_PASSWORD = "wrong_password";
	public static final String LOGIN_FAILED = "login_failed";
	@Autowired
	private LoginService loginService;
	
	@RequestMapping("login.do")
	public void login(                                                        
			@RequestParam(value="id") String id,
			@RequestParam(value="pwd") String pwd,
			@RequestParam(value="role") String role,
			HttpServletResponse httpServletResponse
			) throws IOException {
		
		System.out.println("到了登录的方法.....................");
		System.out.println(id + "  " + pwd + "  " + role);
		
		String data = "";
		try {
			loginService.vertify(id, pwd, role, false);
			data = SUCCESS;
			
			//如果登录成功就将信息保存在shiro的session中
			System.out.println("登录成功！");
			User user = new User();
			user.setId(id);
			user.setRole(role);
			/*
			 * 将user对象放到session中
			 * 重启服务器时，tomcat会将session持久化，所以需要给user实现序列化接口
			 */
			SecurityUtils.getSubject().getSession().setAttribute("currentUser", user);
		}catch(UnknownAccountException uae) {
			System.out.println("用户名不存在！");
			data = NO_ID;
		}catch(IncorrectCredentialsException ice) {
			System.out.println("用户名或密码错误！");
			data = WRONG_PASSWORD;
		}catch(Exception exception) {
			exception.printStackTrace();
			System.out.println("登录失败！");
			data = LOGIN_FAILED;
		}
		
		//ajax返回登录信息给浏览器
		Writer writer = httpServletResponse.getWriter();
		try {
			writer.write(data);
		}catch (Exception e) {
		}finally {
			writer.flush();
			writer.close();
		}
		System.out.println();
	}
}
