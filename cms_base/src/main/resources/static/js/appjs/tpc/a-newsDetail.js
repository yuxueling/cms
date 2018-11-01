//页头
function searchA() {
    var key = $("#searchKeyA").val();
    window.location.href = "/contTpc/view/a-productCatalog";
}

function searchB() {
    var key = $("#searchKeyB").val();
    window.location.href = "/contTpc/view/a-productCatalog";
}

$(function () {
    $("#m6").addClass("on");
    $("#m1").removeClass("on");
    vm.getContent();
});


//页中
var vm = new Vue({
    el: '#main',
    data: {
        newsCont: {}
    },
    methods: {
        getContent: function () {

            $.ajax({
                url: "/contTpc/getContent",
                type: "post",
                data: {
                    type: 'companyNews'
                },
                success: function (data) {
                    vm.newsCont = data.row;
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


