package com.fengxing.ems.Myutils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.fengxing.ems.Exception.CourseTimeResolveException;

public class CourseTimeUtils {
	public static final List<String> DAYS = new ArrayList<>();
	public static final List<String> CHINESE_DAYS = new ArrayList<>();
	public static final List<String> SECTIONS = new ArrayList<>();
	public static final List<String> SECTIONS_PLUS = new ArrayList<>();
	
	static {
		 String[] days = {"mon", "tues", "wed", "thur", "fri", "sat", "sun"};
		 String[] chineseDays = {"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期天"};
		 String[] sections = {"1-2节", "3-5节", "6-7节", "8-10节", "11-12节"};
		 String[] sectionsPlus = {"1-2节(7:50~9:25)", "3-5节(9:45~12:10)", "6-7节(13:50~15:25)",
				 "8-10节(15:45~18:10)", "11-12节(19:00~21:25)"};
		 for(String item : days) {
			 DAYS.add(item);
		 }
		 
		 for(String item : sections) {
			 SECTIONS.add(item);
		 }
		 
		 for(String item : chineseDays) {
			 CHINESE_DAYS.add(item);
		 }
		 
		 for(String item : sectionsPlus) {
			 SECTIONS_PLUS.add(item);
		 }
	}
	
	
	public static class CourseTime{
		private Weeks weeks;
		private List<ExactTime> exactTimes;
		public Weeks getWeeks() {
			return weeks;
		}
		public void setWeeks(Weeks weeks) {
			this.weeks = weeks;
		}
		public List<ExactTime> getExactTimes() {
			return exactTimes;
		}
		public void setExactTimes(List<ExactTime> exactTimes) {
			this.exactTimes = exactTimes;
		}
		@Override
		public String toString() {
			return "CourseTime [weeks=" + weeks + ", exactTimes=" + exactTimes + "]";
		}
	}
	
	private static CourseTime resolveCourseTime(String time) throws Exception {
		CourseTime courseTime = new CourseTime();
		String[] items = time.split("\\|");
		
		//解析周次
		Pattern pattern = Pattern.compile("([\\d]{1,2})-([\\d]{1,2})week");
		Matcher matcher = pattern.matcher(items[0]);
		matcher.find();
		
		Weeks weeks = new Weeks();
		weeks.setStartWeek(Integer.valueOf(matcher.group(1)));
		weeks.setEndWeek(Integer.valueOf(matcher.group(2)));
		
		//解析课程节数目
		List<ExactTime> exactTimes = new ArrayList<ExactTime>();
		String[] items1 = items[1].split(";");
		Pattern pattern2 = Pattern.compile("([\\D]{2,5})-(\\d)");
		for(String item : items1) {
			ExactTime exactTime = new ExactTime();
			Matcher matcher2 = pattern2.matcher(item);
			matcher2.find();
			
			exactTime.setDay(DAYS.indexOf(matcher2.group(1)));
			exactTime.setSection(Integer.valueOf(matcher2.group(2)) - 1);
			
			exactTimes.add(exactTime);
		}
		courseTime.setExactTimes(exactTimes);
		courseTime.setWeeks(weeks);
		
		return courseTime;
	}
	
	/*
	 * createTime:2017-12-6 10:45
	 * 将数据库中 1-12week|mon-1;wed-2 格式的时间转换为1-12周 星期一(1-2节) 星期三(3-5节)
	 */
	public static String convertDataBaseTimeToUITime(String time) throws Exception{
		//解析出日期
		CourseTimeUtils.CourseTime courseTime = CourseTimeUtils.resolve(time);
		
		//构造成  格式
		StringBuffer stringBuffer = new StringBuffer();
		
		//构造 1-12周
		stringBuffer.append(courseTime.getWeeks().getStartWeek());
		stringBuffer.append("-");
		stringBuffer.append(courseTime.getWeeks().getEndWeek());
		stringBuffer.append("周;");
		
		//构造 星期一(1-2节) 星期三(3-5节)
		for(CourseTimeUtils.ExactTime exactTime : courseTime.getExactTimes()) {
			stringBuffer.append(CourseTimeUtils.CHINESE_DAYS.get(exactTime.getDay()));
			stringBuffer.append("(");
			stringBuffer.append(CourseTimeUtils.SECTIONS.get(exactTime.getSection()));
			stringBuffer.append(")");
			stringBuffer.append(" ");
		}
		//删除最后一个字符
		stringBuffer.deleteCharAt(stringBuffer.length() - 1);
		return stringBuffer.toString();
	}
	
	/*
	 * createTime: 2017-12-6 14:28
	 * 构造查询上课时间的正则表达式
	 * 数据库时间格式：1-12week|mon-1;wed-2
	 */
	public static String getRegexForQueryCourseTime(Integer day, Integer section) {
		String regex = "";
		if(day != null && day != 0) {
			regex += CourseTimeUtils.DAYS.get(day - 1);
		}
		if(section != null && section != 0) {
			regex += "-" + section;
		}
		if(!regex.equals("")) {
			regex = "%" + regex + "%";
		}
		return regex;
	}
	
	public static CourseTime resolve(String time) throws  CourseTimeResolveException{
		CourseTime courseTime = null;
		try {
			courseTime = resolveCourseTime(time);
			
		}catch(Exception exception) {
			exception.printStackTrace();
			throw new CourseTimeResolveException(time + "  解析错误");
		}
		return courseTime;
	}
	
	public static class ExactTime{
		private int day;
		private int section;
		public int getDay() {
			return day;
		}
		public void setDay(int day) {
			this.day = day;
		}
		public int getSection() {
			return section;
		}
		public void setSection(int section) {
			this.section = section;
		}
		@Override
		public String toString() {
			return "ExactTime [day=" + day + ", section=" + section + "]";
		}
		
		/*
		 * createTime: 2017-12-7 10:57
		 * 将exactTime转化为数字格式 星期二 1-2节 变为 1.0
		 */
		public static List<String> convertToString(List<ExactTime> exactTimes){
			List<String> result = new ArrayList<String>();
			for(ExactTime exactTime : exactTimes) {
				String item = exactTime.getDay() + "" + exactTime.getSection();
				result.add(item);
			}
			return result;
		}
	}
	
	public static class Weeks{
		private int startWeek;
		private int endWeek;
		public int getStartWeek() {
			return startWeek;
		}
		public void setStartWeek(int startWeek) {
			this.startWeek = startWeek;
		}
		public int getEndWeek() {
			return endWeek;
		}
		public void setEndWeek(int endWeek) {
			this.endWeek = endWeek;
		}
		@Override
		public String toString() {
			return "Weeks [startWeek=" + startWeek + ", endWeek=" + endWeek + "]";
		}
	}
}
