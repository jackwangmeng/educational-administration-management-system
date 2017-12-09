package com.fengxing.ems.service.serviceImpl;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fengxing.ems.entity.Student;
import com.fengxing.ems.entity.User;
import com.fengxing.ems.mapper.StudentMapper;
import com.fengxing.ems.service.serviceInterface.LoginService;
import com.fengxing.ems.service.serviceInterface.StudentService;
import com.fengxing.ems.shiro.ShiroUtils;

/*
 * createTime: 2017-12-19:45
 */
@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	private StudentMapper studentMapper;
	
	@Autowired
	private LoginService loginService;
	
	@Override
	public Student getStudent(String studentId) throws Exception{
		//调用mapper获取数据
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", studentId);
		
		Student student =  studentMapper.selectAllInfo(params).get(0);
		System.out.println("student: " + student);
		return student;
	}

	/*
	 * createTime: 2017-12-9 10:10
	 * 更新学生的个人信息 家庭住址，电话号码，邮箱
	 */
	@Override
	@Transactional //spring事务处理
	public void updatePersonalInfo(Map<String, Object> params)throws Exception{
		studentMapper.updatePersonalInfo(params);
	}

	/*
	 * createTime:2017-12-9 13:31
	 * 修改学生密码
	 */
	@Override
	@Transactional
	public void changePassword(String studentId, String oldPassword, 
			String newPassword)throws Exception {
		//验证旧密码是否正确
		loginService.vertify(studentId, oldPassword, User.STUDENT, false);
		
		//更新密码
		//(1).将密码加密
		String encodedPassword = 
				ShiroUtils.encode("md5", 1024, newPassword, studentId);
		//(2).持久化到数据库
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", studentId);
		params.put("password", encodedPassword);
		studentMapper.updatePassword(params);
	}
}
