<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>我的资料</title>
<%@include file="../common/head.jsp"%>
<link rel="stylesheet" type="text/css"
	href="resource/webuploader/webuploader.css">
<!--引入JS-->
<script type="text/javascript" src="resource/webuploader/webuploader.js"></script>
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
										value="${user.userId }" /> <!-- 用户头像 -->
											<div class="am-u-md-8">
												<div id="uploader-demo">
													<div id="fileList" class="uploader-list">
														<div id="WU_FILE_0"
															class="file-item thumbnail upload-state-done">
															<img src="user/photo/${user.userPhoto }"
																style="width: 95px; height: 95px"
																class="am-img-thumbnail">
														</div>
													</div>
													<div id="filePicker">选择头像</div>
												</div>
											</div> <script type="text/javascript">
												var userId = "${user.userId}";
												var $list = $("#fileList");
												// 初始化Web Uploader
												var uploader = WebUploader
														.create({

															// 选完文件后，是否自动上传。
															auto : true,

															// swf文件路径
															swf : 'resource/webuploader/Uploader.swf',

															// 文件接收服务端。
															server : 'user/update-img/'+ userId,

															// 选择文件的按钮。可选。
															// 内部根据当前运行是创建，可能是input元素，也可能是flash.
															pick : '#filePicker',

															// 只允许选择图片文件。
															accept : {
																title : 'Images',
																extensions : 'gif,jpg,jpeg,bmp,png',
																mimeTypes : 'image/*'
															}
														});
												// 当有文件添加进来的时候
												uploader
														.on(
																'fileQueued',
																function(file) {
																	var $li = $('<div id="' + file.id + '" class="file-item thumbnail">'
																			+ "<img class='am-img-thumbnail'>"
																			+ '</div>'), $img = $li
																			.find('img');

																	// $list为容器jQuery实例
																	$list
																			.html($li);

																	// 创建缩略图
																	// 如果为非图片文件，可以不用调用此方法。
																	// thumbnailWidth x thumbnailHeight 为 100 x 100
																	uploader
																			.makeThumb(
																					file,
																					function(
																							error,
																							src) {
																						if (error) {
																							$img
																									.replaceWith('<span>不能预览</span>');
																							return;
																						}

																						$img
																								.attr(
																										'src',
																										src);
																					},
																					92,
																					92);
																});
												// 文件上传过程中创建进度条实时显示。

												// 文件上传成功，给item添加成功class, 用样式标记上传成功。
												uploader
														.on(
																'uploadSuccess',
																function(file) {
																	var $li = $('#'
																			+ file.id), $error = $li
																			.find('div.error');

																	// 避免重复创建
																	if (!$error.length) {
																		$error = $(
																				'<div class="error am-alert am-alert-success"></div>')
																				.appendTo(
																						$li);
																	}
																	$error
																			.text('修改成功');
																});

												// 文件上传失败，显示上传出错。
												uploader
														.on(
																'uploadError',
																function(file) {
																	var $li = $('#'
																			+ file.id), $error = $li
																			.find('div.error');

																	// 避免重复创建
																	if (!$error.length) {
																		$error = $(
																				'<div class="error  am-alert am-alert-danger"></div>')
																				.appendTo(
																						$li);
																	}

																	$error
																			.text('修改失败');
																});

												// 完成上传完了，成功或者失败，先删除进度条。
												uploader.on('uploadComplete',
														function(file) {

														});
											</script>
										</td>
									<td><lable for="userName"><h2>用户名:</h2></lable></td>
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