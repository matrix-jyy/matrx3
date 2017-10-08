//MGird基础展示============================
var myGrid;
function myGridShow() {
	$('#demo1').show();
	//初始化表格 
	myGrid=MGrid.initGrid({
		url: basePath+"/api/showList",  //数据加载地址
		delUrl: basePath+"/api/del"  //数据删除地址
	});
}


//MForm基础展示============================
var myForm;
function myFormShow() {
	$('#demo1').show();
	//初始化表单					
	myForm = MForm.initForm({
		invokeUrl : basePath+"/api/addOrModify"
	});
}