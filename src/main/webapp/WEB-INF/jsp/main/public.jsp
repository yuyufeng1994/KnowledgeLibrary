<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>公共知识库</title>
<%@include file="../common/head.jsp"%>
<style type="text/css">
.am-slider-c3 .am-slider-desc {
	background-color: #0e90d2;
	filter: alpha(opacity = 80); /*IE滤镜，透明度50%*/
	-moz-opacity: 0.8; /*Firefox私有，透明度50%*/
	opacity: 0.8; /*其他，透明度50%*/
}

.title-header a {
	color: #333;
}

.a-black {
	color: #333;
}
</style>
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
					<div class="am-fl am-cf title-header">
						<strong class="am-text-primary am-text-lg">公共知识库</strong> <small>
							<c:forEach items="${plist}" var="c">
							/ <a href="user/public/${c.classificationId}/1">${c.classificationName}</a>
							</c:forEach>
						</small>
					</div>
				</div>


				<div class="am-u-sm-12">
					<div id="my-slider" data-am-widget="slider"
						class="am-slider  am-slider-c3"
						data-am-slider='{&quot;animation&quot;:&quot;slide&quot;,&quot;animationLoop&quot;:true,&quot;itemWidth&quot;:150,&quot;itemHeight&quot;:150,&quot;itemMargin&quot;:0}'>
						<ul class="am-slides">
							<c:forEach items="${list}" var="c">
								<li><a href="user/public/${c.classificationId}/1"
									title="${c.classificationName} : ${c.classificationBrief}"><img
										src="resource/image/help.png" class="am-radius"></a>
									<div class="am-slider-desc">${c.classificationName}</div></li>
							</c:forEach>
						</ul>
					</div>
				</div>

				<c:if test="${page.pageNum == 1}">
					<div data-am-widget="intro"
						class="am-intro am-cf am-intro-default am-u-sm-12">
						<div class="am-g am-intro-bd">
							<div class="am-intro-left am-u-sm-3">
								<img src="resource/image/help.png" alt="小娜"
									style="width: 200px; height: 200px;" />
							</div>
							<div class="am-intro-right am-u-sm-9">
								<h1>${classi.classificationName}</h1>
								<p>${classi.classificationBrief}</p>
							</div>
						</div>
					</div>
				</c:if>

				<div class="am-u-sm-12">
					<hr>
				</div>

				<div class="am-panel am-panel-default">
					<!-- <header class="am-panel-hd">
						<h3 class="am-panel-title">最新录入</h3>
					</header> -->
					<c:forEach items="${page.list }" var="f">
						<article>
							<!-- 评论容器 -->
							<div class="">
								<div class="am-comment-bd">
									<div class="am-g am-intro-bd">
										<div class="am-intro-left am-u-sm-2">
											<img src="user/thumbnail/${f.fileUuid}/png" alt="null"
												class="am-img-thumbnail"
												style="width: 100px; height: 100px; overflow: hidden">
										</div>
										<div class="am-intro-right am-u-sm-10">
											<h3 class="am-comment-title">
												<a class="a-black" href="user/file/${f.fileUuid}">${f.fileName }.${f.fileExt}</a>
											</h3>
											<p>简介：${f.fileBrief }</p>
										</div>
										<div class="am-comment-meta">
											<!-- 文件元数据 -->
											<a href="#link-to-user" class="am-comment-author">用户:
												${f.userName }</a>
											<!-- 作者 -->
											创建于
											<time datetime="">
												<fmt:formatDate value="${f.fileCreateTime }"
													pattern="yyyy-MM-dd HH:mm:ss" />
											</time>
										</div>
									</div>
								</div>

							</div>
						</article>


					</c:forEach>
				</div>

				<div class="am-fr">
					<ul class="am-pagination">
						<c:if test="${page.pageNum > 1}">
							<li><a onclick="gotoPage(${page.prePage })">«</a></li>
						</c:if>

						<c:forEach items="${page.navigatepageNums}" var="p">
							<c:if test="${page.pageNum==p}">
								<li class="am-active"><a onclick="gotoPage(${p})">${p}</a></li>
							</c:if>
							<c:if test="${page.pageNum!=p}">
								<li><a onclick="gotoPage(${p})">${p}</a></li>
							</c:if>
						</c:forEach>
						<c:if test="${page.pageNum < page.pages}">
							<li><a onclick="gotoPage(${page.nextPage })">»</a></li>
						</c:if>
					</ul>
				</div>

				<script type="text/javascript">
					var id=${classi.classificationId}
					function gotoPage(page) {
						window.location.href = "user/public/"+id+"/"+page;
					}
				</script>

			</div>
		</div>
	</div>

	<%@include file="../common/footer.jsp"%>
	<!-- content end -->
</body>
<script type="text/javascript">

</script>
</html>