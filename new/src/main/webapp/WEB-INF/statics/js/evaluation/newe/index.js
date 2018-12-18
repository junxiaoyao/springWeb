//页面主体数据
var myData = {
			data:{
				title1:'11111',
				title2:"2017年",
			}
		}
//办公室数据
var myOffice = {
		data:{
			allOffice:[],
			nowOffice:[],
			listOffice:[],
			questionData : [],
		}
}
var invesUser = {
		data:[]
}
//整个问卷ID
var evaluationId
//树形结构数据
var treeL = [];
var treeR = []

//树形结构保存数据
var inveSelect=[]
//富文本内容
var taskDes
//问卷时间标识
var taskTimeDes
//开始时间
var startDate
//结束时间
var endDate
//目标对象
var targetArr = []
//问卷内容数据

var questionData = {
		data:[]
}

$().ready(function() {
	
	//富文本编辑器
	addEdit()
	
	//点击确定后获取富文本编辑器内容，渲染在页面上
	$('#editText').find('.btn-primary').on('click',function(){
		var text = editor2.txt.html();
//		if(text ===''){
//			toastr.error('请输入文本')
//			return false
//		}
//		text = filterXSS(text)
		taskDes = text
		$('.new-info-text').html(text)
	});
	
	
	//修改年份
	$('.addYear').on('click',function(){
		var val = $(this).parent().parent().find('input').val()
		var text = myData.data.title2
		$(".new-title-bottom").html(val+text)
		taskTimeDes = val
		$(this).parent().parent().find('input').val("")
	})
	//编辑服务对象
	$('body').on("click",'.new-object-edit',function(){
		$('#choose-tree1').data('jstree', false)//这个是关键，如果不清空实例，jstree不会重新生成
		$('#choose-tree1').data('jstree', false).empty();
		$('#choose-tree2').data('jstree', false)//这个是关键，如果不清空实例，jstree不会重新生成
		$('#choose-tree2').data('jstree', false).empty();
		$('#choose-tree1').jstree("destroy")
		$('#choose-tree2').jstree("destroy")
		var officeId = $(this).attr('data-id')
		$('#chooseMan .btn-primary').attr('data-id',officeId)
		var arr
	    for(var i =0;i<inveSelect.length;i++){
	      if(inveSelect[i].id == officeId){
	        arr = inveSelect[i].userType
	      }
	    }
		console.log(arr)
		$('#choose-tree1').jstree(
				setTree(treeL)
		);
		$('#choose-tree2').jstree(
				setTree(treeR)
		);
		$('#choose-tree1').on("ready.jstree", function (e, data) {
		      if(arr){
		          $(".jstree-anchor").each(function (i, v) {
		            for(var x =0;x<arr.length;x++){
		              if($(v).parent().attr('id')==arr[x]){
		                $('#choose-tree1').jstree().select_node($(v))
		              }
		            }
		          });
		       }
		      
		})
		$('#choose-tree2').on("ready.jstree", function (e, data) {
		      if(arr){
		          $(".jstree-anchor").each(function (i, v) {
		            for(var x =0;x<arr.length;x++){
		              if($(v).parent().attr('id')==arr[x]){
		                $('#choose-tree2').jstree().select_node($(v))
		              }
		            }
		          });
		       }
		})
		$('#chooseMan').modal()
	})
	//确定保存服务对象
	$('body').on('click','#chooseMan .btn-primary',function(){
    var id = $(this).attr('data-id')
    for(var i =0;i<inveSelect.length;i++){
      if(inveSelect[i].id == id){
        inveSelect.splice(i,1)
      }
    }
    var obj = {}
    var userType = []
    $(".jstree-anchor").each(function (i, v) {
      if($(v).hasClass('jstree-clicked')){
        var selId = $(v).parent().attr('id')
        obj.id = id ;
        userType.push(selId)
      }
      
    });
    if(userType.length>0){
    	obj.userType =userType
    }
    if($.isEmptyObject(obj)){
    	console.log(1)
    	return false
    }
    inveSelect.push(obj)
    console.log(inveSelect)
	})
	//全选、反选
	$('body').on('click','.select_all',function(){
    $("#choose-tree1 .jstree-anchor").each(function (i, v) {
      if($(v).hasClass('jstree-clicked')){
        return
      }
      else{
        $('#choose-tree1').jstree().select_node($(v))
      }
    });
    $("#choose-tree2 .jstree-anchor").each(function (i, v) {
        if($(v).hasClass('jstree-clicked')){
          return
        }
        else{
          $('#choose-tree2').jstree().select_node($(v))
        }
      });
	})
  $('body').on('click','.select_out',function(){
    $("#choose-tree1 .jstree-anchor").each(function (i, v) {
      if($('#choose-tree1').jstree().is_parent ($(v))&&$(v).parent().attr('aria-level')=='1'){
        return
      }
      if($(v).hasClass('jstree-clicked')){
        $('#choose-tree1').jstree().deselect_node($(v))
      }
      else{
        $('#choose-tree1').jstree().select_node($(v))
      }
    });
    $("#choose-tree2 .jstree-anchor").each(function (i, v) {
        if($('#choose-tree2').jstree().is_parent ($(v))&&$(v).parent().attr('aria-level')=='1'){
          return
        }
        if($(v).hasClass('jstree-clicked')){
          $('#choose-tree2').jstree().deselect_node($(v))
        }
        else{
          $('#choose-tree2').jstree().select_node($(v))
        }
      });
  })
	//删除科室
	$('body').on('click','.new-object-del',function(){
		var id = $(this).attr("data-id")
		for(var i =0;i<myOffice.data.nowOffice.length;i++){
			if(myOffice.data.nowOffice[i].id===id){
				myOffice.data.listOffice.push(myOffice.data.nowOffice[i])
				myOffice.data.nowOffice.splice(i,1)
				
			}
			
		};
		RenderOffice(myOffice.data);
		RenderSelect(myOffice.data)
//		$(this).popover({
//			content:'<div class="popover"><div class="arrow"></div><div class="del-title"><i class="glyphicon glyphicon-info-sign"></i>你确定这项问卷吗？</div><div class="del-btn"><button class="del_cancel">取消</button><button class="del_confirm" data-id='+id+'>确定</button></div></div>',
//			placement:'top',
//			html:true,
//			
//		})
//		$(this).popover().show()
		
	})
	$('body').on('click','.del_confirm',function(){
		var id = $(this).attr("data-id");
		
	})
	//点击新增科室
	$('body').on('click','.new-add',function(){
		RenderSelect(myOffice.data)
		$('.add-select').toggle()
	})
	$('body').on('click','.select-option',function(){
		var id = $(this).attr("data-id")
		for(var i =0;i<myOffice.data.listOffice.length;i++){
			if(myOffice.data.listOffice[i].id==id){
				myOffice.data.nowOffice.push(myOffice.data.listOffice[i])
				myOffice.data.listOffice.splice(i,1)
				
			}
		};
		RenderOffice(myOffice.data);
		RenderSelect(myOffice.data)
		$('.select-option').hide()
	})
	
	
	
	
	

	
})



