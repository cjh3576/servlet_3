<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Notice Write</title>
<c:import url="../temp/bootstrap.jsp" />
<script type="text/javascript">
	$(function() {
		var file = document.getElementById('file');
		var num = 0;
		var d1=0;
	$('#add').click(function() {
		d1++;
		if (num < 5) {
/* 		file.innerHTML = file.innerHTML + '<input type="file" class="form-control" id="f1" name = "f1">'; */
		$("#addfile").append('<div id="'+d1+'"><input type="file" class="form-control" id="" name="f'+d1+'"><span title="'+d1+'" class="del">X</span></div>')
		num++;
		} else{
			alert('5개 파일까지 가능합니다');
		}
	});
	
	$("#addfile").on("click",".del", function(){
		//$(this).prev().remove();
		//$(this).remove();
		var v = $(this).attr('title');
		$('#'+v).remove();
		num--;
	});
		
	});
</script>
<style type="text/css">
.del{
	color: red;
	cursor: pointer;
}
</style>
</head>
<body>
	<c:import url="../temp/header.jsp" />
	<div class="container">
		<form action="./qnaWrite" method="post" enctype="multipart/form-data">
			<div class="form-group">
				<label for="title">Title:</label> <input type="text"
					class="form-control" id="title" name="title">
			</div>
			<div class="form-group">
				<label for="writer">Writer:</label> <input type="text"
					class="form-control" id="writer" name="writer">
			</div>
			<div class="form-group">
				<label for="contents">Contents:</label>
				<textarea class="form-control" rows="5" id="contents"
					name="contents"></textarea>
			</div>
			<div class="form-group" id="addfile">
				<label for="file">file:</label>
				
			</div>
			<div class="form-group">
				<input type="button" id="add" value="Add" class="btn btn-primary">
			</div>
			<button class="btn btn-danger">Write</button>
		</form>


	</div>
</body>
</html>