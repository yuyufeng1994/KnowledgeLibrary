<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>我的资源</title>
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
						<strong class="am-text-primary am-text-lg">导入成功</strong> / <small>我刚刚导入成功的文件</small>
					</div>
				</div>
				<hr>
				<div class="am-u-sm-12">
					<table
						class="am-table table-hover am-table-radius am-table-hover am-table-compact am-table-striped">
						<thead>
							<tr>
								<th>文件ID</th>
								<th>缩略图</th>
								<th>文件名称</th>
								<th>文件大小</th>
								<th>文件状态</th>
								<th>上传时间</th>
								<th>文件类别</th>
								<th>管理</th>
								<th>在线预览</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${files}" var="f">
								<tr title="简介：${f.fileBrief}">
									<td>${f.fileId }</td>
									<td><a href="user/thumbnail/${f.fileUuid}/png"><img
											src="user/thumbnail/${f.fileUuid}/png" alt="null"
											class="am-img-thumbnail"
											style="width: 50px; height: 50px; overflow: hidden"></a></td>
									<td title="${f.fileName }.${f.fileExt }">${f.hiddenedFileName }.${f.fileExt }</td>
									<td>${f.fileSizeFormat }</td>
									<td>${f.fileStateStr }</td>
									<td><fmt:formatDate value="${f.fileCreateTime }"
											pattern="yyyy-MM-dd HH:mm:ss" /></td>
									<td>${f.classificationName }</td>

									<td>
										<div class="am-dropdown" data-am-dropdown="">
											<button
												class="am-btn am-btn-default am-btn-xs am-dropdown-toggle"
												data-am-dropdown-toggle="">
												<span class="am-icon-cog"></span> <span
													class="am-icon-caret-down"></span>
											</button>
											<ul class="am-dropdown-content">
												<li><a href="user/edit/${f.fileUuid}">修改</a></li>
												<li><a href="user/download/${f.fileUuid}/${f.fileExt}">下载</a></li>
												<li><a onclick="del(this,'${f.fileId}')"
													style="cursor: pointer;">删除</a></li>
											</ul>
											<script type="text/javascript">
												function del(mythis, id) {
													var $mthis = $(mythis);
													$.post("user/file-del/" + id, function(data) {
														if (data.success == true) {
															$mthis.parent().parent().parent().parent().parent().hide("fast");
														} else {
															alert("删除失败！");
														}
											
											
											
													})
											
											
												}
											</script>
										</div>
									</td>
									<td><a class="am-btn am-btn-secondary am-btn-xs"
										target="_blank" href="user/file/${f.fileUuid}"> 预览 <i
											class="am-icon-cloud"></i>
									</a></td>
								</tr>
							</c:forEach>

						</tbody>
					</table>

				</div>
				<div class="am-g">
					<div class="am-u-sm-12 am-u-md-3">
						<a href="user/upload" class="am-btn am-btn-primary">继续导入</a>
					</div>
				</div>

			</div>
		</div>
	</div>

	<%@include file="../common/footer.jsp"%>
	<!-- content end -->
</body>
</html>