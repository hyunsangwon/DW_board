package com.dw.board.utils;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class PageHandler {

	private int total; //전체 게시물 수
	private int pageNum; //현재 페이지
	private int pageSize; //1페이지에 몇개 게시물 표시할지
	private int startPage; //현재 블록 첫 페이지
	private int endPage; //현재 블록 마지막 페이지
	private boolean hasPreviousPage=true; //이전 버튼 유무
	private boolean hasNextPage=true;//다음 버튼 유무
	private int nowBlock; //현재 블록
	private int lastBlock; //마지막 블록
	private int navigatePages; //블록에 페이지 몇개 표시할지 (ex 1블록 : 1,2,3,4,5)

	/**
	 * @return int
	 * @author : Sangwon Hyun
	 * @date : 2022. 5. 31.
	 * comment : 총 페이지 수 
	 */
	public int calcPage(int total, int pageSize) {
		int pages = total / pageSize;
		if(total % pageSize > 0) ++pages;
		return pages;
	}
	
	/**
	 * 
	 * @author : Sangwon Hyun
	 * @date : 2022. 5. 31.
	 * comment : 현재 페이지 블록 알아내기
	 */
	public void setNowBlock(int pageNum) {
		this.nowBlock = pageNum / this.navigatePages;
		if(pageNum % this.navigatePages > 0) this.nowBlock++;
	}
	
	/**
	 * @param total
	 * @author : Sangwon Hyun
	 * @date : 2022. 5. 31.
	 * comment : 마지막 블록 알아내기
	 */
	public void setLastBlock(int total) {
		this.lastBlock = total / (this.navigatePages * this.pageSize);
		if(total % (this.navigatePages * this.pageSize) > 0) this.lastBlock++;
	}
	
	//현재 블록의 처음 페이지
 	/**
 	 * @param nowBlock
 	 * @author : Sangwon Hyun
 	 * @date : 2022. 5. 31.
 	 * comment : 
 	 */
 	public void setStartPage(int nowBlock) {
		this.startPage = (nowBlock * this.navigatePages) - (this.navigatePages - 1);
	}
 	
	//현재 블록의 마지막 페이지
	public void setEndPage(int nowBlock, int pages) {
		this.endPage = nowBlock * this.navigatePages;
		if(nowBlock == this.lastBlock) {
			this.endPage = pages;
		}
	}
	//이전버튼, 다음버튼 유무 판단
	public void setPreNext(int pageNum) {
		if(this.lastBlock == 1){
			setHasPreviousPage(false);
			setHasNextPage(false);
		}
		if(this.lastBlock > 1 && this.lastBlock == this.nowBlock) {
			setHasPreviousPage(true);
			setHasNextPage(false);
		}
		if(this.lastBlock > 1 && pageNum <= this.navigatePages) {
			setHasPreviousPage(false);
			setHasNextPage(true);
		}
	}
	
}