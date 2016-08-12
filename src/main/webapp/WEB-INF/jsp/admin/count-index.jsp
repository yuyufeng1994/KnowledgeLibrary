<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>SOKLIB后台管理-统计</title>
<%@include file="../common/head.jsp"%>
<script type="text/javascript"
	src="resource/script/echarts.common.min.js"></script>
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
				<div class="am-u-md-12">
					<div class="am-panel am-panel-default">
						<div class="am-panel-hd am-cf">近期上传</div>
						<div class="am-panel-bd" id="collapse-panel-recent">
							<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
							<div id="recent-charts" style="width: 1000px; height: 350px;"></div>
							<script type="text/javascript">
								// 基于准备好的dom，初始化echarts实例
								$(function(){
									var url = "admin/count/recent-files";
									$.get(url,function(data){
										var dataList = data.data;
										var recentChart = echarts.init(document
												.getElementById('recent-charts'));
										var k = 0;
										function randomData() {
										    now = new Date(+now + oneDay);
										    value = list[k++];
										    return {
										        name: now.toString(),
										        value: [
										            [now.getFullYear(), now.getMonth() + 1, now.getDate()].join('/'),
										           value
										        ]
										    }
										}
										var data = [];
										var before = new Date();
										var list = dataList;
										before.setMonth(before.getMonth()-1);
										var now = +new Date(before);
										var oneDay = 24 * 3600 * 1000;
										var value = Math.random() * 1000;
										for (var i = 0; i <32; i++) {
										    data.push(randomData());
										}

										option = {
										    title: {
										        text: '文件上传实时数据'
										    },
										    tooltip: {
										        trigger: 'axis',
										        formatter: function (params) {
										            params = params[0];
										            var date = new Date(params.name);
										            return date.getDate() + '/' + (date.getMonth() + 1) + '/' + date.getFullYear() + ' : 上传量' + params.value[1];
										        },
										        axisPointer: {
										            animation: false
										        }
										    },
										    xAxis: {
										        type: 'time',
										        splitLine: {
										            show: false
										        }
										    },
										    yAxis: {
										        type: 'value',
										        boundaryGap: [0, '100%'],
										        splitLine: {
										            show: false
										        }
										    },
										    series: [{
										        name: '模拟数据',
										        type: 'line',
										        showSymbol: false,
										        hoverAnimation: false,
										        data: data
										    }]
										};
										recentChart.setOption(option);
									});
								});
							</script>
						</div>
					</div>
				</div>
				<div class="am-u-md-12">
					<div class="am-panel am-panel-default">
						<div class="am-panel-hd am-cf">文档点击率排行</div>
						<div class="am-panel-bd" id="collapse-panel-hot">
							<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
							<div id="filehot-charts" style="width: 1000px; height: 350px;"></div>
							<script type="text/javascript">
								$(function() {
									var url = "admin/count/hot-file";
									var fileList = new Array();
									$.get(url,function(data) {
											fileList = data.data;
											var fileNames = [];
											var clickTimes = [];
											for(var i = 0;i<fileList.length;i++){
												fileNames[i] = fileList[i].fileName;
												clickTimes[i] = fileList[i].fileClickTimes;
											}
											// 基于准备好的dom，初始化echarts实例
											var filehotCharts = echarts.init(document.getElementById('filehot-charts'));
											option = {
												    color: ['#3398DB'],
												    tooltip : {
												        trigger: 'axis',
												        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
												            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
												        }
												    },
												    grid: {
												        left: '3%',
												        right: '4%',
												        bottom: '3%',
												        containLabel: true
												    },
												    xAxis : [
												        {
												            type : 'category',
												            data :fileNames,
												            axisTick: {
												                alignWithLabel: true
												            },
												            axisLabel : {  
									                            show:true,  
									                            interval: 0  
									                        }  
												        }
												    ],
												    yAxis : [
												        {
												            type : 'value'
												        }
												    ],
												    series : [
												        {
												            name:'点击量',
												            type:'bar',
												            barWidth: '40%',
												            data:clickTimes
												        }
												    ]
												};
											// 使用刚指定的配置项和数据显示图表。
											filehotCharts.setOption(option);
										});
								});
							</script>
						</div>
					</div>

					<div class="am-panel am-panel-default">
						<div class="am-panel-hd am-cf">热门分类排行</div>
						<div class="am-panel-bd am-collapse am-in"
							id="collapse-panel-hot-class">
							<div id="hotClass-charts" style="width: 1000px; height: 350px;"></div>
							<script type="text/javascript">
								$(function(){
									var url = "admin/count/hot-classes";
									$.get(url,function(data){
										var dataList = data.data;
										var name = [];
										var clickTimes = [];
										var forkTimes = [];
										var uploadTimes = [];
										var hot = [];
										for(var i = 0;i<dataList.length;i++){
											name[i] = dataList[i].className;
											clickTimes[i] = dataList[i].classClickTimes;
											forkTimes[i] = dataList[i].forkFileTimes;
											uploadTimes[i] = dataList[i].uploadFileTimes;
											hot[i] = dataList[i].score;
										}
										var hotClassCharts = echarts.init(document.getElementById('hotClass-charts'));
										option = {
											    tooltip : {
											        trigger: 'axis',
											        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
											            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
											        }
											    },
											    legend: {
											        data:['热度','上传量','点击量','收藏量']
											    },
											    grid: {
											        left: '2%',
											        right: '4%',
											        bottom: '3%',
											        containLabel: true
											    },
											    xAxis : [
											        {
											            type : 'category',
											            data : name
											        }
											    ],
											    yAxis : [
											        {
											            type : 'value'
											        }
											    ],
											    series : [
											        {
											            name:'热度',
											            type:'bar',
											            data:hot,
											            markLine : {
											                lineStyle: {
											                    normal: {
											                        type: 'dashed'
											                    }
											                },
											                data : [
											                    [{type : 'max'}, {type : 'min'}]
											                ]
											            }
											        },
											        {
											            name:'上传量',
											            type:'bar',
											            data:uploadTimes
											        },
											        {
											            name:'点击量',
											            type:'bar',
											            data:clickTimes
											        },
											        {
											            name:'收藏量',
											            type:'bar',
											            data:forkTimes
											        },
											    ]
											};
										hotClassCharts.setOption(option);
									});
								});
							</script>
						</div>
					</div>

				</div>
				<div class="am-u-md-12">
					<div class="am-panel am-panel-default">
						<div class="am-panel-hd am-cf">活跃用户排行</div>
						<div class="am-panel-bd" id="collapse-panel-hot-user">
							<div id="activeUser-charts" style="width: 1000px; height: 450px;"></div>
							<script type="text/javascript">
								$(function(){
									var url = "admin/count/active-user";
									$.get(url,function(data){
										var dataList = data.data;
										var name = [];
										var clickTimes = [];
										var forkTimes = [];
										var uploadTimes = [];
										var active = [];
										for(var i = 0;i<dataList.length;i++){
											name[i] = dataList[i].userName;
											clickTimes[i] = dataList[i].clickFileTimes;
											forkTimes[i] = dataList[i].forkFileTimes;
											uploadTimes[i] = dataList[i].uploadFileTimes;
											active[i] = dataList[i].active;
										}
										var activeUserCharts = echarts.init(document.getElementById('activeUser-charts'));
										option = {
											    tooltip : {
											        trigger: 'axis',
											        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
											            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
											        }
											    },
											    legend: {
											        data:['活跃度','上传量','点击量','收藏量']
											    },
											    grid: {
											        left: '2%',
											        right: '4%',
											        bottom: '3%',
											        containLabel: true
											    },
											    xAxis : [
											        {
											            type : 'category',
											            data : name
											        }
											    ],
											    yAxis : [
											        {
											            type : 'value'
											        }
											    ],
											    series : [
											        {
											            name:'热度',
											            type:'bar',
											            data:active,
											            markLine : {
											                lineStyle: {
											                    normal: {
											                        type: 'dashed'
											                    }
											                },
											                data : [
											                    [{type : 'max'}, {type : 'min'}]
											                ]
											            }
											        },
											        {
											            name:'上传量',
											            type:'bar',
											            data:uploadTimes
											        },
											        {
											            name:'点击量',
											            type:'bar',
											            data:clickTimes
											        },
											        {
											            name:'收藏量',
											            type:'bar',
											            data:forkTimes
											        },
											    ]
											};
										activeUserCharts.setOption(option);
									});
								});
							</script>
						</div>
					</div>


					<div class="am-panel am-panel-default">
						<div class="am-panel-hd am-cf">网站点击率</div>
						<div class="am-panel-bd" id="collapse-panel-site-click">
							<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
							<div id="click-charts" style="width: 1000px; height: 350px;"></div>
							<script type="text/javascript">
								// 基于准备好的dom，初始化echarts实例
								$(function(){
									var url = "admin/count/click-times";
									$.get(url,function(data){
										var dataList = data.data;
										var recentChart = echarts.init(document
												.getElementById('click-charts'));
										var k = 0;
										function randomData() {
										    now = new Date(+now + oneDay);
										    value = list[k++];
										    return {
										        name: now.toString(),
										        value: [
										            [now.getFullYear(), now.getMonth() + 1, now.getDate()].join('/'),
										           value
										        ]
										    }
										}

										var data = [];
										var before = new Date();
										var list = dataList;
										before.setMonth(before.getMonth()-1);
										var now = +new Date(before);
										var oneDay = 24 * 3600 * 1000;
										var value = Math.random() * 1000;
										for (var i = 0; i <dataList.length; i++) {
										    data.push(randomData());
										}

										option = {
										    title: {
										        text: '网站点击率'
										    },
										    tooltip: {
										        trigger: 'axis',
										        formatter: function (params) {
										            params = params[0];
										            var date = new Date(params.name);
										            return date.getDate() + '/' + (date.getMonth() + 1) + '/' + date.getFullYear() + ' : 上传量' + params.value[1];
										        },
										        axisPointer: {
										            animation: false
										        }
										    },
										    xAxis: {
										        type: 'time',
										        splitLine: {
										            show: false
										        }
										    },
										    yAxis: {
										        type: 'value',
										        boundaryGap: [0, '100%'],
										        splitLine: {
										            show: false
										        }
										    },
										    series: [{
										        name: '模拟数据',
										        type: 'line',
										        showSymbol: false,
										        hoverAnimation: false,
										        data: data
										    }]
										};
										recentChart.setOption(option);
									});
								});
							</script>
						</div>
					</div>

				</div>

			</div>
		</div>
	</div>

	<%@include file="../common/footer.jsp"%>
</body>
</html>