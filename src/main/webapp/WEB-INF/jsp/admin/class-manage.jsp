<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>SOKLIB后台管理-分类管理</title>
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
			<div class="admin-content-body">
				<div class="am-cf am-padding am-padding-bottom-0">
					<div class="am-fl am-cf">
						<strong class="am-text-primary am-text-lg">表格</strong> / <small>Table</small>
					</div>
				</div>

				<hr>

				<div class="am-g">
					<div class="am-u-sm-12 am-u-md-6">
						<div class="am-btn-toolbar">
							<div class="am-btn-group am-btn-group-xs">
								<button type="button" class="am-btn am-btn-default">
									<span class="am-icon-plus"></span> 新增
								</button>
								<button type="button" class="am-btn am-btn-default">
									<span class="am-icon-save"></span> 保存
								</button>
								<button type="button" class="am-btn am-btn-default">
									<span class="am-icon-archive"></span> 审核
								</button>
								<button type="button" class="am-btn am-btn-default">
									<span class="am-icon-trash-o"></span> 删除
								</button>
							</div>
						</div>
					</div>
					<div class="am-u-sm-12 am-u-md-3">
						<div class="am-form-group">
							<select data-am-selected="{btnSize: 'sm'}" style="display: none;">
								<option value="option1">所有类别</option>
								<option value="option2">IT业界</option>
								<option value="option3">数码产品</option>
								<option value="option3">笔记本电脑</option>
								<option value="option3">平板电脑</option>
								<option value="option3">只能手机</option>
								<option value="option3">超极本</option>
							</select>
							<div class="am-selected am-dropdown " id="am-selected-aby8p"
								data-am-dropdown="">
								<div class="am-selected-content am-dropdown-content">
									<h2 class="am-selected-header">
										<span class="am-icon-chevron-left">返回</span>
									</h2>
									<ul class="am-selected-list">
										<li class="am-checked" data-index="0" data-group="0"
											data-value="option1"><span class="am-selected-text">所有类别</span>
											<i class="am-icon-check"></i></li>
										<li class="" data-index="1" data-group="0"
											data-value="option2"><span class="am-selected-text">IT业界</span>
											<i class="am-icon-check"></i></li>
										<li class="" data-index="2" data-group="0"
											data-value="option3"><span class="am-selected-text">数码产品</span>
											<i class="am-icon-check"></i></li>
										<li class="" data-index="3" data-group="0"
											data-value="option3"><span class="am-selected-text">笔记本电脑</span>
											<i class="am-icon-check"></i></li>
										<li class="" data-index="4" data-group="0"
											data-value="option3"><span class="am-selected-text">平板电脑</span>
											<i class="am-icon-check"></i></li>
										<li class="" data-index="5" data-group="0"
											data-value="option3"><span class="am-selected-text">只能手机</span>
											<i class="am-icon-check"></i></li>
										<li class="" data-index="6" data-group="0"
											data-value="option3"><span class="am-selected-text">超极本</span>
											<i class="am-icon-check"></i></li>
									</ul>
									<div class="am-selected-hint"></div>
								</div>
							</div>
						</div>
					</div>
					<div class="am-u-sm-12 am-u-md-3">
						<div class="am-input-group am-input-group-sm">
							<input type="text" class="am-form-field"> <span
								class="am-input-group-btn">
								<button class="am-btn am-btn-default" type="button">搜索</button>
							</span>
						</div>
					</div>
				</div>

				<div class="am-g">
					<div class="am-u-sm-12">
						<form class="am-form">
							<table
								class="am-table am-table-striped am-table-hover table-main">
								<thead>
									<tr>
										<th class="table-check"><input type="checkbox"></th>
										<th class="table-id">ID</th>
										<th class="table-title">标题</th>
										<th class="table-type">类别</th>
										<th class="table-author am-hide-sm-only">作者</th>
										<th class="table-date am-hide-sm-only">修改日期</th>
										<th class="table-set">操作</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td><input type="checkbox"></td>
										<td>1</td>
										<td><a href="#">Business management</a></td>
										<td>default</td>
										<td class="am-hide-sm-only">测试1号</td>
										<td class="am-hide-sm-only">2014年9月4日 7:28:47</td>
										<td>
											<div class="am-btn-toolbar">
												<div class="am-btn-group am-btn-group-xs">
													<button
														class="am-btn am-btn-default am-btn-xs am-text-secondary">
														<span class="am-icon-pencil-square-o"></span> 编辑
													</button>
													<button
														class="am-btn am-btn-default am-btn-xs am-hide-sm-only">
														<span class="am-icon-copy"></span> 复制
													</button>
													<button
														class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only">
														<span class="am-icon-trash-o"></span> 删除
													</button>
												</div>
											</div>
										</td>
									</tr>
								</tbody>
							</table>
							<div class="am-cf">
								共 15 条记录
								<div class="am-fr">
									<ul class="am-pagination">
										<li class="am-disabled"><a href="#">«</a></li>
										<li class="am-active"><a href="#">1</a></li>
										<li><a href="#">2</a></li>
										<li><a href="#">3</a></li>
										<li><a href="#">4</a></li>
										<li><a href="#">5</a></li>
										<li><a href="#">»</a></li>
									</ul>
								</div>
							</div>
							<hr>
							<p>注：.....</p>
						</form>
					</div>

				</div>
			</div>
		</div>
	</div>

	<%@include file="../common/footer.jsp"%>
</body>
</html>