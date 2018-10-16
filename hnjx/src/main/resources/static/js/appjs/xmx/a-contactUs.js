$(function () {
    vm.init();
    vm.listContent();
});

var vm = new Vue({
    el: '#site',
    data: {
        categoryTree: {},
        contactInfo: {},
        langType: 'english',
        contentList: [],
        events: []
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
        listProduct: function (contCategoryId, categoryName) {
            //跳转到详情产品页
            window.location.href = "/contXmx/openViewListProduct/" + contCategoryId + "/" + categoryName;
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