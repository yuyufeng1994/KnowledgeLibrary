<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>SOKLIB后台管理-分类管理</title>
<%@include file="../common/head.jsp"%>
<link rel="stylesheet" type="text/css"
	href="resource/webuploader/webuploader.css">
<!--引入JS-->
<script type="text/javascript" src="resource/webuploader/webuploader.js"></script>
</head>
<body>
	<%@include file="../common/admin-header.jsp"%>
	<div class="am-cf admin-main">
		<!-- sidebar start -->
		<%@include file="../common/admin-slidebar.jsp"%>
		<!-- sidebar end -->
		<!-- content start -->
		<div class="admin-content">
			<div class="admin-content-body">
				<div class="am-cf am-padding am-padding-bottom-0">
					<div class="am-fl am-cf">
						<strong class="am-text-primary am-text-lg">分类管理</strong> / <small>对系统中的分类进行管理</small>
					</div>
				</div>
				<hr>
				<div class="am-g">
					<div class="am-u-md-3 am-form">
						<div class="am-form-group">
							<label for="uploader-demo" class="am-u-sm-3 am-form-label">配图
							</label>
							<div id="uploader-demo">
								<div id="fileList" class="uploader-list">
									<div id="WU_FILE_0"
										class="file-item thumbnail upload-state-done">
										<img id="fileClassPicture" src=""
											style="width: 150px; height: 150px" class="am-img-thumbnail">
									</div>
								</div>

								<center id="filePicker" style="margin-top: 5px;">选择图片</center>
							</div>
							<script type="text/javascript">
							
							</script>

						</div>
					</div>
					<div class="am-u-md-7 am-form">
						<div class="am-form-group">
							<label for="user-name" class="am-u-sm-3 am-form-label">名称
							</label> <input type="text" id="fileClassName" placeholder="名称 / Name">
						</div>

						<div class="am-form-group">
							<label for="fileClassBrief" class="am-u-sm-3 am-form-label">简介
							</label>
							<textarea class="" rows="5" id="fileClassBrief"
								placeholder="输入分类简介"></textarea>
						</div>
						<input name="fileClassId" id="fileClassId" type="hidden" value="">


					</div>
					<div class="am-u-md-2 am-form">
						<label for="" class="am-u-sm-3 am-form-label">提示： </label>
					</div>
				</div>
				<div class="am-g">
					<div class="am-panel am-panel-default">
						<div class="am-panel-hd">
							<div class="am-btn-group">
								<button class="am-btn am-btn-primary am-round" id="reply-button"
									onclick="reply()">
									<i class="am-icon-angle-left"></i>
								</button>
								<button class="am-btn am-btn-primary am-round"
									id="parent-button" onclick="fileClassSureParent()"></button>
							</div>
						</div>
						<div class="am-panel-bd">
							<div class="" id="child-content"></div>
						</div>
					</div>




				</div>
			</div>
		</div>




	</div>

	<%@include file="../common/footer.jsp"%>
</body>
<script type="text/javascript">
	var classId = 1
	var className = "";
	var parentId = 1;
	var classBrief = "";
	var classPicture = "";
	var resdata;
	function loadTypes() {
		if (classId == null) {
			classId = 1;
		}
		$.get("child-file-class/" + classId, function(data) {
			resdata = data.data;
			var str = '';
			for (var i = 1; i < data.data.length; i++) {
				str += "<div class='am-btn-group' style='margin-top:10px;'>" +
					"<button class='button-margin am-btn am-btn-default am-round'" + "onclick='fileClassSure(" + i + ")'>" + data.data[i].classificationName + "</button>" +
					"<button class='button-margin am-btn am-btn-default am-round'" +
					"onClick=changeType(" + data.data[i].classificationId + "," + "'" + data.data[i].classificationName + "'" + ")>" +
					"<i class='am-icon-angle-right'></i>" +
					"</button></div>&nbsp;";
			}
			if (data.data.length == 1) {
				str = '无子节点...';
			}
			parentId = data.data[0].parentId;
			className = data.data[0].classificationName;
			classId = data.data[0].classificationId;
			classBrief = data.data[0].classificationBrief;
			classPicture = data.data[0].classificationPicture;
			$("#parent-button").text(className)
			$("#child-content").html(str);

			fileClassSureParent();

		})

	}

	function changeType(id, name) {
		classId = id;
		className = name;
		loadTypes();

	}


	function reply() {
		classId = parentId;
		loadTypes();
	}


	function fileClassSure(i) {
		uploader.options.server = 'admin/update-classpic/' + resdata[i].classificationId;
		//console.log(uploader.options.server)
		$("#fileClassId").val(resdata[i].classificationId)
		$("#fileClassName").val(resdata[i].classificationName)
		$("#fileClassBrief").val(resdata[i].classificationBrief)
		$("#fileClassPicture").attr("src", "thumbnail/" + resdata[i].classificationPicture)
	}
	function fileClassSureParent() {
		$("#fileClassId").val(classId)
		$("#fileClassName").val(className)
		$("#fileClassBrief").val(classBrief)
		$("#fileClassPicture").attr("src", "thumbnail/" + classPicture)
	}
	loadTypes();


	//webuploader
	var $list = $("#fileList");
	// 初始化Web Uploader
	var uploader = WebUploader.create({

		// 选完文件后，是否自动上传。
		auto : true,

		// swf文件路径
		swf : 'resource/webuploader/Uploader.swf',

		// 文件接收服务端。
		server : 'admin/update-classpic/' + 1,
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
	uploader.on('fileQueued', function(file) {
		var $li = $(
				'<div id="' + file.id + '" class="file-item thumbnail">' +
				"<img id='fileClassPicture' style='width: 150px; height: 150px' class='am-img-thumbnail' >" +
				'</div>'
			),
			$img = $li.find('img');


		// $list为容器jQuery实例
		$list.html($li);

		// 创建缩略图
		// 如果为非图片文件，可以不用调用此方法。
		// thumbnailWidth x thumbnailHeight 为 100 x 100
		uploader.makeThumb(file, function(error, src) {
			if (error) {
				$img.replaceWith('<span>不能预览</span>');
				return;
			}

			$img.attr('src', src);
		}, 150, 150);
	});
	// 文件上传过程中创建进度条实时显示。

	// 文件上传成功，给item添加成功class, 用样式标记上传成功。
	uploader.on('uploadSuccess', function(file) {
		var $li = $('#' + file.id),
			$error = $li.find('div.error');

		// 避免重复创建
		if (!$error.length) {
			$error = $('<div class="error am-alert am-alert-success"></div>').appendTo($li);
		}
		$error.text('修改成功');
	});

	// 文件上传失败，显示上传出错。
	uploader.on('uploadError', function(file) {
		var $li = $('#' + file.id),
			$error = $li.find('div.error');

		// 避免重复创建
		if (!$error.length) {
			$error = $('<div class="error  am-alert am-alert-danger"></div>').appendTo($li);
		}

		$error.text('修改失败');
	});

	// 完成上传完了，成功或者失败，先删除进度条。
	uploader.on('uploadComplete', function(file) {});
</script>
</html>