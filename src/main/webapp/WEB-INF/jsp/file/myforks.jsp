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
              <button type="button" class="am-btn am-btn-default"  id="doc-prompt-toggle-add"><span class="am-icon-plus"></span> 新增文件夹</button>
              <button type="button" class="am-btn am-btn-default" id="doc-prompt-toggle-delete"><span class="am-icon-trash-o"></span> 删除文件夹</button>
            </div>
          </div>
        </div>
        <div class="am-u-sm-12 am-u-md-3">
          <div class="am-form-group">
            <select data-am-selected="{btnSize: 'sm'}" style="display: none;">
              <option value="option1">全部文件夹</option>
              <option value="option2">IT业界</option>
              <option value="option3">数码产品</option>
              <option value="option3">笔记本电脑</option>
              <option value="option3">平板电脑</option>
              <option value="option3">只能手机</option>
              <option value="option3">超极本</option>
            </select>
          </div>
        </div>
        <div class="am-u-sm-12 am-u-md-3">
          <div class="am-input-group am-input-group-sm">
            <input type="text" class="am-form-field">
          <span class="am-input-group-btn">
            <button class="am-btn am-btn-default" type="button">搜索</button>
          </span>
          </div>
        </div>
      </div>

      <div class="am-g">
        <div class="am-u-sm-12">
          
            <table class="am-table am-table-striped am-table-hover table-main">
              <thead>
              <tr>
                <th class="table-check"><input type="checkbox"></th>
                <th class="table-image">缩略图</th>
                <th class="table-title">文件名称</th>
                <th class="table-type">文件夹</th>
                <th class="table-author am-hide-sm-only">文件作者</th>
                <th class="table-date am-hide-sm-only">收藏日期</th>
                <th class="table-text">收藏备注</th>
                <th class="table-set">操作</th>
              </tr>
              </thead>
              <tbody>
              <tr>
                <td><input type="checkbox"></td>
                <td><a href="#">图片</a></td>
                <td>Business management</td>
                <td>default</td>
                <td class="am-hide-sm-only">测试1号</td>
                <td class="am-hide-sm-only">2014年9月4日 7:28:47</td>
                <td class="am-hide-sm-only">备注</td>
                <td>
                  <div class="am-btn-toolbar">
                    <div class="am-btn-group am-btn-group-xs">
                      
                      <button class="am-btn am-btn-default am-btn-xs am-text-secondary" data-am-modal="{target: '#doc-modal-1', closeViaDimmer: 0, width: 400, height: 260}"><span class="am-icon-pencil-square-o"></span> 编辑</button>
                      <button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only"><span class="am-icon-trash-o"></span> 删除</button>
                    </div>
                  </div>
                </td>
              </tr>
          
 
             
              </tbody>
            </table>
            <form>
            <div class="am-cf">
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

          </form>
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
				收藏文件 <a href="javascript: void(0)" class="am-close am-close-spin"
					data-am-modal-close>&times;</a>
			</div>
			<div class="am-modal-bd">
     	
          <form class="am-form">
			<div class="am-g am-margin-top-sm">
			  
			  <div class="am-u-sm-3   am-text-left"  >名称:</div>
              <div class="am-u-sm-8 am-u-end">
                <input type="text" class="am-input-sm" readonly="readonly">
              </div>
			
             <div class="am-u-sm-3  am-text-left" style="margin-top:10px;">类别:</div>
             <div class="am-u-sm-8 am-u-end" style="margin-top:10px;margin-left:-10px;">
              <select data-am-selected="{btnSize: 'sm'}">
                <option value="option1">选项一</option>
                <option value="option2">选项二</option>
                <option value="option3">选项三</option>
              </select>
             </div>
            
			
			
              <div class="am-u-sm-3  am-text-left" style="margin-top:10px;margin-bottom:10px;">备注:</div>
              <div class="am-u-sm-8  am-u-end" style="margin-top:10px;margin-bottom:10px;">
                <textarea rows="2"></textarea>
              </div>
              
              
               <div class="am-margin" >
    		  	<button type="button" class="am-btn am-btn-primary am-btn-xs">提交保存</button>
     			<button type="button" class="am-btn am-btn-primary am-btn-xs">放弃保存</button>
    			</div>
            </div>
          </form>
     
    	</div>

		</div>
		
	</div>
	
	<!-- 新建文件夹 -->
	<div class="am-modal am-modal-prompt" tabindex="-1" id="my-prompt-add">
		<div class="am-modal-dialog">
			<div class="am-modal-hd">新建文件夹</div>
			<div class="am-modal-bd">
				文件夹名称 :&nbsp&nbsp&nbsp<input type="text" style="display:inline;width:200px;" class="am-modal-prompt-input">
			</div>
			<div class="am-modal-footer">
				<span class="am-modal-btn" data-am-modal-cancel>取消</span> <span
					class="am-modal-btn" data-am-modal-confirm>添加</span>
			</div>
		</div>
	</div>
	
	<!-- 删除文件夹 -->
	<div class="am-modal am-modal-prompt" tabindex="-1" id="my-prompt-delete">
		<div class="am-modal-dialog">
			<div class="am-modal-hd">删除文件夹</div>
			<div class="am-modal-bd">
				选择文件夹:&nbsp&nbsp&nbsp
              <select data-am-selected="{btnSize: 'sm'}">
                <option value="option1">选项一</option>
                <option value="option2">选项二</option>
                <option value="option3">选项三</option>
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
$(function() {
  $('#doc-prompt-toggle-add').on('click', function() {
    $('#my-prompt-add').modal({
      relatedTarget: this,
       onConfirm: function(e) {
        alert('添加成功')
      },
      onCancel: function(e) {
        //alert('不想说!');
      } 
    });
  });
});
$(function() {
	  $('#doc-prompt-toggle-delete').on('click', function() {
	    $('#my-prompt-delete').modal({
	      relatedTarget: this,
	      onConfirm: function(e) {
	        alert('删除成功')
	      },
	      onCancel: function(e) {
	       // alert('不想说!');
	      } 
	    });
	  });
	});
</script>
</html>