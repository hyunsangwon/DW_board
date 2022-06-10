package com.dw.board.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dw.board.mapper.StudentsMapper;
import com.dw.board.vo.StudentsVO;
import com.github.pagehelper.PageHelper;

@Service
public class StudentsService {

	@Autowired
	private StudentsMapper studentsMapper;
	@Autowired
	private PasswordEncoder passwordEncoder; 
	
	//학생 저장
	public int setStudents(StudentsVO vo) {
		//학생 비밀번호 암호화 
		String password = vo.getStudentsPassword();
		password = passwordEncoder.encode(password);
		vo.setStudentsPassword(password);
		return studentsMapper.insertStudents(vo);
	}
	
	//학생 전체 조회
	public List<StudentsVO> getAllStudentsList(){
		return studentsMapper.selectAllStudentsList();
	}
	
	//학생 전체 조회 (Map)
	public List<Map<String, Object>> getAllStudentsListByMap(int pageNum, int pageSize){
		PageHelper.startPage(pageNum, pageSize); 
		return studentsMapper.selectAllStudentsListByMap();
	}
	
	//특정 학생 조회
	public StudentsVO getStudents(int studentsId) {
		return studentsMapper.selectStudents(studentsId);
	}
	
	//특정 학생 제거
	@Transactional(rollbackFor = Exception.class)
	public int getRemoveStudents(int studentsId) {
		return studentsMapper.deleteStudents(studentsId);
	}
	
	//특정 학생 수정
	@Transactional(rollbackFor = Exception.class)
	public int getUpdateStudents(StudentsVO vo, int studentsId) {
		vo.setStudentsId(studentsId);
		String password = vo.getStudentsPassword();
		password = passwordEncoder.encode(password);
		vo.setStudentsPassword(password);
		return studentsMapper.updateStudents(vo);
	}
	
	//가입된 학생인지 아닌지 여부 체크
	public boolean isStudents(StudentsVO vo, HttpSession httpSession) {
		
		StudentsVO student = studentsMapper.selectStudentsOne(vo);
		if(student == null) { //쿼리결과가 null 리턴
			return false;
		}
		String inputPassword = vo.getStudentsPassword(); //HTML에서 받아온 비밀번호
		String password = student.getStudentsPassword(); //DB에서 가져온 비빌번호
		
		if(!passwordEncoder.matches(inputPassword, password)) { //비밀번호 체크
			return false;
		}
		httpSession.setAttribute("studentsId", student.getStudentsId());
		httpSession.setAttribute("studentsName", student.getStudentsName());
		
		return true;
	}
	
}
