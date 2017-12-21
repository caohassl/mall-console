<!DOCTYPE html>
<html>
<head>
    <title>API管理平台</title>
<#import "/common/common.macro.ftl" as netCommon>
    <link rel="stylesheet" href="${request.contextPath}/static/adminlte/plugins/datatables/dataTables.bootstrap.css">
<@netCommon.commonStyle />
    <!-- DataTables -->


</head>
<body>
    <!-- header -->
    <!-- left -->

    <!-- Content Wrapper. Contains page content -->
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>商品基本信息维护<small>API管理平台</small></h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">

                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">商品列表</h3>
                            <button class="btn btn-info btn-xs pull-right" id="add" >+新增商品</button>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body">
                            <table id="goods_list" class="table table-bordered table-striped">
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>商品编号</th>
                                    <th>操作</th>
                                    <th>商品名称</th>
                                    <th>商品规格</th>
                                    <th>价格</th>
                                    <th>库量</th>
                                    <th>商品状态</th>
                                    <th>活动价格</th>
                                    <th>商品描述</th>
                                    <th>插入时间</th>
                                    <th>更新时间</th>
                                </tr>
                                </thead>
                            </table>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->

                </div>
            </div>
        </section>

<!-- 新增--编辑.模态框 -->
<div class="modal fade" id="addModal" tabindex="-1" role="dialog"  aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" >新增商品</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal form" role="form" >
                    <div class="form-group">
                        <label for="lastname" class="col-sm-2 control-label">商品名称<font color="red">*</font></label>
                        <div class="col-sm-4"><input type="text" class="form-control" name="goodsName" placeholder="请输入商品名称" maxlength="20" ></div>
                        <label for="lastname" class="col-sm-2 control-label">商品规格<font color="red">*</font></label>
                        <div class="col-sm-4"><input type="text" class="form-control" name="goodsSpec" placeholder="请输入商品规格" maxlength="20" ></div>
                    </div>

                    <div class="form-group">
                        <label for="lastname" class="col-sm-2 control-label">商品价格<font color="red">*</font></label>
                        <div class="col-sm-4"><input type="text" class="form-control" name="goodsPrice" placeholder="请输入商品价格" ></div>
                        <label for="lastname" class="col-sm-2 control-label">商品库存<font color="red">*</font></label>
                        <div class="col-sm-4"><input type="text" class="form-control" name="goodsAmount" placeholder="请输入商品库存" ></div>
                    </div>

                    <div class="form-group">
                        <label for="lastname" class="col-sm-2 control-label">活动价格</label>
                        <div class="col-sm-4"><input type="text" class="form-control" name="promotionGoodsPrice" placeholder="请输入商品活动价格"></div>
                        <label for="lastname" class="col-sm-2 control-label">商品描述</label>
                        <div class="col-sm-4"><input type="text" class="form-control" name="goodsDesc" placeholder="请输入商品描述"  ></div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-6">
                            <button type="submit" class="btn btn-primary"  >保存</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            <input type="hidden" name="id"/>
                        </div>
                    </div>

                </form>
            </div>
        </div>
    </div>
</div>


<!-- 上传图片.模态框 -->
<div class="modal fade" id="upLoadPicModal" tabindex="-1" role="dialog"  aria-hidden="true">
    <div class="modal-dialog <#--modal-lg-->">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" >商品上传图片--<span id="goodsName"></span></h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal form" role="form" >
                    <#--<div class="form-group">-->
                        <#--<label for="lastname" class="col-sm-3 control-label">商品编号</label>-->
                        <#--<div class="col-sm-3"><input type="text" class="form-control" name="goodsId" placeholder="请输入商品名称" maxlength="20" ></div>-->
                        <#--<label for="lastname" class="col-sm-3 control-label">商品名称</label>-->
                        <#--<div class="col-sm-3"><input type="text" class="form-control" name="goodsName" placeholder="请输入商品规格" maxlength="20" ></div>-->
                    <#--</div>-->

                    <div class="form-group">
                        <label for="lastname" class="col-sm-3 control-label" id="width">商品图片一</label>
                        <div class="col-sm-9"><input type="file" class="form-control"  name="uploadFile" flag="1" placeholder="商品图片一"></div>
                    </div>
                    <div class="form-group">
                        <label for="lastname" class="col-sm-3 control-label">商品图片二</label>
                        <div class="col-sm-9"><input type="file" class="form-control" name="uploadFile" flag="2" placeholder="商品图片二"></div>
                    </div>
                    <div class="form-group">
                        <label for="lastname" class="col-sm-3 control-label">商品图片三</label>
                        <div class="col-sm-9"><input type="file" class="form-control" name="uploadFile" flag="3" placeholder="商品图片三"></div>
                    </div>
                    <div class="form-group">
                        <label for="lastname" class="col-sm-3 control-label">商品图片四</label>
                        <div class="col-sm-9"><input type="file" class="form-control" name="uploadFile" flag="4" placeholder="商品图片四"  ></div>
                    </div>
                    <div class="form-group">

                        <div   id="uploadPic1" class="picDiv"><img /></div>
                        <div   id="uploadPic2" class="picDiv"><img /></div>
                        <div   id="uploadPic3" class="picDiv"><img ></div>
                        <div   id="uploadPic4" class="picDiv"><img /></div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-6">
                            <button type="button" class="btn btn-info picSubmit"  >保存</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            <input type="hidden" id="goodsId"/>
                        </div>
                    </div>

                </form>
            </div>
        </div>
    </div>
</div>

<@netCommon.commonScript />
<!-- DataTables -->
<script src="${request.contextPath}/static/adminlte/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="${request.contextPath}/static/adminlte/plugins/datatables/dataTables.bootstrap.min.js"></script>
<script src="${request.contextPath}/static/plugins/jquery/jquery.validate.min.js"></script>
<!-- moment -->
<script src="${request.contextPath}/static/adminlte/plugins/daterangepicker/moment.min.js"></script>

<script>
    goodsList = eval('('+ '${goodsList}' +')');
</script>
<script src="${request.contextPath}/static/js/goods.js"></script>
</body>
</html>
