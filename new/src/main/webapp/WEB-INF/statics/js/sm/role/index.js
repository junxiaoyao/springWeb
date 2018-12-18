$().ready(function() {
	var tableHeight = $('.contents').height() - 225;
	var status, edit;
	$('#roleTable').on('draw.dt', function() {
		resetDtWidth();
	}).DataTable({
		"serverSide": true,
		"ajax": {
			"url": CTX + "/sm/role/list",
			"type": "post",
			"data": {
				"perms[0]": "sm_role_status",
				"perms[1]": "sm_role_te"
			},
			"dataSrc": function(json) {
				status = json.perms.sm_role_status;
				edit = json.perms.sm_role_te;
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
			[2, 'asc']
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
			"data": "name",
			"width": "200px",
			"orderable": false
		}, {
			"data": "key",
			"width": "200px",
			"orderable": false
		}, {
			"data": "createTime",
			"width": "110px",
			"render": function(data, type, row, meta) {
				return (new Date(data)).Format("yyyy-MM-dd hh:mm:ss");
			}
		}, {
			"data": "description",
			"width": "null",
			"orderable": false
		}, {
			"data": "isBuiltIn",
			"width": "80px",
			"orderable": false,
			"render": function(data, type, row, meta) {
				if (data == true) return "<span class='label label-danger'>内置角色</span>";
				return "自建角色";
			}
		}, {
			"data": "isValid",
			"width": "100px",
			"orderable": false,
			"render": function(data, type, row, meta) {
				if (data == true) return "<span class='label label-success'>可用</span>";
				return "<span class='label label-danger'>不可用</span>";
			}
		}, {
			"data": null,
			"orderable": false,
			"width": "150px",
			"className": 'dataTables_toolcolumn',
			"render": function(data, type, row, meta) {
				if (row.isBuiltIn) {
					return "";
				}
				var content = "";
				if (status) {
					if (row.isValid) {
						content = content + "<i class='fa fa-close text-danger disable' data-toggle='tooltip' data-placement='right' title='禁用'></i>";
					} else {
						content = content + "<i class='fa fa-check text-success enable' data-toggle='tooltip' data-placement='right' title='启用'></i>";
					}
				}
				if (edit) {
					content = content + "<i class='fa fa-edit text-primary edit' data-toggle='tooltip' data-placement='right' title='编辑'></i>";
				}
				return content;
			}
		}]
	});
	
	/**当前选中 高亮显示*/
	$("#roleTable").on("click", "tr", function() {
		$("#roleTable tbody tr.highlight").each(function(){
			console.log($(this));
			if($(this)!=null){
				$(this).removeClass( 'highlight' );
			}
		});
		$(this).addClass('highlight');
	});
	
	/** 搜索*/
	$("#searchBtn").on('click', function() {
		$('#roleTable').DataTable().column(1).search($("#name").val(), true).draw();
	});

	/** 新增*/
	$("#addBtn").on('click', function() {
		$("#modal").load(CTX + "/sm/role/toAdd",function(){
			$("#modal").modal("show");
		});
	});

	/** 禁用*/
	$("#roleTable").on("click", ".dataTables_toolcolumn .disable", function() {
		var id = $(this).parents("tr").attr("id");
		swal({
			title: "操作提示!",
			text: "确认禁用该角色?",
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
					url: CTX + "/sm/role/status",
					data: {
						"id": id,
						"isValid": false
					},
					type: 'post',
					success: function(message) {
						toastr.success(message);
						$('#roleTable').DataTable().draw(false);
					},
					error: function(XMLHttpRequest, textStatus, errorThrown) {
						toastr.error(XMLHttpRequest.responseText);
					}
				});
			}
		});
	});

	/** 启用*/
	$("#roleTable").on("click", ".dataTables_toolcolumn .enable", function() {
		var id = $(this).parents("tr").attr("id");
		$.ajax({
			url: CTX + "/sm/role/status",
			data: {
				"id": id,
				"isValid": true
			},
			type: 'post',
			success: function(message) {
				toastr.success(message);
				$('#roleTable').DataTable().draw(false);
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				toastr.error(XMLHttpRequest.responseText);
			}
		});
	});

	/** 编辑*/
	$("#roleTable").on("click", ".dataTables_toolcolumn .edit", function() {
		var id = $(this).parents("tr").attr("id");
		$("#modal").load(CTX + "/sm/role/toEdit", {
			"id": id
		},function(){
			$("#modal").modal("show");
		});
	});

});