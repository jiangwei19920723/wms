layui.config({
    base : "js/"
}).use(['form','layer','jquery','table','laydate'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        $ = layui.jquery,
        table=layui.table,
        laydate=layui.laydate;


    /*数据表格初始化*/
    function getDataCallBack() {
    	var res = synAjax("GET", '/v0.1/goods/list', null, '查询失败!');
    	for (var i = 0; i < res.length; i++) {
			res[i].sellNumber=0;
		}
    	tab(res);
        return false;
    }
    function tab(res){
    	table.render({
            elem:'#invoice_datagrid_all'
            ,height:600
            ,page: true //开启分页
            ,cols: [[ //表头
            	{type:'checkbox'}
                ,{field: 'goodsName', title: '货物名称',width:300}
                ,{field: 'goodsCode', title: '货物编号'}
                ,{field: 'goodsUnit', title: '单位'}
                ,{field: 'goodsType', title: '类型'}
                ,{field: 'goodsFactory', title: '所属工厂'}
                ,{field: 'goodsNumber', title: '库存数量'}
                ,{field:'sellNumber', title: '销售数量',edit: 'text'}
            ]]
            ,data:res
         });
    }
    //初始化数据方法
    getDataCallBack();
    var data =new Array();
    table.on('edit(doEven)', function(obj){
    	if (obj.value>obj.data.goodsNumber) {
    		layer.msg("销售数量不能大于库存数量！");
		}else {
			for (var i = 0; i < data.length; i++) {
				if (data[i].goodsName ==obj.data.goodsName) {
					data.splice(i,1,obj.data);
				}
			}
		}
    });
    table.on('checkbox(doEven)', function(obj){
    	if (obj.checked == true & obj.type == 'one') {
    		data.push(obj.data);		
		}else if(obj.checked == false & obj.type == 'one'){
			for (var i = 0; i < data.length; i++) {
				if (data[i].goodsName == obj.data.goodsName) {
					data.splice(i,1);
				}
			}
		}
    });
    callbackdata = function (){
    	return data;
    }
})