package com.dw.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String callLoginPage() {
		return "login";
	}
	
	@GetMapping("/join")
	public String callJoinPage() {
		return "join";
	}
	
	@GetMapping("/logout")
	public String callLoginout(HttpSession httpSession) {
		//세션 remove
		httpSession.removeAttribute("studentsId");
		httpSession.removeAttribute("studentsName");
		return "login";
	}
	
}
