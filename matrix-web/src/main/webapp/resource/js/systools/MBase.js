/**
 * 加载后台运行时支持的css样式和js脚本
 * by 姜友瑶
 * 2015-12-1
 */
function getRootPath() {
	var curWwwPath = window.document.location.href;
	var pathName = window.document.location.pathname;
	var url=curWwwPath.substring(0, curWwwPath.indexOf(pathName));
	if(url.indexOf('localhost')>0){
		return url+ pathName.substring(0, pathName.substr(1).indexOf('/') + 1); 
	}
	if(url.split(".").length==4){
		return  url+ pathName.substring(0, pathName.substr(1).indexOf('/') + 1); 
	}
	return url; 
}
/** 全局路径，代表当前网站的根URL */
var basePath = getRootPath();

/*******************************************************************************
 * base style and script not change the file order
 ******************************************************************************/
document.write('<link href="' + basePath + '/resource/plugin/bootstrap-3.3.5/css/bootstrap.min.css" rel="stylesheet" type="text/css" />'
		+ '<link href="' + basePath + '/resource/plugin/Validform_v5.3.2/style.css" rel="stylesheet" type="text/css" />'
		+ '<link href="' + basePath + '/resource/plugin/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />'
		+ '<link href="' + basePath + '/resource/css/styleOne/animate.min.css" rel="stylesheet" type="text/css" />'
		+ '<link href="' + basePath + '/resource/css/styleOne/style.min.css" rel="stylesheet" type="text/css" />'
		+ '<link href="' + basePath + '/resource/css/styleOne/awesome-bootstrap-checkbox.css" rel="stylesheet" type="text/css" />'
		
		+ '	<link href="' + basePath + '/resource/plugin/datetimrpicker/bootstrap-datetimepicker.css" rel="stylesheet">'
		+ '<link href="' + basePath + '/resource/plugin/bootstrap-table/bootstrap-table.css" rel="stylesheet" type="text/css" />'
		+ '<link href="' + basePath + '/resource/plugin/select2/select2.min.css" rel="stylesheet" />'
		+ '<link href="' + basePath + '/resource/plugin/bootstrapvalidator/css/bootstrapValidator.css" rel="stylesheet" />'
		
		/*全页面图标*/
		+ '<link rel="shortcut icon" href="' + basePath + '/resource/images/favicon.ico">'
		
		);
