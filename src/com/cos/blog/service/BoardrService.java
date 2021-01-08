package com.cos.blog.service;

import java.util.List;

import com.cos.blog.domain.board.Board;
import com.cos.blog.domain.board.BoardDao;
import com.cos.blog.domain.board.dto.DetailRespDto;
import com.cos.blog.domain.board.dto.SaveReqDto;

public class BoardrService {

	private BoardDao boardDao;
	
	public BoardrService() {
		boardDao = new BoardDao();
	}
	
	public DetailRespDto 글상세보기(int id) {
		// 조회수 업데이트치기
		return boardDao.findById(id);
	}
	
	public int 글개수() {
		return boardDao.count();
	}
	
	public int 글쓰기(SaveReqDto dto) {
		return boardDao.save(dto);
	}
	
	public List<Board> 글목록보기(int page){
		return boardDao.findAll(page);
	}

}
