<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member Join</title>
<c:import url="../temp/bootstrap.jsp" />
</head>
<body>
	<jsp:include page="../temp/header.jsp" />
	<div class="container main">

		<h2>Join form</h2>
		<form action="./memberJoin" method="post" enctype="multipart/form-data">
			<div class="form-group">
				<label for="id">Id:</label> <input type="text" class="form-control"
					id="iid" placeholder="Enter Id" name="id"> <input
					type="hidden" id="idConfirm" value="0"> <input
					type="button" value="중복확인" id="idoverlap">
				<div id="idcheck" class="check"></div>
			</div>
			<div class="form-group">
				<label for="pw">Password:</label> <input type="password"
					class="form-control" id="ipw" placeholder="Enter password"
					name="pw">
				<div id="pwcheck" class="check"></div>
			</div>
			<div class="form-group">
				<label for="pwd">Password:</label> <input type="password"
					class="form-control" id="ipwd1" placeholder="Enter password"
					name="pwd1">
				<div id="pwdoverlap" class="check"></div>
			</div>
			<div class="form-group">
				<label for="name">Name:</label> <input type="text"
					class="form-control" id="iname" placeholder="Enter name"
					name="name">
				<div id="namecheck" class="check"></div>
			</div>
			<div class="form-group">
				<label for="age">Age:</label> <input type="text"
					class="form-control" id="iage" placeholder="Enter age" name="age">
				<div id="agecheck" class="check"></div>
			</div>
			<div class="form-group">
				<label for="phone">Phone:</label> <input type="text"
					class="form-control" id="iphone" placeholder="Enter Phone"
					name="phone">
				<div id="phonecheck" class="check"></div>
			</div>
			<div class="form-group">
				<label for="email">Email:</label> <input type="email"
					class="form-control" id="iemail" placeholder="Enter email"
					name="email">
				<div id="emailcheck" class="check"></div>
			</div>
			<div class="form-group">
				<label for="file">file:</label> 
				<input type="file" class="form-control" id="f1" name = "f1">
			</div>
			<button class="btn btn-primary">Join</button>
		</form>
	</div>
</body>
</html>