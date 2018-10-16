$(function () {
    vm.init();
    vm.listProduct();
});


var vm = new Vue({
    el: '#site',
    data: {
        categoryTree: {},
        contactInfo:{},
        langType: 'english',
        events: [],
        productList:[],
        pageTool:{
            limit:12,
            offset:0,
            currentPage:1,
            totalPages:0,
            pageList:[]
        },
        navBar:{}
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
                    vm.categoryTree = data;
                    vm.events = data.state.events;
                    vm.contactInfo = data.state.contactInfo;
                }
            });
        },
        listProduct: function (contCategoryId,categoryName) {
            $.ajax({
                url: "/contXmx/listProductByCategory",
                type: "post",
                data: {
                    limit:vm.pageTool.limit,
                    offset:vm.pageTool.offset,
                    contCategoryId:contCategoryId,
                    langType:vm.langType
                },
                success: function (data) {
                    vm.productList=data.rows;
                    vm.pageTool.totalPages=Math.ceil(data.total/vm.pageTool.limit);
                    vm.pageTool.pageList.length = 0;
                    for(var i=1;i<=vm.pageTool.totalPages;i++){
                        vm.pageTool.pageList.push(i);
                    }
                }
            });
        },
        selectPage:function (page,step) {
           if(page=='currentPage'){
               page=vm.pageTool.currentPage;
           }
           if(page=='endPage'){
               page=vm.pageTool.totalPages;
           }

            var currentPage=page+step;
            if(page<=1 && step==-1){
                currentPage=1;
            }
            if(page>=vm.pageTool.totalPages && step==1){
                currentPage=vm.pageTool.totalPages;
            }
            vm.pageTool.currentPage = currentPage;
            vm.pageTool.offset = (currentPage-1) * vm.pageTool.limit;
            vm.listProduct();
        },
        viewProduct:function (contProductId) {
            window.location.href="/contXmx/showProduct/"+contProductId;
        }


    }
});