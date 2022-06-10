package com.dw.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentsController {

	@GetMapping("/students")
	public String loadStudentsPage() {
		return "students";
	}
}