//设置树形结构
function setTree(data){
	 
	return {"core":{
        "animation" : 0,
        "themes" : { "dots": false,"icons":false ,"stripes":false},
        "check_callback" : true,
        "multiple" : true,
        'data' :  data,
        "dblclick_toggle":false,
    },
    "force_text": true,
    "plugins" : ["state","checkbox","themes", "html_data","changed"],
    "checkbox": {
        "keep_selected_style": false,//是否默认选中
        "three_state": true,//父子级别级联选择
        "tie_selection": true
    },
	 }
	
};

//获取右边主体数据
function GoRight(id){
	evaluationId = id
	inveSelect = []
	$.ajax({
		url:CTX+'/evaluation/getRight', 
		data:{generalQuesId:id},
		method:'get',
		success:function(data){
			console.log(data)
			myData.data.title1 = data.titlelift;
			myData.data.title2 = data.titleright;
			myOffice.data.allOffice = data.targets;
			myOffice.data.listOffice = data.targets;
			myOffice.data.nowOffice = [];
			myOffice.data.questionData = data.subjects
			invesUser.data=[]
			var userlist = data.invesUserType
			for(var i =0;i<userlist.length;i++){
				if(userlist[i].parentId){
					var parentId = userlist[i].parentId
					for(var j =0;j<invesUser.data.length;j++){
						if(invesUser.data[j].id == parentId){
							var obj = {}
							obj.id = userlist[i].id
							obj.text = userlist[i].typeName
							invesUser.data[j].children.push(obj)
						}
					}
				}
				else{
					var obj = {}
					obj.id=userlist[i].id;
					obj.text = userlist[i].typeName;
					obj.state = { 'opened' : true,},
					obj.children =[]
					invesUser.data.push(obj)
				}
			}
			var invesUserLeng =Math.ceil(invesUser.data.length/2)
			treeL=invesUser.data.slice(0,invesUserLeng)
			treeR=invesUser.data.slice(invesUserLeng,invesUser.data.length)
			console.log(treeL,treeR)
			RenderRight(myData)
//			RenderOffice(myOffice)
			setEndTime()
			
		}
	})
}
//设置选择时间

