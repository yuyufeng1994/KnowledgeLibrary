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
							<div id="recent-charts" style="width: 800px; height: 350px;"></div>
							<script type="text/javascript">
								// 基于准备好的dom，初始化echarts实例
								var recentChart = echarts.init(document
										.getElementById('recent-charts'));
								function randomData() {
									now = new Date(+now + oneDay);
									value = value + Math.random() * 21 - 10;
									return {
										name : now.toString(),
										value : [
												[ now.getFullYear(),
														now.getMonth() + 1,
														now.getDate() ]
														.join('/'),
												Math.round(value) ]
									}
								}

								var data = [];
								var now = +new Date(1997, 9, 3);
								var oneDay = 24 * 3600 * 1000;
								var value = Math.random() * 1000;
								for (var i = 0; i < 1000; i++) {
									data.push(randomData());
								}

								option = {
									title : {
										text : '动态数据 + 时间坐标轴'
									},
									tooltip : {
										trigger : 'axis',
										formatter : function(params) {
											params = params[0];
											var date = new Date(params.name);
											return date.getDate() + '/'
													+ (date.getMonth() + 1)
													+ '/' + date.getFullYear()
													+ ' : ' + params.value[1];
										},
										axisPointer : {
											animation : false
										}
									},
									xAxis : {
										type : 'time',
										splitLine : {
											show : false
										}
									},
									yAxis : {
										type : 'value',
										boundaryGap : [ 0, '100%' ],
										splitLine : {
											show : false
										}
									},
									series : [ {
										name : '模拟数据',
										type : 'line',
										showSymbol : false,
										hoverAnimation : false,
										data : data
									} ]
								};
								//时时数据
								/*
									app.timeTicket = setInterval(function() {
								
										for (var i = 0; i < 5; i++) {
											data.shift();
											data.push(randomData());
										}
								
										myChart.setOption({
											series : [ {
												data : data
											} ]
										});
									}, 1000);*/
								recentChart.setOption(option);
							</script>
						</div>
					</div>
				</div>
				<div class="am-u-md-6">
					<div class="am-panel am-panel-default">
						<div class="am-panel-hd am-cf">文档点击率排行</div>
						<div class="am-panel-bd" id="collapse-panel-hot">
							<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
							<div id="filehot-charts" style="width: 500px; height: 450px;"></div>
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
												            barWidth: '60%',
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
						<div class="am-panel-bd am-collapse am-in" id="collapse-panel-hot-class">
							<div id="hotClass-charts" style="width: 500px; height: 450px;"></div>
							<script type="text/javascript">
								$(function(){
									var url = "admin/count/hot-classes";
									$.get(url,function(data){
										console.log(data.data);
										var dataList = data.data;
										console.log(dataList[0]);
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
										console.log(name);
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
											                    [{type : 'min'}, {type : 'max'}]
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
				<div class="am-u-md-6">
					<div class="am-panel am-panel-default">
						<div class="am-panel-hd am-cf">活跃用户排行</div>
						<div class="am-panel-bd" id="collapse-panel-hot-user">图表统计显示</div>
					</div>


					<div class="am-panel am-panel-default">
						<div class="am-panel-hd am-cf">网站点击率</div>
						<div class="am-panel-bd" id="collapse-panel-site-click">图表统计显示</div>
					</div>

				</div>

			</div>
		</div>
	</div>

	<%@include file="../common/footer.jsp"%>
</body>
</html>