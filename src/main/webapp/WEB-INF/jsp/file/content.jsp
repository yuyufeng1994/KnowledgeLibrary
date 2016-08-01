<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>资源主体</title>
<%@include file="../common/head.jsp"%>
<style type="text/css">
#main-content {
	height: 500px;
}
</style>
</head>
<body>
	<%@include file="../common/header.jsp"%>
	<div class="am-cf admin-main">
		<!-- sidebar start -->
		<!-- sidebar end -->
		<!-- content start -->
		<div class="admin-content">
			<div class="admin-content-body">
				<div class="am-cf am-padding">
					<div class="am-fl am-cf">
						<strong class="am-text-primary am-text-lg"><a
							href="javascript:history.go(-1)">返回</a></strong> / <small>资源在线预览</small>
					</div>
				</div>
				<div class="am-u-sm-12">
					<div class="am-u-md-12 am-u-sm-12 am-u-lg-8">
						<div class="am-panel am-panel-default">
							<div class="am-panel-bd am-collapse am-in">
								<div id="main-content"></div>
							</div>
						</div>
						<div class="am-panel am-panel-default">
							<div class="am-panel-hd am-cf"
								data-am-collapse="{target: '#collapse-panel-comment'}">
								评论<span class="am-icon-chevron-down am-fr"></span>
							</div>
							<div id="collapse-panel-comment" class="am-in">《》！</div>
							<div class="am-panel-bd am-collapse am-in am-cf"
								id="collapse-panel-file-info">

								<ul class="am-pagination am-fr admin-content-pagination">
									<li class="am-disabled"><a href="#">«</a></li>
									<li class="am-active"><a href="#">1</a></li>
									<li><a href="#">2</a></li>
									<li><a href="#">3</a></li>
									<li><a href="#">4</a></li>
									<li><a href="#">5</a></li>
									<li><a href="#">»</a></li>
								</ul>
							</div>
						</div>
					</div>

					<div class="am-u-md-12 am-u-sm-12 am-u-lg-4">
						<div class="am-panel am-panel-default">
							<div class="am-panel-hd am-cf"
								data-am-collapse="{target: '#collapse-panel-file-info1'}">
								${fileInfo.fileName }.${fileInfo.fileExt} <span
									class="am-icon-chevron-down am-fr"></span>
							</div>
							<div class="am-panel-bd am-collapse am-in am-cf"
								id="collapse-panel-file-info1">

								<ul class="am-list admin-content-file">

									<li>
										<p>
											<span class="am-badge am-badge-secondary">大小</span>
											${fileInfo.fileSizeFormat }
										</p>
									</li>
									<li>
										<p>
											<span class="am-badge am-badge-secondary">上传时间</span>
											<fmt:formatDate value="${fileInfo.fileCreateTime }"
												pattern="yyyy-MM-dd HH:mm:ss" />
											</td>
										</p>
									</li>
									<li>
										<p>
											<span class="am-badge am-badge am-badge-secondary">类别</span>
											${fileInfo.fileClassId }
										</p>
									</li>
									<li>
										<p>
											<span class="am-badge am-badge-secondary">简介</span>
											${fileInfo.fileBrief }
										</p>

									</li>
								</ul>
							</div>

						</div>
						<div class="am-panel am-panel-default">
							<div class="am-panel-hd am-cf"
								data-am-collapse="{target: '#collapse-panel-link-file'}">
								关联文档<span class="am-icon-chevron-down am-fr"></span>
							</div>
							<div class="am-panel-bd am-collapse am-in am-cf"
								id="collapse-panel-link-file">

								<ul class="am-list admin-content-file">
									<li>
										<p>3.3 of 5MB - 5 mins - 1MB/Sec</p>

									</li>
									<li>
										<p>3.3 of 5MB - 5 mins - 3MB/Sec</p>
									</li>
									<li>
										<p>3.3 of 5MB - 5 mins - 3MB/Sec</p>
									</li>
								</ul>
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
<script src="resource/script/pdfobject.min.js"></script>
<script type="text/javascript" src="resource/ckplayer/ckplayer.js"
	charset="utf-8"></script>
<script src="resource/script/file-view.js"></script>

<script type="text/javascript">
	//判断是哪种浏览方式在网页中呈现
	var viewJudge = {
		//office类型，用pdf呈现
		pdf : function(ext) {
			if (ext == "doc" || ext == "docx" || ext == "xls" || ext == "xlsx" || ext == "ppt" || ext == "pptx" || ext == "pdf") {
				return true;
			}
			else {
				return false;
			}
		},
		//图片类型
		jpg : function(ext) {
			if (ext == "png" || ext == "gif" || ext == "jpg" || ext == "jpeg" || ext == "bmp" || ext == "pptx" || ext == "pdf") {
				return true;
			}
			else {
				return false;
			}
		},
		//视频类型
		flv : function(ext) {
			if (ext == "mp4" || ext == "avi" || ext == "flv" || ext == "rmvb" || ext == "wmv" || ext == "mkv") {
				return true;
			}
			else {
				return false;
			}
		},
		txt : function(ext) {
			if (ext == "txt") {
				return true;
			}
			else {
				return false;
			}
		},
		mp3 : function(ext) {
			if (ext == "mp3") {
				return true;
			}
			else {
				return false;
			}
		}
	}


	var ext = "${fileInfo.fileExt}";
	var uuid = "${fileInfo.fileUuid}";
	var fileUrl = "user/thumbnail/" + "${fileInfo.fileUuid}" + "/";
	var $content = $("#main-content");
	if (viewJudge.pdf(ext)) {
		PDFObject.embed(fileUrl + "pdf", document.getElementById("main-content")); //pdf预览插件
	}
	else if (viewJudge.jpg(ext)) {
		$content.html("<img src=" + fileUrl + "jpg" + " class='am-img-thumbnail am-radius'>");
		$content.css("height", "auto");
	}
	else if (viewJudge.flv(ext)) {
		//function ckmarqueeadv(){return ''}//文字广告
		var flashvars = {
			f : 'rtmp://192.168.1.104/lib/' + uuid + '.flv',
			c : 0
		};
		var params = {
			bgcolor : '#FFF',
			allowFullScreen : true,
			allowScriptAccess : 'always',
			wmode : 'transparent'
		};
		CKobject.embedSWF('resource/ckplayer/ckplayer.swf', 'main-content', 'ckplayer_a1', '100%', '100%', flashvars, params);
	}else if(viewJudge.txt(ext)){
		$content.load(fileUrl+"txt");
	}else if(viewJudge.mp3(ext)){
		$content.html("<audio src='"+fileUrl+"mp3"+"' controls='controls'>	</audio>");
	}
	

</script>
</html>