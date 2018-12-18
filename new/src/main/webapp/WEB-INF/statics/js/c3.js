$().ready(function() {
	
	resizeContentWrapper();

	$(window).on('resize', function() {
		resizeContentWrapper();
	});

	function resizeContentWrapper() {
		$('.contents').height($(window).height()-$('.main-header').height()-$('.main-footer').height()-$('.tab-nav').height()-11);
	}
	
	/*$('.main-sidebar').resize(function() {
	});*/
	
	toastr.options = {
	  "closeButton": false,
	  "debug": false,
	  "progressBar": false,
	  "positionClass": "toast-top-center",
	  "onclick": null,
	  "showDuration": "400",
	  "hideDuration": "400",
	  "timeOut": "3000", 
	  "extendedTimeOut": "400",
	  "showEasing": "swing",
	  "hideEasing": "linear",
	  "showMethod": "fadeIn",
	  "hideMethod": "fadeOut"
	}
	
});


//日期格式化扩展
Date.prototype.Format = function(fmt) { 
    var o = {
        "M+": this.getMonth() + 1,
        //月份
        "d+": this.getDate(),
        //日
        "h+": this.getHours(),
        //小时
        "m+": this.getMinutes(),
        //分
        "s+": this.getSeconds(),
        //秒
        "q+": Math.floor((this.getMonth() + 3) / 3),
        //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        }
    }
    return fmt;
}

function resetDtWidth() {
	$(".dataTables_scrollHeadInner").css("width", "100%");
	$(".dataTables_scrollHeadInner table").css("width", "100%");
	$(".autowidth").css("width", "auto");
}