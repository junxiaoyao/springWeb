$(function(){
	var tid;
	$(window).resize(function() {
		clearTimeout(tid);
        tid = setTimeout(function() {
        	location.reload();
        }, 1000);
	});
	
	$("#backPwdForm").on('submit', function() {
		var userName= $(this).find("input[name='userName']").val();
		if(userName=="" || userName==null){
			toastr.error('请输入您要找回密码的账号！');
			return false;
		}
		
		var params = $(this).serialize();
		var url = $(this).attr('action');
		$.ajax({
			url: url,
			data: params,
			type: 'post',
			success: function(message) {
				toastr.success(message);
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				toastr.error(XMLHttpRequest.responseText);
			}
		});
		return false;
	});

});

/*function findPwd(){
	var userName=$('#userName').val();
	if(userName=="" || userName==null){
		toastr.warning('请输入您要找回密码的账号！');
		return ;
	}
	$.ajax({
		type:'post',
		url:CTX+"/np/password/backPassword",
		data:{
			"userName":userName
			},
			success:function(message){
				toastr.success(message);
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				toastr.error(XMLHttpRequest.responseText);
		}
	});
}*/