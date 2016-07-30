<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="admin-sidebar am-offcanvas" id="admin-offcanvas">
	<div class="am-offcanvas-bar admin-offcanvas-bar">
		<ul class="am-list admin-sidebar-list">
			<li><a href="user/index"><span class="am-icon-home"></span>首页</a></li>
			<li><a href="admin-form.html"><span class="am-icon-users"></span> 公共知识库</a></li>
			<li class="admin-parent"><a class="am-cf" data-am-collapse="{target: '#collapse-nav'}"><span class="am-icon-user"></span> 个人知识库<span
					class="am-icon-angle-right am-fr am-margin-right"></span></a>
				<ul class="am-list am-collapse admin-sidebar-sub am-in"
					id="collapse-nav">
					<li><a href="user/upload"><span class="am-icon-cloud-upload"></span> 上传</a></li>
					<li><a href="user/newfile"><span class="am-icon-pencil-square-o"></span> 新建</a></li>
					<li><a href="admin-user.html" class="am-cf"><span class="am-icon-file-text"></span> 我的资源</a></li>

					<li><a href="admin-form.html"><span class="am-icon-code-fork"></span> 我的收藏</a></li>
				</ul></li>

			<li><a href="#"><span class="am-icon-info"></span> 个人信息</a></li>
		</ul>

		<div class="am-panel am-panel-default admin-sidebar-panel">
			<div class="am-panel-bd">
				<p>
					<span class="am-icon-bookmark"></span> 公告
				</p>
				<p>时光静好，与君语；细水流年，与君同。—— Amaze UI</p>
			</div>
		</div>

		<div class="am-panel am-panel-default admin-sidebar-panel">
			<div class="am-panel-bd">
				<p>
					<span class="am-icon-tag"></span> wiki
				</p>
				<p>Welcome to the Amaze UI wiki!</p>
			</div>
		</div>
	</div>
</div>