
$(function(){
    vm.init();
});

var vm = new Vue({
    el: '#site',
    data: {
        categoryTree:{},
        langType:'english',
        events:[]
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
                }
            });

        },
        listProduct: function (contCategoryId,categoryName) {
            //跳转到详情产品页
            window.location.href="/contXmx/openViewListProduct/"+contCategoryId+"/"+categoryName;
        }

    }
});