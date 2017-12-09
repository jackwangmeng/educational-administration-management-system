package com.fengxing.ems.Myutils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.text.AbstractDocument.Content;

import com.fengxing.ems.Myutils.CourseTimeUtils.CourseTime;
import com.fengxing.ems.Myutils.CourseTimeUtils.ExactTime;
import com.fengxing.ems.entity.Score;

/*
 * createTime: 2017-12-8 16:58
 */
public class ScheduleUtils {
	
	/*
	 * createTime: 2017-12-8 16:58 学生课表 将数据库查出来的score数据转换为前端表格能识别的数据结构
	 */
	public static List<Map<String, Object>> convertToStudentSchedule(List<Score> scores) 
			throws Exception{
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		//放入第一列
		for (int i = 0; i < CourseTimeUtils.SECTIONS_PLUS.size(); i++) {
			Map<String, Object> tmMap = new HashMap<String, Object>();
			tmMap.put("time", CourseTimeUtils.SECTIONS_PLUS.get(i));
			list.add(tmMap);
		}
		
		for(Score score : scores) {
			CourseTime courseTime = CourseTimeUtils.resolve(score.getCourse().getTime());
			for(ExactTime exactTime : courseTime.getExactTimes()) {
				//表格的列索引
				String day = CourseTimeUtils.DAYS.get(exactTime.getDay());
				//表格的行索引
				Integer section = exactTime.getSection();
				//单个单元格显示的内容
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(score.getCourse().getCourseTemple().getName());
				stringBuffer.append("<br>");
				stringBuffer.append(score.getCourse().getTeacher().getName());
				stringBuffer.append("<br>");
				stringBuffer.append(courseTime.getWeeks().getStartWeek());
				stringBuffer.append("-");
				stringBuffer.append(courseTime.getWeeks().getEndWeek());
				stringBuffer.append("周");
				stringBuffer.append("<br>");
				stringBuffer.append(score.getCourse().getRoom());
				
				System.out.println("content: " + stringBuffer);
				
				
				list.get(section).put(day, stringBuffer.toString());
			}
		}
		return list;
	}
}
