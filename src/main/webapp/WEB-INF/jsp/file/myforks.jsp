<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>我的资源</title>
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
      <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">我的收藏</strong> / <small>收藏的资源</small></div>
      </div>

      <hr>

      <div class="am-g">
        <div class="am-u-sm-12 am-u-md-6">
          <div class="am-btn-toolbar">
            <div class="am-btn-group am-btn-group-xs">
              <button type="button" class="am-btn am-btn-default"  id="doc-prompt-toggle-add"><span class="am-icon-plus"></span> 新增收藏夹</button>
              <button type="button" class="am-btn am-btn-default" id="doc-prompt-toggle-delete"><span class="am-icon-trash-o"></span> 删除收藏夹</button>
            </div>
          </div>
        </div>
        <div class="am-u-sm-12 am-u-md-3">
        
          <div class="am-form-group">
           
            <select id="select"  data-am-selected="{searchBox: 1}" style="display: none;">
              
            </select>
            
          </div>
        </div>
        <div class="am-u-sm-12 am-u-md-3">
         <form action="user/myforks/-1/1" method="get">
          <div class="am-input-group am-input-group-sm">
            <input type="text" name="search" id="search" value="${param.search}"  class="am-form-field">
          <span class="am-input-group-btn">
            <button class="am-btn am-btn-default" type="submit">搜索</button>
          </span>
         
          </div>
           </form>
        </div>
      </div>

      <div class="am-g">
        <div class="am-u-sm-12">
          
            <table class="am-table am-table-striped am-table-hover table-main">
              <thead>
              <tr>
            
                <th class="table-image">缩略图</th>
                <th class="table-title">文件名称</th>
                <th class="table-author am-hide-sm-only">文件作者</th>
                <th class="table-date am-hide-sm-only">收藏日期</th>
                <th class="table-type">收藏夹</th>
                <th class="table-text">收藏备注</th>
                <th class="table-set">操作</th>
                <th class="table-type">预览</th>
              </tr>
              </thead>
              <tbody>
          	        <c:forEach items="${page.list}" var="f">
								<tr>
								
									<td><a href="user/thumbnail/${f.fileUuId}/png"><img
											src="user/thumbnail/${f.fileUuId}/png" alt="null"
											class="am-img-thumbnail"
											style="width: 50px; height: 50px; overflow: hidden"></a></td>
									<td id="fileName">${f.fileName}.${f.fileExt}</td>
									<td>${f.userName}</td>
									<td><fmt:formatDate value="${f.forkCreateTime}"
											pattern="yyyy-MM-dd HH:mm:ss" /></td>
									<input type="hidden" id="forkId" value="${f.forkId}">
									<td class="am-hide-sm-only" id="docName">${f.docName}</td>
                					<td id="forkNote" class="am-hide-sm-only">${f.forkNote}</td>
                				
									<td>
										<div class="am-btn-toolbar">
												<div class="am-btn-group am-btn-group-xs">
													<button  
														class="modify am-btn am-btn-default am-btn-xs am-text-secondary"
														data-am-modal="{target: '#doc-modal-1', closeViaDimmer: 0, width: 400, height: 260}">
														<span class="am-icon-pencil-square-o"></span> 修改
													</button>
													<button 
														class="delete am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only">
														<span class="am-icon-trash-o"></span> 删除
													</button>
												</div>
										</div>
									</td>
									
									<td><a class="am-btn am-btn-secondary am-btn-xs"
										target="_blank" href="user/file/${f.fileUuId}"> 预览 <i
											class="am-icon-cloud"></i>
									</a></td>
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

    <footer class="admin-content-footer">
      <hr>
      <p class="am-padding-left">© 2014 AllMobilize, Inc. Licensed under MIT license.</p>
    </footer>

  </div>
	</div>
	<!-- 模态窗口 -->
	<div class="am-modal am-modal-no-btn" tabindex="-1" id="doc-modal-1">
		<div class="am-modal-dialog">
			<div class="am-modal-hd">
				修改收藏 <a href="javascript: void(0)" class="am-close am-close-spin"
					data-am-modal-close>&times;</a>
			</div>
			<div class="am-modal-bd">

				<div class="am-form">
					<div class="am-g am-margin-top-sm">
						<input type="hidden" id="cForkId"> 
						<input type="hidden" id="cDocName"> 
						<div class="am-u-sm-3   am-text-left ">名称:</div>
						<div class="am-u-sm-8 am-u-end">
							<input type="text" class="am-input-sm"
								value="" id="cFileName"
								readonly="readonly">
						</div>

						<div class="am-u-sm-3  am-text-left" style="margin-top: 10px;">文件夹:</div>
						<div class="am-u-sm-8 am-u-end"
							style="margin-top: 10px; margin-left: -10px;">
							<select id="select1" data-am-selected="{searchBox: 1}">

							</select>
						</div>
						<div class="am-u-sm-3  am-text-left"
							style="margin-top: 10px; margin-bottom: 10px;">备注:</div>
						<div class="am-u-sm-8  am-u-end"
							style="margin-top: 10px; margin-bottom: 10px;">
							<textarea rows="2" id="cForkNote" required="required"
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
	</div>
	
	<!-- 新建文件夹 -->
	<div class="am-modal am-modal-prompt" tabindex="-1" id="my-prompt-add">
		<div class="am-modal-dialog">
			<div class="am-modal-hd">新建文件夹</div>
			<div class="am-modal-bd">
				收藏夹名称 :&nbsp&nbsp&nbsp<input  type="text" style="display:inline;width:200px;" id="sDocName" class="am-modal-prompt-input">
			</div>
			<div class="am-modal-footer">
				<span class="am-modal-btn" data-am-modal-cancel>取消</span> 
				<span class="am-modal-btn" onclick="insertDoc()" data-am-modal-confirm >添加</span>
			</div>
		</div>
	</div>
	
	<!-- 删除文件夹 -->
	<div class="am-modal am-modal-prompt" tabindex="-1" id="my-prompt-delete">
		<div class="am-modal-dialog">
			<div class="am-modal-hd">删除文件夹</div>
			<div class="am-modal-bd">
				选择收藏夹:&nbsp&nbsp&nbsp
              <select data-am-selected="{searchBox: 1}" id="dSelect"> 
              
              </select>
            
			</div>
			<div class="am-modal-footer">
				<span class="am-modal-btn" data-am-modal-cancel>取消</span> <span
					class="am-modal-btn" data-am-modal-confirm>删除</span>
			</div>
		</div>
	</div>
	
	<%@include file="../common/footer.jsp"%>
	<!-- content end -->
