$(function(){
	// 复选框
    $('input').iCheck({
      checkboxClass: 'icheckbox_square-blue',
      radioClass: 'iradio_square-blue',
      increaseArea: '20%' // optional
    });
    
	// 登陆.规则校验
	var loginFormValid = $("#loginForm").validate({
		errorElement : 'span',
        errorClass : 'help-block',
        focusInvalid : true,  
        rules : {  
        	userName : {  
        		required : true ,
                minlength: 5,
                maxlength: 18
            },  
            password : {  
            	required : true ,
                minlength: 5,
                maxlength: 18
            } 
        }, 
        messages : {  
        	userName : {  
                required :"请输入登陆账号."  ,
                minlength:"登陆账号不应低于5位",
                maxlength:"登陆账号不应超过18位"
            },  
            password : {
            	required :"请输入登陆密码."  ,
                minlength:"登陆密码不应低于5位",
                maxlength:"登陆密码不应超过18位"
            }
        },
        /*可以给未通过验证的元素加效果、闪烁等*/
		highlight : function(element) {  
            $(element).closest('.form-group').addClass('has-error');  
        },
        /*每个字段验证通过执行函数*/
        success : function(label) {  
            label.closest('.form-group').removeClass('has-error');  
            label.remove();  
        },
        /*更改错误信息显示的位置*/
        errorPlacement : function(error, element) {  
            element.parent('div').append(error);  
        },
        /*通过验证后运行的函数，里面要加上表单提交的函数，否则表单不会提交*/
        submitHandler : function(form) {
			$.post(base_url + "/validate", $("#loginForm").serialize(), function(data, status) {
				if (data.code == "200") {
					ComAlert.show(1, "登陆成功", function(){
						window.location.href = base_url+"login";
					});
				} else {
					ComAlert.show(2, data.msg);
				}
			});
		}
	});
});