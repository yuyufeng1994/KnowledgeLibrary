<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>首页</title>
<style>
.am-g a {
	
}
</style>
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
						<strong class="am-text-primary am-text-lg">首页</strong> / <small>信息概览</small>
					</div>
				</div>

				<ul
					class="am-avg-sm-1 am-avg-md-4 am-margin am-padding am-text-center admin-content-list ">
					<li><a href="user/public/1/1" class="am-text-success"><span
							class="am-icon-btn am-icon-file-text"></span><br />今日录入<br /> <span
							id="count-today">0</span></a></li>
					<li><a href="user/public/1/1" class="am-text-warning"><span
							class="am-icon-btn am-icon-briefcase"></span><br />可用文档<br /> <span
							id="count-publicfiles">0</span></a></li>
					<li><a href="user/myfiles/1" class="am-text-danger"><span
							class="am-icon-btn am-icon-recycle"></span><br />我的资源<br /> <span
							id="count-userfiles">0</span></a></li>
					<li><a href="user/myforks/-1/1" class="am-text-secondary"><span
							class="am-icon-btn am-icon-user-md"></span><br />我的收藏<br /> <span
							id="count-forkfiles">0</span></a></li>
				</ul>
				<script type="text/javascript">
					$.get("user/count-publicfiles", function(data) {
						$("#count-publicfiles").text(data.data);
					})
					$.get("user/count-forkfiles", function(data) {
						$("#count-forkfiles").text(data.data);
					})
					$.get("user/count-today", function(data) {
						$("#count-today").text(data.data);
					})
					$.get("user/count-userfiles", function(data) {
						$("#count-userfiles").text(data.data);
					})
				</script>
				<div class="am-g">
					<div class="am-u-sm-12">
						<table class="am-table am-table-bd am-table-striped">
							<thead>
								<tr>
									<th colspan="5">近期热门</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${hot }" var="f">
									<tr>
										<td><img src="user/thumbnail/${f.fileUuid}/png"
											alt="null" class="am-img-thumbnail"
											style="width: 50px; height: 50px; overflow: hidden"></td>
										<td><a target="_blank"
											title="${f.fileName }.${f.fileExt }"
											href="user/file/${f.fileUuid}">${f.fileName }.${f.fileExt }</a></td>
										<td style="width:60%">简介：${f.fileBrief }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>

				<div class="am-g">
					<div class="am-u-md-6">
						<div class="am-panel am-panel-default">
							<div class="am-panel-hd am-cf"
								data-am-collapse="{target: '#collapse-panel-re'}">
								最近阅读<span class="am-icon-chevron-down am-fr"></span>
							</div>
							<div id="collapse-panel-re" class="am-in">
								<table class='am-table am-table-striped am-text-nowrap'>
									<c:forEach items="${recent }" var="f">
										<tr>
											<td><img src="user/thumbnail/${f.fileUuid}/png"
												alt="null" class="am-img-thumbnail"
												style="width: 30px; height: 30px; overflow: hidden"></td>
											<td><a target="_blank"
												title="${f.fileName }.${f.fileExt }"
												href="user/file/${f.fileUuid}">${f.hiddenedFileName }.${f.fileExt }</a></td>
											<td><fmt:formatDate value="${f.fileCreateTime }"
													pattern="yyyy-MM-dd HH:mm:ss" /></td>
										</tr>
									</c:forEach>

								</table>
							</div>
						</div>

					</div>

					<div class="am-u-md-6">
						<div class="am-panel am-panel-default">
							<div class="am-panel-hd am-cf"
								data-am-collapse="{target: '#collapse-panel-sh'}">
								最新分享<span class="am-icon-chevron-down am-fr"></span>
							</div>
							<div id="collapse-panel-sh" class="am-in">
								<table class='am-table am-table-striped am-text-nowrap'>
									<c:forEach items="${share }" var="f">
										<tr>
											<td><img src="user/thumbnail/${f.fileUuid}/png"
												alt="null" class="am-img-thumbnail"
												style="width: 30px; height: 30px; overflow: hidden"></td>
											<td><a target="_blank"
												title="${f.fileName }.${f.fileExt }"
												href="user/file/${f.fileUuid}">${f.hiddenedFileName }.${f.fileExt }</a></td>
											<td><fmt:formatDate value="${f.fileCreateTime }"
													pattern="yyyy-MM-dd HH:mm:ss" /></td>
										</tr>
									</c:forEach>

								</table>
							</div>
						</div>

					</div>
				</div>
			</div>
			<footer class="admin-content-footer">
				<hr>
				<p>© 2016 中国软件杯 知识库管理系统 启航队</p>
			</footer>
			<%@include file="../common/footer.jsp"%>

		</div>
		<!-- content end -->
	</div>

</body>
<script type="text/javascript">
	
</script>
</html>