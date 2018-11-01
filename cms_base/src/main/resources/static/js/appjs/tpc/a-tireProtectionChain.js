//页头
function searchA() {
    var key=$("#searchKeyA").val();
    window.location.href = "/contTpc/view/a-productCatalog" ;
}
function searchB() {
    var key=$("#searchKeyB").val();
    window.location.href = "/contTpc/view/a-productCatalog" ;
}

$(function() {
    $("#m2").addClass("on");
    $("#m1").removeClass("on");
    vm.listContent();
});


//页中
var vm = new Vue({
    el: '#main',
    data: {
        aboutUsContList:[]
    },
    methods: {
        listContent:function () {

            $.ajax({
                url: "/contTpc/listContent",
                type: "post",
                data: {
                    type: 'companyProfile',
                    limit:3,
                    offset:0
                },
                success: function (data) {
                    vm.aboutUsContList=data.rows;
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
        submit:function () {
            vf.formDO.title="您有新消息了~";

            $.ajax({
                url: "/contTpc/saveInquery",
                type: "post",
                data: {
                    contForm: JSON.stringify(vf.formDO),
                    contFormData:JSON.stringify(vf.formDataDOList)
                },
                success: function (data) {
                    if(data.code==0){
                        //刷新页面
                        alert("Success ！");
                        vf.clear();
                    }
                }
            });
        },
        clear:function () {
            var  formDataDOList= [
                {title: 'Feedback topic', value: ''},
                {title: 'Name', value: ''},
                {title: 'E-mail', value: ''},
                {title: 'Phone', value: ''},
                {title: 'Information description', value: ''}
            ];
            vf.formDataDOList=formDataDOList;
        }
    }
});


