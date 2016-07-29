<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>用户登录</title>
<%@include file="common/head.jsp"%>
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
			<h1>知识库管理系统</h1>
			<p>知识库管理系统软件将来自工作中、生活中、培训中、组织内的各类资料和领域知识进行管理维护并提供关联分析和综合查询服务。</p>
		</div>
		<hr />
	</div>
	<div class="am-g" style="background: white">
		<div class="am-u-lg-4 am-u-md-8 am-u-sm-centered">
			<!--	<h3>登录</h3>
			<hr>
			<div class="am-btn-group">
				<a href="#" class="am-btn am-btn-secondary am-btn-sm"><i
					class="am-icon-github am-icon-sm"></i> Github</a> <a href="#"
					class="am-btn am-btn-success am-btn-sm"><i
					class="am-icon-google-plus-square am-icon-sm"></i> Google+</a> <a
					href="#" class="am-btn am-btn-primary am-btn-sm"><i
					class="am-icon-stack-overflow am-icon-sm"></i> stackOverflow</a>
			</div>
			<br> <br>
 -->
			<form method="post" class="am-form" id="login-form">
				<label for="email">邮箱:</label> <input type="email" id="email"
					value=""> <br> <label for="password">密码:</label> <input
					type="password" id="password" value=""> <br> <label
					for="remember-me"> <input id="remember-me" type="checkbox"
					checked="checked"> 记住密码
				</label> <br />
				<div class="am-alert am-alert-danger" data-am-alert id="alert-div"></div>
				<div class="am-cf">
					<input type="submit" name="" value="登 录" id="login-button"
						data-am-loading="{spinner: 'circle-o-notch', loadingText: '登录中...', resetText: '登录'}"
						class="am-btn am-btn-primary am-btn-sm am-fl btn-loading-example">
					<input type="submit" name="" value="忘记密码 ^_^? "
						class="am-btn am-btn-default am-btn-sm am-fr">
				</div>
				<p align="right">
					还没有帐号？<a href="#">立即注册</a>
				</p>
			</form>
			<hr>
			<p>© 2016 中国软件杯 知识库管理系统 启航队</p>
		</div>
	</div>
</body>

<script type="text/javascript">
	var userInfo = {
		init : function() {
			var userPassword = $.cookie('userPassword');
			var userEmail = $.cookie('userEmail');
			if (userPassword == null || userEmail == null || userPassword == "null" || userEmail == "null" || userPassword == "" || userEmail == "") {
				$("#remember-me").attr("checked", false);
			}
			else {
				//userInfo.validateUser(userEmail, userPassword);
			}

		},
		validateUser : function(email, password) {
			$.AMUI.progress.start();
			$.post("login-submit", {
				userEmail : email,
				userPassword : password
			}, function(result) {

				$('#login-button').button('loading');
				if (result.success == true) {
					//记住密码
					if ($("#remember-me").is(':checked')) {
						$.cookie('userEmail', email, {
							expires : 7,
							path : '/lib'
						});
						$.cookie('userPassword', password, {
							expires : 7,
							path : '/lib'
						});
					}
					else {
						//取消记住密码
						$.cookie('userEmail', null);
						$.cookie('userPassword', null);

					}
					//进入首页
					window.location.href = "user/index";
				}
				else {
					//result.error
					$('#login-button').button('reset');
					$('#alert-div').html(result.error);
					$("#alert-div").show("fast");
				}
				$.AMUI.progress.done();

			});
		}
	}

	$(function() {
		$("#alert-div").hide();
		userInfo.init();
		$("#login-form").submit(function() {
			$('#login-button').button('loading');
			userInfo.validateUser(
				$("#email").val(),
				$("#password").val()
			)
			return false;
		})
	})
</script>
</html>