/**
 * 封装bootstrap-table
 * 
 * 1、对bootstrap-table的属性进行一些默认的配置， 2、扩展了对搜索按钮、go按钮 3、处理搜索表单的值添加到bootstrap-table
 * 的默的queryParams中 3.提供一些与表单相关的操作
 * @author 姜友瑶
 * @date 2016-06-24
 */


//公用表格对象
var MGrid = function() {

	"use strict";

	return {

		/** 初始化表格 */
		initGrid : function(_initParam) {
			/** 表格默认属性 */
			var option = {
				tableSelecter : "#mgrid",// 表格选择器 请保持唯一
				serchFormSelecter : "#serchform",// 搜索表单选择器
				searchListSelecter:".search-list",
				$table : null,// 当前表单的jQuery对象
				pagination : true,// 显示分页栏
				sidePagination : 'server',// 服务器分页
				dataSearch : true,// 显示搜索框
				selectItemName : "keys", // 设置checkbox的值
				pageList : [ 10, 20, 50, 100, 500 ],// 设置分页显示条数下拉控件的值
				showColumns : true,// 显示选择显示列按钮
				showRefresh : true,// 显示刷新按钮
				showToggle : true,// 显示卡片切换
				clickToSelect : true,// 点击行时选中
				delUrl : "",// 数据删除地址
				queryParams : function(params) {
					return $.extend(params, MForm
							.toJson(this.serchFormSelecter));
				},// 添加搜索值
				method : "post",// 数据提交方式
				contentType : "application/x-www-form-urlencoded",
			};
			option = $.extend(option, _initParam);
			var mygrid = new grid(option);
			mygrid.init();
			return mygrid;
		},

		/** 表格序号调用 */
		indexfn : function(value, row, index) {
			return index + 1;
		},

		/** 获取url */
		getUrl : function(value, row, index) {
			return "<a href=" + value + " target='blanck' >" + value + "</a>";
		},

		getImage : function(value, row, index) {
			if(!value&&value==''){
				return "";
			}
			return "<img width='150px;' height='120'  src='" + value + "' />";
		},
		/** 获取时间 */
		getTime : function(value, row, index) {
			var temp = "";
			try {
				temp = value
				var date = new Date(temp);
				if (!temp) {
					return "";
				} else {
					return date.getFullYear() + "-" + setFomartZero((date.getMonth() + 1))
					+ "-" + setFomartZero(date.getDate()) + " " + setFomartZero(date.getHours())
					+ ":" + setFomartZero(date.getMinutes());
				}
			} catch (e) {
				console.err('MGrid getTime执行失败');
			}
			return temp;
		},
		/** 获取时间 */
		getTimeDD : function(value, row, index) {
			var temp = "";
			try {
				temp = value
				var date = new Date(temp);
				if (!temp) {
					return "";
				} else {
					return date.getFullYear() + "-" + setFomartZero((date.getMonth() + 1))
					+ "-" + setFomartZero(date.getDate()) ;
				}
			} catch (e) {
				console.err('MGrid getTime执行失败');
			}
			return temp;
		},
		/** 获取时间 */
		getTimeHHSS : function(value, row, index) {
			var temp = "";
			try {
				temp = value
				var date = new Date(temp);
				if (!temp) {
					return "";
				} else {
					return  setFomartZero(date.getHours())
					+ ":" + setFomartZero(date.getMinutes());
				}
			} catch (e) {
				console.err('MGrid getTime执行失败');
			}
			return temp;
		},
		goPage : function(node, max, tableId) {
			var $node=$(node);
			var page = $(node).prev("input").val();
			$("#" + tableId).bootstrapTable('selectPage', page);
			var pages= $(node).next(".pagination").find(".page-number");
			//改变分页样式，但是没效果好奇怪
			pages.each(function(){
				var li = $(this);
				li.attr("class","page-number")
				if(li.find("a").html()==page){
					li.attr("class","page-number active")
				} 
			});
			
		}
	}
}();

function setFomartZero(date){
	if(date<10){
		return "0"+date;
	}
	return date;
}
//私有对象
function grid(option) {

	this.initParam = option;
	this.initParam.$table = $(this.initParam.tableSelecter);
	this.initParam.$table.bootstrapTable(this.initParam);

	/** 初始化表格 */
	this.init=function(){
		//处理搜索组件
		_table=this;
		$(_table.initParam.searchListSelecter).find("li").click(function(){
			$searlist=$(_table.initParam.searchListSelecter);
			$input=$("#"+$(_table.initParam.searchListSelecter).data("for"));
			$button=$(_table.initParam.searchListSelecter).find("button");
			$input.attr("name",$(this).data("field"));
			$button.html(	$(this).find("a").html()+' <span class="caret"></span>');
		});
	};
	
	
	
	/** 搜索按钮方法 */
	this.serchData = function() {
		this.initParam.$table.bootstrapTable('refresh', {
			silent : false
		});
	};

	/** 跳转页面 */
	this.goPage = function(node, max, tableId) {
		var page = 1;
		var pageNow = $(node).prev().val();
		if (pageNow > max) {
			page = max;
		} else {
			page = pageNow <= 0 ? "1" : pageNow;
		}
		this.initParam.$table.bootstrapTable('selectPage', +page);
	};

	/**
	 * 删除表格单条数据
	 */
	this.delItem = function(id) {
		var _this = this;
		MTools.handleItem(this.initParam.delUrl + "?keys=" + id, "确认要删除这条数据吗？",
				function() {
					_this.serchData();
				});
	};

	/**
	 * 批量删除表格单条数据
	 */
	this.delItems = function(idFiledName) {
		var count = this.initParam.$table.bootstrapTable('getSelections').length;
		if (count < 1) {
			layer.msg("请选择您要删除的数据", {
				icon : 5
			});
			return false;
		}
		var _this = this;
		MTools.handleItem(this.initParam.delUrl + "?keys="
				+ this.getSelectItemsIds(idFiledName), "确认要删除这" + count + "条数据吗？",
				function() {
					_this.serchData();
				});
	};

	/**
	 * 获取被选中项的id 转换为用逗号分开的id返回
	 * 
	 * @param idFiledName
	 *            id 字段的字段名称
	 */
	this.getSelectItemsIds = function(idFiledName) {
		if (!idFiledName) {
			idFiledName = "id";
		}
		var items = this.initParam.$table.bootstrapTable('getSelections');
		var keys = "";
		for (var i = 0; i < items.length; i++) {
			if (i == items.length - 1) {
				keys += items[i][idFiledName];
			} else {
				keys += items[i][idFiledName] + ",";
			}
		}
		return keys;
	};
	
}
