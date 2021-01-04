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

		<input type="text"  name="address" class="form-control" placeholder="Enter Address"  required/>
		<br/>
		<button type="submit" class="btn btn-primary">회원가입완료</button>
	</form>
</div>
</body>
</html>