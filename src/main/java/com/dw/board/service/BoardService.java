package com.dw.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dw.board.mapper.BoardMapper;
import com.dw.board.vo.BoardVO;

@Service
public class BoardService {

	@Autowired
	private BoardMapper boardMapper;
	
	//게시판 저장
	public int setBoard(BoardVO vo) {
		return boardMapper.insertBoard(vo);
	}
	
	//게시판 전체 조회
	public List<Map<String, Object>> getAllBoardList(){
		return boardMapper.selectAllBoardList();
	}
}
