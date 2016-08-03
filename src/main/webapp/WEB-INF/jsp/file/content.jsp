<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>资源主体</title>
<%@include file="../common/head.jsp"%>
<style type="text/css">
#main-content {
	height: 500px;
}
</style>
</head>
<body>
	<%@include file="../common/header.jsp"%>
	<div class="am-cf admin-main">
		<!-- sidebar start -->
		<!-- sidebar end -->
		<!-- content start -->
		<div class="admin-content">
			<div class="admin-content-body">
				<div class="am-cf am-padding">
					<div class="am-fl am-cf">
						<strong class="am-text-primary am-text-lg"><a>资源在线预览</a></strong> / <small>${fileInfo.fileName }.${fileInfo.fileExt }</small>
					</div>
				</div>
				<div class="am-u-sm-12">
					<div class="am-u-md-12 am-u-sm-12 am-u-lg-8">
						<div class="am-panel am-panel-default">
							<div class="am-panel-bd am-collapse am-in">
								<div id="main-content"></div>
							</div>
						</div>
						<div class="am-panel am-panel-default">
							<div class="am-panel-hd am-cf"
								data-am-collapse="{target: '#collapse-panel-comment'}">
								评论<span class="am-icon-chevron-down am-fr"></span>
							</div>
							<div id="collapse-panel-comment" class="am-in">《》！</div>
							<div class="am-panel-bd am-collapse am-in am-cf"
								id="collapse-panel-file-info">

								<ul class="am-pagination am-fr admin-content-pagination">
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
					</div>

					<div class="am-u-md-12 am-u-sm-12 am-u-lg-4">
						<div class="am-panel am-panel-default">

							<div class="am-panel-hd am-cf"
								data-am-collapse="{target: '#collapse-panel-file-info1'}"
								title="${fileInfo.fileName }.${fileInfo.fileExt }">
								${fileInfo.hiddenedFileName }.${fileInfo.fileExt } <span
									class="am-icon-chevron-down am-fr"></span>
							</div>
							<div class="am-panel-bd am-collapse am-in am-cf"
								id="collapse-panel-file-info1">

								<ul class="am-list admin-content-file">
									<li>
										<p>
											<span class="am-badge am-badge-secondary">上传用户</span>
											${fileInfo.userName }
										</p>

									</li>

									<li>
										<p>
											<span class="am-badge am-badge-secondary">大小</span>
											${fileInfo.fileSizeFormat }
										</p>
									</li>

									<li>
										<p>
											<span class="am-badge am-badge am-badge-secondary">类别</span>
											${fileInfo.classificationName }
										</p>
									</li>
									<li>
										<p>
											<span class="am-badge am-badge-secondary">上传时间</span>
											<fmt:formatDate value="${fileInfo.fileCreateTime }"
												pattern="yyyy-MM-dd HH:mm:ss" />
											</td>
										</p>
									</li>
									<li>
										<p>
											<span class="am-badge am-badge-secondary">简介</span>
											${fileInfo.fileBrief }
										</p>

									</li>
									<li>
										<div class="am-btn-group am-btn-group-xs">
											<button type="button" class="am-btn am-btn-default">
												<span class="am-icon-plus"></span> 新增
											</button>
											<button type="button" class="am-btn am-btn-default">
												<span class="am-icon-save"></span> 保存
											</button>
											<button type="button" class="am-btn am-btn-default"
												onclick="findDoc()"
												data-am-modal="{target: '#doc-modal-1', closeViaDimmer: 0, width: 400, height: 260}">
												<span class="am-icon-archive"></span> 收藏
											</button>
											<button type="button" class="am-btn am-btn-default">
												<span class="am-icon-trash-o"></span> 下载
											</button>
										</div>

									</li>
								</ul>
							</div>

						</div>
						<div class="am-panel am-panel-default">
							<div class="am-panel-hd am-cf"
								data-am-collapse="{target: '#collapse-panel-link-file'}">
								关联文档<span class="am-icon-chevron-down am-fr"></span>
							</div>
							<div class="am-panel-bd am-collapse am-in am-cf"
								id="collapse-panel-link-file">

								<ul class="am-list admin-content-file">
									<li>
										<p>3.3 of 5MB - 5 mins - 1MB/Sec</p>

									</li>
									<li>
										<p>3.3 of 5MB - 5 mins - 3MB/Sec</p>
									</li>
									<li>
										<p>3.3 of 5MB - 5 mins - 3MB/Sec</p>
									</li>
								</ul>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
	<!-- 模态窗口 -->
	<div class="am-modal am-modal-no-btn" tabindex="-1" id="doc-modal-1">
		<div class="am-modal-dialog">
			<div class="am-modal-hd">
				收藏文件 <a href="javascript: void(0)" class="am-close am-close-spin"
					data-am-modal-close>&times;</a>
			</div>
			<div class="am-modal-bd">

				<div class="am-form">
					<div class="am-g am-margin-top-sm">

						<div class="am-u-sm-3   am-text-left ">名称:</div>
						<div class="am-u-sm-8 am-u-end">
							<input type="text" class="am-input-sm"
								value="${fileInfo.fileName}.${fileInfo.fileExt }"
								readonly="readonly">
						</div>

						<div class="am-u-sm-3  am-text-left" style="margin-top: 10px;">文件夹:</div>
						<div class="am-u-sm-8 am-u-end"
							style="margin-top: 10px; margin-left: -10px;">
							<select id="select" data-am-selected="{searchBox: 1}">

							</select>
						</div>

						<div class="am-u-sm-3  am-text-left"
							style="margin-top: 10px; margin-bottom: 10px;">备注:</div>
						<div class="am-u-sm-8  am-u-end"
							style="margin-top: 10px; margin-bottom: 10px;">
							<textarea rows="2" id="forkNote" required="required"
								placeholder="文件备注"></textarea>
						</div>


						<div class="am-margin">
							<button type="button" onclick="submit()"
								class="am-btn am-btn-primary am-btn-xs" data-am-modal-close>提交保存</button>
							<button type="button" class="am-btn am-btn-primary am-btn-xs"
								data-am-modal-close>放弃保存</button>
						</div>
					</div>


				</div>

			</div>

		</div>
		
		<div class="am-modal am-modal-loading am-modal-no-btn" tabindex="-1" id="my-modal-loading">
 		 <div class="am-modal-dialog">
    		<div class="am-modal-hd">保存成功</div>
   			 <div class="am-modal-bd">
     		 <span class="am-icon-spinner am-icon-spin"></span>
   		 </div>
 		 </div>
		</div>
		<%@include file="../common/footer.jsp"%>
		<!-- content end -->
