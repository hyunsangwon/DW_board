package com.dw.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.dw.board.vo.LogVO;

//log같은 기록데이터는 insert 하고 select만 구현한다.
@Mapper
public interface LogsMapper {

	public int insertLogs(LogVO logVO);//접속이력 저장
	
	public List<Map<String, Object>> selectBoardLogs(int logId);//이력 불러오기
	
}
