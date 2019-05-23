<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../temp/bootstrap.jsp" />
</head>
<body>
<jsp:include page="../temp/header.jsp" />
<div class="container main">
		<h2>Login</h2>
		<form action="./memberLogin" method="post">
			<div class="form-group">
				<label for="id">ID:</label> 
				<input type="text" class="form-control"	name="id">
			</div>
			<div class="form-group">
				<label for="pw">Password:</label> <input type="password"
					class="form-control" name="pw">
			</div>
			<div class="checkbox">
				<label><input type="checkbox" name="check"  value="true"> Remember me</label>
			</div>
			<button type="submit" class="btn btn-default">Login</button>
		</form>

	</div>
</body>
</html>