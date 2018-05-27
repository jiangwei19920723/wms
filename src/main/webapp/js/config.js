var hostpath = '';
var b =true;
function synAjax(requestType, url, data, msg) {
	var r = undefined;
	if (b ==true) {
		b =false;
		$.ajax({
			type : requestType,
			dateType : "json",
			contentType : "application/json",
			url : hostpath + url,
			data : JSON.stringify(data),
			async : false,
			success : function(res) {
				r = res;
			},
			error : function(res) {
				layer.msg(msg);
			}
		});
		b=true;
	}
	return r;
}

function synAjaxJson(requestType, url, data, msg) {
	var r = undefined;
	if (b==true) {
		b=false;
		$.ajax({
			type : requestType,
			dateType : "json",
			contentType : "application/json",
			url : hostpath + url,
			data : data,
			async : false,
			success : function(res) {
				r = res;
			},
			error : function(res) {
				layer.msg(msg);
			}
		});
		b=true;
	}
	return r;
}
