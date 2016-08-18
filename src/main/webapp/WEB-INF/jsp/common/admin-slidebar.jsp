<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="admin-sidebar am-offcanvas" id="admin-offcanvas">
	<div class="am-offcanvas-bar admin-offcanvas-bar">
		<ul class="am-list admin-sidebar-list">
			<li><a href="admin/index"><span class="am-icon-home"></span>首页</a></li>
			<li class="admin-parent"><a class="am-cf"
				data-am-collapse="{target: '#collapse-nav-person'}"><span
					class="am-icon-circle-o"></span> 统计 <span
					class="am-icon-angle-right am-fr am-margin-right"></span></a>
				<ul class="am-list am-collapse admin-sidebar-sub am-in"
					id="collapse-nav-person">
					<li><a href="admin/count/index" class="am-cf"><span
							class="am-icon-bar-chart"></span> 图表</a></li>
				</ul></li>
			<li class="admin-parent"><a class="am-cf"
				data-am-collapse="{target: '#collapse-nav-public'}"><span
					class="am-icon-magnet"></span> 管理<span
					class="am-icon-angle-right am-fr am-margin-right"></span></a>
				<ul class="am-list am-collapse admin-sidebar-sub am-in"
					id="collapse-nav-public">
					<li><a href="admin/file-manage-ui/1" class="am-cf"><span
							class="am-icon-file-text"></span> 文件管理</a></li>
					<li><a href="admin/user-manage-ui/1"><span
							class="am-icon-users"></span> 用户管理</a></li>
					<li><a href="admin/class-manage-ui"><span
							class="am-icon-sort"></span> 分类管理</a></li>
				</ul></li>
			<li class="admin-parent"><a class="am-cf"
				data-am-collapse="{target: '#collapse-nav-sys'}"><span
					class="am-icon-assistive-listening-systems"></span> 系统<span
					class="am-icon-angle-right am-fr am-margin-right"></span></a>
				<ul class="am-list am-collapse admin-sidebar-sub am-in"
					id="collapse-nav-sys">
					<li><a href="admin/log"><span class="am-icon-bug"></span>系统日志
					</a></li>
				</ul></li>
		</ul>

		<div class="am-panel am-panel-default admin-sidebar-panel">
			<div class="am-panel-bd">
				<p>
					<span class="am-icon-bookmark"></span> 公告
				</p>
				<p>知识就是力量 —— SOKLIB</p>
			</div>
		</div>

	</div>
</div>