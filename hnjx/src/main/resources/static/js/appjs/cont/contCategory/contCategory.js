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
		                //ajaxParams: {sort:'order_num'}, // 请求数据的ajax的data属性,在此处的作用是排序
		                expandColumn: '1',// 在哪一列上面显示展开按钮
		                striped: true, // 是否各行渐变色
		                bordered: true, // 是否显示边框
		                expandAll: false, // 是否全部展开
						columns : [{
									field : 'contCategoryId', 
									title : 'ID' ,
			                        visible: false,
			                        align: 'center',
			                        valign: 'center',
			                        width: '5%'
								},{
									field : 'categoryName', 
									title : '类别名称' ,
									valign: 'center',
									width: '20%'
								} ,{
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
			                        title: '操作',
			                        field: 'contCategoryId',
			                        align: 'center',
			                        valign: 'center',
			                        formatter: function (item, index) {
			                        	var e = '<a class="btn btn-primary btn-sm '
			                                + s_edit_h
			                                + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
			                                + item.contCategoryId
			                                + '\')"><i class="fa fa-edit"></i></a> ';
			                        	var d = '<a class="btn btn-warning btn-sm '
			                                + s_remove_h
			                                + '" href="#" title="删除"  mce_href="#" onclick="remove(\''
			                                + item.contCategoryId
			                                + '\')"><i class="fa fa-remove"></i></a> ';
			                        	var f = '<a class="btn btn-primary btn-sm '
			                                + s_add_h
			                                + '" href="#" mce_href="#" title="添加下级" onclick="add(\''
			                                + item.contCategoryId
			                                + '\')"><i class="fa fa-plus"></i></a> ';
			                        	return e+d+f;
			                        }
			                    }]
					});
}
function reLoad() {
	load();
}
function add(parentCategoryId) {
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/add?parentCategoryId='+parentCategoryId // iframe的url
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

}