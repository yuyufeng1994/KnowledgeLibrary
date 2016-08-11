<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>首页</title>
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
									<th colspan="5">个性推荐</th>
								</tr>
							</thead>
							<tbody>

							</tbody>
						</table>
					</div>
				</div>

				<div class="am-g">
					<div class="am-u-md-6">
						<div class="am-panel am-panel-default">
							<div class="am-panel-hd am-cf"
								data-am-collapse="{target: '#collapse-panel-1'}">
								最近阅读<span class="am-icon-chevron-down am-fr"></span>
							</div>
							<div class="am-panel-bd am-collapse am-in" id="collapse-panel-1">

							</div>
						</div>
						<div class="am-panel am-panel-default">
							<div class="am-panel-hd am-cf"
								data-am-collapse="{target: '#collapse-panel-2'}">
								热门文档<span class="am-icon-chevron-down am-fr"></span>
							</div>
							<div id="collapse-panel-2" class="am-in">
								<table
									class="am-table am-table-bd am-table-bdrs am-table-striped am-table-hover">

								</table>
							</div>
						</div>
					</div>

					<div class="am-u-md-6">
						<div class="am-panel am-panel-default">
							<div class="am-panel-hd am-cf"
								data-am-collapse="{target: '#collapse-panel-4'}">
								最新分享<span class="am-icon-chevron-down am-fr"></span>
							</div>
							<div id="collapse-panel-4" class="am-panel-bd am-collapse am-in">
								
							</div>
						</div>

						<div class="am-panel am-panel-default">
							<div class="am-panel-hd am-cf"
								data-am-collapse="{target: '#collapse-panel-3'}">
								最近留言<span class="am-icon-chevron-down am-fr"></span>
							</div>
							<div class="am-panel-bd am-collapse am-in am-cf"
								id="collapse-panel-3">
								<ul class="ds-top-threads" data-range="weekly"
									data-num-items="5"></ul>

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