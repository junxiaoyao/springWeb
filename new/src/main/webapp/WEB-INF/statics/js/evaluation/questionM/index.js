$(document).ready(function(){
	//消除300毫秒延迟
	FastClick.attach(document.body);

  //选择事件监听
  $('body').on('click','.options_item',function(){
    //修改对应样式
	  $(this).find(".answer-circle").addClass('answer-circle-sel').find('.answer-select').css('display','inline-block')
	    $(this).siblings().find('.answer-circle').removeClass('answer-circle-sel')
	    .find('.answer-select').css('display','none')
  })
})  