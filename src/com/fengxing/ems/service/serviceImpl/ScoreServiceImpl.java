package com.fengxing.ems.service.serviceImpl;

import static org.hamcrest.CoreMatchers.containsString;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fengxing.ems.Exception.CourseRepeatException;
import com.fengxing.ems.Exception.CourseTimeConflictException;
import com.fengxing.ems.Myutils.CourseTimeUtils;
import com.fengxing.ems.Myutils.CourseTimeUtils.CourseTime;
import com.fengxing.ems.Myutils.CourseTimeUtils.ExactTime;
import com.fengxing.ems.entity.Activity;
import com.fengxing.ems.entity.Course;
import com.fengxing.ems.entity.Score;
import com.fengxing.ems.mapper.CourseMapper;
import com.fengxing.ems.mapper.ScoreMapper;
import com.fengxing.ems.service.serviceInterface.ScoreService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
/*
 * createTime 2017-12-6 15:41
 */
import com.sun.org.apache.bcel.internal.generic.NEW;

@Service
public class ScoreServiceImpl implements ScoreService{
	
	@Autowired
	private CourseMapper courseMapper;
	
	@Autowired
	private ScoreMapper scoreMapper;
	
	/*
	 * createTime: 2017-12-6 15:41
	 * 学生选课
	 */
	@Override
	public void studentAddCourse(String activityId, String studentId, String courseId) 
			throws Exception {
		//判断是否重复选课，课程时间是否有冲突
		//(1).查出courseId的详细信息
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", courseId);
		Course course = courseMapper.selectWithoutAnyForeignKeyInfo(params).get(0);
		System.out.println("course: " + course.toString());
		
		//(2).查出该学生选过的和courseId课程的学期相同的课程
		Map<String, Object> params1 = new HashMap<String, Object>();
		params1.put("courseTerm", course.getTerm());
		params1.put("studentId", studentId);
		List<Score> scores = scoreMapper.selectForJudgeCourseConflict(params1);
		for(Score score : scores) {
			System.out.println("score: " + score.toString());
		}
		
		//(3).判断是否有冲突
		checkIfConflict(course, scores);
		
		//(4).开始选课
		Map<String, Object> params2 = new HashMap<String, Object>();
		params2.put("activityId", activityId);
		params2.put("studentId", studentId);
		params2.put("courseId",courseId);
		params2.put("message", new String());
		scoreMapper.studentAddCourse(params2);
		String message = (String)params2.get("message");
		System.out.println("调用存储过程返回的消息： " + message);
		if(!message.equals("success")) {
			throw new RuntimeException("选课失败！");
		}
	}
	
	
	/*
	 * createTime:2017-12-7 10:48
	 * 判断是否重复选课，课程时间是否有冲突
	 * 因为数据库中的时间格式为 1-12week|mon-1;wed-2 在存储过程中不好判断，所以只好在java代码中判断
	 */
	private static void checkIfConflict(Course course, List<Score> scores)
			throws Exception {
		//判断课程是否重复
		for(Score score : scores) {
			if(score.getCourse().getId().equals(course.getId())) {
				throw new CourseRepeatException("不能重复选课！");
			}
		}
		
		//判断课程时间是否冲突
		CourseTime targetCourseTime = CourseTimeUtils.resolve(course.getTime());
		List<String> exactTimes = ExactTime.convertToString(targetCourseTime.getExactTimes());
		for(Score score : scores) {
			CourseTime courseTime = CourseTimeUtils.resolve(score.getCourse().getTime());
			if(targetCourseTime.getWeeks().getStartWeek() > courseTime.getWeeks().getEndWeek()
				|| targetCourseTime.getWeeks().getEndWeek() < courseTime.getWeeks().getStartWeek()) {
				return;
			}
			List<String> list = ExactTime.convertToString(courseTime.getExactTimes());
			for(String item : list) {
				if(exactTimes.indexOf(item) != -1) {
					throw new CourseTimeConflictException
						("选课失败！与" + score.getCourse().getCourseTemple().getName() + "冲突");
				}
			}
		}
	}

	/*
	 * createTime:2017-12-7 11:52
	 * 学生查出来这个学期选过的课程
	 */
	@Override
	public Map<String, Object> studentGetAddedCourse(Map<String, Object> params,
				Integer pageSize, Integer pageNumber) throws Exception{
		//构造mapper的参数
		params.put("courseTerm", "2017-2018-2");
		
		//分页
		Page<Object> pages = PageHelper.startPage(pageNumber, pageSize);
		
		//查出数据
		List<Score> scores = scoreMapper.studentGetAddedCourse(params);
		for(Score score : scores) {
			System.out.println("score： " + score);
		}
		
		//转换时间格式
		for(Score score : scores) {
			score.getCourse().setTime(CourseTimeUtils.convertDataBaseTimeToUITime(
					score.getCourse().getTime()));
		}
		
		//封装结果
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("res", scores);
		result.put("total", pages.getTotal());
		
		return result;
	}


	/*
	 * createTime: 2017-12-7 16:17
	 * 学生退选
	 */
	@Override
	public void studentDeleteCourse(String studentId, String courseId) throws Exception {
		//查出退选课程的activity信息
		//(1).封装mapper的参数
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", courseId);
		
		//(2).查询数据
		Course course = courseMapper.selectOnlyActivityInfo(params).get(0);
		System.out.println("course: " + course.toString());
		
		//判断是否能进行退选
		checkIfAbleDeleteCourse(course.getActivity());
		
		//进行退选
		//(1)封装mapper的参数
		Map<String, Object> params1 = new HashMap<String,Object>();
		params1.put("studentId", studentId);
		params1.put("courseId", courseId);
		scoreMapper.studentDeleteCourse(params1);
		
		String message = (String)params1.get("message");
		System.out.println("message: " + message);
		if(!message.equals("success")) {
			throw new RuntimeException("调用存储过程退选失败！");
		}
	}
	
	/*
	 * createTime: 2017-12-7 16:56
	 * 根据activity 判断是否能进行退选操作
	 */
	private static void checkIfAbleDeleteCourse(Activity activity) throws Exception{
		if(activity == null) {
			throw new RuntimeException("无法退选，该课程无activity");
		}
		//判断活动是否被激活
		if(activity.getActive() == null || !activity.getActive()) {
			throw new RuntimeException("选课(退选)活动没用被激活!");
		}
		
		//判断有没有开始和过期
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date endTime = format.parse(activity.getEndTime());
		Date startTIme = format.parse(activity.getStartTime());
		
		if(System.currentTimeMillis() > endTime.getTime()) {
			throw new RuntimeException("退选过期了！");
		}
		if(System.currentTimeMillis() < startTIme.getTime()) {
			throw new RuntimeException("退选还没开始！");
		}
	}

	/*
	 * createTime: 2017-12-7 21:31
	 * 学生查成绩
	 */
	@Override
	public Map<String, Object> studentGetScore(Map<String, Object> params,
			Integer pageSize, Integer pageNumber)throws Exception {
		//开始分页
		Page<Object> pages = PageHelper.startPage(pageNumber, pageSize);
		
		//查成绩
		List<Score> scores = scoreMapper.studentGetScore(params);
		for(Score score : scores) {
			System.out.println("score: " + score);
		}
		//封装结果
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("res", scores);
		result.put("total", pages.getTotal());
		
		return result;
	}
}
