/**
 * 公共调用js
 */
$(function() {
	
	loading = function(){
		var d = dialog({
			width: 40,
			height: 40
		});

		d.showModal();
	};
	
	//select2 bootstrap样式
	$(".select2").select2({
		theme: "bootstrap",
		language: "zh-CN",
        placeholder: "请选择"
	});
	
	// 分页
	page = function(n,s) {
		/*alert("n:"+n);
		alert("s:"+s);*/
		$("#pageNo").val(n);
		$("#searchForm").submit();
	};

	// 全选/反选(如果有了iCheck该方法就会失效)
	checkAll = function(chkAll, name) {
		$("input[name='" + name + "']").prop("checked", chkAll.checked);

	};
	
	// 获取选中checkbox的值
	checkValue = function(name) {
		var str = "";
		$('input[type="checkbox"][name=' + name + ']').each(function() {
			if ($(this).prop('checked')) {
				str += $(this).val() + ",";
			}
		});
		if (str != "")
			str = str.substring(0, str.length - 1);
		return str;
	};

	// 编辑
	update = function(url) {
		var ids = checkValue("checkbox");
		if (ids == "") {
			var d = dialog({
				title : '提示',
				width : 200,
				content : '请选择要编辑的记录',
				okValue : '确定',
				ok : function() {
					this.close();
					return false;
				}
			});
			d.showModal();
			return false;
		}
		if (ids.split(",").length > 1) {
			var d = dialog({
				title : '提示',
				width : 200,
				content : '请选择一笔记录',
				okValue : '确定',
				ok : function() {
					this.close();
					return false;
				}
			});
			d.showModal();
			return false;
		}
//		window.location = url + "?id=" + ids;
		window.location = url + "/" + ids;
	};

	// 删除全部
	deleteAll = function(message, url) {
		var ids = checkValue("checkbox");
		if (ids == "") {
			var d = dialog({
				title : '提示',
				width : 200,
				content : '请选择要删除的记录',
				okValue : '确定',
				ok : function() {
					this.close();
					return false;
				}
			});
			d.showModal();
			return false;
		}
		confirmx(message, url + "/" + ids);
	};

	// 确认删除
	confirmx = function(message, url) {
		var d = dialog({
			title : '提示',
			width : 300,
			content : message,
			okValue : '确定',
			ok : function() {
				this.title('提交中…');
				window.location = url;
				return false;
			},
			cancelValue : '取消',
			cancel : function() {
			}
		});
		d.showModal();
		/*
		 * $.dialog.confirm(message, function(){
		 * $.dialog.tips(message,1,'loading.gif'); location = url; });
		 */
		return false;
	};
	
	//弹出层
	openWindow=function(url, title,width,height) {
		var index = layer.open({
			type : 2,
			title : title,
			area : [ width, height],
			fix : false, //不固定
			maxmin : true,
			content : url
		});
	}
	
	//关闭弹出层
	closeWindow=function () {
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		parent.layer.close(index);
	}
	
	//返回list页面
	goback=function (url) {
		window.location = url;
	}

    /***
	 * 下拉框异步通用方法
     * @param id
     * @param url
     */
	select=function(id,url,initvalue){
		console.log(id+"--"+url+"--"+initvalue);
        var options = [];
        $.ajax({
            url:url,
            dataType:"json",
            type: "GET",
            async:true,
            success: function (res) {
                for(var i= 0, len=res.length;i<len;i++){
                    var option = {"id":res[i]["code"], "text":res[i]["name"]};
                    options.push(option);
                    $("#"+id+"").select2({
                        data: options
                    });
                }
                //设置默认值
                if(initvalue!=""){
                    $("#"+id+"").val(initvalue).trigger('change');
                }else{

                }
			},
            error:function(res){

            }
		});
	}
	
	selectOne=function(id,url,initvalue){
        var options = [];
        $.ajax({
            url:url,
            dataType:"json",
            type: "GET",
            async:true,
            success: function (res) {
                for(var i= 0, len=res.length;i<len;i++){
                    var option = {"id":res[i]["code"], "text":res[i]["roomId"]};
                    options.push(option);
                    $("#"+id+"").select2({
                        data: options
                    });
				}
              //设置默认值
                if(initvalue!=""){
                    $("#"+id+"").val(initvalue).trigger('change');
                }else{

                }
			},
            error:function(res){
            	
            }
		});
	}


});
