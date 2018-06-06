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
    	var res = synAjax("GET", '/v0.1/sell/list', null, '查询失败!');
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
            	{field: 'id', title: '销售单号',width:150}
                ,{field: 'createTime', title: '创建时间',width:140,sort: true,templet:'<div>{{ layui.laytpl.toDateString(d.createTime,"yyyy-MM-dd")}}</div>'}
                ,{field: 'sellPrice', title: '销售总价（元）',width:140}
                ,{field: 'createBy', title: '销售单创建人'}
                ,{field: 'sellBy', title: '买家姓名'}
                ,{field: 'phone', title: '买家电话',width:140}
                ,{field:'doinvoice1',title:'打印',toolbar:'#btnBar1'}
                ,{field:'doinvoice',title:'操作',toolbar:'#btnBar',width:200}
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
			$(".over_time_2").before('<div class="layui-col-xs6 over_time_1"><div class="grid-demo grid-demo-bg1"><div class="layui-inline"><input type="text" class="layui-input"style="width:332px;" id ="sellBy"></div></div></div></div>');
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
			var res = synAjaxJson("GET", '/v0.1/sell/select', 'startTime='+start_time+'&endTime='+end_time, "查询失败！");
			tab(res);
		}else if (value == 1) {
			var sellBy = $("#sellBy").val();
			var res = synAjaxJson("GET", '/v0.1/sell/select', 'sellBy='+sellBy, "查询失败！");
			tab(res);
		}
    });
    /**
     * 删除进货表
     */
    table.on('tool(doEven)', function(obj){
        var data = obj.data;
        if(obj.event == 'del'){
          layer.confirm('是否删除', function(index){
        	  synAjaxJson("DELETE", '/v0.1/sell/delete/'+data.id, null, "删除失败！");
              obj.del();
              layer.close(index);
          });
        }
        if(obj.event == 'sel'){
        	layer.open({
                type: 2
                ,offset: 'auto' //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                ,id: 'goods' //防止重复弹出
                ,content: 'page/sell/goods_vo_list.html'
                ,btn: '确认'
                ,btnAlign: 'c' //按钮居中
                ,shade: 0 //不显示遮罩
                ,area: ['80%', '90%']
                ,yes: function(index,layero){
                	layer.closeAll();
                }
        		,success: function (layero, index) {  
        		$(layero).find('iframe')[0].contentWindow.getId(data.id);
            } 
              });
          }
        if (obj.event == 'print') {
        	window.open("print.html?id="+data.id, "_blank", "scrollbars=yes,resizable=1,modal=false,alwaysRaised=yes");
		}
      });
})