<#include "/include/macros.ftl">
<@header></@header>
<div class="clearfix"></div>
<div class="row">
    <div class="col-md-12 col-sm-12 col-xs-12">
        <@breadcrumb>
            <ol class="breadcrumb">
                <li><a href="/">首页</a></li>
                <li class="active">日志管理</li>
            </ol>
        </@breadcrumb>
        <div class="x_panel">
            <div class="x_content">
                <div class="<#--table-responsive-->">
                    <div class="btn-group hidden-xs" id="toolbar">
                        <@shiro.hasPermission name="log:batchDelete">
                            <button id="btn_delete_ids" type="button" class="btn btn-danger" title="删除选中">
                                <i class="fa fa-trash-o fa-fw"></i>
                            </button>
                        </@shiro.hasPermission>
                    </div>
                    <table id="tablelist">
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<@addOrUpdateMOdal defaultTitle="查看日志">
    <input type="hidden" name="id">
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="username">用户名 <span class="required">*</span></label>
        <div class="col-md-6 col-sm-6 col-xs-12">
            <input type="text" class="form-control col-md-7 col-xs-12" name="username" id="username" required="required" placeholder="请输入用户名"/>
        </div>
    </div>
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="password">密码 </label>
        <div class="col-md-6 col-sm-6 col-xs-12">
            <input type="password" class="form-control col-md-7 col-xs-12" id="password" name="password" placeholder="请输入密码 6位以上"/>
        </div>
    </div>
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="nickname">昵称 </label>
        <div class="col-md-6 col-sm-6 col-xs-12">
            <input type="text" class="form-control col-md-7 col-xs-12" name="nickname" id="nickname" placeholder="请输入昵称"/>
        </div>
    </div>
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="mobile">手机 </label>
        <div class="col-md-6 col-sm-6 col-xs-12">
            <input type="tel" class="form-control col-md-7 col-xs-12" name="mobile" id="mobile" data-validate-length-range="6,20" placeholder="请输入手机号"/>
        </div>
    </div>
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">邮箱 </label>
        <div class="col-md-6 col-sm-6 col-xs-12">
            <input type="email" class="form-control col-md-7 col-xs-12" name="email" id="email" placeholder="请输入邮箱"/>
        </div>
    </div>
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="qq">QQ </label>
        <div class="col-md-6 col-sm-6 col-xs-12">
            <input type="number" class="form-control col-md-7 col-xs-12" name="qq" id="qq" placeholder="请输入QQ"/>
        </div>
    </div>
</@addOrUpdateMOdal>
<@footer>
    <script>
        /**
         * 操作按钮
         * @param code
         * @param row
         * @param index
         * @returns {string}
         */
        function operateFormatter(code, row, index) {
            var currentLogId = '${log.id}';
            var trLogId = row.id;
            var operateBtn = [
                '<@shiro.hasPermission name="log:edit"><a class="btn btn-sm btn-success btn-update" data-id="' + trLogId + '"title="编辑"><i class="fa fa-edit fa-fw"></i></a></@shiro.hasPermission>',
            ];
            operateBtn.push('<@shiro.hasPermission name="log:delete"><a class="btn btn-sm btn-danger btn-remove" data-id="' + trLogId + '"title="删除"><i class="fa fa-trash-o fa-fw"></i></a></@shiro.hasPermission>');
            return operateBtn.join('');
        }

        $(function () {
            var options = {
                url: "/log/list",
                getInfoUrl: "/log/get/{id}",
                updateUrl: "/log/edit",
                removeUrl: "/log/remove",
                createUrl: "/log/add",
                columns: [
                    {
                        checkbox: true
                    }, {
                        field: 'id',
                        title: 'ID',
                        formatter: function (code) {
                            return code ? code : '-';
                        }
                    }, {
                        field: 'logId',
                        title: '用户ID',
                        formatter: function (code) {
                            return code ? code : '-';
                        }
                    }, {
                        field: 'type',
                        align: 'center',
                        title: '用户类型',
                        formatter: function (code) {
                            return code ? code : '-';
                        }
                    }, {
                        field: 'content',
                        title: '日志内容',
                        formatter: function (code) {
                            return code ? code : '-';
                        }
                    }, {
                        field: 'spiderType',
                        title: 'qq',
                        formatter: function (code) {
                            return code ? code : '-';
                        }
                    }, {
                        field: 'logType',
                        title: '用户类型',
                        align: 'center',
                        formatter: function (code) {
                            return code ? code : '-';
                        }
                    }, {
                        field: 'os',
                        title: '系统类型',
                        align: 'center',
                        formatter: function (code) {
                            return code ? code : '-';
                        }
                    }, {
                        field: 'browser',
                        title: '浏览器类型',
                        align: 'center',
                        formatter: function (code) {
                            return code ? code : '-';
                        }
                    }, {
                        field: 'requestUrl',
                        title: '请求URl',
                        align: 'center',
                        formatter: function (code) {
                            return code ? code : '-';
                        }
                    }, {
                        field: 'createTime',
                        title: '访问时间',
                        align: 'center',
                        formatter: function (code) {
                            return new Date(code).format("yyyy-MM-dd hh:mm:ss")
                        }
                    },{
                        field: 'operate',
                        title: '操作',
                        align: "center",
                        width: '150px',
                        formatter: operateFormatter //自定义方法，添加操作按钮
                    }
                ],
                modalName: "日志"
            };
            // 初始化table组件
            var table = new Table(options);
            table.init();

        });
    </script>
</@footer>