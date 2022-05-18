package com.dw.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dw.board.vo.StudentsVO;

@Mapper
public interface StudentsMapper {
	
	//1. 메소드를 클릭하고 
	//2. 단축키입력 : Alt + Shift + J
	/**
	 * @param vo
	 * @return
	 * @author : Sangwon Hyun
	 * @date : 2022. 5. 18.
	 * comment : 학생 저장
	 */
	public int insertStudents(StudentsVO vo);


	/**
	 * @return
	 * @author : Sangwon Hyun
	 * @date : 2022. 5. 18.
	 * comment : 학생 전체 조회
	 */
	public List<StudentsVO> selectAllStudentsList();
	
	
	/**
	 * @return
	 * @author : Sangwon Hyun
	 * @date : 2022. 5. 18.
	 * comment : 학생 전체 조회 (Map)
	 */
	public List<Map<String, Object>> selectAllStudentsListByMap();
	
	
	
	/**
	 * @param studentsId
	 * @return
	 * @author : Sangwon Hyun
	 * @date : 2022. 5. 18.
	 * comment : 특정 학생 조회
	 */
	public StudentsVO selectStudents(@Param("studentsId") int studentsId);
	
	
	/**
	 * @param studentsId
	 * @return
	 * @author : Sangwon Hyun
	 * @date : 2022. 5. 18.
	 * comment : 특정 학생 제거
	 */
	public int deleteStudents(@Param("studentsId") int studentsId);
	
	
	/**
	 * @param vo
	 * @return
	 * @author : Sangwon Hyun
	 * @date : 2022. 5. 18.
	 * comment : 특정 학생 수정
	 */
	public int updateStudents(StudentsVO vo);
}
