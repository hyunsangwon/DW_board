package com.dw.board.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardRestController {
	
	@GetMapping("/")
	public String callHelloWorld() {
		return "Hello World!";
	}
}
