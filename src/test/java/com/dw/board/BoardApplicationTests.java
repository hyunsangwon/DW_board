package com.dw.board;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dw.board.mapper.BoardMapper;
import com.dw.board.utils.PageHandler;

@SpringBootTest
class BoardApplicationTests {
	
	@Autowired
	private PageHandler pageHandler;
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Test
	void contextLoads() {
		int total = boardMapper.selectAllBoardTotal();//전체 게시물 수
		System.out.println("total => "+total);

		int pageNum = 1; //현재 페이지 번호
		int pageSize = 10; //한 페이지에 게시물 10개
		int navigatePages = 5; //한 블록에 페이지 5개
		
		pageHandler.setTotal(total);
		pageHandler.setPageNum(pageNum);
		pageHandler.setPageSize(pageSize);
		pageHandler.setNavigatePages(navigatePages);
		
		pageHandler.setNowBlock(pageNum);
		int nowBlock = pageHandler.getNowBlock(); //현재 블록
		System.out.println("현재 블록 => "+nowBlock);
		
		pageHandler.setLastBlock(total);
		int lastBlock = pageHandler.getLastBlock();
		System.out.println("마지막 블록 => "+lastBlock);
		
		pageHandler.setStartPage(nowBlock);
		int startPage = pageHandler.getStartPage();
		System.out.println("현재 페이지 => "+startPage);
		
		int pages = pageHandler.calcPage(total, pageSize);
		pageHandler.setEndPage(nowBlock, pages);
		int lastPage = pageHandler.getEndPage();
		System.out.println("마지막 페이지 => "+lastPage);
		
		pageHandler.setPreNext(pageNum);
		boolean hasPreviousPage =  pageHandler.isHasPreviousPage();
		boolean hasNestPage = pageHandler.isHasNextPage();
		System.out.println("이전 버튼 유무 => "+hasPreviousPage);
		System.out.println("다음 버튼 유무 => "+hasNestPage);
		
		int limitStart = ((pageNum - 1) * pageSize);
		List<Map<String, Object>> list = boardMapper.selectAllBoardListTest(limitStart,pageSize);
		
		for(Map<String, Object> param : list) {
			String studentsName = (String) param.get("studentsName");
			System.out.println(studentsName);
		}
		
	}
}