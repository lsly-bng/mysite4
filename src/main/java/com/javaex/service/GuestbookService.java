package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Service
public class GuestbookService {

	// field
	@Autowired
	private GuestbookDao gbDao;

	// constructor
	// method - g/s
	// method - general

	// addList (등록폼)
	public List<GuestbookVo> getGbList() {

		List<GuestbookVo> gbList = gbDao.getGbList();

		return gbList;
	}

	// add (등록)
	public int gbInsert(GuestbookVo gbVo) {

		int count = gbDao.gbInsert(gbVo);

		return count;
	}

	// deleteForm (삭제폼)
	public int gbDelete(GuestbookVo gbVo) {

		int count = gbDao.gbDelete(gbVo);

		return count;
	}
}
