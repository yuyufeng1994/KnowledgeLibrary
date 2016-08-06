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
								<table class="am-table table-main am-vertical-align-middle">
									<tr>
										<td><input id="userId" type="hidden"
											value="${user.userId }" /> <!-- 用户头像 -->
										</td>
										<td><h2 for="name-box">邮箱:</h2></td>
										<td>
											<div id="name-box" class="  am-form-icon am-form-feedback">
												<input type="email" id="userEmail"
													class="am-form-field am-round" value="${user.userEmail}"
													placeholder="${user.userEmail}">
												<!--  am-form-success
											<span class="am-icon-check"></span>
											-->
											</div>
										</td>
										<td>
											<div class="am-form-group">
												<div class="am-u-sm-10 am-u-sm-offset-2">
													<button type="button" id="user-submit"
														class="am-btn am-btn-default"
														data-am-modal="{target: '#my-modal-loading'}">更换邮箱绑定</button>
													<div class="am-modal am-modal-loading am-modal-no-btn"
														tabindex="-1" id="my-modal-loading">
														<div class="am-modal-dialog">
															<div id="emailMsg" class="am-modal-hd">发送邮箱中...</div>
															<div class="am-modal-bd">
																<span class="am-icon-spinner am-icon-spin"></span>
															</div>
														</div>
													</div>
												</div>
											</div>
										</td>
									</tr>
									<tr>
										<td></td>
										<td><h2 for="userEmail">密码:</h2></td>
										<td>
											<div id="" class="  am-form-icon am-form-feedback">
												<span value="${user.userPassword}"> *******</span>
											</div>
										</td>
										<td>
											<div class="am-form-group">
												<div class="am-u-sm-10 am-u-sm-offset-2">
													<button type="button" class="am-btn am-btn-default"
														id="doc-prompt-toggle">修改密码</button>
												</div>
											</div>
										</td>
									</tr>
								</table>
							</form>
						</div>

					</div>
				</div>
			</div>

		</div>
	</div>
	<!-- 模态框 -->
	<div class="am-modal am-modal-prompt" tabindex="-1" id="my-prompt">
		<div class="am-modal-dialog">
			<div class="am-modal-hd ">修改密码</div>
			<hr>
			<div class="am-modal-bd">
				<label class="am-u-sm-2 am-form-label" for="oldPassword">旧密码
				</label><input id="oldPassword" type="password"
					class="am-modal-prompt-input am-round"><br /> <label
					class="am-u-sm-2 am-form-label" for="newPassword">新密码 </label> <input
					id="newPassword" type="password"
					class="am-modal-prompt-input am-round"><br /> <label
					class="am-u-sm-2 am-form-label" for="confirmPassword">确认 </label> <input
					id="confirmPassword" type="password"
					class="am-modal-prompt-input am-round">
			</div>
			<div class="am-modal-footer">
				<span class="am-modal-btn" data-am-modal-cancel>取消</span> <span
					id="pwd-submit" class="am-modal-btn" data-am-modal-confirm>提交</span>
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
				alert(data.data);
				var $modal = $('#my-modal-loading');
				$modal.modal('close');
				if(data.data=="发送邮箱成功"){
					window.location.href="login";
				}
			})
			.error(function(){
				var $modal = $('#my-modal-loading');
				$modal.modal('close');
				alert("修改失败");
			});
		});
	});
	$(function() {
		$('#doc-prompt-toggle').on('click', function() {
			$('#my-prompt').modal({
				relatedTarget : this,
				onConfirm : function(e) {
					var userId = $("#userId").val();
					var oldPassword = e.data[0];
					var newPassword = e.data[1];
					var confirmPassword = e.data[2];
					var url = "user/update-password";
					var args = {
						"userId" : userId,
						"oldPassword" : oldPassword,
						"newPassword" : newPassword,
						"confirmPassword" : confirmPassword,
						"time" : new Date()
					};
					$.post(url, args, function(data) {
						alert(data.data);
					});
				},
				onCancel : function(e) {
				}
			});
		});
	});
</script>
</html>