layui.config({
	base : "js/"
}).use(['form','layer'],function(){
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		$ = layui.jquery;
	//video背景
	$(window).resize(function(){
		if($(".video-player").width() > $(window).width()){
			$(".video-player").css({"height":$(window).height(),"width":"auto","left":0});
		}else{
			$(".video-player").css({"width":$(window).width(),"height":"auto","left":-($(".video-player").width()-$(window).width())/2});
		}
	}).resize();
	
	//登录按钮事件
	form.on("submit(login)",function(data){
		var r = synAjax("POST","/v0.1/user/login",data.field,"用户名或密码错误!");
		if (r == undefined) {
			return false;			
		}else {
			try {
                setSessionCookie('userName', r.userName);
                setSessionCookie('loginName', r.loginName);
                setSessionCookie('gender',r.gender);
                setSessionCookie('phone',r.phone);
                setSessionCookie('address',r.address);
            }catch (e){
                console.log(e);
            }
			window.location.href = "/index.html";
			return false;			
		}
	})
})