</body>
<script src="resource/script/pdfobject.min.js"></script>
<script type="text/javascript" src="resource/ckplayer/ckplayer.js"
	charset="utf-8"></script>


<script type="text/javascript">
//操作收藏的js

    function findDoc(){
	    $.ajax({
			url : "user/findAllByUserId",
			type : "get",
			datatype : "json",
			//ansyn : false,
			success : function(JsonResult) {
				var len=JsonResult.data.length;
				var docInfos=JsonResult.data;
				var innerStr="";
				$('#select').empty();
				for(var i=0;i<len;i++)
				{	
					if(i==0){
						innerStr += "<option value='" + docInfos[i].docId
						+ "' selected='selected'>"
						+ docInfos[i].docName + "</option>"
					}else{
						innerStr += "<option value='" + docInfos[i].docId
						+ "'>"
						+ docInfos[i].docName + "</option>"
					}
				}
				$('#select').append(innerStr);
			}
		})
	}
   	function submit(){
   		docId=$("#select").val();
   		docName=$("#select").text();
   		forkNote=$("#forkNote").val();
   	    if(forkNote==null||forkNote=="")
   		{
   			forkNote="无";
   		} 
   		fileId=${fileInfo.fileId};
   		 $.ajax({
   			
			url : "user/insertFork",
			data:{docId:docId,forkNote:forkNote,fileId:fileId},
			type : "POST",
			datatype : "json",
			//ansyn : false,
			success : function(JsonResult) {
				/* var len=JsonResult.data.length;
				var docInfos=JsonResult.data; */
				alert(JsonResult.error);
			}
		}) 
		
   		
   	}
   	
   	
   	
	//------------------------
	var ext = "${fileInfo.fileExt}";
	var uuid = "${fileInfo.fileUuid}";
	//判断是哪种浏览方式在网页中呈现
	//页面显示js
	var host = location.host; // 192.168.1.104
	host = host.substring(0, host.indexOf(':'));
	var fileUrl = "user/thumbnail/" + "${fileInfo.fileUuid}" + "/";
	var $content = $("#main-content");
</script>

<script src="resource/script/file-view.js"></script>
</html>