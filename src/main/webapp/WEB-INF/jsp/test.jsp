<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>test</title>
<%@include file="common/head.jsp"%>
</head>
<body>
	<button id="btn">点我</button>
 	<a herf="admin/lucene">建索引</a>
</body>
<script type="text/javascript">
	$(function(){
		$("#btn").click(function(){
			var url = "admin/recommend";
			var args = {"userId":"2016014"};
			$.post(url,args,function(data){
				console.log(data.data);
			});
		});
	});
</script>
</html>
