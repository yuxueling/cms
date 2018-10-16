
$(function(){
    vm.init();
});

var vm = new Vue({
    el: '#site',
    data: {
        categoryTree:{},
        contactInfo:{},
        productList:[],
        limit:100,
        offset:0,
        contCategoryId:0,
        langType:'english',
        categoryName:'',
        events:[],
        contentList: []
    },
    methods: {
        init: function () {

            $.ajax({
                url: "/cont/contCategory/treeInfo",
                type: "post",
                data: {
                    langType: vm.langType
                },
                success: function (data) {
                    vm.categoryTree=data;
                    var contCategoryId = data.children[0].id;
                    var categoryName = data.children[0].text;
                    vm.events=data.state.events;
                    vm.contactInfo=data.state.contactInfo;
                    //初始化产品
                    vm.listProduct(contCategoryId,categoryName);
                }
            });

        } ,
        listProduct: function (contCategoryId,categoryName) {
            vm.categoryName=categoryName;
            $.ajax({
                url: "/contXmx/listProductByCategory",
                type: "post",
                data: {
                    limit:vm.limit,
                    offset:vm.offset,
                    contCategoryId:contCategoryId,
                    langType:vm.langType
                },
                success: function (data) {
                    vm.productList=data.rows;
                }
            });
        },
        viewProduct:function (contProductId) {
            window.location.href="/contXmx/showProduct/"+contProductId;
        },
        listContent: function () {
            $.ajax({
                url: "/contXmx/listContent",
                type: "post",
                data: {
                    limit: 10,
                    offset: 0,
                    type: 'contactUs',
                    langType: vm.langType
                },
                success: function (data) {
                    vm.contentList = data.rows;
                }
            });
        }

    }
});