
$(function(){
    vm.init();
    vm.openGet();
});

var vm = new Vue({
    el: '#site',
    data: {
        categoryTree:{},
        contactInfo:{},
        product:{},
        langType:'english',
        events:[],
        contentDO:{}
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
        openGet:function () {
            $.ajax({
                url: "/contXmx/openGetNewsDetail",
                type: "get",
                success: function (data) {
                    if(data.code==0){
                        vm.contentDO=data.row;
                    }
                }
            });
        }

    }
});