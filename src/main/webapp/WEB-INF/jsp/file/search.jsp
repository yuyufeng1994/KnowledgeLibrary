<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>智能检索</title>
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
        		<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">智能检索</strong> / <small>搜索知识</small></div>
     		 </div>
     		 
    <div class="am-tabs am-margin" data-am-tabs>
      <ul class="am-tabs-nav am-nav am-nav-tabs">
        <li class="am-active"><a href="#tab1">检索信息</a></li>
      </ul>
	 <form   method="post" id="search">
      <div class="am-tabs-bd" >
        <div class="am-tab-panel am-fade am-in am-active" >
          <div class="am-g am-margin-top">
            <div class="am-u-sm-4  am-text-right">关键信息</div>
            <div class="am-u-sm-8 ">
             <input type="text" name="fileName" style="width:32%" class="am-input-lg" >
            </div>
             
          </div>

          <div class="am-g am-margin-top">
            <div class="am-u-sm-4  am-text-right">知识点</div>
            <div class="am-u-sm-8 ">
               <input type="text" name="keyWord" style="width:32%"  class="am-input-lg" >
            </div>
         
          </div>
          
           <div class="am-g am-margin-top">
            <div class="am-u-sm-4  am-text-right">文件类型</div>
            <div class="am-u-sm-8 ">
              <select name="fileExt" data-am-selected="{btnWidth: '32%',btnSize: 'sm'}">
                <option  value="office">office</option>
                <option  value="vedio" >视频</option>
                <option  value="image">图片</option>
                <option  value="else">其他</option>
              </select>
            </div>
          </div>
			
		  <div class="am-g am-margin-top" >
            <div class="am-u-sm-4  am-text-right">文件分类</div>
            <input type="hidden" value="1" id="fileClassId" >
            <div class="am-u-sm-8 " id="selectId">
                
            </div>
          </div>
				
			
          <div class="am-g am-margin-top">
            <div class="am-u-sm-4  am-text-right">
                   时间范围
            </div>
            
           
            <div class="am-u-sm-2">
             
                <div class="am-form-group am-form-icon">
                  <i class="am-icon-calendar"></i>
                  <input name="fileCreateTime" type="date" style="width:70%" class="am-form-field am-input-sm" placeholder="日期">
                </div>
            </div>
            <div class="am-u-sm-2" style="margin-left:-95px;">
                <div class="am-form-group am-form-icon " >
                  <i class="am-icon-calendar"></i>
                   <input name="endTime" type="date" style="width:70%" class="am-form-field am-input-sm" placeholder="日期">
                </div>
            </div>
            
            <div class="am-u-sm-4 ">
            </div>
          </div>

        <div class="am-g am-margin-top">
            <div class="am-u-sm-5">
              
            </div>
            <div class="am-u-sm-7">
                 <button type="button" id="search1"  style="margin-top:-30px;margin-left:-120px;width:15%" class="am-btn am-btn-primary ">智能搜索</button>
               	 
     			 <button type="button" id="search2"   style="margin-top:-30px;margin-left:40px;width:15%" class="am-btn am-btn-primary ">结果搜索</button>
            </div>
          </div>
        </div>
        
        </form>
      </div>
				<div class="am-u-sm-12">
					<c:forEach items="${page.data}" var="f">
						<article>
							<div class="am-comment-bd">
								<div class="am-g am-intro-bd">
									<div class="am-intro-left am-u-sm-2">
										<img src="user/thumbnail/${f.fileUuid}/png" alt="null"
											class="am-img-thumbnail"
											style="width: 150px; height: 150px; overflow: hidden">
									</div>
									<div class="am-intro-right am-u-sm-10">
										<h3 class="am-comment-title">
											<a class="a-black" target="_blank"
												href="user/file/${f.fileUuid}">${f.fileName }.${f.fileExt}</a>
										</h3>
										<p  style="white-space:nowrap; overflow:hidden; text-overflow:ellipsis;" >简介：${f.fileBrief }</p>
										<p  style="white-space:nowrap; overflow:hidden; text-overflow:ellipsis;">知识点:${f.fileKeyWords}</p>
										<p  style="white-space:nowrap; overflow:hidden; text-overflow:ellipsis;">内容:${f.fileText}</p>
										<a href="#link-to-user" class="am-comment-author">用户:
											${f.userName}</a>
										<!-- 作者 -->
										创建于
										<time>
											<fmt:formatDate value="${f.fileCreateTime }"
												pattern="yyyy-MM-dd HH:mm:ss" />
										</time>
									</div>

								</div>

							</div>
						</article>


					</c:forEach>
				</div>

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
									
									<c:if test="${page.pageNum < page.totalPage}">
										<li><a onclick="gotoPage(${page.nextPage })">»</a></li>
									</c:if>
								</ul>
				</div>
			
			</div>

    
  </div>
     	    
     	    
     		 
     		 
     		 

			</div>
		</div>
	</div>

	<%@include file="../common/footer.jsp"%>
	
</body>
<script type="text/javascript">
$("#search1").click(function(){
	
	$("#search").attr("action","user/search/0/1")
	$("#search").submit();
	
	
})
$("#search2").click(function(){
	
	$("#search").attr("action","user/search/1/1")
	$("#search").submit();
	
})
$(window).load(function() {
	 classId=$("#fileClassId").val();
	 $.ajax({
			url : "user/getClass",
			type : "POST",
			data:{classId:classId},
			datatype : "json",
			//ansyn : false,
			success : function(JsonResult) {
				
				var len=JsonResult.data.length;
				var classification=JsonResult.data;
				var innerStr="<option value='-1' selected='selected'>"+"所有分类" + "</option>";
				for(var i=0;i<len;i++)
				{	
					
						innerStr += "<option value='" + classification[i].classificationId+"'>"
						+ classification[i].classificationName + "</option>"
					
				}
				var str = "<select onchange='getChild(this)'>"

				+ innerStr

				+ "</select>";
				$("#selectId").append(str);
			}
	}) 
	
});
function getChild(object)
{
	$(object).nextAll().remove();
	var classId=$(object).find("option:selected").val();
	$("#fileClassId").val(classId);
	$.ajax({
		url : "user/getClass",
		type : "POST",
		data:{classId:classId},
		datatype : "json",
		//ansyn : false,
		success : function(JsonResult) {
			
			var len=JsonResult.data.length;
			var classification=JsonResult.data;
			var innerStr="<option value='-1' selected='selected'>"+"所有分类" + "</option>";
			for(var i=0;i<len;i++)
			{	
					innerStr += "<option value='" + classification[i].classificationId+"'>"
					+ classification[i].classificationName + "</option>"
			}
			var str = "<select style='margin-left:10px;'  onchange='getChild(this)'>"

			+ innerStr

			+ "</select>";
			$("#selectId").append(str);
		}
	}) 
	
	$("#selectId").find("select").attr("data-am-selected","btnWidth:'30%',btnSize: 'sm'")
	
}


</script>

</html>