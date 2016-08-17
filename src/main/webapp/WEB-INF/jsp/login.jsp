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

.get {
	background: #1E5B94;
	color: #fff;
	text-align: center;
	padding: 100px 0;
}

.get-title {
	font-size: 200%;
	border: 2px solid #fff;
	padding: 20px;
	display: inline-block;
}

.get-btn {
	background: #fff;
}

.detail {
	background: #fff;
}

.detail-h2 {
	text-align: center;
	font-size: 150%;
	margin: 40px 0;
}

.detail-h3 {
	color: #1f8dd6;
}

.detail-p {
	color: #7f8c8d;
}

.detail-mb {
	margin-bottom: 30px;
}

.hope {
	background: #0bb59b;
	padding: 50px 0;
}

.hope-img {
	text-align: center;
}

.hope-hr {
	border-color: #149C88;
}

.hope-title {
	font-size: 140%;
}

.about {
	background: #fff;
	padding: 40px 0;
	color: #7f8c8d;
}

.about-color {
	color: #34495e;
}

.about-title {
	font-size: 180%;
	padding: 30px 0 50px 0;
	text-align: center;
}

.footer p {
	color: #7f8c8d;
	margin: 0;
	padding: 15px 0;
	text-align: center;
	background: #2d3e50;
}
</style>
</head>
<body>
	<header class="am-topbar am-topbar-fixed-top">
		<div class="am-container">
			<h1 class="am-topbar-brand">
				<a>SOKLIB - 知识库管理系统</a>
			</h1>

			<div class="am-collapse am-topbar-collapse" id="collapse-head">

				<div class="am-topbar-right">
					<a href="registerUI" class="am-btn am-btn-secondary am-topbar-btn am-btn-sm">
						<span class="am-icon-pencil"></span> 注册
					</a>
				</div>

				<div class="am-topbar-right">
					<button class="am-btn am-btn-primary am-topbar-btn am-btn-sm" onclick="loginWindow()">
						<span class="am-icon-user"></span> 登录
					</button>
				</div>
			</div>
		</div>
	</header>
	<div class="get">
		<div class="am-g">
			<div class="am-u-lg-12" id="adver">
				<h1 class="get-title">SOKLIB - 知识库管理系统</h1>

				<h2 class="detail-h2">Intelligent 智能、Convenient 便捷、Effective 有效</h2>
				<p>将来自工作中、生活中、培训中、组织内的各类资料和领域知识进行管理维护并提供关联分析和综合查询服务</p>
				
				
				<p>特色：智能提取演绎、清晰的知识网络、多格式在线浏览、智能推荐</p>
				<p>
					<a class="am-btn am-btn-sm get-btn" onclick="loginWindow()">立即登录</a>
				</p>
			</div>

			<div class="am-u-lg-12" id="userlogin" style="display: none">
				<div class="am-u-lg-4 am-u-md-8 am-u-sm-centered">
					<h1>用户登录</h1>

					<form method="post" class="am-form" id="login-form">
						<label for="email">邮箱:</label> <input type="email" id="email" placeholder="邮箱作为帐号登录"
							value=""> <br> <label for="password">密码:</label> <input
							type="password" id="password" value=""  placeholder="填写密码"> <br> <label
							for="remember-me"> <input id="remember-me" 
							type="checkbox" checked="checked"> 记住密码
						</label> <br />
						<div class="am-alert am-alert-danger" data-am-alert id="alert-div"></div>
						<div class="am-cf">
							<input type="submit" name="" value="登 录" id="login-button"
								data-am-loading="{spinner: 'circle-o-notch', loadingText: '登录中...', resetText: '登录'}"
								class="am-btn am-btn-default am-btn-sm am-fl btn-loading-example">
							<input type="button" onclick="javascript:alert('请联系管理员')"
								value="忘记密码 ^_^? " class="am-btn am-btn-default am-btn-sm am-fr">
						</div>
						<p align="right">
							还没有帐号？<a href="registerUI">立即注册</a>
						</p>
					</form>

				</div>

			</div>
		</div>
	</div>
	<script type="text/javascript">
		function loginWindow() {
			$("#adver").hide("fast")
			$("#userlogin").show()
		}
	</script>
	<footer class="footer">
		<p>©2016 中国软件杯，启航队</p>
	</footer>

</body>

<script type="text/javascript">
	var userInfo = {
		init : function() {
			var userPassword = $.cookie('userPassword');
			var userEmail = $.cookie('userEmail');
			if (userPassword == null || userEmail == null
					|| userPassword == "null" || userEmail == "null"
					|| userPassword == "" || userEmail == "") {
				$("#remember-me").attr("checked", false);
			} else {
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
					} else {
						//取消记住密码
						$.cookie('userEmail', null);
						$.cookie('userPassword', null);

					}
					//进入首页
					window.location.href = "user/index";
				} else {
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
			userInfo.validateUser($("#email").val(), $("#password").val())
			return false;
		})
	})
</script>
</html>