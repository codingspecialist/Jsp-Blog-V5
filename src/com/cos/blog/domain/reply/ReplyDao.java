package com.cos.blog.domain.reply;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.cos.blog.config.DB;
import com.cos.blog.domain.reply.dto.SaveReqDto;

public class ReplyDao {

	public int save(SaveReqDto dto) { // 회원가입
		String sql = "INSERT INTO reply(userId, boardId, content, createDate) VALUES(?,?,?, now())";
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getUserId());
			pstmt.setInt(2, dto.getBoardId());
			pstmt.setString(3, dto.getContent());
			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // 무조건 실행
			DB.close(conn, pstmt);
		}
		return -1;
	}
}