function setEndTime(){
	resetTime()
	var today = new Date();
	$('#datetimepicker').datetimepicker(
			{
				format: 'yyyy-mm-dd',//显示格式
				maxView:'decade',
				todayHighlight: 1,//今天高亮
				minView: "month",//设置只显示到月份
				startView:4,
				language:'zh-CN',
				forceParse: 0,
				showMeridian: 3,
				autoclose: 1,//选择后自动关闭
				initialDate:today,
				pickerPosition:'top-left',
			}
	);
	$('#startDatetimepicker').datetimepicker(
			{
				format: 'yyyy-mm-dd',//显示格式
				maxView:'decade',
				todayHighlight: 1,//今天高亮
				minView: "month",//设置只显示到月份
				startView:4,
				language:'zh-CN',
				forceParse: 0,
				showMeridian: 3,
				autoclose: 1,//选择后自动关闭
				initialDate:today,
				pickerPosition:'top-left',
			}
	);
	$('#startDatetimepicker').on('changeDate', function(ev){
		startDate = ev.date
	});
	$('#datetimepicker').on('changeDate', function(ev){
		endDate = ev.date
	});
}
//初始化时间
function resetTime(){
	var today = new Date();
	var year = today.getFullYear();
	var mon = today.getMonth()+1;
	var day = today.getDate();
	var todayText = year+'-'+mon+'-'+day;
	startDate = new Date(year,mon,day)
	endDate = new Date(year,mon,day)
	$('#datetimepicker input').val(todayText)
	$('#startDatetimepicker input').val(todayText)
}

//通过模板渲染科室
function RenderOffice(data){
	var html = template("my-object",data)
	$('.new-object').html(html)
	
}
//通过模板渲染右侧主体内容
function RenderRight(listData){
	var html = template("my-tabpanel",listData)
	$('.tab-pane').html(html)
	
}
//渲染新增选项
function RenderSelect(data){
	var html = template("select_option",data)
	$('.add-select').html(html)
}
var editor2
//富文本编辑器初始化
function addEdit(){
	
	var E = window.wangEditor
	editor2 = new E('#edit-body')
	editor2.customConfig.menus = [
	'head',  // 标题
    'bold',  // 粗体
    'fontSize',  // 字号
    'fontName',  // 字体
    'italic',  // 斜体
    'underline',  // 下划线
    'strikeThrough',  // 删除线
    'foreColor',  // 文字颜色
    'backColor',  // 背景颜色
    'justify',  // 对齐方式
    'undo',  // 撤销
    'redo'  // 重复                
    ]
	editor2.create()
}

function Submit(){
	console.log(myOffice.data.nowOffice)
	for(var i =0;i<myOffice.data.nowOffice.length;i++){
		var obj = {}
		obj.id = myOffice.data.nowOffice[i].id
		targetArr.push(obj) 
	}
	var data = {
			id:evaluationId,
			taskDes:taskDes,
			taskTimeDes:taskTimeDes,
			startDate:startDate,
			endDate:endDate,
			target:[
			        {
			        	id:4,
			        	userType:[104]
			        }
			]
			};
	$.ajax({
		url:CTX+'/evaluation/createQues', 
		headers:{
			"content-Type":"application/json"
		},
		data:JSON.stringify(data),
		method:'post',
		success:function(data){
			console.log(data)
		}
	})
}






