<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>文件信息修改</title>
<%@include file="../common/head.jsp"%>
<style>
.button-margin {
	margin-bottom: 5px;
}

#relation-files a {
	color: black;
}
</style>
</head>
<body>
	<input id="main_file_id" type="hidden" value="${fileInfo.fileId}">
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
						<strong class="am-text-primary am-text-lg">文件信息修改</strong> / <small>对我的文件名称、简介、分类等基本信息的修改</small>
					</div>
				</div>
				<hr>
				<div class="am-g">
					<div class="am-u-sm-12 am-u-md-4 am-u-md-push-8">
						<div class="am-panel am-panel-default">
							<div class="am-panel-bd">
								<div class="am-g">
									<div class="am-u-md-4">
										<a href="user/thumbnail/${fileInfo.fileUuid}/png"
											target="_blank"> <img
											class="am-img-circle am-img-thumbnail"
											src="user/thumbnail/${fileInfo.fileUuid}/png" alt="">
										</a>
									</div>
									<div class="am-u-md-8">
										<p>预览图</p>
										<form class="am-form">
											<div class="am-form-group">
												<input type="file" id="user-pic">
												<p class="am-form-help">请选择要上传的文件...</p>
												<button type="button"
													class="am-btn am-btn-primary am-btn-xs">保存</button>
											</div>
										</form>
									</div>
								</div>
							</div>
						</div>

						<div class="am-panel am-panel-default">
							<div class="am-panel-bd">
								<div class="user-info">
									<p>
										<strong>上传用户:</strong>${fileInfo.userName}</p>

								</div>
								<div class="user-info">
									<p>
										<strong>文件大小:</strong>${fileInfo.fileSizeFormat}</p>
								</div>
								<div class="user-info">
									<p>
										<strong>创建日期:</strong>
										<fmt:formatDate value="${fileInfo.fileCreateTime }"
											pattern="yyyy-MM-dd HH:mm:ss" />
									</p>
								</div>

							</div>
						</div>

					</div>

					<div class="am-u-sm-12 am-u-md-8 am-u-md-pull-4">
						<form id="myform" class="am-form am-form-horizontal" method="post"
							onkeypress="if(event.keyCode==13||event.which==13){return false;}"
							action="user/file-edit-submit">
							<input name="fileUuid" type="hidden" value="${fileInfo.fileUuid}">
							<div class="am-form-group">
								<label for="user-name" class="am-u-sm-3 am-form-label">文件名称</label>
								<div class="am-u-sm-8" style="padding-right: 0px">
									<input type="text" id="user-name" name="fileName"
										placeholder="文件名称" value="${fileInfo.fileName }"> <small>输入它名字，让大家记住它...</small>
								</div>
								<label for="user-name" class="am-u-sm-1 am-form-label"
									style="padding-left: 0px">.txt</label>
							</div>

							<div class="am-form-group">
								<label for="file-class" class="am-u-sm-3 am-form-label">文件分类</label>
								<div class="am-u-sm-9">
									<div class="am-input-group am-input-group">
										<span class="am-input-group-btn">
											<button class="am-btn am-btn-default" type="button"
												id="file-class-button">
												<i class="am-icon-sort"></i> 请选择
											</button>
										</span> <input name="fileClassId" id="fileClassId" type="hidden"
											value="${fileInfo.fileClassId}"> <input
											id="fileClassName" class="am-form-field" type="text"
											placeholder="所有类别" disabled
											value="${fileInfo.classificationName }">
									</div>
									<small>它属于...</small>
								</div>
							</div>
							<div class="am-form-group">
								<label for="file-relation" class="am-u-sm-3 am-form-label">关联文件</label>
								<div id="file-relation"
									class="am-panel am-panel-default am-u-sm-9"
									style="border: none">

									<div class="am-btn-group">
										<button type="button" onclick="addRelation()"
											class="am-btn am-btn-default am-radius">
											<i class="am-icon-plus"></i> 添加
										</button>
										<button type="button" onclick="autoRelation()"
											class="am-btn am-btn-default am-radius">自动关联</button>
									</div>
									<span class="am-icon-chevron-down am-fr"
										data-am-collapse="{target: '#collapse-panel-2'}"></span>
									<div id="collapse-panel-2" class="am-in">
										<br>
										<table class="am-table am-table-bdrs">
											<tbody id="relation-files">

											</tbody>
										</table>
									</div>
								</div>
							</div>
							<div class="am-modal am-modal-no-btn" tabindex="-1"
								id="relation-modal">
								<div class="am-modal-dialog">
									<div class="am-modal-hd">
										<div class="am-input-group am-input-group-sm">
											<input type="search" class="am-form-field" id="search-text"
												placeholder="输入文件名称或文件ID"> <span
												class="am-input-group-btn">
												<button class="am-btn am-btn-default" type="button"
													onclick="relationSearch()">
													<i class="am-icon-search"></i> 搜索
												</button>
											</span> <span class="am-input-group-btn">
												<button class="am-btn am-btn-primary"
													onclick="relationSure()" type="button">
													<i class="am-icon-anchor"></i> 关联选中文件
												</button>
											</span>
										</div>
									</div>
									<div class="am-modal-bd"
										style="height: 400px; overflow: scroll;">
										<table class="am-table am-table-bdrs am-table-striped">
											<thead>
												<tr>
													<th>文件ID</th>
													<th class="am-text-center">文件名称</th>
													<th>选择</th>
												</tr>
											</thead>
											<tbody id="search-result">
											</tbody>
										</table>
									</div>

								</div>
							</div>
							<script type="text/javascript">
							$.postJSON = function(url, data, callback) {  
						        return jQuery.ajax({  
						            'type' : 'POST',  
						            'url' : url,  
						            'contentType' : 'application/json',  
						            'data' : JSON.stringify(data),  
						            'dataType' : 'json',  
						            'success' : callback  
						        });  
						    }; 
							var mainFileId = ${fileInfo.fileId};
								function addRelation() {
									$("#relation-modal").modal();
							
								}
								
								function autoRelation() {
								
								}
								
								function relationSure() {
									$("#relation-modal").modal();
									$("#wait-model-text").text("关联中...");
									$("#wait-modal").modal();
									var mid = $("main_file_id").val();
									var map = {mainFileId:mainFileId,relationList:[]};
									var checks = $("#search-result").find('input:checked')
									for (var i = 0; i < checks.length; i++) {
										map.relationList.push(checks[i].value)
									}
									$.postJSON("user/add-relations",{mainFileId:mainFileId,list:map.relationList},function(data){
										$("#wait-model-text").text("成功关联 "+data.data+" 个文件");
										getRelation()
										setTimeout(function(){
											$("#wait-modal").modal();
										 },1000);
									})
								}
								function deleteRelation(mythis,relationFileId){
									$.ajax({
									    url: 'user/del-relations/'+mainFileId+'/'+relationFileId,
									    type: 'DELETE',
									    success: function(result) {
									    	if(result.success == true){
									    		$(mythis).parent().parent().hide("fast");
									    	}
									    }
									});
								}
								function getRelation(){
									$("#relation-files").hide();
									$.post("user/get-relations/"+mainFileId,function(data){
										var str = "";
										for (var i = 0; i < data.data.length; i++) {
											str += "<tr><td><a target='_blank' title='点击预览' href='user/file/" + data.data[i].relationFile.fileUuid + "'><img src='user/thumbnail/"+
											data.data[i].relationFile.fileUuid+"/png' width='30' height='30' alt='...' class='am-img-thumbnail am-radius'>  "
											 + data.data[i].relationFile.fileName + "." + data.data[i].relationFile.fileExt + "</a></td>"
												+"<td>"+"<button type='button' title='删除' onclick='deleteRelation(this,"+data.data[i].relationFileId+")' class='am-close am-close-alt am-close-spin am-icon-times'></button>"+"</td></tr>";
										}
										$("#relation-files").html(str);
										$("#relation-files").show("fast");
										
									})
								}
								function relationSearch() {
									var searchInfo = $("#search-text").val();
									$.post("user/file-search", {
										searchInfo ,
										searchInfo
									}, function(data) {
										var str = "";
										for (var i = 0; i < data.data.length; i++) {
											str += "<tr><td>" + data.data[i].fileId + "</td><td><a target='_blank' title='点击预览' href='user/file/" + data.data[i].fileUuid + "'>" +
												data.data[i].fileName + "." + data.data[i].fileExt + "</a></td><td><label>  <input type='checkbox' value='" + data.data[i].fileId + "'></label></td></tr>";
										}
										$("#search-result").html(str);
									})
								}
							</script>
							<div class="am-form-group">
								<label for="file-breif" class="am-u-sm-3 am-form-label">简介</label>
								<div class="am-u-sm-9">
									<textarea class="" rows="5" id="file-breif" name="fileBrief"
										placeholder="输入文件简介">${fileInfo.fileBrief }</textarea>
									<small>简单描述文件内容...</small>
								</div>
							</div>

							<div class="am-form-group">
								<div class="am-u-sm-3 am-u-sm-push-3">
									<button type="submit" id="submit-button"
										class="am-btn am-btn-primary">保存修改</button>
								</div>
								<div class="am-u-sm-5">
									<a href="javascript:history.go(-1)"
										class="am-btn am-btn-default">返回</a>
								</div>

							</div>
						</form>
						<hr>


					</div>
				</div>

			</div>
			<footer class="admin-content-footer">
				<hr>
				<p>© 2016 中国软件杯 知识库管理系统 启航队</p>
			</footer>
		</div>

	</div>

	<%@include file="../common/footer.jsp"%>
	<!-- content end -->
	<div class="am-modal am-modal-loading am-modal-no-btn" tabindex="-1"
		id="wait-modal">
		<div class="am-modal-dialog">
			<div class="am-modal-hd" id="wait-model-text">保存中...</div>
			<div class="am-modal-bd">
				<span class="am-icon-spinner am-icon-spin"></span>
			</div>
		</div>
	</div>
	<div class="am-modal am-modal-no-btn" tabindex="-1"
		id="file-class-modal">
		<div class="am-modal-dialog">
			<div class="am-modal-hd">
				<div class="am-btn-group">
					<button class="am-btn am-btn-primary am-round" id="reply-button"
						onclick="reply()">
						<i class="am-icon-angle-left"></i>
					</button>
					<button class="am-btn am-btn-primary am-round" id="parent-button"
						onclick="fileClassSureParent()">所有分类</button>
				</div>
			</div>
			<div class="am-modal-bd" id="child-content"></div>
		</div>
	</div>



