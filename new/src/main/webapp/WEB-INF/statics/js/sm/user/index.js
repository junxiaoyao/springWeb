$().ready(function() {
	var tableHeight = $('.contents').height() - 225;

	var status, edit, reset;
	$('#userTable').on('draw.dt', function() {
		resetDtWidth();
	}).DataTable({
		"serverSide": true,
		"ajax": {
			"url": CTX + "/sm/user/list",
			"type": "post",
			"data": {
				"perms[0]": "sm_user_status",
				"perms[1]": "sm_user_te",
				"perms[2]": "sm_user_reset"
			},
			"dataSrc": function(json) {
				status = json.perms.sm_user_status;
				edit = json.perms.sm_user_te;
				reset = json.perms.sm_user_reset;
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
		"ordering": true,
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
			"data": "username",
			"width": "90px",
			"orderable": false
		}, {
			"data": "registTime",
			"width": "100px",
			"render": function(data, type, row, meta) {
				return (new Date(data)).Format("yyyy-MM-dd hh:mm:ss");
			}
		}, {
			"data": "loginCount",
			"width": "60px"
		}, {
			"data": "lastLoginTime",
			"width": "120px",
			"orderable": true,
			"render": function(data, type, row, meta) {
				if (data == null) {
					return "还未登录";
				}
				return (new Date(data)).Format("yyyy-MM-dd hh:mm:ss");
			}
		}, {
			"data": "lastLoginIp",
			"width": "80px",
			"orderable": false
		}, {
			"data": "formatLastLoginResource",
			"width": "90px",
			"orderable": false
		}, {
			"data": "lastFailTime",
			"width": "120px",
			"orderable": false,
			"render": function(data, type, row, meta) {
				if (data == null) {
					return "无";
				}
				return (new Date(data)).Format("yyyy-MM-dd hh:mm:ss");
			}
		}, {
			"data": "failCount",
			"width": "90px",
			"orderable": false
		}, {
			"data": "status",
			"width": "60px",
			"orderable": false,
			"render": function(data, type, row, meta) {
				if (data == 0) return "<span class='label label-success'>正常</span>";
				if (data == 1) return "<span class='label label-danger'>已锁定</span>";
				if (data == 2) return "<span class='label label-warning'>审核中</span>";
				if (data == 3) return "<span class='label label-default'>已删除</span>";
			}
		}, {
			"data": null,
			"orderable": false,
			"width": "null",
			"className": 'dataTables_toolcolumn',
			"render": function(data, type, row, meta) {
				var content = "";
				if (status) {
					if (row.status == 0) {
						content = content + "<i class='fa fa-close text-danger disable' data-toggle='tooltip' data-placement='right' title='停用'></i>";
					} else {
						content = content + "<i class='fa fa-check text-success enable' data-toggle='tooltip' data-placement='right' title='启用'></i>";
					}
				}
				if (edit) {
					content = content + "<i class='fa fa-edit text-primary edit' data-toggle='tooltip' data-placement='right' title='编辑'></i>";
				}
				if (reset) {
					content = content + "<i class='fa fa-unlock-alt text-danger reset' data-toggle='tooltip' data-placement='right' title='重置密码'></i>";
				}
				return content;
			}
		}],

	});
	
	/**当前选中 高亮显示*/
	$("#userTable").on("click", "tr", function() {
		$("#userTable tbody tr.highlight").each(function(){
			console.log($(this));
			if($(this)!=null){
				$(this).removeClass( 'highlight' );
			}
		});
		$(this).addClass('highlight');
	});
	
//	/** 搜索 */
//	$("#searchBtn").on('click', function() {
//		$('#userTable').DataTable().column(0).search($("#userName").val(), true).column(1).search($("#realName").val(), true).draw();
//	});

	/** 搜索 */
	$("#searchBtn").on('click', function() {
		$('#userTable').DataTable().column(0).search($("#userName").val(), true).draw();
	});

	
	/** 添加 */
	$("#addBtn").on("click", function() {
		$("#modal").load(CTX + "/sm/user/toAdd");
		$("#modal").modal("show");
	});
	
	/** 导入 */
	$("#inputBtn").on("click", function() {
		$("#modal").load(CTX + "/sm/user/toAdd");
		$("#modal").modal("show");
	});

	/** 禁用 */
	$("#userTable").on("click", ".dataTables_toolcolumn .disable", function() {
		var id = $(this).parents("tr").attr("id");
		swal({
			title: "操作提示!",
			text: "确认要禁用该账号？",
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
					url: CTX + "/sm/user/status",
					data: {
						"id": id,
						"status": 1
					},
					type: 'post',
					success: function(message) {
						toastr.success(message);
						$('#userTable').DataTable().draw(false);
					},
					error: function(XMLHttpRequest, textStatus, errorThrown) {
						toastr.error(XMLHttpRequest.responseText);
					}
				});
			}
		});
	});

	/** 启用 */
	$("#userTable").on("click", ".dataTables_toolcolumn .enable", function() {
		var id = $(this).parents("tr").attr("id");
		$.ajax({
			url: CTX + "/sm/user/status",
			data: {
				"id": id,
				"status": 0
			},
			type: 'post',
			success: function(message) {
				toastr.success(message);
				$('#userTable').DataTable().draw(false);
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				toastr.error(XMLHttpRequest.responseText);
			}
		});
	});

	/** 编辑 */
	$("#userTable").on("click", ".dataTables_toolcolumn .edit", function() {
		var id = $(this).parents("tr").attr("id");
		$("#modal").load(CTX + "/sm/user/toEdit",{"id":id});
		$("#modal").modal("show");
	});

	/** 重置密码 */
	$("#userTable").on("click", ".dataTables_toolcolumn .reset", function() {
		var id = $(this).parents("tr").attr("id");
		swal({
			title: "操作提示!",
			text: "确认要重置该账号的密码？",
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
					url: CTX + "/sm/user/reset",
					data: {
						"id": id
					},
					type: 'post',
					success: function(message) {
						toastr.success(message);
						$('#userTable').DataTable().draw(false);
					},
					error: function(XMLHttpRequest, textStatus, errorThrown) {
						toastr.error(XMLHttpRequest.responseText);
					}
				});
			}
		});
	});
});