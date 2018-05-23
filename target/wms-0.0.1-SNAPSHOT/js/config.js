var hostpath = '';
function synAjax(requestType, url, data, msg) {
	var r = undefined;
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
	return r;
}

function synAjaxJson(requestType, url, data, msg) {
	var r = undefined;
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
	return r;
}