</body>
<script type="text/javascript">
		var classId=${fileInfo.fileClassId}
		var className="${fileInfo.classificationName }";
		var parentId = 1;
		function loadTypes(){
			if(classId == null){
				classId = 1;
			}
			$.get("child-file-class/"+classId,function(data){
				
				var str='';
				for(var i = 1;i<data.data.length;i++){
					str+="<div class='am-btn-group'>"+
						"<button class='button-margin am-btn am-btn-default am-round' onclick=fileClassSure("+data.data[i].classificationId+","+"'"+data.data[i].classificationName+"'"+")>"+data.data[i].classificationName+"</button>"+
						"<button class='button-margin am-btn am-btn-default am-round'"+
							"onClick=changeType("+data.data[i].classificationId+","+"'"+data.data[i].classificationName+"'"+")>"+
							"<i class='am-icon-angle-right'></i>"+
						"</button></div>&nbsp;";
				}
				parentId = data.data[0].parentId;
				className = data.data[0].classificationName;
				classId = data.data[0].classificationId;
				
				$("#parent-button").text(className)
				$("#child-content").html(str);
				$('#file-class-modal').modal("open");
			})
			
		}
		
		function changeType(id,name){
			classId = id;
			className=name;
			loadTypes();
		}
		
		
		function reply(){
			classId = parentId;
			loadTypes();
		}
		
		$('#file-class-button').click(function() {
			loadTypes();
		});
		
		function fileClassSure(id,name){
			$("#fileClassId").val(id)	
			$("#fileClassName").val(name)	
			$('#file-class-modal').modal("close");
		}
		function fileClassSureParent(){
			$("#fileClassId").val(classId)	
			$("#fileClassName").val(className)	
			$('#file-class-modal').modal("close");
		}

		$("#myform").submit(function(){
			$("#wait-model-text").text("保存中...");
			$("#wait-modal").modal();
			 var $btn = $("#submit-button");
			$btn.button('loading');
			$btn.text("保存中...");
			$.post("user/file-edit-submit?"+$(this).serialize(),function(data){
				$btn.text(data.error);
				$("#wait-model-text").text(data.error);
				setTimeout(function(){
				      $("#wait-modal").modal();
				  }, 1000);
				setTimeout(function(){
				      $btn.button('reset');
				  },3000);
			})
			return false;
		});

		getRelation();//获取关联文件
		//$("#relation-modal").modal();
</script>
</html>