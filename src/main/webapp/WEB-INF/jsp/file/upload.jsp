<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>知识导入</title>
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
				<div class="am-cf am-padding">
					<div class="am-fl am-cf">
						<strong class="am-text-primary am-text-lg">导入知识</strong> / <small>支持文件的批量上传及压缩包导入，支持Office文档、PDF、图像、音视频和图纸等各类型文件。</small>
					</div>
				</div>

				<div class="am-u-sm-12">

					<div id="uploader" class="wu-example">
						<!--用来存放文件信息-->
						<div id="thelist" class="uploader-list">
							
						</div>
						<div class="btns">
							<div id="picker">添加文件</div>
							<div id="unc-div" style="display: none;"
								class="am-alert am-alert-secondary" data-am-alert>
								<span class="am-icon-question-circle-o"></span>
								您选择的文件中含有压缩文件，请选择导入方式：
								<button id="if-unc" type="button"
									class="am-btn am-btn-xs am-btn-primary" data-am-button>导入压缩包</button>
								<script>
									$(function() {
										
										$.post("user/re-compress")
										
										var $toggleButton = $('#if-unc');
										$toggleButton.on('click', function() {
											setButtonStatus();
										});
								
										function setButtonStatus() {
											$.post("user/tog-compress", function(data) {
												if (data == "success") {
													var status = $toggleButton.hasClass('am-active') ? '解压导入' : '导入压缩包';
													$('#if-unc').text(status);
												}
											})
										}
									})
								</script>
							</div>
							<button id="ctlBtn" class="am-btn am-btn-warning am-radius">开始导入</button>
						</div>
					</div>

				</div>




			</div>
		</div>
		<%@include file="../common/footer.jsp"%>
		<!-- content end -->
	</div>
</body>
<script type="text/javascript">
	$list = $('#thelist'),
	$btn = $('#ctlBtn'),
	state = 'pending',
	uploader;

	var uploader = WebUploader.create({
		// swf文件路径
		swf : 'resource/webuploader/Uploader.swf',

		// 文件接收服务端。
		server : 'user/upload-file',

		// 选择文件的按钮。可选。
		// 内部根据当前运行是创建，可能是input元素，也可能是flash.
		pick : '#picker',

		// 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
		resize : false
	});

	// 当有文件被添加进队列的时候
	uploader.on('fileQueued', function(file) {
		$list.append('<div id="' + file.id + '" class="am-panel am-panel-secondary" style="margin-bottom:3px">' +
			'<div class="am-panel-hd">' +'<span class="state am-badge am-badge-secondary">等待上传...</span>'+" "+ file.name 
			+  '</div>' +
			
			'</div>');
		if (file.ext == "zip" || file.ext == "rar") {
			$("#unc-div").show("fast");
		}
	});
	// 文件上传过程中创建进度条实时显示。
	uploader.on('uploadProgress', function(file, percentage) {
		var $li = $('#' + file.id),
			$percent = $li.find('.am-progress .am-progress-bar');

		// 避免重复创建
		if (!$percent.length) {
			
			$percent = $('<div class="am-progress am-progress-striped  am-active">' +
				'<div class="am-progress-bar am-progress-bar-secondary"  style="width: 0%">' +
				'</div>' +
				'</div>').appendTo($li).find('.am-progress-bar');
		}

		$li.find('span.state').text('上传中');
		var res=  (percentage * 100).toFixed(2);
		$li.find('.am-progress-bar-secondary').text(res+"%");
		$percent.css('width', percentage * 100 + '%');
	});
	$btn.on('click', function() {
		if (state === 'uploading') {
			uploader.stop();
		}
		else {
			uploader.upload();
		}
	});

	uploader.on('uploadSuccess', function(file) {
		var $li = $('#' + file.id);
		$li.find('span.state').text('已上传');
		$percent = $li.find('.am-progress');
		$bar = $li.find('.am-progress-bar');
		$percent.removeClass("am-active").removeClass("am-progress-striped");
		$bar.addClass("am-progress-bar-success");
		$("#unc-div").hide("fast");
	});

	uploader.on('uploadError', function(file) {
		var $li = $('#' + file.id);
		$li.find('p.state').text('上传出错');
		$percent = $li.find('.am-progress');
		$bar = $li.find('.am-progress-bar');
		$percent.removeClass("am-active").removeClass("am-progress-striped");
		$bar.addClass("am-progress-bar-danger");
		$("#unc-div").hide("fast");
	});

	uploader.on('uploadComplete', function(file) {
		$('#' + file.id).find('.progress').fadeOut();
	
	})
</script>
</html>