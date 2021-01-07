<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">

	<div class="m-2">
		<form class="form-inline d-flex justify-content-end"
			action="/blog/board">
			<input type="hidden" name="cmd" value="search" /> <input
				type="hidden" name="page" value="0" /> <input type="text"
				name="keyword" class="form-control mr-sm-2" placeholder="Search">
			<button class="btn btn-primary m-1">검색</button>

		</form>
	</div>

	<div class="progress col-md-12 m-2">
		<div class="progress-bar" style="width: 70%"></div>
	</div>

	<!-- JSTL foreach문을 써서 뿌리세요. el표현식과 함께 -->

	<c:forEach var="board" items="${boards}">
		<div class="card col-md-12 m-2">
			<div class="card-body">
				<h4 class="card-title">${board.title}</h4>
				<a href="/blog/board?cmd=detail&id=${board.id}" class="btn btn-primary">상세보기</a>
			</div>
		</div>
	</c:forEach>
	
	<br />
	<!-- disabled -->
	<ul class="pagination justify-content-center">
		<li class="page-item"><a class="page-link" href="/blog/board?cmd=list&page=${param.page-1}">Previous</a></li>
		<li class="page-item"><a class="page-link" href="/blog/board?cmd=list&page=${param.page+1}">Next</a></li>
	</ul>
</div>

</body>
</html>


