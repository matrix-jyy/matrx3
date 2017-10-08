/**
 * 公共功能js
 * 2016-11-17 by 姜友瑶
 */

var fnPublci=function(){
	
	return {
		
		/**
		 * 初始化一个树形的功能选择框  
		 * seler 选择器
		 * def 默认值
		 */
		initFunctionSelect:function (seler,def){
			$.AjaxProxy().invoke(basePath+"/admin/sysFunction/all",
					function(loj) {
						$(seler).createSelectTree(
										loj.attr("result").rows,
										{	id : "fnId",		
											parent : "fnParentId",	
											value : "fnName",	
											append : false,
											defaultValue : def,
											defaultHtml : '<option value="" selected="selected">一级功能</option>'
										});
					});
		},
		
		/**
		 * 打开图标显示界面，并把选中的值传给控件
		 * */
		 showIcon:function() {
			layer.open({
				type : 2,
				title : "选择图标",
				area : [  '80%', '80%' ],
				fix : true,
				maxmin : true,
				content : basePath+'/common/redirect/icons'
			});
		}
		
		
	}
}();
