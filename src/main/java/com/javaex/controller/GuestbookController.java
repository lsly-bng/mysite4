package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping(value = "/guest")
public class GuestbookController {

	// field
	@Autowired
	private GuestbookService gbService;
	// constructor
	// method - g/s
	// method - general

	// addList (등록폼)
	@RequestMapping(value = "/addList", method = { RequestMethod.GET, RequestMethod.POST })
	public String addList(Model model) {
		System.out.println("GuestbookController>addList()");

		List<GuestbookVo> gbList = gbService.getGbList();

		// DispatcherServlet에 데이터를 보냄
		model.addAttribute("gbList", gbList);

		return "guestbook/addList";
	}

	// add (등록)
	@RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST })
	public String add(@ModelAttribute GuestbookVo gbVo) {
		System.out.println("GuestbookController>add()");

		gbService.gbInsert(gbVo);

		return "redirect:/guest/addList";
	}

	// deleteForm (삭제폼)
	@RequestMapping(value = "/deleteForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String deleteForm() {
		System.out.println("GuestbookController>deleteForm()");

		return "guestbook/deleteForm";
	}

	// delete (삭제)
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@ModelAttribute GuestbookVo gbVo) {
		System.out.println("GuestbookController>delete()");

		gbService.gbDelete(gbVo);

		return "redirect:/guest/addList";
	}

}
