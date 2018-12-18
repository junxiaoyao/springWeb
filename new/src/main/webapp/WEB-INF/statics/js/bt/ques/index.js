$().ready(function() {
    var tableHeight = $('.contents').height() - 225;



    var offline;
    $('#questionnaireTable').on('draw.dt', function() {
        resetDtWidth();
    }).DataTable({
        "serverSide": true,
        "ajax": {
            "url": CTX + "/bt/ques/listvo",
            "type": "post",
            "data": {
                "perms[0]": "stem_update",
                "perms[1]": "stem_add",
                "perms[2]": "select_deploy"

            },
            "dataSrc": function(json) {
                offline = json.perms.sm_session_offline;
                return json.data;
            }
        },
        //页码样式
        "pagingType": "full_numbers",
        //默认表格记录数
        "pageLength": 18,
        //开启分页
        "paging": true,
        //表格最大高度
        "scrollY": tableHeight,
        //当显示更少的记录时，是否允许表格减少高度
        "scrollCollapse": true,
        //开启自定义表格记录数功能
        "lengthChange": false,
        //开启搜索功能
        "searching": true,
        //"searchDelay": 800,
        //开启排序功能
        "ordering": false,
        //设置默认排序列
        "order": [
            [3, 'asc']
        ],
        "processing": true,
        "autoWidth": false,
        "info": true,
        "language": {
            "info": "当前第_PAGE_页,共_PAGES_页",
            "infoEmpty": "无分页信息",
            "paginate": {
                "first": "<<",
                "previous": "<",
                "next": ">",
                "last": ">>"
            },
            "lengthMenu": '每页显示<select>' + '<option value="10">10</option>' + '<option value="10">15</option>' + '<option value="20">20</option>' + '<option value="30">30</option>' + '<option value="40">40</option>' + '<option value="50">50</option>' + '<option value="-1">所有</option>' + '</select>条记录',
            "search": "全局搜索:",
            "searchPlaceholder": "Search...",
            "processing": "加载中...",
            "emptyTable": "没有相关数据"
        },
        "rowId": 'id',
        "initComplete": function(settings, json) {
            resetDtWidth();
        },
        "columns": [{
            "data": "theme",
            "width": "null",
            "orderable": false
        },
            {
                "data": "title",
                "width": "null",
                "orderable": false
            },
			{
            "data": "createTime",
            "width": "100px",
            "render": function(data, type, row, meta) {
                return (new Date(data)).Format("yyyy-MM-dd hh:mm:ss");
            }
        }, {
            "data": "userName",
            "width": "100px"
        }, {
            "data": "",
            "orderable": false,
            "width": "null",
            "className": 'dataTables_toolcolumn',
            "render": function(data, type, row, meta) {
                var content = "";
                content = content + "<i class='fa fa-edit text-primary edit' data-toggle='tooltip' data-placement='right' title='编辑'></i>";
                content = content + " <i class='fa fa-close text-danger disable' data-toggle='tooltip' data-placement='right' title='删除'></i>";
                return content;
            }
        }],

    });

    /**当前选中 高亮显示*/
    $("#questionnaireTable").on("click", "tr", function() {
        $("#questionnaireTable tbody tr.highlight").each(function(){
            console.log($(this));
            if($(this)!=null){
                $(this).removeClass( 'highlight' );
            }
        });
        $(this).addClass('highlight');
    });

    /** 搜索 */
    $("#searchBtn").on('click', function() {
        $('#questionnaireTable').DataTable().column(0).search($("#username").val(), true).column(1).search($("#realName").val(), true).draw();
    });

    /** 添加 */
    $("#addBtn").on("click", function() {
        $("#modal").load(CTX + "/bt/ques/addView");
        $("#modal").modal("show");
    });

    /** 导入 */
    $("#inputBtn").on("click", function() {
        $("#modal").load(CTX + "/sm/user/toAdd");
        $("#modal").modal("show");
    });

    /** 禁用 */
    $("#questionnaireTable").on("click", ".dataTables_toolcolumn .disable", function() {
        var id = $(this).parents("tr").attr("id");
        swal({
            title: "操作提示!",
            text: "确认要删除该题干吗？",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "是的, 继续!",
            cancelButtonText: "让我再考虑一下!",
            closeOnConfirm: true,
            closeOnCancel: true
        }, function(isConfirm) {
            if (isConfirm) {
                $.ajax({
                    url: CTX + "/bt/stem/delete",
                    data: {
                        "id": id
                    },
                    type: 'get',
                    success: function(message) {
                        toastr.success(message);
                        $('#questionnaireTable').DataTable().draw(false);
                    },
                    error: function(XMLHttpRequest, textStatus, errorThrown) {
                        toastr.error(XMLHttpRequest.responseText);
                    }
                });
            }
        });
    });
    /** 编辑 */
    $("#questionnaireTable").on("click", ".dataTables_toolcolumn .edit", function() {
        var id = $(this).parents("tr").attr("id");
        $("#modal").load(CTX + "/bt/ques/editView",{"id":id});
        $("#modal").modal("show");
    });
});