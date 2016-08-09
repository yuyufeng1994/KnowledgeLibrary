<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>test</title>
<%@include file="common/head.jsp"%>
</head>
<body>
	<form action="test-sub" method="post">
		<input type="text" name="str" value="${str}" />
		<button type="submit">提交</button>
	</form>
	<p></p>
</body>
</html>
