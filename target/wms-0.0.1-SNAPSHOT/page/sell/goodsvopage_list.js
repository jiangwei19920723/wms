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
    	var res = synAjax("GET", '/v0.1/sell/list/'+sellRiseId, null, '查询失败!');
    	tab(res);
        return false;
    }
    function tab(res){
    	table.render({
            elem:'#invoice_datagrid_all'
            ,height:315
            ,page: true //开启分页
            ,cols: [[ //表头
                {field: 'goodsName', title: '货物名称'}
                ,{field: 'goodsCode', title: '货物编号'}
                ,{field: 'goodsUnit', title: '单位'}
                ,{field: 'goodsType', title: '类型'}
                ,{field: 'goodsFactory', title: '所属工厂'}
                ,{field: 'sellNumber', title: '销售数量'}
                ,{field: 'goodsPrice', title: '销售价格（元）'}
            ]]
            ,data:res
         });
    }
    //初始化数据方法
    getDataCallBack();
})