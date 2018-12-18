$(function(){
	var tid;
	$(window).resize(function() {
		clearTimeout(tid);
        tid = setTimeout(function() {
        	location.reload();
        }, 1000);
	});
	
	$("#resetPwdForm").on('submit', function() {
		var userName= $(this).find("input[name='userName']").val();
		var newPwd=  $(this).find("input[name='pwd']").val();
		var newPwdTwo = $(this).find("input[name='newPwd']").val();
		if(newPwd.length<6){
			toastr.error("对不起，您输入的密码小于六位，请重新输入！");  
		}else if(newPwdTwo.length<6){
			toastr.error("对不起，您输入的密码小于六位，请重新输入！");
		}
		
		if(newPwd!==newPwdTwo){
			toastr.error("两次密码不一致，请核对后重新输入!");
			return ;
		}
		
		 if(newPwd=="" || newPwd==null){
			toastr.error("新密码不能为空！");
			return ;
		}else if(newPwdTwo=="" || newPwdTwo==null){
			toastr.error("请再次输入您的新密码！");
			return ;
		}
		
		var params = $(this).serialize();
		var url = $(this).attr('action');
		$.ajax({
			url: url,
			data: params,
			type: 'post',
			success: function(message) {
				toastr.success(message);
				window.location.href=CTX+"/logout";
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				toastr.error(XMLHttpRequest.responseText);
			}
		});
		return false;
	});

});


/*function resetPwd(){
	var userName=$('#userName').val();
	var newPwd=$('#pwd').val();
	var newPwdTwo=$('#newpwd').val();
	
	if(newPwd.length<6){
		toastr.error("对不起，您输入的密码小于六位，请重新输入！");  
	}else if(newPwdTwo.length<6){
		toastr.error("对不起，您输入的密码小于六位，请重新输入！");
	}
	
	if(newPwd!==newPwdTwo){
		toastr.error("两次密码不一致，请核对后重新输入!");
		return ;
	}
	
	 if(newPwd=="" || newPwd==null){
		toastr.error("新密码不能为空！");
		return ;
	}else if(newPwdTwo=="" || newPwdTwo==null){
		toastr.error("请再次输入您的新密码！");
		return ;
	}
	 $.ajax({
			url: CTX + "/np/password/resetPassword",
			data: {
				"userName": userName,
				"newPwd": newPwd
			},
			type: 'post',
			success: function(message) {
				toastr.success(message);
				window.location.href=CTX+"/logout";
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				toastr.error(XMLHttpRequest.responseText);
			}
		});

}*/