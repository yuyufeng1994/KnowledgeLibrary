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
								<tr>
									<td>1</td>
									<td>John Clark</td>
									<td><a href="#">Business management</a></td>
									<td><span class="am-badge am-badge-success">+20</span></td>
									<td><a class="am-btn am-btn-secondary am-btn-xs"
										target="_blank" href=""> 预览 <i class="am-icon-cloud"></i>
									</a></td>
								</tr>
								<tr>
									<td>2</td>
									<td>风清扬</td>
									<td><a href="#">公司LOGO设计</a></td>
									<td><span class="am-badge am-badge-danger">+2</span></td>
									<td><a class="am-btn am-btn-secondary am-btn-xs"
										target="_blank" href=""> 预览 <i class="am-icon-cloud"></i>
									</a></td>
								</tr>
								<tr>
									<td>3</td>
									<td>詹姆斯</td>
									<td><a href="#">开发一款业务数据软件</a></td>
									<td><span class="am-badge am-badge-warning">+10</span></td>
									<td><a class="am-btn am-btn-secondary am-btn-xs"
										target="_blank" href=""> 预览 <i class="am-icon-cloud"></i>
									</a></td>
								</tr>
								<tr>
									<td>4</td>
									<td>云适配</td>
									<td><a href="#">适配所有网站</a></td>
									<td><span class="am-badge am-badge-secondary">+50</span></td>
									<td><a class="am-btn am-btn-secondary am-btn-xs"
										target="_blank" href=""> 预览 <i class="am-icon-cloud"></i>
									</a></td>
								</tr>

								<tr>
									<td>5</td>
									<td>呵呵呵</td>
									<td><a href="#">基兰会获得BUFF</a></td>
									<td><span class="am-badge">+22</span></td>
									<td><a class="am-btn am-btn-secondary am-btn-xs"
										target="_blank" href=""> 预览 <i class="am-icon-cloud"></i>
									</a></td>
								</tr>
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
								<ul class="am-list admin-content-file">
									<li><strong><span class="am-icon-upload"></span>
											Kong-cetian.Mp3</strong>
										<p>3.3 of 5MB - 5 mins - 1MB/Sec</p>
										<div
											class="am-progress am-progress-striped am-progress-sm am-active">
											<div class="am-progress-bar am-progress-bar-success"
												style="width: 82%">82%</div>
										</div></li>
									<li><strong><span class="am-icon-check"></span>
											好人-cetian.Mp3</strong>
										<p>3.3 of 5MB - 5 mins - 3MB/Sec</p></li>
									<li><strong><span class="am-icon-check"></span>
											其实都没有.Mp3</strong>
										<p>3.3 of 5MB - 5 mins - 3MB/Sec</p></li>
								</ul>
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
									<tbody>
										<tr>
											<th class="am-text-center">#</th>
											<th>浏览器</th>
											<th>访问量</th>
										</tr>
										<tr>
											<td class="am-text-center"><img src="" alt=""></td>
											<td>Google Chrome</td>
											<td>3,005</td>
										</tr>
										<tr>
											<td class="am-text-center"><img src="" alt=""></td>
											<td>Mozilla Firefox</td>
											<td>2,505</td>
										</tr>
										<tr>
											<td class="am-text-center"><img src="" alt=""></td>
											<td>Internet Explorer</td>
											<td>1,405</td>
										</tr>
										<tr>
											<td class="am-text-center"><img src="" alt=""></td>
											<td>Opera</td>
											<td>4,005</td>
										</tr>
										<tr>
											<td class="am-text-center"><img src="" alt=""></td>
											<td>Safari</td>
											<td>505</td>
										</tr>
									</tbody>
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
								<ul class="am-list admin-content-task">
									<li>
										<div class="admin-task-meta">Posted on 25/1/2120 by John
											Clark</div>
										<div class="admin-task-bd">The starting place for
											exploring business management; helping new managers get
											started and experienced managers get better.</div>
										<div class="am-cf">
											<div class="am-btn-toolbar am-fl">
												<div class="am-btn-group am-btn-group-xs">
													<button type="button" class="am-btn am-btn-default">
														<span class="am-icon-check"></span>
													</button>
													<button type="button" class="am-btn am-btn-default">
														<span class="am-icon-pencil"></span>
													</button>
													<button type="button" class="am-btn am-btn-default">
														<span class="am-icon-times"></span>
													</button>
												</div>
											</div>
											<div class="am-fr">
												<button type="button"
													class="am-btn am-btn-default am-btn-xs">删除</button>
											</div>
										</div>
									</li>
									<li>
										<div class="admin-task-meta">Posted on 25/1/2120 by 呵呵呵</div>
										<div class="admin-task-bd">
											基兰和狗熊出现在不同阵营时。基兰会获得BUFF，“装甲熊憎恨者”。狗熊会获得BUFF，“时光老人憎恨者”。</div>
										<div class="am-cf">
											<div class="am-btn-toolbar am-fl">
												<div class="am-btn-group am-btn-group-xs">
													<button type="button" class="am-btn am-btn-default">
														<span class="am-icon-check"></span>
													</button>
													<button type="button" class="am-btn am-btn-default">
														<span class="am-icon-pencil"></span>
													</button>
													<button type="button" class="am-btn am-btn-default">
														<span class="am-icon-times"></span>
													</button>
												</div>
											</div>
											<div class="am-fr">
												<button type="button"
													class="am-btn am-btn-default am-btn-xs">删除</button>
											</div>
										</div>
									</li>
								</ul>
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