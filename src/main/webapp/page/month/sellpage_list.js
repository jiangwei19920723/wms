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
        elem:'#time'
       ,type: 'month'
    })
    /**
     * 条件查询结果
     */
    $(".btn").click(function(){
    	var time = $("#time").val();
		var inport = synAjax("GET", '/v0.1/sell/select/group?time='+time, null, '查询失败!');
    	tab(inport);
        return false;
    });
    /*数据表格初始化*/
    function getDataCallBack() {
    	var d = new Date();
    	var vYear = d.getFullYear();
    	var vMon = d.getMonth() + 1;
    	if (vMon<10) {
			vMon="0"+vMon;
		}
    	var time = vYear+'-'+vMon
		var inport = synAjax("GET", '/v0.1/sell/select/group?time='+time, null, '查询失败!');
    	tab(inport);
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
            ,height:600
            ,page: true //开启分页
            ,cols: [[ //表头
                {field: 'goodsName', title: '货物名称'}
                ,{field: 'goodsCode', title: '货物编号'}
                ,{field: 'inportNumber', title: '销售总数量'}
                ,{field: 'goodsUnit', title: '单位'}
                ,{field: 'inportPrice', title: '销售总价（元）'}
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
    //初始化数据方法
    getDataCallBack();

})