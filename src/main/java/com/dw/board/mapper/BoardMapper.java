package com.dw.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.dw.board.vo.BoardVO;


@Mapper	
public interface BoardMapper {

	/**
	 * @param vo
	 * @return
	 * @author : Sangwon Hyun
	 * @date : 2022. 5. 19.
	 * comment : 게시판 저장
	 */
	public int insertBoard(BoardVO vo);
	
	
	/**
	 * @return
	 * @author : Sangwon Hyun
	 * @date : 2022. 5. 19.
	 * comment : 전체 게시판 조회
	 */
	public List<Map<String, Object>> selectAllBoardList();
	
	public int updateBoard(BoardVO vo);
	
	public int deleteBoard(int boardId);
	
	public BoardVO selectBoardOne(int boardId);
	
}
