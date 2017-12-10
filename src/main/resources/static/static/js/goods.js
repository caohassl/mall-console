$(function() {

	// init date tables
	var userListTable = $("#goods_list").dataTable({
		"data":$(goodsList),
		"columns": [

			{ "data": 'id', "bSortable": false, "visible" : false},
			{ "data": 'goodsId', "visible" : true, "bSortable": false},
            {
                "data": '操作' ,
                "width":'16%',
                "bSortable": false,
                "render": function ( data, type, row ) {
                    return function(){
                        // html
                        var html = '<p id="'+ row.id +'"  goodsId="'+row.goodsId+'" goodsStatus="'+row.goodsStatus+'">'+
                            '<button class="btn btn-warning btn-xs upGoods" >上架</button>  '+
                            '<button class="btn btn-warning btn-xs downGoods" >下架</button>  '+
                            '<button class="btn btn-warning btn-xs update" >编辑</button>  '+
                            '<button class="btn btn-danger btn-xs delete" >删除</button>  '+
                            '</p>';

                        return html;
                    };
                }
            },
			{ "data": 'goodsName', "visible" : true, "bSortable": false},
			{ "data": 'goodsSpec', "visible" : true, "bSortable": false},
			{ "data": 'goodsPrice', "visible" : true, "bSortable": true},
			{ "data": 'goodsAmount', "visible" : true, "bSortable": false},
            {
                "data": 'goodsStatus' ,
                "bSortable": false,
                "visible" : true,
                "render": function ( data, type, row ) {
                    return function(){
                        var html='';
                        if(data=="00"){
						html="<font color='green'>上架</font>";
						}else {
                        	html="<font color='red'>下架</font>";
						}
                        return html;
                    };
                }
            },
	{ "data": 'promotionGoodsPrice', "visible" : true, "bSortable": false},
	{ "data": 'goodsDesc', "visible" : true, "bSortable": false},
	{ "data": 'insertTime', "visible" : true, "bSortable": false},
	{ "data": 'updateTime', "visible" : false, "bSortable": false}
		],
		"language" : {
			"sProcessing" : "处理中...",
			"sLengthMenu" : "每页 _MENU_ 条记录",
			"sZeroRecords" : "没有匹配结果",
			"sInfo" : "第 _PAGE_ 页 ( 总共 _PAGES_ 页，_TOTAL_ 条记录 )",
			"sInfoEmpty" : "无记录",
			"sInfoFiltered" : "(由 _MAX_ 项结果过滤)",
			"sInfoPostFix" : "",
			"sSearch" : "搜索:",
			"sUrl" : "",
			"sEmptyTable" : "表中数据为空",
			"sLoadingRecords" : "载入中...",
			"sInfoThousands" : ",",
			"oPaginate" : {
				"sFirst" : "首页",
				"sPrevious" : "上页",
				"sNext" : "下页",
				"sLast" : "末页"
			},
			"oAria" : {
				"sSortAscending" : ": 以升序排列此列",
				"sSortDescending" : ": 以降序排列此列"
			}
		}
	});

    // job operate upGoods
    $("#goods_list").on('click', '.upGoods',function() {
        var id = $(this).parent('p').attr("id");
        var goodsId = $(this).parent('p').attr("goodsId");
        var goodsStatus = $(this).parent('p').attr("goodsStatus");
        if("00"==goodsStatus){
            ComAlert.show(2, "商品已是上架状态");
            return;
		}
        ComConfirm.show("确认上架商品【"+goodsId+"】?", function(){
            $.ajax({
                type : 'POST',
                url : base_url + "/goodsInfo/upGoods",
                data : {
                    "id" : id
                },
                dataType : "json",
                success : function(data){
                    if (data.code == 200) {
                        ComAlert.show(1, "上架成功", function(){
                            window.location.reload();
                        });
                    } else {
                        ComAlert.show(2, (data.msg || "上架失败") );
                    }
                },
            });
        });
    });

    // job operate downGoods
    $("#goods_list").on('click', '.downGoods',function() {
        var id = $(this).parent('p').attr("id");
        var goodsId = $(this).parent('p').attr("goodsId");
        var goodsStatus = $(this).parent('p').attr("goodsStatus");
        if("01"==goodsStatus){
            ComAlert.show(2, "商品已是下架状态");
            return;
        }
        ComConfirm.show("确认下架商品【"+goodsId+"】?", function(){
            $.ajax({
                type : 'POST',
                url : base_url + "/goodsInfo/downGoods",
                data : {
                    "id" : id
                },
                dataType : "json",
                success : function(data){
                    if (data.code == 200) {
                        ComAlert.show(1, "下架成功", function(){
                            window.location.reload();
                        });
                    } else {
                        ComAlert.show(2, "下架失败");
                    }
                },
            });
        });
    });


	// job operate delete
	$("#goods_list").on('click', '.delete',function() {
		var id = $(this).parent('p').attr("id");
		var goodsId = $(this).parent('p').attr("goodsId");
		ComConfirm.show("确认删除商品【"+goodsId+"】?", function(){
			$.ajax({
				type : 'POST',
				url : base_url + "/goodsInfo/delete",
				data : {
					"id" : id
				},
				dataType : "json",
				success : function(data){
					if (data.code == 200) {
						ComAlert.show(1, "删除成功", function(){
							window.location.reload();
						});
					} else {
						ComAlert.show(2, (data.msg || "删除失败") );
					}
				},
			});
		});
	});

	// jquery.validate 自定义校验 “英文字母开头，只含有英文字母、数字和下划线”
	jQuery.validator.addMethod("userNameValid", function(value, element) {
		var length = value.length;
		var valid = /^[a-zA-Z][a-zA-Z0-9_]*$/;
		return this.optional(element) || valid.test(value);
	}, "只支持英文字母开头，只含有英文字母、数字和下划线");

	// 新增
	$("#add").click(function(){
		$('#addModal').modal({backdrop: false, keyboard: false}).modal('show');
	});

	var addModalValidate = $("#addModal .form").validate({
		errorElement : 'span',  
        errorClass : 'help-block',
        focusInvalid : true,  
        rules : {
			userName : {
				required : true,
				minlength: 5,
				maxlength: 20,
				userNameValid: true
			},
			password : {
            	required : true,
				minlength: 5,
				maxlength: 20
            }
        }, 
        messages : {
			userName : {
            	required :"请输入“登录账号”",
				minlength: "长度不可少于5",
				maxlength: "长度不可多余20"
            },
			password : {
            	required :"请输入“登录密码”",
				minlength: "长度不可少于5",
				maxlength: "长度不可多余20"
            }
        },
		highlight : function(element) {  
            $(element).closest('.form-group').addClass('has-error');  
        },
        success : function(label) {  
            label.closest('.form-group').removeClass('has-error');  
            label.remove();  
        },
        errorPlacement : function(error, element) {  
            element.parent('div').append(error);  
        },
        submitHandler : function(form) {
        	$.post(base_url + "/userInfo/add",  $("#addModal .form").serialize(), function(data, status) {
    			if (data.code == "200") {
					$('#addModal').modal('hide');
					setTimeout(function () {
						ComAlert.show(1, "新增成功", function(){
							window.location.reload();
						});
					}, 315);
    			} else {
					ComAlert.show(2, (data.msg || "新增失败") );
    			}
    		});
		}
	});
	$("#addModal").on('hide.bs.modal', function () {
		$("#addModal .form")[0].reset();
		addModalValidate.resetForm();
		$("#addModal .form .form-group").removeClass("has-error");
		$(".remote_panel").show();	// remote
	});

	// 更新
	$("#user_list").on('click', '.update',function() {

		// base data
		$("#updateModal .form input[name='id']").val($(this).parent('p').attr("id"));
		$("#updateModal .form input[name='userName']").val($(this).parent('p').attr("userName"));
        $("#updateModal .form input[name='password']").val("");
		$("#updateModal .form input[name='type']").eq($(this).parent('p').attr("type")).click();
		$("#updateModal .form input[name='realName']").val($(this).parent('p').attr("realName"));

		// show
		$('#updateModal').modal({backdrop: false, keyboard: false}).modal('show');
	});
	var updateModalValidate = $("#updateModal .form").validate({
		errorElement : 'span',  
        errorClass : 'help-block',
        focusInvalid : true,
		highlight : function(element) {
            $(element).closest('.form-group').addClass('has-error');  
        },
        success : function(label) {  
            label.closest('.form-group').removeClass('has-error');  
            label.remove();  
        },
        errorPlacement : function(error, element) {  
            element.parent('div').append(error);  
        },
        submitHandler : function(form) {
			// post
    		$.post(base_url + "/user/update", $("#updateModal .form").serialize(), function(data, status) {
    			if (data.code == "200") {
					$('#updateModal').modal('hide');
					setTimeout(function () {
						ComAlert.show(1, "更新成功", function(){
							window.location.reload();
						});
					}, 315);
    			} else {
					ComAlert.show(2, (data.msg || "更新失败") );
    			}
    		});
		}
	});
	$("#updateModal").on('hide.bs.modal', function () {
		$("#updateModal .form")[0].reset()
	});

	/*
	// 新增-添加参数
	$("#addModal .addParam").on('click', function () {
		var html = '<div class="form-group newParam">'+
				'<label for="lastname" class="col-sm-2 control-label">参数&nbsp;<button class="btn btn-danger btn-xs removeParam" type="button">移除</button></label>'+
				'<div class="col-sm-4"><input type="text" class="form-control" name="key" placeholder="请输入参数key[将会强转为String]" maxlength="200" /></div>'+
				'<div class="col-sm-6"><input type="text" class="form-control" name="value" placeholder="请输入参数value[将会强转为String]" maxlength="200" /></div>'+
			'</div>';
		$(this).parents('.form-group').parent().append(html);
		
		$("#addModal .removeParam").on('click', function () {
			$(this).parents('.form-group').remove();
		});
	});
	*/

});
