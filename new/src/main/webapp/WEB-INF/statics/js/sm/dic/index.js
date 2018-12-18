$().ready(function() {
	var tableHeight = $('.contents').height() - 225;

	var edit;
	$('#dictionaryTable').on('draw.dt', function() {
		resetDtWidth();
	}).DataTable({
		"serverSide": true,
		"ajax": {
			"url": CTX + "/sm/dic/list",
			"type": "post",
			"data": {
				"perms[0]": "sm_dic_te",
			},
			"dataSrc": function(json) {
				edit = json.perms.sm_dic_te;
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
			"data": "groupId",
			"width": "null",
			"visible": false
		}, {
			"data": "name",
			"width": "220px",
			"orderable": false
		}, {
			"data": "code",
			"width": "250px",
			"orderable": false
		}, {
			"data": "type",
			"width": "200px",
			"orderable": false
		}, {
			"data": "value",
			"width": "120px",
			"orderable": false
		}, {
			"data": null,
			"orderable": false,
			"width": "null",
			"className": 'dataTables_toolcolumn',
			"render": function(data, type, row, meta) {
				var content = "<i class='fa fa-edit text-primary edit' data-toggle='tooltip' data-placement='right' title='编辑'></i>";
				return content;
			}
		}],

	});
	
	/**当前选中 高亮显示*/
	$("#dictionaryTable").on("click", "tr", function() {
		$("#dictionaryTable tbody tr.highlight").each(function(){
			console.log($(this));
			if($(this)!=null){
				$(this).removeClass( 'highlight' );
			}
		});
		$(this).addClass('highlight');
	});
	
	/** 编辑 */
	$("#dictionaryTable").on("click", ".dataTables_toolcolumn .edit", function() {
		var id = $(this).parents("tr").attr("id");
		$("#modal").load(CTX + "/sm/dic/toEdit",{"id":id});
		$("#modal").modal("show");
	});

});