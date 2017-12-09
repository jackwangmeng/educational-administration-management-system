package com.fengxing.ems.handler.student.courseMange;

import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fengxing.ems.Exception.CourseRepeatException;
import com.fengxing.ems.Exception.CourseTimeConflictException;
import com.fengxing.ems.entity.Activity;
import com.fengxing.ems.entity.Student;
import com.fengxing.ems.entity.User;
import com.fengxing.ems.mapper.ScoreMapper;
import com.fengxing.ems.service.serviceInterface.ScoreService;

/*
 * createTime: 2017-12-6 15:28
 * 选课，退选
 */
@Controller
@RequestMapping("stu/courseMange/")
public class AddCourseHandler {
	
	@Autowired
	private ScoreService scoreService;
	
	/*
	 * createTime: 2017-12-6 15:28
	 * 选课
	 */
	@RequestMapping("/addCourse.do")
	public void addCourse(
			@RequestParam(value="courseId", required=true) String courseId,
			HttpServletResponse response
			) {
		System.out.println("到了选课的方法！");
		System.out.println("courseId: " + courseId);
		
		//获取service的参数
		//(1).从session中获取activity的信息
		Activity activity = (Activity)SecurityUtils.
				getSubject().getSession().getAttribute("activity");
		if(activity == null) {
			System.out.println("没用从选课首页进来，可能遭到攻击");
			return;
		}
		//(2).获取user
		User user = (User)SecurityUtils.getSubject().
				getSession().getAttribute("currentUser");
		
		//调用service方法选课
		String message = ""; 
		try {
			scoreService.studentAddCourse(activity.getId(), user.getId(), courseId);
			message = "选课成功！";
		}catch(CourseRepeatException cre) {
			System.out.println(cre.getMessage());
			message = cre.getMessage();
		}
		catch(CourseTimeConflictException ctce) {
			System.out.println(ctce.getMessage());
			message = ctce.getMessage();
		}
		catch(Exception e) {
			message = "系统错误";
			e.printStackTrace();
		}
		
		//ajax返回选课结果
		try {
			Writer writer = response.getWriter();
			writer.write(message);
			writer.flush();
			writer.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 *  createTime：2017-12-7 16:13
	 *  退选
	 */
	@RequestMapping("/deleteCourse.do")
	public void deleteCourse(
			@RequestParam(value="courseId", required=true) String courseId,
			HttpServletResponse response
			) {
		System.out.println("到了退选的方法!  courseId: " + courseId);
		//获取student
		User user = (User)
				SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		
		//退选
		String message = "";
		try {
			scoreService.studentDeleteCourse(user.getId(), courseId);
			message = "success";
		}catch(Exception exception) {
			message = "failed";
			exception.printStackTrace();
		}
		
		//返回结果到前端
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
