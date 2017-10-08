/**
 * 封装了一下validform，提供了更丰富的datatype
 * 
 * @author 姜友瑶 2016-06-24
 */
;

// 正两位浮点数和0
var regx = /^\d+\.?\d{0,2}$/;
// 正负整数
var zfzs = /^-?\d+$/;
var idCardReg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
// 手机号或者座机号
var phoneORmobile = /(\d{3}-\d{8}|\d{4}-\d{7})|(^1[358]\d{9}$)/;
var MValidform = function() {

	"use strict";// 严格模式
	return {
		// 验证一个form表格
		validform : function(formSelecter) {

			var exForm = $(formSelecter).Validform(
					{

						tiptype : 3,
						// 扩展自定义dattype
						datatype : {

							// 正浮点2位小数
							"price" : function(gets, obj, curform, regxp) {
								if (obj.val() == "")
									return false;
								if (!isNaN(obj.val())) {
									return regx.test(obj.val())
								} else {
									return false;
								}
							},

							// 正负int都可以
							"zfn" : function(gets, obj, curform, regxp) {

								if (obj.val() == "")
									return false;
								if (!isNaN(obj.val())) {
									return zfzs.test(obj.val());
								} else {
									return false;
								}
							},

							// 省份证格式验证
							"idCard" : function(gets, obj, curform, regxp) {

								if (obj.val() == "") {
									return false;
								}
								var result = idCardReg.test(obj.val());
								if (result) {
									return true;
								} else {
									return false;
								}
							},
							"pOrm" : function(gets, obj, curform, regxp) {

								if (obj.val() == "") {
									return false;
								}
								var result = phoneORmobile.test(obj.val());
								if (result) {
									return true;
								} else {
									return false;
								}

							},
							// 百分比
							"hl" : function(gets, obj, curform, regxp) {
								if (obj.val() == "")
									return false;
								if (!isNaN(obj.val())) {
									var isHl = (parseFloat(obj.val())) >= 0
											&& (parseFloat(obj.val())) <= 100;
									return regx.test(obj.val()) && isHl;
								} else {
									return false;
								}
							},
						},
					});
			return exForm;
		},

	}
}();
