package com.fengxing.ems.mapper;


import java.util.Map;

import com.fengxing.ems.entity.User;

public interface UserMapper {
	public User getUser(Map<String, Object> params);
}
