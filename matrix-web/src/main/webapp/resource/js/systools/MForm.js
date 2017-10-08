/**
 * 与表单相关的方法
 * 
 * @author 姜友瑶
 */

// 公用的form对象
var MForm = function() {
	"use strict";// 严格模式

	return {

		/**
		 * 表单提交前，初始化一些必要的事情
		 */
		initForm : function(_initParam) {

			var option = {
				beforeSubmit : null,// 表单提交之前调用函数
				afterSubmit : null,// 刷新数据回调函数
				formSelecter : "#dataform",// 表单默认选择器
				isvalid : true,// 是否对表单进行验证
				validform : null,// 验证表单
				extendParam : {},// 需要添加到表单中的额外参数会一起提交到服务器
				invokeUrl : "",// 数据提交地址
				layerIndex : "",// layer当前窗口的index值（用来关闭ifram）
				isAutoClose : true,// 是否自动关闭
			}
			option = $.extend(option, _initParam);
			var mForm = new mform(option);
			return mForm;
		},

		/**
		 * form表单数据转json
		 */
		toJson : function(form) {
			var result = {};
			var fieldArray = $(form).serializeArray();
			for (var i = 0; i < fieldArray.length; i++) {
				var field = fieldArray[i];
				if (field.name in result) {
					result[field.name] += "," + field.value;
				} else {
					result[field.name] = field.value;
				}
			}
			return result;
		},
		/**
		 * 重置表单
		 */
		reset : function clear_form(formId) {
			$(':input', formId).not(':button, :submit, :reset, :hidden')
					.val('').removeAttr('value').removeAttr('checked')
					.removeAttr('selected');
			$(".select2", formId).val("").trigger("change");
		}

	}
}();

// 私有form表单对象
function mform(option) {

	this.initParam = option;
	// 对表单进验证
	if (this.initParam.isvalid) {
		this.initParam.validform = MValidform
				.validform(this.initParam.formSelecter);
	}
	if (parent.layer.getFrameIndex) {
		this.initParam.layerIndex = parent.layer.getFrameIndex(window.name);
	}

	// 提交表单数据
	this.submit = function() {

		var _this = this;

		// 验证表单
		if (this.initParam.isvalid) {
			if (!this.initParam.validform.check()) {
				return false;
			}
		}
		// 调用beforesubmit
		if (this.initParam.beforeSubmit) {
			if (this.initParam.beforeSubmit() == false) {
				return;
			}
		}

		// 提交表单数据
		$.AjaxProxy(
				{
					p : $.extend(MForm.toJson(_this.initParam.formSelecter),
							_this.initParam.extendParam)

				}).invoke(
				_this.initParam.invokeUrl,
				function(loj) {
					if (layer.alert) {
						// 执行成功弹出提示层
						layer.msg(loj.getValue("info"), {
							icon : 1,
							time : 1000
						}, function(inindex) {
							// 关闭提示层
							layer.close(inindex);
							// 自定义刷新回调
							if (_this.initParam.afterSubmit) {
								_this.initParam.afterSubmit();
							}
							// 关闭iframe层
							if (_this.initParam.isAutoClose
									&& _this.initParam.layerIndex) {
								parent.layer.close(_this.initParam.layerIndex);
							}
						});
						return;
					} else {
						// 自定义刷新回调
						if (_this.initParam.afterSubmit) {
							_this.initParam.afterSubmit(loj);
						}
					}
				});
	};
	return this;
}