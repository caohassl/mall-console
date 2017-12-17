$(function() {

// jquery.validate 自定义校验 "价格验证"
    jQuery.validator.addMethod("priceInvalid", function(value, element) {
        var length = value.length;
        var valid = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
        return this.optional(element) || valid.test(value);
    }, "此字段数值不合法");

// 数量验证
    jQuery.validator.addMethod("amountInvalid", function(value, element) {
        var length = value.length;
        var valid = /[1-9][0-9]*/;
        return this.optional(element) || valid.test(value);
    }, "此字段数值不合法");

    /**
     * 判断是否null
     * @param data
     */
    function isNull(data){
        return (data == "" || data == undefined || data == null) ? true : false;
    }

	// init date tables
	var userListTable = $("#goods_list").dataTable({
		"data":$(goodsList),
		"columns": [

			{ "data": 'id', "bSortable": false, "visible" : false},
			{ "data": 'goodsId', "visible" : true, "bSortable": false,"width":'10%'},
            {
                "data": '操作' ,
                "width":'22%',
                "bSortable": false,
                "render": function ( data, type, row ) {
                    return function(){
                        // html
                        var html = '<p id="'+ row.id +'"  goodsId="'+row.goodsId+'" goodsStatus="'+row.goodsStatus+'" goodsName="'+row.goodsName+'">'+
                            '<button class="btn btn-warning btn-xs upGoods" >上架</button>  '+
                            '<button class="btn btn-warning btn-xs downGoods" >下架</button>  '+
                            '<button class="btn btn-warning btn-xs upLoadPic" >上传图片</button>  '+
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
	{ "data": 'goodsDesc', "visible" : false, "bSortable": false},
	{ "data": 'insertTime', "visible" : false, "bSortable": false},
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



	// 新增
	$("#add").click(function(){
		$('#addModal').modal({backdrop: false, keyboard: false}).modal('show');
	});


	var addModalValidate = $("#addModal .form").validate({
		errorElement : 'span',  
        errorClass : 'help-block',
        focusInvalid : true,  
        rules : {
            goodsName : {
				required : true,
                minlength:1,
				maxlength: 50,
			},
            goodsSpec : {
            	required : true,
                minlength:1,
				maxlength: 20
            },
            goodsPrice : {
                required : true,
                maxlength: 20,
                priceInvalid :true
            },
            goodsAmount:{
                required : true,
                maxlength: 20,
                amountInvalid :true
            },
            promotionGoodsPrice:{
                required : false,
                maxlength: 20,
                priceInvalid :true
            },
            goodsDesc:{
                required : false,
                maxlength: 200,
            }
        }, 
        messages : {
            goodsName : {
                required : "请输入商品名称",
                minlength: "商品名称不能为空",
                maxlength: "商品名称超过50字"
            },
            goodsSpec : {
                required : "请输入商品规格",
                minlength: "商品规格不能为空",
                maxlength: "商品规格超过20字"
            },
            goodsPrice : {
                required : "请输入商品价格",
                maxlength: "商品价格超过20字"
            },
            goodsAmount:{
                required : "请输入商品数量",
                maxlength: "商品数量超过20字"
            },
            promotionGoodsPrice:{
                maxlength: "活动价格超过20字"
            },
            goodsDesc:{
                maxlength: "商品描述超过200字"
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
            var id=$("#addModal .form input[name='id']").val();
            if(!isNull(id)){
                $.post(base_url + "/goodsInfo/update",  $("#addModal .form").serialize(), function(data, status) {
                    if (data.code == "200") {
                        $('#addModal').modal('hide');
                        setTimeout(function () {
                            ComAlert.show(1, "编辑商品成功", function(){
                                window.location.reload();
                            });
                        }, 315);
                    } else {
                        ComAlert.show(2, (data.msg || "编辑商品失败") );
                    }
                });
            }else{
                $.post(base_url + "/goodsInfo/add",  $("#addModal .form").serialize(), function(data, status) {
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
        }
	});
	$("#addModal").on('hide.bs.modal', function () {
		$("#addModal .form")[0].reset();
		addModalValidate.resetForm();
		$("#addModal .form .form-group").removeClass("has-error");
		$(".remote_panel").show();	//remote
	});

	// 更新
	$("#goods_list").on('click', '.update',function() {
	    //给框设置值
        var id = $(this).parent('p').attr("id");
        $.ajax({
            type : 'POST',
            url : base_url + "/goodsInfo/getGoodsById",
            data : {
                "id" : id
            },
            dataType : "json",
            success : function(data){
                if (data.code == 200) {
                var goodsInfo=data.content;
                    $("#addModal .form input[name='goodsName']").val(goodsInfo.goodsName);
                    $("#addModal .form input[name='goodsSpec']").val(goodsInfo.goodsSpec);
                    $("#addModal .form input[name='goodsPrice']").val(goodsInfo.goodsPrice);
                    $("#addModal .form input[name='goodsAmount']").val(goodsInfo.goodsAmount);
                    $("#addModal .form input[name='promotionGoodsPrice']").val(goodsInfo.promotionGoodsPrice);
                    $("#addModal .form input[name='goodsDesc']").val(goodsInfo.goodsDesc);
                    $("#addModal .form input[name='id']").val(goodsInfo.id);

                    $('#addModal').modal({backdrop: false, keyboard: false}).modal('show');

                } else {
                    ComAlert.show(2, (data.msg || "获取商品失败") );
                }
            },
        });
	});

    // 上传图片
    $("#goods_list").on('click', '.upLoadPic',function() {
        //设置值
        var goodsId=$(this).parent("p").attr("goodsId");
        var goodsName=$(this).parent("p").attr("goodsName");
        $("#upLoadPicModal" ).find("#goodsId").text(goodsId);
        $("#upLoadPicModal").find("#goodsName").text(goodsName);
        var width3=$("#width").width();
        var totalWidth=6;//$(".modal-lg").width()/100;
        var widthValue=totalWidth*width3+'px';
        $('#uploadPic1').css("height",widthValue);
        $('#uploadPic1').css("width",widthValue);
        $('#uploadPic2').css("height",widthValue);
        $('#uploadPic2').css("width",widthValue);
        $('#uploadPic3').css("height",widthValue);
        $('#uploadPic3').css("width",widthValue);
        $('#uploadPic4').css("height",widthValue);
        $('#uploadPic4').css("width",widthValue);

        $(".picDiv").css("float","left").css("border","1px solid #1d1a1a;");
        $("#upLoadPicModal").find("img").css("width","100%").css("height","100%");

        $('#uploadPic1').find("img").eq(0).attr("src","/upload/" + goodsId + "/"+goodsName+"(1).jpg");
        $('#uploadPic2').find("img").eq(0).attr("src","/upload/" + goodsId + "/"+goodsName+"(2).jpg");
        $('#uploadPic3').find("img").eq(0).attr("src","/upload/" + goodsId + "/"+goodsName+"(3).jpg");
        $('#uploadPic4').find("img").eq(0).attr("src","/upload/" + goodsId + "/"+goodsName+"(4).jpg");

        $('#upLoadPicModal').modal({backdrop: false, keyboard: false}).modal('show');
    });

    $("#upLoadPicModal input[type='file']").change(function(){
        var goodsId=$("#goodsId").text();
        var goodsName=$("#goodsName").text();
        var file=$(this)[0].files[0];
        var fileName=$(this)[0].files[0].name;
        var suffix=fileName.substr(fileName.indexOf(".")+1);
        if('jpg'!=suffix)return;
        var date=new FormData();
        date.append("uploadFile",file);
        date.append("goodsId",goodsId);
        date.append("flag",$(this).attr("flag"));
        date.append("goodsName",goodsName);
        $.ajax({
            url:'/goodsInfo/upLoadPic',
            type:'POST',
            data:date,
            cache: false,
            contentType: false,    //不可缺
            processData: false,    //不可缺
            success:function(data){
            var map=data.content;
            $("#uploadPic"+map.flag).find("img").attr("src",map.url+"?"+Math.random());
            }
        })

    });

});
