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
             <input type="text" name="fileName" value="${file.fileName}" style="width:32%" class="am-input-lg" >
            </div>
             
          </div>

          <div class="am-g am-margin-top">
            <div class="am-u-sm-4  am-text-right">知识点</div>
            <div class="am-u-sm-8 ">
               <input type="text" name="keyWord" value="${keyWord}" style="width:32%"  class="am-input-lg" >
            </div>
         
          </div>
          
           <div class="am-g am-margin-top">
            <div class="am-u-sm-4  am-text-right">文件类型</div>
            <div class="am-u-sm-8 ">
              <select name="fileExt" id="fileExt"  >
                <option  value="all">所有</option>
                <option  value="office">office</option>
                <option  value="video" >视频</option>
                <option  value="image">图片</option>
                <option  value="else">其他</option>
              </select>
            </div>
          </div>
			
		  <div class="am-g am-margin-top" >
            <div class="am-u-sm-4  am-text-right">文件分类</div>
            <input type="hidden" value="1" name="fileClassId" id="fileClassId" >
            <input type="hidden" name="echo" value="1" id="echo"> 
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
                  <input name="fileCreateTime"  id="fileCreateTime" type="date"  class="am-form-field am-input-sm" placeholder="日期">
                </div>
            </div>
            <div class="am-u-sm-2" >
                <div class="am-form-group am-form-icon " >
                  <i class="am-icon-calendar"></i>
                   <input name="endTime" id="endTime" type="date"  class="am-form-field am-input-sm" placeholder="日期">
                </div>
            </div>
            
            <div class="am-u-sm-4 ">
            </div>
          </div>

        <div class="am-g am-margin-top">
            <div class="am-u-sm-4 am-text-right">
              &nbsp
            </div>
            
            <div class="am-u-sm-2">
                 <button type="button" id="search1"  style="margin-top:-30px" class="am-btn am-btn-primary ">智能搜索</button>
            </div>  
             <div class="am-u-sm-2">	 
     			 <button type="button" id="search2"   style="margin-top:-30px" class="am-btn am-btn-primary ">结果搜索</button>
           </div> 
           <div class="am-u-sm-4 ">
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
										<p  style="white-space:nowrap; overflow:hidden; text-overflow:ellipsis;">关键词:${f.fileKeyWords}</p>
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
										<li><a onclick="gotoPage(${page.nextPage})">»</a></li>
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
	echo();
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
			if(len==0)
				return ;
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
function getChild1(echoId)
{	
	var classId=$("#echo").val();
	$.ajax({
		url : "user/getClass",
		type : "POST",
		data:{classId:classId},
		datatype : "json",
		//ansyn : false,
		success : function(JsonResult) {
			
			var len=JsonResult.data.length;
			if(len==0)
				return ;
			var classification=JsonResult.data;
			var innerStr="<option value='-1' selected='selected'>"+"所有分类" + "</option>";
			for(var i=0;i<len;i++)
			{	
				   if(echoId==classification[i].classificationId)
				   {
						innerStr += "<option selected='selected' value='" + classification[i].classificationId+"'>"
						+ classification[i].classificationName + "</option>"
				
				   }else{
					   innerStr += "<option value='" + classification[i].classificationId+"'>"
						+ classification[i].classificationName + "</option>"
				   }
			}
			var str = "<select style='margin-right:10px;'  onchange='getChild(this)'>"

			+ innerStr

			+ "</select>";
			$("#selectId").append(str);
		}
	}) 
	
	$("#selectId").find("select").attr("data-am-selected","btnWidth:'30%',btnSize: 'sm'")
	
}
function gotoPage(page) {
	var url=window.location.pathname;
	var str=url.split("/");
	$("#search").attr("action","user/search/"+str[4]+"/"+page)
	$("#search").submit();
}
function echo()
{   
	/* var fileCreateTime="${file.fileCreateTime}";
	alert(fileCreateTime)
	$("#fileCreateTime").val(fileCreateTime);
	var endTime="${endTime}";
	$("#endTime").val(endTime); */
	
	
	var fileExt="${file.fileExt}";
	for(var i=0;i<$("#fileExt").find("option").length;i++)
	{	
		$("#fileExt").find("option").eq(i).removeAttr("selected");
		if($("#fileExt").find("option").eq(i).val()==fileExt)
		{   
			
			$("#fileExt").find("option").eq(i).attr("selected",true);
		}
	}
	var classIds="${classIds}";
	var str=classIds.split(".");
	if(str=="")
	{
		getChild1(1);
	}
	else{
		alert(str);
		for(var i=0;i<str.length;i++)
		{	
			if(i+1!=str.length)
			$("#echo").val(str[i+1]);
			getChild1(str[i]);
			
		}
		
	}
	
	
	
	
	
	

}


</script>

</html>