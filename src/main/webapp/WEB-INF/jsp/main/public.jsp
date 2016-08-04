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
				<div data-am-widget="intro"
					class="am-intro am-cf am-intro-default am-u-sm-12">
					<div class="am-g am-intro-bd">
						<div class="am-intro-left am-u-sm-3">
							<img src="resource/image/help.png" alt="小娜" />
						</div>
						<div class="am-intro-right am-u-sm-9">
							<h1>${classi.classificationName}</h1>
							<p>${classi.classificationBrief}</p>
						</div>
					</div>
				</div>

				<div class="am-panel am-panel-default">
					<header class="am-panel-hd">
						<h3 class="am-panel-title">最新录入</h3>
					</header>
					<div class="am-panel-bd">
						<c:forEach items="${page.list }" var="f">
							<div class="am-g">
								<div class="am-u-sm-12">
									<img src="user/thumbnail/${f.fileUuid}/png" alt="null"
										class="am-img-thumbnail"
										style="width: 100px; height: 100px; overflow: hidden"> <strong>${f.fileName }.${f.fileExt}</strong><br>${f.fileBrief }</div>
							</div>
							<hr>
						</c:forEach>
					</div>
				</div>

			</div>
		</div>
	</div>

	<%@include file="../common/footer.jsp"%>
	<!-- content end -->
</body>
<script type="text/javascript">

</script>
</html>