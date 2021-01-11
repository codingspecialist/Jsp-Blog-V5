<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<div class="container">
	<c:if test="${sessionScope.principal.id == dto.userId}">
		<a href="/blog/board?cmd=updateForm&id=${dto.id}" class="btn btn-warning" >수정</a>
		<button onClick="deleteById(${dto.id})" class="btn btn-danger">삭제</button>
	</c:if>
	
	<br />
	<br />
	<h6 class="m-2">
		작성자 : <i>${dto.username}</i> 조회수 : <i>${dto.readCount}</i>
	</h6>
	<br />
	<h3 class="m-2">
		<b>${dto.title}</b>
	</h3>
	<hr />
	<div class="form-group">
		<div class="m-2">${dto.content}</div>
	</div>

	<hr />
	
	<!-- 댓글 박스 -->
	<div class="row bootstrap snippets">
		<div class="col-md-12">
			<div class="comment-wrapper">
				<div class="panel panel-info">
					<div class="panel-heading m-2"><b>Comment</b></div>
					<div class="panel-body">
							<input type="hidden" name="userId" value="${sessionScope.principal.id}" />
							<input type="hidden" name="boardId" value="${dto.id}" />
							<textarea id="content" id="reply__write__form" class="form-control" placeholder="write a comment..." rows="2"></textarea>
							<br>
						
							<button onClick="replySave(${sessionScope.principal.id}, ${dto.id})" class="btn btn-primary pull-right">댓글쓰기</button>
				
						<div class="clearfix"></div>
						<hr />
						
						<!-- 댓글 리스트 시작-->
						<ul id="reply__list" class="media-list">
						
								<!-- 댓글 아이템 -->
								<li id="reply-1" class="media">		
									<div class="media-body">
										<strong class="text-primary">홍길동</strong>
										<p>
											댓글입니다.
										</p>
									</div>
									<div class="m-2">
		
										<i onclick="#" class="material-icons">delete</i>

									</div>
								</li>
							
						</ul>
						<!-- 댓글 리스트 끝-->
					</div>
				</div>
			</div>

		</div>
	</div>
	<!-- 댓글 박스 끝 -->
</div>

<script src="/blog/js/boardDetail.js"></script>
</body>
</html>


    