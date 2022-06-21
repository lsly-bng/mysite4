package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	// field
	@Autowired
	private UserDao userDao;

	// constructor
	// method - g/s
	// method - general

	// 로그인
	public UserVo login(UserVo uVo) {
		System.out.println("UserService>login()");

		UserVo authUser = userDao.getUser(uVo);

		return authUser;
	}

	// 회원가입
	public int join(UserVo uVo) {
		System.out.println("UserService>join()");

		int count = userDao.insert(uVo);

		return count;
	}

	// 수정폼
	public UserVo getInfo(int no) {
		System.out.println("UserService>getInfo()");

		UserVo uVo = userDao.getInfo(no);

		return uVo;
	}

	// 수정
	public int modify(UserVo uVo) {
		System.out.println("UserService>modify()");

		int count = userDao.modify(uVo);

		return count;
	}

}
