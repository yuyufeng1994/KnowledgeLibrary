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
	<c:forEach items='${list}' var="r">
		<tr>
			<td>${r.content }</td>
			<td>${r.fileId}</td>
			</p>
	</c:forEach>

<br>


	<table style="border:1px solid black;width:300px;">
		<tr>
			<td><button onclick='upthis(this)'><i class='am-icon-caret-up'></i></button><br><button onclick='downthis(this)'><i class='am-icon-caret-down'></i></button></td><td>1</td>
		</tr>
		<tr>
			<td><button onclick='upthis(this)'><i class='am-icon-caret-up'></i></button><br><button onclick='downthis(this)'><i class='am-icon-caret-down'></i></button></td><td>2</td>
		</tr>
		<tr>
			<td><button onclick='upthis(this)'><i class='am-icon-caret-up'></i></button><br><button onclick='downthis(this)'><i class='am-icon-caret-down'></i></button></td><td>3</td>
		</tr>
		<tr>
			<td><button onclick='upthis(this)'><i class='am-icon-caret-up'></i></button><br><button onclick='downthis(this)'><i class='am-icon-caret-down'></i></button></td><td>4</td>
		</tr>
		<tr>
			<td><button onclick='upthis(this)'><i class='am-icon-caret-up'></i></button><br><button onclick='downthis(this)'><i class='am-icon-caret-down'></i></button></td><td>5</td>
		</tr>
	</table>
</body>
<script type="text/javascript">
function upthis(mythis){
	$this = $(mythis);
	$thisTr = $this.parent().parent()
	$prevTr = $thisTr.prev();
	$thisTr.insertBefore($prevTr)
	
}

function downthis(mythis){
	$this = $(mythis);
	$thisTr = $this.parent().parent()
	$nextTr = $thisTr.next();
	$thisTr.insertAfter($nextTr);
}
</script>
</html>
