package com.fengxing.ems.handler.student.courseMange;
/*
 * createTime: 2017-12-7 11:29
 */

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fengxing.ems.entity.User;
import com.fengxing.ems.service.serviceInterface.ScoreService;

@Controller
@RequestMapping("stu/courseMange")
public class AlreadlyAddedCourseHandler {
	
	@Autowired
	private ScoreService scoreService;
	
	/*
	 * createTime: 2017-12-7 11:30
	 * 映射到已选课程的页面
	 */
	@RequestMapping("/addedCoursePage.do")
	public String addedCoursePage() {
		System.out.println("到了已选课程的页面");
		return "stu_pages/courseMange/addedCourse";
	}
	
	/*
	 * createTime: 2017-12-7 11:38
	 * 加载已选课程的数据
	 */
	@ResponseBody
	@RequestMapping("/addedCourseTableRes.json")
	public Map<String, Object> getAddedCourseRes(
			@RequestParam(value="pageSize", required=false, defaultValue="8") Integer pageSize,
			@RequestParam(value="offset", required=false, defaultValue="0") Integer offset
			){
		System.out.println("到了加载表格资源这里...............");
		//处理分页参数
		Integer pageNumber = offset / pageSize + 1;
		
		//封装service的参数
		User user = (User)
				SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("studentId", user.getId());
		
		//调用service查出数据
		Map<String, Object> result = null;
		try {
			result = scoreService.studentGetAddedCourse(params, pageSize, pageNumber);
		}catch(Exception exception) {
			exception.printStackTrace();
		}
		return result;
	}
}
