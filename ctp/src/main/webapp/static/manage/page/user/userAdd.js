layui.use(['form','layer'],function(){
    var form = layui.form
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;
    console.log(111);
    
    form.on("submit(addUser)",function(data){
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        
        
        
        var operationType = $(".operationType").val();
        
        if(operationType == 1){
        	console.log("post新增请求");
        	$.ajax({
            	url:"/ctp/user/0",
            	type:"POST",
            	dataType:"json",
            	data:{
            		_method:$("._method").val(),
            		loginName:$(".userName").val(),
            		email : $(".userEmail").val(),
            		gender : data.field.gender,
            		userGrade : data.field.userGrade,
            		userStatus : data.field.userStatus,
            		createTime : submitTime,
            		myIntroduction : $(".userDesc").val(),
            	},
            	success:function(result){
            		if(result.statuCode == 1){
            			top.layer.close(index);
                        top.layer.msg("用户添加成功！",{icon:1,time:1000});
                        layer.closeAll("iframe");
                        //刷新父页面
                        parent.location.reload();
            		}else if(result.statuCode == 0){
            			top.layer.msg("系统错误，用户添加失败！",{icon:5,time:1000});
            		}else if(result.statuCode == 2){
            			top.layer.msg("已有该用户！",{icon:4,time:1000});
            		}
//            		console.log(result);
            	},
            	error:function(){
            		top.layer.msg("系统繁忙，请稍后重试！",{icon:5,time:1000});
            	}
            })
        }else if(operationType == 2){
        	console.log("put修改请求");
        	$.ajax({
            	url:"/ctp/user/"+$(".userId").val(),
            	type:"PUT",
            	dataType:"json",
            	data:{
            		id:$(".userId").val(),
            		loginName:$(".userName").val(),
            		email : $(".userEmail").val(),
            		gender : data.field.gender,
            		userGrade : data.field.userGrade,
            		userStatus : data.field.userStatus,
            		createTime : submitTime,
            		myIntroduction : $(".userDesc").val(),
            	},
            	success:function(result){
            		if(result.statuCode == 1){
            			top.layer.close(index);
                        top.layer.msg("保存成功！",{icon:1,time:1000});
            		}else{
            			top.layer.msg("系统错误，保存失败！",{icon:5,time:1000});
            		}
            		layer.closeAll("iframe");
            		//刷新父页面
                    parent.location.reload();
            	},
            	error:function(){
            		top.layer.msg("系统繁忙，请稍后重试！",{icon:5,time:1000});
            	}
            })
        }
        // 实际使用时的提交信息
        // $.post("上传路径",{ 
        //     userName : $(".userName").val(),  //登录名
        //     userEmail : $(".userEmail").val(),  //邮箱
        //     userSex : data.field.sex,  //性别
        //     userGrade : data.field.userGrade,  //会员等级
        //     userStatus : data.field.userStatus,    //用户状态
        //     newsTime : submitTime,    //添加时间
        //     userDesc : $(".userDesc").text(),    //用户简介
        // },function(res){
        //
        // })
        /*var dataTest = {
        		loginName:$(".userName").val(),
        		email : $(".userEmail").val(),
        		gender : data.field.gender,
        		userStatus : data.field.userStatus,
        		createTime : submitTime,
        		myIntroduction : $(".userDesc").val(),
        	}*/
        
        
        
        return false;
    })

    //格式化时间
    function filterTime(val){
        if(val < 10){
            return "0" + val;
        }else{
            return val;
        }
    }
    //定时发布
    var time = new Date();
    var submitTime = time.getFullYear()+'-'+filterTime(time.getMonth()+1)+'-'+filterTime(time.getDate())+' '+filterTime(time.getHours())+':'+filterTime(time.getMinutes())+':'+filterTime(time.getSeconds());

})