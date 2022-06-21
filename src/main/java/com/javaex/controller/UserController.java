package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
public class UserController {

	// field
	@Autowired
	private UserService userService;

	// constructor
	// method - g/s
	// method - general

	// join --> 회원가입
	@RequestMapping(value = "/user/join", method = { RequestMethod.GET, RequestMethod.POST })
	public String join(@ModelAttribute UserVo uVo) {
		System.out.println("UserController>join()");

		int count = userService.join(uVo);
		System.out.println("UserController:" + count);

		return "user/joinOk";
	}

	// joinForm --> 회원가입폼
	@RequestMapping(value = "/user/joinForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String joinForm() {
		System.out.println("UserController>joinForm");

		return "user/joinForm";
	}

	// loginForm --> 로그인폼
	@RequestMapping(value = "/user/loginForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String loginForm() {
		System.out.println("UserController>loginForm()");

		return "user/loginForm";
	}

	// login --> 로그인
	@RequestMapping(value = "/user/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(@ModelAttribute UserVo uVo, HttpSession session) {
		System.out.println("UserController>login()");

		UserVo authUser = userService.login(uVo);

		// 세션 저장
		if (authUser != null) {
			System.out.println("로그인 성공");

			session.setAttribute("authUser", authUser);

			return "redirect:/main";
		} else {
			System.out.println("로그인 실패");

			return "redirect:/user/loginForm?result=fail";
		}
	}

	// modifyForm --> 수정폼
	@RequestMapping(value = "/user/modifyForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyForm(Model model, HttpSession session) {
		System.out.println("UserController>modifyForm");

		// 세션에서 no 값 꺼내기
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		int no = authUser.getNo();

		// no 에 따른 회원정보 uVo에 담기
		UserVo uVo = userService.getInfo(no);

		// Attribute 설정하기
		model.addAttribute("uVo", uVo);

		return "user/modifyForm";
	}

	// modify --> 수정
	@RequestMapping(value = "/user/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(@ModelAttribute UserVo uVo, HttpSession session) {
		System.out.println("UserController>modify()");

		// 세션에서 no 값 꺼내오기
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		int no = authUser.getNo();

		// modify 메소드 불러와서 uVo 수정하기
		userService.modify(uVo);

		// 수정된 회원 이름 변경하기
		authUser.setName(uVo.getName());

		return "redirect:/main";
	}

	// logout --> 로그아웃
	@RequestMapping(value = "/user/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout(HttpSession session) {
		System.out.println("UserController>logout()");

		session.invalidate();

		return "redirect:/main";
	}

}
