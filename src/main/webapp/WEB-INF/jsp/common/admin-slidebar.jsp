<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="admin-sidebar am-offcanvas" id="admin-offcanvas">
	<div class="am-offcanvas-bar admin-offcanvas-bar">
		<ul class="am-list admin-sidebar-list">
			<li><a href="admin/index"><span class="am-icon-home"></span>首页</a></li>
			<li class="admin-parent"><a class="am-cf"
				data-am-collapse="{target: '#collapse-nav-person'}"><span
					class="am-icon-user"></span> 统计<span
					class="am-icon-angle-right am-fr am-margin-right"></span></a>
				<ul class="am-list am-collapse admin-sidebar-sub am-in"
					id="collapse-nav-person">
					<li><a href="admin/index" class="am-cf"><span
							class="am-icon-file-text"></span> 访问量</a></li>
					<li><a href="admin/index"><span
							class="am-icon-cloud-upload"></span> 知识节点</a></li>
				</ul></li>
			<li class="admin-parent"><a class="am-cf"
				data-am-collapse="{target: '#collapse-nav-public'}"><span
					class="am-icon-users"></span> 管理<span
					class="am-icon-angle-right am-fr am-margin-right"></span></a>
				<ul class="am-list am-collapse admin-sidebar-sub am-in"
					id="collapse-nav-public">
					<li><a href="admin/file-manage-ui/1" class="am-cf"><span
							class="am-icon-file-text"></span> 文件管理</a></li>
					<li><a href="admin/user-manage-ui"><span
							class="am-icon-code-fork"></span> 用户管理</a></li>
					<li><a href="admin/class-manage-ui"><span
							class="am-icon-code-fork"></span> 分类管理</a></li>
				</ul></li>
			<li class="admin-parent"><a class="am-cf"
				data-am-collapse="{target: '#collapse-nav-person'}"><span
					class="am-icon-user"></span> 系统<span
					class="am-icon-angle-right am-fr am-margin-right"></span></a>
				<ul class="am-list am-collapse admin-sidebar-sub am-in"
					id="collapse-nav-person">
					<li><a href="admin/red5" class="am-cf"><span
							class="am-icon-file-text"></span> 流媒体服务</a></li>
					<li><a href="user/upload"><span
							class="am-icon-cloud-upload"></span>系统日志 </a></li>
				</ul></li>
		</ul>

		<div class="am-panel am-panel-default admin-sidebar-panel">
			<div class="am-panel-bd">
				<p>
					<span class="am-icon-bookmark"></span> 公告
				</p>
				<p>时光静好，与君语；细水流年，与君同。—— SOKLIB</p>
			</div>
		</div>

	</div>
</div>