/**
 * 自定义UI组件库
 * @author 姜友瑶
 * */

/**
 * 自动填充select中的option节点，改节点显示有树的形状 selectID select控件的di
 * options=[{ID:'213',PARENT_ID:'123123',NAME:'JYY',value:'213'},{{ID:'213',PARENT_ID:'123123',NAME:'JYY'}}]
 */

var MUI=function(){
	
	"use strict";// 严格模式
	
return {
		
	//全局常量 弹窗宽度
	 SIZE_S:'40%',
	 SIZE_L:'70%',
	 SIZE_M:['100%','100%'],
	 
	 /** 图片在上传的时候，需要在页面嵌套一个jspifram页面 页面处理完图片后服务器返回脚本代码控制父窗口显示图片。 */
	 upolad : function(img_id, file_id) {
	 	if ($("#" + file_id).val() == '') {
	 		layer.alert("请先选择要上传的文件！");
	 		return;
	 	}
	 	$("#" + img_id).attr("src", basePath + "/plat/images/loading.gif");
	 	$("#" + img_id).show();
	 },


	 /**
	  * 打开多图上传页面
	  * 
	  * @param fn
	  * 回调函数 {err:0,url:'http://12312!!!!.jpg'}
	  */
	 openMultipleUpload : function(fn) {
	 	KindEditor.ready(function(K) {
	 		var editor = K.editor({
	 			cssPath :basePath + '/resource/plugin/kindeditor/plugins/code/prettify.css',
	 			uploadJson :basePath + '/resource/plugin/kindeditor/uploadFile.jsp',
	 			fileManagerJson :basePath + '/admin/uploadFile/loadGallery',
	 			allowFileManager : true
	 		});
	 		editor.loadPlugin('multiimage', function() {
	 			editor.plugin.multiImageDialog({
	 				clickFn : function(urlList) {
	 					fn(urlList);
	 					editor.hideDialog();
	 				}
	 			});
	 		});
	 	});
	 },

	 /**
	  * 打开远程图片空间
	  * 
	  * @param fn 回调函数 url:'http://12312.jpg'
	  *            event 触发开启事件源
	  */
	 openServiceImgSpace : function(fnn,event) {
	 	KindEditor.ready(function(K) {
	 		var editor = K.editor({
	 			fileManagerJson : basePath + '/admin/uploadFile/loadGallery',
	 		});
	 		
	 		$(event).click(function(){
	 			
	 			editor.loadPlugin('filemanager', function() {
		 			editor.plugin.filemanagerDialog({
		 				viewType : 'VIEW',
		 				dirName : 'image',
		 				clickFn : function(url, title) {
		 					fnn(url);
		 					editor.hideDialog();
		 				}
		 			});
		 		});
	 		});
	 	});
	 },
	 /**
	  * 创建一个富文本编辑器
	  */
	 createEdit:function( name, width ,heigt ,editor ){
		 
		 KindEditor.ready(function(K) {
			window[editor]= K.create('textarea[name="'+name+'"]', {
					cssPath :  basePath + '/resource/plugin/kindeditor/plugins/code/prettify.css',
					uploadJson :  basePath + '/resource/plugin/kindeditor/uploadFile.jsp',
					fileManagerJson :  basePath + '/admin/uploadFile/loadGallery',
					allowFileManager : true,
					width : width,
					height : heigt,
					afterCreate : function() {
						var self = this;
						K.ctrl(document, 13, function() {
							self.sync();
							document.forms['example'].submit();
						});
						K.ctrl(self.edit.doc, 13, function() {
							self.sync();
							document.forms['example'].submit();
						});
					}
				});
			prettyPrint();
			});
	 },
	 /**
	  * 初始化地区选择框
	  */
	initAreaInfo:function(option){
		//请求地址
		var url=basePath+"/busCommon/findChildenArea";
		// 初始化参数
		var initParam = {
			params: [{divId:"a",info:"aa"},{divId:"b",info:"bb"}],//对应的html元素id,以及提示信息
			parentId:0,//我们规定数据库中最高级别没有父级id,因此父级id为0
			defInfo:"请选择"//默认的提示信息，如果没有info或者info为空字符串则提示默认的信息
		};
		$.extend(initParam, option);
		var htmlInfo=initParam.params;
		for(var i=0;i<htmlInfo.length;i++ ){
		    var obj="";
			var html="";
			obj=htmlInfo[i];
			if(i==0){
				//getChildArea(htmlInfo[i].divId,parentId,htmlInfo[i].info);
				$.AjaxProxy({a:false,c:false,p:{parentId:initParam.parentId}}).invoke(url,function(loj){
					//如果没有提示信息则显示为默认的提示信息
					if(!obj.info || obj.info==''){
						obj.info=initParam.defInfo;
					}
					html+='<option value="">'+obj.info+'</option>';
					for ( var j = 0; j < loj.getRowCount(); j++) {
						html+='<option value="'+ loj.getString(j,"areaId")+'">'+ loj.getString(j,"areaName")+'</option>';
					   }
					$("#"+obj.divId).html(html);
				});
			}
			$("#"+obj.divId).change(function(){
				var sValue=this.options[this.options.selectedIndex].value;
				console.log(sValue);
				for ( var k = 0; k <htmlInfo.length; k++) {
                    if(htmlInfo[k].divId==$(this).attr("id") && k+1<htmlInfo.length){
                    	 var childParam=htmlInfo[(k+1)];
                    	if(!childParam.info || childParam.info==''){
                    		childParam.info=initParam.defInfo;
    					}
        				MUI.getChildArea(childParam.divId,sValue,childParam.info,url);
                    }
				}
			});
			
		}
	},
	getChildArea:function(divId,parentId,info,url){
		var html="";
		html+='<option value="">'+info+'</option>';
		$.AjaxProxy({a:true,c:true,p:{parentId:parentId}}).invoke(url,function(loj){
			for ( var i = 0; i < loj.getRowCount(); i++) {
				html+='<option value="'+ loj.getString(i,"areaId")+'">'+ loj.getString(i,"areaName")+'</option>';
			   }
			if(loj.getRowCount()<=0){
				$("#"+divId).hide();
			}
			else{
				$("#"+divId).show();
				$("#"+divId).html(html);
			}
		});
	},
	/**
	 * 初始化文件上传
	 * 需要传一个input框的选择器，支持多个文件同时初始化
	 * 实现原理：在页面创建一个ifram用于提交文件，每个input框会对应的创建一个隐藏input，把原始
	 * input的值复制给隐藏input这个input会用于最后的提交。
	 * 还会创建一个form表单，表单中有一个文件选择框。
	 * 点击原始input会触发文件选择器的点击事件弹出文件选择框。
	 * 文件上传后会改变隐藏input框架的value值。
	 * 
	 */
	initImgUpload:function(selected){
	
		//文件类型
		var fileTypes="gif,jpg,jpeg,png,bmp";
		//最大5M
		var maxSize=5*1024*1024;
		
		//在界面创建一个iframe
		var iframe = $('<iframe name="blankFrame" ></iframe>');
		iframe.css("display", "none");	
		$("body").append(iframe);
		$(selected).each(function(i){
			var $input=$(this);
			var id=$input.attr("id");
			if($input.data("filetypes")){
				fileTypes=$input.data("filetypes");
			}
			
			//创建一个隐藏控件,如果input有值创建一个img
			if($input.val()!=null &&$input.val().length>0 ){
				$input.next("a").after("<img class='loadingbig' src='"+$input.val()+"' />")
				$input.before("<input  name='"+$input.attr("name")+"' value='"+$input.val()+"'   id='"+$input.attr("id")+"_hide'  style='display:none;' /> ")
			}else{
				$input.before("<input  name='"+$input.attr("name")+"'   id='"+$input.attr("id")+"_hide'  style='display:none;' /> ")
			}
			//删除控件本身的name属性
			$input.removeAttr("name");
			//添加点form表单
			var form = $('<form method="post" enctype="multipart/form-data" target="blankFrame"  style="display:none;" ></form>')
			form.attr("action",basePath+"/admin/uploadFile/doUpload?callBack=MUI.uploadCallBack&inputId="+id);
			//创建一个file文件
			var file=$('<input  type="file" multiple name="file-1" />');
			form.append(file);
			//文件改变就提交
			file.change(function(){
				var filePath = file.val();
				var fileName = filePath.substring(filePath.lastIndexOf("\\") + 1,
						filePath.length);
				//文件类型判断
				var photoExt=fileName.substr(fileName.lastIndexOf(".")+1).toLowerCase();//获得文件后缀名
			    if(fileTypes.indexOf(photoExt)<0){
			       layer.msg("允许的文件类型是："+fileTypes,{icon:2,time:2000});
			        return false;
			    }

			    var obj=file.get(0);
			    var fileSize = 0;
			  	fileSize = obj.files[0].size;     
			    if(fileSize>=maxSize){
			        layer.msg("照片最大尺寸为"+maxSize/1024+"KB，请重新选择文件!",{icon:2});
			        return false;
			    }
				//校验通过开始上传
			    
				//改变输入框字符
				$input.val(fileName);
				//创建加载图
				if($input.next().next().is("img")){
					$input.next().next().attr("src" ,basePath+"/resource/images/loadingbig.gif");
				}else{
					$input.next("a").after("<img class='loadingbig' src='"+basePath+"/resource/images/loadingbig.gif' />")
				}
				//提交表单
				form.submit();
			});
			// 绑定事件
			$input.click(function(){
				file.trigger("click");
			});
			$input.next("a").click(function(){
				file.trigger("click");
			});
			//把组件添加到文档中
			$("body").append(form);
		});
		
		
	},
	 uploadCallBack:function(id,path){
		 console.log(path);
		$("#"+id+"_hide").val(path);
		$("#"+id).next().next().attr("src" ,path);
	}
	
	
}
	
}();


