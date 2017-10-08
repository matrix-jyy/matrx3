<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.zkingsoft.com" prefix="matrix"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<LINK rel="Bookmark" href="../images/favicon.ico">
<!-- 本框架基本脚本和样式 -->
<script type="text/javascript"
	src="${path }/resource/js/plugin/jquery-2.1.4.min.js"></script>
<script type="text/javascript"
	src="${path }/resource/js/systools/MBase.js"></script>
<script type="text/javascript"
	src="${path }/resource/js/plugin/echarts.js"></script>	
<style type="text/css">
	.div{
		height: 200px;
	}
	.div1{
	height: 500px;
	}
</style>
</head>
<body class="gray-bg">
	<div class="panel panel-default">
  		<div class="panel-heading">
    		<h3 class="panel-title">柱状图</h3>
 		 </div>
		 <div class="panel-body">
		    <div class="row">
				<div class="col-xs-4 div" id="echarts1"></div>
				<div class="col-xs-4 div" id="echarts2"></div>
				<div class="col-xs-4 div" id="echarts3"></div>
			</div>
		  </div>
	</div>
	<div class="panel panel-default">
  		<div class="panel-heading">
    		<h3 class="panel-title">饼状图</h3>
 		 </div>
		 <div class="panel-body">
		    <div class="row">
				<div class="col-xs-4 div" id="echarts4"></div>
				<div class="col-xs-4 div" id="echarts5"></div>
				<div class="col-xs-4 div" id="echarts6"></div>
			</div>
		  </div>
	</div>
	<div class="panel panel-default">
  		<div class="panel-heading">
    		<h3 class="panel-title">折线图</h3>
 		 </div>
		 <div class="panel-body">
		    <div class="row">
				<div class="col-xs-4 div" id="echarts7"></div>
				<div class="col-xs-4 div" id="echarts8"></div>
				<div class="col-xs-4 div" id="echarts9"></div>
			</div>
		  </div>
	</div>
	<div class="panel panel-default">
  		<div class="panel-heading">
    		<h3 class="panel-title">环状图</h3>
 		 </div>
		 <div class="panel-body">
		    <div class="row">
				<div class="col-xs-4 div" id="echarts10"></div>
				<div class="col-xs-4 div" id="echarts11"></div>
				<div class="col-xs-4 div" id="echarts12"></div>
			</div>
		  </div>
	</div>
	<div class="panel panel-default">
  		<div class="panel-heading">
    		<h3 class="panel-title">其他图形</h3>
 		 </div>
		 <div class="panel-body">
		    <div class="row">
				<div class="col-xs-4 div1" id="echarts13"></div>
				<div class="col-xs-8 div1" id="echarts14"></div>
			</div>
		  </div>
	</div>
</body>
<script type="text/javascript"
	src="${path }/resource/js/systools/MJsBase.js"></script>
<script type="text/javascript">



//============================================================================
var myEcharts1 = echarts.init(document.getElementById("echarts1"));
myEcharts1.setOption({
	color: ['#3398DB'],
    tooltip : {
        trigger: 'axis',
        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        }
    },
 
    xAxis : [
        {
            type : 'category',
            data : ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
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
            name:'直接访问',
            type:'bar',
            barWidth: '60%',
            data:[10, 52, 200, 334, 390, 330, 220]
        }
    ]
    
});
//============================================================================
var myEcharts2 = echarts.init(document.getElementById("echarts2"));
myEcharts2.setOption({
	   tooltip : {
	        trigger: 'axis',
	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
	        }
	    },
	    legend: {
	        data:['直接访问','邮件营销','联盟广告','视频广告','搜索引擎','百度','谷歌','必应','其他']
	    },
	    xAxis : [
	        {
	            type : 'category',
	            data : ['周一','周二','周三','周四','周五','周六','周日']
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value'
	        }
	    ],
	    series : [
	        {
	            name:'直接访问',
	            type:'bar',
	            data:[320, 332, 301, 334, 390, 330, 320]
	        },
	        {
	            name:'邮件营销',
	            type:'bar',
	            stack: '广告',
	            data:[120, 132, 101, 134, 90, 230, 210]
	        },
	        {
	            name:'联盟广告',
	            type:'bar',
	            stack: '广告',
	            data:[220, 182, 191, 234, 290, 330, 310]
	        },
	        {
	            name:'视频广告',
	            type:'bar',
	            stack: '广告',
	            data:[150, 232, 201, 154, 190, 330, 410]
	        },
	        {
	            name:'搜索引擎',
	            type:'bar',
	            data:[862, 1018, 964, 1026, 1679, 1600, 1570],
	           /*  markLine : {
	                lineStyle: {
	                    normal: {
	                        type: 'dashed'
	                    }
	                },
	                data : [
	                    [{type : 'min'}, {type : 'max'}]
	                ]
	            } */
	        },
	        {
	            name:'百度',
	            type:'bar',
	            barWidth : 5,
	            stack: '搜索引擎',
	            data:[620, 732, 701, 734, 1090, 1130, 1120]
	        },
	        {
	            name:'谷歌',
	            type:'bar',
	            stack: '搜索引擎',
	            data:[120, 132, 101, 134, 290, 230, 220]
	        },
	        {
	            name:'必应',
	            type:'bar',
	            stack: '搜索引擎',
	            data:[60, 72, 71, 74, 190, 130, 110]
	        },
	        {
	            name:'其他',
	            type:'bar',
	            stack: '搜索引擎',
	            data:[62, 82, 91, 84, 109, 110, 120]
	        }
	    ]
	
	
})

