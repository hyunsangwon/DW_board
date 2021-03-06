package com.dw.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dw.board.service.StudentsService;
import com.dw.board.vo.StudentsVO;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/api/v1") 
public class StudentsRestController {
	
		@Autowired
		private StudentsService studentsService;
	
		//중요한 정보를 서버에 전송할 때 post사용!
		@CrossOrigin
		@PostMapping("/login")
		public boolean callIsLogin(@RequestBody StudentsVO vo, HttpSession httpSession) {
			boolean isLogin = studentsService.isStudents(vo, httpSession);
			return isLogin;
		}
		
		//학생 저장
		//post는 body로 데이터를 받음 (보안)
		@CrossOrigin
		@PostMapping("/students")
		public int callSaveStudents(@RequestBody StudentsVO vo) {
			return studentsService.setStudents(vo);
		}
		
		//학생조회 (VO로 리턴해보기)
		@GetMapping("/students")
		public List<StudentsVO> callStudentsList(HttpSession httpSession){
			return studentsService.getAllStudentsList();
		}
		
		//학생 조회 (Map으로 리턴해보기)
		@GetMapping("/students/map")
		public PageInfo<Map<String, Object>> callStudentsListByMap(){
			int pageNum = 1; //현재 페이지
			int pageSize = 2; //한페이지에 게시물 몇개
			List<Map<String, Object>> list = studentsService.getAllStudentsListByMap(pageNum, pageSize);
			int navigatePages = 3; //한블록에 몇페이지
			return new PageInfo<Map<String, Object>>(list, navigatePages);
		}
		
		//특정 학생 조회(PK로 조회예정)
		@GetMapping("/students/id/{id}")
		public StudentsVO callStudents(@PathVariable("id") int studentsId) {
			return studentsService.getStudents(studentsId);
		}
		
		//특정 학생 삭제
		@DeleteMapping("/students/id/{id}")
		public int callRemoveStudents(@PathVariable("id") int studentsId) {
			return studentsService.getRemoveStudents(studentsId);
		}
		
		//특정 학생 수정
		@PatchMapping("/students/id/{id}")
		public int callUpdateStudents(@PathVariable("id") int studentsId,
				@RequestBody StudentsVO vo) {
			return studentsService.getUpdateStudents(vo, studentsId);
		}
		
		
}
