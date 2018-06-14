layui.config({
    base : "js/"
}).use(['form','layer','jquery','table'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        $ = layui.jquery,
        table=layui.table;
    var a = 0;
    
    form.on('submit(infoSumbit)', function(data){
    	var arr = new Array();
    	for (var i = 0; i < a; i++) {
			var goodsName = $("#goodsName"+i).val();
			var sellNumber = $("#sellNumber"+i).val();
			var goodsPrice = $("#goodsPrice"+i).val();
			if (sellNumber ==0) {
				layer.msg("销售数量不能为0或空！");
				return false;
			}
			if (goodsPrice == "") {
				layer.msg("总价格不能为空！");
				return false;				
			}
			arr.push(goodsName+","+sellNumber+","+goodsPrice);
		}
    	data.field.goods=arr;
        var r = synAjax("POST", '/v0.1/sell/insert', data.field, '库存数量不足，添加失败!');
        if (r !=undefined) {
        	layer.msg("添加成功！");	
		}
        return false;
    });
    $("#goods").click(function(){
    	layer.open({
            type: 2
            ,offset: 'auto' //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
            ,id: 'goods' //防止重复弹出
            ,content: 'page/sell/goods_list.html'
            ,btn: '确认'
            ,btnAlign: 'c' //按钮居中
            ,shade: 0 //不显示遮罩
            ,area: ['80%', '90%']
            ,yes: function(index,layero){
            	var res = $(layero).find('iframe')[0].contentWindow.callbackdata();
            	if (a !=0) {
					for (var i = 0; i < a; i++) {
						$('#good'+i).remove();
					}
				}
            	a = res.length;
            	for (var i = 0; i < res.length; i++) {
            		$('#good').after(
            		'<div class="layui-form-item" id="good'+i+'">                                                                                           '+
            		'  <div class="layui-inline">                                                                                                       '+
            		'    <label class="layui-form-label">货物名称</label>                                                                              '+
            		'    <div class="layui-input-inline" style="width: 300px;">                                                                                              '+
            		'      <input style="width: 300px;" lay-verType="msg" type="text" id="goodsName'+i+'" required lay-verify="required" autocomplete="off" class="layui-input info_input" disabled="disabled" value="'+res[i].goodsName+'"> '+
            		'    </div>                                                                                                                        '+
            		'  </div>                                                                                                                          '+
            		'  <div class="layui-inline">                                                                                                      '+
            		'    <label class="layui-form-label">销售数量</label>                                                                              '+
            		'    <div class="layui-input-inline">                                                                                              '+
            		'      <input lay-verType="msg" type="text" id="sellNumber'+i+'" required lay-verify="required/number" autocomplete="off" class="layui-input info_input" value="'+res[i].sellNumber+'">      '+
            		'    </div>                                                                                                                        '+
            		'  </div>                                                                                                                          '+
            		'  <div class="layui-inline">                                                                                                      '+
            		'    <label class="layui-form-label">单价(元)</label>                                                                            '+
            		'    <div class="layui-input-inline">                                                                                              '+
            		'      <input lay-verType="msg" type="text" id="goodsPrice'+i+'" required lay-verify="required/number" autocomplete="off" class="layui-input info_input">          '+
            		'    </div>                                                                                                                        '+
            		'  </div>                                                                                                                          '+
            		'</div>');
				}
                layer.closeAll();
            }
          });
    });
    $("#sellPrice").click(function(){
    	var number = 0;
    	for (var i = 0; i < a; i++) {
			var goodsPrice = $("#goodsPrice"+i).val();
			var sellNumber = $("#sellNumber"+i).val();
			number+=(parseFloat(goodsPrice)*parseFloat(sellNumber));
		}
    	$("#sellPrice").val(number);
    });
})
