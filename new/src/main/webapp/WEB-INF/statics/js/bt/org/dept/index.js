$().ready(function() {
	var tableHeight = $('.contents').height() - 225;

	var edit, instruct;
	$('#orgDeptTable').on('draw.dt', function() {
		resetDtWidth();
	}).DataTable({
		"serverSide": true,
		"ajax": {
			"url": CTX + "/bt/org/dept/list",
			"type": "post",
			"data": {
				"perms[0]": "bt_org_dept_isValid",
				"perms[1]": "bt_org_dept_te",
			},
			"dataSrc": function(json) {
				isValid = json.perms.bt_org_dept_isValid;
				edit = json.perms.bt_org_dept_te;
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
		"columns": [ {
			"data": "id",
			"width": "null",
			"visible": false
		},{
			"data": "name",
			"width": "300px",
			"orderable": false
		},{
			"data": "orgId",
			"width": "null",
			"orderable": false
		},  {
			"data": "orgName",
			"width": "300px",
			"orderable": false
		}, {
			"data": "isValid",
			"width": "70px",
			"orderable": false,
			"render": function(data, type, row, meta) {
				if (data == true) return "<span class='label label-success'>启用</span>";
				return "<span class='label label-danger'>停用</span>";
			}
		},{
			"data": null,
			"orderable": false,
			"width": "null",
			"className": 'dataTables_toolcolumn',
			"render": function(data, type, row, meta) {
				var content = "";
				if (row.isValid == true) {
					content = content + "<i class='fa fa-close text-danger disable' data-toggle='tooltip' data-placement='right' title='停用'></i>";
				} else {
					content = content + "<i class='fa fa-check text-success enable' data-toggle='tooltip' data-placement='right' title='启用'></i>";
				}
				content = content + "<i class='fa fa-edit text-primary edit' data-toggle='tooltip' data-placement='right' title='编辑'></i>";
				return content;
			}
		}],
	});
	/**当前选中 高亮显示*/
	$("#orgDeptTable").on("click", "tr", function() {
		$("#orgDeptTable tbody tr.highlight").each(function(){
			console.log($(this));
			if($(this)!=null){
				$(this).removeClass( 'highlight' );
			}
		});
		$(this).addClass('highlight');
	});
	/** 搜索 */
	$("#searchBtn").on('click', function() {
		$('#orgDeptTable').DataTable().column(1).search($("#name").val(), true).column(3).search($("#orgId").val(), false).draw();
	});
	
	/** 添加 */
	$("#addBtn").on("click", function() {
		$("#modal").load(CTX + "/bt/org/dept/toAdd");
		$("#modal").modal("show");
	});

	/** 禁用 */
	$("#orgDeptTable").on("click", ".dataTables_toolcolumn .disable", function() {
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
					url: CTX + "/bt/org/dept/isValid",
					data: {
						"id": id,
						"isValid": false
					},
					type: 'post',
					success: function(message) {
						toastr.success(message);
						$('#orgDeptTable').DataTable().draw(false);
					},
					error: function(XMLHttpRequest, textStatus, errorThrown) {
						toastr.error(XMLHttpRequest.responseText);
					}
				});
			}
		});
	});

	/** 启用 */
	$("#orgDeptTable").on("click", ".dataTables_toolcolumn .enable", function() {
		var id = $(this).parents("tr").attr("id");
		alert(id);
		$.ajax({
			url: CTX + "/bt/org/dept/isValid",
			data: {
				"id": id,
				"isValid": true
			},
			type: 'post',
			success: function(message) {
				toastr.success(message);
				$('#orgDeptTable').DataTable().draw(false);
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				toastr.error(XMLHttpRequest.responseText);
			}
		});
	});

	/** 编辑 */
	$("#orgDeptTable").on("click", ".dataTables_toolcolumn .edit", function() {
		var id = $(this).parents("tr").attr("id");
		$("#modal").load(CTX + "/bt/org/dept/toEdit",{"id":id});
		$("#modal").modal("show");
	});
	
});