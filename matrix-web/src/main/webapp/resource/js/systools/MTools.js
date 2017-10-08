/**
 * 常用工具和方法 本js依赖jQuery
 * 
 * @author 姜友瑶
 */

var MTools = {

	/**
	 * 用逗号分开的存的id是否包含对应的id
	 * 如比较 123,123,124 中是否包含12这个主键
	 * 比对的方法是 字符串中是否包含"^12$" "^12,.*" 或者  ".+,12$"  或者  ".+,12,$"或者 ".+,12,.+" 字符串
	 * */
	isContainId:function(ids,id){
		var reg1=new RegExp("^"+id+"$|^"+id+",.*|.+,"+id+"$|.+,"+id+",$|.+,"+id+",.+"); 
		if(reg1.test(ids)){
			return true;
		}else{
			return false;
		}
	},
		
	/**
	 * 对表格中单挑数据进行处理 请在url后添加参数 url: 远程地址 msg：提示信息 callbackfn:操作成功后回调函数
	 */
	handleItem : function(url, msg, callbackfn) {
		layer.confirm(msg, function() {
			$.AjaxProxy().invoke(url, function(loj) {
				layer.msg(loj.getValue("info"), {icon:1,time: 1000},function(){
					if (callbackfn) {
						callbackfn();
					}
				});
				
			});
		});
	},

	/** 返回一个随机数 */
	random : function() {
		return Math.floor(Math.random() * (1000000000 + 1));
	},

	/** 返回一个随机字符串 */
	randomStr : function() {
		return 'pp' + Math.floor(Math.random() * (1000000000 + 1));
	},

	/** 页面定向 */
	redirect : function(url) {
		window.location.href = url;
	},

	/** 页面定向 无视iframe */
	toPredirect : function(url) {
		window.top.location.href = url;
	},

	/** 图片在上传的时候，需要在页面嵌套一个jspifram页面 页面处理完图片后服务器返回脚本代码控制父窗口显示图片。 */
	upolad : function(img_id, file_id) {
		if ($("#" + file_id).val() == '') {
			layer.alert("请先选择要上传的文件！");
			return;
		}
		$("#" + img_id).attr("src",
				basePath + "/resource/images/loadingbig.gif");
		$("#" + img_id).show();
	},

	/** 在页面删除一个节点 */
	delThis : function(id) {
		$('#' + id).remove();
	},

	/** 在页面删除一个节点,带上滑效果 */
	delThisSlide : function(id) {
		$('#' + id).slideUp("fast", function() {
			$('#' + id).remove();
		});
	},

	changeValidateCode : function(id) {
		$("#" + id).attr("src",
				basePath + "/common/validateImg?rand=" + this.random())
	},

	chooesFile : function(fileElmentId) {
		$(fileElmentId).trigger("click");
	},
	upFile : function(submitBtn) {
		$(submitBtn).trigger("click");
	},
	selectFile : function(node,fileTextId,submitNode) {
		var filePath = $(fileTextId).val();
		var fileName = filePath.substring(filePath.lastIndexOf("\\") + 1,
				filePath.length);
		$(node).val(fileName);
		$(submitNode).trigger("click");
	},

	/**
	 * 根据autoFull的css类自动填充select框里面的option selecteder ：选择器名 value： 默认值为id file：
	 * 默认值name url： 请求路径 param：{type : ''} 对象 为查询条件属性 defaultValue： 默认值 by
	 * luoyuanhong 2016-7-13
	 */
	autoFullSelect : function() {

		var initParam = {
			value : "id",// value默认值
			filed : "name",// filed默认值
		}

		$(".autoFull")
				.each(
						function() {
							var _this = $(this);
							var def = _this.data("def");

							var param = eval("(" + _this.data("param") + ")");
							var value = _this.data("value") == null ? initParam.value
									: _this.data("value");
							var filed = _this.data("filed") == null ? initParam.filed
									: _this.data("filed");
							var url = _this.data("url");
							// 增加一个排除不显示的 值得,参数是数组如 jsp的input属性data-hidden=1,2
							eval("var hidden = [" + _this.data("hidden") + "]");
							// 取服务器请求数据
							$
									.AjaxProxy({
										p : param
									})
									.invoke(
											url,
											function(loj) {
												console.log(loj);
												var html = '';
												a: for (var i = 0; i < loj
														.getRowCount(); i++) {
													b: for (var j = 0; j < hidden.length; j++) {
														if (hidden[j]
																&& hidden[j] == loj
																		.getString(
																				i,
																				value)) {
															html += "";
															continue a;
														}
													}
													if (def
															&& ((def + "")
																	.indexOf(loj
																			.getString(
																					i,
																					value)) > -1)) {
														/*
														 * 修改页面仍然要保存请选择页面因此去掉
														 * _this.html('');
														 */
														html += '<option selected="selected" value="'
																+ loj
																		.getString(
																				i,
																				value)
																+ '">'
																+ loj
																		.getString(
																				i,
																				filed)
																+ "</option>";
													}

													else {
														html += '<option value="'
																+ loj
																		.getString(
																				i,
																				value)
																+ '">'
																+ loj
																		.getString(
																				i,
																				filed)
																+ "</option>";
													}

												}
												_this.append(html);
											});
						});
	},
	/**
	 * 初始化时间控件方法
	 * @param _initParam:
	 */
	ininDatetimepicker:function(_initParam){
		var initParam = {
			format : "yyyy-mm-dd", //默认显示年与日，如果想显示十分秒:"yyyy-mm-dd hh:ii:ss"
			maxView: 4, //日期时间选择器最高能展示的选择范围视图。
			minView: "month", 	//"month",只显示年月日的选择,不会再跳转去选择时分秒；如果想要选择时分秒的:"hour"
			autoclose: true,	//true,选择完是否自动关闭，如果不关闭，改为:false
			minuteStep: 5,	//此数值被当做步进值用于构建小时视图。对于每个 minuteStep 都会生成一组预设时间（分钟）用于选择
			weekStart : 1, //一周从哪一天开始。0（星期日）到6（星期六）
			timeSelecter: ".datetimepicker",  //时间选择器的类名
			state : "none", //none,不做限制，beforeToday：只能选择当前日期之前，aferToday：只能选择当前日期之后
			listenBlur:false //由于validform当输入日期后还提示输入，因此 时间按控件当日期更改后，触发该类元素的blur事件，validfrom会验证
		}
		
		if(_initParam!=null){
			//把传过来的参数覆盖默认参数
			initParam = $.extend(initParam, _initParam);
		}
		if(initParam.state=="beforeToday"){
			initParam = $.extend(initParam,{endDate : new Date()});
			//日期只能选择当前日，及当前日之前的日期
			
		}else if(initParam.state=="aferToday"){
			//日期只能选择当前日，及当前日之后的日期
			initParam = $.extend(initParam,{startDate : new Date()});
		 }
			//可以选择任意日期
		var dataObj=$(initParam.timeSelecter).datetimepicker(initParam);
		
		if(initParam.listenBlur){
			dataObj.on('changeDate', function(e){
				$(initParam.timeSelecter).trigger("blur");
			});	
		}
	},
	/**
	 * 结束时间不能小于开始时间
	 * beginTimeId:开始时间控件的id，注意要传#
	 * endTimeId：结束时间控件的id，注意要传#
	 */
	limitStartEndTime:function(_initParam){
		var initParam = {
				beginTimeId :"#beginTime",
				endTimeId : "#endTime"
			}		
		if(_initParam!=null){
			initParam = $.extend(initParam, _initParam);
		}
		$(initParam.endTimeId).datetimepicker('setStartDate', $(initParam.beginTimeId).val());
		$(initParam.beginTimeId).datetimepicker('setEndDate', $(initParam.endTimeId).val());

		//限制结束时间不能大于开始时间
		$(initParam.beginTimeId).datetimepicker().on('changeDate', function(e){
			var currentTime = e.date;
			$(initParam.endTimeId).datetimepicker('setStartDate', currentTime);
		});	
		$(initParam.endTimeId).datetimepicker().on('changeDate', function(e){
			var currentTime = e.date;
			$(initParam.beginTimeId).datetimepicker('setEndDate', currentTime);
		});
	},

	/* 关闭弹出的窗口 */
	closeForm : function() {
		var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
		parent.layer.close(index);
	}
}
