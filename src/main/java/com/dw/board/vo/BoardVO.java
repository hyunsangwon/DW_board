package com.dw.board.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardVO {
	private int boardId;
	private int studentsId;
	private String title;
	private String content;
	private String updateAt;
	private String createAt;
}
