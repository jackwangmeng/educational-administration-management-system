package com.fengxing.ems.mapper;

import java.util.List;
import java.util.Map;

import com.fengxing.ems.entity.Course;
import com.fengxing.ems.entity.Score;

/*
 * createTime:2017-12-7 9:03
 */
public interface ScoreMapper {
	//选课
	public void studentAddCourse(Map<String, Object> params)throws Exception;
	
	//退选
	public void studentDeleteCourse(Map<String, Object> params)throws Exception;
	
	//给选课判断是否冲突的时候用,只查出了课程时间，课程学期
	public List<Score> selectForJudgeCourseConflict(Map<String, Object> params)throws Exception;
	
	//显示选过的课程
	public List<Score> studentGetAddedCourse(Map<String, Object> params)throws Exception;
	
	//学生查成绩
	public List<Score> studentGetScore(Map<String, Object> params)throws Exception;
	
	//学生查询课表
	public List<Score> studentGetSchedule(Map<String, Object> params)throws Exception;
	
}
