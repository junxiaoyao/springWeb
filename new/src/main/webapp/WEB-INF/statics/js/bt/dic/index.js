$().ready(function() {
	
	var $dataPage = $('#dataPage');
	
	/**页面初始化*/
	$dataPage.load(CTX + "/bt/dictionary/target");
	
	/** 搜索 */
	$("#searchBtn").on('click', function() {
		$('#userTable').DataTable().column(0).search($("#targetName").val(), true).draw();
	});

	/** 添加 */
	$("#addBtn").on("click", function() {
		$("#modal").load(CTX + "/bt/dictionary/toAdd");
		$("#modal").modal("show");
	});
	
	/** 导入 */
	$("#inputBtn").on("click", function() {
		$("#modal").load(CTX + "/bt/dictionary/toAdd");
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
					url: CTX + "/bt/dictionary/status",
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
			url: CTX + "/bt/dictionary/status",
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
		$("#modal").load(CTX + "/bt/dictionary/toEdit",{"id":id});
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
					url: CTX + "/bt/dictionary/reset",
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