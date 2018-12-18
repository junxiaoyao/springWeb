/// <reference path="../../jquery/jquery.d.ts" />
function getQueryString(name) { 
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
        var r = window.location.search.substr(1).match(reg); 
        if (r != null) return unescape(r[2]); 
        return null; 
    } 
$(document).ready(function(){
	var phoneNumber = getQueryString("p");
	$("#phone").val(phoneNumber);
	if(msg){
    	toastr.error(msg)
	}
	//消除300毫秒延迟
	FastClick.attach(document.body);
  //手机号验证让验证码按钮变为可点
//  $('.phone_box input').on('input',function(){
//    var phone = $(this).val()
//    if((/^1[34578]\d{9}$/.test(phone))){ 
//      $('.getCode').removeAttr('disabled').removeClass('disabled');
//    } 
//    else{
//      $('.getCode').attr('disabled','disabled').addClass('disabled');
//    }
//  })
	$('.getCode').on('click',function(){
		var phone = $('.phone_box input').val()
		if((/^1[34578]\d{9}$/.test(phone))){ 
			$.ajax({
				url:CTX+'/evaluation/login',
				headers:{
					"content-Type":"application/json"
				},
				data:JSON.stringify({phone:phone}),
				type:'post',
				success:function(data){
					console.log(data)
				}
				
			})
		} 
	    else{
	    	toastr.error('请输入正确的手机号')
	    }
		})
  $('.login_btn').on('click',function(){
    var phone = $('.phone_box input').val()
    var code = $('.code_box input').val();
    if((/^1[34578]\d{9}$/.test(phone))){ 
        if(code!=''){
        	$.ajax({
				url:CTX+'/evaluation/login',
				headers:{
				},
				data:{phoneNum:phone,verCode:code},
				type:'post',
				success:function(data){
					console.log(data)
					if(!data){
        	toastr.error('登录失败，请重新登录');
					}else{

			        	window.location.href="/evaluation/mobilequetion"
					}
				},error:function(){
		        	toastr.error('登录失败，请重新登录');
				}
				
			})
        }
        else{
        	toastr.error('请输入验证码')
        }
      } 
      else{
    	  toastr.error('请输入正确的手机号')
      }
  })
});