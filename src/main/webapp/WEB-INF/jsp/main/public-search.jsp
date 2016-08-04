<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>公共知识库</title>
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
						<strong class="am-text-primary am-text-lg">公共知识库</strong> / <small>大家的知识库</small>
					</div>
				</div>
				<div class="am-u-sm-12">
					<div id="my-slider" data-am-widget="slider"
						class="am-slider  am-slider-c3"
						data-am-slider='{&quot;animation&quot;:&quot;slide&quot;,&quot;animationLoop&quot;:true,&quot;itemWidth&quot;:150,&quot;itemHeight&quot;:150,&quot;itemMargin&quot;:5}'>
						<ul class="am-slides">
							<li><a href="#"><img
									src="resource/image/educational.png" class="am-radius"></a>
								<div class="am-slider-desc">所有分类</div></li>
							<li><a href="#"><img
									src="resource/image/educational.png" class="am-radius"></a>
								<div class="am-slider-desc">计算机系统</div></li>
							<li><a href="#"><img
									src="resource/image/educational.png" class="am-radius"></a>
								<div class="am-slider-desc">程序语言</div></li>
							<li><a href="#"><img
									src="resource/image/educational.png" class="am-radius"></a>
								<div class="am-slider-desc">操作系统</div></li>
							<li><a href="#"><img
									src="resource/image/educational.png" class="am-radius"></a>
								<div class="am-slider-desc">网络</div></li>
							<li><a href="#"><img
									src="resource/image/educational.png" class="am-radius"></a>
								<div class="am-slider-desc">多媒体</div></li>
							<li><a href="#"><img
									src="resource/image/educational.png" class="am-radius"></a>
								<div class="am-slider-desc">数据结构</div></li>
							<li><a href="#"><img
									src="resource/image/educational.png" class="am-radius"></a>
								<div class="am-slider-desc">计算机系统</div></li>
							<li><a href="#"><img
									src="resource/image/educational.png" class="am-radius"></a>
								<div class="am-slider-desc">计算机系统</div></li>
						</ul>
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