<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>新建文档</title>
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
						<strong class="am-text-primary am-text-lg">新建</strong> / <small>新建一个知识文档</small>
					</div>
				</div>
				<div class="am-u-sm-12">
					<div class="am-panel am-panel-default">
						<div class="am-panel-hd">
							<div class="am-g">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">文档标题：</div>
								<div class="am-u-sm-8 am-u-md-4">
									<input id="fileName" type="text" class="am-input-lg"
										style="width: 100%" value="${session_new_file.name }">
								</div>
								<div class="am-u-sm-8 am-u-md-6">
									<div class="am-btn-group am-btn-group-xs">
										<button type="button" id="save-button"
											class="am-btn am-btn-default">
											<span class="am-icon-save"></span> 暂存
										</button>
										<button type="button" class="am-btn am-btn-default">
											<span class="am-icon-trash-o"></span> 删除
										</button>
										<button type="button" id="complete-button"
											class="am-btn am-btn-default">
											<span class="am-icon-save"></span> 完成
										</button>
									</div>
								</div>
							</div>

						</div>
						<div class="am-panel-bd"
							style="height: 530px; width: 100%; overflow: scroll;">
							<!-- 加载编辑器的容器 -->
							<script id="container" name="content" type="text/plain">
        						${session_new_file.content }
  							</script>
							<!-- 配置文件 -->
							<script type="text/javascript"
								src="resource/ueditor/ueditor.config.js"></script>
							<!-- 编辑器源码文件 -->
							<script type="text/javascript"
								src="resource/ueditor/ueditor.all.js"></script>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
	<%@include file="../common/footer.jsp"%>
	<!-- content end -->
</body>
<script type="text/javascript">
	var ue = UE.getEditor('container', {
		initialFrameWidth : 860,
		initialFrameHeight : 450,
		autoFloatEnabled : false,
		toolbars : [
			[
				'source' , //源代码
				'undo', //撤销
				'redo', //重做
				'bold', //加粗
				'italic', //斜体
				'underline', //下划线
				'subscript', //下标
				'formatmatch', //格式刷
				'selectall', //全选
				'justifyleft', //居左对齐
				'justifyright', //居右对齐
				'justifycenter', //居中对齐
				'justifyjustify', //两端对齐
				'forecolor', //字体颜色
				'insertcode', //代码语言
				'fontfamily', //字体
				'fontsize', //字号
				'paragraph', //段落格式
				'touppercase', //字母大写
				'tolowercase', //字母小写
				'inserttable', //插入表格
				'charts', // 图表
			], [
				'indent', //首行缩进
				'snapscreen', //截图
				'strikethrough', //删除线
				'fontborder', //字符边框
				'superscript', //上标
				'blockquote', //引用
				'pasteplain', //纯文本粘贴模式
				'horizontal', //分隔线
				'removeformat', //清除格式
				'time', //时间
				'date', //日期
				'unlink', //取消链接
				'insertrow', //前插入行
				'insertcol', //前插入列
				'mergeright', //右合并单元格
				'mergedown', //下合并单元格
				'deleterow', //删除行
				'deletecol', //删除列
				'splittorows', //拆分成行
				'splittocols', //拆分成列
				'splittocells', //完全拆分单元格
				'deletecaption', //删除表格标题
				'inserttitle', //插入标题
				'mergecells', //合并多个单元格
				'deletetable', //删除表格
				'cleardoc', //清空文档
				'insertparagraphbeforetable', //"表格前插入行"
				'simpleupload', //单图上传
				'edittable', //表格属性
				'edittd', //单元格属性
				'link', //超链接
				'emotion', //表情
				'spechars', //特殊字符
				'searchreplace', //查询替换
				'map', //Baidu地图
				'insertvideo', //视频
				'help', //帮助
				'backcolor', //背景色
				'insertorderedlist', //有序列表
				'insertunorderedlist', //无序列表
				'directionalityltr', //从左向右输入
				'directionalityrtl', //从右向左输入
				'rowspacingtop', //段前距
				'rowspacingbottom', //段后距
				'pagebreak', //分页
				'insertframe', //插入Iframe
				'imagenone', //默认
				'imageleft', //左浮动
				'imageright', //右浮动
				'attachment', //附件
				'imagecenter', //居中
				'wordimage', //图片转存
				'lineheight', //行间距
				'edittip ', //编辑提示
				'customstyle', //自定义标题
				'autotypeset', //自动排版
				'background', //背景
				'template', //模板
				'scrawl', //涂鸦
				'music', //音乐
			] ]
	});
	//对编辑器的操作最好在编辑器ready之后再做
	ue.ready(function() {
		//获取html内容，返回: <p>hello</p>
		var html = ue.getContent();
		//获取纯文本内容，返回: hello
		var txt = ue.getContentTxt();
	});



	$(function() {

		$("#complete-button").click(function() {
			var html = ue.getContent();
			$.post("user/newfile/save", {
				fileName : $("#fileName").val(),
				content : html,
				classId : 1
			}, function(data) {
				var $btn = $("#complete-button");
				$btn.button('loading');
				$btn.text("处理中...")
				if (data.success == true) {
					//保存
					$.post("user/newfile/complete", {}, function(data) {
						if (data.success == true) {
							$btn.text("完成")
							window.location.href = "user/myfiles/1";
						}
						else {
							$btn.button('reset');
							$btn.text("失败...")
						}


					})

				}
				else {
					$btn.text('暂存失败');
				}



			})

		})


		function fn_save() {
			var $btn = $("#save-button");
			var html = ue.getContent();
			$.post("user/newfile/save", {
				fileName : $("#fileName").val(),
				content : html,
				classId : 1
			}, function(data) {
				$btn.button('loading');
				$btn.text("暂存中...")
				if (data.success == true) {
					$btn.text('暂存成功');

				}
				else {
					$btn.text('暂存失败');
				}


				setTimeout(function() {
					$btn.button('reset');
				}, 3000);

			})
		}

		$("#save-button").click(fn_save);
	})
</script>
</html>