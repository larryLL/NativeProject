layui.use(['form','layer','jquery'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer
        $ = layui.jquery;

    //登录按钮
    form.on("submit(login)",function(data){
    	console.log($(".layui-form").serialize());
    	$.ajax({
    		url:"/ctp/manage/login",
    		type:"post",
    		dataType:"json",
    		data:$(".layui-form").serialize(),
    		success:function(result){
    			console.log(result);
    			if(result.statuCode == 1){
    				$(".loginBody .layui-form-item .layui-input").parent().removeClass("layui-input-focus");
    				$("#login_btn").text("登录中...").attr("disabled","disabled").addClass("layui-disabled");
//        	        localStorage.setItem("loginUser",result);
    				sessionStorage.setItem("loginAdmin",JSON.stringify(result.obj));
    				setTimeout(function(){
        	            window.location.href = "../../index.html";
        	        },1000);
        	        
    			}else if(result.statuCode == 0){
    				layer.msg("验证码错误",{icon:5,time:1000});
    				$("#code").parent().addClass("layui-input-focus").find(".layui-input").focus();
    			}else if(result.statuCode == 2){
    				layer.msg("该登陆账号不存在！",{icon:5,time:1000});
    				$(".loginBody .layui-form-item .layui-input").parent().removeClass("layui-input-focus");
    			}else if(result.statuCode == 3){
    				layer.msg("密码错误！",{icon:5,time:1000});
    				$("#password").parent().addClass("layui-input-focus").find(".layui-input").focus();
    			}else if(result.statuCode == 4){
    				layer.msg("你不是管理员，没有权利登陆该系统！",{icon:4,time:1000});
    				$(".loginBody .layui-form-item .layui-input").parent().removeClass("layui-input-focus");
    			}
    		
    		},
    		error:function(){
    			layer.msg("系统繁忙，请稍后重试！");
    		}
    	})
        
        return false;
    })

    //表单输入效果
    $(".loginBody .input-item").click(function(e){
        e.stopPropagation();
        $(this).addClass("layui-input-focus").find(".layui-input").focus();
    })
    $(".loginBody .layui-form-item .layui-input").focus(function(){
        $(this).parent().addClass("layui-input-focus");
    })
    $(".loginBody .layui-form-item .layui-input").blur(function(){
        $(this).parent().removeClass("layui-input-focus");
        if($(this).val() != ''){
            $(this).parent().addClass("layui-input-active");
        }else{
            $(this).parent().removeClass("layui-input-active");
        }
    })
    
    
})
