$().ready(function() {
	var tableHeight = $('.contents').height() - 225;

	var offline;
	$('#sessionTable').on('draw.dt', function() {
		resetDtWidth();
	}).DataTable({
		"serverSide": true,
		"ajax": {
			"url": CTX + "/sm/session/list",
			"type": "post",
			"data": {
				"perms[0]": "sm_session_offline"
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
		"ordering": true,
		//设置默认排序列
		"order": [
			[3, 'desc']
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
		"rowId": 'sessionId',
		"initComplete": function(settings, json) {
			resetDtWidth();
		},
		"columns": [{
			"data": "principalAccount",
			"width": "200px",
			"orderable": false
		}, {
			"data": "principalHost",
			"width": "200px",
			"orderable": false
		}, {
			"data": "formatUserAgent",
			"width": "150px",
			"orderable": false
		}, {
			"data": "sessionCreateTime",
			"width": "150px",
			"render": function(data, type, row, meta) {
				return (new Date(data)).Format("yyyy-MM-dd hh:mm:ss");
			}
		}, {
			"data": "sessionLastAccessTime",
			"width": "150px",
			"orderable": false,
			"render": function(data, type, row, meta) {
				return (new Date(data)).Format("yyyy-MM-dd hh:mm:ss");
			}
		}, {
			"data": "onlineTime",
			"width": "150px",
			"orderable": false
		}, {
			"data": "formatTimeOut",
			"width": "150px",
			"orderable": false
		}, {
			"data": null,
			"orderable": false,
			"width": "null",
			"className": 'dataTables_toolcolumn',
			"render": function(data, type, row, meta) {
				var content = "";
				if (offline) {
					content = content + "<i class='fa fa-minus-circle text-danger offline' data-toggle='tooltip' data-placement='right' title='强制下线'></i>";
				}
				return content;
			}
		}],

	});
	
	/**当前选中 高亮显示*/
	$("#sessionTable").on("click", "tr", function() {
		$("#sessionTable tbody tr.highlight").each(function(){
			console.log($(this));
			if($(this)!=null){
				$(this).removeClass( 'highlight' );
			}
		});
		$(this).addClass('highlight');
	});
	
	
	/** 搜索 */
	$("#searchBtn").on('click', function() {
		$('#sessionTable').DataTable().column(0).search($("#username").val(), true).draw();
	});

	/** 下线 */
	$("#sessionTable").on("click", ".dataTables_toolcolumn .offline", function() {
		var id = $(this).parents("tr").attr("id");
		swal({
			title: "操作提示!",
			text: "强制下线将会导致该用户退出系统!",
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
					url: CTX + "/sm/session/offline",
					data: {
						"id": id
					},
					type: 'post',
					success: function(message) {
						toastr.success(message);
						$('#sessionTable').DataTable().draw(false);
					},
					error: function(XMLHttpRequest, textStatus, errorThrown) {
						toastr.error(XMLHttpRequest.responseText);
					}
				});
			}
		});
	});
});