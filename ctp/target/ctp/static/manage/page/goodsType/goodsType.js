
function formatDate(timestamp) {
	var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
    var Y = date.getFullYear() + '-';
    var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
    var D = date.getDate() + ' ';
    var h = date.getHours() + ':';
    var m = date.getMinutes() + ':';
    var s = date.getSeconds();
    return Y+M+D+h+m+s;
    }

layui.use(['form','layer','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;
    
    //类型种类列表
    var tableIns = table.render({
        elem: '#goodsTypeList',
        url : '/ctp/goodsType/0',
        method:"GET",
        contentType:"application/json",
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 20,
        id : "goodsTypeListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'typeNumber', title: '商品类型编号', minWidth:100, align:"center"},
            {field: 'typeName', title: '类型名称', minWidth:100, align:'center'},
            {field: 'typeDetail', title: '类型介绍',  align:'left'},
            {field: 'createTime', title: '创建时间', align:'center',minWidth:160,templet:function(d){
            	return formatDate(d.createTime);
            }},
            {title: '操作', minWidth:100, templet:'#goodsTypeListBar',fixed:"right",align:"center"}
        ]],
    	done:function(res){
    		console.log(res);
    	}
    });

    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search_btn").on("click",function(){
        if($(".searchVal").val() != ''){
            table.reload("goodsTypeListTable",{
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                	typeNumber: $(".searchVal").val()  //搜索的关键字
                }
            })
        }else{
            layer.msg("请输入搜索的内容");
        }
    });
    //自动监听搜索
    $(".searchVal").keyup(function(event){
    	table.reload("goodsTypeListTable",{
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
            	typeNumber: $(".searchVal").val()  //搜索的关键字
            }
        })
    });

    //添加商品类型
    function addGoodsType(edit,title,operationType,btn){
        var index = layui.layer.open({
            title : title,
            type : 2,
            area: ['400px', '300px'],
            content : "goodsTypeAdd.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                body.find(".operationType").val(operationType);
                body.find("#btn").text(btn);
                if(edit){
                	body.find(".typeId").val(edit.id);
                    body.find(".typeName").val(edit.typeName);  //商品类型名称
                    body.find(".typeDetail").text(edit.typeDetail);    //用户简介
                }
                form.render();
                setTimeout(function(){
                    layui.layer.tips('点击此处返回类型种类列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
        })
//        layui.layer.full(index);
        window.sessionStorage.setItem("index",index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(window.sessionStorage.getItem("index"));
        })
    }
    
    $(".addGoodsType_btn").click(function(){
    	addGoodsType(false,"新增商品种类",1,"立即新增");
    })

    //批量删除
    $(".delAll_btn").click(function(){
        var checkStatus = table.checkStatus('goodsTypeListTable'),
            data = checkStatus.data,
            goodsTypeId = [];
        if(data.length > 0) {
            for (var i in data) {
            	goodsTypeId.push(data[i].id);
            }
            var testArr = [0,1];
            console.log(testArr);
            layer.confirm('确定删除选中的商品类型？', {icon: 3, title: '提示信息'}, function (index) {
            	var indexDelete = layer.load();
            	$.ajax({
                	url:"/ctp/goodsType/batch/0",
                	type:"POST",
                	traditional: true,
                	data:{
                		goodsTypeId:goodsTypeId,
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
            	
                layer.close(index);
                
            })
        }else{
            layer.msg("请选择需要删除的类型");
        }
    })
    
    function userStatusChange(obj,userStatus){
    	$.ajax({
        	url:"/ctp/goodsType/"+obj.data.id,
        	type:"PUT",
        	dataType:"json",
        	data:{
        		id:obj.data.id,
        		userStatus: userStatus,
        	},
        	success:function(result){
        		obj.update({
                	userStatus:userStatus
                });
        	},
        	error:function(){
        		layer.msg("系统繁忙，请稍后重试！",{icon:5,time:1000});
        	}
        })
    }
    
    //列表操作
    table.on('tool(goodsTypeList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;
        console.log(obj);
        if(layEvent === 'edit'){ //编辑
        	addGoodsType(data,"编辑类型",2,"保存");
        }else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除此商品类型？',{icon:3, title:'提示信息'},function(index){
                   	var indexDelete = layer.load();
                    $.ajax({
                    	url:"/ctp/goodsType/"+obj.data.id,
                    	type:"DELETE",
                    	dataType:"json",
                    	data:{
                    		id:obj.data.id,
                    	},
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
                    	}
                    });
                    layer.close(index);
            });
        }
    });
    
    
})
