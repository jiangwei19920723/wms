layui.config({
    base : "js/"
}).use(['form','layer','jquery','table','laydate'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        $ = layui.jquery,
        table=layui.table,
        laydate=layui.laydate;


    /*日期选择插件*/
    laydate.render({
        elem:'#start_time'
    })
    laydate.render({
        elem:'#end_time'
    })
    /*
     * 查询条件
     */
    function selectCondition(){
		$(".over_time_2").before('<div class="layui-col-xs6 over_time_1"><div class="grid-demo grid-demo-bg1"><div class="layui-inline"><input type="text" class="layui-input" id="start_time"></div>至<div class="layui-inline"><input type="text" class="layui-input" id="end_time"></div></div></div></div>');
    }
    /*数据表格初始化*/
    function getDataCallBack() {
    	var res = synAjax("GET", '/v0.1/sellreturn/list', null, '提交失败!');
    	tab(res);
        return false;
    }
    /**
     * 表格
     * @param res
     * @returns
     */
    function tab(res){
    	table.render({
            elem:'#invoice_datagrid_all'
            ,height:315
            ,initSort: {
                field: 'createTime' //排序字段，对应 cols 设定的各字段名
                    ,type: 'desc' //排序方式  asc: 升序、desc: 降序、null: 默认排序
             }
            ,page: true //开启分页
            ,cols: [[ //表头
            	{field: 'id', title: '退货单号',width:150}
                ,{field: 'goodsName', title: '货物名称'}
                ,{field: 'goodsCode', title: '货物单号'}
                ,{field: 'createTime', title: '创建时间',width:140,sort: true,templet:'<div>{{ layui.laytpl.toDateString(d.createTime,"yyyy-MM-dd")}}</div>'}
                ,{field: 'sellReturnNumber', title: '数量'}
                ,{field: 'goodsUnit', title: '单位'}
                ,{field: 'sellReturnPrice', title: '退货总价（元）',width:140}
                ,{field: 'createBy', title: '退货单创建人'}
                ,{field: 'sellReturnBy', title: '买家姓名'}
                ,{field: 'phone', title: '买家电话',width:140}
                ,{field:'doinvoice',title:'操作',toolbar:'#btnBar'}
            ]]
            ,data:res
         });
    }
    /**
     * 时间戳处理方法
     * @param d
     * @param format
     * @returns {string}
     */
    layui.laytpl.toDateString = function(d, format){
        var date = new Date(d || new Date())
            ,ymd = [
            this.digit(date.getFullYear(), 4)
            ,this.digit(date.getMonth() + 1)
            ,this.digit(date.getDate())
        ]
            ,hms = [
            this.digit(date.getHours())
            ,this.digit(date.getMinutes())
            ,this.digit(date.getSeconds())
        ];

        format = format || 'yyyy-MM-dd HH:mm:ss';

        return format.replace(/yyyy/g, ymd[0])
            .replace(/MM/g, ymd[1])
            .replace(/dd/g, ymd[2])
            .replace(/HH/g, hms[0])
            .replace(/mm/g, hms[1])
            .replace(/ss/g, hms[2]);
    };

    /**
     * 数字前置补0
     * @param num
     * @param length
     * @param end
     * @returns {*}
     */
    layui.laytpl.digit = function(num, length, end){
        var str = '';
        num = String(num);
        length = length || 2;
        for(var i = num.length; i < length; i++){
            str += '0';
        }
        return num < Math.pow(10, length) ? str + (num|0) : num;
    };
    //初始化查询
    selectCondition();
    //初始化数据方法
    getDataCallBack();
    //查询条件改变时
    $("#select").change(function(){
    	$(".over_time_1").remove();
    	var value = $("#select").val();
    	if (value == 0) {
    		$(".over_time_2").before('<div class="layui-col-xs6 over_time_1"><div class="grid-demo grid-demo-bg1"><div class="layui-inline"><input type="text" class="layui-input" id="start_time"></div>至<div class="layui-inline"><input type="text" class="layui-input" id="end_time"></div></div></div></div>');
			laydate.render({
		        elem:'#start_time'
		    })
		    laydate.render({
		        elem:'#end_time'
		    })
		}else if (value == 1) {
			$(".over_time_2").before('<div class="layui-col-xs6 over_time_1"><div class="grid-demo grid-demo-bg1"><div class="layui-inline"><input type="text" class="layui-input"style="width:332px;" id ="code"></div></div></div></div>');
		}else if (value == 2) {
			$(".over_time_2").before('<div class="layui-col-xs6 over_time_1"><div class="grid-demo grid-demo-bg1"><div class="layui-inline"><input type="text" class="layui-input"style="width:332px;" id ="name"></div></div></div></div>');			
		}
    });
    /**
     * 条件查询结果
     */
    $(".btn").click(function(){
    	var value = $("#select").val();
    	if (value ==0) {
			var start_time = $("#start_time").val();
			var end_time = $("#end_time").val();
			var res = synAjaxJson("GET", '/v0.1/sellreturn/select', 'startTime='+start_time+'&endTime='+end_time, "查询失败！");
			tab(res);
		}else if (value == 1) {
			var code = $("#code").val();
			var res = synAjaxJson("GET", '/v0.1/sellreturn/select', 'goodsCode='+code, "查询失败！");
			tab(res);
		}else if (value == 2) {
			var name = $("#name").val();
			var res = synAjaxJson("GET", '/v0.1/sellreturn/select', 'goodsName='+name, "查询失败！");
			tab(res);
		}
    });
    /**
     * 删除进货表
     */
    table.on('tool(doEven)', function(obj){
        var data = obj.data;
        if(obj.event === 'del'){
          layer.confirm('是否删除', function(index){
        	  synAjaxJson("DELETE", '/v0.1/sellreturn/delete/'+data.id, null, "库存数量已卖出，数量不够，删除失败！");
              obj.del();
              layer.close(index);
          });
        }
      });
})