package com.cos.blog.domain.reply.dto;

import lombok.Data;

@Data
public class SaveReqDto {
	private int userId;
	private int boardId;
	private String content;
}
