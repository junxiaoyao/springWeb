
$().ready(function() {
	var tableHeight = $('.contents').height() - 225;
//	var disable,edit,reset;
	$('#logTable').DataTable({
		"serverSide": true,
		"ajax": {
			"url": CTX + "/sm/log/list",
			"type": "post",
	        "data": function(data){
		      return $.extend({}, data, {
		    	  "startTime": $("#startTime").val(),
		    	  "endTime": $("#endTime").val()
	         });
	       }

//			"dataSrc": function(json) {
//				status = json.perms.sm_log_status;
//				edit = json.perms.sm_log_te;
//				reset = json.perms.sm_log_reset;
//				return json.data;
//			}
		},
		//页码样式
		"pagingType": "full_numbers",
		//默认表格记录数
		"pageLength": 18,
		//开启分页
		"paging": true,
		//表格最大高度
		"scrollY": tableHeight,
		//当显示更少的记录时，是否允许表格减少高度
		"scrollCollapse": true,
		//开启自定义表格记录数功能
		"lengthChange": false,
		//开启搜索功能
		"searching": true,
		//"searchDelay": 800,
		//开启排序功能
		"ordering": true,
		//设置默认排序列
		"order": [
			[1, 'desc']
		],
		"processing": true,
		"autoWidth": true,
		"info": true,
		"language": {
			"info": "当前第_PAGE_页,共_PAGES_页",
			"infoEmpty": "无分页信息",
			"paginate": {
				"first": "<<",
				"previous": "<",
				"next": ">",
				"last": ">>"
			},
			"lengthMenu": '每页显示<select>' + '<option value="10">10</option>' + '<option value="10">15</option>' + '<option value="20">20</option>' + '<option value="30">30</option>' + '<option value="40">40</option>' + '<option value="50">50</option>' + '<option value="-1">所有</option>' + '</select>条记录',
			"search": "全局搜索:",
			"searchPlaceholder": "Search...",
			"processing": "加载中...",
			"emptyTable": "没有相关数据"
		},
		"rowId": 'id',
		"columns": [{
			"data": "userName",
			"width": "100px",
			"orderable": false
		}, {
			"data": "createTime",
			"width": "130px",
			"render": function(data, type, row, meta){
				return (new Date(data)).Format("yyyy-MM-dd hh:mm:ss");
			}
		}, {
			"data": "ip",
			"width": "130px",
			"orderable": false
		}, {
			"data": "content",
			"orderable": false
		}]
		
	});
	
	/**当前选中 高亮显示*/
	$("#logTable").on("click", "tr", function() {
		$("#logTable tbody tr.highlight").each(function(){
			console.log($(this));
			if($(this)!=null){
				$(this).removeClass( 'highlight' );
			}
		});
		$(this).addClass('highlight');
	});
	
	$("#searchBtn").on('click', function() {
		$('#logTable').DataTable().column(0).search($("#realName").val(), true).draw();
	});
		

	
//	$("#logTable").on("click",".dataTables_toolcolumn .disable", function() {
//		
//	});
//	
//	$("#logTable").on("click",".dataTables_toolcolumn .edit", function() {
//		
//	});
//	
//	$("#logTable").on("click",".dataTables_toolcolumn .reset", function() {
//		
//	});
	
	
});