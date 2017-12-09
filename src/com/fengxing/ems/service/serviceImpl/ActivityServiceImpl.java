package com.fengxing.ems.service.serviceImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fengxing.ems.Exception.CannotIntoException;
import com.fengxing.ems.Myutils.DateFormatUtils;
import com.fengxing.ems.entity.Activity;
import com.fengxing.ems.entity.Student;
import com.fengxing.ems.mapper.ActivityMapper;
import com.fengxing.ems.mapper.StudentMapper;
import com.fengxing.ems.service.serviceInterface.ActivityService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
/*
 * createTime:2017-12-5 15:47
 */
@Service
public class ActivityServiceImpl implements ActivityService {
	
	@Autowired
	private ActivityMapper activityMapper;
	
	@Autowired
	private StudentMapper studentMapper;

	/*
	 * createTime:2017-12-5 15:50
	 * 学生获取选课活动的方法
	 */
	@Override
	public Map<String, Object> studentGetActivity
		(Map<String, Object> params, int pageSize, int pageNumber) throws Exception{
		//开始分页
		Page<Object> pages = PageHelper.startPage(pageNumber, pageSize);
		
		//封装mapper的请求参数
		params.put("active", true);
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		String dateString = format.format(date);
		params.put("currentTime", dateString);//传入当前时间
		
		//查询数据
		List<Activity> activities = activityMapper.select(params);
		
		//封装结果
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("res", activities);
		result.put("total", pages.getTotal());
		
		return result;
	}

	/*
	 * createTime:16:39
	 * 判断是否有权限参与选课活动，没用权限的话抛出CannotIntiException异常
	 */
	@Override
	public Activity checkIfCanIntoActivity(String activityId, String studentId) 
			throws Exception {
		//查出学生的信息
		Map<String, Object> studentParams = new HashMap<String, Object>();
		studentParams.put("id", studentId);
		Student student = studentMapper.selectPublicInfo(studentParams).get(0);
		System.out.println("student: " + student);
		
		//查出activity的详细信息
		Map<String, Object> activityParams = new HashMap<String, Object>();
		activityParams.put("id", activityId);
		Activity activity = activityMapper.select(activityParams).get(0);
		System.out.println("activity: " + activity);
		
		//判断是否有权限进入选课
		//(1).判断选课活动是否激活
		Boolean activie = activity.getActive();
		if(null == activie || activie.equals(false)) {
			throw new CannotIntoException("选课活动未被激活");
		}
		
		//(2).时间是否过期,选课活动是否开始
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date endTime = format.parse(activity.getEndTime());
		Date startTIme = format.parse(activity.getStartTime());
		
		if(System.currentTimeMillis() > endTime.getTime()) {
			throw new CannotIntoException("选课时间过期");
		}
		
		if(System.currentTimeMillis() < startTIme.getTime()) {
			throw new CannotIntoException("选课时间还没开始");
		}
		
		
		//(3).是否有权限
		String studentMajor = student.getCls().getMajor().getName();
		String studentCollege = student.getCls().getMajor().getCollege();
		Integer studentGrade = student.getCls().getGrade();
		
		String activityMajor = activity.getTargetMajorName();
		String activityCollege = activity.getTargetCollege();
		Integer activityGrade = activity.getTargetGrade();
		
		if(activityMajor != null && activityMajor != "") {
			if(!activityMajor.equals(studentMajor)) {
				throw new CannotIntoException("专业不匹配！");
			}
		}
		if(activityCollege != null && activityCollege != "") {
			if(!activityCollege.equals(studentCollege)) {
				throw new CannotIntoException("学院不匹配！");
			}
		}
		if(activityGrade != null && activityGrade != 0) {
			if(!activityGrade.equals(studentGrade)) {
				throw new CannotIntoException("年级不匹配！");
			}
		}
		return activity;
	}

}
