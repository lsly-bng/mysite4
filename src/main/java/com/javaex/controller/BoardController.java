package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/board")
public class BoardController {

	// field
	@Autowired
	private BoardService brdService;

	// constructor
	// method - g/s
	// method - general

	// 게시판 리스트 -> 전체
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String getList(Model model) {
		System.out.println("BoardController>getList()");

		List<BoardVo> brdList = brdService.getList();

		model.addAttribute("brdList", brdList);

		return "board/list";
	}

	// 게시판 글 1개 불러오기
	@RequestMapping(value = "/read", method = { RequestMethod.GET, RequestMethod.POST })
	public String read(@PathVariable("no") int no, Model model) {
		System.out.println("BoardController>read()");

		brdService.updateHit(no);

		BoardVo bVo = brdService.read(no);

		model.addAttribute("bVo", bVo);

		return "board/read";
	}

	// 글쓰기 폼 불러오기
	@RequestMapping(value = "/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm(@ModelAttribute BoardVo bVo, HttpSession session) {
		System.out.println("BoardController>writeForm()");

		// 세션
		UserVo authUser = (UserVo) session.getAttribute("authuser");

		if (authUser == null) {
			return "redirect:/user/loginForm";
		} else {
			return "board/writeForm";
		}
	}

	// 게시판 글쓰기 메소드
	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute BoardVo bVo, HttpSession session) {
		System.out.println("BoardController>write()");

		UserVo authUser = (UserVo) session.getAttribute("authUser");

		bVo.setUserNo(authUser.getNo());

		brdService.write(bVo);

		return "redirect:/board/list";
	}

	// 게시판 글 삭제
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@RequestParam("no") int no) {
		System.out.println("BoardController>delete()");

		brdService.delete(no);

		return "redirect:/board/list";
	}

	// 게시판 글 수정폼
	@RequestMapping(value = "/modifyForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(@ModelAttribute BoardVo bVo, @RequestParam("no") int no) {
		System.out.println("BoardController>modifyForm()");

		bVo = brdService.read(no);

		return "board/modifyForm";
	}

	// 게시판 글 수정
	@RequestMapping(value = "/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(@ModelAttribute BoardVo bVo, HttpSession session) {
		System.out.println("BoardController>modify()");

		UserVo authUser = (UserVo) session.getAttribute("authUser");

		bVo.setUserNo(authUser.getNo());

		brdService.modify(bVo);

		return "redirect:/board/list";
	}
}
