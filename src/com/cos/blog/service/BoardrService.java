package com.cos.blog.service;

import com.cos.blog.domain.board.BoardDao;
import com.cos.blog.domain.board.dto.SaveReqDto;
import com.cos.blog.domain.user.UserDao;

public class BoardrService {

	private BoardDao boardDao;
	
	public BoardrService() {
		boardDao = new BoardDao();
	}
	
	public int 글쓰기(SaveReqDto dto) {
		return boardDao.save(dto);
	}
}
