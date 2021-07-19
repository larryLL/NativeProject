function formatDate(timestamp) {
	var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
    var Y = date.getFullYear() + '-';
    var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
    var D = date.getDate() + ' ';
    return Y+M+D;
 }

layui.use(['form','layer','laydate','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table;

    //商品列表
    var tableIns = table.render({
        elem: '#goodsList',
        url : 'http://localhost:8080/ctp/goods/0',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limit : 10,
        limits : [10,15,20,25],
        id : "goodsListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'goodsNumber', title: '商品编号', width:200, align:"center"},
            {field: 'goodsName', title: '商品名称', width:250},
            {field: 'user', title: '发布者', align:'center',width:80,templet:function(d){return d.user.userName;}},
            {field: 'goodsStatus', title: '发布状态',  align:'center',templet:"#goodsStatus"},
            /*{field: 'newsLook', title: '浏览权限', align:'center'},
            {field: 'newsTop', title: '是否置顶', align:'center', templet:function(d){
                return '<input type="checkbox" name="newsTop" lay-filter="newsTop" lay-skin="switch" lay-text="是|否" '+d.newsTop+'>'
            }},*/
            {field: 'createTime', title: '创建时间', align:'center', minWidth:110, templet:function(d){
                return formatDate(d.createTime);
            }},
            {field: 'registerTime', title: '发布时间', align:'center', minWidth:110, templet:function(d){
                if(d.registerTime == undefined) return "未发布";
            	return formatDate(d.registerTime);
            }},
            {title: '操作', width:170, templet:'#goodsListBar',fixed:"right",align:"center"}
        ]],
        done:function(data){
        	console.log(data);
        }
    });

    //是否置顶
    /*form.on('switch(newsTop)', function(data){
        var index = layer.msg('修改中，请稍候',{icon: 16,time:false,shade:0.8});
        setTimeout(function(){
            layer.close(index);
            if(data.elem.checked){
                layer.msg("置顶成功！");
            }else{
                layer.msg("取消置顶成功！");
            }
        },500);
    })*/

    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search_btn").on("click",function(){
        if($(".searchVal").val() != ''){
            table.reload("goodsListTable",{
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                	goodsNumber: $(".searchVal").val()  //搜索的关键字
                }
            })
        }else{
            layer.msg("请输入搜索的内容");
        }
    });

    $(".searchVal").keyup(function(event){
    	table.reload("goodsListTable",{
    		page:{
    			curr: 1 //重新从第 1 页开始
    		},
    		where:{
    			goodsNumber:$(".searchVal").val()	//搜索的商品编号
    		}
    	});
    });
    
    //批量删除
    $(".delAll_btn").click(function(){
        var checkStatus = table.checkStatus('goodsListTable'),
            data = checkStatus.data,
            goodsId = [];
        if(data.length > 0) {
            for (var i in data) {
            	goodsId.push(data[i].id);
            }
            layer.confirm('确定删除选中的文章？', {icon: 3, title: '提示信息'}, function (index) {
                var indexDelete = layer.load();
                $.ajax({
                	url:"/ctp/goods/batch/0",
                	type:"POST",
                	traditional: true,
                	data:{
                		goodsId:goodsId,
                		_method:"DELETE"
                	},
                	dataType:"json",
                	success:function(result){
                		if(result.success){
                			layer.msg("操作成功",{icon:1,time:1000});
                			tableIns.reload();
                			layer.close(indexDelete);
                		}else{
                			layer.msg("操作失败",{icon:5,time:1000});
                			layer.close(indexDelete);
                		}
                		
                	},
                	error:function(){
                		layer.msg("系统繁忙，请稍后重试！",{icon:5,time:1000});
                		layer.close(indexDelete);
                	}
                });
            })
        }else{
            layer.msg("请选择需要删除的文章");
        }
    })

    //商品审核
    function checkGoods(obj,goodsStatus){
    	var index = layer.load();
    	console.log(obj.data.user.loginName);
    	$.ajax({
        	url:"/ctp/goods/"+obj.data.id,
        	type:"PUT",
        	dataType:"json",
        	data:{
        		id:obj.data.id,
        		goodsStatus: goodsStatus,
        		userFlag: obj.data.user.loginName
        	},
        	success:function(result){
        		obj.update({
        			goodsStatus:goodsStatus
                });
        		layer.close(index);
        		layer.msg("操作成功",{icon:1,time:1000});
//        		console.log($(this).addClass(" layui-btn-disabled"));
        		tableIns.reload();
        	},
        	error:function(){
        		layer.close(index);
        		layer.msg("系统繁忙，请稍后重试！",{icon:5,time:1000});
        	}
        })
    }
    
    //列表操作
    table.on('tool(goodsList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'check'){ //编辑
        	
        	layer.confirm("该商品是否通过审核？",{icon:3, title:'提示信息'},function(){
        		checkGoods(obj,2);  //2：审核通过
        		
                layer.close(layEvent);
        		
        	});
        } else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除此商品？',{icon:3, title:'提示信息'},function(index){
            	var indexDelete = layer.load();
            	$.ajax({
                	url:"/ctp/goods/"+obj.data.id,
                	type:"DELETE",
                	contentType:"application/json;charset=utf-8",
//                	traditional: true,
                	data:{
                		goodsId:obj.data.id,
                	},
                	success:function(result){
                		if(result.success){
                			layer.close(indexDelete);
                			layer.msg("操作成功",{icon:1,time:1000});
                			tableIns.reload();
                			
                		}else{
                			layer.close(indexDelete);
                			layer.msg("操作失败",{icon:5,time:1000});
                		}
                		
                	},
                	error:function(){
                		layer.close(indexDelete);
                		layer.msg("系统繁忙，请稍后重试！",{icon:5,time:1000});
                		
                	}
                });
            	
                layer.close(index);
            });
        }
    });

})