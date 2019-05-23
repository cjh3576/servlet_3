<%@page import="com.jh.notice.noticeDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Notice Select</title>
<jsp:include page="../temp/bootstrap.jsp"/>
</head>
<body>
<jsp:include page="../temp/header.jsp"/>
	<div class = "container">
	<h1>Notice Select</h1>
	<table class="table table-hover">
			<thead>
				<tr>
					<th>SUBJECT</th>
					<th>NAME</th>
					<th>DATE</th>
					<th>HIT</th>
					<th>File</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${noticedto.title}</td>
					<td>${noticedto.writer}</td>
					<td>${noticedto.reg_date}</td>
					<td>${noticedto.hit}</td>
					<td><a href="../upload/${upload.fname}">${upload.oname}</a></td>
				</tr>
				<tr>
					<td colspan="5">${requestScope.noticedto.contents}
				</tr>
			</tbody>
		
		</table>
	
	
	</div>
</body>
</html>