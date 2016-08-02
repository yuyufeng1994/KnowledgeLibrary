<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../common/head.jsp"%>
<title>用户注册</title>
<style type="text/css">
.header {
	text-align: center;
}

.header h1 {
	font-size: 200%;
	color: #333;
	margin-top: 30px;
}

.header p {
	font-size: 14px;
}
</style>

</head>
<body>

	<div class="header">
		<div class="am-g">
			<h1>知识库管理系统注册</h1>
			<p>知识库管理系统软件将来自工作中、生活中、培训中、组织内的各类资料和领域知识进行管理维护并提供关联分析和综合查询服务。</p>
		</div>
		<hr />
	</div>
	<div class="am-g" style="background: white">
		<div class="am-u-lg-4 am-u-md-8 am-u-sm-centered">
			<form method="post" class="am-form" id="login-form"
				action="register?action=register">
				<label for="email">邮箱:</label> <input type="email" id="email"
					value=""> <br> <label for="password">密码:</label> <input
					type="password" id="password" value=""><br> <label
					for="userName">用户名:</label> <br /> <input type="text"
					id="userName" name="userName" value=""> <br>
				<div class="am-cf">
					<input type="submit" name="" value="立即注册" id="login-button"
						class="am-btn am-btn-primary am-btn-sm am-fl"> 
					<a class="am-btn am-btn-default am-btn-sm am-fl" href="javascript:history.go(-1)">返回</a>
				</div>
			</form>
			<hr>
			<p>© 2016 中国软件杯 知识库管理系统 启航队</p>
		</div>
	</div>

</body>
</html>