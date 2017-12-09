package com.fengxing.ems.Myutils;

import static org.hamcrest.CoreMatchers.either;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

import javafx.scene.input.TransferMode;
import net.sf.jsqlparser.util.AddAliasesVisitor;

/*
 * createTime：2017-12-8 13：20
 * 计算学期
 */
public class TermUtils {
	
	//获取一个以termString为开头的长度为length的terms数组
	public static List<Term> getTerms(String termString, int length){
		List<Term> terms = new ArrayList<Term>();
		Term term = getTerm(termString);
		terms.add(term);
		for(int i = 0; i < length; i++) {
			terms.add(terms.get(terms.size() - 1).lastTerm());
		}
		return terms;
	}
	
	//获取当前学期
	public static Term getCurrentTerm() {
		Calendar date = Calendar.getInstance();
		int year = date.get(Calendar.YEAR);
		int month = date.get(Calendar.MONTH);
		
		Term term = new Term();
		if(month > 7) {
			term.setStartYear(year);
			term.setEndYear(year + 1);
			term.setTerm(1);
		}
		else {
			term.setStartYear(year - 1);
			term.setEndYear(year);
			term.setTerm(2);
		}
		return term;
	}
	

	
	//将string转化为term
	public static Term getTerm(String termString) {
		String[] items = termString.split("-");
		Term term = new Term();
		term.setStartYear(Integer.valueOf(items[0]));
		term.setEndYear(Integer.valueOf(items[1]));
		term.setTerm(Integer.valueOf(items[2]));
		return term;
	}
	
	public static class Term{
		private int startYear;
		private int endYear;
		private int term;
		public int getStartYear() {
			return startYear;
		}
		public void setStartYear(int startYear) {
			this.startYear = startYear;
		}
		public int getEndYear() {
			return endYear;
		}
		public void setEndYear(int endYear) {
			this.endYear = endYear;
		}
		public int getTerm() {
			return term;
		}
		public void setTerm(int term) {
			this.term = term;
		}
		
		@Override
		public String toString() {
			return getStartYear() + "-" + getEndYear() + "-" + getTerm();
		}
		
		//获取下一个term
		public  Term nextTerm() {
			Term term = this;
			Term result = new Term();
			if(term.getTerm() == 1) {
				result.setStartYear(term.getStartYear());
				result.setEndYear(term.getEndYear());
				result.setTerm(2);
			}
			else {
				result.setStartYear(term.getStartYear() + 1);
				result.setEndYear(term.getEndYear() + 1);
				result.setTerm(1);
			}
			return result;
		}
		
		//获取上一个term
		public Term lastTerm() {
			Term term = this;
			Term result = new Term();
			if(term.getTerm() == 1) {
				result.setStartYear(term.getStartYear() - 1);
				result.setEndYear(term.getEndYear() - 1);
				result.setTerm(2);
			}
			else {
				result.setStartYear(term.getStartYear());
				result.setEndYear(term.getEndYear());
				result.setTerm(1);
			}
			return result;
		}
	}
}
