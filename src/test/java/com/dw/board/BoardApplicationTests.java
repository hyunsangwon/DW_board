package com.dw.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.dw.board.mapper.StudentsMapper;
import com.dw.board.vo.StudentsVO;

@SpringBootTest
class BoardApplicationTests {

	@Autowired
	private PasswordEncoder passwordEncoder; 
	@Autowired
	private StudentsMapper studentsMapper;
	
	@Test
	void contextLoads() {
		String inputUserId = "apple";
		String inputPassword = "8080";
		
		if(inputUserId != null || inputPassword != null) System.out.println("값 입력");
		
		StudentsVO vo = new StudentsVO();
		vo.setStudentsName(inputUserId);
		vo.setStudentsPassword(inputPassword);
		vo = studentsMapper.selectStudentsOne(vo);
		
		if(vo != null) System.out.println("없는 회원");
		
		String encodePassword = passwordEncoder.encode(inputPassword);
		if(!passwordEncoder.matches(encodePassword, inputPassword)) {
			System.out.println("비밀번호가 다름 입력");
		}
		
	}

}