//定义一些jquery UI插件
(function($) {
	
	$.fn.extend({
		
		createSelectTree : function(options, seting) {
			// 配置对象
			var _seting = {
				id : "id",		// 选项的值
				parent : "parentId",	// 父节点值
				value : "name",	// 要显示的名称
				append:false,	// 是否为追加 ，默认不追加
				defaultValue:null,  //默认选中值
				defaultHtml:"",  //默认option
			}
			var _seting = $.extend(_seting, seting);
			// 用每一个节点的id标识一个节点对象，这里是所有对象的map集合
			var _options = {};
			var nodeNength = options.length;
			for (var i = 0; i < nodeNength; i++) {
				// 用id来唯一标识这个节点
				_options[options[i][_seting.id]] = options[i];
			}
			// 构建树模型 root 是这课树的集合
			var root = {};
			root.children = [];
			for (var i = 0; i < nodeNength; i++) {
				var node = options[i];
				// _options中 如果存在 options 对应的 父节，把当前的节点放入父节点
				if (_options[node[_seting.parent]]) {
					var parent = _options[node[_seting.parent]];
					if (parent.children) {
						parent.children.push(_options[node[_seting.id]]);
					} else {
						parent.children = [];
						parent.children.push(_options[node[_seting.id]]);
					}
				} else {// 如果不存在父节点就放入根节点中
					root.children.push(_options[node[_seting.id]]);
				}
			}
			// 创建html代码加载到树当中
			var html = "";
			if(_seting.defaultHtml){
				html+=_seting.defaultHtml;
			}
			html += this.getOptionHtml(root.children, _seting, _options);
			
			if(_seting.append){
				$(this).html($(this).html()+html);
			}else{
				$(this).html(html);	
			}
			
			
		},
		getOptionHtml : function(children, _seting, _options) {
			if (children) {
				var length = children.length;
				var html = "";
				for (var i = 0; i < length; i++) {
					var node = children[i];
					var prefix = "| ";
					var count = 0;
					count += this.getParentCount(node, _seting, count, _options);
					for (var j = 0; j < count; j++) {
						prefix += " - ";
					}
					if(_seting.defaultValue && _seting.defaultValue==node[_seting.id] ){
						html += "<option selected='selected' value='" + node[_seting.id] + "' >" + prefix + node[_seting.value] + "</option>"
					}else{
						html += "<option value='" + node[_seting.id] + "' >" + prefix + node[_seting.value] + "</option>"
					}
					html += this.getOptionHtml(node.children, _seting, _options);
				}
				return html;
			} else {
				return "";
			}
		},
		// 获父节点数量
		getParentCount : function(node, _seting, count, _options) {
			var str = _options[node[_seting.parent]];
			if (str) {
				count++;
				return this.getParentCount(_options[node[_seting.parent]], _seting, count, _options);
			} else {
				return count;
			}
		}
	});

})(jQuery);






















