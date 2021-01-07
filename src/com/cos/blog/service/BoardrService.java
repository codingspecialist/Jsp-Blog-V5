package com.cos.blog.service;

import java.util.List;

import com.cos.blog.domain.board.Board;
import com.cos.blog.domain.board.BoardDao;
import com.cos.blog.domain.board.dto.SaveReqDto;

public class BoardrService {

	private BoardDao boardDao;
	
	public BoardrService() {
		boardDao = new BoardDao();
	}
	
	public int 글쓰기(SaveReqDto dto) {
		return boardDao.save(dto);
	}
	

}
