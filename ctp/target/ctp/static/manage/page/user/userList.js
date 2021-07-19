
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
    
    //用户列表
    var tableIns = table.render({
        elem: '#userList',
        url : '/ctp/user/0',
        method:"GET",
        contentType:"application/json",
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 20,
        id : "userListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'loginName', title: '登陆名', minWidth:100, align:"center"},
            {field: 'email', title: '用户邮箱', minWidth:200, align:'center',templet:function(d){
                return '<a class="layui-blue" href="mailto:'+d.email+'">'+d.email+'</a>';
            }},
            {field: 'gender', title: '用户性别', align:'center',templet:function(d){
            	if(d.gender == 1){
            		return "男";
            	}else if(d.gender == 0){
            		return "女";
            	}else{
            		return "保密";
            	}
            }},
            {field: 'userStatus', title: '用户状态',  align:'center',templet:"#userStatus"},
            {field: 'role', title: '用户类型', align:'center',templet:function(d){
            	if(d.role != undefined){
            		if(d.role.id == 1){
                        return "管理员";
                    }else if(d.role.id == 2){
                        return "普通用户";
                    }
            	}else{
            		return "暂无角色";
            	}
            	
                /**/
            }},
            {field: 'loginTime', title: '最后登录时间', align:'center',minWidth:160,templet:function(d){
//            	console.log();
            	if(d.loginTime == undefined)return "未登陆过";
            	return formatDate(d.loginTime);
            }},
            {title: '操作', minWidth:175, templet:'#userListBar',fixed:"right",align:"center"}
        ]],
    	done:function(res){
    		console.log(res);
    	}
    });

    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search_btn").on("click",function(){
        if($(".searchVal").val() != ''){
            table.reload("userListTable",{
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    userName: $(".searchVal").val()  //搜索的关键字
                }
            })
        }else{
            layer.msg("请输入搜索的内容");
        }
    });
    //自动监听搜索
    $(".searchVal").keyup(function(event){
    	table.reload("userListTable",{
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                userName: $(".searchVal").val()  //搜索的关键字
            }
        })
    });

    //添加用户
    function addUser(edit,title,operationType,btn){
        var index = layui.layer.open({
            title : title,
            type : 2,
            content : "userAdd.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                body.find(".operationType").val(operationType);
                body.find("#btn").text(btn);
                if(edit){
                	body.find(".userId").val(edit.id);
                    body.find(".userName").val(edit.userName);  //登录名
                    body.find(".userEmail").val(edit.email);  //邮箱
                    body.find(".userSex input[value="+edit.gender+"]").prop("checked","checked");  //性别
                    if(edit.role != null){
                    	body.find(".userGrade").val(edit.role.id);  //会员类型
                    }
                    
                    body.find(".userStatus").val(edit.userStatus);    //用户状态
                    body.find(".userDesc").text(edit.myIntroduction);    //用户简介
                }
                form.render();
                setTimeout(function(){
                    layui.layer.tips('点击此处返回用户列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
        })
        layui.layer.full(index);
        window.sessionStorage.setItem("index",index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(window.sessionStorage.getItem("index"));
        })
    }
    
    $(".addUser_btn").click(function(){
        addUser(false,"新增用户",1,"立即新增");
    })

    //批量删除
    $(".delAll_btn").click(function(){
        var checkStatus = table.checkStatus('userListTable'),
            data = checkStatus.data,
            userId = [];
        if(data.length > 0) {
            for (var i in data) {
                userId.push(data[i].id);
            }
            var testArr = [0,1];
            console.log(testArr);
            console.log(userId);
            layer.confirm('确定删除选中的用户？', {icon: 3, title: '提示信息'}, function (index) {
            	var indexDelete = layer.load();
            	$.ajax({
                	url:"/ctp/user/batch/0",
                	type:"POST",
                	traditional: true,
                	data:{
                		"userId":userId,
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
            layer.msg("请选择需要删除的用户");
        }
    })
    
    function userStatusChange(obj,userStatus){
    	$.ajax({
        	url:"/ctp/user/"+obj.data.id,
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
    table.on('tool(userList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;
        console.log(obj);
        if(layEvent === 'edit'){ //编辑
            addUser(data,"编辑用户",2,"保存");
        }else if(layEvent === 'usable'){ //启用禁用
        	
            var _this = $(this),
                usableText = "是否确定禁用此用户？",
                btnText = "已禁用";
            if(_this.text()=="已禁用"){
                usableText = "是否确定启用此用户？",
                btnText = "已启用";
                
            }
            layer.confirm(usableText,{
                icon: 3,
                title:'系统提示',
                cancel : function(index){
                    layer.close(index);
                }
            },function(index){
            	if(_this.text() == "已启用"){
            		userStatusChange(obj,1);
            		_this.text(btnText);
                    layer.close(index);
            	}else if(_this.text() == "已禁用"){
            		userStatusChange(obj,0);
            		_this.text(btnText);
                    layer.close(index);
            	}
                
            },function(index){
//            	console.log(222);
                layer.close(index);
            });
        }else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除此用户？',{icon:3, title:'提示信息'},function(index){
                   	var indexDelete = layer.load();
                    $.ajax({
                    	url:"/ctp/user/"+obj.data.id,
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
