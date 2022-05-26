package com.dw.board.controller;

import java.util.List;
import java.util.Map;

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

import com.dw.board.service.BoardService;
import com.dw.board.vo.BoardVO;

@RestController
@RequestMapping("/api/v1") 
public class BoardRestController {

	@Autowired
	private BoardService boardService;
	
	//게시판 저장 (C)
	@CrossOrigin
	@PostMapping("/board")
	public int callSaveBoard(@RequestBody BoardVO vo) {
		return boardService.setBoard(vo);
	}
	
	//게시판 전체조회 (R)
	@CrossOrigin
	@GetMapping("/board")
	public List<Map<String, Object>> callBoardList(){
		return boardService.getAllBoardList();
	}
	
	//게시물 삭제 (D)	
	@CrossOrigin
	@DeleteMapping("/board/boardId/{id}")
	public int callRemoveBoard(@PathVariable("id") int boardId) {
		return boardService.getRemoveBoard(boardId);
	}
	
	//게시물 수정 (U)
	@CrossOrigin
	@PatchMapping("/board/boardId/{id}")
	public int callUpdateBoard(@PathVariable("id") int boardId, @RequestBody BoardVO vo) {
		return boardService.getUpdateBoard(vo, boardId);
	}
	
	//게시물 상세보기 (R)
	@CrossOrigin
	@GetMapping("/board/boardId/{id}")
	public BoardVO callBoard(@PathVariable("id") int boardId) {
		return boardService.getBoard(boardId);
	}
	
}
