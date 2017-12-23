<!DOCTYPE html>
<html>
<head>
    <title>MALL管理平台</title>
<#import "/common/common.macro.ftl" as netCommon>
    <link rel="stylesheet" href="${request.contextPath}/static/adminlte/plugins/datatables/dataTables.bootstrap.css">
<@netCommon.commonStyle />
    <!-- DataTables -->
</head>
<body class="hold-transition skin-blue sidebar-mini <#if cookieMap?exists && "off" == cookieMap["adminlte_settings"].value >sidebar-collapse</#if>">
<div class="wrapper">
    <!-- header -->
<@netCommon.commonHeader />
    <!-- left -->
<@netCommon.commonLeft "login" />

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->

        <iframe id="menuFrame" name="menuFrame" src="" style="overflow:visible;" scrolling="yes" frameborder="no" height="100%!important" width="100%">
        </iframe>
    </div>
    <!-- footer -->
<@netCommon.commonFooter />
</div>

<@netCommon.commonScript />
<!-- DataTables -->
<script src="${request.contextPath}/static/adminlte/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="${request.contextPath}/static/adminlte/plugins/datatables/dataTables.bootstrap.min.js"></script>
<script src="${request.contextPath}/static/plugins/jquery/jquery.validate.min.js"></script>
<!-- moment -->
<script src="${request.contextPath}/static/adminlte/plugins/daterangepicker/moment.min.js"></script>
<script src="${request.contextPath}/static/js/index.js"></script>
</body>
</html>
