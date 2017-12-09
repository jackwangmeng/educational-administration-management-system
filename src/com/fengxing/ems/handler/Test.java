package com.fengxing.ems.handler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fengxing.ems.Myutils.DateFormatUtils;
import com.fengxing.ems.entity.Activity;
import com.fengxing.ems.entity.Course;
import com.fengxing.ems.entity.CourseTemple;
import com.fengxing.ems.entity.Major;
import com.fengxing.ems.entity.Student;
import com.fengxing.ems.entity.Teacher;
import com.fengxing.ems.mapper.ActivityMapper;
import com.fengxing.ems.mapper.CourseMapper;
import com.fengxing.ems.mapper.CourseTempleMapper;
import com.fengxing.ems.mapper.StudentMapper;
import com.fengxing.ems.mapper.TeacherMapper;

@Controller
public class Test {
	
	@Autowired
	CourseMapper mapper;
	
	@Autowired
	TeacherMapper teacherMapper;
	
	@Autowired
	ActivityMapper activityMapper;
	
	@Autowired
	CourseTempleMapper courseTempleMapper;
	
	@Autowired
	StudentMapper studentMapper;
	
	
	//添加课程
	@RequestMapping("/test")
	public void test1(
		 ) {
		System.out.println("来到了测试插入课程的方法....................");
		Course course = new Course();
		
		course.setCourseTemple(new CourseTemple("1"));
		course.setTeacher(new Teacher("1111"));
		course.setMaxCount(150);
		course.setRoom("主教203");
		course.setTime("1-12week|mon-3;fri-1");
		course.setTerm("2015-2016-2");
		course.setStatus(Course.END);
		
		mapper.insert(course);
		System.out.println("插入后的数据：" + course);
	}
	
	
	public void test(
		 ) {
		System.out.println("来到了正在测试的方法#######################");
		Activity activity = new Activity();
		
		DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
		activity.setStartTime("2017-01-1 16:00:00");
		activity.setEndTime("2018-01-10 16:00:00");
		activity.setActive(true);
//		activity.setTargetCollege("");
//		activity.setTargetGrade();
//		activity.setTargetMajorName("");
		activity.setExtra("2016二类通识课选课");
		activity.setActive(false);
		activityMapper.insert(activity);
		System.out.println("activity: " + activity.toString());
	}
	

	public void test2(
			@RequestParam(value="id") String id,
			@RequestParam(value="pwd") String pwd,
			@RequestParam(value="role") String role
		 ) {
		List<Course> courses = mapper.selectAllInfo(new HashMap<>());
		for(Course course : courses) {
			System.out.println("course: " + course.toString());
		}
	}
	
	//添加老师
	public void insertTeacher() {
		Teacher teacher = new Teacher("1119");
		teacher.setName("马特 达蒙");
		teacher.setMajor(new Major("表演"));
		teacher.setPassword("1111");
		teacher.setNumber("1111123424244");
		teacher.setEmail("matdameng@163.com");
		teacher.setPhone("121212-2323");
		teacher.setAdress("南昌大学教师公寓");
		teacher.setPro("教授");
		
		teacherMapper.insert(teacher);
		System.out.println(teacher);
	}
	
	//添加课程模板
	public void insertCourseTemple() {
		CourseTemple courseTemple = new CourseTemple();
		courseTemple.setName("唐诗鉴赏");
		courseTemple.setHour(32);
		courseTemple.setPoint(2);
		courseTemple.setOpenCollege("人文学院");
		courseTemple.setCourseKind("二类通识课");
		
		courseTempleMapper.insert(courseTemple);
		System.out.println(courseTemple);
	}
	
	//查询学生信息
	public void selectStudent() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", "5902414061");
		Student student = studentMapper.selectPublicInfo(params).get(0);
		System.out.println("student: " + student);
	}
}
