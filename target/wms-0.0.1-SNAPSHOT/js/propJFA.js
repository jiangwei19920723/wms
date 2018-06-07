String.prototype.trim = function(){ 
    return this.replace(/(^\s*)|(\s*$)/g, ""); 
}
String.prototype.replaceAll = function(searchValue, replaceValue){
    if(searchValue instanceof RegExp){
        var regex = new RegExp(searchValue.source);
        regex.global = true;
        return this.replace(regex, replaceValue);
    }
    var array = this.split(searchValue);
    if(array.length==0){
        return this;
    }
    var s = array[0];
    for(var i=1;i<array.length;i++){
        s += replaceValue + array[i];
    }
    return s;
}
String.prototype.startsWith = function(prefix){
    return this.indexOf(prefix) == 0;
}
String.prototype.endsWith = function(suffix){
    return suffix == this.substring(this.length - suffix.length);
}
Array.prototype.contains = function(searchValue){
    for(var i=0;i<this.length;i++){
        if(this[i]==searchValue){
            return true;
        }
    }
    return false;
}
Array.prototype.toSet = function(){
    var array = new Array();
    for(var i=0;i<this.length;i++){
        if(!array.contains(this[i])){
            array.push(this[i]);
        }
    }
    return array;
}
/**
 * alvis_wh propwh - v1.0.0
 * 
 * @author weihan
 */
/**
 * 获取被选单选框对象，没有指定名称的单选框被选择，则返回undefined
 * 
 * @param name
 *            单选框名称
 */
function getRadio(name) {
    var radioObjects = document.getElementsByName(name);
    if (radioObjects == null || radioObjects == undefined) {
        return undefined;
    }
    if (radioObjects.length == null || radioObjects.length == undefined) {
        if (radioObjects.checked) {
            return radioObjects;
        }
    }
    for (var i = 0; i < radioObjects.length; i++) {
        if (radioObjects[i].checked) {
            return radioObjects[i];
        }
    }
    return undefined;
}
/**
 * 获取被选单选框的值，未选择时返回undefined
 * 
 * @param name
 *            单选框名称
 */
function getRadioValue(name) {
    var radio = getRadio(name);
    return radio == undefined ? undefined : radio.value;
}
/**
 * 获取指定名称单选框中取值为value的单选框对象，找不到该对象则返回undefined
 * 
 * @param name
 *            单选框名称
 * @param value
 *            单选框值
 */
function getRadioByValue(name, value) {
    var radioObjects = document.getElementsByName(name);
    if (radioObjects == null || radioObjects == undefined) {
        return undefined;
    }
    if (radioObjects.length == null || radioObjects.length == undefined) {
        if (radioObjects.value == value) {
            return radioObjects;
        }
    }
    for (var i = 0; i < radioObjects.length; i++) {
        if (radioObjects[i].value == value) {
            return radioObjects[i];
        }
    }
    return undefined;
}

function getCheckBoxValue(name){
	var chkItemArray = document.getElementsByName(name); 
	var result = []; 
	if(chkItemArray!=null&&chkItemArray!=undefined){
		for(var i=0;i<chkItemArray.length;i++){
			if(chkItemArray[i].checked){
				result.push(chkItemArray[i].value); 
			}
		}
	}
	return result; 
}
/**
 * 校验指定email是否为合法的email格式
 * 
 * @param email
 *            email地址
 */
function validEmail(email){
	var format = /^\s*([A-Za-z0-9_]+[_\-\.\w]*@([_\-\w]+\.)+[_\-\w]{2,3})\s*$/;
    return format.test(email);
}
/**
 * 校验指定手机号码是否为合法的手机号码格式
 * 
 * @param mobilePhone
 *            手机号码
 */
function validMobilePhone(mobilePhone){
    var format = /^1\d{10}$/;
    return format.test(mobilePhone);
}
function validFixedPhone(fixedPhone){
    var format = /^0\d{2,3}-[1-9]\d{6,7}$/;
    return format.test(fixedPhone);
}
function validPhone(phone){
    var format = /^(1\d{10})|(0\d{2,3}-[1-9]\d{6,7})$/;
    return format.test(phone);
}

