package com.dw.board.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dw.board.service.LogsService;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/api/v1") 
public class LogsRestController {

	@Autowired
	private LogsService logsService;
	
	//http://localhost:8080/api/v1/logs?pageNum=1&pageSize=10
	@CrossOrigin
	@GetMapping("/logs")
	public PageInfo<Map<String, Object>> getLogsList(@RequestParam("pageNum") int pageNum, 
			@RequestParam("pageSize") int pageSize){
		
		List<Map<String, Object>> list = logsService.getLogsList(pageNum, pageSize);
		
		return new PageInfo<Map<String, Object>>(list);
	}
	
	@CrossOrigin
	@GetMapping("/logs/logId/{logId}")
	public Map<String, Object> callLogs(@PathVariable("logId") int logId) {
		return logsService.getLogs(logId);
	}
	
}