//============================================================================
var myEcharts3 = echarts.init(document.getElementById("echarts3"));
myEcharts3.setOption({
	title: {
        text: 'IOS',
    },
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'shadow'
        }
    },
    
    xAxis: {
        type: 'value',
        boundaryGap: [0, 0.01]
    },
    yAxis: {
        type: 'category',
        data: ['4.x','5.x','6.x','7.x','中国']
    },
    series: [
             {
                 type:'bar',
                 data:[33,75,150,1800],
             
             },
     ]
    
});


//============================================================================
var myEcharts4 = echarts.init(document.getElementById("echarts4"));
myEcharts4.setOption({	
	title : {
        text: '某站点用户访问来源',
        subtext: '纯属虚构',
        x:'center'
    },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        left: 'left',
        data: ['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
    },
    series : [
        {
            name: '访问来源',
            type: 'pie',
            radius : '55%',
            center: ['50%', '60%'],
            data:[
                {value:335, name:'直接访问'},
                {value:310, name:'邮件营销'},
                {value:234, name:'联盟广告'},
                {value:135, name:'视频广告'},
                {value:1548, name:'搜索引擎'}
            ],
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
	
})
//============================================================================
var myEcharts5 = echarts.init(document.getElementById("echarts5"));
myEcharts5.setOption({
	series : [ {
		name : "饼状图",
		//类型饼状图
		type : "pie",
		//半径
		radius : '50%',
		roseType : 'angle',
		itemStyle:{ 
            normal:{ 
                label:{ 
                   show: true, 
                   formatter: '{b} : {c} ({d}%)' 
                }, 
                labelLine :{show:true}
            } 
        } ,
		data : [ {
			value : 400,
			name : '搜索引擎'
		}, {
			value : 335,
			name : '直接访问'
		}, {
			value : 310,
			name : '邮件营销'
		}, {
			value : 274,
			name : '联盟广告'
		}, {
			value : 235,
			name : '视频广告'
		} ]
	}]

	
})
//============================================================================
var myEcharts6 = echarts.init(document.getElementById("echarts6"));
myEcharts6.setOption({
	series : [ {
		name : "饼状图",
		//类型饼状图
		type : "pie",
		//半径
		radius : '90%',
		//roseType : 'angle',
		itemStyle:{ 
            normal:{ 
                label:{ 
                   show: true, 
                   formatter: '{d}%' ,
                }, 
            } 
        } ,
        label: {   //内部显示
            normal: {
                position: 'inner'
            }
        },
        labelLine: { //取消折线
            normal: {
                show: false
            }
        },
		data : [ {
			value : 400,
			name : '搜索引擎'
		}, {
			value : 335,
			name : '直接访问'
		}, {
			value : 310,
			name : '邮件营销'
		}, {
			value : 274,
			name : '联盟广告'
		}, {
			value : 235,
			name : '视频广告'
		} ]
	}]

});
//============================================================================
var myEcharts7 = echarts.init(document.getElementById("echarts7"));
myEcharts7.setOption({
	 	tooltip: {
	    	x:'right',
	        trigger: 'axis'
	    },
		 xAxis:  {
	        type: 'category',
	        boundaryGap: false,
	        data: ['1','2','3','4','5','6','7','8','9','10','11','12','13','14','15']
	    },
	    yAxis: {
	        type: 'value',
	    },
	    series: [
	             {
	                 name:'红色',
	                 type:'line',
	                 smooth: true,
	                 data:[200,180,220, 210, 224, 215, 150,215,280,200,220,110,120,110,200],
	             
	             },
	     ]
})
//=============================================================================

var myEcharts8 = echarts.init(document.getElementById("echarts8"));
myEcharts8.setOption({
	title: {
        text: '多折线图',
    },
    tooltip: {
    	x:'right',
        trigger: 'axis'
    },
    legend: {
        data:['红色','绿色']
    },
    
    xAxis:  {
        type: 'category',
        boundaryGap: false,
        data: ['00:00','01:00','02:00','03:00','04:00','05:00','06:00','07:00','08:00','09:00','10:00']
    },
    yAxis: {
        type: 'value',
        data:[0,100,200,300]
    },
    series: [
             {
                 name:'红色',
                 type:'line',
                 data:[200,180,220, 210, 224, 215, 150,215,280,200,220],
             
             },
             {
                 name:'绿色',
                 type:'line',
                 data:[100,90, 120,50, 40,70, 70, 40,30,120,90],
               
             }
         ]
    
})

//============================================================================
var myEcharts9 = echarts.init(document.getElementById("echarts9"));
myEcharts9.setOption({
	 title: {
	        text: '堆叠区域图'
	    },
	    tooltip : {
	        trigger: 'axis'
	    },
	    legend: {
	        data:['邮件营销','联盟广告','视频广告','直接访问','搜索引擎']
	    },
	    toolbox: {
	        feature: {
	            saveAsImage: {}
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
	            boundaryGap : false,
	            data : ['周一','周二','周三','周四','周五','周六','周日']
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value'
	        }
	    ],
	    series : [
	        {
	            name:'邮件营销',
	            type:'line',
	            stack: '总量',
	            areaStyle: {normal: {}},
	            data:[120, 132, 101, 134, 90, 230, 210]
	        },
	        {
	            name:'联盟广告',
	            type:'line',
	            stack: '总量',
	            areaStyle: {normal: {}},
	            data:[220, 182, 191, 234, 290, 330, 310]
	        },
	        {
	            name:'视频广告',
	            type:'line',
	            stack: '总量',
	            areaStyle: {normal: {}},
	            data:[150, 232, 201, 154, 190, 330, 410]
	        },
	        {
	            name:'直接访问',
	            type:'line',
	            stack: '总量',
	            areaStyle: {normal: {}},
	            data:[320, 332, 301, 334, 390, 330, 320]
	        },
	        {
	            name:'搜索引擎',
	            type:'line',
	            stack: '总量',
	            label: {
	                normal: {
	                    show: true,
	                    position: 'top'
	                }
	            },
	            areaStyle: {normal: {}},
	            data:[820, 932, 901, 934, 1290, 1330, 1320]
	        }
	    ]
});
//--=========================================================
var myEcharts10 = echarts.init(document.getElementById("echarts10"));
myEcharts10.setOption({
	tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b}: {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        x: 'left',
        y: 'bottom',
        data:['f1','f2','f3','f4','f5']
    },
	
    series: [
             {
                 name:'故障分析',
                 type:'pie',
                 radius: ['50%', '70%'],       //内环半径，外环半径
                 avoidLabelOverlap: false,
                 label: {
                     normal: {
                         show: false,
                         position: 'center'
                     },
                     emphasis: {
                         show: true,
                         textStyle: {
                             fontSize: '30',
                             fontWeight: 'bold'
                         }
                     }
                 },
                 labelLine: {
                     normal: {
                         show: false
                     }
                 },
                 data:[
                     {value:335, name:'f1'},
                     {value:310, name:'f2'},
                     {value:234, name:'f3'},
                     {value:135, name:'f4'},
                     {value:1548, name:'f5'}  
                 ]
             }
         ]
	
});
//============================================================================

var myEcharts11 = echarts.init(document.getElementById("echarts11"));
myEcharts11.setOption({
	title: {
		x:'right',
		y:'bottom',
        text: '模块一',
    },
    legend: {
        orient: 'vertical',
        x: 'right',
        data:['温度一','温度二','温度三']
    },
    series: [
             {
                 name:'1',
                 type:'pie',
                 clockWise : false,
                 radius: ['70', '90'],   //内环半径，外环半径
                 hoverAnimation:false,    //扇形放大效果 
                 label: {normal: {
                         show: false,
                         position: 'center'
                     },
                     emphasis: {  
                    	 show: true,
                         textStyle: {
                             fontSize: '8',
                             fontWeight: 'bold'
                         }
                     }
                 },
                 labelLine: { normal: {
                         show: false
                     }
                 },
                 data : [ {
     				value : 40,
     				name : '温度一'
     			}, {
     				value : 60,
     				name : 'invisible',
     				itemStyle : {
     					normal:{opacity:0},
     					emphasis:{opacity:0},
     				}
     			} ]
                
             },
             {
                 name:'2',
                 type:'pie',
                 clockWise : false,
                 radius: ['50', '70'],   //内环半径，外环半径
                 hoverAnimation:false,    //扇形放大效果 
                 label: {normal: {
                     show: false,
                     position: 'center'
                 },
                 emphasis: {  
                	 show: true,
                     textStyle: {
                         fontSize: '8',
                         fontWeight: 'bold'
                     }
                 }
                 },
                 labelLine: { normal: {
                         show: false
                     }
                 },
                 data : [ {
     				value : 60,
     				name : '温度二'
     			}, {
     				value : 40,
     				name : 'invisible',
     				itemStyle : {
     					normal:{opacity:0},
     					emphasis:{opacity:0},
     				}
     			} ]
                
             },
             {
                 name:'3',
                 type:'pie',
                 clockWise : false,
                 radius: ['30', '50'],   //内环半径，外环半径
                 hoverAnimation:false,    //扇形放大效果
                 label: {normal: {
                     show: false,
                     position: 'center'
                 },
                 emphasis: {  
                	 show: true,
                     textStyle: {
                         fontSize: '8',
                         fontWeight: 'bold'
                     }
                 }
                 },
                 labelLine: { normal: {
                         show: false
                     }
                 },
                 data : [ {
     				value : 30,
     				name : '温度三'
     			}, {
     				value : 70,
     				name : 'invisible',
     				itemStyle : {
     					normal:{opacity:0},
     					emphasis:{opacity:0},
     				}
     			} ]
                
             },
             
         ]
});

//--=========================================================
var myEcharts12 = echarts.init(document.getElementById("echarts12"));
myEcharts12.setOption({
	 series : [
	           {
	               type: 'pie',
	               radius : ['60%', '80%'],
	               label: {
	                   normal: {
	                       position: 'center'
	                   }  
	               },
	               data:[
	                   {
	                       value:335, name:'完成率',
	                       label: {
	                           normal: {
	                               formatter: '{d} %',
	                               textStyle: {
	                                   fontSize: 12
	                               }
	                           }
	                       }
	                   },
	                   {
	                       value:310, name:'',
	                       tooltip: {
	                           show: false
	                       },
	                       itemStyle: {
	                           normal: {
	                               color: '#999'
	                           }
	                       },
	                       label: {
	                           normal: {
	                               formatter: '\n完成率'
	                           }
	                       }
	                   }
	               ]
	           }
	       ]
})
//--=========================================================
var myEcharts13 = echarts.init(document.getElementById("echarts13"));
myEcharts13.setOption({
	 
	    series: [
	        {
	            name: '给定转速',
	            type: 'gauge',
	            radius :'100',
	            startAngle :'180',
	            endAngle :'0',
	            min:0,
	            max:10,
	            title :{
	            	offsetCenter :[0,'-20%'],
	            },
	         detail: {formatter:'{value} kr/m'},
	            data: [{value: 2864/1000, name: 'kr/m'}]
	        }
	    ]
});
//--=========================================================
var myEcharts13 = echarts.init(document.getElementById("echarts13"));
myEcharts13.setOption({
	 
	    series: [
	        {
	            name: '给定转速',
	            type: 'gauge',
	            radius :'100',
	            startAngle :'180',
	            endAngle :'0',
	            min:0,
	            max:10,
	            title :{
	            	offsetCenter :[0,'-20%'],
	            },
	            detail: {formatter:'{value} kr/m'},
	            data: [{value: 2864/1000, name: 'kr/m'}]
	        }
	    ]
})

//混合图形加缩放
var xData = function() {
    var data = [];
    for (var i = 1; i < 15; i++) {
        data.push(i + "月份");
    }
    return data;     //获取x时间轴
}();
var data1=[709,1917,2455,2610,1719,1433,1544,3285,5208,3372,2484,4078];
var data2=[327,1776,507,1200,800,482,204,1390,1001,951,381,220];
var data3=[1036,3693,2962,3810,2519,1915,1748,4675,6209,4323,2865,4298];

var myEcharts14 = echarts.init(document.getElementById("echarts14"));
myEcharts14.setOption({
	// backgroundColor: "#344b58",                      //背景颜色
	    "title": {									  //标题
	        "text": "混合图形加缩放",
	        "subtext": "副标题",
	        //x: "4%",

	        textStyle: {
	            color: '#fff',
	            fontSize: '22'
	        },
	        subtextStyle: {
	            color: '#90979c',
	            fontSize: '16',

	        },
	    },
	    
	    "tooltip": {								 //指示样式
	        "trigger": "axis",
	        "axisPointer": {
	            "type": "shadow",
	            textStyle: {
	                color: "#fff"
	            }

	        },
	    },
	    
	    "grid": {										//坐标系
	        "borderWidth": 0,
	        "top": 110,
	        "bottom": 95,
	        textStyle: {
	            color: "#fff"
	        }
	    },
	    
	    "legend": {										//图例组件
	        x: '4%',
	        top: '11%',
	        textStyle: {
	            color: '#90979c',
	        },
	        "data": ['女', '男'] //要和series中name值对应
	    },
	   
	    "xAxis": [{
	        "type": "category",                         //x轴
	        "axisLine": {
	            lineStyle: {
	                color: '#90979c'
	            }
	        },
	        "splitLine": {
	            "show": false
	        },
	        "axisTick": {
	            "show": false
	        },
	        "splitArea": {
	            "show": false
	        },
	        "axisLabel": {
	            "interval": 0,

	        },
	        "data": xData, //x轴值和series中data对应
	    }],
	    "yAxis": [{										//y轴值在series：data中设置
	        "type": "value",
	        "splitLine": {
	            "show": false
	        },
	        "axisLine": {
	            lineStyle: {
	                color: '#90979c'
	            }
	        },
	        "axisTick": {
	            "show": false
	        },
	        "axisLabel": {
	            "interval": 0,

	        },
	        "splitArea": {
	            "show": false
	        },

	    }],
	    
	    "calculable": true,										//是否显示拖拽用的手柄
	    "dataZoom": [{											//缩放轴
	        "show": true,
	        "height": 30,
	        "xAxisIndex": [
	            0
	        ],
	        bottom: 30,
	        "start": 10,
	        "end": 80,
	        handleIcon: 'path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z',
	        handleSize: '110%',
	        handleStyle:{
	            color:"#d3dee5",
	            
	        },
	           textStyle:{
	            color:"#fff"},
	           borderColor:"#90979c"
	        
	        
	    }, {
	        "type": "inside",
	        "show": true,
	        "height": 15,
	        "start": 1,
	        "end": 35
	    }],
	    
	    
	    "series": [{												//y轴对应的值
	            "name": "女",
	            "type": "bar",	  //柱状图
	            "stack": "总量",   //名称相同的值会进行累加
	            "barMaxWidth": 35,
	            "barGap": "10%",
	            "itemStyle": {
	                "normal": {
	                    "color": "rgba(255,144,128,1)",
	                    "label": {
	                        "show": true,
	                        "textStyle": {
	                            "color": "#fff"
	                        },
	                        "position": "insideTop",
	                        formatter: function(p) {
	                            return p.value > 0 ? (p.value) : '';
	                        }
	                    }
	                }
	            },
	            "data": data1,
	        },

	        {
	            "name": "男",
	            "type": "bar",
	            "stack": "总量",
	            "itemStyle": {
	                "normal": {
	                    "color": "rgba(0,191,183,1)",
	                    "barBorderRadius": 0,
	                    "label": {
	                        "show": true,
	                        "position": "top",
	                        formatter: function(p) {
	                            return p.value > 0 ? (p.value) : '';
	                        }
	                    }
	                }
	            },
	            "data": data2
	        }, {
	            "name": "总数",
	            "type": "line",//折线图
	            "stack": "总量",//在柱状图上堆叠
	            symbolSize:10,
	            symbol:'circle',
	            "itemStyle": {
	                "normal": {
	                    "color": "rgba(252,230,48,1)",
	                    "barBorderRadius": 0,
	                    "label": {
	                        "show": true,
	                        "position": "top",
	                        formatter: function(p) {
	                            return p.value > 0 ? (p.value) : '';
	                        }
	                    }
	                }
	            },
	            "data": data3
	        },
	    ]
})
</script>
</html>
