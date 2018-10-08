
$(function(){
    vm.init();
});

var vm = new Vue({
    el: '#site',
    data: {
        categoryTree:{},
        product:{},
        langType:'english'
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
                }
            });

        } ,
        getProduct: function (contCategoryId) {
            $.ajax({
                url: "/contXmx/getProduct",
                type: "post",
                data: {
                    contCategoryId:contCategoryId,
                    langType:vm.langType
                },
                success: function (data) {
                    vm.product=data.row;
                }
            });
        }

    }
});