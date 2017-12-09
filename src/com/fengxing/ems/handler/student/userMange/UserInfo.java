package com.fengxing.ems.handler.student.userMange;

import static org.junit.Assert.assertTrue;

import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fengxing.ems.entity.Student;
import com.fengxing.ems.entity.User;
import com.fengxing.ems.service.serviceInterface.StudentService;

/*
 * createTime: 2017-12-8 19:56
 * 显示用户个人信息
 */
@Controller
@RequestMapping("stu/userMange")
public class UserInfo {
	
	@Autowired
	private StudentService studentService;
	
	/*
	 * createTime: 2017-12-8 19:57
	 * 显示用户的个人信息
	 */
	@RequestMapping("showUserInfo.do")
	public String showUserInfo(HttpServletRequest request) {
		System.out.println("到了显示学生个人信息的页面");
		//获取service参数
		User user = (User)SecurityUtils.getSubject().
				getSession().getAttribute("currentUser");
		
		//获取学生信息
		try {
			Student student = studentService.getStudent(user.getId());
			request.setAttribute("student", student);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "stu_pages/userMange/showUserInfo";
	}
	
	/*
	 * createTime: 2017-12-9 10:01
	 * ajax修改学生信息
	 */
	@RequestMapping("/changeUserInfo.do")
	public void changeUserInfo(
		@RequestParam(value="adress", required=true)String adress,
		@RequestParam(value="phone", required=true)String phone,
		@RequestParam(value="email", required=true)String email,
		HttpServletResponse response
			) {
		//封装service的参数
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("adress", adress);
		params.put("phone", phone);
		params.put("email", email);
		User user = (User)SecurityUtils.getSubject().
				getSession().getAttribute("currentUser");
		params.put("id", user.getId());
		
		//调用service更新
		String message = "";
		try {
			studentService.updatePersonalInfo(params);
			message = "success";
		}catch(Exception exception) {
			message = "failed";
			exception.printStackTrace();
		}
		
		//返回ajax结果
		try {
			Writer writer = response.getWriter();
			writer.write(message);
			writer.flush();
			writer.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
