$(function(){
	var tid;
	$(window).resize(function() {
		clearTimeout(tid);
        tid = setTimeout(function() {
        	location.reload();
        }, 1000);
	});

});
function doedit(){
	var oldPwd=$("#oldPassword").val();
	var newPwd=$('#newPassword').val();
	var newPwdTwo=$('#newpwd').val();
	
	if(oldPwd.length<6){
		toastr.error("对不起，您输入的密码小于六位，请重新输入！");  
	}else if(newPwd.length<6){
		toastr.error("对不起，您输入的密码小于六位，请重新输入！");
	}else if(newPwdTwo.length<6){
		toastr.error("对不起，您输入的密码小于六位，请重新输入！");
	}
	
	if(newPwd!==newPwdTwo){
		toastr.error("两次密码不一致，请核对后重新输入!");
		return ;
	}
	
	if(oldPwd == newPwd){
		toastr.error("新密码不能与旧密码一致！");
			return;
	}else if(oldPwd=="" || oldPwd==null){
		toastr.error("旧密码不能为空！");
		return ;
	}else if(newPwd=="" || newPwd==null){
		toastr.error("新密码不能为空！");
		return ;
	}else if(newPwdTwo=="" || newPwdTwo==null){
		toastr.error("请再次输入您的新密码!");
		return ;
	}
	
	$.ajax({
		url: CTX + "/bt/ia/updatePwd",
		data: {
			"oldPwd": oldPwd,
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

	
	/*$.get(CTX+"/bt/ia/updatePwd",param,function(data){
		if(data==-1){
			alert("您输入的原密码有误，请重新输入");
		}else if(data==1){
			alert("密码修改成功！");
			window.location.href=CTX+'/logout';
		}
	});*/
}