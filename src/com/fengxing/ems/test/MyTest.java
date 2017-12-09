package com.fengxing.ems.test;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.fengxing.ems.Myutils.CourseTimeUtils;
import com.fengxing.ems.Myutils.TermUtils;
import com.fengxing.ems.Myutils.TermUtils.Term;
import com.fengxing.ems.service.serviceImpl.CourseServiceImpl;
import com.fengxing.ems.shiro.ShiroUtils;
import com.sun.org.apache.bcel.internal.generic.NEW;

class MyTest {
	
	@Test
	public void test() {
		String salt = "1111";
		String password = "1111";
		System.out.println(ShiroUtils.encode("md5", 1024, password, salt).toString());
	}
	
	/*
	 * createTime:2017-12-6 10:40
	 * 测试日期解析器
	 */
	public void test1() {
		String time = "1-12week|mon-1;wed-2";
		try {
			CourseTimeUtils.CourseTime courseTime = CourseTimeUtils.resolve(time);
			System.out.println(courseTime);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/*
	 * createTime: 2017-12-6 14:40
	 */
	public void test2() {
		Integer day = 0;
		Integer section = 1;
		System.out.println(CourseTimeUtils.getRegexForQueryCourseTime(day, section));
	}
	
	public void test5() {
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			System.out.println(map.get("ssdsd"));
		}catch(Exception exception) {
			exception.printStackTrace();
		}
	}
	
}
