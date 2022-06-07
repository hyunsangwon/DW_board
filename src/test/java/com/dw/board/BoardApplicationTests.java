package com.dw.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dw.board.utils.PageHandler;

@SpringBootTest
class BoardApplicationTests {

	@Autowired
	private PageHandler pageHandler;
	
	//페이징 로직 구현하기
	@Test
	void contextLoads() {
		int sqlTotal = 100; //SQL에서 가져온 전체 count
		int pageNum = 1; //현재 페이지 번호
		int pageSize = 10; //한 페이지에 보여줄 게시물 수
		int navigatePages = 5;
		
		pageHandler.setTotal(sqlTotal);
		pageHandler.setPageNum(pageNum);                                                               
		pageHandler.setPageSize(pageSize);
		pageHandler.setNavigatePages(navigatePages);
		
		pageHandler.setNowBlock(pageNum);
		int nowBlock = pageHandler.getNowBlock();
		System.out.println("현재 블록은 => "+nowBlock);
		
		pageHandler.setLastBlock(sqlTotal);
		int lastBlock = pageHandler.getLastBlock();
		System.out.println("마지막 블록은 => "+lastBlock);
		
		pageHandler.setStartPage(nowBlock);
		int startPage = pageHandler.getStartPage();
		System.out.println("현재 페이지는 => "+startPage);
		
		int pages = pageHandler.calcPage(sqlTotal, pageSize);
		pageHandler.setEndPage(nowBlock, pages);
		int lastPage = pageHandler.getEndPage();
		System.out.println("마지막 페이지는 => "+lastPage);
		
		pageHandler.setPreNext(pageNum);
		boolean hasPreviousPage = pageHandler.isHasPreviousPage();
		boolean hasNextPage = pageHandler.isHasNextPage();
		System.out.println("이전버튼 유무 => "+hasPreviousPage);
		System.out.println("다음버튼 유무 => "+hasNextPage);
		
		
		int limitLeft = ((pageNum - 1 ) * pageSize);
		int limitRight = pageSize;
		System.out.println("limit 왼쪽 => "+ limitLeft);
		System.out.println("limit 오른쪽 => "+ limitRight);
	}

}
