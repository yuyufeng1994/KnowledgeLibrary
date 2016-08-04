<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>我的资料</title>
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
				<div class="am-cf am-padding am-padding-bottom-0">
					<div class="am-fl am-cf">
						<strong class="am-text-primary am-text-lg">我的资料</strong> / <small>个人信息</small>
					</div>
				</div>

				<hr>

				<div class="am-g">
					<div class="am-u-sm-8 am-u-sm-centered">
						<form class="am-form">
							<table class="am-table table-main">
								<tr>
									<td><input id="userId" type="hidden"
										value="${user.userId }" /> <!-- 用户头像 --></td>
									<td><lable for="userName">用户名:</lable></td>
									<td>
										<div id="name-box" class="  am-form-icon am-form-feedback">
											<input type="text" id="userName" class="am-form-field"
												value="${user.userName}" placeholder="${user.userName}">
											<!--  am-form-success
											<span class="am-icon-check"></span>
											-->
										</div>
									</td>
								</tr>
								<tr>
									<td></td>
									<td></td>
									<td>
										<div class="am-form-group">
											<div class="am-u-sm-10 am-u-sm-offset-2">
												<button type="button" id="user-submit"
													class="am-btn am-btn-default">提交修改</button>
											</div>
										</div>
									</td>
								</tr>
							</table>
						</form>
					</div>

				</div>
			</div>

			<footer class="admin-content-footer">
				<hr>
				<p class="am-padding-left">© 2014 AllMobilize, Inc. Licensed
					under MIT license.</p>
			</footer>

		</div>
	</div>

	<%@include file="../common/footer.jsp"%>
	<!-- content end -->
</body>
<script type="text/javascript">
	$(function() {
		$("#user-submit").click(function() {
			var userName = $("#userName").val();
			var userId = $("#userId").val();
			var userPhoto = "11";
			var url = "user/update-user";
			var args = {
				"userName" : userName,
				"userId" : userId,
				"userPhoto" : userPhoto,
				"time" : new Date()
			};
			$.post(url, args, function(data) {
				var userName = data.data.userName;
				$("#userName").attr("value",userName);
			}).success(function() {
				$("#userName").after("<span class='am-icon-check am-form-success'></span>");
				alert("修改成功");
			}).error(function() {
				alert("修改失败");
			});
		});
	});
</script>
</html>