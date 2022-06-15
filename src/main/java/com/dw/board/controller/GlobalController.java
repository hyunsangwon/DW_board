package com.dw.board.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GlobalController implements ErrorController{

	@GetMapping("/error")
	public String handleError(ModelMap model, HttpServletRequest request) {
		String status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE).toString();
		if(status.equals("404")) {
			return "error/error404";
		}
		if(status.equals("500")) {
			return "error/error500";
		}
		return null;
	}
	
	
}
