package com.fengxing.ems.Myutils;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;


/*
 * createTime: 2017-12-4 19:01
 */


public class DateFormatUtils {
	public static Date getDate(DateFormat dateFormat, String dateString) {
		try {
			return dateFormat.parse(dateString);
		}catch(ParseException exception) {
			System.out.println("日期解析错误!!!!!!!!!!!!!!!!!!!!!!!!!!1");
			exception.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
