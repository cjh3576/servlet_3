<%@page import="com.jh.notice.noticeDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<noticeDTO> ar = (ArrayList<noticeDTO>)request.getAttribute("list");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="../temp/bootstrap.jsp"/>
</head>
<body>
<jsp:include page="../temp/header.jsp"/>
	<div class="container">
	<h1>Notice List</h1>
	<table class="table table-hover">
	<thead>
		<tr>
			<th>NUM</th>
			<th>TITLE</th>
			<th>WRITER</th>
			<th>DATE</th>
			<th>HIT</th>
		</tr>
	</thead>
		<%for(noticeDTO noticedto : ar ){ %>
		<tr>
			<td><%=noticedto.getNum() %></td>
			<td><%=noticedto.getTitle() %></td>
			<td><%=noticedto.getWriter() %></td>
			<td><%=noticedto.getReg_date() %></td>
			<td><%=noticedto.getHit() %></td>
		</tr>
		<%} %>
	</table>	
	</div>
	
	<a href="./noticeWrite">Go write</a>
</body>
</html>