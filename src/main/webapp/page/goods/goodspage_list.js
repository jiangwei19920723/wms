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
    	var res = synAjax("GET", '/v0.1/goods/list', null, '查询失败!');
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
                {field: 'goodsName', title: '货物名称'}
                ,{field: 'goodsCode', title: '货物编号'}
                ,{field: 'createTime', title: '创建时间',sort: true,templet:'<div>{{ layui.laytpl.toDateString(d.createTime,"yyyy-MM-dd")}}</div>'}
                ,{field: 'goodsNumber', title: '数量'}
                ,{field: 'goodsUnit', title: '单位'}
                ,{field: 'goodsType', title: '类型'}
                ,{field: 'goodsFactory', title: '所属工厂'}
                ,{field: 'goodsPrice', title: '单价（元）',event: 'setPrice',style:'cursor: pointer;'}
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
		}else if (value == 3) {
			$(".over_time_2").before('<div class="layui-col-xs6 over_time_1"><div class="grid-demo grid-demo-bg1"><div class="layui-inline"><input type="text" class="layui-input"style="width:332px;" id ="name"></div></div></div></div>');			
		}else if (value == 4) {
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
			var res = synAjaxJson("GET", '/v0.1/goods/select', 'startTime='+start_time+'&endTime='+end_time, "查询失败！");
			tab(res);
		}else if (value == 1) {
			var code = $("#code").val();
			var res = synAjaxJson("GET", '/v0.1/goods/select', 'goodsCode='+code, "查询失败！");
			tab(res);
		}else if (value == 2) {
			var name = $("#name").val();
			var res = synAjaxJson("GET", '/v0.1/goods/select', 'goodsName='+name, "查询失败！");
			tab(res);
		}else if (value == 3) {
			var name = $("#name").val();
			var res = synAjaxJson("GET", '/v0.1/goods/select', 'goodsFactory='+name, "查询失败！");
			tab(res);
		}else if (value == 4) {
			var name = $("#name").val();
			var res = synAjaxJson("GET", '/v0.1/goods/select', 'goodsType='+name, "查询失败！");
			tab(res);
		}
    });
    /**
     * 删除进货表
     */
    table.on('tool(doEven)', function(obj){
        var data = obj.data;
        if(obj.event === 'setPrice'){
            layer.prompt({
              formType: 2
              ,title: '修改 货物名称 为 ['+ data.goodsName +'] 的单价（元）'
              ,value: data.goodsPrice
            }, function(value, index){
              layer.close(index);
              //这里一般是发送修改的Ajax请求
              data.goodsPrice =value;
              var res = undefined;
              res = synAjax("PUT", '/v0.1/goods/updateGoodsPrice', data, '修改失败!');
              if (res ==undefined ) {
				return false;
              }else {
            	  layer.msg("修改成功！");
            	  //同步更新表格和缓存对应的值
            	  obj.update({
            		  goodsPrice: value
            	  });				
              }
            });
          }
        if(obj.event === 'del'){
        	var goodsName = data.goodsName;
        	var isEnpty = synAjax("GET", '/v0.1/inport/select/'+goodsName,null, '查询失败!');
        	var isEnpty2 = synAjax("GET", '/v0.1/sell/select/'+goodsName,null, '查询失败!');
        	var isEnpty3 = synAjax("GET", '/v0.1/sellreturn/select/'+goodsName,null, '查询失败!');
        	if (isEnpty == true || isEnpty2 == true || isEnpty3 == true) {
        		layer.msg("进货单、销售单或退货单中货物已存在，禁止删除！");
        		return false;
    		}
          layer.confirm('是否删除', function(index){
        	  synAjaxJson("DELETE", '/v0.1/goods/delete/'+data.id, null, "删除失败！");
              obj.del();
              layer.close(index);
          });
        }
      });
})