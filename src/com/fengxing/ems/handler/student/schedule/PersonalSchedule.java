package com.fengxing.ems.handler.student.schedule;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
/*
 * createTime: 2017-12-8 14:53
 * 个人课表
 */

import com.fengxing.ems.entity.User;
import com.fengxing.ems.service.serviceInterface.ScheduleService;

@RequestMapping("stu/schedule")
@Controller
public class PersonalSchedule {
	
	@Autowired
	private ScheduleService scheduleService;
	
	/*
	 * createTime：2017-12-8 14：56
	 */
	@RequestMapping("/personalSchedulePage.do")
	public String personalSchedulePage(HttpServletRequest request) {
		System.out.println("到了个人课表的页面！");
		return "stu_pages/schedule/personalSchedule";
	}
	
	/*
	 * createTIme: 2017-12-8 14:56
	 * 获取课表资源
	 */
	@ResponseBody
	@RequestMapping("/personalScheduleRes.json")
	public Map<String, Object> personalScheduleRes(
			@RequestParam(value="courseTerm", required=false)String courseTerm
			){
		System.out.println("到了加载课表资源的方法！");
		
		//构造service的参数
		User user = (User)SecurityUtils.getSubject().
				getSession().getAttribute("currentUser");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("studentId", user.getId());
		if(courseTerm == null || courseTerm.equals("")) {
			courseTerm = "2017-2018-2";
		}
		params.put("courseTerm", courseTerm);
		
		//获取数据
		Map<String, Object> result = null;
		try {
			result = scheduleService.studentSchedule(params);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
