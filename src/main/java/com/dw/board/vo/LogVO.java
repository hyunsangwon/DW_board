package com.dw.board.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogVO {
	private int logId;
	private String ip;
	private String latitude;//36.3286904
	private String longitude;//127.4229992
	private String url;
	private String httpMethod;
	private String createAt;
}