</body>
<script type="text/javascript">

//添加收藏夹
$(function() {
  $('#doc-prompt-toggle-add').on('click', function() {
    $('#my-prompt-add').modal({
      
      relatedTarget: this,
       onConfirm: function(e) {
    	   var docName=$("#sDocName").val();
    	    if(docName=="")
    	    {
    	    	 alert("名称不能为空");
    	    	 return ;
    	    }
    		 $.ajax({
    			
    				url : "user/insertDoc",
    				data:{docName:docName},
    				type : "post",
    				datatype : "json",
    				//ansyn : false,
    				success : function(JsonResult) {
    					alert(JsonResult.error);
    					findDoc('#select');
    				}
    			})
       },
      onCancel: function(e) {
        //alert('不想说!');
      } 
    });
  });
});
//删除收藏夹
$(function() {
	  $('#doc-prompt-toggle-delete').on('click', function() {
		findDoc('#dSelect');
		
	    $('#my-prompt-delete').modal({
	      relatedTarget: this,
	      onConfirm: function(e) {
	    	  var docId=$("#dSelect").val();	    
	    		 $.ajax({
	    				url : "user/deleteDoc",
	    				data:{docId:docId},
	    				type : "post",
	    				datatype : "json",
	    				//ansyn : false,
	    				success : function(JsonResult) {
	    					alert(JsonResult.error);
	    					findDoc('#select');
	    				}
	    			})
	      },
	      onCancel: function(e) {
	       // alert('不想说!');
	      } 
	    });
	  });
	});
//查看收藏夹
function findDoc(selectId){
var str=window.location.pathname;
var strs=str.split("/");

var docName=$("#cDocName").val();

$.ajax({
	url : "user/findAllByUserId",
	type : "post",
	datatype : "json",
	//ansyn : false,
	success : function(JsonResult) {
		var len=JsonResult.data.length;
		var docInfos=JsonResult.data;
		var innerStr="";
		if(selectId=='#select')
		{
			innerStr="<option value='-1'>"+"所有收藏" + "</option>";
		}
		$(selectId).empty();
		for(var i=0;i<len;i++)
		{	
			if(docInfos[i].docId==strs[4]&&selectId=='#select'||docName==docInfos[i].docName){
				innerStr += "<option value='" + docInfos[i].docId+"' selected='selected'>"
				+ docInfos[i].docName + "</option>"
			}else{
				innerStr += "<option value='" + docInfos[i].docId
				+ "'>"
				+ docInfos[i].docName + "</option>"
			}
		}
		$(selectId).append(innerStr);
	}
})
}

//翻页
var url = "user/myforks/";
function gotoPage(page) {
	
	var search=window.location.search;
	var docId=$("#select option:selected").val();
	if(search=="")
	    window.location.href = url +docId+"/"+page;
	else
	{
		window.location.href = url +docId+"/"+page+search;
	}
}
//回显
$(".modify").click(function(){
	
	var node=$(this).parent().parent().parent().parent();
    var fileName=node.find("#fileName").html();
    alert(fileName);
    $("#cFileName").val(fileName);
    var forkId=node.find("#forkId").val();
    $("#cForkId").val(forkId);
    var forkNote=node.find("#forkNote").html();
    $("#cForkNote").val(forkNote);
    var docName=node.find("#docName").html();
    $("#cDocName").val(docName)
    findDoc('#select1');
    
});
//删除收藏
$(".delete").click(function(){
	var node=$(this).parent().parent().parent().parent();
	var forkId=node.find("#forkId").val();
	$.ajax({
	url : "user/deleteFork",
	data:{forkId:forkId},
	type : "POST",
	datatype : "json",
	//ansyn : false,
	success : function(JsonResult) {
		/* var len=JsonResult.data.length;
		var docInfos=JsonResult.data; */
		node.remove();
		alert(JsonResult.error);
	}
    }) 
    
});
$("#select").change(function(){
		
	var docId=$(this).val();
	var search=window.location.search;
	var url;
	if(search=="")
	{
		url = "user/myforks/"+docId+"/1";
	}else{
		
		url = "user/myforks/"+docId+"/1"+search;
	}
	window.location.href = url;
});
findDoc('#select');
//提交修改
function submit(){
	
		docId=$("#select1").val();
		docName=$("#select1 option:selected").text();
		forkNote=$("#cForkNote").val();
		forkId=$("#cForkId").val();
	    if(forkNote=="")
		{
			forkNote="无";
		} 
		$.ajax({
		url : "user/modifyFork",
		data:{forkId:forkId,docId:docId,forkNote:forkNote},
		type : "POST",
		datatype : "json",
		//ansyn : false,
		success : function(JsonResult) {
			/* var len=JsonResult.data.length;
			var docInfos=JsonResult.data; */
			var node=$("tr").find("input[value="+forkId+"]").parent();
			node.find("#docName").text(docName);
			node.find("#forkNote").text(forkNote);
		}
	    }) 
}

</script>

</html>