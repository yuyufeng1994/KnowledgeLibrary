<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>SOKLIB后台管理-用户管理</title>
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
						<strong class="am-text-primary am-text-lg">管理</strong> / <small>用户管理</small>
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
						<div class="am-input-group am-input-group-sm">
							<input type="text" class="am-form-field" id="searchName">
							<span class="am-input-group-btn">
								<button id="searcher" class="am-btn am-btn-default"
									type="button">搜索</button>
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
										<th class="tabble-img">头像</th>
										<th class="table-title">昵称</th>
										<th class="table-password">密码</th>
										<th class="table-author ">邮箱地址</th>
										<th class="table-type am-hide-sm-only">权限</th>
										<th class="table-set">操作</th>
									</tr>
								</thead>
								<tbody id="json-list">
									<c:forEach items="${pages.dataList}" var="user">
										<tr>
											<td><input type="checkbox"></td>
											<td>${user.userId }</td>
											<td>
												<img src="user/photo/${user.userPhoto }"
																style="width: 50px; height:50px"/>
											</td>
											<td>
											<td>${user.userName }</td>
											<td class="am-hide-sm-only">${user.userPassword }</td>
											<td class="am-hide-sm-only">${user.userEmail }</td>
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
									</c:forEach>
								</tbody>
							</table>
							<div class="am-cf">
								<span id="total">共 ${pages.total } 条记录</span>
								<div class="am-fr">
									<ul
										class="am-pagination am-pagination-select">
										<li class="am-pagination-prev "><span id="prev" class="am-btn am-btn-default">上一页</span>
										</li>
										<li class="am-pagination-input">
												<input type="text" id="page-count" value="${ pages.pageNo}"/>
										</li>
										<li class="am-pagination-next "><span id="next" class="am-btn am-btn-default">下一页</span>
										</li>
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
<script type="text/javascript">
	$(function(){
		function getJson(current){
			if(current==null){
				current = 1;
			}
			var pageNumber = current;
			var pageSize = 10;
			var searchName = $("#searchName").val();
			var url = "admin/user-list/"+current;
			var args = {"searcher":searchName,"pageNumber":pageNumber,"pageSize":pageSize,"time":new Date()};
			$.post(url,args,function(data){
				var dataList = data.data.dataList;
				console.log(dataList);
				 var totalCount = data.data.total; // 总记录数  
	             var pageSize = 10; // 每页显示几条记录  
	             var pageTotal = Math.ceil(totalCount/pageSize);//总页数 
	             var startPage = pageSize * (current  - 1);  //第一个
	             var endPage = startPage + pageSize - 1;  //最后一个
	             var $table = $("#json-list");  
	             $("#total").empty().text("共"+totalCount+"条记录");
	             $table.empty();  
	             for (var i = 0; i < pageSize; i++) {  
	                 $table.append('<tr class="tr-tag"></tr>');  
	             }  
				 if (current !=0) {     // 当只有一页时  
			            for (var j = 0; j < pageSize; j++) {  
			            	if(j==totalCount%10){
			            		break;
			            	}
			                $(".tr-tag").eq(j).append("<td class='col1'><input class='table-check' type='checkbox' value='"+parseInt(j + 1)+"'/></td>")  
			               .append("<td class='col3'>" + dataList[j].userId   + "</td>")
			               .append("<td class='col4'><img src=user/photo/"+dataList[j].userPhoto+" style='width: 50px; height:50px'/></td>")
			               .append("<td class='col5'>" + dataList[j].userName    + "</td>")
			                .append("<td class='col6'>" + dataList[j].userPassword     + "</td>")
			                  .append("<td class='col7'>" + dataList[j].userEmail   + "</td>")
			             .append("<td class='col8'>" + dataList[j].userType      + "</td>")    
			             .append("<td>"+
			            " <div class='am-btn-toolbar'><div class='am-btn-group am-btn-group-xs'>"+
                            " <button class='am-btn am-btn-default am-btn-xs am-text-secondary'>"+
                            "<span class='am-icon-pencil-square-o'></span> 编辑</button>"+
						"<button class='am-btn am-btn-default am-btn-xs am-hide-sm-only'>"+
						"<span class='am-icon-copy'></span> 初始化密码</button>"+
						"<button	class='am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only'>"+
						"<span class='am-icon-trash-o'></span> 删除"+
						"</button></div></div>"+"</td>") 
			            }  
			        } 
			        $("#page-count").attr("value",current);
			});
		}
		$("#searcher").click(function(){
			getJson(1);
			$("#page-count").attr("value",1);
		});
		$("#prev").click(function(){
			var current = $("#page-count").val();
			alert(current);
			current--;
			$("#page-count").attr("value",current);
			getJson(current);
		});
		$("#next").click(function(){
			var current = $("#page-count").val();
			current++;
			$("#page-count").attr("value",current);
			getJson(current);
		});
	});
</script>
</html>