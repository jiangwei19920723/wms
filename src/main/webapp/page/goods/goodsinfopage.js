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
    	var goodsName = data.field.goodsName;
    	var isEnpty = synAjax("GET", '/v0.1/goods/select/'+goodsName,null, '查询失败!');
    	if (isEnpty == true) {
    		layer.msg("货物名称已存在，添加失败！");
    		return false;
		}
        synAjax("POST", '/v0.1/goods/insert', data.field, '添加失败!');
        layer.msg("添加成功！");
        return false;
    });

})
