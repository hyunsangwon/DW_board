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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dw.board.service.BoardService;
import com.dw.board.vo.BoardVO;
import com.github.pagehelper.PageInfo;

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
	//리턴타입을 List<Map<String, Object>> => PageInfo<Map<String, Object>>
	public PageInfo<Map<String, Object>> callBoardList(@RequestParam("pageNum") int pageNum, 
			@RequestParam("pageSize") int pageSize){
		
		List<Map<String, Object>> list = boardService.getAllBoardList(pageNum, pageSize);
		
		return new PageInfo<Map<String, Object>>(list);
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
	
	//게시물 카운트
	@CrossOrigin
	@PatchMapping("/board/views/boardId/{id}")
	public int callBoardViews(@PathVariable("id") int boardId) {
		return boardService.getUpdateBoardView(boardId);
	}
	
	//쿼리 스트링으로 검색한 작성자 게시판 리스트 조회
	@CrossOrigin
	@GetMapping("/board/search")
	public List<Map<String, Object>> callBoardSearch(@RequestParam("writer") String writer){
		return boardService.getSearchBoardList(writer);
	}
	
	//게시판 통계 조회
	@CrossOrigin
	@GetMapping("/board/statistics")
	public Map<String, Object> callBoardStatistics(){
		return boardService.getBoardStatistics();
	}
	
}
