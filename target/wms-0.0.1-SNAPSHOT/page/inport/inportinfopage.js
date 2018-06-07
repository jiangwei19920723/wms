layui.config({
    base : "js/"
}).use(['form','layer','jquery','table'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        $ = layui.jquery,
        table=layui.table;

    /*发票账户信息异步提交*/
    form.on('submit(infoSumbit)', function(data){
        synAjax("POST", '/v0.1/inport/insert', data.field, '添加失败!');
        layer.msg("添加成功！");
        return false;
    });
    var sel = $("#goodsName");
    var res =  synAjax("GET", '/v0.1/goods/list', null, '查询失败!');
    for (var i = 0; i < res.length; i++) {
		$('<option value="'+res[i].goodsName+'">'+res[i].goodsName+'</option>').appendTo(sel);
	}
    form.render();
    form.on('select(goodsName)', function(data){
    	  for (var i = 0; i < res.length; i++) {
    		  if (res[i].goodsName == sel.val()) {
    			  $("#goodsCode").val(res[i].goodsCode);
    			  $("#goodsUnit").val(res[i].goodsUnit);
    		  }
    	  }
    	  if (sel.val() == "") {
    		  $("#goodsCode").val("");
			  $("#goodsUnit").val("");
    	  }
    });
})
