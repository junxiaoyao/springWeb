/// <reference path="../../jquery/jquery.d.ts" />

$(document).ready(function(){
  $('#alreadyTable').on('hidden.bs.modal', function (e) {
    //模态框关闭后销毁表格
    
  })
  $('.already').click(function(){
	var taskId = 523940001868677120
	$.ajax({
		url:CTX+'/evaluation/showfill',
		data:{taskId:taskId},
		dataType:'json',
		type:'GET',
		success:function(data){
			console.log(data)
			$('.myTable').dataTable( {
			      "data": alreadyData,
			      "columns": columns,
			      //开启自定义表格记录数功能
			      "lengthChange": false,
			      //开启搜索功能
			      "searching": false,
			      //"searchDelay": 800,
			      //开启排序功能
			      "info":false,
			      "paging": false,
			      "ordering": false,
			      "retrieve":true,
			       "destroy":true,
			      "language": {
			        "info": "当前第_PAGE_页,共_PAGES_页",
			        "infoEmpty": "无分页信息",
			        "paginate": {
			          "first": "<<",
			          "previous": "<",
			          "next": ">",
			          "last": ">>"
			        },
			        "processing": "加载中...",
			        "emptyTable": "没有相关数据"
			      },
			  } );
			$('#alreadyTable').modal({})
		},
		error:function(){
			console.log(data)
		}
	})
    
    
  })
  $('.last').click(function(){
	var taskId = 523940001868677120
	$.ajax({
		url:CTX+'/evaluation/showUnfill',
		data:{taskId:taskId},
		dataType:'json',
		type:'GET',
		success:function(data){
			console.log(data)
			lastData = data
			$('.myTable').dataTable( {
			      "data": lastData,
			      "columns": columns,
			      //开启自定义表格记录数功能
			      "lengthChange": false,
			      //开启搜索功能
			      "searching": false,
			      //"searchDelay": 800,
			      //开启排序功能
			      "info":false,
			      "paging": false,
			      "ordering": false,
			      "retrieve":true,
			       "destroy":true,
			      "language": {
			        "info": "当前第_PAGE_页,共_PAGES_页",
			        "infoEmpty": "无分页信息",
			        "paginate": {
			          "first": "<<",
			          "previous": "<",
			          "next": ">",
			          "last": ">>"
			        },
			        "processing": "加载中...",
			        "emptyTable": "没有相关数据"
			      },
			  	});
			$('#alreadyTable').modal({})
		},
		error:function(){
			console.log(data)
		}
	})
    
    
  })
});

  

var columns = [
  {title:'姓名',data:'typename',"width":'50%'},
  {title:'电话',data:'phone',"width":'50%'}
]
var alreadyData = []
var lastData = []