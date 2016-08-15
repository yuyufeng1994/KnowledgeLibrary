<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>SOKLIB后台管理-系统日志</title>
<style type="text/css">
#log-content {
	color: #f3f3f3;
}

#log-content {
	background-color: black;
}
</style>
<%@include file="../common/head.jsp"%>
</head>
<body>
	<%@include file="../common/admin-header.jsp"%>
	<div class="am-cf admin-main">
		<!-- sidebar start -->
		<%@include file="../common/admin-slidebar.jsp"%>
		<!-- sidebar end -->
		<!-- content start -->
		<div class="admin-content">
			<div class="am-cf am-padding">
				<div class="am-fl am-cf">
					<strong class="am-text-primary am-text-lg">系统日志</strong> / <small>
						<select onchange="change(this)">
							<option value="time">---时时日志---</option>
							<c:forEach items="${days7}" var="d">
								<option value="${d }">---${d}---</option>
							</c:forEach>
					</select>
					</small>
				</div>
			</div>
			<div class="admin-content-body">
				<div class="am-g error-log">
					<div class="am-u-sm-12 am-u-sm-centered">
						<pre class="am-pre-scrollable" id="log-content">
							     </pre>
					</div>
				</div>

			</div>
		</div>
	</div>

	<%@include file="../common/footer.jsp"%>

</body>
<script type="text/javascript">
	function getLog() {
		$.get("admin/log/now", function(res) {
			var str = '';
			for (var i = 0; i < res.data.length; i++) {
				str += "<p>" + res.data[i] + "</p>";
			}
			$('#log-content').html(str);
			$('#log-content').animate({
				scrollTop : $("#log-content")[0].scrollHeight
			}, 500);
		})
	}
	var timer;

	timer = setInterval(getLog, 1000);

	function change(mthis) {
		$this = $(mthis);
		//console.log($this.val());
		if ($this.val() == "time") {
			timer = setInterval(getLog, 1000);
		} else {
			clearInterval(timer);
			var readUrl = 'admin/log/'+$this.val()
			$("#log-content").load(readUrl);
		}

	}
</script>
</html>