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
<script type="text/javascript">
	$(function() {
		$(":input[name='email']").change(function() {
			var val = $(this).val();
			val = $.trim(val);

			if (val != null) {
				var url = "check";
				var args = {
					"userEmail" : val,
					"time" : new Date()
				};
				$.post(url, args, function(data) {
					$("#emailMsg").html(data);
				});
			}
		});
		$(":input[name='password']").change(function() {
			var val = $(this).val();
			val = $.trim(val);

			if (val != null) {
				var url = "check";
				var args = {
					"userPassword" : val,
					"time" : new Date()
				};
				$.post(url, args, function(data) {
					$("#pwdMsg").html(data);
				});
			}
		});
		$(":input[name='repassword']").change(function() {
			var val = $(this).val();
			val = $.trim(val);
			var val2 = $(":input[name='password']").val();
			val2 = $.trim(val2);
			if (val != null) {
				var url = "check";
				var args = {
					"repassword" : val,
					"userPassword" : val2,
					"time" : new Date()
				};
				$.post(url, args, function(data) {
					$("#repwdMsg").html(data);
				});
			}
		});
	})
</script>
</head>
<body>

	<div class="header">
		<div class="am-g">
			<h2>知识库管理系统注册</h2>
			<p>知识库管理系统软件将来自工作中、生活中、培训中、组织内的各类资料和领域知识进行管理维护并提供关联分析和综合查询服务。</p>
		</div>
		<hr />
	</div>
	<div class="am-g" style="background: white">
		<div class="am-u-lg-4 am-u-md-8 am-u-sm-centered">
			<form method="post" class="am-form" id="login-form"
				action="register?action=register">
				<label for="email">邮箱:</label> <input type="email" id="email"
					name="email" value="" required> <label id="emailMsg"></label><br>
				<label for="password">密码:</label> <input type="password"
					id="password" name="password" value="" required><label
					id="pwdMsg"></label><br> <label for="password">确认密码:</label> <input
					type="password" id="repassword" name="repassword" value="" required><label
					id="repwdMsg"></label><br> <label for="userName">用户名:</label>
				<br /> <input type="text" id="userName" name="userName" value=""
					required><label id="nameMsg"></label> <br>
				<div class="am-cf">
					<input type="submit" name="submit" value="立即注册" id="login-button"
						class="am-btn am-btn-primary am-btn-sm am-fl" 	data-am-modal="{target: '#my-modal-loading'}"> <a
						class="am-btn am-btn-default am-btn-sm am-fl"
						href="javascript:history.go(-1)">返回</a>
					<div class="am-modal am-modal-loading am-modal-no-btn"
						tabindex="-1" id="my-modal-loading">
						<div class="am-modal-dialog">
							<div id="emailMsg" class="am-modal-hd" 
							>发送邮箱中...</div>
							<div class="am-modal-bd">
								<span class="am-icon-spinner am-icon-spin"></span>
							</div>
						</div>
					</div>
				</div>
			</form>
			<hr>
			<p>© 2016 中国软件杯 知识库管理系统 启航队</p>
		</div>
	</div>

</body>
</html>