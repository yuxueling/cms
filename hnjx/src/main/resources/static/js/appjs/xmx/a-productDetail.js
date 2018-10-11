
$(function(){
    vm.init();
    vm.getProduct();
});

var vm = new Vue({
    el: '#site',
    data: {
        categoryTree:{},
        product:{},
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

        } ,
        listProduct: function (contCategoryId,categoryName) {

            //跳转到详情产品页

            window.location.href="/contXmx/openViewListProduct/"+contCategoryId+"/"+categoryName;


        },
        getProduct: function () {
            $.ajax({
                url: "/contXmx/getProduct",
                type: "post",
                data: {
                    langType:vm.langType
                },
                success: function (data) {
                    vm.product=data.row;
                }
            });
        }

    }
});