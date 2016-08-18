<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>账号安全</title>
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
						<strong class="am-text-primary am-text-lg">系统通知</strong> / <small>我的消息</small>
					</div>
				</div>
				<div class="am-g">
					<div class="am-u-sm-12">
						<table class="am-table am-table-striped am-table-hover table-main">
							<thead>
								<tr>
									<th class="table-title">标题</th>
									<th class="table-author ">内容</th>
									<th class="table-type am-hide-sm-only">状态</th>
									<th class="table-set">操作</th>
								</tr>
							</thead>
							<tbody id="json-list">
								<c:forEach items="${page.list}" var="msg" varStatus="s">
									<tr id="tr-list">
										<td>${msg.msgTitle }</td>
										<td><textarea
												style="overflow: hidden; background: #ffffff; resize: none; BORDER-BOTTOM: 0px solid; BORDER-LEFT: 0px solid; BORDER-RIGHT: 0px solid; BORDER-TOP: 0px solid;"
												cols="28" rows="1" disabled="disabled">${msg.msgContent }</textarea></td>
										<td><c:if test="${msg.isRead==true }">已读</c:if> <c:if
												test="${msg.isRead==false }">未读</c:if></td>
										<td>
											<div class="am-btn-toolbar">
												<div class="am-btn-group am-btn-group-xs">
													<button id="doc-prompt-toggle" value="${msg.msgTitle }"
														class="lookBtn am-btn am-btn-default am-btn-xs am-text-default am-hide-sm-only">
														<span  value="${msg.msgContent }" class="am-icon-pencil-o"></span> 查看<font value="${msg.msgId }"></font>
													</button>
													<button value="${msg.msgId}" 
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
					</div>
				</div>
			</div>
		</div>
		<div class="am-modal am-modal-prompt" tabindex="-1" id="my-prompt">
			<div class="am-modal-dialog">
				<div class="am-modal-hd msgTitle"></div>
				<div class="am-modal-bd msgContent" >
				</div>
				<div class="am-modal-footer">
					<span class="am-modal-btn" data-am-modal-cancel>返回</span> <span
						class="am-modal-btn" data-am-modal-confirm>标记为已读</span>
				</div>
			</div>
		</div>
		<%@include file="../common/footer.jsp"%>
		<!-- content end -->
</body>
<script type="text/javascript">
function gotoPage(page) {
	window.location.href = "user/message/" + page;
}
$(function() {
	$('.deleteBtn').on('click', function() {
		var msgId = $(this).val();
		console.log(msgId);
		var url = "user/delete";
		var args={"msgId":msgId}
		$.post(url,args,function(data){
  		  window.location.href = "user/message/1" ;
  	  });
	});
	  $('.lookBtn').on('click', function() {
		  var msgTitle =$(this).val();
		  var msgContent = $(this).find("span");
		  var msgId = $(this).find("font");
		  $(".msgTitle").text(msgTitle);
		  $(".msgContent").text(msgContent.attr("value"));
	    $('#my-prompt').modal({
	      relatedTarget: this,
	      onConfirm: function(e) {
	    	  var msg = msgId.attr("value");
	    	  var args={"msgId":msg};
	    	  $.post("user/isread",args,function(data){
	    		  window.location.href = "user/message/1" ;
	    	  });
	      },
	      onCancel: function(e) {
	      }
	    });
	  });
	});
</script>
</html>