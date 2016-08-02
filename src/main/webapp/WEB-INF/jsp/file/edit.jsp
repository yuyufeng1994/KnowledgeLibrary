<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>我的资料</title>
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
										<strong>大小:</strong>${fileInfo.fileSizeFormat}</p>
									<p class="user-info-order"></p>
								</div>

							</div>
						</div>

					</div>

					<div class="am-u-sm-12 am-u-md-8 am-u-md-pull-4">
						<form class="am-form am-form-horizontal">
							<div class="am-form-group">
								<label for="user-name" class="am-u-sm-3 am-form-label">文件名称</label>
								<div class="am-u-sm-8" style="padding-right: 0px">
									<input type="text" id="user-name" placeholder="文件名称"
										value="${fileInfo.fileName }"> <small>输入它名字，让大家记住它...</small>
								</div>
								<label for="user-name" class="am-u-sm-1 am-form-label"
									style="padding-left: 0px">.txt</label>
							</div>

							<div class="am-form-group">
								<label for="file-class" class="am-u-sm-3 am-form-label">文件分类</label>
								<div class="am-u-sm-2">
									<button class="am-btn am-btn-default js-modal-toggle"
										id="file-class-button" type="button">
										<i class="am-icon-sort"></i> 请选择
									</button>
									<small>它属于...</small>
								</div>
								<div class="am-u-sm-7">
									<input name="fileClassId" id="fileClassId" type="hidden"
										value="${fileInfo.fileClassId}"> <input
										id="fileClassName" class="am-form-field" type="text"
										placeholder="所有类别" disabled
										value="${fileInfo.classificationName }">
								</div>
							</div>

							<div class="am-form-group">
								<label for="file-fork" class="am-u-sm-3 am-form-label">添加到收藏</label>
								<div class="am-u-sm-9">
									<input type="text" id="file-fork" placeholder="收藏"> <small>更容易找到它...</small>

								</div>
							</div>



							<div class="am-form-group">
								<label for="file-breif" class="am-u-sm-3 am-form-label">简介</label>
								<div class="am-u-sm-9">
									<textarea class="" rows="5" id="file-breif"
										placeholder="输入文件简介">${fileInfo.fileBrief }</textarea>
									<small>简单描述文件内容...</small>
								</div>
							</div>

							<div class="am-form-group">
								<div class="am-u-sm-9 am-u-sm-push-3">
									<button type="button" class="am-btn am-btn-primary">保存修改</button>
								</div>
							</div>
						</form>
					</div>
				</div>

			</div>
		</div>
	</div>

	<%@include file="../common/footer.jsp"%>
	<!-- content end -->

	<div class="am-modal am-modal-no-btn" tabindex="-1"
		id="file-class-modal">
		<div class="am-modal-dialog">
			<div class="am-modal-hd">
				<div class="am-btn-group">
					<button class="am-btn am-btn-primary" id="reply-button"
						onclick="reply()">
						<i class="am-icon-angle-left"></i>
					</button>
					<button class="am-btn am-btn-primary" id="parent-button"
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
						"<button class='am-btn am-btn-default' onclick=fileClassSure("+data.data[i].classificationId+","+"'"+data.data[i].classificationName+"'"+")>"+data.data[i].classificationName+"</button>"+
						"<button class='am-btn am-btn-default'"+
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



</script>
</html>