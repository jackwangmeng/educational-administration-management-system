package com.fengxing.ems.handler.student.courseScore;
/*
 * createTime: 2017-12-7 19:58
 * 查成绩
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.ErrorData;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fengxing.ems.Myutils.TermUtils;
import com.fengxing.ems.Myutils.TermUtils.Term;
import com.fengxing.ems.entity.User;
import com.fengxing.ems.service.serviceInterface.CourseKindService;
import com.fengxing.ems.service.serviceInterface.ScoreService;

@Controller
@RequestMapping("stu/courseScore")
public class ShowScoreHandler {
	
	@Autowired
	private ScoreService scoreService;
	
	@Autowired
	private CourseKindService courseKindService;
	
	/*
	 * createTime: 2017-12-7 19:59
	 * 映射到查成绩的页面
	 */
	@RequestMapping("/courseScoreList.do")
	public String showScorePage(HttpServletRequest request) {
		try {
			//获取学期信息
			Term term = TermUtils.getCurrentTerm().nextTerm().nextTerm();
			List<Term> terms = TermUtils.getTerms(term.toString(), 12);
			
			request.setAttribute("terms", terms);
			request.setAttribute("courseKinds", courseKindService.getAllCourseKind());
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("到了查成绩的方法");
		return "stu_pages/courseScore/courseScoreList";
	}
	
	/*
	 * createTIme: 2017-12-7 20:08
	 * 加载成绩的表格资源
	 */
	@RequestMapping("scoreRes.json")
	@ResponseBody
	public Map<String, Object> getScoreRes(
		@RequestParam(value="pageSize", required=false, defaultValue="8") Integer pageSize,
		@RequestParam(value="offset", required=false, defaultValue="0") Integer offset,
		
		@RequestParam(value="courseName", required=false) String courseName,
		@RequestParam(value="courseKind", required=false) String courseKind,
		@RequestParam(value="courseTerm", required=false) String courseTerm
			){
		System.out.println("到了加载成绩资源的方法");
		System.out.println("courseName: " + courseName);
		System.out.println("courseKind: " + courseKind);
		System.out.println("courseTerm: " + courseTerm);
		
		//计算分页参数
		Integer pageNumber = offset / pageSize + 1;
		
		//封装service的参数
		Map<String, Object> params = new HashMap<String, Object>();
		User user = (User)
				SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		params.put("studentId", user.getId());
		
		params.put("courseName", courseName);
		params.put("courseKind", courseKind);
		params.put("courseTerm", courseTerm);
		
		//获取数据
		Map<String, Object> result = null;
		try {
			result = scoreService.studentGetScore(params, pageSize, pageNumber);
		}catch(Exception exception) {
			exception.printStackTrace();
		}
		return result;
	}
}
