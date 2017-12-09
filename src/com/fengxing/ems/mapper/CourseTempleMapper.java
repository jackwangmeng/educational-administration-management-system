package com.fengxing.ems.mapper;

import java.util.List;
import java.util.Map;

import com.fengxing.ems.entity.CourseTemple;

/*
 * createTime:2017-12-4 12:00
 */
public interface CourseTempleMapper {
	public List<CourseTemple> select(Map<String, Object> params);
	
	public void insert(CourseTemple courseTemple);
}
