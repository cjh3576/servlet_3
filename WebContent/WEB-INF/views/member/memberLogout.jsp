<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% 
    /*     session.setAttribute("member", null);
    session.removeAttribute("member");
    session.removeValue("member"); */
    session.invalidate(); //세션만료시간을 0으로 설정
    response.sendRedirect("../index.do");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>