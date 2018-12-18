$().ready(function() {
	var tableHeight = $('.contents').height() - 225;

	$('#mailTable').on('draw.dt', function() {
		resetDtWidth();
	}).DataTable({
		"serverSide": true,
		"ajax": {
			"url": CTX + "/sm/mail/outbox/list",
			"type": "post",
			"data": {},
			"dataSrc": function(json) {
				return json.data;
			}
		},
		//页码样式
		"pagingType": "full_numbers",
		//默认表格记录数
		"pageLength": 50,
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
		"ordering": false,
		//设置默认排序列
		"order": [
			[0, 'asc']
		],
		"processing": true,
		"autoWidth": false,
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
		"initComplete": function(settings, json) {
			resetDtWidth();
		},
		"columns": [{
			"data": "to",
			"width": "10%",
			"orderable": false
		}, {
			"data": "subject",
			"width": "70%",
			"orderable": false
		}, {
			"data": "sendTime",
			"width": "20%",
			"render": function(data, type, row, meta) {
				return (new Date(data)).Format("yyyy-MM-dd hh:mm:ss");
			}
		}],

	});
	
	/**当前选中 高亮显示*/
	$("#mailTable").on("click", "tr", function() {
		$("#mailTable tbody tr.highlight").each(function(){
			console.log($(this));
			if($(this)!=null){
				$(this).removeClass( 'highlight' );
			}
		});
		$(this).addClass('highlight');
	});
	
	/** 搜索 */
	$("#searchBtn").on('click', function() {
		$('#mailTable').DataTable().column(0).search($("#to").val(), true).draw();
	});
});