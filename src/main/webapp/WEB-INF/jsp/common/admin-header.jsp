<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<header class="am-topbar am-topbar-inverse admin-header">
	<div class="am-topbar-brand">
		<strong>SOKLIB</strong> <small>知识库管理系统</small>
	</div>




	<div class="am-collapse am-topbar-collapse am-topbar-right"
		id="topbar-collapse">
		<ul class="am-nav am-nav-pills am-topbar-nav  admin-header-list">
			<li><a href="user/message/1"><span class="am-icon-envelope-o"></span>
					消息 <span id ="msg" class="am-badge am-badge-warning">5</span></a></li>
			<li class="am-dropdown" data-am-dropdown><a
				class="am-dropdown-toggle" data-am-dropdown-toggle
				href="javascript:;"> <img alt="" class="am-radius"
					src="user/photo/${session_user.userPhoto}"
					style="width: 17px; height: 17px;"> ${session_user.userName}
					<span class="am-icon-caret-down"></span>
			</a>
				<ul class="am-dropdown-content">
					<li><a href="user/index"><span class="am-icon-user"></span>
							前台</a></li>
					<li><a href="user/userinfo"><span class="am-icon-user"></span>个人信息</a></li>
					<li><a href="logout"><span class="am-icon-power-off"></span>
							退出</a></li>
				</ul></li>
			<li class="am-hide-sm-only"></li>
		</ul>
	</div>

	<form class="am-topbar-form am-topbar-right am-form-inline"
		role="search">
		<div class="am-form-group">
			<input type="text" class="am-form-field am-input-sm"
				placeholder="搜索公共知识库">
			<button class="am-form-field am-input-sm">
				<i class="am-icon-search"></i> 搜索
			</button>
		</div>
	</form>
<script type="text/javascript">
	$(function(){
		$.get("user/count-msg",function(data){
			var msgNum = data.data;
			$("#msg").text(msgNum);
		});
	});
</script>
</header>