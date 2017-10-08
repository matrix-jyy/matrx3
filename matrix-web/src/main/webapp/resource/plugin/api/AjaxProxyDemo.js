//示例1 =============================================
function simpleTest() {
	var data = {
		kinds : '水果'
	};// 传到服务器的参数

	var param = {
		p : data, // p表示要传到服务器的参数
		c : true,// c表示是否在页面显示遮罩层 默认为true
		a : true
	// a表示是否异步请求 默认为是true
	};

	// 初始化AjaxProxy
	var ajaxProxy = $.AjaxProxy(param);
	// 执行请求 第一个参数是访问地址，第二个参数是回调函数
	ajaxProxy.invoke(basePath+"/api/findFruits", function(loj) {
		//这里是回调函数
		// 服务器返回参数被封装在 result 属性中
		$("#serviceParam").html(JSON.stringify(loj.attr("result")));
	});
}
// 示例1end =============================================

//示例2 =============================================
function simpleTest2() {

	$.AjaxProxy({
		
		p:{kinds : '水果'}
		
	}).invoke(basePath+"/api/findFruits", function(loj) {
		
		var result=loj.attr("result");
		var html="";
		for(var i=0; i<result.rows.length; i++){
			html+="<tr><td>"
				+result.rows[i].name+"</td><td>"
				+result.rows[i].price+"</td><td>"
				+"<img width='100' height='100' src='"+result.rows[i].img+"'  /></td></tr>";
		}
		$("#tbody1").html(html);
		
	});
	

}
// 示例2end =============================================


//示例3 =============================================
function simpleTest3() {

	$.AjaxProxy({
		
		p:{kinds : '水果'}
		
	}).invoke(basePath+"/api/findFruits", function(loj) {
		
		var html="";
		for(var i=0; i<loj.getRowCount(); i++){
			html+="<tr><td>"
				+loj.getString(i,"name")+"</td><td>"
				+loj.getString(i,"price")+"</td><td>"
				+loj.getImg(i,"img")+"</td></tr>";
		}
		$("#tbody2").html(html);
		
	});
	

}
// 示例3end =============================================


