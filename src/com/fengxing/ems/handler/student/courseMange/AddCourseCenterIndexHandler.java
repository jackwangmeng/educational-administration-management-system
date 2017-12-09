package com.fengxing.ems.handler.student.courseMange;

import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fengxing.ems.Exception.CannotIntoException;
import com.fengxing.ems.entity.Activity;
import com.fengxing.ems.entity.User;
import com.fengxing.ems.service.serviceInterface.ActivityService;

/*
 * createTime:2017-12-5 15:34
 * 选课中心的首页,显示选课活动信息
 */
@Controller
@RequestMapping("stu/courseMange")
public class AddCourseCenterIndexHandler {
	
	@Autowired
	public ActivityService activityService;
	
	//显示该页面
	@RequestMapping("/addCourseCenterIndex.do")
	public String addCourseCenterIndex() {
		System.out.println("到了选课中心入口的方法............");
		return "stu_pages/courseMange/addCourseCenterIndex";
	}
	
	/*
	 * createTime:2017-12-5 15:50
	 * 获取表格数据
	 */
	@RequestMapping("/activityRes.json")
	@ResponseBody
	public Map<String, Object> getActivityRes(
			@RequestParam(value="pageSize", required=false, defaultValue="8") Integer pageSize,
			@RequestParam(value="offset", required=false, defaultValue="0") Integer offset
			){
		System.out.println("pageSize: " + pageSize);
		System.out.println("offset: " + offset);
		
		//处理前端的请求参数
		Integer pageNumber = offset / pageSize + 1;
		
		//封装mapper的查询参数
		Map<String, Object> params = new HashMap<String, Object>();
		
		//获取结果
		Map<String, Object> result = null;
		try {
			 result = activityService.studentGetActivity(params, pageSize, pageNumber);
		}catch(Exception exception) {
			System.out.println("获取activity数据出错了-------------------");
			exception.printStackTrace();
		}
		return result;
	}
	
	/*
	 * createTime:2017-12-5 16:26
	 * 点击进入选课, ajax判断是否能进入选课
	 * 理论上直接输入地址就可以绕过这一步，从而到选课页面，但即使进了选课页面，不符合选课条件
	 * 还是无法选自己不能选的课，因为选课的方法中有控制(在选课的存储过程中控制)
	 */
	@RequestMapping("/into.do")
	public void intoAddCoursePage(
		@RequestParam(value="activityId", required=true) String activityId,
		HttpServletResponse response
			) {
		System.out.println("到了进入选课的页面...............");
		//获取studentId
		User user = (User)
				SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		String studentId = user.getId();
		
		//判断是否有权限进入选课
		String message = "";
		try {
			Activity activity = 
					activityService.checkIfCanIntoActivity(activityId, studentId);
			message = "success";
			//将activity放到session中，方便查询的时候使用
			SecurityUtils.getSubject().getSession().setAttribute("activity", activity);
		}catch(CannotIntoException cie) {
			System.out.println(cie.getMessage());
			message = "failed";
		}
		catch(Exception exception) {
			message = "wrong";
			exception.printStackTrace();
		}
		
		//ajax返回数据
		Writer writer = null;
		try {
			writer = response.getWriter();
			writer.write(message);
			writer.flush();
			writer.close();
		}catch(Exception exception){
			exception.printStackTrace();
		}
	}
}
