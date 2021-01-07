package com.cos.blog.domain.board.dto;

import lombok.Data;

@Data
public class SaveReqDto {
	private int userId;
	private String title;
	private String content;
}
