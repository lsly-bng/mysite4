package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {

	@Autowired
	private SqlSession sqlSession;

	// 방명록 전체 가져오기
	public List<GuestbookVo> getGbList() {
		System.out.println("GuestbookDao>getGbList()");

		List<GuestbookVo> gbList = sqlSession.selectList("guestbook.selectList");

		return gbList;
	}

	// 방명록 저장하기
	public int gbInsert(GuestbookVo gbVo) {
		System.out.println("GuestbookDao>gbInsert()");

		int count = sqlSession.insert("guestbook.gbInsert", gbVo);

		return count;
	}

	// 방명록 삭제
	public int gbDelete(GuestbookVo gbVo) {
		System.out.println("GuestbookDao>gbDelete()");

		int count = sqlSession.delete("guestbook.gbDelete", gbVo);

		return count;
	}
}
