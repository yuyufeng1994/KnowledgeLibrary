<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>账号安全</title>
<%@include file="../common/head.jsp"%>
</head>
<body>
	<%@include file="../common/header.jsp"%>
	<div class="am-cf admin-main">
		<!-- sidebar start -->
		<%@include file="../common/slidebar.jsp"%>
		<!-- sidebar end -->
		<!-- content start -->
		<div class="admin-content">
			<div class="admin-content-body">
				<div class="am-cf am-padding">
					<div class="am-fl am-cf">
						<strong class="am-text-primary am-text-lg">我的资料</strong> / <small>账号设置</small>
					</div>
				</div>
				<div class="am-u-sm-12">
					<div class="am-g">
						<div class="am-u-sm-8 am-u-sm-centered">
							<form class="am-form">
								<table class="am-table table-main">
									<tr>
										<td><input id="userId" type="hidden"
											value="${user.userId }" /> <!-- 用户头像 --></td>
										<td><h2 for="userEmail">邮箱:</h2></td>
										<td>
											<div id="name-box" class="  am-form-icon am-form-feedback">
												<input type="email" id="userEmail" class="am-form-field"
													value="${user.userEmail}" placeholder="${user.userEmail}">
												<!--  am-form-success
											<span class="am-icon-check"></span>
											-->
											</div>
										</td>
										<td>
											<div class="am-form-group">
												<div class="am-u-sm-10 am-u-sm-offset-2">
													<button type="button" id="user-submit"
														class="am-btn am-btn-default">更换邮箱绑定</button>
												</div>
											</div>
										</td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										
									</tr>
								</table>
							</form>
						</div>

					</div>
				</div>
			</div>

		</div>
	</div>
	</div>

	<%@include file="../common/footer.jsp"%>
	<!-- content end -->
</body>
<script type="text/javascript">
	$(function() {
		$("#user-submit").click(function() {
			var userEmail = $("#userEmail").val();
			var userId = $("#userId").val();
			var userPhoto = "11";
			var url = "user/update-userEmail?action=register";
			var args = {
				"userEmail" : userEmail,
				"userId" : userId,
				"time" : new Date()
			};
			$.post(url, args, function(data) {
				alert("发送邮箱中。。。。");
				//var userName = data.data.userEmail;
				//$("#userEmail").attr("value",userEmail);
			}).success(function() {
				alert("修改成功");
				window.location.href = "login";
			}).error(function() {
				alert("修改失败");
			});
		});
	});
</script>
</html>