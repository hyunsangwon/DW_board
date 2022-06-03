package com.dw.board.controller;

import java.util.List;
import java.util.Map;

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
	public String callBoardPage(ModelMap model,
			@RequestParam("pageNum") int pageNum, 
			@RequestParam("pageSize") int pageSize) {
		
		List<Map<String, Object>> list = boardService.getAllBoardList(pageNum, pageSize);
		model.addAttribute("pageHandler", new PageInfo<Map<String, Object>>(list));
		return "board";
	}
		
}
