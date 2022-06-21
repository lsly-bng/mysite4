package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	// field
	@Autowired
	private BoardDao brdDao;

	// constructor
	// method - g/s
	// method - general

	// 게시판 리스트
	public List<BoardVo> getList() {

		List<BoardVo> brdList = brdDao.getList();

		return brdList;
	}

	// 게시글 1개 불러오기
	public BoardVo read(int no) {

		BoardVo bVo = brdDao.read(no);

		return bVo;
	}

	// 게시판 글쓰기 메소드
	public int write(BoardVo bVo) {
		System.out.println("BoardDao>write()");

		int count = brdDao.write(bVo);

		return count;
	}

	// 게시판 글 삭제 메소드
	public int delete(int no) {
		System.out.println("BoardDao>delete()");

		int count = brdDao.delete(no);

		return count;
	}

	// 게시판 글 수정 메소드
	public int modify(BoardVo bVo) {
		System.out.println("BoardDao>modify()");

		int count = brdDao.modify(bVo);

		return count;
	}

	// 조회수 업데이트 메소드
	public int updateHit(int no) {
		System.out.println("BoardDao>updateHit()");

		int count = brdDao.updateHit(no);

		return count;
	}
}
