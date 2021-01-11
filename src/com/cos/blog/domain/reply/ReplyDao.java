package com.cos.blog.domain.reply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.cos.blog.config.DB;
import com.cos.blog.domain.reply.dto.SaveReqDto;

public class ReplyDao {

	public Reply findById(int id){
		String sql = "SELECT * FROM reply WHERE id = ?";
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs  = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs =  pstmt.executeQuery();
			
			// Persistence API
			if(rs.next()) { // 커서를 이동하는 함수
				Reply reply = new Reply();
				reply.setId(rs.getInt("id"));
				reply.setUserId(rs.getInt("userId"));
				reply.setBoardId(rs.getInt("boardId"));
				reply.setContent(rs.getString("content"));
				return reply;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // 무조건 실행
			DB.close(conn, pstmt, rs);
		}
		return null;
	}
	
	public int save(SaveReqDto dto) { // 회원가입
		String sql = "INSERT INTO reply(userId, boardId, content, createDate) VALUES(?,?,?, now())";
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int generateKey;
		try {
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, dto.getUserId());
			pstmt.setInt(2, dto.getBoardId());
			pstmt.setString(3, dto.getContent());
			int result = pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				generateKey = rs.getInt(1);
				System.out.println("생성된 키(ID) : "+generateKey);
				if(result == 1) {
					return generateKey;	
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // 무조건 실행
			DB.close(conn, pstmt);
		}
		return -1;
	}
}