Date.prototype.format = function(format){   
   var o = {
     "M+" : this.getMonth()+1, // month
     "d+" : this.getDate(),    // day
     "h+" : this.getHours(),   // hour
     "m+" : this.getMinutes(), // minute
     "s+" : this.getSeconds(), // second
     "q+" : Math.floor((this.getMonth()+3)/3), // quarter
     "S" : this.getMilliseconds() // millisecond
   }   
   if(/(y+)/.test(format)) format=format.replace(RegExp.$1,   
     (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
   for(var k in o)if(new RegExp("("+ k +")").test(format))   
     format = format.replace(RegExp.$1,   
       RegExp.$1.length==1 ? o[k] :    
         ("00"+ o[k]).substr((""+ o[k]).length));   
   return format;   
}

function validSymbol(symbol){
    var format = /^[2A6E]*[809D]+[25D4]?[1AB5]*$/;
    return format.test(email);
}
/**
 * 校验指定IP地址是否为合法的IP地址格式
 * 
 * @param ip
 *            IP地址
 */
function validIp(ip){
    var format=/^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5]|\*)$/;
    return format.test(ip);
}
function setCookie(name, value, seconds) {
    var cookie = name + "=" + escape(value);
    if (seconds >= 0) {
        var date = new Date();
        date.setSeconds(date.getSeconds() + seconds);
        cookie += ";expires=" + date.toUTCString()+";path=/;";
    }
    document.cookie = cookie+";path=/;";
}
function setSessionCookie(name, value) {
    setCookie(name, value, -1);
}
function getCookie(name) {
    if (document.cookie) {
        var index = document.cookie.indexOf(name + "=");
        if (index != -1 && (index == 0 || document.cookie.substring(index - 2, index - 1) == ";")) {
            index = index + name.length + 1;
            var index2 = document.cookie.indexOf(";", index);
            if (index2 == -1) {
                index2 = document.cookie.length;
            }
            return unescape(document.cookie.substring(index, index2));
        }
    }
    return "";
}
function removeCookie(name) {
    setCookie(name, "", 0);
}
function areCookiesSupported() {
    setCookie("test", "test", 1);
    var res = getCookie("test") == "test";
    removeCookie("test");
    return res;
}
function getPlatform() {
    return navigator.platform == "Win32" ? "win" : navigator.platform == "WinCE" ? "wince" : /linux/i.test(navigator.platform) ? "linux" : /mac/i.test(navigator.platform) ? "mac" : "win";
}
function getBrowserName() {
    if (navigator.userAgent.indexOf("Opera") >= 0) {
        return "Opera";
    } else  if (navigator.userAgent.indexOf("Firefox") >= 0) {
        return "Firefox";
    } else if (navigator.userAgent.indexOf("MSIE") >= 0) {
        return "IE";
    } else if (navigator.userAgent.indexOf("Chrome") >= 0) {
        return "Chrome";
    } else if (navigator.userAgent.indexOf("Safari") >= 0) {
        return "Safari";
    }
    return "";
}
function isDotNetSupported(version) {
    function compareVersions(vers1, vers2) {
        var i = 0, k = 0, m = 0;
        do {
            k = i < vers1.length ? Number(vers1[i]) : 0;
            m = i < vers2.length ? Number(vers2[i]) : 0;
            ++i;
        } while ((i < vers1.length || i < vers2.length) && k == m);
        if (k < m) {
            return -1;
        }
        if (k > m) {
            return 1;
        }
        return 0;
    }
    var d = navigator.userAgent.match(/\.NET CLR [0-9.]+/g);
    if (d == null || d.length == 0) {
        return false;
    }
    if (version == null || version == undefined){
        return true;
    }
    var versions = version.split(".");
    if (versions.length == 0) {
        return false;
    }
    for (var i = 0; i < dotNetCtrs.length; ++i) {
        var f = d[i].match(/\.NET CLR ([0-9.]+)/);
        if (f != null && f.length == 2) {
            var clrVersions = f[1].split(".");
            if (clrVersions.length > 0 && compareVersions(versions, clrVersions) <= 0) {
                return true;
            }
        }
    }
    return false;
}
function isClickOnceAvailable() {
    return isDotNetSupported("2.0.0");
}

