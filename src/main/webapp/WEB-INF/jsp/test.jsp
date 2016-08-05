<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>test</title>
<%@include file="common/head.jsp"%>
</head>
<body>
	<div class="am-u-sm-12" id="class-charts"
		style="width: 1000px; height: 400px; border: red 1px solid"></div>
	<script type="text/javascript" src="resource/script/echarts.min.js"></script>
	<script type="text/javascript">
		var nodeId = 1;
		$.get("node", function(res) {
			var getData = {
				data : [],
				links : []
			};
			var s = res.data;
			getData.data.push({
				name : s[0].classificationName,
				x :6,
				y : 0,
			});
			var re = [];
			for (var i = 0; i < s.length; i++) {
				re.push({
					parentId : s[i].parentId,
					classificationId : s[i].classificationId,
					classificationName : (s[i].classificationName)
				})
			}
	
	
			var fn = {
				getclass : function(nid,name, depth) {
					//console.log("relen:" + re.length+" nid:"+nid)
					if (re.length == 1) {
						return;
					}
					var index = 0;
					for (var i = 0; i < re.length; i++) {
						if (re[i].parentId == nid) {
							index++;
							var di = {
								name : re[i].classificationName,
								x : index,
								y : depth * 1.5,
							}
							var li = {
								source : name,
								target : re[i].classificationName
							}
	
							getData.data.push(di);
							getData.links.push(li);
							//console.log(re[i].parentId+" "+re[i].classificationId)
							fn.getclass(re[i].classificationId,re[i].classificationName ,depth + 1)
						}
	
					}
				}
			}
			fn.getclass(s[0].classificationId,s[0].classificationName ,1)
	
	
			var option = {
				title : {
					text : 'Graph 简单示例'
				},
				tooltip : {},
				animationDurationUpdate : 1500,
				animationEasingUpdate : 'quinticInOut',
				series : [
					{
						type : 'graph',
						layout : 'none',
						symbolSize : 60,
						roam : true,
						label : {
							normal : {
								show : true
							}
						},
						edgeSymbol : [ 'circle', 'arrow' ],
						edgeSymbolSize : [ 4, 10 ],
						edgeLabel : {
							normal : {
								textStyle : {
									fontSize : 20
								}
							}
						},
						data : getData.data,
						links : getData.links,
						lineStyle : {
							normal : {
								opacity : 0.9,
								width : 2,
								curveness : 0
							}
						}
					}
				]
			};
			var myChart = echarts.init(document.getElementById('class-charts'));
			myChart.setOption(option);
	
		})
	</script>
</body>
</html>
