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
						<strong class="am-text-primary am-text-lg"><a href="javascript:history.go(-1)">返回</a></strong>
						/ <small>资源在线预览</small>
					</div>
				</div>
				<div class="am-u-sm-12">
					<div class="am-u-md-12 am-u-sm-12 am-u-lg-8">
						<div class="am-panel am-panel-default">
							<div class="am-panel-bd am-collapse am-in">
								<div class="widget-content">
									<div id="main-content">
										<input id="pdf-content" type="hidden" value="">
									</div>
								</div>
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
								${fileInfo.fileName }.${fileInfo.fileExt}<span class="am-icon-chevron-down am-fr"></span>
							</div>
							<div class="am-panel-bd am-collapse am-in am-cf"
								id="collapse-panel-file-info1">

								<ul class="am-list admin-content-file">
									
									<li>
										<p><span class="am-badge am-badge-secondary">大小</span> ${fileInfo.fileSizeFormat }</p>
									</li>
									<li>
										<p><span class="am-badge am-badge-secondary">上传时间</span> <fmt:formatDate value="${fileInfo.fileCreateTime }"
											pattern="yyyy-MM-dd HH:mm:ss" /></td></p>
									</li>
									<li>
										<p><span class="am-badge am-badge am-badge-secondary">类别</span> ${fileInfo.fileClassId }</p>
									</li>
									<li>
										<p><span class="am-badge am-badge-secondary">简介</span> ${fileInfo.fileBrief }</p>

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
<script type="text/javascript">
	var ext = "${fileInfo.fileExt}";
	var fileUrl = "user/thumbnail/"+"${fileInfo.fileUuid}"+"/";
	if(ext == "doc" || ext == "docx"|| ext == "xls"|| ext == "xlsx"|| ext == "ppt"|| ext == "pptx"){		
		PDFObject.embed(fileUrl+"pdf", document.getElementById("main-content")); //pdf预览插件
	}
</script>
</html>