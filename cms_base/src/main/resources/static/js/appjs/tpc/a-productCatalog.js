//页头
function searchA() {
    var key = $("#searchKeyA").val();
    window.location.href = "/contTpc/view/a-productCatalog";
}

function searchB() {
    var key = $("#searchKeyB").val();
    window.location.href = "/contTpc/view/a-productCatalog";
}

function listProduct(contCategoryId) {
    vm.listProduct(contCategoryId);
}

$(function () {
    $("#m3").addClass("on");
    $("#m1").removeClass("on");
    vm.listProduct();
    vm.listContent();
});


//页中
var vm = new Vue({
    el: '#main',
    data: {
        catalogCont: {},
        productList:[],
        categoryInfo:{},
        productName:'',
        pageTool:{
            limit:6,
            offset:0,
            total:0,
            totalPages:0
        }
    },
    methods: {
        listContent: function () {
            $.ajax({
                url: "/contTpc/listContent",
                type: "post",
                data: {
                    type: 'catalogDesc',
                    limit: 1,
                    offset: 0
                },
                success: function (data) {
                    vm.catalogCont = data.rows[0];
                }
            });
        },
        listProduct: function (contCategoryId) {
            $.ajax({
                url: "/contTpc/listProductByCategory",
                type: "post",
                data: {
                    limit: vm.pageTool.limit,
                    offset: vm.pageTool.offset,
                    contCategoryId: contCategoryId
                },
                success: function (data) {
                    vm.productList = data.rows;
                    vm.pageTool.total = data.total;
                    vm.pageTool.totalPages = Math.ceil(data.total / vm.pageTool.limit);
                }
            });


            $.ajax({
                url: "/contTpc/getCategoryInfo",
                type: "post",
                data: {
                    contCategoryId:contCategoryId,
                },
                success: function (data) {
                    if(data.code==0){
                        if(data.row){
                            vm.categoryInfo=data.row;
                        }else {
                            vm.categoryInfo={categoryName:'All Tire protection chains'};
                        }
                    }

                }
            });
        }
    }
});

//页脚
var vf = new Vue({
    el: '#footer',
    data: {
        formDO: {},
        formDataDOList: [
            {title: 'Feedback topic', value: ''},
            {title: 'Name', value: ''},
            {title: 'E-mail', value: ''},
            {title: 'Phone', value: ''},
            {title: 'Information description', value: ''}
        ]
    },
    methods: {
        submit: function () {
            vf.formDO.title = "您有新消息了~";

            $.ajax({
                url: "/contTpc/saveInquery",
                type: "post",
                data: {
                    contForm: JSON.stringify(vf.formDO),
                    contFormData: JSON.stringify(vf.formDataDOList)
                },
                success: function (data) {
                    if (data.code == 0) {
                        //刷新页面
                        alert("Success ！");
                        vf.clear();
                    }
                }
            });
        },
        clear: function () {
            var formDataDOList = [
                {title: 'Feedback topic', value: ''},
                {title: 'Name', value: ''},
                {title: 'E-mail', value: ''},
                {title: 'Phone', value: ''},
                {title: 'Information description', value: ''}
            ];
            vf.formDataDOList = formDataDOList;
        }
    }
});


