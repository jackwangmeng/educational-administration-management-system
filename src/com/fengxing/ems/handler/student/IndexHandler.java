package com.fengxing.ems.handler.student;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * createTime:2017-12-4 11:50
 */
@Controller
@RequestMapping("/stu")
public class IndexHandler {
	/*
	 * createTime:2017-12-4 11:52
	 * 跳转到学生的首页
	 */
	@RequestMapping("/indexPage.do")
	public String indexPage() {
		return "stu_pages/index";
	}  
}
