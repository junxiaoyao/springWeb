$().ready(function(){
		$('body').on('click','.history_item',function(){
			var id = $(this).attr('data-id')
			window.location.href = CTX+'/evaluation/showQuesState?taskId='+id
		})
	})