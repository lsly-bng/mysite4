package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	// field
	@Autowired
	private SqlSession sqlSession;
	// constructor
	// method - g/ss

	// 로그인 --> 회원정보 가져오기
	public UserVo getUser(UserVo uVo) {
		System.out.println("UserDao>getUser()");

		UserVo authUser = sqlSession.selectOne("user.getUser", uVo);

		return authUser;
	}

	// 회원가입 --> 회원정보 저장
	public int insert(UserVo uVo) {
		System.out.println("UserDao>insert()");

		int count = sqlSession.insert("user.insert", uVo);

		return count;
	}

	// 수정폼 --> 회원정보 가져오기
	public UserVo getInfo(int no) {
		System.out.println("UserDao>getInfo()");

		UserVo uVo = sqlSession.selectOne("user.getInfo", no);

		return uVo;
	}

	// 수정 --> 회원정보 수정
	public int modify (UserVo uVo) {
		System.out.println("UserDao>update()");

		int count = sqlSession.update("user.update", uVo);

		return count;
	}

}
