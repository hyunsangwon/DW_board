package com.dw.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dw.board.service.BoardService;
import com.github.pagehelper.PageInfo;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/home")
	public String callHomepage() {
		return "index";
	}
	
	//http://localhost:8080/board?pageNum=1&pageSize=10
	@GetMapping("/board")
	public String callBoardPage(ModelMap model,HttpSession httpSession,
			@RequestParam("pageNum") int pageNum, 
			@RequestParam("pageSize") int pageSize) {
		
		List<Map<String, Object>> list = boardService.getAllBoardList(pageNum, pageSize);
		model.addAttribute("pageHandler", new PageInfo<Map<String, Object>>(list));
		//세션 데이터 가져오기 (추후 로직구현 예정)
		int studentsId = (int) httpSession.getAttribute("studentsId");
		String studentsName = (String) httpSession.getAttribute("studentsName");
		model.addAttribute("studentsId", studentsId);
		model.addAttribute("studentsName", studentsName);
		return "board";
	}
		
}
