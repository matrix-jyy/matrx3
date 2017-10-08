/**
 * ajax请求的代理类 1、系统在一般情况都使用本类进行ajax请求不在使用原生的或者jQuery的ajax 原因：
 * 1、本类对异常信息，错误信息进行了信息提示处理 2、本类与服务器端的AjaxResult.java 对应，接收的数据格式是AjaxResult.java
 * 的json格式数据
 * 
 * author ：姜友瑶; 2016-06-02
 */
(function($) {
	$.fn.extend({
		invoke : function(url, callback) {
			$this = this;
			// 是否开启遮罩
			if (this.attr("c")) {
				layer.load(2, {
					time : 10 * 1000,
					shade : [ 0.4, '#aeaeae' ]
				});
			}
			$.ajax({
				type : "post",
				url : url,
				data : this.attr("data"),
				async : this.attr("a"),
				success : function(result) {
					// 全局异常处理器发出的错误信息
					if (result.status == "ok") {
						$this.attr("result", result);
						layer.closeAll('loading');
						if (callback != null) {
							callback($this);
						}
					} else if (result.status == "err") {
						layer.closeAll('loading');
						layer.msg(result.info, {
							icon : 2,time:1000
						});
						return null;
					} else if (result.status == "unkwonerr") {
						layer.closeAll('loading');
						layer.msg(result.info, {
							icon : 2
						});
						return null;
					}else {
						console.log(result)
						if(result=="loginTimeOut..."){
							layer.closeAll('loading');
							layer.msg("登陆超时请重新登陆！！", {
								icon : 2
							},function(){
								MTools.toPredirect(basePath+"/common/redirect/login");
							});
						}
						return null;
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					layer.closeAll('loading');
					layer.msg("请求遇到问题-错误编号：" + XMLHttpRequest.status, {
						icon : 2
					});
					
				},
			});
		},
		getRowCount : function() {
			if(this.attr("result").rows==null){
				return 0;
			}
			return this.attr("result").rows.length;
		},
		// * @param key列名
		getValue : function(key) {
			var temp = "";
			try {
				temp = eval("this.attr('result')." + key);
			} catch (e) {
				console.error('getValueg执行失败');
				console.log(e);
			}
			return temp;
		},
		/**
		 * @param mapName游标名称
		 * @param index
		 *            角标
		 * @param key列名
		 * @returns
		 */
		getString : function(index, key) {
			var temp = "";
			try {
				temp = eval("this.attr('result').rows[" + index + "]." + key);
				if (!temp) {
					temp = "";
				}
			} catch (e) {
				console.error('getString执行失败');
				console.log(e);
			}
			return temp;
		},
		/**
		 * 获取一个Img 标签
		 * 
		 * @param index
		 *            角标
		 * @param key列名
		 * @param w
		 *            宽
		 * @param h
		 *            高
		 * @returns
		 */
		getImg : function(index, key, w, h) {
			if (!w) {
				w = 120;
			}
			if (!h) {
				h = 100;
			}
			var temp = "";
			try {
				temp = eval("this.attr('result').rows[" + index + "]."
						+ key);
				if (!temp) {
					return "";
				}
			} catch (e) {
				console.error('getImg执行失败');
				console.log(e);
			}
			return "<img height='" + h + "' width='" + w + "' src=" + temp
					+ "  >";
		},
		/**
		 * 
		 * 根据传入的键值对 对象选取匹配结构的值返回
		 * 
		 * @param index
		 *            角标
		 * @param key
		 *            列名
		 * @param params
		 *            需要被判断的键值对
		 * 
		 * @returns
		 */
		getSwitch : function(index, key, paramsStr) {
			var params = eval("(" + paramsStr + ")");
			var temp = "";
			try {
				temp = eval("this.attr('result').rows[" + index + "]."+key);
				// 没有获取到值
				if (!temp) {
					// 是否存在默认值
					if (params.empty) {
						return params.empty;
					} else {
						return "";
					}
				} else {
					return params[temp];
				}
			} catch (e) {
				console.error('getSwitch执行失败');
				console.log(e);
			}
			return temp;
		},
		getDate : function(index, key) {
			var temp = "";
			try {
				temp = eval("this.attr('result').rows[" + index + "]['" + key
						+ "']");
				var date = new Date(temp);
				if (!temp) {
					return "";
				} else {
					return date.getFullYear() + "-" + (date.getMonth() + 1)
							+ "-" + date.getDate() + " " + date.getHours()
							+ ":" + date.getMinutes();
				}
			} catch (e) {
				console.error('getDate执行失败');
				console.log(e);
			}
			return temp;
		}
	});
	$.extend({
		/** 初始化过程请求参数 */
		AjaxProxy : function(option) {
			// 初始化参数
			var initParam = {
				data : {},
				a : true,
				c : true
			};
			if (option == undefined) {
				var option = {};
			}
			if (option.p) {
				initParam.data = $.extend(initParam.data,
						option.p);
			}
			initParam = $.extend(initParam, option);
			delete initParam.p;
			return $(initParam);
		}
	});

})(jQuery);
