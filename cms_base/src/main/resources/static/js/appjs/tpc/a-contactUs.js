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
    $("#m7").addClass("on");
    $("#m1").removeClass("on");
});


//页中
var vm = new Vue({
    el: '#main',
    data: {
        formDO: {},
        formDataDOList: [
            {title: 'Name', value: ''},
            {title: 'Phone', value: ''},
            {title: 'Email', value: ''},
            {title: 'Message', value: ''}
        ]
    },
    methods: {
        submit: function () {
            vm.formDO.title = "您有新消息了~";

            $.ajax({
                url: "/contTpc/saveInquery",
                type: "post",
                data: {
                    contForm: JSON.stringify(vm.formDO),
                    contFormData: JSON.stringify(vm.formDataDOList)
                },
                success: function (data) {
                    if (data.code == 0) {
                        //刷新页面
                        alert("Success ！");
                        vm.clear();
                    }
                }
            });
        },
        clear: function () {
            var formDataDOList = [
                {title: 'Name', value: ''},
                {title: 'Phone', value: ''},
                {title: 'Email', value: ''},
                {title: 'Message', value: ''}
            ];
            vm.formDataDOList = formDataDOList;
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
            vm.formDataDOList = formDataDOList;
        }
    }
});


