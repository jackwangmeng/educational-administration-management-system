package com.fengxing.ems.handler.student.userMange;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fengxing.ems.entity.User;
import com.fengxing.ems.service.serviceInterface.StudentService;

/*
 * createTime:2017-12-9 10:59
 * 修改密码
 */
@Controller
@RequestMapping("stu/userMange")
public class ChangePassowrd {
	
	@Autowired
	private StudentService studentService;
	
	/*
	 * createTime；2017-12-9 11:05
	 * 映射到改密码的页面
	 */
	@RequestMapping("/changePasswordPage.do")
	public String changePasswordPage(HttpServletRequest request) {
		System.out.println("到了改密码的函数！");
		User user = (User)SecurityUtils.getSubject().
				getSession().getAttribute("currentUser");
		request.setAttribute("id", user.getId());
		return "stu_pages/userMange/changePassword";
	}
	
	/*
	 * createTime: 2017-12-9 13:28
	 * ajax修改密码
	 */
	@RequestMapping("changePassword.do")
	public void changePassword(
			@RequestParam(value="oldPwd", required=true)String oldPassword,
			@RequestParam(value="newPwd", required=true)String newPassword,
			HttpServletResponse response
			) {
		System.out.println("oldPassword: " + oldPassword);
		System.out.println("newPassword: " + newPassword);
		//获取studentId
		User user = (User)SecurityUtils.getSubject().
				getSession().getAttribute("currentUser");
		String studentId = user.getId();
		
		//调用service更新密码
		String message = "";
		try {
			studentService.changePassword(studentId, oldPassword, newPassword);
			message = "success";
		}catch(Exception exception) {
			message = "failed";
			exception.printStackTrace();
		}
		
		//返回ajax的结果
		try {
			Writer writer = response.getWriter();
			writer.write(message);
			writer.flush();
			writer.close();
		}catch(Exception exception) {
			exception.printStackTrace();
		}
	}
}
