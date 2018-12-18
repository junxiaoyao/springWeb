$().ready(function(){
	$('.glyphicon-remove').on('click',function(){
		var id = $(this).attr('data-id')
		var that = $(this).parent()
		DeleteMsg(id,that)
	})
})

function DeleteMsg(id,that){
	$.ajax({
		type:'post',
		dataType:'json',
		url:CTX+'/evaluation/readInformation',
		data:{infoId:id},
		success:function(data){
			toastr.success('操作成功')
			that.hide()
		},
		error:function(){
			toastr.error('操作失败')
		}
	})
}