/**
 * 以AJAX方式在指定容器中以GET请求方式打开指定URL
 */
function ajax(containerId, url, params, successFunction,dataType,async){
    var id = null;
    if(containerId!=null){
    	id = containerId.indexOf("#")==0 ? containerId : "#"+containerId;
    }
    if(params==undefined || params==null){
        params = "";
    }
    if(id!=null){
		$(id).html("...");
	}
    if(async == undefined || async == null){
    	async = true;
    }
    var res = true;
    $.ajax({
        type: "POST",
        cache: false,
        async: async,
        url: url,
        data: params,
        dataType: dataType,
        success:function(msg){
	    	if((successFunction==null || successFunction==undefined) && id!=null){
				$(id).html(msg);
			}else if(typeof(successFunction)=="function"){
				successFunction(msg);
			}
        },
        error:function(){
            res = false;
        }
    });
    return res;
}

function formOnSubmit(formId){
	if(formId.indexOf("#")!=0){
    	formId = "#"+formId;
    }
    var formObj = $(formId);
    var onsubmit = formObj.attr("onsubmit");
    if(onsubmit!=null && onsubmit!=undefined && onsubmit!=""){
    	var func = new Function(onsubmit);
    	return func();
    }
    return true;
}

/**
 * 以AJAX方式在指定容器中以POST请求方式提交指定FORM
 */
function ajaxPost(containerId, formId, successFunction, dataType){
	if(!formOnSubmit(formId)){
		return;
	}
    if(formId.indexOf("#")!=0){
    	formId = "#"+formId;
    }
    var formObj = $(formId);
	var id = null;
    if(containerId!=null){
    	id = containerId.indexOf("#")==0 ? containerId : "#"+containerId;
    }
	if(id!=null){
		if($(formId+" :input[type='file']")!=undefined){
			if($(formId+" :input[type='file']").length==0){
				$(id).html("...");
			}else{
				$(id).hide();
			}
		}else{
			$(id).hide();
		}
	}
    var res = true;
	var options = {
	    target: id,
        cache: false,
	    url: formObj.attr("action"),
	    type: "POST",
	    dataType: dataType,
	    success: function(msg){
			if((successFunction==null || successFunction==undefined) && id!=null){
				$(id).html(msg);
				$(id).show();
			}else if(typeof(successFunction)=="function"){
				successFunction(msg);
			}
	    },
        error: function(){
            res = false;
        }
	};
	formObj.ajaxSubmit(options);
	return res;
}

function matchs(s,regex){
    var subs = new Array();
    regex.lastIndex = 0;
    var index = s.search(regex);
    if(index>=0){
        var sub = s.substring(index);
        var fullRegex = copyFullRegex(regex);
        while(!fullRegex.test(sub)){
            sub = sub.substring(0,sub.length-1);
        }
        subs.push(sub);

        var suffix = s.substring(index+sub.length);
        if(suffix.length>0){
            var subsubs = matchs(suffix,regex);
            for(var i=0;i<subsubs.length;i++){
                subs.push(subsubs[i]);
            }
        }
    }
    return subs;
}

function checkBoxAllClick(obj,name){
	$("input[type='checkbox'][name='"+name+"']").attr("checked",$(obj).attr("checked")=="checked");
}

function checkBoxToAllClick(obj,id){
	if(obj.checked == document.getElementById(id).checked)
		return;
	var checked = true;
	$("input[type='checkbox'][name='"+obj.name+"']").each(
		function(){
			if(!this.checked){
				checked = false;
				return;
			}
		}
	);
	$("#"+id).attr("checked",checked);
	
}
