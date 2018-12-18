
$().ready(function() {
	
	/**当前选中 高亮显示*/
	$("#cacheTable").on("click", "tr", function() {
		$("#cacheTable tbody tr.highlight").each(function(){
			console.log($(this));
			if($(this)!=null){
				$(this).removeClass( 'highlight' );
			}
		});
		$(this).addClass('highlight');
	});
	
	/** 清空缓存 */
	$("#cacheTable").on("click", ".offline", function() {
		var $this = $(this),
		datas = $this.data(),
		region = datas.region;
		swal({
			title: "操作提示!",
			text: "即将清空该区域缓存!",
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
					url: CTX + "/sm/cache/clearCache",
					data: {
						"region": region
					},
					type: 'post',
					success: function(message) {
						toastr.success(message);
						$("[data-region="+region+"]").parent().prev().html(message);
					},
					error: function(XMLHttpRequest, textStatus, errorThrown) {
						toastr.error(XMLHttpRequest.responseText);
					}
				});
			}
		});
	});
	
	$("#clearAllCache").on("click",function(){
		window.location.href=CTX + "/sm/cache/clearAllCache";
	//	 $.get(CTX + "/sm/cache/clearAllCache");
	});
	
});