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
								<button id="delete-user-list" type="button"
									class="am-btn am-btn-default">
									<span class="am-icon-trash-o"></span> 批量删除
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
						<table class="am-table am-table-striped am-table-hover table-main">
							<thead>
								<tr>
									<th class="table-check"><input id="checkAll"
										type="checkbox"></th>
									<th class="table-id">ID</th>
									<th class="tabble-img">头像</th>
									<th class="table-title">昵称</th>
									<th class="table-author ">邮箱地址</th>
									<th class="table-type am-hide-sm-only">权限</th>
									<th class="table-set">操作</th>
								</tr>
							</thead>
							<tbody id="json-list">
								<c:forEach items="${page.list}" var="user">
									<tr id="tr-list">
										<td><input type="checkbox" value="${user.userId }"></td>
										<td value="${user.userId }">${user.userId }</td>
										<td><img src="user/photo/${user.userPhoto }"
											style="width: 50px; height: 50px" /></td>
										<td>${user.userName }</td>
										<td class="am-hide-sm-only">${user.userEmail }</td>
										<td class="am-hide-sm-only"><c:if
												test="${user.userType ==0}">管理员</c:if> <c:if
												test="${user.userType ==1}">普通用户</c:if> <c:if
												test="${user.userType ==2}">未激活用户</c:if></td>
										<td>
											<div class="am-btn-toolbar">
												<div class="am-btn-group am-btn-group-xs">
													<select
														class="selector am-btn am-btn-default am-btn-xs am-hide-sm-only"">
														<option>权限操作</option>
														<option value="0">管理员</option>
														<option value="1">普通用户</option>
														<option value="2">锁定</option>
													</select>
													<button id="doc-prompt-toggle"
														class="doc-prompt-toggle msgBtn am-btn am-btn-default am-btn-xs am-hide-sm-only" value="${user.userId }">
														<span class="am-icon-refresh"></span> 发送消息
													</button>

													<button
														class="refreshBtn am-btn am-btn-default am-btn-xs am-hide-sm-only">
														<span class="am-icon-refresh"></span> 初始化密码
													</button>

													<button
														class="deleteBtn am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only">
														<span class="am-icon-trash-o"></span> 删除
													</button>
												</div>
											</div>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
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
						<hr>
						<p>注：.....</p>
					</div>

				</div>
			</div>
		</div>
	</div>
	<div class="am-modal am-modal-prompt" tabindex="-1" id="my-prompt">
	  <div class="am-modal-dialog">
	    <div class="am-modal-hd"><input type="text" class="am-modal-prompt-input" placeholder="请输入标题"></div>
	    <div class="am-modal-bd">
	    <textarea rows="5" cols="55" class="am-modal-prompt-input" placeholder="清输入消息内容"></textarea>
	    </div>
	    <div class="am-modal-footer">
	      <span class="am-modal-btn" data-am-modal-cancel>取消</span>
	      <span class="am-modal-btn" data-am-modal-confirm>提交</span>
	    </div>
	  </div>
	</div>
	<%@include file="../common/footer.jsp"%>
</body>
<script type="text/javascript">
function gotoPage(page) {
	window.location.href = "admin/user-manage-ui/" + page;
}
	$(function(){
		$('.doc-prompt-toggle').on('click', function() {
			var userId = $(this).val();
		    $('#my-prompt').modal({
		      relatedTarget: this,
		      onConfirm: function(e) {
		    	  var datas = e.data;
		    	  if(datas[0]==""){
		    		 alert("请输入标题");
		    	  }
		    	  else if(datas[1]==""){
		    		  alert("请输入消息内容");
		    	  }else{
		    		  var url = "admin/send-msg";
		    		  var args = {"userId":userId,"msgTitle":datas[0],"msgContent":datas[1]};
		        	$.post(url,args,function(data){
		        	})
		    	  }
		      },
		      onCancel: function(e) {
		      }
		    });
		  });
		function getJson(){
			var searchName = $("#searchName").val();
			var url = "admin/user-manage-ui/1?searchValue=" +searchName;
			window.location.href = url;
		}
		$("#searcher").click(function(){
			getJson();
		});
		$(document).on("click",".deleteBtn",function(){
			console.log(this);
			var tr = $(this).parent().parent().parent().parent();
			var userId = tr.find("td")[1].textContent;
			console.log(userId);
			var url = "admin/remove-user";
			var args ={"userId":userId,"time":new Date()};
			$.post(url,args,function(data){
				if(data.data == "success"){
		   			var current = $("#page-count").val();
		 			 getJson();
			}else{
				alert("修改失败");
			}
			});
		});
		$(document).on("click",".refreshBtn",function(){
			var tr = $(this).parent().parent().parent().parent();
			var userId = tr.find("td")[1].textContent;
			console.log(userId);
			var url = "admin/refresh-userpwd";
			var args ={"userId":userId,"time":new Date()};
			$.post(url,args,function(data){
				if(data.data == "success"){
			   			var current = $("#page-count").val();
			 			 getJson();
				}else{
					alert("修改失败");
				}
			});
		});
		$(document).on("change",".selector",function(){
			var select = $(this).val();
			var tr = $(this).parent().parent().parent().parent();
			var userId = tr.find("td")[1].textContent;
			console.log(userId);
			console.log(select);
			var url = "admin/change-usertype";
			var args ={"userId":userId,"userType":select,"time":new Date()};
			$.post(url,args,function(data){
				if(data.data == "success"){
			   			var current = $("#page-count").val();
			 			 getJson();
				}else{
					alert("修改失败");
				}
			});
		});
		$(document).on("click","#checkAll",function(){
			var checkAll = $(this).prop("checked"); 
			$(":input[type='checkbox']").prop("checked", checkAll); 

		});
		$(document).on("click",".table-check",function(){
			$(this).attr("checked");
			console.log(this);
		});
		$(document).on("click","#delete-user-list",function(){
			 var userList = new Array();
			var len = 0;
			var list = "";
				$(":input[type='checkbox']").each(function(){
					var flag = $(this).prop("checked");
					if(len != 0||flag){
						userList[len] = $(this).val();
						list+=userList[len];
						list+="A";
					}
				});
				console.log(list);
				var url = "admin/remove-userList";
				var args =  { "userList": list };
				$.post(url,args,function(data){
					if(data.data == "success"){
			   			var current = $("#page-count").val();
			 			 getJson();
				}else{
					alert("修改失败");
				}
				});
		});	
	});
</script>
</html>