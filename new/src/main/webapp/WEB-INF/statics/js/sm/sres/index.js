$().ready(function() {
	var $sresPage = $("#sresPage");
	$sresPage.load(CTX + "/sm/sres/list", {});
	
	/** 禁用 */
	$("#sresPage").on("click", ".treetable .disable", function() {
		var id = $(this).parents("tr").attr("data-tt-id");
		swal({
			title: "操作提示!",
			text: "如果该权限下面包含子权限，将会一起被禁用，确认继续？",
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
					url: CTX + "/sm/sres/status",
					data: {
						"id": id,
						"status": false
					},
					type: 'post',
					success: function(message) {
						toastr.success(message);
						$sresPage.load(CTX + "/sm/sres/list", {});
					},
					error: function(XMLHttpRequest, textStatus, errorThrown) {
						toastr.error(XMLHttpRequest.responseText);
					}
				});
			}
		});
	});

	/** 启用 */
	$("#sresPage").on("click", ".treetable .enable", function() {
		var id = $(this).parents("tr").attr("data-tt-id");
		$.ajax({
			url: CTX + "/sm/sres/status",
			data: {
				"id": id,
				"status": true
			},
			type: 'post',
			success: function(message) {
				toastr.success(message);
				$sresPage.load(CTX + "/sm/sres/list", {});
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				toastr.error(XMLHttpRequest.responseText);
			}
		});
	});

	/** 编辑 */
	$("#sresPage").on("click", ".treetable .edit", function() {
		var id = $(this).parents("tr").attr("data-tt-id");
		$("#modal").load(CTX + "/sm/sres/toEdit",{"id":id});
		$("#modal").modal("show");
	});
	
});