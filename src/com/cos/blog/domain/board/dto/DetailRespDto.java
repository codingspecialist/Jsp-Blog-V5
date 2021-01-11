package com.cos.blog.domain.board.dto;

import java.util.List;

import com.cos.blog.domain.reply.Reply;

import lombok.Data;

@Data
public class DetailRespDto {
	private int id;
	private String title;
	private String content;
	private int readCount;
	private String username;
	private int userId;

	// 루시 필터 적용해보기
	public String getTitle() {
		return title.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}
}
