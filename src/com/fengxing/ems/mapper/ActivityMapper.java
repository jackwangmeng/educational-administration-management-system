package com.fengxing.ems.mapper;
/*
 * createTime:2017-12-4 18:42
 */

import java.util.List;
import java.util.Map;

import com.fengxing.ems.entity.Activity;

public interface ActivityMapper {
	public void insert(Activity activity);
	
	public List<Activity> select(Map<String, Object> params);
}
