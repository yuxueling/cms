
$(function(){
    vm.init();
    vm.listNews();
});

var vm = new Vue({
    el: '#site',
    data: {
        categoryTree:{},
        contactInfo:{},
        langType:'english',
        events:[],
        newsList:[],
        pageTool:{
            limit:12,
            offset:0,
            currentPage:1,
            currentLength:0,
            totalPages:0,
            pageList:[]
        }
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
                    vm.events=data.state.events;
                    vm.contactInfo=data.state.contactInfo;
                }
            });

        },
        listProduct: function (contCategoryId,categoryName) {
            //跳转到详情产品页
            window.location.href="/contXmx/openViewListProduct/"+contCategoryId+"/"+categoryName;
        },
        listNews: function () {
            $.ajax({
                url: "/contXmx/listContent",
                type: "post",
                data: {
                    limit:vm.pageTool.limit,
                    offset:vm.pageTool.offset,
                    type:'news',
                    langType:vm.langType
                },
                success: function (data) {
                    vm.newsList=data.rows;
                    vm.pageTool.currentLength=data.rows.length;
                    vm.pageTool.totalPages=Math.ceil(data.total/vm.pageTool.limit);
                    vm.pageTool.pageList.length = 0;
                    for(var i=1;i<=vm.pageTool.totalPages;i++){
                        vm.pageTool.pageList.push(i);
                    }
                }
            });
        }

    }
});