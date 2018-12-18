$().ready(function() {
	var tableHeight = $('.contents').height() - 225;
	var edit;
	$('#orgTable').on('draw.dt', function() {
		resetDtWidth();
	}).DataTable({
		"serverSide": true,
		"ajax": {
			"url": CTX + "/bt/org/org/list",
			"type": "post",
			"data": {
				"perms[0]": "bt_org_org_status",
				"perms[1]": "bt_org_org_te",
			},
			"dataSrc": function(json) {
				status = json.perms.bt_org_org_status;
				edit = json.perms.bt_org_org_te;
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
			[5, 'asc']
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
		"columns": [ {
			"data": "id",
			"width": "null",
			"visible": false
		},
		{
			"data": "fullName",
			"width": "220px",
			"orderable": false
		}, 
		{
			"data": "shortName",
			"width": "100px",
			"orderable": false
		},
		{
			"data": "address",
			"width": "200px",
			"orderable": false
		},  {
			"data": "orgCode",
			"width": "100px",
			"orderable": false
		},  {
			"data": "establishTime",
			"width": "130px",
			"render": function(data, type, row, meta) {
				return (new Date(data)).Format("yyyy-MM-dd");
			}
		},  {
			"data": "industry",
			"width": "60px",
			"orderable": false
		}, {
			"data": "status",
			"width": "60px",
			"orderable": false,
			"render": function(data, type, row, meta) {
				if (data == 0) return "<span class='label label-success'>正常</span>";
				if (data == 1) return "<span class='label label-danger'>待审核</span>";
				if (data == 2) return "<span class='label label-warning'>已锁定</span>";
			}
		}, {
			"data": null,
			"orderable": false,
			"width": "150px",
			"className": 'dataTables_toolcolumn',
			"render": function(data, type, row, meta) {
				var content = "";
				if (status) {
					if (row.status == 0) {
						content = content + "<i class='fa fa-lock text-danger disable' data-toggle='tooltip' data-placement='right' title='停用'></i>";
					} else if(row.status == 2) {
						content = content + "<i class='fa fa-check text-success enable' data-toggle='tooltip' data-placement='right' title='启用'></i>";
					}else{
						content = content + "<i class='fa fa-paper-plane text-warning check' data-toggle='tooltip' data-placement='right' title='审核'></i>";
					}
				}
				content = content + "<i class='fa fa-edit text-primary edit' data-toggle='tooltip' data-placement='right' title='编辑'></i>";
				return content;
			}
		}],

	});
	/**当前选中 高亮显示*/
	$("#orgTable").on("click", "tr", function() {
		$("#orgTable tbody tr.highlight").each(function(){
			console.log($(this));
			if($(this)!=null){
				$(this).removeClass( 'highlight' );
			}
		});
		$(this).addClass('highlight');
	});
	/** 搜索 */
	$("#searchBtn").on('click', function() {
		$('#orgTable').DataTable().column(1).search($("#fullName").val(), true).column(2).search($("#orgCode").val(), true).draw();
	});
	
	/** 添加 */
	$("#addBtn").on("click", function() {
		$("#modal").load(CTX + "/bt/org/org/toAdd");
		$("#modal").modal("show");
	});
	
	/** 禁用 */
	$("#orgTable").on("click", ".dataTables_toolcolumn .disable", function() {
		var id = $(this).parents("tr").attr("id");
		swal({
			title: "操作提示!",
			text: "确认要禁用该机构？",
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
					url: CTX + "/bt/org/org/status",
					data: {
						"id": id,
						"status": 2
					},
					type: 'post',
					success: function(message) {
						toastr.success(message);
						$('#orgTable').DataTable().draw(false);
					},
					error: function(XMLHttpRequest, textStatus, errorThrown) {
						toastr.error(XMLHttpRequest.responseText);
					}
				});
			}
		});
	});

	/** 启用 */
	$("#orgTable").on("click", ".dataTables_toolcolumn .enable", function() {
		var id = $(this).parents("tr").attr("id");
		$.ajax({
			url: CTX + "/bt/org/org/status",
			data: {
				"id": id,
				"status": 0
			},
			type: 'post',
			success: function(message) {
				toastr.success(message);
				$('#orgTable').DataTable().draw(false);
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				toastr.error(XMLHttpRequest.responseText);
			}
		});
	});
	
	/** 审核 */
	$("#orgTable").on("click", ".dataTables_toolcolumn .check", function() {
		var id = $(this).parents("tr").attr("id");
		$("#modal").load(CTX + "/bt/org/org/check",{"id":id});
		$("#modal").modal("show");
	});

	/** 编辑 */
	$("#orgTable").on("click", ".dataTables_toolcolumn .edit", function() {
		var id = $(this).parents("tr").attr("id");
		$("#modal").load(CTX + "/bt/org/org/toEdit",{"id":id});
		$("#modal").modal("show");
	});
	
});