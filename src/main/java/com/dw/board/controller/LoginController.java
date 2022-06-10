package com.dw.board.controller;

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
	
}
