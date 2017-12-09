package com.fengxing.ems.handler.student.courseMange;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fengxing.ems.entity.Activity;
import com.fengxing.ems.service.serviceInterface.CourseKindService;
import com.fengxing.ems.service.serviceInterface.CourseService;
/*
 * createTime:2017-12-5 19:35
 */
@Controller
@RequestMapping("stu/courseMange")
public class courseHandler {
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private CourseKindService courseKindService;
	
	/*
	 * createTime: 2017-12-5 19:32
	 * 映射到显示选课课程的页面
	 */
	@RequestMapping("coursePage.do")
	public String coursePage(
			HttpServletRequest request
			) {
		//查出课程分类的信息
		try {
			//把课程分类信息放到request域中
			request.setAttribute("courseKinds", courseKindService.getAllCourseKind());
		}catch(Exception exception) {
			System.out.println("查询课程分类的时候出现了异常");
			exception.printStackTrace();
		}
		return "stu_pages/courseMange/course";
	}
	
	/*
	 * createTIme: 2017-12-5 19:42
	 * 获取课程数据
	 */
	@RequestMapping("courseRes.json")
	@ResponseBody
	public Map<String, Object> getCourseRes(
		@RequestParam(value="pageSize", required=false, defaultValue="8") Integer pageSize,
		@RequestParam(value="offset", required=false, defaultValue="0") Integer offset,
		
		@RequestParam(value="day", required=false) Integer day,
		@RequestParam(value="section", required=false) Integer section,
		@RequestParam(value="courseName", required=false) String courseName,
		@RequestParam(value="courseKind", required=false) String courseKind
			){
		System.out.println("day: " + day);
		System.out.println("section: " + section);
		System.out.println("courseName: " + courseName);
		System.out.println("courseKind: " + courseKind);
		System.out.println();
		
		//处理参数
		Integer pageNumber = offset / pageSize + 1;
		
		//构造mapper的参数
		Activity activity = (Activity)
				SecurityUtils.getSubject().getSession().getAttribute("activity");
		if(activity == null) {
			System.out.println("没用经过验证！ 直接用url进入选课");
			return null;
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("activityId", activity.getId());
		params.put("courseKind", courseKind);
		params.put("courseName", courseName);
		params.put("day", day);//前台索引从1开始
		params.put("section", section);//前台索引从1开始
		
		//获取数据
		Map<String, Object> result = null;
		try {
			result = courseService.studentGetCourse(params, pageSize, pageNumber);
		}catch(Exception exception) {
			exception.printStackTrace();
		}
		return result;
	}
}
