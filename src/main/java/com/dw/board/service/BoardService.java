package com.dw.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dw.board.mapper.BoardMapper;
import com.dw.board.vo.BoardVO;

@Service
public class BoardService {

	@Autowired
	private BoardMapper boardMapper;
	
	//게시판 저장
	@Transactional(rollbackFor = Exception.class)
	public int setBoard(BoardVO vo) {
		return boardMapper.insertBoard(vo);
	}
	//게시판 전체 조회
	public List<Map<String, Object>> getAllBoardList(){
		return boardMapper.selectAllBoardList();
	}
	//게시물 수정
	@Transactional(rollbackFor = Exception.class)
	public int getUpdateBoard(BoardVO vo, int boardId) {
		vo.setBoardId(boardId);
		return boardMapper.updateBoard(vo);
	}
	//게시물 삭제
	@Transactional(rollbackFor = Exception.class)
	public int getRemoveBoard(int boardId) {
		return boardMapper.deleteBoard(boardId);
	}
	//게시물 상세조회
	public BoardVO getBoard(int boardId) {
		return boardMapper.selectBoardOne(boardId);
	}
}
