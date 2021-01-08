package com.cos.blog.service;

import java.util.List;

import com.cos.blog.domain.board.Board;
import com.cos.blog.domain.board.BoardDao;
import com.cos.blog.domain.board.dto.DetailRespDto;
import com.cos.blog.domain.board.dto.SaveReqDto;
import com.cos.blog.domain.board.dto.UpdateReqDto;

public class BoardrService {

	private BoardDao boardDao;
	
	public BoardrService() {
		boardDao = new BoardDao();
	}
	
	public int 글수정(UpdateReqDto dto) {
		return boardDao.update(dto);
	}
	
	public int 글삭제(int id) {
		return boardDao.deleteById(id);
	}
	
	// 하나의 서비스안에 여러가지 DB관련 로직이 섞여 있죠.
	public DetailRespDto 글상세보기(int id) {
		// 조회수 업데이트치기
		int result = boardDao.updateReadCount(id);
		if(result == 1) {
			return boardDao.findById(id);
		}else {
			return null;
		}
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
