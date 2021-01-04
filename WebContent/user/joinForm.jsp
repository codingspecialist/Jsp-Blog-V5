<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form action="/blog/user?cmd=join" method="post">
		<div class="form-group">
			<input type="text" name="username" class="form-control" placeholder="Enter Username"  required/>
		</div>

		<div class="form-group">
			<input type="password" name="password"  class="form-control" placeholder="Enter Password"  required/>
		</div>

		<div class="form-group">
			<input type="email"  name="email"  class="form-control" placeholder="Enter Email" required />
		</div>
		<div class="d-flex justify-content-end">
			<button type="button" class="btn btn-info" onClick="goPopup();">주소검색</button>
		</div>
		<input type="text"  name="address"  id="address" class="form-control" placeholder="Enter Address"  required readonly/>
		<br/>
		<button type="submit" class="btn btn-primary">회원가입완료</button>
	</form>
</div>

<script>
	function goPopup() {
		// 주소검색을 수행할 팝업 페이지를 호출합니다.
		// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
		var pop = window.open("/blog/user/jusoPopup.jsp", "pop",
				"width=570,height=420, scrollbars=yes, resizable=yes");
	}

	function jusoCallBack(roadFullAddr) {
		var addressEl = document.querySelector("#address");
		addressEl.value = roadFullAddr;
	}
</script>
</body>
</html>