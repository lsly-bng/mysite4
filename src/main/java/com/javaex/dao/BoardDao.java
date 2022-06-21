package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;

	// 게시판 리스트
	public List<BoardVo> getList() {
		System.out.println("BoardDao>getList()");

		List<BoardVo> brdList = sqlSession.selectList("board.selectList");

		return brdList;
	}

	// 게시판 글 1개 불러오기
	public BoardVo read(int no) {
		System.out.println("BoardDao>read()");

		BoardVo bVo = sqlSession.selectOne("board.selectOne", no);

		return bVo;
	}

	// 게시판 글쓰기 메소드
	public int write(BoardVo bVo) {
		System.out.println("BoardDao>write()");

		int count = sqlSession.insert("board.insert", bVo);

		return count;
	}

	// 게시판 글 삭제 메소드
	public int delete(int no) {
		System.out.println("BoardDao>delete()");

		int count = sqlSession.delete("board.delete", no);

		return count;
	}

	// 게시판 글 수정 메소드
	public int modify(BoardVo bVo) {
		System.out.println("BoardDao>modify()");

		int count = sqlSession.update("board.update", bVo);

		return count;
	}

	// 조회수 업데이트 메소드
	public int updateHit(int no) {
		System.out.println("BoardDao>updateHit()");

		int count = sqlSession.update("board.updateHit", no);

		return count;
	}

}
