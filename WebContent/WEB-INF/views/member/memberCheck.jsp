<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member Check</title>
<c:import url="../temp/bootstrap.jsp" />

</head>
<body>
	<jsp:include page="../temp/header.jsp" />
	<div class="container main">
		<h2>약관동의</h2>
		<form action="./memberJoin" method="get" denctype="multipart/form-data">
			<div class="checkbox">
				<label><input type="checkbox" id="checkAll"> 전체동의</label>
			</div>
			<div class="checkbox">
				<label><input type="checkbox" class="check"> 동의A</label>
			</div>
			<div class="checkbox">
				<label><input type="checkbox" class="check"> 동의B</label>
			</div>
			<div class="checkbox">
				<label><input type="checkbox" class="check"> 동의C</label>
			</div>
			<input type="submit" class="btn btn-default" value="Join" id="join">
		</form>
	</div>
</body>
</html>