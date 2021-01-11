package com.cos.blog.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.domain.board.Board;
import com.cos.blog.domain.board.dto.CommonRespDto;
import com.cos.blog.domain.board.dto.DetailRespDto;
import com.cos.blog.domain.board.dto.SaveReqDto;
import com.cos.blog.domain.board.dto.UpdateReqDto;
import com.cos.blog.domain.reply.Reply;
import com.cos.blog.domain.user.User;
import com.cos.blog.service.BoardrService;
import com.cos.blog.service.ReplyService;
import com.cos.blog.util.Script;
import com.google.gson.Gson;

// http://localhost:8080/blog/board
@WebServlet("/board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BoardController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		BoardrService boardService = new BoardrService();
		ReplyService replyService = new ReplyService();
		// http://localhost:8080/blog/board?cmd=saveForm
		HttpSession session = request.getSession();
		if (cmd.equals("saveForm")) {
			User principal = (User) session.getAttribute("principal");
			if (principal != null) {
				RequestDispatcher dis = request.getRequestDispatcher("board/saveForm.jsp");
				dis.forward(request, response);
			} else {
				RequestDispatcher dis = request.getRequestDispatcher("user/loginForm.jsp");
				dis.forward(request, response);
			}
		} else if (cmd.equals("save")) {
			int userId = Integer.parseInt(request.getParameter("userId"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");

			SaveReqDto dto = new SaveReqDto();
			dto.setUserId(userId);
			dto.setTitle(title);
			dto.setContent(content);
			int result = boardService.글쓰기(dto);
			if (result == 1) { // 정상
				response.sendRedirect("index.jsp");
			} else {
				Script.back(response, "글쓰기실패");
			}
		} else if (cmd.equals("list")) {
			int page = Integer.parseInt(request.getParameter("page"));  // 최초 : 0, Next : 1, Next: 2
			List<Board> boards = boardService.글목록보기(page);
			request.setAttribute("boards", boards);
			
			// 계산 (전체 데이터수랑 한페이지몇개 - 총 몇페이지 나와야되는 계산) 3page라면 page의 맥스값은 2
			// page == 2가 되는 순간  isEnd = true
			// request.setAttribute("isEnd", true);
			int boardCount = boardService.글개수();
			int lastPage = (boardCount-1)/4; // 2/4 = 0, 3/4 = 0, 4/4 = 1, 9/4 = 2 ( 0page, 1page, 2page) 
			double currentPosition = (double)page/(lastPage)*100;
			
			request.setAttribute("lastPage", lastPage);
			request.setAttribute("currentPosition", currentPosition);
			RequestDispatcher dis = request.getRequestDispatcher("board/list.jsp");
			dis.forward(request, response);
		}else if(cmd.equals("detail")) {
			int id = Integer.parseInt(request.getParameter("id"));
			
			DetailRespDto dto = boardService.글상세보기(id); // board테이블+user테이블 = 조인된 데이터!!
			List<Reply> replys = replyService.글목록보기(id);
			
			if(dto == null) {
				Script.back(response, "상세보기에 실패하였습니다");
			}else {
				request.setAttribute("dto", dto);
				request.setAttribute("replys", replys);
				//System.out.println("DetailRespDto : "+dto);
				RequestDispatcher dis = request.getRequestDispatcher("board/detail.jsp");
				dis.forward(request, response);
			}
		}else if(cmd.equals("delete")) {
			
			// 1. 요청 받은 json 데이터를 자바 오브젝트로 파싱
			int id = Integer.parseInt(request.getParameter("id"));

			// 2. DB에서 id값으로 글 삭제
			int result = boardService.글삭제(id);
			
			// 3. 응답할 json 데이터를 생성
			CommonRespDto<String> commonRespDto = new CommonRespDto<>();
			commonRespDto.setStatusCode(result);
			commonRespDto.setData("성공");
			
			Gson gson = new Gson();
			String respData = gson.toJson(commonRespDto);
			System.out.println("respData : "+respData);
			PrintWriter out = response.getWriter();
			out.print(respData);
			out.flush();
		}else if(cmd.equals("updateForm")) {
			int id = Integer.parseInt(request.getParameter("id"));
			DetailRespDto dto = boardService.글상세보기(id);
			request.setAttribute("dto", dto);
			RequestDispatcher dis = request.getRequestDispatcher("board/updateForm.jsp");
			dis.forward(request, response);
		}else if(cmd.equals("update")) {
			int id = Integer.parseInt(request.getParameter("id"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			UpdateReqDto dto = new UpdateReqDto();
			dto.setId(id);
			dto.setTitle(title);
			dto.setContent(content);
			
			int result = boardService.글수정(dto);
			
			if(result == 1) {
				// 고민해보세요. 왜 RequestDispatcher 안썻는지... 한번 써보세요. detail.jsp 호출
				response.sendRedirect("/blog/board?cmd=detail&id="+id);
			}else {
				Script.back(response,"글 수정에 실패하였습니다.");
			}
		}else if(cmd.equals("search")) {
			String keyword = request.getParameter("keyword");
			int page = Integer.parseInt(request.getParameter("page"));
			
			List<Board> boards = boardService.글검색(keyword, page);
			request.setAttribute("boards", boards);
			
			int boardCount = boardService.글개수(keyword);
			int lastPage = (boardCount-1)/4; // 2/4 = 0, 3/4 = 0, 4/4 = 1, 9/4 = 2 ( 0page, 1page, 2page) 
			double currentPosition = (double)page/(lastPage)*100;

			
			
			request.setAttribute("lastPage", lastPage);
			request.setAttribute("currentPosition", currentPosition);
			RequestDispatcher dis = request.getRequestDispatcher("board/list.jsp");
			dis.forward(request, response);
		}
	}

}
