<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Notice Write</title>
<c:import url="../temp/bootstrap.jsp" />
</head>
<body>
	<c:import url="../temp/header.jsp" />
	<div class="container">
		<form action="./noticeWrite" method="post" enctype="multipart/form-data">
			<div class="form-group">
				<label for="title">Title:</label> 
				<input type="text" class="form-control" id="title" name = "title">
			</div>
			<div class="form-group">
				<label for="writer">Writer:</label> <input type="text"
					class="form-control" id="writer" name="writer">
			</div>
			<div class="form-group">
				<label for="contents">Contents:</label>
				<textarea class="form-control" rows="5" id="contents" name="contents"></textarea>
			</div>
			<div class="form-group">
				<label for="file">file:</label> 
				<input type="file" class="form-control" id="f1" name = "f1">
			</div>
			<button class="btn btn-primary">Write</button>
		</form>


	</div>
</body>
</html>