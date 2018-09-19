var prefix = "/cont/contCategory"
$(function() {
	load();
});

function load() {
	$('#exampleTable').bootstrapTreeTable(
					{
						id: 'contCategoryId',
		                code: 'contCategoryId',
		                parentCode: 'parentCategoryId',
		                type: "GET", // 请求数据的ajax类型
		                url: prefix + '/list', // 请求数据的ajax的url
		                ajaxParams: {sort:'order_num'}, // 请求数据的ajax的data属性
		                expandColumn: '1',// 在哪一列上面显示展开按钮
		                striped: true, // 是否各行渐变色
		                bordered: true, // 是否显示边框
		                expandAll: false, // 是否全部展开
						columns : [{
									field : 'contCategoryId', 
									title : '主键' ,
			                        visible: false,
			                        align: 'center',
			                        valign: 'center',
			                        width: '5%'
								},{
									field : 'categoryType', 
									title : '类别类型：（CmsCategoryType）' ,
									valign: 'center',
									width: '20%'
								},{
									field : 'parentCategoryId', 
									title : '上级类别' ,
									valign: 'center',
									width: '20%'
								},{
									field : 'categoryName', 
									title : '类别名称' ,
									valign: 'center',
									width: '20%'
								} ]
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
function add() {
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/add' // iframe的url
	});
}
function edit(id) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/edit/' + id // iframe的url
	});
}
function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/remove",
			type : "post",
			data : {
				'contCategoryId' : id
			},
			success : function(r) {
				if (r.code==0) {
					layer.msg(r.msg);
					reLoad();
				}else{
					layer.msg(r.msg);
				}
			}
		});
	})
}

function resetPwd(id) {
}
function batchRemove() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要删除的数据");
		return;
	}
	layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['contCategoryId'];
		});
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : prefix + '/batchRemove',
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {

	});
}